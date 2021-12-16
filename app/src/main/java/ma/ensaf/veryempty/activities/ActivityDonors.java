package ma.ensaf.veryempty.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import ma.ensaf.veryempty.R;
import ma.ensaf.veryempty.adapters.DonorsAdapter;
import ma.ensaf.veryempty.data.Constants;
import ma.ensaf.veryempty.databinding.ActivityDonorsBinding;
import ma.ensaf.veryempty.models.HeaderItem;
import ma.ensaf.veryempty.models.RowItem;
import ma.ensaf.veryempty.models.Users;
import ma.ensaf.veryempty.models.UsersListItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ActivityDonors extends BaseActivity {

    private static final String TAG = ActivityDonors.class.getSimpleName();

    ActivityDonorsBinding binding;

    private View parent_view;

    private DonorsAdapter donorsAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, ActivityDonors.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @SuppressLint({"CheckResult", "SetTextI18n", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_donors);
        parent_view = findViewById(android.R.id.content);

        initToolbar(binding.toolbar,true);

        bindRecyclerView();
    }

    private void bindRecyclerView() {
        // show the list of donors
        binding.donorsRecyclerView.setLayoutManager(new LinearLayoutManager(activityContext));
        binding.donorsRecyclerView.setHasFixedSize(true);
        binding.donorsRecyclerView.setNestedScrollingEnabled(false);
        //set data and list adapter
        donorsAdapter = new DonorsAdapter(activityContext, null);
        binding.donorsRecyclerView.setAdapter(donorsAdapter);

        // clicking the ask for help button
        donorsAdapter.SetOnItemClickListener((v, position, obj) -> {
            Log.e("position", ((RowItem) obj).getUsers().getName());
        });
    }

    private void groupDataIntoHashMap(List<Users> usersList) {

        LinkedHashMap<String, Set<Users>> groupedHashMap = new LinkedHashMap<>();

        Set<Users> list = null;
        for (Users userObj : usersList) {
            String hashMapKey = userObj.getLastDonatedDate();
            if (groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                Objects.requireNonNull(groupedHashMap.get(hashMapKey)).add(userObj);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                list = new LinkedHashSet<>();
                list.add(userObj);
                groupedHashMap.put(hashMapKey, list);
            }
        }

        //Generate list from map
        generateListFromMap(groupedHashMap);
    }

    private void generateListFromMap(LinkedHashMap<String, Set<Users>> groupedHashMap) {
        // We linearly add every item into the consolidatedList.
        List<UsersListItem> consolidatedList = new ArrayList<>();
        for (String date : groupedHashMap.keySet()) {
            HeaderItem headerItem = new HeaderItem();
            headerItem.setDate(date);
            consolidatedList.add(headerItem);
            for (Users userModel : Objects.requireNonNull(groupedHashMap.get(date))) {
                RowItem rowItem = new RowItem();
                rowItem.setUsers(userModel);
                consolidatedList.add(rowItem);
            }
        }

        donorsAdapter.setUsersListItemList(consolidatedList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        groupDataIntoHashMap(Constants.getUsers(activityContext));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }else{
            Snackbar.make(parent_view, item.getTitle()+" clicked", Snackbar.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_donors, menu);
        return true;
    }
}

