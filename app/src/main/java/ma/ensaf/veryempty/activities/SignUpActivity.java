package ma.ensaf.veryempty.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import ma.ensaf.veryempty.R;
import ma.ensaf.veryempty.databinding.ActivitySignUpBinding;

public class SignUpActivity extends BaseActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private ActivitySignUpBinding binding;
    String[] cities = {"Select City","Casablanca", "Fes", "Meknes", "Rabat", "Tangier", "Oujda","Marrakech","Tetuan","Laayoune"};

    public static void start(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        //setting the toolbar
        initToolbar(binding.toolbar,true);
        setToolbarTitle(null);

        //Lisetners
        setListeners();
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
    }

    private void setListeners() {
        binding.registerLink.setOnClickListener(v -> SignInActivity.start(activityContext));
    }

}