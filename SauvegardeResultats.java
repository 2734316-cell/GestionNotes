import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Responsable de la sauvegarde des résultats (classement + moyennes)
 * dans un fichier texte de sortie.
 */
public class SauvegardeResultats {

    /**
     * Sauvegarde le classement des étudiants dans un fichier texte.
     *
     * @param etudiants     La liste triée des étudiants.
     * @param cheminFichier Le chemin du fichier de sortie.
     */
    public static void sauvegarderResultats(List<Etudiant> etudiants, String cheminFichier) {

        try (BufferedWriter ecrivain = new BufferedWriter(new FileWriter(cheminFichier))) {

            // En-tête du fichier avec la date de génération
            String dateGeneration = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            ecrivain.write("================================================");
            ecrivain.newLine();
            ecrivain.write("    RAPPORT DE NOTES - CLASSEMENT DES ÉTUDIANTS");
            ecrivain.newLine();
            ecrivain.write("    Généré le : " + dateGeneration);
            ecrivain.newLine();
            ecrivain.write("================================================");
            ecrivain.newLine();
            ecrivain.newLine();

            // Tableau du classement
            ecrivain.write(String.format("%-5s %-20s %-10s %-30s%n",
                    "Rang", "Nom", "Moyenne", "Détail des cours"));
            ecrivain.write("------------------------------------------------");
            ecrivain.newLine();

            int rang = 1;
            for (Etudiant etudiant : etudiants) {

                // Construire le détail des cours
                StringBuilder detailCours = new StringBuilder();
                List<String> cours = etudiant.getCours();
                List<Double> notes = etudiant.getNotes();

                for (int i = 0; i < cours.size(); i++) {
                    detailCours.append(cours.get(i))
                            .append(":")
                            .append(String.format("%.1f", notes.get(i)));
                    if (i < cours.size() - 1) {
                        detailCours.append("  ");
                    }
                }

                ecrivain.write(String.format("%-5d %-20s %-10.2f %s%n",
                        rang, etudiant.getNom(), etudiant.getMoyenne(), detailCours.toString()));
                rang++;
            }

            ecrivain.newLine();
            ecrivain.write("================================================");
            ecrivain.newLine();
            ecrivain.write("    STATISTIQUES GÉNÉRALES");
            ecrivain.newLine();
            ecrivain.write("================================================");
            ecrivain.newLine();

            // Statistiques globales
            double moyenneGenerale = calculerMoyenneGenerale(etudiants);
            Etudiant meilleur = etudiants.get(0);
            Etudiant dernierEtudiant = etudiants.get(etudiants.size() - 1);

            ecrivain.write(String.format("  Nombre d'étudiants     : %d%n", etudiants.size()));
            ecrivain.write(String.format("  Moyenne générale       : %.2f%n", moyenneGenerale));
            ecrivain.write(String.format("  Meilleur étudiant      : %s (%.2f)%n",
                    meilleur.getNom(), meilleur.getMoyenne()));
            ecrivain.write(String.format("  Étudiant le plus faible: %s (%.2f)%n",
                    dernierEtudiant.getNom(), dernierEtudiant.getMoyenne()));
            ecrivain.newLine();
            ecrivain.write("================================================");
            ecrivain.newLine();

            System.out.println("[OK] Résultats sauvegardés dans : " + cheminFichier);

        } catch (IOException e) {
            System.out.println("[ERREUR] Impossible d'écrire dans le fichier : " + cheminFichier);
            System.out.println("  Détail : " + e.getMessage());
        }
    }

    /**
     * Calcule la moyenne générale de tous les étudiants.
     *
     * @param etudiants La liste des étudiants.
     * @return La moyenne générale.
     */
    private static double calculerMoyenneGenerale(List<Etudiant> etudiants) {
        if (etudiants.isEmpty()) return 0.0;

        double somme = 0.0;
        for (Etudiant e : etudiants) {
            somme += e.getMoyenne();
        }
        return somme / etudiants.size();
    }
}

