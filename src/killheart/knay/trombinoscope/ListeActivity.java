package killheart.knay.trombinoscope;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
	RelativeLayout layoutGlobal;                 //< Le layout de l'activité complète
	LinearLayout listeEleve;                     //< Le layout qui affiche juste la liste
	Button BoutonRetour;                         //< Le bouton retour pour lui ajouter le listener
	Button BoutonApercue;                        //< Le bouton pour lancer le trombinoscope, pour lui ajouter le listener
	
	// ----- ----- Les classes et variables classiques ----- ----- 
	Group listegroupe;                           //< Les groupe contenue dans la classe
	Grade classe;                                //< La scolaire a afficher
	
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
		Pupil e = new Pupil();
		classe = new Grade();
		listegroupe = new Group();
		
		/*Test*/
		File Racine = Environment.getExternalStorageDirectory();
		String chemin = ""+Racine+"/trombiscol/Xml/classe.xml";
		XmlManipulator xml = new XmlManipulator(chemin);
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
		/*
		listegroupe.ajouterEleve(e);
		listegroupe.ajouterEleve(e);
		listegroupe.ajouterEleve(e);
		listegroupe.ajouterEleve(e);
		listegroupe.ajouterEleve(e);
		listegroupe.ajouterEleve(e);
		listegroupe.ajouterEleve(e);
		listegroupe.ajouterEleve(e);
		listegroupe.ajouterEleve(e);
		listegroupe.ajouterEleve(e);
		*/
		classe.afficher(listeEleve, this, Pupil.MODE_LISTE);
		
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

}
