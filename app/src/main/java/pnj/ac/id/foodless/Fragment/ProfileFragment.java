package pnj.ac.id.foodless.Fragment;

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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import pnj.ac.id.foodless.Activity.Login;
import pnj.ac.id.foodless.CurrentUser;

import pnj.ac.id.foodless.Model.User;


import pnj.ac.id.foodless.R;

import static android.support.constraint.Constraints.TAG;


public class    ProfileFragment extends Fragment {

    private TextView fullname, emailUser, addressUser, no_phoneUser, logout;
    private View ProfileView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

    private void getUserDetail() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference retriveUser = database.getReference("Users");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            retriveUser.child(Objects.requireNonNull(FirebaseAuth.getInstance()).getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
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
