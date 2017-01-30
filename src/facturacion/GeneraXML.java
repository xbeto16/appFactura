package facturacion;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.events.Namespace;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class GeneraXML {

	
	
	
	private static final String COMPROBANTE_XML = "./sapNomina.xml";

	private static DocumentBuilderFactory dbf = DocumentBuilderFactory
            .newInstance();
	 
	public void testXmlDocumentWithNamespaces() throws Exception {
        DocumentBuilder db = dbf.newDocumentBuilder();
        DOMImplementation domImpl = db.getDOMImplementation();
        Document document = buildExampleDocumentWithNamespaces(domImpl);
        serialize(domImpl, document);
    }

    private static Document buildExampleDocumentWithNamespaces(
            DOMImplementation domImpl) {
        Document document = domImpl.createDocument("http://www.sat.gob.mx/cfd/3",
                "cfdi:Comprobante", null);
        Element element = document.createElementNS("http://another.namespace",
                "element");
        document.getDocumentElement().appendChild(element);
        return document;
    }

    private void serialize(DOMImplementation domImpl, Document document) {
        DOMImplementationLS ls = (DOMImplementationLS) domImpl;
        LSSerializer lss = ls.createLSSerializer();
        LSOutput lso = ls.createLSOutput();
        lso.setByteStream(System.out);
        lss.write(document, lso);
    }
    public static void main(String[] args) throws Exception {
 
    	//Document doc = new Document();
    	GeneraXML domTest = new GeneraXML();
         domTest.testXmlDocumentWithNamespaces();
    	
/*    	Namespace sNS = Namespace.getNamespace("someNS", "someNamespace");
    	Element element = new Element("SomeElement", sNS);
    	element.setAttribute("someKey", "someValue", Namespace.getNamespace("someONS", "someOtherNamespace"));
    	Element element2 = new Element("SomeElement", Namespace.getNamespace("someNS", "someNamespace"));
    	element2.setAttribute("someKey", "someValue", sNS);
    	element.addContent(element2);
    	doc.addContent(element);*/

    	
    	
    	/*private static DocumentBuilderFactory dbf = DocumentBuilderFactory
                .newInstance();
*/
        /*public static void main(String[] args) throws Exception {
           DomTest domTest = new DomTest();
            domTest.testXmlDocumentWithNamespaces();
        }*/

        
    	
    	
        /*Comprobante comprobante = new Comprobante();
        comprobante.setVersion("3.2");
        comprobante.setSerie("HDS");
        comprobante.setFolio("3");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        comprobante.setFecha(sdf.format(new Date()));
        comprobante.setTipoDeComprobante("egreso");
        comprobante.setFormaDePago("PAGO EN UNA SOLA EXHIBICIÓN");
        comprobante.setCondicionesDePago("CONTADO");
        comprobante.setMetodoDePago("Efectivo");
        comprobante.setNoCertificado("20001000000200001745");
        comprobante.setSubTotal("4716.08");
        comprobante.setDescuento("864.21");
        comprobante.setMotivoDescuento("Deducciones nómina");
        comprobante.setTipoCambio("1.00");
        comprobante.setMoneda("MXP");
        comprobante.setTotal("3506.00");
        comprobante.setLugarExpedicion("MONTEMORELOS, NUEVO LEÓN");
         
        Emisor emisor = new Emisor("UMO8409105C1", "UNIVERSIDAD DE MONTEMORELOS, A.C.");
        DomicilioFiscal domicilio = new DomicilioFiscal("Libertad Pte.", "1300", "Barrio Matamoros", "Montemorelos", "MUN. EMISOR", "MEXICO", "MEXICO", "53125");
        emisor.setDomicilioFiscal(domicilio);
//        RegimenFiscal regimen = new RegimenFiscal("PERSONAS MORALES DEL REGIMEN FISCAL");
//        emisor.setRegimenFiscal(regimen);
        //emisor.setExpedidoEn(new ExpedidoEn());
        comprobante.setEmisor(emisor);
         
        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(Comprobante.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FRAGMENT, "cfdi");
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv32.xsd");
         
        // Write to System.out
        m.marshal(comprobante, System.out);
 
        // Write to File
        m.marshal(comprobante, new File(COMPROBANTE_XML));*/

    }

}
