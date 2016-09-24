package se.chalmers.cid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MentorListActivity extends AppCompatActivity {

    String[] randomData = {"Fredrik \n 23224244", "Per-Einar \n 523224244", "Fritjolf \n 3224244",
            "Reidar Ramb \n +48 553244", "Per-Einar \n 07352222", "Fritjolf \n +523224244",
            "Frank Sinatra \n Snapchat: frankyboy1337", "Gandalf \n gandalfdagray@hotmail.se",
            "Rudolf \n 554244", "Emilie \n +2349995", "Håkon \n Haakon13@mail.com",
            "Liam \n 073528838, txt me (;", "Snoop Dogg \n Snoop@dogg.com"};
    //for more advanced data look into "simple cursor adapter"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);


        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.activity_listitem, randomData); // feeds random data looking like activity_listitem

        ListView listView = (ListView) findViewById(R.id.listView); //the list view with id "listView"
        listView.setAdapter(adapter);       //adapter feeds it




    }
}
