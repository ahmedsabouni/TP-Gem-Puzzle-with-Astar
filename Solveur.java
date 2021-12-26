import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

public class Solveur {
    private ArrayList<Noeud> liste_noeuds_fermes;
    private ArrayList<Noeud> liste_noeuds_ouverts;

    public Grille chargerFichGrille(String nomFichier) {
        int [][] grille = null;
        File file = new File("puzzles/"+nomFichier);
        try {
                 Scanner input = new Scanner(file);  // Create a Scanner object
                int taille = input.nextInt();  // Read next line
                System.out.println("la taille est "+ taille);  // Print the line
            input.close();  // Close the file 
            grille = new int[taille][taille];
            // Print the line
                Scanner sc = new Scanner(file);                
                sc.nextLine(); /* pour commencer de lire à partir de la deuxieme ligne */
                int i = 0;     
                while (sc.hasNextLine() && i < taille) {
                    String line = sc.nextLine();
                    
                    String[] lineArray = line.trim().split("\\s+"); /* on split la ligne en plusieurs elements pour nn'import nombre d'espace */
                    
                    
                        for (int j = 0; j < lineArray.length; j++) { 
                            
                            grille[i][j] = Integer.parseInt(lineArray[j]);
                        }
                    
                    i++;
                }
                
                sc.close();
                
            
        } catch (FileNotFoundException e) {
            System.out.println("il y'a un probleme avec le fichier ");
            e.printStackTrace();
        }
        Grille g = new Grille(grille);   
        return g;
    }

    

}
