package killheart.knay.trombinoscope;

import android.os.Bundle;
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
 * L'activité affichant le trombi des élèves d'une classe sur un
 * terminal Android.
 * Elle affiche une scolaire qui elle-même déclenche l'affichage
 * d'un groupe, qui lui-même lance l'affichage de chacun de ses
 * élèves.
 * 
 * @todo Lire les groupes et scolaire directement dans le XML avec xmlmanipulator.
 */
public class TrombiActivity extends Activity {
	// ----- ----- Les classes Android ----- ----- 
	RelativeLayout layoutGlobal;
	LinearLayout trombiEleve;
	
	/**
	 * @author David et Jonathan
	 * 
	 * Cette fonction est appelée à la création de l'activité Android.
	 * Elle initialise la scolaire et l'ajoute au layour principal.
	 * 
	 * @param savedInstanceState L'état de l'application.
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);         //< Appel au super-constructeur
		Pupil e = new Pupil();
		
		
		layoutGlobal = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_trombi, null);
		Button BoutonRetour = (Button)layoutGlobal.findViewById(R.id.btn_retourTrombi); //< On recupère le bouton de retour
		BoutonRetour.setOnClickListener(new OnClickListener() {//< On déclare un nouveau “OnClickListener” pour le bouton retour
			public void onClick(View v) {
			    finish();
			}
		});
		
		Button BoutonApercue = (Button)layoutGlobal.findViewById(R.id.btn_lancerapercue); //< On recupère le bouton de l'aperçue
		BoutonApercue.setOnClickListener(new OnClickListener() {//< On déclare un nouveau “OnClickListener” pour le bouton retour
			public void onClick(View v) {
				Intent intent = new Intent(TrombiActivity.this, ListeActivity.class); //< On fait un lient entre l'activité et celle que va lancer
				startActivity(intent); //< On lance l'activité trombinoscope
			}
		});
		
		trombiEleve = (LinearLayout)layoutGlobal.findViewById(R.id.trombilayout);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		
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
		getMenuInflater().inflate(R.menu.trombi, menu);
		return true;
	}

}
