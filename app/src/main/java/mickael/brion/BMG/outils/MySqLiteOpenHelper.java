package mickael.brion.BMG.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MySqLiteOpenHelper extends SQLiteOpenHelper {

    private final Context contexte;
    private static MySqLiteOpenHelper instance;
    private static final String DATABASE_NAME = "bmg.db";
    private String DATABASE_PATH; // chemin défini dans le constructeur
    private static final int DATABASE_VERSION = 1; // l'incrément appelle onUpgrade(), décrément => onDowngrade()

    /**
     * retourne l'instance unique de la classe
     * création si elle n'existe pas
     * @param contexte
     * @return
     */
    public static synchronized MySqLiteOpenHelper getInstance(Context contexte){
        if (instance == null){
            instance = new MySqLiteOpenHelper(contexte);
        }
        return  instance;
    }


    private MySqLiteOpenHelper( Context contexte) {
        super(contexte, DATABASE_NAME, null, DATABASE_VERSION);
        this.contexte = contexte;
        String filesDir = contexte.getFilesDir().getPath();
        DATABASE_PATH = filesDir.substring(0,filesDir.lastIndexOf("/"))+ "/databases/";
        // si la BDD n'existe pas dans le dossier de l'APP
        if (!checkdatabases()){
            //copie de la base de données vers DATABASE
            copydatabase();
        }
    }

    private void copydatabase() {
        final String outFileName = DATABASE_PATH + DATABASE_NAME;
        InputStream myInput;
        try {
            // Ouvre la bdd de 'assets' en lecture
            myInput = contexte.getAssets().open(DATABASE_NAME);
            // dossier de destination
            File pathFile = new File(DATABASE_PATH);
            if (!pathFile.exists()){
                if (!pathFile.mkdirs()){
                    Toast.makeText(contexte, "Erreur : copydatabase(), pathFile.mkdirs()",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            // Ouverture en écriture du fichier bdd de destination
            OutputStream myOutput = new FileOutputStream(outFileName);

            // transfert de inputfile vers outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Fermeture
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (IOException e){
            e.printStackTrace();
            Toast.makeText(contexte, "Erreur : copydatabase()",Toast.LENGTH_SHORT).show();
        }
        // on ajoute le numéro de version
        try{
            SQLiteDatabase checkDb = SQLiteDatabase.openDatabase(DATABASE_PATH + DATABASE_NAME, null, SQLiteDatabase.OPEN_READWRITE);
            checkDb.setVersion(DATABASE_VERSION);
        }
        catch (SQLiteException e){
            // la bd n'existe pas
        }

    }

    /**
     * Vérifie l'existene de l abase de donnée
     * @return
     */
    private boolean checkdatabases() {
        // retourne true/false si la bdd existe dans le dossier de l'app
        File dbFile = new File(DATABASE_PATH + DATABASE_NAME);
        return dbFile.exists();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /**
     * Si changement de version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion){
            Log.d("debug", "onUpgrade() : oldVersion=" + oldVersion + ",newVersion=" + newVersion);
            contexte.deleteDatabase(DATABASE_NAME);
            copydatabase();
        }
    }
}
