package ma.ensaf.veryempty.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import ma.ensaf.veryempty.R;
import ma.ensaf.veryempty.databinding.ActivityRegisterDonorBinding;

public class ActivityRegisterDonor extends BaseActivity {

    private static final String TAG = ActivityRegisterDonor.class.getSimpleName();

    ActivityRegisterDonorBinding binding;

    private View parent_view;
    boolean maleGenderPressed = false;
    boolean femaleGenderPressed = false;

    String selectedSex = null;

    public static void start(Context context) {
        Intent intent = new Intent(context, ActivityRegisterDonor.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @SuppressLint({"CheckResult", "SetTextI18n", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_donor);
        parent_view = findViewById(android.R.id.content);

        initToolbar(binding.toolbar,true);
        setToolbarTitle(null);

        // on click male gender
        binding.genderMaleImageView.setOnClickListener(v -> {
            // toggle button
            toggleGenderButtonStates(true);
        });

        // on click female gender
        binding.genderFemaleImageView.setOnClickListener(v -> {
            // toggle button
            toggleGenderButtonStates(false);
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
        binding.buttonRegisterDonor.setOnClickListener(v -> {
            ActivityRequestBlood.start(activityContext);
        });

//        Tools.systemBarLollipopTransparent(this);
    }

    private void toggleGenderButtonStates(boolean isMale) {
        if(isMale){
            selectedSex = "male";
            // set male views
            binding.genderMaleImageView.setImageState(new int[] {android.R.attr.state_pressed},true);
            binding.genderMaleImageView.setColorFilter(ContextCompat.getColor(activityContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.genderMaleTextView.setTextColor(ContextCompat.getColor(activityContext,R.color.be_hero_dark));

            // reset female views
            binding.genderFemaleImageView.setImageState(new int[] {-android.R.attr.state_pressed},true);
            binding.genderFemaleImageView.setColorFilter(ContextCompat.getColor(activityContext, R.color.be_hero_dark_grey), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.genderFemaleTextView.setTextColor(ContextCompat.getColor(activityContext,R.color.be_hero_dark_grey));
        }else{
            selectedSex = "female";
            // reset male views
            binding.genderMaleImageView.setImageState(new int[] {-android.R.attr.state_pressed},true);
            binding.genderMaleImageView.setColorFilter(ContextCompat.getColor(activityContext, R.color.be_hero_dark_grey), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.genderMaleTextView.setTextColor(ContextCompat.getColor(activityContext,R.color.be_hero_dark_grey));
            // set female views
            binding.genderFemaleImageView.setImageState(new int[] {android.R.attr.state_pressed},true);
            binding.genderFemaleImageView.setColorFilter(ContextCompat.getColor(activityContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            binding.genderFemaleTextView.setTextColor(ContextCompat.getColor(activityContext,R.color.be_hero_dark));
        }
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

