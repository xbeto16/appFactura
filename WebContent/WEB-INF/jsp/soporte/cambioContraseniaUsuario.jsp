<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<html>
	<head>
	
        <script type="text/javascript" src="dwr/interface/usuarioServicio.js"></script>  
         <script type="text/javascript" src="js/soporte/cambioPassUsuario.js"></script>                 
       
       
       
       <link rel="stylesheet" type="text/css" href="css/template.css" media="screen,print"  />
	<link rel="stylesheet" type="text/css" href="css/menuTree.css" media="screen,print"  />
	<link rel="stylesheet" type="text/css" href="css/redmond/jquery-ui-1.8.13.custom.css"  />

 	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
 	<script type="text/javascript" src="js/jquery.ui.datepicker-es.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.13.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type='text/javascript' src='js/jquery.formatCurrency-1.4.0.min.js'></script>
 	
	<script type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	 
	<link rel="stylesheet" type="text/css" href="css/forma.css" media="all"  >
	
	
		
	<script type='text/javascript' src='js/jquery.hoverIntent.minified.js'></script>
	<script type="text/javascript" src="js/jquery.plugin.menuTree.js"></script>
	<script type="text/javascript" src="js/jquery.plugin.tracer.js"></script>
				
 	<script type="text/javascript" src="dwr/engine.js"></script>
 	<script type="text/javascript" src="dwr/util.js"></script>
 	<script type="text/javascript" src="dwr/interface/usuarioServicio.js"></script>
 	<script type="text/javascript" src="js/forma.js"></script>
 	<script type="text/javascript" src="js/general.js"></script>
 		
	<script type="text/javascript" src="js/soporte/menuAplicacion.js"></script>

		
	</head>
<body>
<div id="contenedorForma">
<form:form id="formaGenerica" name="formaGenerica" method="POST" commandName="cambioContraUsuario">
<fieldset class="ui-widget ui-widget-content ui-corner-all">		
<legend class="ui-widget ui-widget-header ui-corner-all">Cambio de Contrase&ntilde;a de Usuarios</legend>
	
<table cellpadding="0" cellspacing="0" border="0" width="950px">

	<tr>
		<td class="label">
			<label for="usuarioID">NÚmero: </label>
		</td>
		<td >
			<form:input id="usuarioID" name="usuarioID" path="usuarioID"  size="7" tabindex="1"/> 
		 
		</td>
		<td class="separador"></td>
		<td class="label">
			<label for="nombre">Nombre:</label>
		</td>
		<td>
			<form:input id="nombreCompleto" name="nombreCompleto" path="nombreCompleto" size="40" tabindex="2" 
			onBlur=" ponerMayusculas(this)" readOnly="true"/>
		</td>		
	</tr>

	<tr>
		<td class="label">
			<label for="clave">Clave: </label>
		</td>
		<td >
			<form:input value1='<%= request.getParameter("clave") %>' id="clave" name="clave" path="clave" tabindex="5" readOnly="true" />		
		</td>
		<td class="separador"></td>
			<td class="label">
			<label for="contrasenia">Contrase&ntilde;a Actual:</label>
		</td>
		<td>
			<form:password id="contrasenia" name="contrasenia" path="contrasenia" size="20" tabindex="6" />
		</td>	
	</tr>
	

	<tr>
		<td class="label">
			<label for="contrasenia">Nueva Contrase&ntilde;a:</label>
		</td>
		<td>
			<input type="password" id="nuevaContra" name="nuevaContra" size="20" tabindex="6" />
		</td>	
		<td class="separador"></td>	
		<td class="label">
			<label for="contrasenia">Confirma Contrase&ntilde;a:</label>
		</td>
		<td>
			<input type="password" id="Confirmacontra" name="Confirmacontra"  size="20" tabindex="6" />
		</td>	
	</tr>

</table>
	 <table align="right">
				<tr>
					<td align="right">
						<input type="submit" id="actualizar" name="actualizar" class="submit" value="Actualizar"/>
						<input type="button" id="Continuar" name="Continuar" class="submit" value="Continuar"/>
						<input type="hidden" id="tipoTransaccion" name="tipoTransaccion"/>
					</td>
				</tr>
		</table>	
			<br>	
		<table id="reglas de pass">
		 <tr>
			<td class="label">
			<DIV class="label"><label> 
			Reglas para definir password:
			<br>
			1. Longitud mínima de 6 caracteres.
			<br>
			2. Debe contener al menos: 1 Caracter Alfabético May&uacute;scula, 1 Caracter Alfabético Min&uacute;scula,1 N&uacute;mero o Caracter Especial.
			<br>
			3. No debe ser igual a ningun password anterior.
				</label>
			</DIV>
			</td>
		</tr>
		</table>
</fieldset>
</form:form>

  
</div>
<div id="cargando" style="display: none;">	
</div>
<div id="cajaLista" style="display: none;">
	<div id="elementoLista"/>
</div>
<c:set var="varUs" value="<%= request.getParameter(\"user\") %>"/>
<c:if test="${varUs!=''}"> 
		<script type="text/javascript"> 
		
		var val1 = "<%= request.getParameter("user") %>"; 
		document.getElementById('usuarioID').value = val1;
		</script> 
		</c:if> 
	<c:if test="${varUs==null}"> 
		<script type="text/javascript"> 
		$('#Continuar').hide();
		document.getElementById('usuarioID').value = '';
		</script>
	</c:if>  
</body>
<div id="mensaje" style="display: none;"/>
</html>