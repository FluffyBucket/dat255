package se.chalmers.cid;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
	}
}
