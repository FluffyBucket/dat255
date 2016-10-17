package se.chalmers.cid.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import se.chalmers.cid.R;
import se.chalmers.cid.adapter.InterestAdapter;
import se.chalmers.cid.databinding.ActivityProfileBinding;
import se.chalmers.cid.models.User;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onUserDataLoaded() {
        setContentView(R.layout.activity_profile);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        binding.setUser(user);

        if (!mUser.getId().equals(user.getId())) {
            findViewById(R.id.profileName).setFocusable(false);
            findViewById(R.id.biographyText).setFocusable(false);
            findViewById(R.id.profileAge).setFocusable(false);
        }

        fixGenderIcon(user);

        GridView interestGrid = (GridView) findViewById(R.id.interestList);
        interestGrid.setAdapter(new InterestAdapter(this, user));
        interestGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view;

                if (img.getImageAlpha() == 70) {
                    img.setImageAlpha(255);
                } else {
                    img.setImageAlpha(70);
                }
            }
        });
        setDynamicHeight(interestGrid);
    }

    private void fixGenderIcon(User user) {
        switch (user.getGender()){
            case "male":
                ((ImageView)(findViewById(R.id.sexImg))).setImageResource(R.drawable.ic_male);
                break;
            case "female":
                ((ImageView)(findViewById(R.id.sexImg))).setImageResource(R.drawable.ic_female);
                break;
            case "neutral":
                ((ImageView)(findViewById(R.id.sexImg))).setImageResource(R.drawable.ic_neutral);
                break;
            case "apache":
                ((ImageView)(findViewById(R.id.sexImg))).setImageResource(R.drawable.ic_apache);
                break;
            default:
                ((ImageView)(findViewById(R.id.sexImg))).setImageResource(R.drawable.ic_questionmark);
                break;
        }
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

        if (items > 5) {
            float x = items / 5;
            int rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                            finish();
                        }
                    });
        }
        if (item.getItemId() == R.id.action_mentorlist) {

            Intent intent = new Intent(this, MentorListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void phoneButton(View v){
        if(!(mUser.getPhone().isEmpty())){
            dialPhoneNumber(mUser.getPhone());
        } else {
            Toast.makeText(getApplicationContext(), "This person has no phone number currently...", Toast.LENGTH_SHORT).show();
        }
    }
    private void dialPhoneNumber(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
    public void mailButton(View v){
        if(!(mUser.getEmail().isEmpty())){
            sendEmail(mUser.getEmail());
        } else {
            Toast.makeText(getApplicationContext(), "This person has no phone number currently...", Toast.LENGTH_SHORT).show();
        }
    }
    protected void sendEmail(String mail) {
        Log.i("Send email", "");

        String[] TO = {mail};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Mentorite");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi, I found you on Mentorite and would like for you to become my mentor!");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
    public void facebookButton(View v){
        if(!(mUser.getFacebook().isEmpty())){
            fixUrl(mUser.getFacebook());
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fixUrl(mUser.getFacebook()))));
        } else {
            Toast.makeText(getApplicationContext(), "This person has no Facebook currently...", Toast.LENGTH_SHORT).show();
        }


    }

    private String fixUrl(String url){
        if(url.contains("com/")){
            url = url.substring(url.lastIndexOf("com/") + 4);
        }
        url = "https://m.facebook.com/" + url;
        return url;
    }

}
