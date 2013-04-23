package killheart.knay.trombinoscope;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlManipulator {
	private Node Racine;
	
	/**
	 * @author David et Jonathan
	 * 
	 * Constructeur par défaut de la classe.
	 * Ouvre le fichier Xml dans le constructeur
	 * pour éviter la surcharge si on l'ouvre et
	 *  le fermer a chaque fois qu'on doit lire le fichier.
	 *  
	 *  @param File chemin d'acces du fichier.
	 */
	public XmlManipulator(String File){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//< Creer un arbre d'objet DOM appartir du fichier Xml.
		DocumentBuilder builder = null;//< declaration de variable et initialisation.
		Document dom = null;
		
		try {
			builder = factory.newDocumentBuilder();//< Définit l'API DOM pour obtenir des instances de documents à partir d'un document XML. 
		} catch (ParserConfigurationException e) {//< exception cas d'erreur.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			FileInputStream Path = new FileInputStream(File);//< FileInputStream lecture de flux d'octets bruts.
		} catch (FileNotFoundException e) {//< exception cas d'erreur.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			dom = builder.parse(File);//< parse le fichier Xml.
		} catch (SAXException e) {//< exception cas d'erreur.
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {//< exception cas d'erreur.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Racine = dom.getDocumentElement();//< Racine de l'arbre du document, et fournit l'accès principal aux données du document.
			
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction Lire permet de lire les élèves appartenant 
	 * a une classe.
	 *  
	 *  @param tags Nom de la classe.
	 */
	public void LireScolaire(String tags){
		Pupil e =new Pupil();//< nouvel ogjet de la class Pupil.
		Grade scolaire = new Grade();//< nouvel  objet de la classe Grade.
		
		if(Racine.toString() == "trombiscol"){//< Si le noeud racine = trombiscol on continue.
			Node Scolaire = Racine.getFirstChild();//< On récupère le fils du noeud racine.
			if ((Scolaire.getAttributes().toString()) == tags){//< On compare l'attribut au tags.Si c'est la bonne class on continu.
				Node Groupe = Scolaire.getFirstChild();//< On récupère le fils du noeud précedent.
				String NomGroupe = Groupe.getAttributes().toString();//< ON récupère le nom du groupe.
				Node Suivant = Groupe.getFirstChild();//< on recupere le fils suivant
				
				Document dom = null;
				NodeList ListEleves = dom.getElementsByTagName("groupe");//< On créer une list contenant tous les enfants du parametre.
				
				for(int i =0;i<ListEleves.getLength();i++){
					Node premierpersonne = ListEleves.item(i);
					Element firstPersonElement = (Element)premierpersonne;

		            //-------
		            NodeList NameList = firstPersonElement.getElementsByTagName("nom");
		            Element NomElement = (Element)NameList.item(0);

		            NodeList textNom = NomElement.getChildNodes();
		            String Noms = ((Node)textNom.item(0)).getNodeValue().trim();
					e.setNom(Noms);
					
					//----------
					NodeList PrenomListee = firstPersonElement.getElementsByTagName("prenom");
		            Element PrenomElement = (Element)PrenomListee.item(0);

		            NodeList textPrenom = PrenomElement.getChildNodes();
		            String Prenoms = ((Node)textPrenom.item(0)).getNodeValue().trim();
					e.setPrenom(Prenoms);
					
					
				}
			}
		}
		else{
		}
		scolaire.ajouterEleve(e);
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction LireEleve permet de lire un eleve
	 * en fonction de son nom, prenom ou date de naissance 
	 *  
	 *  @param Nom Nom de l'eleve.
	 *  @param Prenom Prenom de l'eleve.
	 *  @param date date de naissance de l'eleve.
	 */
	public void LireEleve(String Nom,String Prenom,Date date){
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction AjoutEleve permet de rajouter un eleve
	 *  
	 *  @param Groupe Nom du groupe de l'eleve.
	 *  @param Nom Nom de l'eleve.
	 *  @param Prenom Prenom de l'eleve.
	 *  @param date date de naissance de l'eleve.
	 */
	public void RajouterEleves(String Groupe,String Nom,String Prenom,Date date_naissance){
	}
}
