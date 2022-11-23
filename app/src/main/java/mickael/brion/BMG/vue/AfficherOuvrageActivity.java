package mickael.brion.BMG.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import mickael.brion.BMG.R;
import mickael.brion.BMG.conctroleur.Controle;
import mickael.brion.BMG.modele.Ouvrage;

public class AfficherOuvrageActivity extends AppCompatActivity {

    private TextView txtID;
    private TextView txtTitre;
    private TextView txtAuteur;
    private TextView txtGenre;
    private TextView txtSalle;
    private TextView txtRayon;
    private ImageView imgCouverture;
    private  ImageView imgDispo;
    private Ouvrage ouvrage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_ouvrage);
        ecouteMenu((ImageButton)findViewById(R.id.btnRetourDeAffichageOuvrage),ListerOuvragesActivity.class);
        init();
    }
/*
* Initialisation
 */
    private void init() {
        Controle controle;
        controle = Controle.getInstance(this);
        int position = 0;

        Intent intent = getIntent();
        if (intent != null)
        {
            if (intent.hasExtra("position"))
            {
                position = intent.getIntExtra("position",0);
            }
            this.ouvrage = controle.getLesOuvrages().get(position);

            txtID = (TextView)findViewById(R.id.txtID);
            txtAuteur = (TextView)findViewById(R.id.txtAuteur);
            txtGenre = (TextView)findViewById(R.id.txtGenre);
            txtRayon = (TextView)findViewById(R.id.txtRayon);
            txtSalle = (TextView)findViewById(R.id.txtSalle);
            txtTitre = (TextView)findViewById(R.id.txtTitre);
            imgCouverture = (ImageView)findViewById(R.id.imgCouverture);
            imgDispo =(ImageView)findViewById(R.id.imgDispo);
            valorise();

        }
    }

    /**
     * Fait correspondre les valeurs des textView et de image view avec les valeurs de l'ouvrages trouvé
     */
    private void valorise() {
        txtTitre.setText(ouvrage.getTitre());
        txtAuteur.setText(ouvrage.getAuteur());
        txtGenre.setText(ouvrage.getGenre());
        txtRayon.setText(ouvrage.getRayon());
        txtSalle.setText(ouvrage.getSalle().toString());
        txtID.setText(ouvrage.getNoOuvrage().toString());
        setCouverture();
        setDisponibilite();


    }

    private void ecouteMenu(ImageButton btn, final Class classe) {
        btn.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                // lance une nouvelle activité
                Intent intent = new Intent(AfficherOuvrageActivity.this, classe);
                startActivity(intent); // méthode de la classe AppCompatActivity
            }
        });
    }

    private void setDisponibilite(){
        int image;
        if (ouvrage.getDispo().equals("D"))
        {
            image = getResources().getIdentifier("dispo","drawable",this.getPackageName());
        } else {
            image = getResources().getIdentifier("emprunte","drawable",this.getPackageName());
        }
        imgDispo.setImageResource(image);
    }

    private void setCouverture()
    {
        String imageName = 'a' + ouvrage.getNoOuvrage().toString();
        int res = getResources().getIdentifier(imageName,"drawable",this.getPackageName());
        if (res == 0)
        {
            res = getResources().getIdentifier("a0","drawable",this.getPackageName());
        }
        imgCouverture.setImageResource(res);
    }


}