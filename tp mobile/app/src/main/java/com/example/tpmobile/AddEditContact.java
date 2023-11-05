package com.example.tpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddEditContact extends AppCompatActivity {
    private ImageButton imgPhoto,imgValid;
    private EditText edNom,edPrenom,edNumero;
    private String nom,prenom,num;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_edit_contact);

        Intent  intent = getIntent();
        id=-1;
        if(intent.hasExtra("idcontact")){
            id = intent.getIntExtra("idcontact",-1);
            nom = intent.getStringExtra("nom");
            prenom = intent.getStringExtra("prenom");
            num = intent.getStringExtra("num");


        }


        imgValid = findViewById(R.id.imgbtnValid_Edit);
        edNom = findViewById(R.id.edNom_Edit);
        edPrenom = findViewById(R.id.edPrenom_Edit);
        edNumero = findViewById(R.id.edNum_Edit);

        edNumero.setText(num);
        edNom.setText(nom);
        edPrenom.setText(prenom);

        imgValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = edNom.getText().toString();
                String prenom = edPrenom.getText().toString();
                String num = edNumero.getText().toString();
                ContactManager manager = new ContactManager(AddEditContact.this);
                manager.ouvrir();

                long a = manager.edit(id,nom,prenom,num);
                Toast.makeText(AddEditContact.this, "Contact édité", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddEditContact.this,Affichage.class);
                startActivity(i);

            }
        });


    }
}