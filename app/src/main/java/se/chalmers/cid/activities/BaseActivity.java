package se.chalmers.cid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import se.chalmers.cid.models.User;

public abstract class BaseActivity extends AppCompatActivity {

    private static boolean mPersistenceEnabled = false;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    protected DatabaseReference mDatabaseReference;
    protected FirebaseUser mFirebaseUser;
    protected User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!mPersistenceEnabled) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            mPersistenceEnabled = true;
        }
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mFirebaseUser = firebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    mDatabaseReference.child(mFirebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                mUser = dataSnapshot.getValue(User.class);
                                onUserDataLoaded();
                            } else {
                                onUserDataDoesNotExist();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                } else {
                    onUserSignedOut();
                }
            }
        };
    }

    protected abstract void onUserDataLoaded();

    protected void onUserDataDoesNotExist() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    protected void onUserSignedOut() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

}
