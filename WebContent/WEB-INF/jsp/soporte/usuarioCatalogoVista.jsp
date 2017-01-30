<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<html>
	<head>
		<script type="text/javascript" src="dwr/interface/usuarioServicio.js"></script>  
        <script type="text/javascript" src="dwr/interface/sucursalesServicio.js"></script>  
        <script type="text/javascript" src="dwr/interface/rolesServicio.js"></script>  
        <script type="text/javascript" src="js/soporte/usuarioCatalogo.js"></script>                    
	</head>
<body>
<div id="contenedorForma">
<form:form id="formaGenerica" name="formaGenerica" method="POST" commandName="usuario">
<fieldset class="ui-widget ui-widget-content ui-corner-all">		
<legend class="ui-widget ui-widget-header ui-corner-all">Usuarios</legend>
	
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td class="label">
			<label for="numero">Número: </label>
		</td>
		<td >
			<form:input id="usuarioID" name="usuarioID" path="usuarioID"  size="7" tabindex="1" />
		</td>
		<td class="separador"></td>
		<td class="label">
			<label for="nombre">Nombre:</label>
		</td>
		<td>
			<form:input id="nombre" name="nombre" path="nombre" size="25" tabindex="2" 
				onBlur=" ponerMayusculas(this)"/>
		</td>		
	</tr>
	<tr>
		<td class="label">
			<label for="apPaterno">Apellido Paterno: </label>
		</td>
		<td >
			<form:input id="apPaterno" name="apPaterno" path="apPaterno" size="25" tabindex="3" 
			onBlur=" ponerMayusculas(this)" />  
		</td>
		<td class="separador"></td>
		<td class="label" nowrap="nowrap">
			<label for="apMaterno">Apellido Materno:</label>
		</td>
		<td>
			<form:input id="apMaterno" name="apMaterno" path="apMaterno" size="25" tabindex="4"
			onBlur=" ponerMayusculas(this)"/>
		</td>		
	</tr>

	<tr>
		<td class="label">
			<label for="clave">Clave: </label>
		</td>
		<td >
			<form:input type="text" id="clave" name="clave" path="clave" tabindex="5" size="25"/>		
		</td>
		<td class="separador"></td>
		<td class="label">
			<label for="contrasenia">Contrase&ntilde;a:</label>
		</td>
		<td>
			<form:password id="contrasenia" name="contrasenia" path="contrasenia" size="25" tabindex="6"/>
		</td>		
	</tr>
	<tr>
		<td class="label">
			<label for="correo">Correo: </label>
		</td>
		<td >
			<form:input id="correo" name="correo" path="correo" size="40" tabindex="7" />
			
		</td>
		<td class="separador"></td>
		<td class="label">
			<label for="Sucursal">Sucursal Usuario:</label>
		</td>
		<td nowrap="nowrap">
			<form:input id="sucursalUsuario" name="sucursalUsuario" path="sucursalUsuario" size="7" tabindex="8" />
			<input id="nomSucursal" name="nomSucursal" size="30" tabindex="9" disabled="true" readOnly="true"/> 
		</td>		
	</tr>
	<tr>
		<td class="label">
			<label for="rol">Rol: </label>
		</td>
		<td nowrap="nowrap">
			<form:input id="rolID" name="rolID" path="rolID" size="7" tabindex="10" />
			<input id="nombreRol" name="nombreRol" size="35" tabindex="11" disabled="true" readOnly="true"/>    
		</td>
		<td class="separador"></td>
		<td class="label" nowrap="nowrap">
			<label for="fechUltAcces">Fecha de Último Acceso:</label>
		</td>
		<td>
			<form:input id="fechUltAcces" name="fechUltAcces" path="fechUltAcces" size="25" tabindex="14" 
			disabled="true" readOnly="true"/>
		</td>
	</tr>
	<tr>
		<td>
			<label for="fechUltPass">Direcci&oacute;n IP: </label>
		</td>
		<td >
			<form:input id="ipSesion" name="ipSesion" path="ipSesion" size="25" tabindex="13" />  
		</td>
		<td class="separador"></td>
		<td class="label" nowrap="nowrap">
			<label for="fechUltPass">Fecha del Último Password: </label>
		</td>
		<td >
			<form:input id="fechUltPass" name="fechUltPass" path="fechUltPass" size="25" tabindex="15" 
			disabled="true" readOnly="true"/>  
		</td>
	</tr>	
	<tr>
		<td class="label">
			<label for="estatus">Estatus:</label>
		</td>
		<td>
			<form:input id="estatus" name="estatus" path="estatus" size="25" tabindex="16" disabled="true" 
			readOnly="true" />
		</td>
		<td class="separador"></td>
		<td class="separador"></td>
		<td class="separador"></td>			
	</tr>
	 <tr>
		<td colspan="5" align="right">
			<input type="submit" id="agrega" name="agrega" class="submit" value="Agrega"/>
			<input type="submit" id="modifica" name="modifica" class="submit"  value="Modifica"/>
			<input type="hidden" id="tipoTransaccion" name="tipoTransaccion"/>
		</td>
	</tr> 
	
</table>
</fieldset>
</form:form>

</div>
<div id="cargando" style="display: none;">	
</div>
<div id="cajaLista" style="display: none;">
	<div id="elementoLista"></div>
</div>
</body>
<div id="mensaje" style="display: none;"></div>
</html>