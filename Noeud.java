import java.util.ArrayList;
// Taquin
public class Noeud {
    private Grille grille;
    private Noeud pere;
    private int g;
    int taille = this.grille.getTaille();

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
    public int h1() {
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
        int h2=0;
        int[][] indices ={{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
        for (int i = 1; i < taille*taille; i++) {
            for (int j = 0; j < taille; j++) {
                for (int k = 0; k < taille; k++) {
                    if (this.grille.getValeur(j, k) == i) {
                        h2 += Math.abs(indices[i-1][0]-j)+Math.abs(indices[i-1][1]-k);}
                }
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
    public int f() {
        return this.f1();
    }
    public boolean estUnEtatFinal() {
        int[][] indices ={{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
        for (int i = 1; i < taille*taille; i++) {
            for (int j = 0; j < taille; j++) {
                for (int k = 0; k < taille; k++) {
                    if (this.grille.getValeur(j, k) == i) {
                        if (indices[i-1][0]!=j || indices[i-1][1]!=k)
                            return false;
                    }
                }
            }
        }
        return true;
    }
    public ArrayList<Grille> successeurs(){
        ArrayList<Grille> successeurs = new ArrayList<Grille>();

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
                        Grille grille2 = new Grille(this.grille.copier());
                        grille2.getGrille()[i][j] = grille2.getGrille()[i+1][j];
                        grille2.getGrille()[i+1][j] = 0;
                        successeurs.add(grille2);
                    }
                    if (j-1>=0) { // 0 peut aller à gauche 
                        Grille grille2 = new Grille(this.grille.copier());
                        grille2.getGrille()[i][j] = grille2.getGrille()[i][j-1];
                        grille2.getGrille()[i][j-1] = 0;
                        successeurs.add(grille2);
                    }
                    if (j+1<taille) { // 0 peut aller à droite
                        Grille grille2 = new Grille(this.grille.copier());
                        grille2.getGrille()[i][j] = grille2.getGrille()[i][j+1];
                        grille2.getGrille()[i][j+1] = 0;
                        successeurs.add(grille2);
                    }
                }
            }
        }
        return successeurs;

    }
}

