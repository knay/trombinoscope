package killheart.knay.trombinoscope;

import java.io.File;

import android.R.string;
import android.os.Bundle;
import android.os.Environment;
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
        CreateRepertory();//< appel a la fonction CreateRepertory()
        CreateFolder(FolderXml);
        
        
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
