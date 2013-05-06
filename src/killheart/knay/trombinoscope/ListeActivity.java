package killheart.knay.trombinoscope;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * @author David et Jonathan
 * 
 * L'activité affichant la liste des élèves d'une classe sur un
 * terminal Android.
 * Elle affiche une scolaire qui elle-même déclenche l'affichage
 * d'un groupe, qui lui-même lance l'affichage de chacun de ses
 * élèves.
 * 
 * @todo Lire les groupes et scolaire directement dans le XML avec xmlmanipulator.
 */
public class ListeActivity extends Activity {
	// ----- ----- Les classes Android ----- ----- 
	private RelativeLayout layoutGlobal;                 //< Le layout de l'activité complète
	private LinearLayout listeEleve;                     //< Le layout qui affiche juste la liste
	private Button BoutonRetour;                         //< Le bouton retour pour lui ajouter le listener
	private Button BoutonApercue;                        //< Le bouton pour lancer le trombinoscope, pour lui ajouter le listener
	
	// ----- ----- Les classes et variables classiques ----- ----- 
	private Group listegroupe;                           //< Les groupe contenue dans la classe
	private Grade classe;                                //< La scolaire a afficher
	private XmlManipulator ManipulXml;                   //< Le manipulateur du fichier XML
	
	/**
	 * @author David et Jonathan
	 * 
	 * Cette fonction est appelée à la création de l'activité Android.
	 * Elle initialise la scolaire et l'ajoute au layour principal.
	 * 
	 * @param savedInstanceState L'état de l'application.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);          //< Appel au super-constructeur
		classe = new Grade();
		listegroupe = new Group();
		
		/*Test*/
		File Racine = Environment.getExternalStorageDirectory();
		String chemin = ""+Racine+"/trombiscol/Xml/classe.xml";//< Chemin en fonction de la racine de la sdcard
		XmlManipulator xml = new XmlManipulator(chemin);
		this.ManipulXml = xml;
		listegroupe = xml.LireEleve();
		
