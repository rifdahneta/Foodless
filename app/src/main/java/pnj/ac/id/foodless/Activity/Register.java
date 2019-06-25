package pnj.ac.id.foodless.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import pnj.ac.id.foodless.R;
import pnj.ac.id.foodless.Model.User;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText editRegFullname, editRegAlamat, editRegPhone, editRegEmail, editRegPassword;
    private Button btnRegis, btnLoginReg;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init(){
        editRegFullname = findViewById(R.id.edit_nama_regis);
        editRegAlamat = findViewById(R.id.edit_alamat_regis);
        editRegPhone = findViewById(R.id.edit_notelp_regis);
        editRegEmail = findViewById(R.id.edit_email_regis);
        editRegPassword = findViewById(R.id.edit_password_regis);

        btnRegis = findViewById(R.id.button_regis);
        btnLoginReg = findViewById(R.id.button_loginreg);


        progressBar = findViewById(R.id.progress_regis);

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegis.setOnClickListener(this);
        btnLoginReg.setOnClickListener(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        if(firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(Register.this, Login.class));
        }
    }

    private void registerUser(){
        final String fullName = editRegFullname.getText().toString().trim();
        final String alamat = editRegAlamat.getText().toString().trim();
        final String phoneNo = editRegPhone.getText().toString().trim();
        final String eMail = editRegEmail.getText().toString().trim();
        final String pass = editRegPassword.getText().toString().trim();

        if(fullName.isEmpty()){
            editRegFullname.setError(getString(R.string.error_input_nama));
            editRegFullname.requestFocus();
            return;
        }

        if (alamat.isEmpty()){
            editRegAlamat.setError(getString(R.string.error_input_address));
            editRegAlamat.requestFocus();
            return;
        }

        if(phoneNo.isEmpty()){
            editRegPhone.setError(getString(R.string.error_input_phone));
            editRegPhone.requestFocus();
            return;
        }

        if(eMail.isEmpty()){
            editRegEmail.setError(getString(R.string.error_input_email));
            editRegEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(eMail).matches())
        {
            editRegEmail.setError(getString(R.string.error_input_email));
            editRegEmail.requestFocus();
            return;
        }


        if(pass.isEmpty()){
            editRegPassword.setError(getString(R.string.error_input_password));
            editRegPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        /**
         * Register a new email and password to Firebase Authentication
         * @param email
         * @param password
         * @param username
         */
        firebaseAuth.createUserWithEmailAndPassword(eMail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullName, alamat, phoneNo, eMail, pass);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()){
                                        sendEmailVerif();
                                    } else {
                                        Toast.makeText(Register.this, "Register Failed", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }

    private void sendEmailVerif()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            //after the email is sent, log out user and finish this activity
                            firebaseAuth.signOut();
                            Toast.makeText(Register.this, getString(R.string.succeed_register), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Register.this, Login.class));
                            finish();
                        }
                        else
                        {
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
    public void onClick(View v){
        if (v.getId() == R.id.button_regis)
        {
            registerUser();
        }

        if (v.getId()== R.id.button_loginreg)
        {
            Intent i = new Intent (Register.this, Login.class);
            startActivity(i);
        }  }



}



