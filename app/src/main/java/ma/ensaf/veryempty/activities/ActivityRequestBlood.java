package ma.ensaf.veryempty.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import ma.ensaf.veryempty.R;
import ma.ensaf.veryempty.databinding.ActivityRequestBloodBinding;


public class ActivityRequestBlood extends BaseActivity {

    private static final String TAG = ActivityRequestBlood.class.getSimpleName();

    ActivityRequestBloodBinding binding;

    String[] persons = {"Select Person Type","Friend", "Family", "Relative", "Patient", "Work Colleague", "Anonymous"};

    String[] cities = {"Select City","Mumbai", "Bengaluru", "Chennai", "Kolkata", "New Delhi", "Sorat"};

    private View parent_view;

    public static void start(Context context) {
        Intent intent = new Intent(context, ActivityRequestBlood.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @SuppressLint({"CheckResult", "SetTextI18n", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_request_blood);
        parent_view = findViewById(android.R.id.content);

        initToolbar(binding.toolbar,true);
        setToolbarTitle(null);

        // requesting for spinner
        ArrayAdapter personsAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, persons);
        personsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.requestForSpinner.setAdapter(personsAdapter);
        binding.requestForSpinner.setSelection(0,false);
        binding.requestForSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    Toast.makeText(activityContext,parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // cities spinner
        ArrayAdapter citiesAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cities);
        citiesAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        binding.citySpinner.setAdapter(citiesAdapter);
        binding.citySpinner.setSelection(0,false);
        binding.citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    Toast.makeText(activityContext,parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // listener for blood group radio groups
        binding.bloodGroupRadioGroup1.setOnCheckedChangeListener(listener);
        binding.bloodGroupRadioGroup2.setOnCheckedChangeListener(listener);
        binding.bloodGroupRadioGroup3.setOnCheckedChangeListener(listener);
        binding.bloodGroupRadioGroup4.setOnCheckedChangeListener(listener);
        binding.bloodGroupRadioGroup5.setOnCheckedChangeListener(listener);
        binding.bloodGroupRadioGroup6.setOnCheckedChangeListener(listener);
        binding.bloodGroupRadioGroup7.setOnCheckedChangeListener(listener);
        binding.bloodGroupRadioGroup8.setOnCheckedChangeListener(listener);

        // show next screen
        binding.buttonRequestBlood.setOnClickListener(v -> {
            ActivityHome.start(activityContext);
        });
    }

    private RadioGroup.OnCheckedChangeListener listener = (group, checkedId) -> {
        switch (group.getId()){
            case R.id.blood_group_radio_group_1:
                resetRadios(group.getId(),R.id.blood_group_radio_group_2,R.id.blood_group_radio_group_3,R.id.blood_group_radio_group_4,R.id.blood_group_radio_group_5,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_8);
                break;
            case R.id.blood_group_radio_group_2:
                resetRadios(group.getId(),R.id.blood_group_radio_group_1,R.id.blood_group_radio_group_3,R.id.blood_group_radio_group_4,R.id.blood_group_radio_group_5,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_8);
                break;
            case R.id.blood_group_radio_group_3:
                resetRadios(group.getId(),R.id.blood_group_radio_group_1,R.id.blood_group_radio_group_2,R.id.blood_group_radio_group_4,R.id.blood_group_radio_group_5,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_8);
                break;
            case R.id.blood_group_radio_group_4:
                resetRadios(group.getId(),R.id.blood_group_radio_group_1,R.id.blood_group_radio_group_2,R.id.blood_group_radio_group_3,R.id.blood_group_radio_group_5,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_8);
                break;
            case R.id.blood_group_radio_group_5:
                resetRadios(group.getId(),R.id.blood_group_radio_group_1,R.id.blood_group_radio_group_2,R.id.blood_group_radio_group_3,R.id.blood_group_radio_group_4,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_8);
                break;
            case R.id.blood_group_radio_group_6:
                resetRadios(group.getId(),R.id.blood_group_radio_group_1,R.id.blood_group_radio_group_2,R.id.blood_group_radio_group_3,R.id.blood_group_radio_group_4,R.id.blood_group_radio_group_5,R.id.blood_group_radio_group_7,R.id.blood_group_radio_group_8);
                break;
            case R.id.blood_group_radio_group_7:
                resetRadios(group.getId(),R.id.blood_group_radio_group_1,R.id.blood_group_radio_group_2,R.id.blood_group_radio_group_3,R.id.blood_group_radio_group_4,R.id.blood_group_radio_group_5,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_8);
                break;
            case R.id.blood_group_radio_group_8:
                resetRadios(group.getId(),R.id.blood_group_radio_group_1,R.id.blood_group_radio_group_2,R.id.blood_group_radio_group_3,R.id.blood_group_radio_group_4,R.id.blood_group_radio_group_5,R.id.blood_group_radio_group_6,R.id.blood_group_radio_group_7);
                break;
        }
    };

    private void resetRadios(int checkedId, int radio_group_1, int radio_group_2, int radio_group_3, int radio_group_4, int radio_group_5
            , int radio_group_6, int radio_group_7) {

        // remove the listeners before clearing so we don't throw an exception
        ((RadioGroup)findViewById(radio_group_1)).setOnCheckedChangeListener(null);
        ((RadioGroup)findViewById(radio_group_2)).setOnCheckedChangeListener(null);
        ((RadioGroup)findViewById(radio_group_3)).setOnCheckedChangeListener(null);
        ((RadioGroup)findViewById(radio_group_4)).setOnCheckedChangeListener(null);
        ((RadioGroup)findViewById(radio_group_5)).setOnCheckedChangeListener(null);
        ((RadioGroup)findViewById(radio_group_6)).setOnCheckedChangeListener(null);
        ((RadioGroup)findViewById(radio_group_7)).setOnCheckedChangeListener(null);
        // clear all the other radion groups
        ((RadioGroup)findViewById(radio_group_1)).clearCheck();
        ((RadioGroup)findViewById(radio_group_2)).clearCheck();
        ((RadioGroup)findViewById(radio_group_3)).clearCheck();
        ((RadioGroup)findViewById(radio_group_4)).clearCheck();
        ((RadioGroup)findViewById(radio_group_5)).clearCheck();
        ((RadioGroup)findViewById(radio_group_6)).clearCheck();
        ((RadioGroup)findViewById(radio_group_7)).clearCheck();
        //reset the listeners
        ((RadioGroup)findViewById(radio_group_1)).setOnCheckedChangeListener(listener);
        ((RadioGroup)findViewById(radio_group_2)).setOnCheckedChangeListener(listener);
        ((RadioGroup)findViewById(radio_group_3)).setOnCheckedChangeListener(listener);
        ((RadioGroup)findViewById(radio_group_4)).setOnCheckedChangeListener(listener);
        ((RadioGroup)findViewById(radio_group_5)).setOnCheckedChangeListener(listener);
        ((RadioGroup)findViewById(radio_group_6)).setOnCheckedChangeListener(listener);
        ((RadioGroup)findViewById(radio_group_7)).setOnCheckedChangeListener(listener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
