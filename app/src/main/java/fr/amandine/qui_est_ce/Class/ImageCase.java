package fr.amandine.qui_est_ce.Class;

public class ImageCase {
    //DONNÉES MEMBRES
    private int idImg;
    private String nameImg;
    private int etatImg;
    private int joueurImg;

    //CONSTRUCTEUR
    public ImageCase(int idImg ,String nameImg, int etatImg, int joueurImg) {
        this.setIdImg(idImg);
        this.setNameImg(nameImg);
        this.setEtatImg(etatImg);
        this.setJoueurImg(joueurImg);
    }

    //GETTERS
    public int getIdImg(){ return  idImg;}

    public String getNameImg() {
        return nameImg;
    }

    public int getEtatImg() {
        return etatImg;
    }

    public int getJoueurImg() {
        return joueurImg;
    }

    //SETTERS
    public void setIdImg(int idImg){ this.idImg = idImg; }

    public void setNameImg(String nameImg) {
        this.nameImg = nameImg;
    }

    public void setEtatImg(int etatImg) {
        this.etatImg = etatImg;
    }

    public void setJoueurImg(int joueurImg) {
        this.joueurImg = joueurImg;
    }

    //TOSTRING
    @Override
    public String toString() {
        return idImg+ "," + nameImg+ "," + etatImg + "," + joueurImg;
    }
}
