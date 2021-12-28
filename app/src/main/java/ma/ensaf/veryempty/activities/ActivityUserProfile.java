package ma.ensaf.veryempty.activities;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import ma.ensaf.veryempty.R;
import ma.ensaf.veryempty.adapters.PostsAdapter;
import ma.ensaf.veryempty.data.Constants;
import ma.ensaf.veryempty.databinding.ActivityUserProfileBinding;
import ma.ensaf.veryempty.models.CUsers;
import ma.ensaf.veryempty.models.Posts;
import ma.ensaf.veryempty.models.RowItem;
import ma.ensaf.veryempty.models.Users;

public class ActivityUserProfile extends BaseActivity {

    private static final String TAG = ActivityUserProfile.class.getSimpleName();

    ActivityUserProfileBinding binding;

    private View parent_view;
    private PostsAdapter postsAdapter;
    private Users parsedUserObj;

    public static void start(Context context, Users obj) {
        Intent intent = new Intent(context, ActivityUserProfile.class);
        intent.putExtra(Constants.USER_EXTRA_OBJECT, obj);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile);
        parent_view = findViewById(android.R.id.content);

        // deserialize object from string to object class.
        parsedUserObj = (Users) getIntent().getSerializableExtra(Constants.USER_EXTRA_OBJECT);

        if (parsedUserObj == null) {
            finish();
        }

        initToolbar(binding.toolbar,true);

        // show the data in the views
        initViews();
        setListeners();
        bindRecyclerView();
    }

    private void setListeners() {
        TextView contactBtn = findViewById(R.id.user_action_contact);
        contactBtn.setOnClickListener(v -> {
            String number =parsedUserObj.getPhoneNumber();
            Uri uri = Uri.parse("tel:"+number);
            startActivity(new Intent(Intent.ACTION_CALL, uri ));
        });
    }

    private void initViews() {
        binding.usernameTextView.setText(parsedUserObj.getName());
        byte[] bytes = Base64.decode(parsedUserObj.getImage(),Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.userContentTop.userImageView.setImageBitmap(bitmap);;
        binding.userContentTop.userLocationTextView.setText(parsedUserObj.getLocation());
        binding.userContentTop.userDescriptionTextView.setText(activityContext.getString(R.string.medium_lorem_ipsum));
    }

    private void bindRecyclerView() {
        // show the list of recent activity
        binding.recentActivityRecyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        binding.recentActivityRecyclerView.setHasFixedSize(true);
        binding.recentActivityRecyclerView.setNestedScrollingEnabled(false);
        //set data and list adapter
        postsAdapter = new PostsAdapter(activityContext, null);
        binding.recentActivityRecyclerView.setAdapter(postsAdapter);
    }

    private void generateListForRecentActivity(List<Posts> posts) {
        // Create new list of posts
        // by filtering the post related to this user
        List<Posts> newPostsList = new ArrayList<>();

        for (Posts posts1:posts) {
            String str_user_id = String.valueOf(posts1.getUser().getId());
            if (str_user_id.toLowerCase().equalsIgnoreCase(String.valueOf(parsedUserObj.getId()))) {
                newPostsList.add(posts1);
            }
        }

        // refresh the adapter
        postsAdapter.setPostsList(newPostsList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        generateListForRecentActivity(Constants.getPosts(activityContext));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        return true;
    }
}