package se.chalmers.cid.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import se.chalmers.cid.models.Attributes;
import se.chalmers.cid.models.User;

public class ProfileActivity extends BaseActivity {

    private User user;

    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onUserDataLoaded() {
        setContentView(R.layout.activity_profile);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        binding.setUser(user);




        if (!mUser.getId().equals(user.getId())) {
            findViewById(R.id.profileName).setFocusable(false);
            findViewById(R.id.biographyText).setFocusable(false);
            findViewById(R.id.profileAge).setFocusable(false);
        }

        if (user.getGender() != null) {
            ((ImageView) (findViewById(R.id.sexImg))).setImageResource(Attributes.GENDERS.get(user.getGender()).getImage());
        }

        if (!user.getContactWays().containsKey("Phone")) {
            findViewById(R.id.phoneImgButton).setVisibility(View.INVISIBLE);
        }

        if (!user.getContactWays().containsKey("Email")) {
            findViewById(R.id.emailImgButton).setVisibility(View.INVISIBLE);
        }

        if (!user.getContactWays().containsKey("Facebook")) {
            findViewById(R.id.facebookImgButton).setVisibility(View.INVISIBLE);
        }

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
            int rows = items / 5 + 1;
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit:
                Intent intent = new Intent(ProfileActivity.this, FirstTimeSetupRoleActivity.class);
                intent.putExtra("users",mUser);
                startActivity(intent);
                finish();
                return true;
            case android.R.id.home:
                
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public void phoneButton(View v) {
        String phoneNumber = user.getContactWays().get("Phone");
        if (phoneNumber != null) {
            dialPhoneNumber(phoneNumber);
        }
    }

    private void dialPhoneNumber(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
        finish();
    }

    public void mailButton(View v) {
        String emailAddress = user.getContactWays().get("Email");
        if (emailAddress != null) {
            sendEmail(emailAddress);
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

    public void facebookButton(View v) {
        String facebookUsername = user.getContactWays().get("Facebook");
        if (facebookUsername != null) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fixUrl(facebookUsername))));
        }
    }

    private String fixUrl(String url) {
        if (url.contains("com/")) {
            url = url.substring(url.lastIndexOf("com/") + 4);
        }
        return "https://m.facebook.com/" + url;
    }

}
