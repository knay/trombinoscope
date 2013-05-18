package killheart.knay.trombinoscope;

import java.util.ArrayList;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * @author David et Jonathan
 *
 * La classe gérant un groupe d'élève.
 * Elle permet de gérer plusieurs groupes d'élève 
 * dans une même classe scolaire.
 * 
 * @todo Ajouter le nom du groupe dans l'affichage.
 */
public class Group {
	private ArrayList<Pupil> eleves = new ArrayList<Pupil>();   //< Les élèves de la classe scolaire
	private String nom = null;
	
	/**
	 * @author David et Jonathan
	 * 
	 * Constructeur par défaut de la classe. 
	 * Met un nom par défaut au groupe.
	 */
	Group() {
		nom = "Sans_Nom";
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Constructeur de la classe. 
	 * Permet de définir le nom du groupe directement.
	 * Si n vaut null, nom sera définit avec un nom par défaut.
	 * 
	 * @param n Le nom que vous souhaitez donner au groupe.
	 */
	Group(String n) {
		if (n != null)
			nom = n;
		else
			nom = "Sans_Nom";
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer le nom du groupe.
	 * 
	 * @return Le nom du groupe.
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de récupérer la liste des élèves du groupe.
	 * 
	 * @return La listge d'élève du groupe.
	 */
	public ArrayList<Pupil> getEleves() {
		return eleves;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de modifier le nom du groupe.
	 * 
	 * @param n Le nom que vous souhaitez attribuer à la classe (si null le nom n'est pas modifié).
	 */
	public void setNom(String n) {
		if (n != null)
			nom = n;
	}
	
	/**
	 * @author David et Jonathan
	 *
	 * Permet de définir l'url des photos pour tous les élèves du groupe.
	 * 
	 * @param url L'url vers l'image (sans le nom de l'image)
	 */
	public void setUrlImage(String url) {
		for (int i = 0; i < eleves.size(); i++) {
			eleves.get(i).setUrlImage(url);     //< On définie l'url de l'image de chaque élèves de chaque groupes
		}
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'ajouter un élève au groupe.
	 * 
	 * @param e L'élève que vous voulez ajouter au groupe.
	 */
	public void ajouterEleve(Pupil e) {
		if (e != null)
			eleves.add(e);
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'ajouter un élève au groupe.
	 * 
	 * @param e L'élève que vous voulez ajouter au groupe.
	 */
	public void retirerEleve(Pupil e) {
		eleves.remove(e);    //< Se sert de la fonction equals() de pupil
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de trouver le plus grand id utilisé par un élève
	 * dans le groupe.
	 */
	public int trouverDernierId() {
		int idMax = 0, idCour = 0;
		for (int i = 0; i < eleves.size(); i++) {
			if ((idCour = eleves.get(i).getId()) > idMax)
				idMax = idCour;
			idCour = 0;
		}
		
		return idMax;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet d'afficher un groupe sur un layout Android. En fonction
	 * du mode choisi l'affichage sera différent.
	 * 
	 * @param layout Le layout sur lequel on souhaite afficher le groupe.
	 * @param c Le contexte Android pour l'affichage.
	 * @param mode Le mode d'affichage de l'élève : MODE_LISTE ou MODE_TROMBI accépté.
	 * @param largeurEcran La largeur de l'écran pour savoir combien mettre d'élève par ligne en mode trombi.
	 * 
	 * @return Pupil.FAILLURE Si une erreur s'est produite.
	 * @return Pupil.SUCCESS Si tout va bien.
	 */
	public int afficher (LinearLayout layout, Context c, int mode, int largeurEcran) {
		LinearLayout ligne = null;
		
		if ((mode != Pupil.MODE_LISTE && mode != Pupil.MODE_TROMBI) || layout == null)
			return Pupil.FAILLURE;
		
		//! Si on est en mode liste on affiche le nom du groupe.
		if (mode == Pupil.MODE_LISTE) {
			TextView txt = new TextView(c);
			LinearLayout souslay = new LinearLayout(c);
		
			txt.setText(nom);
			txt.setTextColor(c.getResources().getColor(R.color.ColorBtWhite));
			txt.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 40)); 
			
			souslay.setOrientation(LinearLayout.VERTICAL); //< On met orientation horizontal sur la ligne
			souslay.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)); 
			souslay.setBackgroundColor(c.getResources().getColor(R.color.bgListeTitreGroupe)); //< On met une couleur de fond
			souslay.setGravity(Gravity.CENTER); //< On centre le tout
			
			souslay.addView(txt);
			
			layout.addView(souslay);
		}
		for (int i = 0; i < eleves.size(); i++) {
			int cr = Pupil.FAILLURE;
			if (mode == Pupil.MODE_LISTE) {
				cr = eleves.get(i).afficher(layout, c, mode);     //< On affiche un élève sur le layout
			}
			else if (mode == Pupil.MODE_TROMBI) {
				if (i % (largeurEcran/100) == 0) {
					ligne = new LinearLayout(c);
					ligne.setOrientation(LinearLayout.HORIZONTAL); //< On met orientation horizontal sur la ligne
					ligne.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)); 
					ligne.setGravity(Gravity.CENTER); //< On centre le tout
				
					layout.addView(ligne);
				}
				cr = eleves.get(i).afficher(ligne, c, mode);     //< On affiche un élève sur le layout
			}
			
			if (cr == Pupil.FAILLURE)
				return cr;
		}
		
		return Pupil.SUCCESS;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Permet de savoir si deux groupes sont les mêmes.
	 * 
	 * @param g Le groupe avec lequel vous souhaitez le comparer.
	 * 
	 * @return true Si les deux groupes sont les mêmes
	 * @return false Sinon
	 */
	public Boolean equals(Group g) {
		if (nom == g.getNom()) {
			if (eleves.equals(g.getEleves()) == true)
				return true;
		}
		return false;
	}
}
