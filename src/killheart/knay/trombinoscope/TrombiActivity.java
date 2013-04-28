package killheart.knay.trombinoscope;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TrombiActivity extends Activity {
	RelativeLayout layoutGlobal;
	LinearLayout trombiEleve;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImageView im = new ImageView(this);
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
		
		
		/*im.setImageResource(R.drawable.limite_tete_photo);
		e.setPhoto(im);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		*/
		setContentView(layoutGlobal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trombi, menu);
		return true;
	}

}
