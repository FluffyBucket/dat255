package se.chalmers.cid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.cid.R;
import se.chalmers.cid.adapter.InterestAdapter;
import se.chalmers.cid.adapter.LanguageAdapter;
import se.chalmers.cid.models.Language;
import se.chalmers.cid.models.User;

public class FirstTimeSetupLanguagesActivity extends AppCompatActivity {

    private User user;
    private List<Integer> userLang = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_setup_languages);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        GridView languageGrid = (GridView) findViewById(R.id.languageList);
        languageGrid.setAdapter(new LanguageAdapter(this));
        languageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view;

                if (userLang.contains(position)) {
                    userLang.remove((Integer) position);
                    img.setImageAlpha(70);
                } else {
                    userLang.add(position);
                    img.setImageAlpha(255);
                }                //view = img;

                //Toast.makeText(MainActivity.this, "Kebab" + img.getImageAlpha(), Toast.LENGTH_SHORT).show();
            }
        });

        setDynamicHeight(languageGrid);
    }

    public void nextActivity(View v){

        Intent intent = new Intent(this,FirstTimeSetupInterestsActivity.class);
        user.setLanguages(userLang);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }

    private void setDynamicHeight(GridView gridView) {
        ListAdapter gridViewAdapter = gridView.getAdapter();
        if (gridViewAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = gridViewAdapter.getCount();
        int rows = 0;

        View listItem = gridViewAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = 200;//listItem.getMeasuredHeight();


        float x = 1;
        if( items > 5 ){
            x = items/5;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }

    public void previousActivity(View v) {
        Intent intent = new Intent(this,FirstTimeSetupBasicsActivity.class);
        user.setLanguages(userLang);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();
    }
}
