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

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class SignUpActivity extends Activity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private EditText mEmailInput;
    private EditText mPasswordInput;
    private EditText mConfirmPasswordInput;
    private Firebase mRootFireBaseRef;

    private Button mSignupButton;
    private Button mLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mRootFireBaseRef =  new Firebase(getResources().getString(R.string.firebase_url));
        mEmailInput = (EditText) findViewById(R.id.signup_email_input);
        mPasswordInput = (EditText) findViewById(R.id.signup_password_input);
        mConfirmPasswordInput = (EditText) findViewById(R.id.signup_confirm_input);


        mSignupButton = (Button) findViewById(R.id.signup_signupBtn);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupAccount();
            }
        });

        mLoginButton = (Button) findViewById(R.id.signup_loginBtn);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startLoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(startLoginActivity);
            }
        });


    }

    private void signupAccount() {
        final String userEmail = mEmailInput.getText().toString();
        String password = mPasswordInput.getText().toString();
        String confirmPass = mConfirmPasswordInput.getText().toString();

        if (!password.equals(confirmPass)) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.non_match_pass), Toast.LENGTH_SHORT).show();
        } else {
            mRootFireBaseRef.createUser(userEmail, password, new Firebase.ResultHandler() {
                @Override
                public void onSuccess() {
                    Log.i(TAG, "Successfully created user with " + userEmail);
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.account_created), Toast.LENGTH_LONG).show();

                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    Log.i(TAG, "User creation error: " + firebaseError.getMessage());
                    Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
