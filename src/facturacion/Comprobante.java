package facturacion;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//This statement means that class "Comprobante.java" is the root-element of our example
@XmlRootElement(namespace = "http://www.sat.gob.mx/cfd/3", name="Comprobante")
public class Comprobante {

	private Emisor emisor;
	private String version;
	private String serie;
	private String folio;
	private String fecha;
	private String tipoDeComprobante;
	private String formaDePago;
	private String condicionesDePago;
	private String metodoDePago;
	private String noCertificado;
	private String subTotal;
	private String descuento;
	private String motivoDescuento;
	private String TipoCambio;
	private String Moneda;
	private String total;
	private String LugarExpedicion;
	private String sello;

	public Emisor getEmisor() {
		return emisor;
	}
	public void setEmisor(Emisor emisor) {
		this.emisor = emisor;
	}	

	@XmlAttribute
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@XmlAttribute
	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	@XmlAttribute
	public String getFolio() {
		return folio;
	}
	
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	@XmlAttribute
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	@XmlAttribute
	public String getTipoDeComprobante() {
		return tipoDeComprobante;
	}
	
	public void setTipoDeComprobante(String tipoDeComprobante) {
		this.tipoDeComprobante = tipoDeComprobante;
	}
	
	@XmlAttribute
	public String getFormaDePago() {
		return formaDePago;
	}
	
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	@XmlAttribute
	public String getCondicionesDePago() {
		return condicionesDePago;
	}

	public void setCondicionesDePago(String condicionesDePago) {
		this.condicionesDePago = condicionesDePago;
	}
	
	@XmlAttribute
	public String getMetodoDePago() {
		return metodoDePago;
	}
	
	public void setMetodoDePago(String metodoDePago) {
		this.metodoDePago = metodoDePago;
	}
	@XmlAttribute
	public String getNoCertificado() {
		return noCertificado;
	}

	public void setNoCertificado(String noCertificado) {
		this.noCertificado = noCertificado;
	}
	@XmlAttribute
	public String getSubTotal() {
		return subTotal;
	}
	
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	@XmlAttribute
	public String getDescuento() {
		return descuento;
	}
	
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	@XmlAttribute
	public String getMotivoDescuento() {
		return motivoDescuento;
	}
	
	public void setMotivoDescuento(String motivoDescuento) {
		this.motivoDescuento = motivoDescuento;
		}
	@XmlAttribute
	public String getTipoCambio() {
		return TipoCambio;
	}
	
	public void setTipoCambio(String TipoCambio) {
		this.TipoCambio = TipoCambio;
	}
	@XmlAttribute
	public String getMoneda() {
		return Moneda;
	}
	
	public void setMoneda(String Moneda) {
		this.Moneda = Moneda;
	}
	@XmlAttribute
	public String getTotal() {
		return total;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}
	
	@XmlAttribute
	public String getLugarExpedicion() {
		return LugarExpedicion;
	}
	
	public void setLugarExpedicion(String LugarExpedicion) {
		this.LugarExpedicion = LugarExpedicion;
	}
	@XmlAttribute
	public String getSello() {
		return sello;
	}
	
	public void setSello(String sello) {
		this.sello = sello;
	} 	
}
