package killheart.knay.trombinoscope;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author David et Jonathan
 * 
 * L'activité affichant le trombi des élèves d'une classe sur un
 * terminal Android.
 * Elle affiche une scolaire qui elle-même déclenche l'affichage
 * d'un groupe, qui lui-même lance l'affichage de chacun de ses
 * élèves.
 * 
 * @todo Lire les groupes et scolaire directement dans le XML avec xmlmanipulator.
 */
public class TrombiActivity extends Activity {
	// ----- ----- Les classes Android ----- ----- 
	private RelativeLayout layoutGlobal;                 //< Le layout de l'activité complète
	private LinearLayout listeEleve;                     //< Le layout qui affiche juste la liste
	private Button BoutonRetour;                         //< Le bouton retour pour lui ajouter le listener
	private Button BoutonExporter;                       //< Le bouton pour lancer l'exportation PDF, pour lui ajouter le listener
	
	// ----- ----- Les classes et variables classiques ----- ----- 
	private Group listegroupe;                           //< Les groupe contenue dans la classe
	private Grade classe;                                //< La scolaire a afficher
	private XmlManipulator ManipulXml;                   //< Le manipulateur du fichier XML
	private int largeurEcran;                            //< La largeur de l'écran pour afficher
	
	/**
	 * @author David et Jonathan
	 * 
	 * Cette fonction est appelée à la création de l'activité Android.
	 * Elle initialise la scolaire et l'ajoute au layour principal.
	 * 
	 * @param savedInstanceState L'état de l'application.
	 */
	@SuppressWarnings("deprecation") //< On enleve le warning car necessaire pour compatibilité ascendante
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);         //< Appel au super-constructeur
		classe = new Grade();
		listegroupe = new Group();
		
		/*Test*/
		File Racine = Environment.getExternalStorageDirectory();
		String chemin = ""+Racine+"/trombiscol/Xml/classe.xml";//< Chemin en fonction de la racine de la sdcard
		ManipulXml = new XmlManipulator(chemin);
		listegroupe = ManipulXml.LireEleve();
		
		layoutGlobal = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_trombi, null);

		BoutonRetour = (Button)layoutGlobal.findViewById(R.id.btn_retourTrombi); //< On recupère le bouton de retour
		BoutonRetour.setOnClickListener(new OnClickListener() {//< On déclare un nouveau “OnClickListener” pour le bouton retour
			public void onClick(View v) {
				finish();
			}
		});
		
		BoutonExporter = (Button)layoutGlobal.findViewById(R.id.btn_exporter); //< On recupère le bouton d'exportation
		BoutonExporter.setOnClickListener(new OnClickListener() {//< On déclare un nouveau “OnClickListener” pour le bouton retour
			public void onClick(View v) {
				final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND_MULTIPLE);
		        emailIntent.setType("text/html");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ "david.ansillon@gmail.com"});
		        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "test");
		        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "bonjour");
		       // emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        
		      
		        FileOutputStream fop = null;
				File file;
				String head = "<HTML>" +
						"<HEAD><TITLE> NomPromotion </TITLE></HEAD>" +
						"<BODY>" +
						"<table name=eleve>";
				String body = "";
				String table = "<tr>";
				int colones = 1;
				ArrayList<Pupil> eleves = new ArrayList<Pupil>();//<Arrayliste de type Pupil
				eleves = classe.getEleves();//<ArrayList contenant la listes des eleves
				for (Pupil selectEleve : eleves) {
					if((colones % 3) == 0){
					colones++;
					body+="<td><img src=/mnt/sdcard/trombiscol/photos/Classe_sansnom/"+selectEleve.getId()+".jpg>"+
							"<p>"+selectEleve.getNom()+"</p>"+
							"<p>"+selectEleve.getPrenom()+"</p></td>"+
							"</tr>";
					}
					else{
						colones++;
						body+="<td><img src=/mnt/sdcard/trombiscol/photos/Classe_sansnom/"+selectEleve.getId()+".jpg>"+
								"<p>"+selectEleve.getNom()+"</p>" +
								"<p>"+selectEleve.getPrenom()+"</p></td>";

					}
				}
				
				
				String finHtml = "</BODY></HTML>";
				try {
		 
					file = new File("/mnt/sdcard/trombiscol/index.html");
					fop = new FileOutputStream(file);
		 
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}
		 
					// get the content in bytes
					byte[] headInBytes = head.getBytes();
					byte[] bodyInBytes = body.getBytes();
					byte[] tableInBytes = table.getBytes();
					byte[] htmlInBytes = finHtml.getBytes();
					
					fop.write(headInBytes);
					fop.write(bodyInBytes);
					fop.write(tableInBytes);
					fop.write(htmlInBytes);
					fop.flush();
					fop.close();
		 
		 
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (fop != null) {
							fop.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				//emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
		        //emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///mnt/sdcard/trombiscol/index.html"));
		        TrombiActivity.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			}
		});
		
		listeEleve = (LinearLayout)layoutGlobal.findViewById(R.id.trombilayout);

		Configuration config = getResources().getConfiguration();
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			largeurEcran = display.getWidth()*70/100;
	    } 
		else if (config.orientation == Configuration.ORIENTATION_PORTRAIT){	
			largeurEcran = display.getWidth();
	    }
		
		classe.ajouterGroup(listegroupe);
		classe.getGroupes().get(0).setNom("Groupe 1");
		
		AndroidTree.CreateFolder(classe.getNom(), "trombiscol/photos"); //< On crée le dossier pour les photos des scolaires
		classe.setUrlImage(Environment.getExternalStorageDirectory() + "/trombiscol/photos/" + classe.getNom()); //< On définit le chemin vers l'image de chaque élève
		
		classe.afficher(listeEleve, this, Pupil.MODE_TROMBI, largeurEcran);
		
		setContentView(layoutGlobal);
	}

	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'initialiser le menu de l'actionbar de l'activité.
	 * 
	 * @param menu Les éléments présents dans l'actionbar.
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.trombi, menu);
		return true;
	}

}
