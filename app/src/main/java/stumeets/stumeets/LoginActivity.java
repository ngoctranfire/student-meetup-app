package stumeets.stumeets;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText mEmailInput;
    private EditText mPasswordInput;
    private Firebase mRootFireBaseRef;

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
        mRootFireBaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Log.i(TAG, "User creation successful!: With Email: " + email + ", Password: " + password);
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_success), Toast.LENGTH_LONG).show();
                startHomeActivity();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.i(TAG, "User creation error: " + firebaseError.getMessage());
                Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startHomeActivity() {
        Intent toHomeActivity = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(toHomeActivity);
    }



}
