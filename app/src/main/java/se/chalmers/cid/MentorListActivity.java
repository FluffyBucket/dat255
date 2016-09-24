package se.chalmers.cid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MentorListActivity extends AppCompatActivity {

    String[] randomData = {"Fredrik", "Per-Einar", "Fritjolf", "Rudolf", "Emilie", "Håkon", "Snoop Dogg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listitem, randomData);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);




    }
}
