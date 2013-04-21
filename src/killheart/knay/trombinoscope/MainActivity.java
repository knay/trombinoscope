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
        CreateRepertory();//< appel a la fonction CreateRepertory()
        CreateFolder(FolderXml);
        
        final Button BtListe = (Button) findViewById(R.id.ViewListe);
		BtListe.setOnClickListener(new OnClickListener() {
      			
		        public void onClick(View v) {
		      	Intent intent = new Intent(MainActivity.this, ListeActivity.class);
		      	startActivity(intent);
		      	}
      });
		final Button BtTrombi = (Button) findViewById(R.id.ViewTrombi);
		BtTrombi.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, TrombiActivity.class);
				startActivity(intent);
			}
		});
		final Button BtCamera = (Button) findViewById(R.id.ViewCamera);
		BtCamera.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, CameraActivity.class);
				startActivity(intent);
			}
		});
        
    }
    /**
	 * @author David et Jonathan
	 * 
	 * Cette fonction est appelée à la création du répertoire.
	 * Si le dossier existe déjà elle ne le crée pas.
	 * 
	 * @param NameFolder Nom du dossier.
	 */
    public void CreateRepertory(){
    	File Racine = Environment.getExternalStorageDirectory();
    	File FileRepertory = new File(Racine+"/trombiscol/");//< Creation du repertoire principal.
    	if (!FileRepertory.exists()) {//< Si il n'existe pas.
    		FileRepertory.mkdir();//< On le crée
    	}
    	
    }
    
    /**
	 * @author David et Jonathan
	 * 
	 * Cette fonction est appelée à la création de nouveau Dossier.
	 * Ex:Si l'on prend une photo on l'enregistre dans le dossier "Picture".
	 * Il faut donc faire appel à la fonction CreateFolder.
	 * Si le dossier existe déjà elle ne le crée pas.
	 * 
	 * @param NameFolder Nom du dossier.
	 */
    public void CreateFolder(String NameFolder){
    	File Racine = Environment.getExternalStorageDirectory();
    	File FileFolder = new File(Racine+"/trombiscol/"+NameFolder+"");
    	if (!FileFolder.exists()) {//< Si il n'existe pas.
    		FileFolder.mkdir();//< On le crée.
    	}
    	
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
