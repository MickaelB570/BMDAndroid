package mickael.brion.BMG.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mickael.brion.BMG.R;
import mickael.brion.BMG.conctroleur.Controle;
import mickael.brion.BMG.modele.AccesLocal;
import mickael.brion.BMG.modele.Ouvrage;

public class RechercherOuvrageActivity extends AppCompatActivity {
    Controle controle;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercher_ouvrage);
        this.controle = Controle.getInstance(this);
        creerListe();
    }

    private void creerListe() {
        ArrayList<Ouvrage> lesOuvrages = controle.getLesOuvrages();
        ArrayList<String> lesGenres = new ArrayList<>();
        lesGenres.add("Biographie");
        lesGenres.add("Conte");
        lesGenres.add("Essai");
        lesGenres.add("Nouvelle");
        lesGenres.add("Correspondance");
        lesGenres.add("Poésie");
        lesGenres.add("Policier");
        lesGenres.add("Théatre");
        lesGenres.add("Roman");
        lesGenres.add("Récit");

        if (lesOuvrages != null){
            // instanciation d'un adapter
            OuvragesListAdapter adapter = new OuvragesListAdapter(lesOuvrages, this);
            ListView lstRechercherOuvrage = (ListView)findViewById(R.id.lstRechercherOuvrage);
            lstRechercherOuvrage.setAdapter(adapter);
        }
        Spinner lstDeroulante =(Spinner)findViewById(R.id.lst_deroulante);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lesGenres);
        lstDeroulante.setAdapter(adapter);
        context = this;
        ecouteMenu((Button)findViewById(R.id.btnRecherche));
    }

    private void ecouteMenu(Button btn) {
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Spinner lstDeroulante =(Spinner)findViewById(R.id.lst_deroulante);
                SearchView maRecherche =(SearchView)findViewById(R.id.rechercherOuvrage);
                String laRecherche = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
                if (lstDeroulante != null)
                {
                    laRecherche = maRecherche.getQuery().toString();
                    String leGenre = lstDeroulante.getSelectedItem().toString();
                    ArrayList<Ouvrage> lesOuvrages;
                    AccesLocal unAccesLocal = new AccesLocal(context);
                    Log.d("nbEnregistrements11","***************************" + leGenre);
                    if (!laRecherche.isEmpty())
                    {
                        Log.d("sansGenre","***********************************" + leGenre);
                        lesOuvrages = unAccesLocal.getOuvragesGenreNomAuteur(laRecherche,leGenre);
                        Log.d("bonjour1", "******************************" + laRecherche);

                        if (lesOuvrages != null) {
                            // instanciation d'un adapter

                            OuvragesListAdapter adapter = new OuvragesListAdapter(lesOuvrages, context);
                            ListView lstRechercherOuvrage = (ListView) findViewById(R.id.lstRechercherOuvrage);

                            lstRechercherOuvrage.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }


                    } else
                    {
                        Log.d("genre","***********************************" + leGenre);
                        lesOuvrages = unAccesLocal.getOuvragesGenres(leGenre);
                        Log.d("bonjour", "******************************" + laRecherche);

                        if (lesOuvrages != null) {
                            // instanciation d'un adapter

                            OuvragesListAdapter adapter = new OuvragesListAdapter(lesOuvrages, context);
                            ListView lstRechercherOuvrage = (ListView) findViewById(R.id.lstRechercherOuvrage);

                            lstRechercherOuvrage.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }
                    }

                }
            }
        });
    }
}