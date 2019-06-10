package pnj.ac.id.foodless;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import pnj.ac.id.foodless.Activity.Form;

public class DetailActivity extends AppCompatActivity {

    private String receiveDetail;

    private ImageView detailImg;
    private TextView detJudul, detAlamat, detEmail, detPhone, detMember, detDesc;
    private Button donateButton;

    private DatabaseReference komunitasRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        komunitasRef = FirebaseDatabase.getInstance().getReference().child("komunitas");
        receiveDetail = getIntent().getExtras().get("communities_detail").toString();

        detailImg = (ImageView) findViewById(R.id.dGambar);
        detJudul = (TextView) findViewById(R.id.dJudul);
        detAlamat = (TextView) findViewById(R.id.dAlamat);
        detEmail = (TextView) findViewById(R.id.dEmail);
        detPhone = (TextView) findViewById(R.id.dPhone);
        detMember = (TextView) findViewById(R.id.dMember);
        detDesc = (TextView) findViewById(R.id.dDeskripsi);
        donateButton = (Button) findViewById(R.id.button);

        RetrieveUserInfo();


    }

    private void RetrieveUserInfo()
    {
        komunitasRef.child(receiveDetail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("alamat_komunitas")))
                {
                    String dImage = dataSnapshot.child("gambar_komunitas").getValue().toString();
                    String djudul = dataSnapshot.child("nama_komunitas").getValue().toString();
                    String dAlamat = dataSnapshot.child("alamat_komunitas").getValue().toString();
                    String dEmail = dataSnapshot.child("email_komunitas").getValue().toString();
                    String dPhone = dataSnapshot.child("notelp_komunitas").getValue().toString();
                    String dMember = dataSnapshot.child("jumlah_member").getValue().toString();
                    String dDesc = dataSnapshot.child("jenis_kegiatan").getValue().toString();

                    Picasso.get().load(dImage).placeholder(R.drawable.buttonprofile).into(detailImg);
                    detJudul.setText(djudul);
                    detAlamat.setText(dAlamat);
                    detEmail.setText(dEmail);
                    detPhone.setText(dPhone);
                    detMember.setText(dMember);
                    detDesc.setText(dDesc);
                }
                else
                {
                    String djudul = dataSnapshot.child("nama_komunitas").getValue().toString();
                    String dAlamat = dataSnapshot.child("alamat_komunitas").getValue().toString();
                    String dEmail = dataSnapshot.child("email_komunitas").getValue().toString();
                    String dPhone = dataSnapshot.child("notelp_komunitas").getValue().toString();
                    String dMember = dataSnapshot.child("jumlah_member").getValue().toString();
                    String dDesc = dataSnapshot.child("jenis_kegiatan").getValue().toString();

                    detJudul.setText(djudul);
                    detAlamat.setText(dAlamat);
                    detEmail.setText(dEmail);
                    detPhone.setText(dPhone);
                    detMember.setText(dMember);
                    detDesc.setText(dDesc);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void donate(View view)
    {
        Intent intent = new Intent(DetailActivity.this, Form.class);
        startActivity(intent);
    }

}
