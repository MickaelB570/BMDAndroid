package mickael.brion.BMG.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import mickael.brion.BMG.R;
import mickael.brion.BMG.modele.Ouvrage;

public class OuvragesListAdapter extends BaseAdapter {

    private ArrayList<Ouvrage> lesOuvrages;
    private LayoutInflater inflater;
    private Context contexte;

    public OuvragesListAdapter(ArrayList<Ouvrage> lesOuvrages, Context contexte) {
        this.lesOuvrages = lesOuvrages;
        this.contexte = contexte;
        this.inflater = LayoutInflater.from(contexte);
    }


    @Override
    public int getCount() {
        return lesOuvrages.size();
    }

    @Override
    public Object getItem(int position) {
        return lesOuvrages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        // Si la ligne n'est pas construite
        if (convertView == null)
        {
            //il fait faire le lien entre inflate et le contexte
            convertView = inflater.inflate(R.layout.layout_list_ouvrages,null);
            holder = new ViewHolder();
            // chaque propriété du holder est relié à une propriété
            holder.btnNumOuvrage = (Button)convertView.findViewById(R.id.btnNumOuvrage);
            holder.txtAuteur = (TextView)convertView.findViewById(R.id.txtAuteur);
            holder.txtTitreOuvrage = (TextView)convertView.findViewById(R.id.txtTitreOuvrage);
            // affecter le holder à la vue
            convertView.setTag(holder);
        } else
        {
            //Récupération du holder dans la vue existante
            holder = (ViewHolder)convertView.getTag();
        }
        // Valorisation du contenu du holder (donc de la ligne)
        holder.btnNumOuvrage.setText(lesOuvrages.get(position).getNoOuvrage().toString());
        holder.txtTitreOuvrage.setText(lesOuvrages.get(position).getTitre());
        holder.txtAuteur.setText(lesOuvrages.get(position).getAuteur());

        // Coder le lcic sur le numéro
        holder.btnNumOuvrage.setTag(position);
        //Coder l'appel de la vue d'affichage d'un ouvrage
        holder.btnNumOuvrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on récupère la position dans la liste
                int p = (int)v.getTag();
                // demande d'affichage de cet ouvrage dans AfficherUnOuvrageActivity
                // Pour ouvrir une Activity, il n'y a qu'une Activity qui peut le faire,
                // hors, je suis dans un Adapter
                // on transmet l'id de l'ouvrage récupéré grâce à sa position dans la liste
                // (et dans la collection lesOuvrages)
                // afficher l'ouvrage qui a pour indice p
                ((ListerOuvragesActivity)contexte).afficheUnOuvrage(p);
            }
        });


        return convertView;
    }

    /**
     *Classe représentatnt le contenu de la ligne
     */
    private class ViewHolder{
        Button btnNumOuvrage;
        TextView txtAuteur;
        TextView txtTitreOuvrage;
    }
}
