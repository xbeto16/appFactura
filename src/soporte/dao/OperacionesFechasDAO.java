package soporte.dao;

import general.dao.BaseDAO;
import herramientas.Constantes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import soporte.bean.OperacionesFechasBean;

public class OperacionesFechasDAO extends BaseDAO {

	public OperacionesFechasDAO() {
		super();
	}
	
	// ------------------ Transacciones ------------------------------------------
	
	/* Suma el numero de Dias a la Fecha Especificada */
	public OperacionesFechasBean sumaDias(OperacionesFechasBean operacionesFechasBean,
										  int tipoCalculo) {
		//Query con el Store Procedure
		String query = "call FECHASCAL(?,?,?,?);";		
		Object[] parametros = {
			operacionesFechasBean.getPrimerFecha(),
		 	Constantes.FECHA_VACIA,
		 	operacionesFechasBean.getNumeroDias(),
		 	tipoCalculo
		};
		
		List matches=jdbcTemplate.query(query, parametros  ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					OperacionesFechasBean fechaBean = new OperacionesFechasBean();
					fechaBean.setFechaResultado(resultSet.getString(1));			
					fechaBean.setDiasEntreFechas(resultSet.getInt(2));					
					return fechaBean;
			}
		});
				
		return matches.size() > 0 ? (OperacionesFechasBean) matches.get(0) : null;
	}

	/* Obtiene el numero de Dias entre dos Fechas */
	public OperacionesFechasBean restaFechas(OperacionesFechasBean operacionesFechasBean,
										  	 int tipoCalculo) {
		//Query con el Store Procedure
		String query = "call FECHASCAL(?,?,?,?);";		
		Object[] parametros = {
			operacionesFechasBean.getPrimerFecha(),
			operacionesFechasBean.getSegundaFecha(),
		 	operacionesFechasBean.getNumeroDias(),
		 	tipoCalculo
		};
		
		List matches=jdbcTemplate.query(query, parametros  ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					OperacionesFechasBean fechaBean = new OperacionesFechasBean();
					fechaBean.setFechaResultado(resultSet.getString(1));			
					fechaBean.setDiasEntreFechas(resultSet.getInt(2));					
					return fechaBean;
			}
		});
				
		return matches.size() > 0 ? (OperacionesFechasBean) matches.get(0) : null;
	}
	
	
}
	