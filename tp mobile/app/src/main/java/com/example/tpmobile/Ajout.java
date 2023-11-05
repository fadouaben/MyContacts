package com.example.tpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ajout extends AppCompatActivity {
    private EditText ednom,edprenom , ednum;
    private Button btnaj,btnquit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        ednom=findViewById(R.id.ednom_ajout);
        edprenom=findViewById(R.id.edprenom_ajout);
        ednum=findViewById(R.id.ednum_ajout);


        btnaj = findViewById(R.id.btn_valide_ajout);
        btnquit = findViewById(R.id.btn_quitter_ajout);



        btnaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = ednom.getText().toString();
                String prenom = edprenom.getText().toString();
                String num = ednum.getText().toString();
                if (!num.equals("") && (!nom.equals("") || !prenom.equals(""))) {
                    Contact c = new Contact(nom, prenom, num);
                    //ajout dans un table (sans base de données)
                    //Accueil.data.add(c);

                    //base de données sqlite
                    ContactManager manager = new ContactManager(Ajout.this);
                    manager.ouvrir();

                    long a = manager.ajout(nom, prenom, num);
                    System.out.print(a);
                    ednum.setText("");
                    ednom.setText("");
                    edprenom.setText("");
                    Toast.makeText(Ajout.this, "contact ajouté!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Ajout.this, "Remplir les champs!!", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ajout.this.finish();
            }
        });


    }
}