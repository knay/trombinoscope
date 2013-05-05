package killheart.knay.trombinoscope;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        /*Test de la création de repertoire*/
        String FolderXml = "Xml";//< Variable FolderXml contenant le nom du dossier ou sont stocké les Xml.
        AndroidTree.CreateRepertory();
		AndroidTree.CreateFolder(FolderXml,"trombiscol");
        
		/**
		 * @author David et Jonathan
		 *
		 * Bouton permettant d'acceder aux Activity
		 * 
		 * @todo 
		 */
		final Button BtListe = (Button) findViewById(R.id.ViewListe);//< Variable faisant le lien au bouton
		BtListe.setOnClickListener(new OnClickListener() {//< On déclare un nouveau “OnClickListener” sur le bouton utilisé pour passer à la seconde activité 
      			
		        public void onClick(View v) {//< fonction Click
			      	Intent intent = new Intent(MainActivity.this, ListeActivity.class);//< creer un intent entre les deux class de chaque activity
			      	startActivity(intent);//< lance l'acctiviter
		      	}
      });
		final Button BtTrombi = (Button) findViewById(R.id.ViewTrombi);
		BtTrombi.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, TrombiActivity.class);
				startActivity(intent);
			}
		});
		
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
