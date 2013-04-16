package killheart.knay.trombinoscope;

import android.provider.MediaStore.Images;
import android.widget.LinearLayout;

/**
 * @author David et Jonathan
 *
 * Classe permettant de gérer un élève du trombinoscope.
 * Elle permet d'afficher l'élève sur une activité android,
 * ainsi que de définir toutes les choses utiles pour référencer 
 * un élève.
 * 
 * @todo compléter la fonction d'affichage de l'élève
 */
public class Pupil {
	// ----- ----- Les constantes ----- -----
	public static final int SUCCESS = 0;         //< Si tout s'est bien passé
	public static final int FAILLURE = -1;       //< Valeur retournée en cas d'érreur
	public static final int PHOTO_DEFINIE = 0;   //< Permet de savoir si une photo a été définie pour cette élève ou pas encore
	public static final int PAS_DE_PHOTO = 1;    //< Permet de savoir si, lors de l'affichage, on a trouvé une photo ou non
	public static final int MODE_LISTE = 1;      //< Permet d'afficher l'élève en mode liste
	public static final int MODE_TROMBI = 2;     //< Permet d'afficher l'élève en mode trombinoscope (prévisualisation)
	
	// ----- ----- Les classes Android ----- ----- 
	private Images photo = null;                 //< La photo Android de l'élève
		
	// ----- ----- Les classes et variables classiques ----- ----- 
	private String nom = null;                   //< Le nom de l'élève
	private String prenom = null;                //< Le prénom de l'élève
	private String dateNaissance = null;         //< La date de naissance de l'élève
	private int id = 0;                          //< L'identifiant unique d'un élève
	
	/**
	 * @author David et Jonathan
	 * 
	 * Constructeur par défaut de la classe.
	 * Met un nom, une date et un prénom par défaut.
	 */
	public Pupil () {
		nom = "Eleve";
		prenom = "1";
		dateNaissance = "00/00/2012";
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Constructeur avec paramètre de la classe.
	 * Permet de définir le nom, prénom, date de naissance et
	 * id à l'instanciation.
	 * 
	 * @param n Le nom que vous souhaitez donner à l'élève.
	 * @param p Le prénom que vous souhaitez donner à l'élève.
	 * @param d La date de naissance que vous souhaitez donner à l'élève.
	 * @param i L'identifiant que vous souhaitez donner à l'élève (doit être > 0 sinon id sera mit à 0).
	 */
	public Pupil (String n, String p, String d, int i) {
		nom = n;
		prenom = p;
		dateNaissance = d;
		
		if (i > 0)
			id = i;
		else
			id = 0;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer le nom de l'élève.
	 * 
	 * @return Le nom de l'élève.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer le prénom de l'élève.
	 * 
	 * @return Le prénom de l'élève.
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer la date de naissance de l'élève.
	 * 
	 * @return La date de naissance de l'élève.
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer l'identifiant de l'élève.
	 * 
	 * @return L'identifiant de l'élève.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer l'image ANdroid de la photo de l'élève.
	 * 
	 * @return L'image Android de la photo de l'élève.
	 */
	public Images getPhoto() {
		return photo;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de (re)définir le nom de l'élève.
	 * 
	 * @param n Le nom que vous souhaitez attribuez à l'élève.
	 */
	public void setNom(String n) {
		nom = n;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de (re)définir le prénom de l'élève.
	 * 
	 * @param p Le prénom que vous souhaitez attribuez à l'élève.
	 */
	public void setPrenom(String p) {
		prenom = p;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de (re)définir la date de naissance de l'élève.
	 * 
	 * @param d La date de naissance que vous souhaitez attribuez à l'élève.
	 */
	public void setDateNaissance(String d) {
		dateNaissance = d;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de (re)définir l'identifiant de l'élève.
	 * 
	 * @param i L'identifiant que vous souhaitez attribuez à l'élève (doit être >= 0).
	 */
	public void setNom(int i) {
		if (i >= 0)
			id = i;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de (re)définir la photo de l'élève.
	 * 
	 * @param ph L'image Android de la photo.
	 */
	public void setPhoto (Images ph) {
		if (ph != null)
			photo = ph;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de savoir si une photo à déjà été définie pour l'élève.
	 * 
	 * @return PAS_DE_PHOTO si aucune photo n'a été attribué à l'élève.
	 * @return PHOTO_DEFINIE sinon.
	 */
	public int hasPhoto() {
		return (photo == null) ? PAS_DE_PHOTO : PHOTO_DEFINIE;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'afficher un élève sur un layout Android. En fonction
	 * du mode choisi l'affichage sera différent.
	 * 
	 * @param layout Le layout sur lequel on souhaite afficher l'élève.
	 * @param mode Le mode d'affichage de l'élève : MODE_LISTE ou MODE_TROMBI accépté.
	 * 
	 * @return SUCCESS si tout s'est bien passé et que la photo de l'élève est définie.
	 * @return PAS_DE_PHOTO si tout s'est bien passé mais que la photo de l'élève n'est pas définie.
	 * @return FAILLURE Si un problème a eu lieu.
	 */
	public int Affiche (LinearLayout layout, int mode) {
		// Si le mode n'existe pas... FAIL !
		if ((mode != MODE_LISTE && mode != MODE_TROMBI) || layout == null)
			return FAILLURE;
		
		// Si la photo n'est pas définie ou affiche un point d'intérogation à la place de la photo
		if (photo == null) {
			if (mode == MODE_LISTE) {
				
			}
			else if (mode == MODE_TROMBI) {
				
			}
			return PAS_DE_PHOTO;
		}
		// Si la photo est définie on affiche l'élève correctement
		else {
			if (mode == MODE_LISTE) {	//< Affichage en mode liste
				
			}
			else if (mode == MODE_TROMBI) { //< Affichage en mode trombi
				
			}
			return SUCCESS;
		}
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer une chaine de caractère caractéristique à l'élève.
	 * 
	 * @return Une chaine de caractère caractéristique à l'élève.
	 */
	public String toString() {
		return id + " " + nom + " "+ prenom + " [" + dateNaissance + "]";
	}
}
