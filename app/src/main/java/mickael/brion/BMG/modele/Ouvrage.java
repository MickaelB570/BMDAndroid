package mickael.brion.BMG.modele;

public class Ouvrage {
    /**
     * Attributs privés de la classe Ouvrage
     * Certains attributs ont une valeur par défaut
     */
    private Integer noOuvrage;
    private String titre;
    private String auteur;
    private Integer salle = 0 ;
    private String rayon = "";
    private String dispo = "D" ;
    private String genre = "";

    /**
     *
     * @param noOuvrage
     * @param titre
     * @param auteur
     * @param salle
     * @param rayon
     * @param dispo
     * @param genre
     */
    public Ouvrage(Integer noOuvrage, String titre, String auteur, Integer salle, String rayon, String dispo, String genre) {
        this.noOuvrage = noOuvrage;
        this.titre = titre;
        this.auteur = auteur;
        this.salle = salle;
        this.rayon = rayon;
        this.dispo = dispo;
        this.genre = genre;
    }

    /**
     *
     * @param noOuvrage
     * @param titre
     * @param auteur
     */
    public Ouvrage(Integer noOuvrage, String titre, String auteur) {
        this.noOuvrage = noOuvrage;
        this.titre = titre;
        this.auteur = auteur;
    }

    public Integer getNoOuvrage() {
        return noOuvrage;
    }

    public void setNoOuvrage(Integer noOuvrage) {
        this.noOuvrage = noOuvrage;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getSalle() {
        return salle;
    }

    public void setSalle(Integer salle) {
        this.salle = salle;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Retourne une chaine de caractère décrivant un ouvrage
     * @return
     */
    @Override
    public String toString() {
        return "Ouvrage{" +
                "noOuvrage=" + noOuvrage +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", salle=" + salle +
                ", rayon='" + rayon + '\'' +
                ", dispo='" + dispo + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}

