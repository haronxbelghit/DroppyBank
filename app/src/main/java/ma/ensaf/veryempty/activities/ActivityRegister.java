package ma.ensaf.veryempty.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseAuth;

import ma.ensaf.veryempty.R;
import ma.ensaf.veryempty.data.Tools;
import ma.ensaf.veryempty.databinding.ActivityRegisterBinding;
import ma.ensaf.veryempty.utils.PreferenceManager;

public class ActivityRegister extends BaseActivity {

    private static final String TAG = ActivityRegister.class.getSimpleName();
    ActivityRegisterBinding binding;
    public static final String FACEBOOK_TAG="FACEBOOK";
    private static final int RC_SIGN_IN=12345;
    protected PreferenceManager preferenceManager;
    protected FirebaseAuth mAuth;

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
        //facebook setting
        preferenceManager = new PreferenceManager(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        setListeners();
    }

    private void setListeners()  {
        binding.registerLink.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),SignUpActivity.class)));
        binding.signInLayout.setOnClickListener(v ->startActivity(new Intent(getApplicationContext(),SignInActivity.class)));
        binding.facebookBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),FacebookAuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        });

    }
}
