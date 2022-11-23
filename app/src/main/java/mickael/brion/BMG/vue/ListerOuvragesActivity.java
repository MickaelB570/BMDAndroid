package mickael.brion.BMG.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import mickael.brion.BMG.R;
import mickael.brion.BMG.conctroleur.Controle;
import mickael.brion.BMG.modele.Ouvrage;

public class ListerOuvragesActivity extends AppCompatActivity {
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister_ouvrages);
        ecouteMenu((ImageButton)findViewById(R.id.btnRetourDeListe),MainActivity.class);
        this.controle = Controle.getInstance(this);
        creerListe();

    }

    private void ecouteMenu(ImageButton btn, final Class classe) {
        btn.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                // lance une nouvelle activité
                Intent intent = new Intent(ListerOuvragesActivity.this, classe);
                startActivity(intent); // méthode de la classe AppCompatActivity
            }
        });
    }

    /**
     * Créer le ListAdapter pour affichage
     */
    private void creerListe() {
        ArrayList<Ouvrage> lesOuvrages = controle.getLesOuvrages();
        if (lesOuvrages != null){
            // instanciation d'un adapter
            OuvragesListAdapter adapter = new OuvragesListAdapter(lesOuvrages, this);
            ListView lstOuvrages = (ListView)findViewById(R.id.lstOuvrages);
            lstOuvrages.setAdapter(adapter);
        }
    }
    public void afficheUnOuvrage(int position) {
        Intent intent = new Intent(ListerOuvragesActivity.this,AfficherOuvrageActivity.class);
        intent.putExtra("postion",position);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}