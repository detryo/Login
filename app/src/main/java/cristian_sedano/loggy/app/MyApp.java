package cristian_sedano.loggy.app;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by Christian on 24/10/2017.
 */

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(3000);
    }
}
