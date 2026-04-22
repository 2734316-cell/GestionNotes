import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Contient les méthodes de calcul et de tri des notes des étudiants.
 */
public class CalculNotes {

    /**
     * Calcule la moyenne de chaque étudiant à partir de ses notes
     * et met à jour la propriété moyenne de chaque objet Etudiant.
     *
     * @param etudiants La liste des étudiants.
     */
    public static void calculerMoyennes(List<Etudiant> etudiants) {

        for (Etudiant etudiant : etudiants) {

            List<Double> notes = etudiant.getNotes();

            if (notes.isEmpty()) {
                etudiant.setMoyenne(0.0);
                continue;
            }

            double somme = 0.0;
            for (double note : notes) {
                somme += note;
            }

            double moyenne = somme / notes.size();
            etudiant.setMoyenne(moyenne);
        }
    }

    /**
     * Classe les étudiants par ordre décroissant de moyenne
     * (le meilleur étudiant en premier).
     *
     * @param etudiants La liste des étudiants à trier.
     */
    public static void classerParMoyenne(List<Etudiant> etudiants) {
        Collections.sort(etudiants, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                // Ordre décroissant : on compare e2 vs e1
                return Double.compare(e2.getMoyenne(), e1.getMoyenne());
            }
        });
    }

    /**
     * Affiche le classement des étudiants dans la console.
     *
     * @param etudiants La liste triée des étudiants.
     */
    public static void afficherClassement(List<Etudiant> etudiants) {

        System.out.println("\n============================================");
        System.out.println("       CLASSEMENT DES ÉTUDIANTS            ");
        System.out.println("============================================");
        System.out.printf("%-5s %-20s %-10s %-30s%n", "Rang", "Nom", "Moyenne", "Notes par cours");
        System.out.println("--------------------------------------------");

        int rang = 1;
        for (Etudiant etudiant : etudiants) {

            // Construire la liste des cours et notes
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

            System.out.printf("%-5d %-20s %-10.2f %s%n",
                    rang, etudiant.getNom(), etudiant.getMoyenne(), detailCours.toString());
            rang++;
        }

        System.out.println("============================================\n");
    }
}
