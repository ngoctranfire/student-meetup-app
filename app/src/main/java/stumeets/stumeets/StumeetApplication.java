package stumeets.stumeets;

import android.app.Activity;
import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by ngoctranfire on 4/7/15.
 */
public class StumeetApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}