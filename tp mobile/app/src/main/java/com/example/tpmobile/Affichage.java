package com.example.tpmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class Affichage extends AppCompatActivity {
    ListView lv ;
    SearchView sv;
    String number;
    ArrayList<Contact> datab = new ArrayList<>();
    MyContactAdapter ad;
    int REQUEST_CALL = 1;
    public ContactManager manager = new ContactManager(Affichage.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);

        lv = findViewById(R.id.lv_affiche);

        /*/sans base de données

        ArrayAdapter ad = new ArrayAdapter<>(Affichage.this, android.R.layout.simple_list_item_1,Accueil.data);
        MyContactAdapter myad = new MyContactAdapter(Affichage.this,Accueil.data);
        lv.setAdapter(myad);


        /*/

        //avec base de données

        manager = new ContactManager(Affichage.this);
        manager.ouvrir();
        datab = manager.getAllContact();
        ad = new MyContactAdapter(Affichage.this,datab);
        lv.setAdapter(ad);

        sv = findViewById(R.id.svRech_affichage);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchContact(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchContact(newText);
                return true;
            }
        });





    }

    private void searchContact(String query) {
        manager.ouvrir();
        ad = new MyContactAdapter(this,manager.recherche(query));
        lv.setAdapter(ad);
    }


    public void editContact(int idcontact, String nom, String prenom, String num){
        Intent intent = new Intent(Affichage.this,AddEditContact.class);
        intent.putExtra("idcontact",idcontact);
        intent.putExtra("nom",nom);
        intent.putExtra("prenom",prenom);
        intent.putExtra("num",num);

        startActivity(intent);

    }
    public void appeler(String dial){
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
    }

    public void autoriser(int requestCall,String num) {
        ActivityCompat.requestPermissions(Affichage.this ,new String[]{Manifest.permission.CALL_PHONE},requestCall);
        number=num;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ad.makePhoneCall(number);
            }
            else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }

        }
    }
}