package se.chalmers.cid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.HashMap;
import java.util.HashSet;

import se.chalmers.cid.R;
import se.chalmers.cid.adapter.LanguageAdapter;
import se.chalmers.cid.models.User;

public class FirstTimeSetupLanguagesActivity extends AppCompatActivity {

    private User mUser;
    private HashSet<String> mLanguageNames = new HashSet<>();
    private LanguageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_setup_languages);

        Intent intent = getIntent();
        mUser = (User) intent.getSerializableExtra("user");

        mAdapter = new LanguageAdapter(this, mUser);

        GridView languageGrid = (GridView) findViewById(R.id.languageList);
        languageGrid.setAdapter(mAdapter);
        languageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view;
                String languageName = mAdapter.getItem(position);
                if (mLanguageNames.contains(languageName)) {
                    mLanguageNames.remove(languageName);
                    img.setImageAlpha(70);
                } else {
                    mLanguageNames.add(languageName);
                    img.setImageAlpha(255);
                }
            }
        });

        setDynamicHeight(languageGrid);
    }

    public void nextActivity(View v){
        HashMap<String, Boolean> languages = new HashMap<>();
        for (String languageName : mLanguageNames) {
            languages.put(languageName, true);
        }
        mUser.setLanguages(languages);

        Intent intent = new Intent(this,FirstTimeSetupInterestsActivity.class);
        intent.putExtra("user", mUser);
        startActivity(intent);
        finish();
    }

    private void setDynamicHeight(GridView gridView) {
        ListAdapter gridViewAdapter = gridView.getAdapter();
        if (gridViewAdapter == null) {
            // pre-condition
            return;
        }

        View listItem = gridViewAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        int totalHeight = 200; //listItem.getMeasuredHeight();

        int items = gridViewAdapter.getCount();

        if( items > 5 ){
            float x = items / 5;
            int rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }


    public void previousActivity(View v) {
        Intent intent = new Intent(this,FirstTimeSetupBasicsActivity.class);
        intent.putExtra("user",mUser);

        startActivity(intent);
        finish();
    }

}
