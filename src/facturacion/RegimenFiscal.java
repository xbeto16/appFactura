package facturacion;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "RegimenFiscal")
//If you want you can define the order in which the fields are written
//Optional
@XmlType(propOrder = {"Regimen"})

public class RegimenFiscal {

	private String Regimen;

	public RegimenFiscal(){
	}
	
	public RegimenFiscal(String Regimen){
		this.Regimen = Regimen;
	}
	
	@XmlAttribute
	public String getRegimen() {
		return Regimen;
	}

	public void setRegimen(String Regimen) {
		this.Regimen = Regimen;
	}	
}