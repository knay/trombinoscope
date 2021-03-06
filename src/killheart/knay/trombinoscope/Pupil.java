package killheart.knay.trombinoscope;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
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
	private String urlImage = null;              //< Le chemin vers l'image
	private int id = 0;                          //< L'identifiant unique d'un élève

	/**
	 * @author David et Jonathan
	 * 
	 * Constructeur par défaut de la classe.
	 * Met un nom, une date et un prénom par défaut.
	 */
	public Pupil () {
		nom = "ANSILLON";
		prenom = "David";
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
	 * Permet de récupérer l'image ANdroid de la photo de l'élève.
	 * 
	 * @return Le chemin vers l'image de l'élève
	 */
	public String getUrlImage() {
		return urlImage;
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
	public void setId(int i) {
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
	 * Permet de définir l'url de la photo de l'élève.
	 * 
	 * @param url L'url vers l'image (sans le nom de l'image)
	 */
	public void setUrlImage(String url) {
		if (url != null && url != "")
			urlImage = url + "/" + id + ".jpg";
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
	 * Permet d'afficher un élève sur un layout Android. Affiche l'elève
	 * en mode liste.
	 * 
	 * @param layout Le layout sur lequel on souhaite afficher l'élève.
	 * @param c Le contexte Android pour l'affichage.
	 * @param mode Le mode d'affichage de l'élève : MODE_LISTE ou MODE_TROMBI accépté.
	 * 
	 * @return SUCCESS si tout s'est bien passé et que la photo de l'élève est définie.
	 * @return PAS_DE_PHOTO si tout s'est bien passé mais que la photo de l'élève n'est pas définie.
	 * @return FAILLURE Si un problème a eu lieu.
	 */
	@SuppressWarnings("deprecation") //< Necessaire pour rétro compatibilité
    private int afficherListe(LinearLayout layout, final Context c, int mode) {
		int cr = SUCCESS;                           //< Valeur de retour
		LinearLayout lay = new LinearLayout(c);     //< Layout correspondant à la ligne
		LinearLayout bordure = new LinearLayout(c); //< Layout correspondant à la ligne
		TextView txt = new TextView(c);             //< Le texte affichant le nom de l'élève dans la liste
		Bitmap bmp = null;                          //< L'image de la photo (pour aller chercher dans l'url)
		
		//! Si le mode n'existe pas... FAIL !
		if ((mode != MODE_LISTE && mode != MODE_TROMBI) || layout == null || c == null)
			return FAILLURE;
		
		//! Préparation du texte du nom
		txt.setText(nom + " " + prenom); //< On met le nom sur le texte android
		txt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)); //< On met le layout
		txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15); //< On change la taille du texte
		txt.setPadding(10, 0, 0, 0); //< On met un petit padding en haut et à gauche (plus jolie)
		txt.setGravity(Gravity.CENTER_VERTICAL);
		txt.setTextColor(c.getResources().getColor(R.color.txtItemList)); //< On met une couleur au texte
		
		//! Préparation de la photo (avec une image par défaut si non définie, avec l'image définie sinon)
		photo = new ImageView(c); //< Instanciation de l'objet
		bmp = BitmapFactory.decodeFile(urlImage);
		if (bmp != null)
			photo.setImageBitmap(bmp); //< on va chercher l'image dans le lien
		else {
			photo.setImageResource(R.drawable.icon_photo); //< On va chercher l'image par défaut
			cr = PAS_DE_PHOTO; //< Compte rendu avec pas de photo
		}
		
		photo.setPadding(3, 0, 0, 0); //< On met un petit padding a gauche
		photo.setLayoutParams(new LinearLayout.LayoutParams(80, LayoutParams.MATCH_PARENT)); //< On redimensionne la view de l'image
		photo.setId(id); //< On definie l'id de la photo
		photo.setOnLongClickListener(new ListePhotoOnLongClick()); //< Mise en place du onlongclicklistener sur la photo (déclenche la prise de photo)
		
		lay.addView(photo); //< Ajout de la photo sur la ligne
		lay.addView(txt); //< Ajout du texte sur la ligne
		
		lay.setOrientation(LinearLayout.HORIZONTAL); //< On met orientation horizontal sur la ligne
		lay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 85)); //< On fait une ligne de 70dp de hauteur
		lay.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.fond_item_liste)); //< On met un fond à l'item
		lay.setGravity(Gravity.CENTER); //< On centre le tout
		
		lay.setOnTouchListener(new ListeOnTouch()); //< Mise en place d'un ontouchlistener pour changer la couleur du layout quand on le touche
		lay.setOnClickListener(new ListeOnClick()); //< Mise en place du onlongclicklistener pour afficher le détail de l'élève
		
		bordure.setOrientation(LinearLayout.HORIZONTAL); //< On met orientation horizontal sur la ligne
		bordure.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 90)); //< On fait une ligne de 70dp de hauteur
		bordure.setBackgroundColor(Color.BLACK); //< On met une couleur noir à la bordure
		
		bordure.addView(lay); //< On ajoute la ligne au layout qui fait la bordure de la ligne
		
		layout.addView(bordure); //< On ajoute la ligne sur le layout demandé
		
		return cr;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'afficher un élève sur un layout Android. Affiche l'elève
	 * en mode trombinoscope.
	 * 
	 * @param layout Le layout sur lequel on souhaite afficher l'élève.
	 * @param c Le contexte Android pour l'affichage.
	 * @param mode Le mode d'affichage de l'élève : MODE_LISTE ou MODE_TROMBI accépté.
	 * 
	 * @return SUCCESS si tout s'est bien passé et que la photo de l'élève est définie.
	 * @return PAS_DE_PHOTO si tout s'est bien passé mais que la photo de l'élève n'est pas définie.
	 * @return FAILLURE Si un problème a eu lieu.
	 */
	@SuppressWarnings("deprecation") //< Necessaire pour compatibilité ascendante
    private int afficherTrombi(LinearLayout layout, final Context c, int mode) {
		int cr = SUCCESS;                       //< Valeur de retour
		LinearLayout lay = new LinearLayout(c); //< Layout correspondant à la ligne
		TextView txt = new TextView(c);         //< Le texte affichant le nom de l'élève dans la liste
		Bitmap bmp = null;                      //< L'image de la photo (pour aller chercher dans l'url)
		
		// Si le mode n'existe pas... FAIL !
		if ((mode != MODE_LISTE && mode != MODE_TROMBI) || layout == null || c == null)
			return FAILLURE;
		
		txt.setText(nom + "\n" + prenom); //< On met le nom sur le texte android
		txt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)); //< On definit le layout du texte
		txt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15); //< On change la taille de la font 
		txt.setPadding(15, 4, 0, 0); //< On met un petit padding pour centrer le nom
		
		//! Préparation de la photo (avec une image par défaut si non définie, avec l'image définie sinon)
		photo = new ImageView(c); //< Instanciation de l'objet
		bmp = BitmapFactory.decodeFile(urlImage);
		if (bmp != null)
			photo.setImageBitmap(bmp); //< on va chercher l'image dans le lien
		else {
			photo.setImageResource(R.drawable.icon_photo); //< On va chercher l'image par défaut
			cr = PAS_DE_PHOTO; //< Compte rendu avec pas de photo
		}
		
		photo.setLayoutParams(new LinearLayout.LayoutParams(80, 80)); //< On redimensionne la view de l'image
		
		lay.addView(photo); //< Ajout de la photo sur la ligne
		lay.addView(txt); //< Ajout du Nom sur la ligne
		
		lay.setOrientation(LinearLayout.VERTICAL); //< On met orientation vertical sur le layout trombi
		lay.setLayoutParams(new LayoutParams(100, 150)); //< On définie la taille d'une 'case' pour l'élève
		lay.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.fond_item_liste)); //< On met un fond à l'item
		
		layout.addView(lay); //< On ajoute la ligne sur le layout demandé
		
		return cr;
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
		
		// Si le mode n'existe pas... FAIL !
		if ((mode != MODE_LISTE && mode != MODE_TROMBI) || layout == null || c == null)
			return FAILLURE;
		
		//! Affichage en mode liste 
		if (mode == MODE_LISTE) {
			cr = afficherListe(layout, c, mode);
		}
		//! Affichage en mode trombinoscope
		else if (mode == MODE_TROMBI) {
			cr = afficherTrombi(layout, c, mode);
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
	
	/**
	 * @author David et Jonathan
	 * 
	 * Classe implémentant ontouchlistener pour la vue en mode liste.
	 * Elle permet de réagir lorsque l'on touche un Eleve en mode liste.
	 */
	public class ListeOnTouch implements OnTouchListener {
		/**
		 * @author David et Jonathan
		 * 
		 * Fonction permettant de réagir lorsqu'on touche le layout.
		 * Lorsque l'on clique elle change la couleur du layout, et 
		 * lorsqu'on relache elle remet dans une couleur initiale.
		 * 
		 * @return Toujours false.
		 */
		@SuppressWarnings("deprecation") //< Nécessaire pour rétro-compatibilité
        public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()) {
				case MotionEvent.ACTION_DOWN:  //< Si on appuie
				case MotionEvent.ACTION_MOVE:  //< Ou si on bouge en restant sur le layout
					v.setBackgroundDrawable(v.getResources().getDrawable(R.drawable.fond_item_liste_pressed));//< Alors on applique la couleur on click
				break;
		        default: //< Sinon
		        	v.setBackgroundDrawable(v.getResources().getDrawable(R.drawable.fond_item_liste));
		        break;
			}
            return false; //< Retourner false sinon le onlongclick ne marche pas.
        }
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Classe implémentant OnLongClickListener pour la vue en mode liste.
	 * Elle permet de réagir lorsque l'on clique longtemps sur la photo
	 * d'un Eleve en mode liste.
	 */
	public class ListePhotoOnLongClick implements OnLongClickListener { 
		/**
		 * @author David et Jonathan
		 * 
		 * Fonction permettant de réagir lorsqu'on clique longtemps sur 
		 * une photo d'un élève. Elle lance l'activité Camera pour prendre 
		 * une photo pour l'élève selectionné.
		 * 
		 * @return Toujours true mais devrait renvoyer false en cas d'erreur.
		 */
		public boolean onLongClick(View v) {
			Intent intent = new Intent(v.getContext(), CameraActivity.class);
			intent.putExtra("url", urlImage); //< On envoie l'id en paramètre à la camera pour savoir où enregistrer la photo
			((Activity) v.getContext()).startActivityForResult(intent, 99); //< On demarre l'activité
			
			return true;
        }
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Classe implémentant OnClickListener pour la vue en mode liste.
	 * Elle permet de réagir lorsque l'on clique sur un élève. Elle 
	 * affiche le détails sur l'élève dans un popup.
	 */
	public class ListeOnClick implements OnClickListener {
		/**
		 * @author David et Jonathan
		 * 
		 * Fonction permettant de réagir lorsqu'on clique sur un élève.
		 * Elle lance un popup détaillant l'élève.
		 * 
		 */
		public void onClick(View v) {
			
			LinearLayout layoutGlobal = null;   //< Le layout global de la boite de dialogue
			final AlertDialog.Builder r = new AlertDialog.Builder(v.getContext());
			TextView titre = null;              //< Le titre de la boite de dialogue
			TextView txtnom = null;             //< Le texte contenant le nom de l'élève
			TextView txtprenom = null;          //< Le texte contenant le prénom de l'élève
			TextView txtdateNaissance = null;   //< Le texte contenant la date de naissance de l'élève
			ImageView imgPhoto = null;          //< L'image view contenant la photo à afficher sur la boite de dialogue
			
			layoutGlobal = (LinearLayout) View.inflate(v.getContext(), R.layout.dialog_liste, null); //< On récupère le layoutglobal a partir de la view
			
			titre = (TextView) layoutGlobal.findViewById(R.id.dialogueTitre); //< On modifie le titre de la boite de dialogue
			titre.setText(prenom + " " + nom);
			
			txtnom = (TextView) layoutGlobal.findViewById(R.id.dialogueNom); //< On modifie le nom de l'élève de la boite de dialogue
			txtnom.setText("Nom : " + nom);
			
			txtprenom = (TextView) layoutGlobal.findViewById(R.id.dialoguePrenom); //< On modifie le prénom de l'élève de la boite de dialogue
			txtprenom.setText("Prénom : " + prenom);
			
			txtdateNaissance = (TextView) layoutGlobal.findViewById(R.id.dialogueNaissance); //< On modifie la date de naissance de l'élève de la boite de dialogue
			txtdateNaissance.setText("Date de naissance : " + dateNaissance);
			
			imgPhoto = (ImageView) layoutGlobal.findViewById(R.id.dialoguePhoto); //< On modifie la photo de l'élève de la boite de dialogue
			if (photo == null)
				imgPhoto.setImageResource(R.drawable.icon_photo); //< Si pas de photo on affiche la photo par défaut
			else {
				photo.buildDrawingCache();
				imgPhoto.setImageBitmap(photo.getDrawingCache());
			}
			/**
			 * Modification de l'eleve
			 */
			Button BoutonModif = (Button)layoutGlobal.findViewById(R.id.bt_ModifierEleve); //< On recupère le bouton de modification
			BoutonModif.setOnClickListener(new OnClickListener() {//< On déclare un nouveau “OnClickListener” pour modifier
				public void onClick(final View w) {
					final LinearLayout sousLayout = (LinearLayout) View.inflate(w.getContext(), R.layout.modif_eleve, null);     //< Le layout de modification d'un eleve
					
					EditText txtnom = null;             //< Le texte contenant le nom de l'élève
					EditText txtprenom = null;          //< Le texte contenant le prénom de l'élève
					EditText txtdateNaissance = null;   //< Le texte contenant la date de naissance de l'élève
					TextView titre = null;              //< Le titre de la boite de dialogue
					
					//sousLayout =  //< On récupère le layoutglobal a partir de la view
					
					titre = (TextView) sousLayout.findViewById(R.id.dialogueTitre); //< On modifie le titre de la boite de dialogue
					titre.setText("Modification");
					
					txtnom = (EditText) sousLayout.findViewById(R.id.NewNom); //< On modifie le nom de l'élève de la boite de dialogue
					txtnom.setText(nom);
					
					txtprenom = (EditText) sousLayout.findViewById(R.id.NewPrenom); //< On modifie le prénom de l'élève de la boite de dialogue
					txtprenom.setText(prenom);
					
					txtdateNaissance = (EditText) sousLayout.findViewById(R.id.NewNaissance); //< On modifie la date de naissance de l'élève de la boite de dialogue
					txtdateNaissance.setText(dateNaissance);
					
					r.setView(sousLayout);
					r.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) { 
							//LinearLayout recupLayout = null;	//< Le layout qui permet de recupe le nom.. lors de lappui sur valider
							//recupLayout = (LinearLayout) View.inflate(w.getContext(), R.layout.modif_eleve, null);    
							//< Le texte contenant le nom de l'élève
							    //< Le texte contenant le prénom de l'élève
							
							EditText Nnom = (EditText) sousLayout.findViewById(R.id.NewNom);
							nom = Nnom.getText().toString();
							
							EditText Nprenom = (EditText) sousLayout.findViewById(R.id.NewPrenom);
							prenom = Nprenom.getText().toString();
							
							EditText NdateNaissance = (EditText) sousLayout.findViewById(R.id.NewNaissance);
							dateNaissance = NdateNaissance.getText().toString();
							
				 			ListeActivity.ManipulXml.modifEleve(Pupil.this);
				 			Toast.makeText(w.getContext().getApplicationContext(), "Nouveau nom : " + nom , Toast.LENGTH_LONG).show();
				 			
						}
					});
					r.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) { 
						
						}
					});
					
					r.show();
				}
			});
			
			/**
			 * Suppresion de cette eleve
			 */
			
			Button BoutonSupp = (Button) layoutGlobal.findViewById(R.id.bt_SupprimerEleve); //< On recupère le bouton de suppression
			BoutonSupp.setOnClickListener(new OnClickListener() {//< On déclare un nouveau “OnClickListener” pour supprimer
				public void onClick(View w) {
					int idEleve = id;//< on recupere l'id de l'eleve selectioner.
					ListeActivity.ManipulXml.DeletePupil(idEleve);//< appel a la fonction de suppression d'un eleve
					//< On close le Layout.
					//< On actualise l'affichage.
				}
				
			});
			
			
			//! On démarre la boite de dialogue
			r.setView(layoutGlobal); //< On y met le layout global à l'interrieur
			r.setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) { 
					
				}
			}).show();
		}
	}
}
