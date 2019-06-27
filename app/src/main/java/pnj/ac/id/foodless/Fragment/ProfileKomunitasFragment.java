package pnj.ac.id.foodless.Fragment;


import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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
import pnj.ac.id.foodless.Model.Communities;
import pnj.ac.id.foodless.R;

public class ProfileKomunitasFragment extends Fragment {
    private View profileKomunitasView;
    private static final String TAG = "ProfileKomunitas";

    private TextView namaKomunitas, emailKomunitas, telponKomuitas, alamatKomunitas,logoutProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        profileKomunitasView = inflater.inflate(R.layout.fragment_profile_komunitas, container, false);

        return profileKomunitasView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        namaKomunitas = profileKomunitasView.findViewById(R.id.namaKomunitasProfile);
        emailKomunitas = profileKomunitasView.findViewById(R.id.emailKomunitasProfile);
        telponKomuitas = profileKomunitasView.findViewById(R.id.telponKomunitasProfile);
        alamatKomunitas = profileKomunitasView.findViewById(R.id.alamatKomunitasProfile);
        logoutProfile = profileKomunitasView.findViewById(R.id.logoutProfile);

        getUserDetail();

        logoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                CurrentUser.full_name = null;
                CurrentUser.email = null;
                CurrentUser.address = null;
                CurrentUser.phone = null;
                Intent intent = new Intent (ProfileKomunitasFragment.this.getActivity(), Login.class);
                startActivity(intent);
            }
        });

    }

    private void getUserDetail() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference retrieveUser = database.getReference("komunitas");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            retrieveUser.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Communities komunitas  = dataSnapshot.getValue(Communities.class);
                            CurrentUser.full_name = komunitas.getNama_komunitas();
                            CurrentUser.phone = komunitas.getnotelp_komunitas();
                            CurrentUser.address = komunitas.getAlamat_komunitas();
                            CurrentUser.email = komunitas.getEmail_komunitas();

                            namaKomunitas.setText(CurrentUser.full_name);
                            emailKomunitas.setText(CurrentUser.email);
                            alamatKomunitas.setText(CurrentUser.address);
                            telponKomuitas.setText(CurrentUser.phone);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w(TAG, "onCancelled: ", databaseError.toException());
                        }
                    });
        }

    }
}
