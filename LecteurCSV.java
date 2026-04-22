import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsable de la lecture du fichier CSV contenant
 * les étudiants, leurs cours et leurs notes.
 *
 * Format attendu du CSV :
 *   Nom,Cours,Note
 *   Alice Dupont,Maths,85
 *   Alice Dupont,Physique,90
 *   Bob Martin,Maths,70
 *   ...
 */
public class LecteurCSV {

    /**
     * Lit le fichier CSV et retourne une liste d'étudiants
     * avec leurs cours et notes déjà chargés.
     *
     * @param cheminFichier Le chemin vers le fichier CSV.
     * @return Liste des étudiants lus depuis le fichier.
     */
    public static List<Etudiant> lireFichier(String cheminFichier) {

        List<Etudiant> etudiants = new ArrayList<>();

        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {

            String ligne;
            boolean premiereLigne = true;

            while ((ligne = lecteur.readLine()) != null) {

                // Ignorer l'en-tête
                if (premiereLigne) {
                    premiereLigne = false;
                    continue;
                }

                // Ignorer les lignes vides
                if (ligne.trim().isEmpty()) {
                    continue;
                }

                // Découper la ligne en colonnes
                String[] colonnes = ligne.split(",");

                if (colonnes.length < 3) {
                    System.out.println("  [Attention] Ligne ignorée (format invalide) : " + ligne);
                    continue;
                }

                String nomEtudiant = colonnes[0].trim();
                String nomCours    = colonnes[1].trim();
                double note;

                // Validation de la note
                try {
                    note = Double.parseDouble(colonnes[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("  [Attention] Note invalide ignorée pour : " + nomEtudiant + " (" + colonnes[2].trim() + ")");
                    continue;
                }

                // Vérifier si l'étudiant existe déjà dans la liste
                Etudiant etudiantExistant = trouverEtudiant(etudiants, nomEtudiant);

                if (etudiantExistant == null) {
                    // Créer un nouvel étudiant
                    Etudiant nouvelEtudiant = new Etudiant(nomEtudiant);
                    nouvelEtudiant.ajouterCours(nomCours, note);
                    etudiants.add(nouvelEtudiant);
                } else {
                    // Ajouter le cours à l'étudiant existant
                    etudiantExistant.ajouterCours(nomCours, note);
                }
            }

        } catch (IOException e) {
            System.out.println("[ERREUR] Impossible de lire le fichier : " + cheminFichier);
            System.out.println("  Détail : " + e.getMessage());
        }

        return etudiants;
    }

    /**
     * Cherche un étudiant dans la liste par son nom.
     *
     * @param etudiants    La liste d'étudiants.
     * @param nom          Le nom à rechercher.
     * @return L'étudiant trouvé, ou null s'il n'existe pas.
     */
    private static Etudiant trouverEtudiant(List<Etudiant> etudiants, String nom) {
        for (Etudiant e : etudiants) {
            if (e.getNom().equalsIgnoreCase(nom)) {
                return e;
            }
        }
        return null;
    }
}

