package soporte.dao;

import general.dao.BaseDAO;
import herramientas.Constantes;
import herramientas.Utileria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import soporte.bean.MunicipiosRepubBean;



public class MunicipiosRepubDAO extends BaseDAO{
	
		
		public MunicipiosRepubDAO() {
			super();
		}
		
		// ------------------ Transacciones ------------------------------------------
		
		//consulta Principal de Municipios
			public MunicipiosRepubBean consultaPrincipal(int estadoID, int municipioID, int tipoConsulta) {
			//Query con el Store Procedure
			String query = "call MUNICIPIOSREPUBCON(?,?,?,?,?,?,?,?,?,?);";								 
			Object[] parametros = {	estadoID,
									municipioID,
									tipoConsulta,
									Constantes.ENTERO_CERO,
									Constantes.ENTERO_CERO,
									Constantes.FECHA_VACIA,
									Constantes.STRING_VACIO,
									"MunicipiosRepubDAO.consultaPrincipal",
									Constantes.ENTERO_CERO,
									Constantes.ENTERO_CERO};
			
					
			List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
				public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					MunicipiosRepubBean municipios = new MunicipiosRepubBean();
						
					municipios.setEstadoID(Utileria.completaCerosIzquierda(resultSet.getInt(1), 5));
					municipios.setMunicipioID(Utileria.completaCerosIzquierda(resultSet.getInt(2), 5));
					municipios.setEmpresaID(Utileria.completaCerosIzquierda(resultSet.getInt(3),10));
					municipios.setNombre(resultSet.getString(4));					

						return municipios;
		
				}
			});
			return matches.size() > 0 ? (MunicipiosRepubBean) matches.get(0) : null;
					
		}
		
			//consulta Foranea de Municipios

			public MunicipiosRepubBean consultaForanea(int estadoID, int municipioID, int tipoConsulta) {
				//Query con el Store Procedure
				String query = "call  MUNICIPIOSREPUBCON(?,?,?,?,?,?,?,?,?,?);";
				Object[] parametros = {	estadoID,
										municipioID,
										tipoConsulta,
										Constantes.ENTERO_CERO,
										Constantes.ENTERO_CERO,
										Constantes.FECHA_VACIA,
										Constantes.STRING_VACIO,
										"MunicipiosRepubDAO.consultaPrincipal",
										Constantes.ENTERO_CERO,
										Constantes.ENTERO_CERO};
				
						
				List matches=jdbcTemplate.query(query, parametros, new RowMapper() {
					public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
						MunicipiosRepubBean municipios = new MunicipiosRepubBean();			
							
						municipios.setMunicipioID(String.valueOf(resultSet.getInt(1)));
						municipios.setNombre(resultSet.getString(2));				
								
							return municipios;
			
					}
				});
				return matches.size() > 0 ? (MunicipiosRepubBean) matches.get(0) : null;
						
			}
			
		
		//Lista de Municipios
		public List listaMunicipios(MunicipiosRepubBean municipios, int tipoLista) {
			//Query con el Store Procedure
			String query = "call MUNICIPIOSREPUBLIS(?,?,?,?,?,?,?,?,?,?);";
			Object[] parametros = {	municipios.getEstadoID(),
									municipios.getNombre(),
									tipoLista,
									parametrosAuditoriaBean.getEmpresaID(),
									parametrosAuditoriaBean.getUsuario(),
									parametrosAuditoriaBean.getFecha(),
									parametrosAuditoriaBean.getDireccionIP(),
									"MunicipiosRepubDAO.listaMunicipios",
									parametrosAuditoriaBean.getSucursal(),
									Constantes.ENTERO_CERO};
			
			
			List matches=jdbcTemplate.query(query, parametros ,new RowMapper() {
				public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					MunicipiosRepubBean municipios = new MunicipiosRepubBean();		
					municipios.setMunicipioID(Utileria.completaCerosIzquierda(resultSet.getInt(1), 5));
					municipios.setNombre(resultSet.getString(2));					
					return municipios;				
				}
			});
					
			return matches;
		}
		
	
}
