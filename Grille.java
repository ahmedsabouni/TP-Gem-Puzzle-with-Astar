public class Grille {
    private int[][] grille;
    
    public Grille(int[][] g) {
        this.grille = g;    
    }

    public int[][] getGrille() {
        return grille;
    }

    public int getTaille() {
        return this.grille.length;
    }

    public int getValeur(int i, int j) {
        return grille[i][j];
    }

    public int[][] copier() {
        int[][] copie = new int[this.grille.length][this.grille.length];
        for (int i = 0; i < this.grille.length; i++) {
            for (int j = 0; j < this.grille.length; j++) {
                copie[i][j] = this.grille[i][j];
            }
        }
        return copie;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < this.grille.length; i++) {
            for (int j = 0; j < this.grille.length; j++) {
                s += this.getValeur(i, j) + " | ";
            }
            s += "\n";
        }
        return s;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Grille) {
            Grille g = (Grille) obj;
            if (this.grille.length != g.getGrille().length) {
                return false;
            }
            for (int i = 0; i < this.grille.length; i++) {
                for (int j = 0; j < this.grille.length; j++) {
                    if (this.grille[i][j] != g.getGrille()[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
