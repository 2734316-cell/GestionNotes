import java.util.ArrayList;
import java.util.List;

/**
 * Représente un étudiant avec son nom, ses cours et ses notes.
 */
public class Etudiant {

    // Attributs privés
    private String nom;
    private List<String> cours;
    private List<Double> notes;
    private double moyenne;

    /**
     * Constructeur de la classe Etudiant.
     * @param nom Le nom complet de l'étudiant.
     */
    public Etudiant(String nom) {
        this.nom = nom;
        this.cours = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.moyenne = 0.0;
    }

    /**
     * Ajoute un cours et sa note associée à l'étudiant.
     * @param cours Le nom du cours.
     * @param note  La note obtenue dans ce cours.
     */
    public void ajouterCours(String cours, double note) {
        this.cours.add(cours);
        this.notes.add(note);
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getNom() {
        return nom;
    }

    public List<String> getCours() {
        return cours;
    }

    public List<Double> getNotes() {
        return notes;
    }

    public double getMoyenne() {
        return moyenne;
    }

    // ── Setter ───────────────────────────────────────────────────────────────

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    /**
     * Retourne une représentation lisible de l'étudiant.
     */
    @Override
    public String toString() {
        return String.format("%-20s | Moyenne : %.2f", nom, moyenne);
    }
}
