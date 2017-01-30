package seguridad.dao;

import herramientas.Utileria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import seguridad.bean.SeguridadUsuarioBean;
import soporte.bean.UsuarioBean;
import soporte.dao.UsuarioDAO;


public class AutenticacionDAO extends JdbcDaoImpl {
	
	private UsuarioDAO usuarioDAO = null;
	private PasswordEncoder passwordEncoder;
	private SaltSource saltSource;
	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		UsuarioBean usuarioBean = new UsuarioBean();
		UsuarioBean usuarioCon = null;
		SeguridadUsuarioBean userDetails = null;
		boolean activo = false;		
		
		usuarioBean.setClave(username);
		//TODO: usuarioCon = usuarioDAO.consultaPorClave(usuarioBean, UsuarioServicio.Enum_Con_Usuario.clave);
		usuarioCon = usuarioDAO.consultaPorClave(usuarioBean, 3);
		if(usuarioCon==null){			
			throw new UsernameNotFoundException("El Usuario " + username  + " no Existe");
		}else{
			List<GrantedAuthority> listaRoles = new ArrayList<GrantedAuthority>();			
			listaRoles.add(new GrantedAuthorityImpl(usuarioCon.getNombreRol()));			
			
			Utileria.escribelog("AutenticacionDAO. ROL.USUARIO. " + usuarioCon.getNombreRol());
			if(usuarioCon.getEstatus().equalsIgnoreCase(UsuarioBean.STATUS_ACTIVO) ){
				activo = true;
			}
			userDetails = new SeguridadUsuarioBean(usuarioCon.getClave(), usuarioCon.getContrasenia(), activo,
												   true, true, true, listaRoles, usuarioCon.getSalt());
			
			Utileria.escribelog(
					"AutenticacionDAO. PassSinEncriptar: " + 
					usuarioCon.getContrasenia() + " . Salt: " +
					saltSource.getSalt(userDetails));
			Utileria.escribelog(
					"AutenticacionDAO. PassEncriptado: " + 
					passwordEncoder.encodePassword(usuarioCon.getContrasenia(), saltSource.getSalt(userDetails)));			

		}		 
		return userDetails;		
	}
	
	
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}


	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}


	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	
	
}
