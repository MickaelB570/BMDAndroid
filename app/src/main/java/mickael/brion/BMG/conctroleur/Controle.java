package mickael.brion.BMG.conctroleur;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import mickael.brion.BMG.modele.AccesLocal;
import mickael.brion.BMG.modele.Ouvrage;

public final class Controle {
    // construction du singleton
    // une seule instance : l'attribut est donc static
    private static Controle instance  = null;
    private static AccesLocal accesLocal;
    private static Context contexte;
    private static ArrayList<Ouvrage> LesOuvrages;
    private static ArrayList<Ouvrage> LesOuvragesGenres;
    private static String unGenre;

    public static  ArrayList<Ouvrage> getLesOuvrages()
    {
        return LesOuvrages;
    }
    public static  ArrayList<Ouvrage> getOuvragesGenres(String leGenre)
    {
        unGenre = leGenre;


        return LesOuvragesGenres;
    }


    /**
     * Constructeur privé
     * Il doit être privé car on ne veut pas pouvoir créer plusieurs instances
     */
    private Controle(){
        //  on appelle le constructeur de la classe mère (object)
        super();
    }


    /**
     * Création de l'instance
     * final pour qu'on ne puisse pas redéfinir la méthode
     * public pour qu'elle puisse être appelée de l'extérieur
     * à l'appel, cette méthode génère un nouveau controleur s'il n'existe pas
     * @return l'instance
     */
    public static final Controle getInstance(Context contexte){
        if (contexte != null)
        {
            Controle.contexte = contexte;
        }

        accesLocal = new AccesLocal(contexte);
        LesOuvrages = accesLocal.getOuvrages();
        Log.d("test","*************" + unGenre);

        LesOuvragesGenres = accesLocal.getOuvragesGenres(unGenre);
        if (LesOuvrages != null)
        {
            Log.d("livreTitre57","*************" + LesOuvrages.get(57).getTitre());
            Log.d("livreAuteur57","*************" + LesOuvrages.get(57).getAuteur());
            Log.d("livreGenre7","*************" + LesOuvrages.get(57).getGenre());

        }


        if (Controle.instance == null) {
            Controle.instance = new Controle();
        }
        // dans tous les cas, il retourne l'instance
        return Controle.instance;
    }

}
