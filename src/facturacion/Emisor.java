package facturacion;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "emisor")
// If you want you can define the order in which the fields are written
// Optional
@XmlType(propOrder = {"rfc", "nombre", "domicilioFiscal" })
//, "expedidoEn", "regimenFiscal"})

public class Emisor {

	    private String rfc;
	    private String nombre;
	    private DomicilioFiscal domicilioFiscal;
	    //private RegimenFiscal RegimenFiscal;
	    //private ExpedidoEn expedidoEn;
	 
	    public Emisor() {
	    }
	 
	    public Emisor(String rfc, String nombre) {
	        this.rfc = rfc;
	        this.nombre = nombre;
	    }
	 
	    @XmlAttribute
	    public String getRfc() {
	        return rfc;
	    }
	 
	    public void setRfc(String rfc) {
	        this.rfc = rfc;
	    }
	 
	    @XmlAttribute
	    public String getNombre() {
	        return nombre;
	    }
	 
	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
	    @XmlElement(name = "DomicilioFiscal")
	    public DomicilioFiscal getDomicilioFiscal() {
	        return domicilioFiscal;
	    }
	 
	    public void setDomicilioFiscal(DomicilioFiscal domicilioFiscal) {
	        this.domicilioFiscal = domicilioFiscal;
	    }
	    /*@XmlElement(name = "RegimenFiscal")
	    public RegimenFiscal getRegimenFiscal() {
	        return RegimenFiscal;
	    }
	 
	    public void setRegimenFiscal(RegimenFiscal RegimenFiscal) {
	        this.RegimenFiscal = RegimenFiscal;
	    }
	    @XmlElement(name = "ExpedidoEn")
	    public ExpedidoEn getExpedidoEn() {
	        return expedidoEn;
	    }
	 
	    public void setExpedidoEn(ExpedidoEn expedidoEn) {
	        this.expedidoEn = expedidoEn;
	    } */  
	
}
