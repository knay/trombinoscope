package killheart.knay.trombinoscope;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
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
		e.setNom("toto");
		e.setPrenom("tata");
		
		layoutGlobal = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_trombi, null);
		trombiEleve = (LinearLayout)layoutGlobal.findViewById(R.id.trombilayout);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		
		im.setImageResource(R.drawable.limite_tete_photo);
		e.setPhoto(im);
		e.afficher(trombiEleve, this, Pupil.MODE_TROMBI);
		
		setContentView(layoutGlobal);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trombi, menu);
		return true;
	}

}
