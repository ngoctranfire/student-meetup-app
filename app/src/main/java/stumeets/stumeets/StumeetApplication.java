package stumeets.stumeets;

import android.app.Activity;
import android.app.Application;

import com.firebase.client.Firebase;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by ngoctranfire on 4/7/15.
 */

/**
 * This extends the regular android application and does the basic image configurations for
 * the Universal Image Loader
 * This also initiates the Firebase connection to the android application and gets it ready for use.
 */
public class StumeetApplication extends Application {

    public static Firebase mRootFireBaseRef;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
        ImageLoader.getInstance().init(config);

    }


}