		layoutGlobal = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_liste, null);

		BoutonRetour = (Button)layoutGlobal.findViewById(R.id.btn_retourliste); //< On recupère le bouton de retour
		BoutonRetour.setOnClickListener(new OnClickListener() {//< On déclare un nouveau “OnClickListener” pour le bouton retour
			public void onClick(View v) {
				finish();
			}
		});
		
		BoutonApercue = (Button)layoutGlobal.findViewById(R.id.btn_lancerapercue); //< On recupère le bouton de l'aperçue
		BoutonApercue.setOnClickListener(new OnClickListener() {//< On déclare un nouveau “OnClickListener” pour le bouton retour
			public void onClick(View v) {
				Intent intent = new Intent(ListeActivity.this, TrombiActivity.class); //< On fait un lient entre l'activité et celle que va lancer
				startActivity(intent); //< On lance l'activité trombinoscope
			}
		});
		
		listeEleve = (LinearLayout)layoutGlobal.findViewById(R.id.listelayout);
		
		classe.ajouterGroup(listegroupe);
		classe.getGroupes().get(0).setNom("1");
		
		classe.afficher(listeEleve, this, Pupil.MODE_LISTE);
		
		AndroidTree.CreateFolder(classe.getNom(), "photo/"); //< On crée le dossier pour les photos des scolaires
		classe.setUrlImage(Environment.getExternalStorageDirectory() + "/trombiscol/photo/" + classe.getNom()); //< On définit le chemin vers l'image de chaque élève
		
		setContentView(layoutGlobal);
	}

	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'initialiser le menu de l'actionbar de l'activité.
	 * 
	 * @param menu Les éléments présents dans l'actionbar.
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.liste, menu);
		return true;
	}

	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'utiliser les bouton du menu.
	 * 
	 * @param menu Les éléments présents dans l'actionbar.
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
        //! wOn regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
           case R.id.action_addeleve:
        	   BoiteDialogueAjout();
          	   return true;
           case R.id.action_remove:
        	   BoiteDialogueSupprimer();
               return true;
           case R.id.action_search:
               return true;
        }
        return false;
    }
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction permet de lancer la boite de dialogue
	 * pour ajouter un eleve.
	 * 
	 */
	private void BoiteDialogueAjout(){
		
        LayoutInflater factory = LayoutInflater.from(this);//< On instancie notre layout en tant que View
        final View alertDialogView = factory.inflate(R.layout.ajout_eleve, null);
 
        AlertDialog.Builder adb = new AlertDialog.Builder(this); //< Création de l'AlertDialog
 
        adb.setView(alertDialogView);//< On affecte la vue personnalisé que l'on a crée à notre AlertDialog
        adb.setTitle("Veuillez remplir le formulaire");//< On donne un titre à l'AlertDialog
        adb.setIcon(android.R.drawable.ic_dialog_alert);//< On modifie l'icône de l'AlertDialog
        
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {//< On affecte un bouton "OK" à notre AlertDialog et on lui affecte un évènement
            public void onClick(DialogInterface dialog, int which) {
            	Pupil e = new Pupil();
            	EditText NouveauNom = (EditText)alertDialogView.findViewById(R.id.NomEleve);//< Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
        		EditText NouveauPrenom = (EditText)alertDialogView.findViewById(R.id.PrenomEleve);//< Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
        		EditText NouveauDate = (EditText)alertDialogView.findViewById(R.id.DateEleve);//< Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
        		Toast.makeText(ListeActivity.this, NouveauNom.getText().toString()+" à étais ajouté", Toast.LENGTH_LONG).show();//< On affiche dans un Toast le texte contenu dans l'EditText de notre AlertDialog
        		
        		ManipulXml.RajouterEleves(NouveauNom.getText().toString(), NouveauPrenom.getText().toString(),NouveauDate.getText().toString());
        		e.setNom(NouveauNom.getText().toString()); //< On définie le nom de l'élève que l'on a ajouté
        		e.setPrenom(NouveauPrenom.getText().toString()); //< On définie le prenom de l'élève que l'on a ajouté
        		e.setDateNaissance(NouveauDate.getText().toString()); //< On définie le dateNaissance de l'élève que l'on a ajouté
        		e.setId(classe.trouverDernierId()+1); //< On définie l'id de l'élève qu'on a ajouté
        		
        		classe.ajouterEleveAuGroupe(e, classe.getGroupes().get(0).getNom()); //< On ajoutes l'élève au premier groupe de la classe
        		classe.actualiserAffichage(listeEleve, layoutGlobal.getContext(), Pupil.MODE_LISTE); //< On rafraichit l'affichage de la liste
        } });
 
        
        adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {//< On crée un bouton "Annuler" à notre AlertDialog et on lui affecte un évènement
            public void onClick(DialogInterface dialog, int which) {//< Lorsque l'on cliquera sur annuler on quittera l'application
            	
          } });
        adb.show();
		
	
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction permet de lancer la boite de dialogue
	 * pour supprimer un eleve.
	 * 
	 */
	private void BoiteDialogueSupprimer(){
		
        LayoutInflater factory = LayoutInflater.from(this);//< On instancie notre layout en tant que View
        final View alertDialogView = factory.inflate(R.layout.supprimer_eleve, null);
 
        AlertDialog.Builder adb = new AlertDialog.Builder(this); //< Création de l'AlertDialog
 
        adb.setView(alertDialogView);//< On affecte la vue personnalisé que l'on a crée à notre AlertDialog
        adb.setTitle("Veuillez remplir le formulaire");//< On donne un titre à l'AlertDialog
        adb.setIcon(android.R.drawable.ic_dialog_alert);//< On modifie l'icône de l'AlertDialog
 
        
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {//< On affecte un bouton "OK" à notre AlertDialog et on lui affecte un évènement
            public void onClick(DialogInterface dialog, int which) {
            	EditText NouveauNom = (EditText)alertDialogView.findViewById(R.id.NomEleve);//< Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
        		EditText NouveauPrenom = (EditText)alertDialogView.findViewById(R.id.PrenomEleve);//< Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
        		Toast.makeText(ListeActivity.this, NouveauNom.getText().toString()+" à étais supprimé", Toast.LENGTH_LONG).show();//< On affiche dans un Toast le texte contenu dans l'EditText de notre AlertDialog
        		
        		/*Test*/
        		String idSuppInt ;
        		idSuppInt = ManipulXml.EleveId(NouveauNom.getText().toString(), NouveauPrenom.getText().toString());
        		ManipulXml.DeletePupil(idSuppInt);
        } });
 
        
        adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {//< On crée un bouton "Annuler" à notre AlertDialog et on lui affecte un évènement
            public void onClick(DialogInterface dialog, int which) {//< Lorsque l'on cliquera sur annuler on quittera l'application
            	
            	finish();
          } });
        adb.show();
		
	
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction permettant de réagir après un retour de resultat d'une activité après sa
	 * fermeture.
	 * Ici on s'en sert pour detecter la valeur de retour de l'activité caméra pour savoir quelle 
	 * ImageView actualiser. Le requestCode attendu est 99.
	 * 
	 * 
	 * @param requestCode Le code idientifiant l'activité qui l'a retourné (pour le retour de la caméra 99)
	 * @param resultCode La valeur de retour de l'activité (ici l'identifiant de l'élève)
	 * @param datas L'intent liant l'actrivité fermé à l'activité courante
	 */
	protected void onActivityResult (int requestCode, int resultCode, Intent datas) {
        super.onActivityResult(requestCode, resultCode, datas);

        if (requestCode == 99) {
        	ImageView ph = (ImageView) listeEleve.findViewById(resultCode);
        	
			Matrix matrix = new Matrix(); //< Création d'une matrice pour pouvoir décaler pivoter l'image
			matrix.postRotate(90); //< On applique une rotation de 90° à la matrice
			
			Bitmap bmp = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/trombiscol/photos/" + resultCode + ".jpg"); //< On récupère la photo du fichier
			bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true); //< On applique la rotation à l'image
			ph.setImageBitmap(bmp); //< On applique l'image à l'imageview
        }
    }

}
