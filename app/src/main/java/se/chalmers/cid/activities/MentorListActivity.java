package se.chalmers.cid.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


import java.util.HashMap;
import java.util.Map;

import se.chalmers.cid.R;
import se.chalmers.cid.models.User;


public class MentorListActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabaseUser;
    private ListView mListView;
    private String userId;
    private User user;
    private ArrayList<User> mentors;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private HashMap<String, User> users;
    private ArrayList<String> mentorNames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        mentors = new ArrayList<User>();
        mentorNames = new ArrayList<String>();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user == null) {
                    Intent intent = new Intent(MentorListActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        };

        Log.d("true/false", "framme1");
        ValueEventListener mentorListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                GenericTypeIndicator<HashMap<String, User>> t = new GenericTypeIndicator<HashMap<String, User>>() {
                };
                users = dataSnapshot.getValue(t);
                Log.d("true/false", "framme2");
                int i = 0;
                for (Map.Entry<String, User> entry : users.entrySet()) {
                //    Log.d("hej", "UserID : " + entry.getKey() + " Count : " + entry.getValue());
                  //  Log.d("hej", "UserID : " + " Count : " + entry.getValue().getRole());
                    Log.d("true/false", "framme3");
                    if (1 == entry.getValue().getRole()) {
                        Log.d("true/false", "framme4");
                        mentors.add(entry.getValue());
                        mentorNames.add(entry.getValue().getName() + " \n " + entry.getValue().getPrefContactWay());
                        i++;
                        Log.d("hej", "ÄR INNE I LOOPEN");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("hej", "loadPost:onCancelled", databaseError.toException());
            }
        };


        mDatabase.addValueEventListener(mentorListener2);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.activity_listitem, mentorNames); // feeds random data looking like activity_listitem

        ListView listView = (ListView) findViewById(R.id.listView); //the list view with id "listView"
        listView.setAdapter(adapter);       //adapter feeds it

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {

            //mAuth.signOut();
            Intent intent = new Intent(MentorListActivity.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

