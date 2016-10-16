package se.chalmers.cid.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;

import se.chalmers.cid.models.User;

public abstract class MentorListAdapter extends BaseAdapter {

    private User mUser;
    private int mLayout;
    private LayoutInflater mInflater;
    private ArrayList<User> mUsers;
    private HashMap<String, User> mUserUids;
    private Query mQuery;
    private ChildEventListener mListener;

    protected MentorListAdapter(Activity activity, final User user, int layout) {
        mUser = user;
        mLayout = layout;
        mInflater = activity.getLayoutInflater();

        mUsers = new ArrayList<>();
        mUserUids = new HashMap<>();

        mQuery = FirebaseDatabase.getInstance().getReference("users");
        mListener = mQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousUserUid) {
                User newUser = dataSnapshot.getValue(User.class);

                if (!validateUser(newUser)) { return; }

                mUserUids.put(dataSnapshot.getKey(), newUser);

                if (previousUserUid == null) {
                    mUsers.add(0, newUser);
                } else {
                    User previousUser = mUserUids.get(previousUserUid);
                    int previousIndex = mUsers.indexOf(previousUser);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == mUsers.size()) {
                        mUsers.add(newUser);
                    } else {
                        mUsers.add(nextIndex, newUser);
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String userUid = dataSnapshot.getKey();
                User oldUser = mUserUids.get(userUid);
                User newUser = dataSnapshot.getValue(User.class);

                boolean newUserIsValid = validateUser(newUser);

                if (oldUser != null && !newUserIsValid) {
                    mUsers.remove(oldUser);
                    mUserUids.remove(userUid);
                    notifyDataSetChanged();
                } else if (newUserIsValid) {
                    if (oldUser == null) {
                        mUsers.add(newUser);
                    } else {
                        int index = mUsers.indexOf(oldUser);
                        mUsers.set(index, newUser);
                    }
                    mUserUids.put(userUid, newUser);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String userUid = dataSnapshot.getKey();
                User user = mUserUids.get(userUid);
                if (user != null) {
                    mUsers.remove(user);
                    mUserUids.remove(userUid);
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousUserUid) {
                String userUid = dataSnapshot.getKey();
                User oldUser = mUserUids.get(userUid);

                User newUser = dataSnapshot.getValue(User.class);
                int index = mUsers.indexOf(oldUser);
                mUsers.remove(index);

                if (previousUserUid == null) {
                    mUsers.add(0, newUser);
                } else {
                    User previousUser = mUserUids.get(previousUserUid);
                    int previousIndex = mUsers.indexOf(previousUser);
                    int nextIndex = previousIndex + 1;
                    if (nextIndex == mUsers.size()) {
                        mUsers.add(newUser);
                    } else {
                        mUsers.add(nextIndex, newUser);
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    private boolean validateUser(User user) {
        if (!user.isMentor()) {
            return false;
        }

        boolean haveCommonLanguages = false;
        boolean haveCommonInterests = false;

        for (String languageName : mUser.getLanguages().keySet()) {
            if (user.getLanguages().containsKey(languageName)) {
                haveCommonLanguages = true;
                break;
            }
        }

        for (String interestName : mUser.getInterests().keySet()) {
            if (user.getInterests().containsKey(interestName)) {
                haveCommonInterests = true;
                break;
            }
        }
        return haveCommonLanguages && haveCommonInterests;
    }

    public void cleanup() {
        mQuery.removeEventListener(mListener);
        mUsers.clear();
        mUserUids.clear();
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public User getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mInflater.inflate(mLayout, viewGroup, false);
        }
        User user = mUsers.get(position);
        populateView(view, user);
        return view;
    }

    protected abstract void populateView(View view, User user);

}
