package killheart.knay.trombinoscope;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	private Element rootXml;
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
		rootXml = dom.getDocumentElement();//< Racine de l'arbre du document, et fournit l'accès principal aux données du document.
			
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction Lire permet d'accéder à la baliser passé
	 * en parametre.
	 *  
	 *  @param tags balise qu'on accéder
	 */
	public void Lire(String tags){
		Pupil e =new Pupil();
		Grade scolaire = new Grade();
		
		NodeList items = rootXml.getElementsByTagName(tags);//< On crée une List contenant tous les noeuds "tags"
		for(int i=0; i<items.getLength(); i++){
				String nom = items.item(0).getNodeValue();
				e.setNom(nom);
				String prenom = items.item(1).getNodeValue();
				e.setPrenom(prenom);
	   	}
		scolaire.ajouterEleve(e);
		
	}
	
}
