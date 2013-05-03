package killheart.knay.trombinoscope;


import java.io.*;
import java.util.Date;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlManipulator {
	private Node RacineXml;
	
	/**
	 * @author David et Jonathan
	 * 
	 * Constructeur par défaut de la classe.
	 * Ouvre le fichier Xml dans le constructeur
	 * pour éviter la surcharge si on l'ouvre et
	 *  le fermer a chaque fois qu'on doit lire le fichier.
	 *  
	 *  @param File chemin d'acces du fichier.
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 */
	public XmlManipulator(String chemin){
		try{
				
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			
			//< création d'un constructeur de documents
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			
			//< lecture du contenu d'un fichier XML avec DOM
			Document document = constructeur.parse(new File(chemin));
			Node Racine = document.getDocumentElement();
			this.RacineXml = Racine;
				
		}catch(ParserConfigurationException pce){
			System.out.println("Erreur de configuration du parseur DOM");
			System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
		}catch(SAXException se){
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel à construteur.parse(xml)");
		}catch(IOException ioe){
			System.out.println("Erreur d'entrée/sortie");
			System.out.println("lors de l'appel à construteur.parse(xml)");
		}
			
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction Lire permet de lire les élèves appartenant 
	 * a une classe.
	 *  
	 *  @param tags Nom de la classe.
	 */
	public void LireScolaire(){
		Pupil e =new Pupil();//< nouvel ogjet de la class Pupil.
		Group Group = new Group();//< nouvel  objet de la classe Grade.
		
		
		if(RacineXml.getNodeName().equals("trombiscol")){//< Si le noeud racine = trombiscol on continue.
			Node Scolaire = RacineXml.getFirstChild();//< On récupère le fils du noeud racine.
			Element r= (Element) Scolaire;
			String name = r.getAttribute("nom");
			if (name.equals("Maisonnier")){//< On compare l'attribut au tags.Si c'est la bonne class on continu.
				Node Groupe = Scolaire.getFirstChild();//< On récupère le fils du noeud précedent.
				String NomGroupe = Groupe.getAttributes().toString();//< ON récupère le nom du groupe.
				//Node Suivant = Groupe.getFirstChild();//< on recupere le fils suivant
				
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
					
					Group.ajouterEleve(e);
				}
			}
		}
		else{
		}
		
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
	public void LireEleve(String Nom){
		
			NodeList liste = ((Document) RacineXml).getElementsByTagName(Nom);
			Element e = (Element)liste.item(0);
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
