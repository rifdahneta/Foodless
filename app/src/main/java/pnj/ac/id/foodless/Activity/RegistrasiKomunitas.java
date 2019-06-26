package pnj.ac.id.foodless.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import pnj.ac.id.foodless.Model.Communities;
import pnj.ac.id.foodless.R;

public class RegistrasiKomunitas extends AppCompatActivity implements View.OnClickListener {

    private EditText editRegNamaKomunitas, editRegAlamatKomunitas, editRegPhoneKomunitas,
            editRegEmailKomunitas, editRegMemberKomunitas, editRegPassKomunitas;
    private Button btnRegisKomunitas;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuthKomunitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_komunitas);
        init();
    }

    private void init() {
        editRegNamaKomunitas = findViewById(R.id.edit_namakomunitas_regis);
        editRegAlamatKomunitas = findViewById(R.id.edit_alamatkomunitas_regis);
        editRegPhoneKomunitas = findViewById(R.id.edit_notelpkomunitas_regis);
        editRegMemberKomunitas = findViewById(R.id.edit_memberkomunitas_regis);
        editRegEmailKomunitas = findViewById(R.id.edit_emailkomunitas_regis);
        editRegPassKomunitas = findViewById(R.id.edit_passwordkomunitas_regis);

        btnRegisKomunitas = findViewById(R.id.button_regiskomunitas);
        progressBar = findViewById(R.id.progress_regiskom);

        firebaseAuthKomunitas = FirebaseAuth.getInstance();

        btnRegisKomunitas.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(firebaseAuthKomunitas.getCurrentUser() != null){
            startActivity(new Intent(RegistrasiKomunitas.this, Login.class));
        }

    }

    private void registerKomunitas(){
        final String namaKomunitas = editRegNamaKomunitas.getText().toString().trim();
        final String alamatKomunitas = editRegAlamatKomunitas.getText().toString().trim();
        final String notelpKomunitas = editRegPhoneKomunitas.getText().toString().trim();
        final String emailKomunitas = editRegEmailKomunitas.getText().toString().trim();
        final String passKomunitas = editRegPassKomunitas.getText().toString().trim();
        final String jmlMemberKomunitas = editRegMemberKomunitas.getText().toString().trim();

        if(namaKomunitas.isEmpty()){
            editRegNamaKomunitas.setError(getString(R.string.error_input_namakom));
            editRegNamaKomunitas.requestFocus();
            return;
        }

        if (alamatKomunitas.isEmpty()){
            editRegAlamatKomunitas.setError(getString(R.string.error_input_address));
            editRegAlamatKomunitas.requestFocus();
            return;
        }

        if(notelpKomunitas.isEmpty()){
            editRegPhoneKomunitas.setError(getString(R.string.error_input_phone));
            editRegPhoneKomunitas.requestFocus();
            return;
        }

        if(emailKomunitas.isEmpty()){
            editRegEmailKomunitas.setError(getString(R.string.error_input_email));
            editRegEmailKomunitas.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailKomunitas).matches())
        {
            editRegEmailKomunitas.setError(getString(R.string.error_input_email));
            editRegEmailKomunitas.requestFocus();
            return;
        }


        if(passKomunitas.isEmpty()) {
            editRegPassKomunitas.setError(getString(R.string.error_input_password));
            editRegPassKomunitas.requestFocus();
            return;
        }

        if(jmlMemberKomunitas.isEmpty()) {
            editRegMemberKomunitas.setError(getString(R.string.error_input_jmlmember));
            editRegMemberKomunitas.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuthKomunitas.createUserWithEmailAndPassword(emailKomunitas, passKomunitas)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Communities user = new Communities(namaKomunitas, alamatKomunitas, notelpKomunitas,
                                    emailKomunitas, passKomunitas, jmlMemberKomunitas);

                            FirebaseDatabase.getInstance().getReference("Communities")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful()){
                                        sendEmailVerifKomunitas();
                                    } else {
                                        Toast.makeText(RegistrasiKomunitas.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegistrasiKomunitas.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    
    private void sendEmailVerifKomunitas() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            //email is sent. log out the user and finish this activity
                            firebaseAuthKomunitas.signOut();
                            Toast.makeText(RegistrasiKomunitas.this, getString(R.string.succeed_register), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrasiKomunitas.this, Login.class));
                            finish();
                        } else {
                            //email not sent and restart this activity
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0,0);
                            startActivity(getIntent());
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_regiskomunitas){
            registerKomunitas();
        }
    }
}