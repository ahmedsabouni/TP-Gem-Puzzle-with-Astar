public class Grille {
    private int Ligne0;
    private  int taille ;
    private int colonne0;
    private int[][] grille;

    public Grille(int[][] g) {
        this.grille = g;
    }

    public int[][] getGrille() {
        return grille;
    }

    public int getColonne0() {
        return colonne0;
    }

    public int getLigne0() {
        return Ligne0;
    }

    public int getTaille() {
        return taille;
    }

    public int getValeur(int i, int j) {
        return grille[i][j];
    }

    public int[][] copier() {
        return this.grille;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < this.grille.length; i++) {
            for (int j = 0; j < this.grille.length; j++) {
                s += this.getValeur(i, j) + " ";
            }
            s += "\n";
        }
        return s;
    }

    public boolean equals(Object obj) {
        return this.equals(obj);
    }
}
