package cristian_sedano.loggy.activitis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import cristian_sedano.loggy.R;
import cristian_sedano.loggy.util.Util;

/**
 * Created by Christian on 23/10/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    private EditText editTextEmail;
    private EditText editTextPass;
    private Switch switchRemember;
    private Button buttonLogin;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buildUI();

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setCreadentialsIfExist();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String pass = editTextPass.getText().toString();

                if (login(email, pass)){
                    goToMain();
                    saveOnPreference(email, pass);

                }
            }
        });

    }

    private void buildUI(){
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPass = (EditText) findViewById(R.id.editTextPass);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    private void setCreadentialsIfExist(){
        String email = Util.getUserMailPref(preferences);
        String pass = Util.getUserPassPref(preferences);

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            editTextEmail.setText(email);
            editTextPass.setText(pass);
        }
    }

    private boolean login(String email, String pass){

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Email is not valid, please, try again", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!isValidPass(pass)){
            Toast.makeText(this, "Password is not valid, 4 characters or more, please try again", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    private void saveOnPreference(String email, String pass){
        if (switchRemember.isChecked()){
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("email", email);
            editor.putString("pass", pass);
            editor.apply();
        }
    }

    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPass(String pass){
        return pass.length() > 4;
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
