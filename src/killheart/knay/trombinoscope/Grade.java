package killheart.knay.trombinoscope;

import java.util.ArrayList;

import android.widget.LinearLayout;

/**
 * @author David et Jonathan
 *
 * Classe permet de gérer une classe scolaire.
 * Elle se base sur la classe Pupil pour gérer ses 
 * élèves.
 * 
 * @todo 
 */
public class Grade {
	private ArrayList<Pupil> eleves = new ArrayList<Pupil>();   //< Les élèves de la classe scolaire
	private String nom = null;                                  //< Le nom de la classe scolaire
	
	/**
	 * @author David et Jonathan
	 * 
	 * Le constructeur par défaut de la classe.
	 * Donne un nom par défaut à la classe.
	 * 
	 */
	public Grade () {
		nom = "Classe_sansnom";
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Le constructeur de la classe permettant de définir le nom
	 * directement.
	 * 
	 * @param n Le nom que vous souhaitez attribuer à la classe (si null un nom par défaut est donné).
	 */
	public Grade(String n) {
		if (n != null)
			nom = n;
		else
			nom = "CLasse_sansnom";
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer le nom de la classe scolaire.
	 * 
	 * @return Le nom de la classe.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de modifier le nom de la classe scolaire.
	 * 
	 * @param n Le nom que vous souhaitez attribuer à la classe (si null le nom n'est pas modifié).
	 */
	public void setNom(String n) {
		if (n != null)
			nom = n;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'ajouter un élève à la classe.
	 * 
	 * @param e L'élève que vous voulez ajouter à la classe scolaire.
	 */
	public void ajouterEleve(Pupil e) {
		if (e != null)
			eleves.add(e);
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'ajouter un élève à la classe.
	 * 
	 * @param e L'élève que vous voulez ajouter à la classe scolaire.
	 */
	public void retirerEleve(Pupil e) {
		eleves.remove(e);    //< Se sert de la fonction equals() de pupil
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'afficher une classe scolaire sur un layout Android. En fonction
	 * du mode choisi l'affichage sera différent.
	 * 
	 * @param layout Le layout sur lequel on souhaite afficher l'élève.
	 * @param mode Le mode d'affichage de l'élève : MODE_LISTE ou MODE_TROMBI accépté.
	 * 
	 * @return Pupil.FAILLURE Si une erreur s'est produite
	 * @return Pupil.SUCCESS sinon
	 */
	public int afficher(LinearLayout layout, int mode) {
		if ((mode != Pupil.MODE_LISTE && mode != Pupil.MODE_TROMBI) || layout == null)
			return Pupil.FAILLURE;
		
		for (int i = 0; i < eleves.size(); i++) {
			System.out.println(eleves.get(i));
			int cr = eleves.get(i).afficher(layout, mode);     //< On affiche un élève sur le layout
			if (cr == Pupil.FAILLURE)
				return cr;
		} 
		
		return Pupil.SUCCESS;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer une chaine de caractère caractéristique à la classe scolaire.
	 * 
	 * @return Une chaine de caractère caractéristique à l'élève.
	 */
	public String toString() {
		return "Classe : " + nom;
	}
	
}
