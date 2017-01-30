package facturacion.servicio;

import java.util.List;
import facturacion.bean.FacturaBean;
import facturacion.dao.FacturaDAO;
import general.bean.MensajeTransaccionBean;
import general.servicio.BaseServicio;

public class FacturaServicio extends BaseServicio{

	FacturaDAO facturaDAO = null;
	public FacturaServicio() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static interface Enum_Tra_Factura {
		int alta = 1;
		int genera = 2;
		int cancela= 3;	
	}
	
	public static interface Enum_Lis_Factura {
		int principal = 1;
		int conceptos = 2;
		int lisReceptores = 3;
	}

	public static interface Enum_Con_Factura{
		int principal = 1;
		int foranea = 2;
		
	}
	public MensajeTransaccionBean grabaTransaccion(int tipoTransaccion, FacturaBean factura){
		MensajeTransaccionBean mensaje = null;
		switch (tipoTransaccion) {
			case Enum_Tra_Factura.alta:		
				mensaje = altaCertificado(factura);				
				break;				
			case Enum_Tra_Factura.genera:
				mensaje = generaFactura(factura);				
				break;
			case Enum_Tra_Factura.cancela:
				mensaje = cancelaFactura(factura);				
				break;
		}
		return mensaje;
	}
	
	
	
	public MensajeTransaccionBean altaCertificado(FacturaBean factura){
		MensajeTransaccionBean mensaje = null;
		//mensaje = facturaDAO.altaCliente(factura);		
		return mensaje;
	}
	
	public MensajeTransaccionBean generaFactura(FacturaBean factura){
		MensajeTransaccionBean mensaje = null;
		//mensaje = facturaDAO.altaCliente(factura);		
		return mensaje;
	}
	public MensajeTransaccionBean cancelaFactura(FacturaBean factura){
		MensajeTransaccionBean mensaje = null;
		//mensaje = facturaDAO.altaCliente(factura);		
		return mensaje;
	}
	
	
	public List lista(int tipoLista, FacturaBean facturaBean){
		List conceptosFactLista = null;

		switch (tipoLista) {
			case Enum_Lis_Factura.conceptos:
				conceptosFactLista = facturaDAO.listaConceptos(facturaBean, tipoLista);
			break;
		}
		return conceptosFactLista;
	}	
	
	public FacturaDAO getFacturaDAO() {
		return facturaDAO;
	}

	public void setFacturaDAO(FacturaDAO facturaDAO) {
		this.facturaDAO = facturaDAO;
	}	
}