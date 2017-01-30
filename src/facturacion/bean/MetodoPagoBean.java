package facturacion.bean;

import general.bean.BaseBean;

public class MetodoPagoBean extends BaseBean{
	
	private String metodPagoID;
	private String descripcion;
	private String estatus;
	public String getMetodPagoID() {
		return metodPagoID;
	}
	public void setMetodPagoID(String metodPagoID) {
		this.metodPagoID = metodPagoID;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
}