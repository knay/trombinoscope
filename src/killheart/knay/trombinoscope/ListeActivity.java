package killheart.knay.trombinoscope;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ListeActivity extends Activity {
	RelativeLayout layoutGlobal;
	LinearLayout listeEleve;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView im = new ImageView(this);
		Pupil e = new Pupil();
		e.setNom("toto");
		e.setPrenom("tata");
		
		layoutGlobal = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_liste, null);
		listeEleve = (LinearLayout)layoutGlobal.findViewById(R.id.listelayout);
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
