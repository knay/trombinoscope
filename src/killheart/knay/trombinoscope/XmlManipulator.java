package killheart.knay.trombinoscope;


import java.io.*;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Date;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlManipulator {
	private Node RacineXml;
	private Node Scolaire;
	private Document Doc;
	private String chemin;
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
			this.Doc = document;
			this.chemin = chemin;
			
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
	 * Fonction Lire permet de lire le nom de la classe.
	 *  
	 */
	public String LireScolaire(){
		//String NomPromotion =((Element) RacineXml).getAttribute("nomscolaire");//< Renvoyer la valeur de l'attribut dont le nom est fourni en paramètre
		String Nom = null;
		NodeList ListEleves =((Element) RacineXml).getElementsByTagName("scolaire");//< Renvoyer une liste des éléments dont le nom est fourni en paramètre
		for(int i =0;i<1;i++){//< On parcourt toute la liste.
			
			Node firstPersonNode = ListEleves.item(i);//< On creer un Node qui contient le noeud que lon parcourt.
            if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){//< si le node est un Element.


                Element firstPersonElement = (Element)firstPersonNode;//< converti le Node en Element.
                
                /*On récupère le nom d'un eleve*/
	            NodeList NameList = firstPersonElement.getElementsByTagName("scolaire");//< On créer une NodeList avec les fils du noeud passer en parametre. 
	            Element NomElement = (Element)NameList.item(0);//< Convertion en Element.
	
	            Nom = NomElement.getAttribute("nomscolaire");//< Renvoyer la valeur de l'attribut dont le nom est fourni en paramètre
				
            }
		}
		return Nom;
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction LireEleve permet de lire les eleves
	 
	 */
	public Group LireEleve(){
		int IdEleveTmp = 0;
		Group Group = new Group();//< nouvel  objet de la classe Grade.	
		
		
		NodeList ListEleves =((Element) RacineXml).getElementsByTagName("eleve");//< Renvoyer une liste des éléments dont le nom est fourni en paramètre
		int taille = ListEleves.getLength();//< calcul de la taille de nodelist.
		for(int i =0;i<taille;i++){//< On parcourt toute la liste.
			Pupil e =new Pupil();//< nouvel ogjet de la class Pupil.
			Node firstPersonNode = ListEleves.item(i);//< On creer un Node qui contient le noeud que lon parcourt.
            if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){//< si le node est un Element.


                Element firstPersonElement = (Element)firstPersonNode;//< converti le Node en Element.
                /*On récupère l'id de l'élève*/
                String tmp = firstPersonElement.getAttribute("id");//< On récupère la valeur du nom.
	            IdEleveTmp = Integer.parseInt(tmp);
                e.setId(IdEleveTmp);
	            
                /*On récupère le nom d'un eleve*/
	            NodeList NameList = firstPersonElement.getElementsByTagName("nom");//< On créer une NodeList avec les fils du noeud passer en parametre. 
	            Element NomElement = (Element)NameList.item(0);//< Convertion en Element.
	
	            NodeList textNom = NomElement.getChildNodes();//< Une NodeList qui contient tous les enfants de ce nœud.
	            String Noms = ((Node)textNom.item(0)).getNodeValue().trim();//< On récupère la valeur du nom.
				e.setNom(Noms);//< on l'ajoute a la classe
				
				/*On récupère le prenom d'un eleve*/
				NodeList PrenomListee = firstPersonElement.getElementsByTagName("prenom");//< Renvoyer une liste des éléments dont le nom est fourni en paramètre 
	            Element PrenomElement = (Element)PrenomListee.item(0);//< Convertion en Element.
	
	            NodeList textPrenom = PrenomElement.getChildNodes();//< Une NodeList qui contient tous les enfants de ce nœud.
	            String Prenoms = ((Node)textPrenom.item(0)).getNodeValue().trim();//< On récupère la valeur du nom.
				e.setPrenom(Prenoms);//< on ajoute a la classe
				
				/*On recupere la date de naissance*/
				NodeList DateList = firstPersonElement.getElementsByTagName("date");//< On créer une NodeList avec les fils du noeud passer en parametre. 
	            Element DateElement = (Element)DateList.item(0);//< Convertion en Element.
	
	            NodeList textDate = DateElement.getChildNodes();//< Une NodeList qui contient tous les enfants de ce nœud.
	            String Date = ((Node)textDate.item(0)).getNodeValue().trim();//< On récupère la valeur du nom.
				e.setDateNaissance(Date);//< on l'ajoute a la classe
				
				Group.ajouterEleve(e);//< on ajoute la classe au groupe.
				
            }
            
		}
		
		return Group;
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
	public void RajouterEleves(String Nom,String Prenom,String DateNaissance){
		int Id = 0;
		Id = IdNext();
		String IdString = Integer.toString(Id);
		
		NodeList nodes = ((Element) RacineXml).getElementsByTagName("groupe");//< On creer une NodeList appartir des enfant du noeud groupe.
		Element Ajout = (Element)nodes.item(0); //< On recupere le premiere element de la NodeList.
		
		Element NewEleve = Doc.createElement("eleve");//<ajout d'un noeud eleve.
	    NewEleve.setAttribute("id", IdString);

		Element NewNom = Doc.createElement("nom");//< ajout d'un noeud nom.
		NewNom.setTextContent(Nom);//< ajout du nom passé en parametre en texte au noeud nom.
		
		Element NewPrenom = Doc.createElement("prenom");//< ajout du noeud prenom.
		NewPrenom.setTextContent(Prenom);//< ajout du prenom passé en parametre en texte au noeud prenom.
		
		Element NewDate = Doc.createElement("date");
		NewDate.setTextContent(DateNaissance);
		
		NewEleve.appendChild(NewNom);//< on met le noeud nom comme fils du noeud eleve.
		NewEleve.appendChild(NewPrenom);//< on met le noeud prenom comme fils du noeud eleve.
		NewEleve.appendChild(NewDate);
		Ajout.appendChild(NewEleve);//< on ajoute le noeud eleve en tant que fils du noeud groupe.
	
		EnregistrerXml();
		
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction IdATNext permet de recuperer l'id a donner au future eleve
	 *  En parcourant tous les id est en récuprérant le plus grand et rajouter +1
	 *  a celui ci pour avoir l'id suivant.
	 */
	private int IdNext(){
		NodeList Id = ((Element) RacineXml).getElementsByTagName("eleve");//< Renvoyer une liste des éléments dont le nom est fourni en paramètre
		int IdMax = 0;//< variable stock le plus grand id trouvé.
		int IdReturn = 0;//< variable de retour.
		int IdTmp = 0;//< variable temporaire stock l'id que l'on parcourt.
		int taille = Id.getLength();//< calcul de la taille de nodelist.
		
		for(int i =0;i<taille;i++){//< On parcourt toute la liste.
			IdTmp = 0;//< initialisation de la varible temporaire.
			Node firstPersonNode = Id.item(i);//< On creer un Node qui contient le noeud que lon parcourt.
            if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){//< si le node est un Element.
            	Element firstPersonElement = (Element)firstPersonNode;//< converti le Node en Element.
            	
	            String tmp = firstPersonElement.getAttribute("id");//< On récupère la valeur du nom.
	            if(tmp != ""){
					IdTmp = Integer.parseInt(tmp);//< Conversion String to int.
		            if(IdTmp > IdMax){//< Si la valeur du id temporaire est superieur a l'id max.
		            	IdMax = IdTmp;//< id temporaire deviens l'id max.
		            }
	            }
            }
            
		}
		IdReturn = IdMax + 1;//< On ajoute 1 a l'id max.
		return IdReturn;//< On retourne cette valeur.
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction DeletePupil permet de supprimer un eleve du fichier xml.
	 * 
	 * @param objet de la class Pupil pour acceder aux methodes.
	 */
	public void DeletePupil(int id){
		
		NodeList Id = ((Element) RacineXml).getElementsByTagName("eleve");//< Renvoyer une liste des éléments dont le nom est fourni en paramètre
		int taille = Id.getLength();//< calcul de la taille de nodelist.
		int IdEleveTmp = 0;
		
		for(int i =0;i<taille;i++){//< On parcourt toute la liste.
			Node firstPersonNode = Id.item(i);//< On creer un Node qui contient le noeud que lon parcourt.
            if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){//< si le node est un Element.
            	Element firstPersonElement = (Element)firstPersonNode;//< converti le Node en Element.
            	
	            String tmp = firstPersonElement.getAttribute("id");//< On récupère la valeur du nom.
	            IdEleveTmp = Integer.parseInt(tmp);
				if(IdEleveTmp == id){
					firstPersonElement.getParentNode().removeChild(firstPersonElement);
				}
            }
		}
		EnregistrerXml();
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction DeletePupil permet d'enregistrer les modification du
	 * Dom dans le fichier xml.
	 */
	private void EnregistrerXml(){
		 try {
		        TransformerFactory tfact =  TransformerFactory.newInstance();
		        Transformer transformer;

				transformer = tfact.newTransformer();
				
		        DOMSource source = new DOMSource(Doc);//< appel au Dom.Modifier seulement dans le flux.
		        File fichier= new File(chemin);//< on creer un objet File contenant le chemin d'acces.
		        FileWriter fw;
				fw = new FileWriter(fichier);//< on creer un objet FileWriter appartir du fichier.
				
				StreamResult result = new StreamResult(fw);//< Objet StreamResult
				transformer.transform(source, result);//< transforme l'arbre DOM en fichier xml.
	        
	         /*Gestion des erreurs*/
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	        } catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	/**
	 * @author David et Jonathan
	 * 
	 * Fonction qui permet de recuperer l'id d'un eleve
	 * en fonction de son nom et prenom.
	 * 
	 * @param Nom
	 * @param Prenom
	 */
	public String EleveId(String NomEleve,String PrenomEleve){
		int IdEleveTmp = 0;
		String IdReturn;
		
		NodeList ListEleves =((Element) RacineXml).getElementsByTagName("eleve");//< Renvoyer une liste des éléments dont le nom est fourni en paramètre
		int taille = ListEleves.getLength();//< calcul de la taille de nodelist.
		for(int i =0;i<taille;i++){//< On parcourt toute la liste.
			Node firstPersonNode = ListEleves.item(i);//< On creer un Node qui contient le noeud que lon parcourt.
            if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){//< si le node est un Element.


                Element firstPersonElement = (Element)firstPersonNode;//< converti le Node en Element.
                String tmp = firstPersonElement.getAttribute("id");//< On récupère la valeur du nom.
	            IdEleveTmp = Integer.parseInt(tmp);
	            
                /*On récupère le nom d'un eleve*/
	            NodeList NameList = firstPersonElement.getElementsByTagName("nom");//< On créer une NodeList avec les fils du noeud passer en parametre. 
	            Element NomElement = (Element)NameList.item(0);//< Convertion en Element.
	
	            NodeList textNom = NomElement.getChildNodes();//< Une NodeList qui contient tous les enfants de ce nœud.
	            String Noms = ((Node)textNom.item(0)).getNodeValue().trim();//< On récupère la valeur du nom.
				
				
				/*On récupère le prenom d'un eleve*/
				NodeList PrenomListee = firstPersonElement.getElementsByTagName("prenom");//< Renvoyer une liste des éléments dont le nom est fourni en paramètre 
	            Element PrenomElement = (Element)PrenomListee.item(0);//< Convertion en Element.
	
	            NodeList textPrenom = PrenomElement.getChildNodes();//< Une NodeList qui contient tous les enfants de ce nœud.
	            String Prenoms = ((Node)textPrenom.item(0)).getNodeValue().trim();//< On récupère la valeur du nom.
				
	            if(Noms.equals(NomEleve) && Prenoms.equals(PrenomEleve)){//< comparaison des nom et prenom de l'élève.
	            	IdReturn = Integer.toString(IdEleveTmp);
	            	return IdReturn;
	            }
            }
		}
		return "null";
	}

	/**
	 * @author David et Jonathan
	 * 
	 * Fonction qui permet de modifier les information concernant
	 * un eleve.
	 * 
	 * @param Objet de la class Pupil
	 */
	public void modifEleve(Pupil eleve){
		
		int IdEleve = eleve.getId();
		String NomEleve = eleve.getNom();
		String PrenomEleve = eleve.getPrenom();
		String DateEleve = eleve.getDateNaissance();
		
		/*Verification*/
		
		NodeList Id = ((Element) RacineXml).getElementsByTagName("eleve");//< Renvoyer une liste des éléments dont le nom est fourni en paramètre
		int taille = Id.getLength();//< calcul de la taille de nodelist.
		int IdEleveTmp = 0;
		
		for(int i =0;i<taille;i++){//< On parcourt toute la liste.
			Node firstPersonNode = Id.item(i);//< On creer un Node qui contient le noeud que lon parcourt.
            if(firstPersonNode.getNodeType() == Node.ELEMENT_NODE){//< si le node est un Element.
            	Element firstPersonElement = (Element)firstPersonNode;//< converti le Node en Element.
            	
	            String tmp = firstPersonElement.getAttribute("id");//< On récupère la valeur du de l'id.
	            IdEleveTmp = Integer.parseInt(tmp);//< conversion de du string en int
				if(IdEleveTmp == IdEleve){
					//On récupère le nom d'un eleve
		            NodeList NameList = firstPersonElement.getElementsByTagName("nom");//< On créer une NodeList avec les fils du noeud passer en parametre. 
		            Element NomElement = (Element)NameList.item(0);//< Convertion en Element.
		           
		            NodeList textNom = NomElement.getChildNodes();//< Une NodeList qui contient tous les enfants de ce nœud.
		           // ((Node) textNom).setTextContent(NomEleve);//< On récupère la valeur du nom.
		          
					/*
		            //On récupère le prenom d'un eleve
					NodeList PrenomListee = firstPersonElement.getElementsByTagName("prenom");//< Renvoyer une liste des éléments dont le nom est fourni en paramètre 
		            Element PrenomElement = (Element)PrenomListee.item(0);//< Convertion en Element.
		
		            NodeList textPrenom = PrenomElement.getChildNodes();//< Une NodeList qui contient tous les enfants de ce nœud.
		            ((Node) textPrenom).setTextContent(PrenomEleve);//< On récupère la valeur du nom.
					
					//On recupere la date de naissance
					NodeList DateList = firstPersonElement.getElementsByTagName("date");//< On créer une NodeList avec les fils du noeud passer en parametre. 
		            Element DateElement = (Element)DateList.item(0);//< Convertion en Element.
		
		            NodeList textDate = DateElement.getChildNodes();//< Une NodeList qui contient tous les enfants de ce nœud.
		            ((Node) textDate).setTextContent(DateEleve);//< On récupère la valeur du nom.
					*/
					
				}
            }
		}
		EnregistrerXml();
		
	}
	
	
}
