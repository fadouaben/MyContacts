package com.example.tpmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactManager {
    SQLiteDatabase db = null;
    Context context;

    public ContactManager(Context context) {
        this.context = context;
    }

    public void ouvrir(){
        ContactHelper helper = new ContactHelper(context,"contactbase.db",null,1);
        db = helper.getWritableDatabase();
    }

    public long ajout(String nom,String prenom,String numero){
        long aj = 0;

        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_nom,nom);
        values.put(ContactHelper.col_prenom,prenom);
        values.put(ContactHelper.col_num,numero);
        aj=11;

        return db.insert(ContactHelper.table_contact,null,values);
    }

    public ArrayList<Contact> getAllContact(){

        ArrayList<Contact> l = new ArrayList<Contact>();
        Cursor cr = db.query(ContactHelper.table_contact,new String[]{ContactHelper.col_nom,ContactHelper.col_prenom,ContactHelper.col_num,ContactHelper.col_id},null,null,null,null,null);
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            String i1 = cr.getString(0);
            String i2 = cr.getString(1);
            String i3 = cr.getString(2);
            int i4 = cr.getInt(3);
            l.add(new Contact(i1,i2, i3,i4));
            cr.moveToNext();
        }
        return l;

    }

    public long edit (int id,String nom,String prenom,String numero){
        long a;
        ContentValues values = new ContentValues();
        values.put(ContactHelper.col_nom,nom);
        values.put(ContactHelper.col_prenom,prenom);
        values.put(ContactHelper.col_num,numero);


        a = db.update(ContactHelper.table_contact,values,ContactHelper.col_id+" =?",new String[]{String.valueOf(id)});
        db.close();
        return a;
    }

    public void supprimer(int id){
        db.delete(ContactHelper.table_contact,ContactHelper.col_id+" =?",new String[]{String.valueOf(id)});

    }

    public ArrayList<Contact> recherche(String query){
        ArrayList<Contact> contacts = new ArrayList<>();
        String queryToSearch = "SELECT * FROM " + ContactHelper.table_contact + " WHERE (" + ContactHelper.col_nom + " LIKE '%" + query + "%') OR ("+ContactHelper.col_prenom + " LIKE '%" + query + "%') OR ("+ContactHelper.col_num + " LIKE '%" + query + "%')";
        Cursor cursor = db.rawQuery(queryToSearch,null);
        if (cursor.moveToNext()){
            do{
                Contact c = new Contact(cursor.getString(cursor.getColumnIndexOrThrow(ContactHelper.col_nom)), cursor.getString(cursor.getColumnIndexOrThrow(ContactHelper.col_prenom)), cursor.getString(cursor.getColumnIndexOrThrow(ContactHelper.col_num)), cursor.getInt(cursor.getColumnIndexOrThrow(ContactHelper.col_id)));
                contacts.add(c);
            }while (cursor.moveToNext());
        }

        return contacts;
    }

    public void fermer(){


    }
}
