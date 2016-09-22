package se.chalmers.cid;

import android.databinding.DataBindingUtil;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import se.chalmers.cid.adapter.InterestAdapter;
import se.chalmers.cid.databinding.ActivityProfileBinding;
import se.chalmers.cid.models.User;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
		User user = new User("Kebabmannen","1995","0730465775","AbraKEBABra, the lover of kebabs <3 with no abs!");
		binding.setUser(user);
		GridView interestGrid = (GridView) findViewById(R.id.interestList);
		interestGrid.setAdapter(new InterestAdapter(this));
		interestGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ImageView img = (ImageView) view;

				if(img.getImageAlpha() == 70){
					img.setImageAlpha(255);
				} else {
					img.setImageAlpha(70);
				}
				//view = img;

				//Toast.makeText(MainActivity.this, "Kebab" + img.getImageAlpha(), Toast.LENGTH_SHORT).show();
			}
		});
		setDynamicHeight(interestGrid);
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

}
