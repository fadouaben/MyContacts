package com.example.tpmobile;

public class Contact {

   public String nom,prenom,numero;
   int idcontact;



    public Contact( String nom, String prenom, String numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;

    }

    public Contact(String nom, String prenom, String numero, int idcontact) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.idcontact = idcontact;
    }

    public int getIdcontact() {
        return idcontact;
    }

    @Override
    public String toString() {
        return  nom + " " + prenom + " " + numero  ;
    }
}

