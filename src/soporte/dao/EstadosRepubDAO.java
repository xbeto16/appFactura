package soporte.dao;

import general.dao.BaseDAO;
import herramientas.Constantes;
import herramientas.Utileria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import soporte.bean.EstadosRepubBean;


public class EstadosRepubDAO extends BaseDAO{


	
	public EstadosRepubDAO() {
		super();
	}
	
	// ------------------ Transacciones ------------------------------------------
	
	//consulta de Estados
		public EstadosRepubBean consulta(int estadoID, int tipoConsulta) {
		//Query con el Store Procedure
		String query = "call ESTADOSREPUBCON(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	estadoID,
								tipoConsulta,
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO,
								Constantes.FECHA_VACIA,
								Constantes.STRING_VACIO,
								"EstadosRepubDAO.consulta",
								Constantes.ENTERO_CERO,
								Constantes.ENTERO_CERO};
		
				
		List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				EstadosRepubBean estados = new EstadosRepubBean();
					
				estados.setEstadoID(Utileria.completaCerosIzquierda(resultSet.getLong(1), 5));
				estados.setEmpresaID(Utileria.completaCerosIzquierda(resultSet.getLong(2),10));
				estados.setNombre(resultSet.getString(3));					

					return estados;
	
			}
		});
		return matches.size() > 0 ? (EstadosRepubBean) matches.get(0) : null;
				
	}
	
	

		
	
	//Lista de Estados
	public List listaEstados(EstadosRepubBean estados, int tipoLista) {
		//Query con el Store Procedure
		String query = "call ESTADOSREPUBLIS(?,?,?,?,?,?,?,?,?);";
		Object[] parametros = {	estados.getNombre(),
								tipoLista,
								parametrosAuditoriaBean.getEmpresaID(),
								parametrosAuditoriaBean.getUsuario(),
								parametrosAuditoriaBean.getFecha(),
								parametrosAuditoriaBean.getDireccionIP(),
								"EstadosRepubDAO.listaEstados",
								parametrosAuditoriaBean.getSucursal(),
								Constantes.ENTERO_CERO};
		
		
		List matches=jdbcTemplate.query(query, parametros ,new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				EstadosRepubBean estados = new EstadosRepubBean();		
				estados.setEstadoID(String.valueOf(resultSet.getLong(1)));
				estados.setNombre(resultSet.getString(2));					
				return estados;				
			}
		});
				
		return matches;
	}
	
	

}