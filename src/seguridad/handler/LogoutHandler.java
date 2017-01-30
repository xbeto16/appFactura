package seguridad.handler;

import herramientas.Utileria;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


public class LogoutHandler extends SecurityContextLogoutHandler {
	
	public void logout(HttpServletRequest request,
 		   HttpServletResponse response,
 		   Authentication authentication){
		
		Cookie cookieSesion = new Cookie("JSESSIONID", null);
		cookieSesion.setPath(request.getContextPath());
		cookieSesion.setMaxAge(0);
		response.addCookie(cookieSesion);
		
		Utileria.escribelog("LogoutHandler. Credenciales: " + authentication.getCredentials() + 
							" .Name: " + authentication.getName() + " .Principal: "
							+ authentication.getPrincipal());
		
		super.logout(request, response, authentication);
	}
	
}
