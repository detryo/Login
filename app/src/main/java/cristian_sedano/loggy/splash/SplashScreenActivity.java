package cristian_sedano.loggy.splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import cristian_sedano.loggy.activitis.LoginActivity;
import cristian_sedano.loggy.activitis.MainActivity;
import cristian_sedano.loggy.util.Util;

/**
 * Created by Christian on 24/10/2017.
 */

public class SplashScreenActivity extends AppCompatActivity{

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentMain = new Intent(this, MainActivity.class);

        if (!TextUtils.isEmpty(Util.getUserMailPref(preferences)) &&  !TextUtils.isEmpty(Util.getUserPassPref(preferences))){

            startActivity(intentMain);

        }else {

            startActivity(intentLogin);
        }

        finish();
    }
}
