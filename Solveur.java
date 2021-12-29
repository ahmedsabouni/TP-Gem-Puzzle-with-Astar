import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

public class Solveur {
    private ArrayList<Noeud> liste_noeuds_fermes=new ArrayList<Noeud>();
    private ArrayList<Noeud> liste_noeuds_ouverts=new ArrayList<Noeud>();

    public Grille chargerFichGrille(String nomFichier) {
        int [][] grille = null;
        File file = new File("puzzles/"+nomFichier);
        try {
            Scanner input = new Scanner(file);  
            int taille = input.nextInt();  // Read next line
            input.close();  // Close the file 
            grille = new int[taille][taille];
            Scanner sc = new Scanner(file);                
            sc.nextLine(); /* pour commencer de lire à partir de la deuxieme ligne */
            int i = 0;     
            while (sc.hasNextLine() && i < taille) {
                String line = sc.nextLine();                    
                String[] lineArray = line.trim().split("\\s+"); /* on split la ligne en plusieurs elements pour n'importe nombre d'espace (trim) pour ignorer les espaces au debut d'une ligne */
                    for (int j = 0; j < lineArray.length; j++) {
                        grille[i][j] = Integer.parseInt(lineArray[j]);
                    }                  
                i++;
            }           
            sc.close();            
        } catch (FileNotFoundException e) {
            System.out.println("il y'a un probléme avec le fichier ");
            e.printStackTrace();
        }
        return new Grille(grille);
    }

    public Noeud algoAstar(Grille initial){
        Noeud noeud_initial = new Noeud(initial,null,0);
        liste_noeuds_ouverts.add(noeud_initial);
        Noeud noeud_courant = noeud_initial;
        while (!liste_noeuds_ouverts.isEmpty() && !noeud_courant.estUnEtatFinal() ){ 
            Noeud min=liste_noeuds_ouverts.get(0);
            for (Noeud noeud : liste_noeuds_ouverts) {
                if (noeud.f1() < min.f1()) {
                    min = noeud;
                }           
            }
            noeud_courant = min;
            liste_noeuds_fermes.add(noeud_courant);
            liste_noeuds_ouverts.remove(noeud_courant);
            for (Grille grille_noeud_fils: noeud_courant.successeurs()) {
                Noeud noeud_fils = new Noeud(grille_noeud_fils,noeud_courant,noeud_courant.getG()+1);
                if (!estDansListeFermes(noeud_fils) && !estDansListeouverts(noeud_fils)) {
                    liste_noeuds_ouverts.add(noeud_fils);
                }
                else if(!estDansListeFermes(noeud_fils) && aUneGrilleIdentique(noeud_fils)!=null ){
                    if (aUneGrilleIdentique(noeud_fils).f1() > noeud_fils.f1()) {
                        liste_noeuds_ouverts.remove(aUneGrilleIdentique(noeud_fils));
                        liste_noeuds_ouverts.add(noeud_fils);
                    }            
                }
            }
        }
            if (noeud_courant.estUnEtatFinal()) {
                return noeud_courant;
            }
            return null; 
    }
    public Noeud aUneGrilleIdentique(Noeud noeud_fils) {
        // chercher dans la liste des noeuds ouverts si il y'a un noeud qui a la meme grille que le noeud courant
        for (Noeud noeud : liste_noeuds_ouverts) {
            if (noeud.getGrille().equals(noeud_fils.getGrille())) {
                return noeud;
            }
        }
        return null;
    }
    public boolean estDansListeFermes(Noeud noeud) {
        for (Noeud noeud_ferme : liste_noeuds_fermes) {
            if (noeud_ferme.getGrille().equals(noeud.getGrille())) {
                return true;
            }
        }
        return false;
    }
    public boolean estDansListeouverts(Noeud noeud) {
        for (Noeud noeud_ferme : liste_noeuds_ouverts) {
            if (noeud_ferme.getGrille().equals(noeud.getGrille())) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Grille> cheminComplet(Noeud noeudFinal) {
        ArrayList<Grille> chemin = new ArrayList<>();
        Noeud noeud = noeudFinal;
        chemin.add(noeud.getGrille());
        System.out.println("Cout pour attendre la solution :" + noeud.getG());
        while (noeud.getPere() != null){
            noeud = noeud.getPere();
            chemin.add(noeud.getGrille());
        }
        return chemin;
    }
    public static void main(String[] args) {
        Solveur solveur = new Solveur();
        Grille grille = solveur.chargerFichGrille("puzzle09.txt");
        System.out.println("la grille du jeu est \n"+grille);
        Noeud noeud_final = solveur.algoAstar(grille);
        // if solveur.algoAstar(grille) returne  null, le puzzle unsolvable
        if (noeud_final.equals(null)) {
            System.out.println("le jeu n'est pas resolvable");
        }
        else {
            System.out.println("le Grille finale est :\n"+noeud_final.getGrille());
            ArrayList<Grille> chemin = solveur.cheminComplet(noeud_final);
            System.out.println("le chemin complet est :\n");
            for (int i = chemin.size()-1; i >= 0; i--) {
                System.out.println(chemin.get(i));
            }
        }
        System.out.println("fin du programme");
    }
    }

