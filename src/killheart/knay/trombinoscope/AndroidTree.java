package killheart.knay.trombinoscope;

import java.io.File;

import android.os.Environment;

/**
 * @author David et Jonathan
 *
 * Cette classe permet de manipuler les dossiers et fichier
 * dans le dossier trombiscol créé à la racine de sdcard.
 * 
 * @todo AJOUTER PLUS DE FONCTIONS !
 */
public class AndroidTree {
	private static File Racine = Environment.getExternalStorageDirectory();
	private static String DossierPrincipal = "trombiscol";
	
	/**
	 * @author David et Jonathan
	 * 
	 * Constructeur par défaut de la classe. Permet d'initialiser
	 * les variables de base.
	 */
	public AndroidTree () {
		
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Cette fonction est appelée à la création du répertoire.
	 * Si le dossier existe déjà elle ne le crée pas.
	 * 
	 * @param NameFolder Nom du dossier.
	 */
    public static void CreateRepertory(){
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
    public static void CreateFolder(String NameFolder,String chemin){
    	File FileFolder = new File(Racine+"/"+chemin+"/"+NameFolder+"");
    	if (!FileFolder.exists()) {//< Si il n'existe pas.
    		FileFolder.mkdir();//< On le crée.
    	}
    	
    	
    }
}
