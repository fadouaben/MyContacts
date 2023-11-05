package com.example.tpmobile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ContactHelper extends SQLiteOpenHelper {

    public static final String table_contact="Contacts";
    public static final String col_id="ID";
    public static final String col_nom="Nom";
    public static final String col_prenom="Prenom";
    public static final String col_num="Numero";

    String requete = "CREATE TABLE " + table_contact + " (" + col_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + col_nom + " TEXT NOT NULL, " + col_prenom + " TEXT NOT NULL, " + col_num + " TEXT NOT NULL);";

    public ContactHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //lors de l'ouverture de la base la premiere fois
        db.execSQL(requete);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //mise à jour de la base
        db.execSQL("drop table " + table_contact);
        onCreate(db);


    }
}
