package killheart.knay.trombinoscope;

import java.io.File;

import android.os.Environment;

public class AndroidTree {
	File Racine = null;
	String DossierPrincipal = null;
	
	public AndroidTree () {
		Racine = Environment.getExternalStorageDirectory();
		DossierPrincipal = "trombiscol";
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
    	File FileRepertory = new File(Racine+"/"+DossierPrincipal+"/");//< Creation du repertoire principal.
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
    	File FileFolder = new File(Racine+"/"+DossierPrincipal+"/"+NameFolder+"");
    	if (!FileFolder.exists()) {//< Si il n'existe pas.
    		FileFolder.mkdir();//< On le crée.
    	}
    	
    	
    }
}
