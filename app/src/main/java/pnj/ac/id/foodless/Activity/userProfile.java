package pnj.ac.id.foodless.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pnj.ac.id.foodless.Fragment.ProfileFragment;
import pnj.ac.id.foodless.R;

public class userProfile extends AppCompatActivity {

    private static final String TAG = "userProfile";

    //firebase
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseAuth mAuth;

    //widgets
    private Button btnSignOut;
    private TextView txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        init();

//        mAuth = FirebaseAuth.getInstance();
//
//        btnSignOut = findViewById(R.id.btnSignOutUser);
//        txtEmail = findViewById(R.id.txtEmailUser);
//
//        loadUserInformation();
//        setupFirebaseListener();
//
//        btnSignOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: attempting to sign out the user");
//                FirebaseAuth.getInstance().signOut();
//
//            }
//        });
    }

    private void init(){
        Log.d(TAG, "init: inflating " + getString(R.string.profile_fragment));

        ProfileFragment fragment = new ProfileFragment();
        FragmentTransaction transaction = userProfile.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.containerProfile, fragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();

    }


//    private void loadUserInformation() {
//        FirebaseUser user = mAuth.getCurrentUser();
//
//        if (user != null) {
//
//            if (user.getEmail() != null) {
//                txtEmail.setText(user.getEmail());
//            }
//        }
//    }
//
//    private void setupFirebaseListener(){
//        Log.d(TAG, "setupFirebaseListener: setting up the auth state listener. ");
//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if(user != null){
//                    Log.d(TAG, "onAuthStateChanged: signed_in: " + user.getUid() );
//                } else {
//                    Log.d(TAG, "onAuthStateChanged: signed_out");
//                    Toast.makeText(userProfile.this, "Signed Out", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(userProfile.this, Login.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                }
//            }
//        };
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(mAuthStateListener != null){
//            FirebaseAuth.getInstance().removeAuthStateListener(mAuthStateListener);
//        }
//    }
}