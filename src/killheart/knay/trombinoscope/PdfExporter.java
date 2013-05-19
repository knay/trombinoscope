package killheart.knay.trombinoscope;
import javax.xml.parsers.*; 
import org.w3c.dom.*; 
import org.xml.sax.*; 
import javax.xml.transform.*; 
import javax.xml.transform.sax.*; 
import javax.xml.transform.dom.*; 
import javax.xml.transform.stream.*; 
import java.io.*; 
import java.util.*; 
import org.apache.fop.apps.Driver;

public class PdfExporter {
	public static void creerPDF(String xml, String xsl, String pdf) throws Exception{
		// création du résultat (pdf)
		Driver driver = new Driver();
		driver.setRenderer(Driver.RENDER_PDF);
		driver.setOutputStream(new java.io.FileOutputStream(pdf));
		Result resultat = new SAXResult(driver.getContentHandler());
		
		// récupération de la source xml
		Source source = new StreamSource(xml);
		
		// création du transformer en fonction du xsl
		Source style = new StreamSource(xsl);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer(style);
		
		// transformation
		transformer.transform(source, resultat);
	}
}
