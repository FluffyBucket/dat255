package se.chalmers.cid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import se.chalmers.cid.R;
import se.chalmers.cid.adapter.MentorListAdapter;
import se.chalmers.cid.models.User;


public class MentorListActivity extends BaseActivity {

    private MentorListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_list);
    }

    @Override
    protected void onUserDataLoaded() {
        mAdapter = new MentorListAdapter(this, mUser, android.R.layout.simple_list_item_1) {
            @Override
            protected void populateView(View view, User user) {
                ((TextView) view.findViewById(android.R.id.text1)).setText(user.getName());
            }
        };
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MentorListActivity.this, ProfileActivity.class);
                User user = mAdapter.getItem(position);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

}
