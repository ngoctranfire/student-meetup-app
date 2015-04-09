package stumeets.stumeets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;

import java.lang.ref.WeakReference;

import stumeets.stumeets.UserData.User;


public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText mEmailInput;
    private EditText mPasswordInput;
    public Firebase mRootFireBaseRef;

    /* Create the Firebase ref that is used for all authentication with Firebase */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mRootFireBaseRef =  new Firebase(getResources().getString(R.string.firebase_url));

        mEmailInput = (EditText) findViewById(R.id.login_email_input);
        mPasswordInput = (EditText) findViewById(R.id.login_password_input);
        Button signupButton = (Button) findViewById(R.id.login_signUpBtn);

        Button loginButton = (Button) findViewById(R.id.login_signInBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyLogin();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSignupIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(toSignupIntent);
            }
        });

    }

    /**
     * Gets data from the inputs and verifies that to login! If the user is already logged in, saved the data.
     */
    private void verifyLogin() {
        final String email = mEmailInput.getText().toString();
        final String password = mPasswordInput.getText().toString();
        mRootFireBaseRef.authWithPassword(email, password, new StoreAuthResultHandler(LoginActivity.this, mRootFireBaseRef, email));
    }



    public static class StoreAuthResultHandler implements Firebase.AuthResultHandler{

        private final WeakReference<Activity> mActivity;
        private final WeakReference<Firebase> mRootFireBaseRef;
        private final boolean isNewAccount;
        private final String userEmail;

        public StoreAuthResultHandler(Activity activity, Firebase rootFireBase, boolean isNewAccount, String userEmail) {
            mActivity = new WeakReference<Activity>(activity);
            mRootFireBaseRef = new WeakReference<Firebase>(rootFireBase);
            this.isNewAccount = isNewAccount;
            this.userEmail = userEmail;
        }
        public StoreAuthResultHandler(Activity activity, Firebase rootFireBase, String userEmail) {
            mActivity = new WeakReference<Activity>(activity);
            mRootFireBaseRef = new WeakReference<Firebase>(rootFireBase);
            isNewAccount = false;
            this.userEmail = userEmail;
        }

        private void storeUserData(String uid, String email) {
            Activity activity = mActivity.get();
            Firebase usersRef = mRootFireBaseRef.get().child(activity.getResources().getString(R.string.user_data_url));
            User newUser = new User(uid, email, ServerValue.TIMESTAMP);
            usersRef.child(uid).setValue(newUser);

        }



        @Override
        public void onAuthenticated(AuthData authData) {
            Log.i(TAG, "User creation successful!: With Email!");
            Activity activity = mActivity.get();
            if (isNewAccount) {
                storeUserData(authData.getUid(), this.userEmail);
            }
            Toast.makeText(activity.getApplicationContext(), activity.getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
            //Add the uid to the shared preferences for later use!
            SharedPreferences preferences = activity.getSharedPreferences(
                    activity.getResources().getString(R.string.stumeets_data), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString((activity.getResources().getString(R.string.stumeets_uid)), authData.getUid());
            editor.apply();

            startHomeActivity();

        }

        private void startHomeActivity() {
            Activity activity = mActivity.get();
            Intent toHomeActivity = new Intent(activity.getApplicationContext(), HomeActivity.class);
            activity.startActivity(toHomeActivity);
        }



        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            Log.i(TAG, "User creation error: " + firebaseError.getMessage());
            Activity activity = mActivity.get();
            Toast.makeText(activity.getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
