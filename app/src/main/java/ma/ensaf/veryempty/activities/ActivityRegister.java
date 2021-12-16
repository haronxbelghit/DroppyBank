package ma.ensaf.veryempty.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import ma.ensaf.veryempty.R;
import ma.ensaf.veryempty.data.Tools;
import ma.ensaf.veryempty.databinding.ActivityRegisterBinding;

public class ActivityRegister extends BaseActivity {

    private static final String TAG = ActivityRegister.class.getSimpleName();

    ActivityRegisterBinding binding;

    public static void start(Context context) {
        Intent intent = new Intent(context, ActivityRegister.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // make status transparent
        Tools.setStatusBarTransparent(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        // go to the register activity
        binding.mobilePhoneLayout.setOnClickListener(v -> ActivityPhoneRegistration.start(activityContext));
    }
}
