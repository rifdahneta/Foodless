package pnj.ac.id.foodless.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.w3c.dom.Text;

import pnj.ac.id.foodless.Activity.Login;
import java.util.Objects;

import pnj.ac.id.foodless.Activity.Form;
import pnj.ac.id.foodless.Activity.Login;
import pnj.ac.id.foodless.CurrentUser;
import pnj.ac.id.foodless.DetailActivity;
import pnj.ac.id.foodless.R;
import pnj.ac.id.foodless.User;

import static android.support.constraint.Constraints.TAG;


public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private static final int ACTIVITY_NUM = 4;

    private View ProfileView;

    private Context mContext;

    private ProgressBar mProgressBar;

    //widgets
    private TextView tFullName, tEmailUser, tPasswordUser, tPhoneUser, tAddressUser;
    private Button btnSignOutProfile;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private TextView fullname, emailUser, addressUser, no_phoneUser, logout;
    private View ProfileView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_profile, container, false);

       tFullName = view.findViewById(R.id.txtFullName);
       tEmailUser = view.findViewById(R.id.txtEmailUser);
       tPasswordUser = view.findViewById(R.id.txtPasswordUser);
       tPhoneUser = view.findViewById(R.id.txtPhoneUser);
       tAddressUser = view.findViewById(R.id.txtAddressUser);

       mProgressBar = (ProgressBar) view.findViewById(R.id.profileProgressBar);

       mContext = getActivity();

       btnSignOutProfile = (Button) view.findViewById(R.id.btnSignOutUser);
       Log.d(TAG, "onCreateView: started.");

       return view;
    }

    /*
        Setup the firebase auth object
     */

    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getInstance().getReference().child("Users").child("1");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null){
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    //User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //retrieve user information from the database
                String full_name = dataSnapshot.child("fullName").getValue().toString();
                String email = dataSnapshot.child("email").getValue().toString();
                String address = dataSnapshot.child("address").getValue().toString();
                String password = dataSnapshot.child("password").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();

                tFullName.setText(full_name);
                tEmailUser.setText(email);
                tPasswordUser.setText(password);
                tAddressUser.setText(address);
                tPhoneUser.setText(phone);
                //retrieve images for the user in question

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                ProfileView = inflater.inflate(R.layout.fragment_profile, container, false);

        return ProfileView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fullname = ProfileView.findViewById(R.id.fullNameProfile);
        emailUser = ProfileView.findViewById(R.id.emailProfile);
        addressUser = ProfileView.findViewById(R.id.addressProfile);
        no_phoneUser = ProfileView.findViewById(R.id.phoneProfile);
        logout = ProfileView.findViewById(R.id.text_logout);

        getUserDetail();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                CurrentUser.full_name = null;
                CurrentUser.email = null;
                CurrentUser.address = null;
                CurrentUser.phone = null;
                Intent intent = new Intent (ProfileFragment.this.getActivity(), Login.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, Login.class));

        }    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

//    private void checkCurrentUser(FirebaseUser user){
//        Log.d(TAG, "checkCurrentUser: checking if user is logged in.");
//
//        if(user == null){
//            Intent intent = new Intent (mCon)
//        }
//    }
    private void getUserDetail() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference retrieveUser = database.getReference("Users");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            retrieveUser.child(Objects.requireNonNull(FirebaseAuth.getInstance()).getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            CurrentUser.full_name = user.getFullName();
                            CurrentUser.email = user.getEmail();
                            CurrentUser.address = user.getAddress();
                            CurrentUser.phone = user.getPhone();

                            fullname.setText(CurrentUser.full_name);
                            emailUser.setText(CurrentUser.email);
                            addressUser.setText(CurrentUser.address);
                            no_phoneUser.setText(CurrentUser.phone);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            Log.w(TAG, "onCancelled: ", databaseError.toException());
                        }
                    });
        }
    }
}