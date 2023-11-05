package com.example.tpmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {
    private TextView tvusername;
    private Button btnajout,btnafff;
    public static ArrayList<Contact> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        tvusername=findViewById(R.id.tvuser_acc);
        btnafff=findViewById(R.id.btn_affiche_accueil);
        btnajout=findViewById(R.id.btn_ajout_accueil);


        Intent x = this.getIntent();
        Bundle b = x.getExtras();
        String u = b.getString("USER");
        tvusername.setText("Accueil de "+u);
        btnajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent aj = new Intent(Accueil.this, Ajout.class);
                startActivity(aj);

            }
        });
        btnafff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aff = new Intent(Accueil.this, Affichage.class);
                startActivity(aff);


            }
        });

    }
}