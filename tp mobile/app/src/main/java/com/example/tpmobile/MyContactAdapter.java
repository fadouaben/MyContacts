package com.example.tpmobile;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MyContactAdapter extends BaseAdapter {
    Context con;
    public ArrayList<Contact> d;
    private static final int REQUEST_CALL = 1;


    public MyContactAdapter(Context con, ArrayList<Contact> d) {
        this.con = con;
        this.d = d;
    }

    @Override
    public int getCount() {
        return d.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inf = LayoutInflater.from(con);

        View l = inf.inflate(R.layout.view_contact,null);
        //recuperation de holders
        TextView tvnom = l.findViewById(R.id.tvnom_contact);
        TextView tvprenom = l.findViewById(R.id.tvprenom_contact);
        TextView tvnum = l.findViewById(R.id.tvnum_contact);
        ImageView imgcall=l.findViewById(R.id.btnimgCall_contact);
        ImageView imgdelete=l.findViewById(R.id.btnimgDelete_contact);
        ImageView imgedit=l.findViewById(R.id.btnimgEdit_contact);
        tvnom.setText(d.get(position).nom);
        tvprenom.setText(d.get(position).prenom);
        tvnum.setText(d.get(position).numero);

        imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Affichage)con).editContact(d.get(position).getIdcontact(),tvnom.getText().toString(),tvprenom.getText().toString(),tvnum.getText().toString());
            }
        });

        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(con);
                alert.setTitle("Attention!! ");
                alert.setMessage("Etes vous sur de supprimer \n" + tvnom.getText().toString() + tvprenom.getText().toString() +"\n de numéro : " + tvnum.getText().toString());

                alert.setPositiveButton("Suppeimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactManager manager = new ContactManager(con);
                        manager.ouvrir();
                        manager.supprimer(d.get(position).getIdcontact());
                        Toast.makeText(con, "contact supprimé!!", Toast.LENGTH_SHORT).show();
                        d.remove(position);
                        ((MyContactAdapter) MyContactAdapter.this).updateContacts(d);

                    }
                });
                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(con, "Suppression annulée", Toast.LENGTH_SHORT).show();

                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();


            }
        });


        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall(tvnum.getText().toString());
            }
        });




        return l;
    }
    public void updateContacts(ArrayList<Contact> newListe){
        d = newListe;
        notifyDataSetChanged();
    }
    public void makePhoneCall(String num){
        if(num.trim().length() > 0){
            if(ContextCompat.checkSelfPermission(con, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                //ActivityCompat.requestPermissions((Activity)con ,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);

                ((Affichage)con).autoriser(REQUEST_CALL,num);
            }
            else {
                String dial ="tel:" + num;
                ((Affichage)con).appeler(dial);
            }


        }
        else {
            Toast.makeText(con,"Ajouter un numero",Toast.LENGTH_SHORT).show();
        }
    }

}
