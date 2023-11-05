package com.example.tpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class  MainActivity extends AppCompatActivity {
    Button btnlog ,btncancel;
    EditText edemail, edpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.this.setContentView(R.layout.activity_main);
        btnlog = this.findViewById(R.id.btn_login_auth);
        btncancel = this.findViewById(R.id.btn_cancel_auth);
        edemail = this.findViewById(R.id.edemail_auth);
        edpwd = this.findViewById(R.id.edpassword_auth);
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edemail.getText().toString();
                String mdp = edpwd.getText().toString();
                if (email.equalsIgnoreCase("test@gmail.com") && mdp.equals("0000")){
                    Intent i = new Intent(MainActivity.this,Accueil.class);
                    i.putExtra("USER",email);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"valeur non valide",Toast.LENGTH_SHORT).show();

                }
            }
        });




    }
}