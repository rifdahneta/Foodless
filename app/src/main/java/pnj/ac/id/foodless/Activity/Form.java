package pnj.ac.id.foodless.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pnj.ac.id.foodless.Model.FormModel;
import pnj.ac.id.foodless.R;

public class Form extends AppCompatActivity implements View.OnClickListener {


    private EditText formNama, formTelepon, formEmail, formJenisMakanan, formKadaluarsa, formPorsi, formKeterangan;
    private Button btnForm;
    private RadioGroup radioJenis;
    private RadioButton radioEvent, radioIndividu;
   private String getJenis, getJenisMakanan, getJumlahPorsi, getKadaluarsa, getKeterangan, getNama, getTelepon, getEmail;

    private FirebaseDatabase database;
    private DatabaseReference getReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        //inisialisasi ID edit text
        formNama = findViewById(R.id.formNama);
        formTelepon = findViewById(R.id.formTelepon);
        formEmail = findViewById(R.id.formEmail);
        formJenisMakanan = findViewById(R.id.formJenisMakanan);
        formKadaluarsa = findViewById(R.id.formKadaluarsa);
        formPorsi = findViewById(R.id.formPorsi);
        formKeterangan = findViewById(R.id.formKeterangan);

        //inisialisasi ID button
        btnForm = findViewById(R.id.btnForm);
        btnForm.setOnClickListener(this);

        //inisialisasi RadioGroup
        radioJenis = findViewById(R.id.radioJenis);
        radioEvent = findViewById(R.id.radioEvent);
        radioIndividu = findViewById(R.id.radioIndividu);

        //inisialisasi Firebase
        database = FirebaseDatabase.getInstance();

    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.btnForm){

            //statement untuk menyimpan data
            int selectedId = radioJenis.getCheckedRadioButtonId();
            if (selectedId == radioEvent.getId()){
                getJenis = radioEvent.getText().toString();
            }else if (selectedId == radioIndividu.getId()){
                getJenis = radioIndividu.getText().toString();
            }
            getNama = formNama.getText().toString();
            getTelepon = formTelepon.getText().toString();
            getEmail = formEmail.getText().toString();
            getJenisMakanan = formJenisMakanan.getText().toString();
            getKadaluarsa = formKadaluarsa.getText().toString();
            getJumlahPorsi = formPorsi.getText().toString();
            getKeterangan = formKeterangan.getText().toString();
            getReference = database.getReference();

            //menyimpan data
            getReference.child("donasi")
                    .push()
                    .setValue(new FormModel(getJenis, getJenisMakanan, getJumlahPorsi, getKadaluarsa, getKeterangan, getNama, getTelepon, getEmail  ))
                    .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Peristiwa ini terjadi saat user berhasil menyimpan datanya kedalam Database
                            formNama.setText("");
                            formTelepon.setText("");
                            formEmail.setText("");
                            formJenisMakanan.setText("");
                            formKadaluarsa.setText("");
                            formPorsi.setText("");
                            formKeterangan.setText("");
                            Toast.makeText(Form.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                        }
                    });

        }

    }
}
