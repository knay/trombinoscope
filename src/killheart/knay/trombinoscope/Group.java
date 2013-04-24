package killheart.knay.trombinoscope;

import java.util.ArrayList;

import android.content.Context;
import android.widget.LinearLayout;

public class Group {
	private ArrayList<Pupil> eleves = new ArrayList<Pupil>();   //< Les élèves de la classe scolaire
	private String nom = null;
	
	/**
	 * @author David et Jonathan
	 * 
	 * Constructeur par défaut de la classe. 
	 * Met un nom par défaut au groupe.
	 */
	Group() {
		nom = "Sans_Nom";
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Constructeur de la classe. 
	 * Permet de définir le nom du groupe directement.
	 * Si n vaut null, nom sera définit avec un nom par défaut.
	 * 
	 * @param n Le nom que vous souhaitez donner au groupe.
	 */
	Group(String n) {
		if (n != null)
			nom = n;
		else
			nom = "Sans_Nom";
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer le nom du groupe.
	 * 
	 * @return Le nom du groupe.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer la liste des élèves du groupe.
	 * 
	 * @return La listge d'élève du groupe.
	 */
	public ArrayList<Pupil> getEleves() {
		return eleves;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de modifier le nom du groupe.
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
	 * Permet d'ajouter un élève au groupe.
	 * 
	 * @param e L'élève que vous voulez ajouter au groupe.
	 */
	public void ajouterEleve(Pupil e) {
		if (e != null)
			eleves.add(e);
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'ajouter un élève au groupe.
	 * 
	 * @param e L'élève que vous voulez ajouter au groupe.
	 */
	public void retirerEleve(Pupil e) {
		eleves.remove(e);    //< Se sert de la fonction equals() de pupil
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'afficher un groupe sur un layout Android. En fonction
	 * du mode choisi l'affichage sera différent.
	 * 
	 * @param layout Le layout sur lequel on souhaite afficher le groupe.
	 * @param c Le contexte Android pour l'affichage.
	 * @param mode Le mode d'affichage de l'élève : MODE_LISTE ou MODE_TROMBI accépté.
	 * 
	 * @return Pupil.FAILLURE Si une erreur s'est produite.
	 * @return Pupil.SUCCESS Si tout va bien.
	 */
	public int afficher (LinearLayout layout, Context c, int mode) {
		if ((mode != Pupil.MODE_LISTE && mode != Pupil.MODE_TROMBI) || layout == null)
			return Pupil.FAILLURE;
		
		for (int i = 0; i < eleves.size(); i++) {
			int cr = eleves.get(i).afficher(layout, c, mode);     //< On affiche un élève sur le layout
			if (cr == Pupil.FAILLURE)
				return cr;
		}
		
		return Pupil.SUCCESS;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de savoir si deux groupes sont les mêmes.
	 * 
	 * @param g Le groupe avec lequel vous souhaitez le comparer.
	 * 
	 * @return true Si les deux groupes sont les mêmes
	 * @return false Sinon
	 */
	public Boolean equals(Group g) {
		if (nom == g.getNom()) {
			if (eleves.equals(g.getEleves()) == true)
				return true;
		}
		return false;
	}
}
