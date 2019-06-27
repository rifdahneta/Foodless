package pnj.ac.id.foodless.Activity;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import pnj.ac.id.foodless.CurrentUser;
import pnj.ac.id.foodless.Model.FormModel;
import pnj.ac.id.foodless.R;
import pnj.ac.id.foodless.Model.User;

public class OrderDetail extends AppCompatActivity {
    private TextView txtNamaDonatur, txtJenisDonatur, txtKadaluarsaDetail, txtPorsi, txtAlamat;
    private Button btnAccept, btnDecline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
    }

    private void init(){
        txtNamaDonatur = findViewById(R.id.txtNamaDonaturDetail);
        txtJenisDonatur = findViewById(R.id.txtJenisDonaturDetail);
        txtKadaluarsaDetail = findViewById(R.id.txtMasaKadaluarsaDetail);
        txtPorsi = findViewById(R.id.txtJumlahPorsiDetail);
        txtAlamat = findViewById(R.id.txtAlamatDetail);

        btnAccept = findViewById(R.id.btnAccept);
        btnDecline = findViewById(R.id.btnDecline);

    }

    private void getOrderDetail(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference retrieveUser = database.getReference("Users");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            retrieveUser.child(Objects.requireNonNull(FirebaseAuth.getInstance()).getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            FormModel formModel = dataSnapshot.getValue(FormModel.class);
                            CurrentUser.full_name = user.getFullName();
                            CurrentUser.email = user.getEmail();
                            CurrentUser.address = user.getAddress();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }
    }
}
