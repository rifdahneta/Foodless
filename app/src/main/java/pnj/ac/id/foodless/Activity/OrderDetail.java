package pnj.ac.id.foodless.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import pnj.ac.id.foodless.R;

public class OrderDetail extends AppCompatActivity {
    private TextView txtNamaDonatur, txtJenisDonatur, txtKadaluarsaDetail, txtPorsi, txtAlamat;
    private Button btnAccept, btnDecline;
    private String getNamaDonatur, getJenisDonatur, getKadaluarsa, getPorsi, getAlamat;
    private FirebaseAuth auth;

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
        
    }
}
