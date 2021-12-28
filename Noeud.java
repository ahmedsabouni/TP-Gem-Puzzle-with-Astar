import java.util.ArrayList;
// Taquin
public class Noeud {
    private Grille grille;
    private Noeud pere;
    private int g;
    

    public Noeud(Grille grille, Noeud p, int g) {
        this.grille = grille;
        this.pere = p;
        this.g = g;
    }
    public Grille getGrille() {
        return grille;
    }
    public Noeud getPere() {
        return pere;
    }
    public int getG() {
        return g;
    }
    public int h1() {
        int taille = grille.getTaille();
        int h1=0,n=0;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                n++;
                if (this.grille.getValeur(i, j) !=n)
                    h1++;
                }
                
            }
        return h1-1;
    }
    public int h2() {
        // la somme des distances de chaque case à sa case cible
        int taille = grille.getTaille();
        int h2=0;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                h2 += Math.abs(i - (this.grille.getValeur(i, j) - 1) / taille) + Math.abs(j - (this.grille.getValeur(i, j) - 1) % taille);
            }
        }
        return h2;

    }
    public int g() {
        return this.g;
    }
    public int f1() {
        return this.g()+this.h1();
    }
    public int f2() {
        return this.g()+this.h2();
    }
    
    public boolean estUnEtatFinal() {
        int k=1;
        for (int i = 0; i < this.grille.getTaille(); i++) {
            for (int j = 0; j < this.grille.getTaille(); j++) {
                if(k==Math.pow(this.grille.getTaille(),2)) k=0;
                if (this.grille.getValeur(i, j) != k) return false;
                k++;
            }
        }
        return true;
    }

    

    public ArrayList<Grille> successeurs(){
        ArrayList<Grille> successeurs = new ArrayList<>();
        int taille = this.grille.getTaille();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (this.grille.getValeur(i, j) == 0) {
                    if (i-1>=0) { // 0  peut  monter
                        Grille grille2 = new Grille(this.grille.copier());
                        grille2.getGrille()[i][j] = grille2.getGrille()[i-1][j];
                        grille2.getGrille()[i-1][j] = 0;
                        successeurs.add(grille2);
                    }
                    if (i+1<taille) { // 0  peut  descendre 
                        Grille grille3 = new Grille(this.grille.copier());
                        grille3.getGrille()[i][j] = grille3.getGrille()[i+1][j];
                        grille3.getGrille()[i+1][j] = 0;                        
                        successeurs.add(grille3);
                    }
                    if (j-1>=0) { // 0 peut aller à gauche 
                        Grille grille4 = new Grille(this.grille.copier());
                        grille4.getGrille()[i][j] = grille4.getGrille()[i][j-1];
                        grille4.getGrille()[i][j-1] = 0;
                        successeurs.add(grille4);
                    }
                    if (j+1<taille) { // 0 peut aller à droite
                        Grille grille5 = new Grille(this.grille.copier());
                        grille5.getGrille()[i][j] = grille5.getGrille()[i][j+1];
                        grille5.getGrille()[i][j+1] = 0;
                        successeurs.add(grille5);
                    }
                }
            }
        }
        return successeurs;

    }
}

