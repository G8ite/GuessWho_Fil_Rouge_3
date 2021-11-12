package fr.amandine.qui_est_ce.Class;

public class ImageCase {
    //Données membres
    private String nameImg;
    private int etatImg;
    private int joueurImg;

    public ImageCase(String nameImg, int etatImg, int joueurImg) {
        this.setNameImg(nameImg);
        this.setEtatImg(etatImg);
        this.setJoueurImg(joueurImg);
    }

    public String getNameImg() {
        return nameImg;
    }

    public int getEtatImg() {
        return etatImg;
    }

    public int getJoueurImg() {
        return joueurImg;
    }

    public void setNameImg(String nameImg) {
        this.nameImg = nameImg;
    }

    public void setEtatImg(int etatImg) {
        this.etatImg = etatImg;
    }

    public void setJoueurImg(int joueurImg) {
        this.joueurImg = joueurImg;
    }

    @Override
    public String toString() {
        return "Name : "+nameImg+ ", State : "+ etatImg + ", Joueur : " + joueurImg;
    }
}
