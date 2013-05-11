package killheart.knay.trombinoscope;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author David et Jonathan
 *
 * Classe permet de gérer la page d'accueil.
 * Elle permettra d'acceder aux activity Liste 
 * et trombi.
 * 
 * @todo 
 */

public class MainActivity extends Activity {
	LinearLayout layoutGlobal = null;    //< Le layout global de l'activité
	ImageView photoPreview1 = null;      //< Photo 1 d'animation
	ImageView photoPreview2 = null;      //< Photo 2 d'animation
	ImageView photoPreview3 = null;      //< Photo 3 d'animation
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        layoutGlobal = (LinearLayout) RelativeLayout.inflate(this, R.layout.activity_main, null);
         
        /*Test de la création de repertoire*/
        String FolderXml = "Xml";//< Variable FolderXml contenant le nom du dossier ou sont stocké les Xml.
        String FolderPhoto = "photos";
        AndroidTree.CreateRepertory();
		AndroidTree.CreateFolder(FolderXml,"trombiscol");
		AndroidTree.CreateFolder(FolderPhoto,"trombiscol");
		
		photoPreview1 = (ImageView) layoutGlobal.findViewById(R.id.photoAnimAccueil1);
		photoPreview2 = (ImageView) layoutGlobal.findViewById(R.id.photoAnimAccueil2);
		photoPreview3 = (ImageView) layoutGlobal.findViewById(R.id.photoAnimAccueil3);
		
		// On crée un utilitaire de configuration pour cette animation
		Animation animation1 = AnimationUtils.loadAnimation(photoPreview1.getContext(), R.anim.anim_accueil);
		Animation animation2 = AnimationUtils.loadAnimation(photoPreview2.getContext(), R.anim.anim_accueil);
		Animation animation3 = AnimationUtils.loadAnimation(photoPreview3.getContext(), R.anim.anim_accueil);
		
		animation2.setStartOffset(3000);
		animation3.setStartOffset(6000);
		// On l'affecte au widget désiré, et on démarre l'animation
		photoPreview1.startAnimation(animation1);
		photoPreview2.startAnimation(animation2);
		photoPreview3.startAnimation(animation3);
		
		/*ActionBar bar = getActionBar();
		bar.setS;*/
        
		final Button BtListe = (Button) layoutGlobal.findViewById(R.id.ViewListe);//< Variable faisant le lien au bouton
		BtListe.setOnClickListener(new OnClickListener() {//< On déclare un nouveau “OnClickListener” sur le bouton utilisé pour passer à la seconde activité 
			/**
			 * @author David et Jonathan
			 *
			 * Listener sur le bouton "acceder à la liste". Permet de lancer l'activité
			 * ListActivity.
			 */
			public void onClick(View v) {//< fonction Click
				Intent intent = new Intent(MainActivity.this, ListeActivity.class);//< creer un intent entre les deux class de chaque activity
				startActivity(intent);//< lance l'acctivité
			}
		});
		final Button BtTrombi = (Button) layoutGlobal.findViewById(R.id.ViewTrombi);
		BtTrombi.setOnClickListener(new OnClickListener(){
			/**
			 * @author David et Jonathan
			 *
			 * Listener sur le bouton "acceder au trombi". Permet de lancer l'activité
			 * TrombiActivity.
			 */
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, TrombiActivity.class);
				startActivity(intent);
			}
		});
		final Button BtQuitter = (Button) layoutGlobal.findViewById(R.id.btn_quitter_appli);
		BtQuitter.setOnClickListener(new OnClickListener(){
			/**
			 * @author David et Jonathan
			 *
			 * Listener sur le bouton "quitter". Permet de quitter l'application.
			 */
			public void onClick(View v){
				finish();
			}
		});
		
		setContentView(layoutGlobal);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
