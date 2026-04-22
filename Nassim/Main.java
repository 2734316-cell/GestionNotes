import java.util.List;

/**
 * Classe principale — point d'entrée du programme.
 *
 * Ce programme :
 *   1. Lit les données depuis un fichier CSV (étudiants, cours, notes)
 *   2. Calcule la moyenne de chaque étudiant
 *   3. Classe les étudiants par moyenne (décroissant)
 *   4. Affiche le classement dans la console
 *   5. Sauvegarde les résultats dans un fichier texte
 */
public class Main {

    // ── Chemins des fichiers (à modifier si besoin) ───────────────────────────
    private static final String FICHIER_CSV_ENTREE  = "etudiants.csv";
    private static final String FICHIER_TXT_SORTIE  = "resultats.txt";

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   Gestion de Notes des Étudiants      ");
        System.out.println("========================================\n");

        // ── Étape 1 : Lecture du fichier CSV ────────────────────────────────
        System.out.println(">> Lecture du fichier CSV : " + FICHIER_CSV_ENTREE);
        List<Etudiant> etudiants = LecteurCSV.lireFichier(FICHIER_CSV_ENTREE);

        if (etudiants.isEmpty()) {
            System.out.println("[ERREUR] Aucun étudiant trouvé. Vérifiez le fichier CSV.");
            return;
        }

        System.out.println("   " + etudiants.size() + " étudiant(s) chargé(s) avec succès.\n");

        // ── Étape 2 : Calcul des moyennes ────────────────────────────────────
        System.out.println(">> Calcul des moyennes...");
        CalculNotes.calculerMoyennes(etudiants);
        System.out.println("   Moyennes calculées.\n");

        // ── Étape 3 : Classement par moyenne ────────────────────────────────
        System.out.println(">> Classement des étudiants par moyenne...");
        CalculNotes.classerParMoyenne(etudiants);
        System.out.println("   Classement effectué.\n");

        // ── Étape 4 : Affichage du classement ───────────────────────────────
        CalculNotes.afficherClassement(etudiants);

        // ── Étape 5 : Sauvegarde des résultats ──────────────────────────────
        System.out.println(">> Sauvegarde des résultats dans : " + FICHIER_TXT_SORTIE);
        SauvegardeResultats.sauvegarderResultats(etudiants, FICHIER_TXT_SORTIE);

        System.out.println("\n========================================");
        System.out.println("   Programme terminé avec succès !      ");
        System.out.println("========================================");
    }
}