package pnj.ac.id.foodless.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pnj.ac.id.foodless.R;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText editLogEmail, editLogPassword;
    private Button btnLogin, btnLogRegis;
    private ProgressBar progressBar;
    private Spinner spinnerRole;

    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabase = FirebaseDatabase.getInstance().getReference();

//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roleUser, android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


//        spinnerRole.setAdapter(adapter);
//        spinnerRole.setOnItemSelectedListener(this);

        init();
    }

    private void init() {
        editLogEmail = findViewById(R.id.edit_email_login);
        editLogPassword = findViewById(R.id.edit_password_login);
        btnLogin = findViewById(R.id.button_login);
        btnLogRegis = findViewById(R.id.buttonlog_register);

        progressBar = findViewById(R.id.progress_login);

        spinnerRole = findViewById(R.id.spinnerRole);
        btnLogin.setOnClickListener(this);
        btnLogRegis.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, MainActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_login) {
            String email = editLogEmail.getText().toString().trim();
            final String password = editLogPassword.getText().toString().trim();

            if (email.isEmpty()) {
                editLogEmail.setError(getString(R.string.error_input_email));
                editLogEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                editLogPassword.setError(getString(R.string.error_input_password));
                editLogPassword.requestFocus();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.GONE);
                    if (!task.isSuccessful()) {
                        Toast.makeText(Login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                    } else {
                        checkIfEmailVerified();
                    }
                }
            });
        }

        if (v.getId() == R.id.buttonlog_register) {
            Intent i = new Intent(Login.this, ChooseRegister.class);
            startActivity(i);
        }
    }

    private void checkIfEmailVerified() {

        if (spinnerRole.getSelectedItem().toString().equalsIgnoreCase("Donatur")) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            assert user != null;
            if (user.isEmailVerified()) {
                //email verified, proceed to finish this activity
                finish();
                Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_LONG).show();

                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);

                finish();
            } else if (!user.isEmailVerified()) {
                Toast.makeText(Login.this, "Email is not verified", Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
            } else {
                Toast.makeText(Login.this, "An error occured. Please try again", Toast.LENGTH_SHORT).show();
            }
        }else{
            if (spinnerRole.getSelectedItem().toString().equalsIgnoreCase("Komunitas")) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                if (user.isEmailVerified()) {
                    //email verified, proceed to finish this activity
                    finish();
                    Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(Login.this, MainActivityKomunitas.class);
                    startActivity(i);

                    finish();
                } else if (!user.isEmailVerified()) {
                    Toast.makeText(Login.this, "Email is not verified", Toast.LENGTH_LONG).show();
                    FirebaseAuth.getInstance().signOut();
                } else {
                    Toast.makeText(Login.this, "An error occured. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
