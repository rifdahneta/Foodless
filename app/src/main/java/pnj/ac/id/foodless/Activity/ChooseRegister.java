package pnj.ac.id.foodless.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pnj.ac.id.foodless.R;

public class ChooseRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_register);
    }

    public void regis_komunitas(View view)
    {
        Intent intent = new Intent(ChooseRegister.this, RegistrasiKomunitas.class);
        startActivity(intent);
    }

    public void regis_donatur (View view)
    {
        Intent intent = new Intent(ChooseRegister.this, Register.class);
        startActivity(intent);
    }

}
