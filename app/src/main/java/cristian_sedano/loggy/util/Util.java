package cristian_sedano.loggy.util;

import android.content.SharedPreferences;

/**
 * Created by Christian on 24/10/2017.
 */

public class Util {
    public static String getUserMailPref(SharedPreferences preferences) {
        return preferences.getString("email","");
    }

    public static String getUserPassPref(SharedPreferences preferences) {
        return preferences.getString("pass", "");
    }

    public static void removeSharedPreferences(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.remove("pass");
        editor.apply();
    }
}
