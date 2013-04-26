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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ListeActivity extends Activity {
	RelativeLayout layoutGlobal;
	LinearLayout listeEleve;
	Button BoutonRetour;
	Button BoutonApercue;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView im = new ImageView(this);
		Pupil e = new Pupil();
		
		/*Test*/
		File Racine = Environment.getExternalStorageDirectory();
		String chemin = ""+Racine+"/trombiscol/Xml/classe.xml";
		XmlManipulator xml = new XmlManipulator(chemin);
		xml.LireScolaire("Maisonnier");
		
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
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		
		im.setImageResource(R.drawable.limite_tete_photo);
		e.setPhoto(im);
		e.afficher(listeEleve, this, Pupil.MODE_LISTE);
		
		setContentView(layoutGlobal);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liste, menu);
		return true;
	}

}
