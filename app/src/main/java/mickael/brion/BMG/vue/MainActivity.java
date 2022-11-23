package mickael.brion.BMG.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import mickael.brion.BMG.R;
import mickael.brion.BMG.conctroleur.Controle;
import mickael.brion.BMG.modele.Ouvrage;

public class MainActivity extends AppCompatActivity {

    /**
     * attribut privé de la classe : objet Controle
     */
    private Controle controle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.controle = Controle.getInstance(this);
        ecouteMenu((ImageButton) findViewById(R.id.btnListerOuvrages),ListerOuvragesActivity.class);
        ecouteMenu((ImageButton)findViewById(R.id.btnRechercher),RechercherOuvrageActivity.class);

        //Ouvrage ouvrage = new Ouvrage(1,"Hernani","Emile Zola");
        //Toast.makeText(this,ouvrage.toString(),Toast.LENGTH_LONG).show();
        //Log.d("ouvrage","***********************************" + ouvrage.toString());
    }

    private void ecouteMenu(ImageButton btn, final Class classe) {
        btn.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                // lance une nouvelle activité
                Intent intent = new Intent(MainActivity.this, classe);
                startActivity(intent); // méthode de la classe AppCompatActivity
            }
        });
    }
}