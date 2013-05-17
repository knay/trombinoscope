package killheart.knay.trombinoscope;

import java.util.ArrayList;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * @author David et Jonathan
 *
 * Classe permet de gérer une classe scolaire.
 * Elle se base sur la classe Pupil pour gérer ses 
 * élèves.
 */
public class Grade {
	private ArrayList<Group> groupes = new ArrayList<Group>();   //< Les élèves de la classe scolaire
	private String nom = null;                                   //< Le nom de la classe scolaire
	
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
			nom = "Classe_sansnom";
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
	 * Permet de récupérer la liste des groupes de la scolaire.
	 * 
	 * @return La liste des groupes de la classe.
	 */
	public ArrayList<Group> getGroupes() {
		return groupes;
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
	 * Permet de définir l'url des photos pour tous les élèves de la classe.
	 * 
	 * @param url L'url vers l'image (sans le nom de l'image)
	 */
	public void setUrlImage(String url) {
		for (int i = 0; i < groupes.size(); i++) {
			groupes.get(i).setUrlImage(url);     //< On définie l'url de l'image de chaque élèves de chaque groupes
		}
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'ajouter un groupe à la classe.
	 * 
	 * @param g Le groupe que vous voulez ajouter à la classe scolaire.
	 */
	public void ajouterGroup(Group g) {
		if (g != null)
			groupes.add(g);
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'ajouter un élève à la classe.
	 * 
	 * @param e L'élève que vous voulez ajouter à la classe scolaire.
	 */
	public void retirerEleve(Group g) {
		groupes.remove(g);    //< Se sert de la fonction equals() de group
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de trouver le premier id utilisable pour ajouter un élève.
	 * Parcours tous les groupes pour trouver le plus grand id.
	 */
	public int trouverDernierId() {
		int idMax = 0, idCour = 0;
		for (int i = 0; i < groupes.size(); i++) {
			if ((idCour = groupes.get(i).trouverDernierId()) > idMax)
				idMax = idCour;
			idCour = 0;
		}
		
		return idMax;
	}
	
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de ré-afficher le layout lorsqu'il a déjà était affiché une premiere fois.
	 * Elle supprime le contenue du layout et ré-affiche la scolaire.
	 * 
	 * @param layout Le layout sur lequel on souhaite afficher l'élève.
	 * @param c Le contexte Android pour l'affichage.
	 * @param mode Le mode d'affichage de l'élève : MODE_LISTE ou MODE_TROMBI accépté.
	 * @param largeurEcran La largeur de l'écran pour mode trombi
	 * 
	 * @return Pupil.FAILLURE Si une erreur s'est produite
	 * @return Pupil.SUCCESS sinon
	 */
	public int actualiserAffichage(LinearLayout layout, Context c, int mode, int largeurEcran) {
		int cr = Pupil.SUCCESS;

		if ((mode != Pupil.MODE_LISTE && mode != Pupil.MODE_TROMBI) || layout == null)
			return Pupil.FAILLURE;
		
		layout.removeAllViews(); //< On éfface tout de la listeEleve
		cr = this.afficher(layout, c, mode, largeurEcran); //< On réaffiche la liste des élèves
		layout.invalidate(); //< On refresh l'affichage de la liste
		
		return cr;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'afficher une classe scolaire sur un layout Android. En fonction
	 * du mode choisi l'affichage sera différent.
	 * 
	 * @param layout Le layout sur lequel on souhaite afficher l'élève.
	 * @param c Le contexte Android pour l'affichage.
	 * @param mode Le mode d'affichage de l'élève : MODE_LISTE ou MODE_TROMBI accépté.
	 * @param largeurEcran La largeur de l'écran pour le mode trombi
	 * 
	 * @return Pupil.FAILLURE Si une erreur s'est produite
	 * @return Pupil.SUCCESS sinon
	 */
	public int afficher (LinearLayout layout, Context c, int mode, int largeurEcran) {
		if ((mode != Pupil.MODE_LISTE && mode != Pupil.MODE_TROMBI) || layout == null)
			return Pupil.FAILLURE;
		
		for (int i = 0; i < groupes.size(); i++) {
			int cr = groupes.get(i).afficher(layout, c, mode, largeurEcran);     //< On affiche un groupe sur le layout
			if (cr == Pupil.FAILLURE)
				return cr;
		}
		
		return Pupil.SUCCESS;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'ajouter un élève a un groupe de la classe.
	 * 
	 * @param e L'élève que vous souhaitez ajouter.
	 * @param c Le nom du groupe auquel vous voulez ajouter l'élève.
	 * 
	 * @return Pupil.FAILLURE Si une erreur s'est produite
	 * @return Pupil.SUCCESS sinon
	 */
	public int ajouterEleveAuGroupe (Pupil e, String nomGroupe) {
		if (e == null || nomGroupe == null || nomGroupe == "")
			return Pupil.FAILLURE;
		
		for (int i = 0; i < groupes.size(); i++) {
			if (groupes.get(i).getNom() == nomGroupe) {
				groupes.get(i).ajouterEleve(e);
				return Pupil.SUCCESS;
			}
		}
		return Pupil.FAILLURE;
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
