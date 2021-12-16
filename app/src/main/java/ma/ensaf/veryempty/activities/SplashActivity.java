package ma.ensaf.veryempty.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import ma.ensaf.veryempty.R;
import ma.ensaf.veryempty.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    ActivitySplashBinding binding;

    private View parent_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        parent_view = findViewById(android.R.id.content);

        // timer task
        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                binding.loadingProgress.setVisibility(View.GONE);
                // Start login activity
                ActivityWelcomeScreen.start(SplashActivity.this);
                // close splash activity
                finish();
            }
        }.start();
    }
}
