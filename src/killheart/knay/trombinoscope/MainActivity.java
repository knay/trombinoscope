package killheart.knay.trombinoscope;

import java.io.File;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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
        CreateRepertory(FolderXml);//< appel a la fonction CreateRepertory().
        
        
    }
    /**
	 * @author David et Jonathan
	 * 
	 * Cette fonction est appelée à la création de nouveau Dossier.
	 * Ex:Si l'on prend une photo on l'enregistre dans le dossier "Picture".
	 * Il faut donc faire appel à la fonction CreateRepertory.
	 * Si le dossier existe déjà elle ne le crée pas.
	 * 
	 * @param string.
	 * @param NameFolder Nom du dossier.
	 * @return void
	 */
    public void CreateRepertory(String NameFolder){
    	File f = new File("/sdcard/trombiscol/"+NameFolder+"");//< Variable File contenant le chemin du repertoire.
    	if (!f.exists()) {//< Si il n'existe pas.
    	  f.mkdir();//< On le crée.
    	}
    	
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
