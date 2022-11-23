package mickael.brion.BMG.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import mickael.brion.BMG.outils.MySqLiteOpenHelper;

public class AccesLocal {
    private MySqLiteOpenHelper instance;
    private SQLiteDatabase bd;

    /**
     * constructeur
     * @param contexte
     */
    public AccesLocal(Context contexte) {
        instance = MySqLiteOpenHelper.getInstance(contexte);
    }

    /**
     * Récupère les ouvrages de la bibliothèque
     * @return
     */
    public ArrayList<Ouvrage> getOuvrages(){
        ArrayList<Ouvrage> lesOuvrages = new ArrayList<>();
        String titre,auteur,rayon,lib_genre,disponibilite;
        Integer no_ouvrage,salle;

        bd = instance.getReadableDatabase();
        String req = "select * from ouvrage";
        // Exécurtion de la requête
        Cursor curseur = bd.rawQuery(req,null);
        Log.d("nbEnregistrements","***************************" + curseur.getCount());
        if (curseur.moveToFirst())
        {
            do {
                no_ouvrage = curseur.getInt(0);
                titre = curseur.getString(1);
                salle = curseur.getInt(2);
                rayon = curseur.getString(3);
                lib_genre = curseur.getString(4);
                auteur = curseur.getString(5);
                disponibilite = curseur.getString(6);

                lesOuvrages.add(new Ouvrage(no_ouvrage,titre, auteur, salle, rayon, disponibilite, lib_genre));
            } while (curseur.moveToNext());
        }
        curseur.close();
        return  lesOuvrages;
    }

    public ArrayList<Ouvrage> getOuvragesGenres(String leGenre){
        ArrayList<Ouvrage> lesOuvrages = new ArrayList<>();
        String titre,auteur,rayon,lib_genre,disponibilite;
        Integer no_ouvrage,salle;
        Log.d("test2","*************" + leGenre);

        bd = instance.getReadableDatabase();
        String req = "select * from ouvrage where lib_genre ='" + leGenre + "'";
        // Exécurtion de la requête
        Cursor curseur = bd.rawQuery(req,null);
        Log.d("nbEnregistrementstest","***************************" + curseur.getCount());
        if (curseur.moveToFirst())
        {
            do {
                no_ouvrage = curseur.getInt(0);
                titre = curseur.getString(1);
                salle = curseur.getInt(2);
                rayon = curseur.getString(3);
                lib_genre = curseur.getString(4);
                auteur = curseur.getString(5);
                disponibilite = curseur.getString(6);

                lesOuvrages.add(new Ouvrage(no_ouvrage,titre, auteur, salle, rayon, disponibilite, lib_genre));
            } while (curseur.moveToNext());
        }
        curseur.close();
        return  lesOuvrages;
    }

    public ArrayList<Ouvrage> getOuvragesGenreNomAuteur(String uneDonnée,String unGenre){
        Log.d("leTestUtime","***************************" + uneDonnée);

        ArrayList<Ouvrage> lesOuvrages = new ArrayList<>();
        String titre,auteur,rayon,lib_genre,disponibilite;
        Integer no_ouvrage,salle;

        bd = instance.getReadableDatabase();
        String req = "select * from ouvrage where (titre like '%" + uneDonnée + "%' or auteur like '%" + uneDonnée + "%') and lib_genre ='" + unGenre +"'";
        // Exécurtion de la requête
        Cursor curseur = bd.rawQuery(req,null);
        Log.d("nbEnregistrementstest","***************************" + curseur.getCount());
        if (curseur.moveToFirst())
        {
            do {
                Log.d("lesPetitsTests","***************************" + curseur.getInt(0));

                no_ouvrage = curseur.getInt(0);
                titre = curseur.getString(1);
                salle = curseur.getInt(2);
                rayon = curseur.getString(3);
                lib_genre = curseur.getString(4);
                auteur = curseur.getString(5);
                disponibilite = curseur.getString(6);

                lesOuvrages.add(new Ouvrage(no_ouvrage,titre, auteur, salle, rayon, disponibilite, lib_genre));
            } while (curseur.moveToNext());
        }
        curseur.close();
        return  lesOuvrages;
    }

    public ArrayList<Ouvrage> getOuvragesAuteurNom(String unDonnée){
        ArrayList<Ouvrage> lesOuvrages = new ArrayList<>();
        String titre,auteur,rayon,lib_genre,disponibilite;
        Integer no_ouvrage,salle;

        bd = instance.getReadableDatabase();
        String req = "select * from ouvrage where titre ='%" + unDonnée + "%' or auteur ='" + unDonnée +"'";
        // Exécurtion de la requête
        Cursor curseur = bd.rawQuery(req,null);
        Log.d("nbEnregistrementstest","***************************" + curseur.getCount());
        if (curseur.moveToFirst())
        {
            do {
                no_ouvrage = curseur.getInt(0);
                titre = curseur.getString(1);
                salle = curseur.getInt(2);
                rayon = curseur.getString(3);
                lib_genre = curseur.getString(4);
                auteur = curseur.getString(5);
                disponibilite = curseur.getString(6);

                lesOuvrages.add(new Ouvrage(no_ouvrage,titre, auteur, salle, rayon, disponibilite, lib_genre));
            } while (curseur.moveToNext());
        }
        curseur.close();
        return  lesOuvrages;
    }



}
