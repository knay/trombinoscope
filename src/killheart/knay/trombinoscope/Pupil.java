package killheart.knay.trombinoscope;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ImageView;

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
	private ImageView photo = null;              //< La photo Android de l'élève
		
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
		nom = "Nom Eleve";
		prenom = "Prenom Eleve";
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
	public ImageView getPhoto() {
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
	public void setPhoto(ImageView ph) {
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
	 * @param c Le contexte Android pour l'affichage.
	 * @param mode Le mode d'affichage de l'élève : MODE_LISTE ou MODE_TROMBI accépté.
	 * 
	 * @return SUCCESS si tout s'est bien passé et que la photo de l'élève est définie.
	 * @return PAS_DE_PHOTO si tout s'est bien passé mais que la photo de l'élève n'est pas définie.
	 * @return FAILLURE Si un problème a eu lieu.
	 */
	public int afficher(LinearLayout layout, final Context c, int mode) {
		int cr = SUCCESS;                           //< Valeur de retour
		LinearLayout lay = new LinearLayout(c);     //< Layout correspondant à la ligne
		LinearLayout bordure = new LinearLayout(c); //< Layout correspondant à la ligne
		TextView txt = new TextView(c);             //< Le texte affichant le nom de l'élève dans la liste
		TextView NomTrombi = new TextView(c); //< Le texte affichant le nom de l'élève dans le trombi
		TextView PrenomTrombi = new TextView(c); //< Le texte affichant le nom de l'élève dans le trombi
		
		// Si le mode n'existe pas... FAIL !
		if ((mode != MODE_LISTE && mode != MODE_TROMBI) || layout == null)
			return FAILLURE;
		
		txt.setText(nom + " " + prenom);//< On met le nom sur le texte android
		NomTrombi.setText(nom);//< On met le nom sur le texte android
		PrenomTrombi.setText(prenom);//< On met le nom sur le texte android
		
		//! Affichage en mode liste 
		if (mode == MODE_LISTE) {
			//! Préparation du texte du nom
			txt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)); //< On met le layout
			txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);   //< On change la taille du texte
			txt.setPadding(10, 20, 0, 0);                       //< On met un petit padding en haut et a gacueh (plus jolie)
			
			//! Préparation de la photo (avec une image par défaut si non définie, avec l'image définie sinon)
			if (photo == null) {   //< Si la photo n'a pas été définie
				photo = new ImageView(c);                                                                  //< Instanciation de l'objet
				photo.setImageResource(R.drawable.icon_photo);                                            //< On va chercher l'image par défaut
				cr = PAS_DE_PHOTO;                                                                         //< Compte rendu avec Pas de photo
			}
			photo.setLayoutParams(new LinearLayout.LayoutParams(80, LayoutParams.MATCH_PARENT));           //< On redimensionne la view de l'image
			
			lay.addView(photo);  //< Ajout de la photo sur la ligne
			lay.addView(txt);    //< Ajout du texte sur la ligne
			
			lay.setOrientation(LinearLayout.HORIZONTAL);                             //< On met orientation horizontal sur la ligne
			lay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 85));    //< On fait une ligne de 70dp de hauteur
			lay.setBackgroundColor(Color.LTGRAY);                                    //< On met une couleur de fond
			lay.setOnLongClickListener(new OnLongClickListener() {
				public boolean onLongClick(View v) {
					CameraActivity Cam = new CameraActivity();
					Intent intent = new Intent(c, Cam.getClass());
					Cam.setNom("ph "+id);
					c.startActivity(intent);
	                return false;
                }
			});
			
			bordure.setOrientation(LinearLayout.HORIZONTAL);                             //< On met orientation horizontal sur la ligne
			bordure.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 90));    //< On fait une ligne de 70dp de hauteur
			bordure.setBackgroundColor(Color.BLACK);                                     //< On met une couleur noir à la bordure
			
			bordure.addView(lay);     //< On ajoute la ligne au layout qui fait la bordure de la ligne
			
			layout.addView(bordure);  //< On ajoute la ligne sur le layout demandé
			
			if (cr == PAS_DE_PHOTO)
				photo = null;         //< On n'oublie pas de remettre la photo a null pour savoir qu'on a pas de vrai photo
		}
		//! Affichage en mode trombinoscope
		else if (mode == MODE_TROMBI) {
			txt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)); //< On definit le layout du texte
			txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);   //< On change la taille de la font 
			NomTrombi.setPadding(15, 4, 0, 0);  //< On met un petit padding pour centrer le nom
			PrenomTrombi.setPadding(15, 4, 0, 0);//< On met un petit padding pour centrer le prenom
			
			if (photo == null) {
				photo = new ImageView(c);  //< Instanciation de l'objet
				photo.setImageResource(R.drawable.icon_photo);  //< On va chercher l'image par défaut
				photo.setPadding(7, 7, 0, 0); //< On met un padding 
				cr = PAS_DE_PHOTO; //< Compte rendu avec Pas de photo
			}
			
			photo.setLayoutParams(new LinearLayout.LayoutParams(80, 80)); //< On redimensionne la view de l'image
			
			lay.addView(photo);  //< Ajout de la photo sur la ligne
			lay.addView(NomTrombi);    //< Ajout du Nom sur la ligne
			lay.addView(PrenomTrombi);    //< Ajout du Prenom sur la ligne
			
			lay.setOrientation(LinearLayout.VERTICAL);//< On met orientation vertical sur le layout trombi
			//lay.setGravity(Gravity.CENTER_HORIZONTAL);//< On centre les éléments
			lay.setLayoutParams(new LayoutParams(150, 150));//< On définie la taille d'une 'case' pour l'élève
			lay.setBackgroundColor(Color.LTGRAY);//< On met une couleur de fond
			
			layout.addView(lay); //< On ajoute la ligne sur le layout demandé
			
			if (cr == PAS_DE_PHOTO)
				photo = null; //< On n'oublie pas de remettre la photo a null pour savoir qu'on a pas de vrai photo
		}
		
		return cr;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de savoir si deux élèves sont les mêmes.
	 * 
	 * @param e L'élève avec lequel vous souhaitez le comparer.
	 * 
	 * @return true Si les deux élèves sont les mêmes
	 * @return false Sinon
	 */
	public Boolean equals(Pupil e) {
		return (nom == e.getNom() && prenom == e.getPrenom() && dateNaissance == e.getDateNaissance() && id == e.getId()) ? true : false;
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
