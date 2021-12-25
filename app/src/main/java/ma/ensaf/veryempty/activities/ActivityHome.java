package ma.ensaf.veryempty.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import ma.ensaf.veryempty.R;
import ma.ensaf.veryempty.adapters.PostsAdapter;
import ma.ensaf.veryempty.data.Constants;
import ma.ensaf.veryempty.databinding.ActivityHomeBinding;
import ma.ensaf.veryempty.utils.PreferenceManager;

public class ActivityHome extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = ActivityHome.class.getSimpleName();

    ActivityHomeBinding binding;
    private PreferenceManager preferenceManager;
    private View parent_view;
    private PostsAdapter postsAdapter;
    BottomNavigationView bottomNavigationView;
    public static void start(Context context) {
        Intent intent = new Intent(context, ActivityHome.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @SuppressLint({"CheckResult", "SetTextI18n", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        parent_view = findViewById(android.R.id.content);
        preferenceManager = new PreferenceManager(getApplicationContext());
        initToolbar(binding.toolbar,false);
        //ImageView share = findViewById(R.id.action_like_image_view);
        initViews();
        setListeners();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    private void setListeners() {
        binding.logoutBtn.setOnClickListener(v -> signOut());
    }

    private void signOut() {
        showToast("signing out...",false);
        preferenceManager.clear();
        startActivity(new Intent(getApplicationContext(),ActivityRegister.class));
        //might generate errors
        LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
        finish();

    }

    private void initViews() {

        // show all the donors
        binding.homeContentTop.buttonFindDonor.setOnClickListener(v -> {
            ActivityDonors.start(activityContext);
        });

        // show the requests
        binding.homeContentTop.buttonViewRequests.setOnClickListener(v -> {
            ActivityRequests.start(activityContext);
        });

        // show the posts
        binding.postsRecyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        binding.postsRecyclerView.setHasFixedSize(true);
        binding.postsRecyclerView.setNestedScrollingEnabled(false);
        //set data and list adapter
        postsAdapter = new PostsAdapter(activityContext, Constants.getPosts(activityContext));
        binding.postsRecyclerView.setAdapter(postsAdapter);

        // clicking the ask for help button
        postsAdapter.SetOnItemClickListener((v, position, obj) -> {
            switch (v.getId()){
                case R.id.action_like_image_view:
                    // get the tag
                    String imageTag = (String) v.findViewById(R.id.action_like_image_view).getTag();
                    if(imageTag.equalsIgnoreCase("liked")){
                        v.findViewById(R.id.action_like_image_view).setTag("like");
                        ((ImageView)v.findViewById(R.id.action_like_image_view)).setImageResource(R.drawable.ic_heart_empty);
                    }else{
                        v.findViewById(R.id.action_like_image_view).setTag("liked");
                        ((ImageView)v.findViewById(R.id.action_like_image_view)).setImageResource(R.drawable.ic_heart_filled);
                    }
                    break;
                case R.id.action_share_image_view:
                    //Snackbar.make(parent_view, "Share Clicked...", Snackbar.LENGTH_LONG).show();
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Hey, I think you might be interested in this Post from the Droppy App \"Link_to_Post\"";
                    String shareSub = "DROPPY Post";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share using"));
                    break;
                /*case R.id.action_comment_image_view:
                    Snackbar.make(parent_view, "Comment Clicked...", Snackbar.LENGTH_LONG).show();
                    break;*/
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.person:
                intent = new Intent(this, ActivityUserProfile.class);
                startActivity(intent);
                return true;

            case R.id.home:
                Toast.makeText(this, "This is Home!", Toast.LENGTH_LONG).show();
                return true;

            case R.id.settings:
                intent = new Intent(this, ActivityRequestBlood.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}
