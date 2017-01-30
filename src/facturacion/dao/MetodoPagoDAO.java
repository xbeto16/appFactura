package facturacion.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import facturacion.bean.MetodoPagoBean;
import general.dao.BaseDAO;
import herramientas.Constantes;

public class MetodoPagoDAO extends BaseDAO{

	public MetodoPagoDAO(){
		super();
	}
	
	public List listaCombo(int tipoLista) {
		//Query con el Store Procedure
		System.out.println("Entra a DAO -> " + tipoLista);
		String query = "call METODOPAGOLIS(?,?);";
		Object[] parametros = {	Constantes.ENTERO_CERO,
								tipoLista
							};		
		List matches=jdbcTemplate.query(query, parametros ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				MetodoPagoBean pago= new MetodoPagoBean();
				pago.setMetodPagoID(String.valueOf(resultSet.getInt(1)));
				pago.setDescripcion(resultSet.getString(2));
				return pago;
			}
		});
		return matches;
	}
	
}
