package stumeets.stumeets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import stumeets.stumeets.UserData.User;
import stumeets.stumeets.Utility.BitmapConverters;


public class HomeActivity extends ActionBarActivity {

    private static final String TAG = ActionBarActivity.class.getSimpleName();
    private SharedPreferences mSharedPreferences;
    private final int PICK_IMAGE_REQUEST = 1;

    private RoundedImageView mProfilePicture;
    private TextView mProfileName;
    private Firebase mRootFireBaseRef;

    private TextView mMatchesCount;
    private TextView mFriendsCount;
    private TextView mGroupsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRootFireBaseRef = new Firebase(getResources().getString(R.string.firebase_url));
        mProfileName = (TextView) findViewById(R.id.profile_name);

        mSharedPreferences = getSharedPreferences(
                getResources().getString(R.string.stumeets_data), Context.MODE_PRIVATE);

        mProfilePicture = (RoundedImageView)findViewById(R.id.profile_picture);

        mProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to Open Image applications like Gallery, Google Photos
                startPickImage();
            }
        });
        String myUserId = mSharedPreferences.getString(getResources().getString(R.string.stumeets_uid), "");
        mRootFireBaseRef.child(getResources().getString(R.string.user_data_url))
                .child(myUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String bitmap64String = (String) dataSnapshot.child("picture").getValue();
                if(!bitmap64String.isEmpty()) {
                    int circlePicture = getResources().getDimensionPixelSize(R.dimen.circle_picture_size);
                    Bitmap bitmap = BitmapConverters.decodeBase64(bitmap64String);
                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, circlePicture, circlePicture, false);

                    mProfilePicture.setImageBitmap(resizedBitmap);
                }
                String email = (String)dataSnapshot.child("email").getValue();
                mProfileName.setText(email);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(TAG, "Something terrible Happened!");
            }
        });

        mMatchesCount = (TextView)findViewById(R.id.matches_count);
        mMatchesCount.setText("3 Matches");

        mFriendsCount = (TextView)findViewById(R.id.friends_count);
        mFriendsCount.setText("4 Friends");

        mGroupsCount = (TextView)findViewById(R.id.groups_count);
        mGroupsCount.setText("2 Groups");

    }

    private void startPickImage() {
        Intent pickImageIntent = new Intent();

        // Show only images, no videos or anything else
        pickImageIntent.setType("image/*");
        pickImageIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Always show the chooser (if there are multiple options available)
        // Verify that there are applications registered to handle this intent
        // (resolveActivity returns null if none are registered)
        if (pickImageIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(pickImageIntent, "Select Picture"), PICK_IMAGE_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                int pictureSize = getResources().getDimensionPixelSize(R.dimen.circle_picture_size);

                Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, pictureSize, pictureSize, false);
                mProfilePicture = (RoundedImageView) findViewById(R.id.profile_picture);
                mProfilePicture.setImageBitmap(resizedBitmap);
                addPictureToDatabase(bitmap);



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param bitmap - the bitmap
     */
    private void addPictureToDatabase(Bitmap bitmap) {
        Resources myResource = getResources();
        String myUserId = mSharedPreferences.getString(myResource.getString(R.string.stumeets_uid), "");

        Firebase userProfile = mRootFireBaseRef.child(myResource.getString(R.string.user_data_url))
                .child(myUserId);

        int pictureSize = getResources().getDimensionPixelSize(R.dimen.match_picture_size);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, pictureSize, pictureSize, false);
        String imageBytes = BitmapConverters.encodeBase64(resizedBitmap);
        userProfile.child("picture").setValue(imageBytes);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

