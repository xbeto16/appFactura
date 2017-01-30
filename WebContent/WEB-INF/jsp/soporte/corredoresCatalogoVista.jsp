<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<html>
	<head>
		<script type="text/javascript" src="dwr/interface/corredorServicio.js"></script>  
        <script type="text/javascript" src="js/soporte/corredorCatalogo.js"></script>                    
	</head>
<body>
<div id="contenedorForma">
<form:form id="formaGenerica" name="formaGenerica" method="POST" commandName="corredorBean">
<fieldset class="ui-widget ui-widget-content ui-corner-all">		
<legend class="ui-widget ui-widget-header ui-corner-all">Corredores</legend>
	
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td class="label">
			<label for="numero">Número: </label>
		</td>
		<td>


			<input id="numeroCorredor" name="numeroCorredor"  size="10" tabindex="1" />
		</td>
		<td class="separador"></td>
		<td class="label">
			<label for="nombre">Nombre:</label>
		</td>
		<td>
			<input id="nombre" name="nombre" size="25" tabindex="2" onBlur=" ponerMayusculas(this)"/>
		</td>		
	</tr>
	<tr>
		<td class="label">
			<label for="apPaterno">Apellido Paterno: </label>
		</td>
		<td >
			<input id="apPaterno" name="apPaterno" size="25" tabindex="3" 
			onBlur=" ponerMayusculas(this)" />  
		</td>
		<td class="separador"></td>
		<td class="label" nowrap="nowrap">
			<label for="apMaterno">Apellido Materno:</label>
		</td>
		<td>
			<input id="apMaterno" name="apMaterno" size="25" tabindex="4" onBlur=" ponerMayusculas(this)"/>
		</td>		
	</tr>
	<tr>
		<td class="label">
			<label for="clave">Calle: </label>
		</td>
		<td>
			<input type="text" id="calle" name="calle" tabindex="5" size="30"/>		
		</td>
	<td class="separador"></td>
	<td class="label">
			<label for="clave">Num Casa: </label>
		</td>
		<td>
			<input type="text" id="numCasa" name="numCasa" tabindex="5" size="10"/>		
		</td>
</tr>
<tr>
	<td class="label">
			<label for="clave">Colonia: </label>
		</td>
		<td>
			<input type="text" id="colonia" name="colonia" tabindex="5" size="30"/>		
		</td>
	<td class="separador"></td>
	<td class="label">
			<label for="clave">Municipio: </label>
		</td>
		<td>
			<input type="text" id="municipio" name="municipio" tabindex="5" size="25"/>		
		</td>
</tr>
<tr>
	<td class="label">
			<label for="clave">Ciudad: </label>
		</td>
	<td>
			<input type="text" id="ciudad" name="ciudad" tabindex="5" size="30"/>		
	</td>
		<td class="separador"></td>
		<td class="label">
			<label for="Sucursal">Telefono:</label>
		</td>
		<td nowrap="nowrap">
			<input id="telefono" name="telefono" size="25" tabindex="8" /> 
		</td>		
</tr>
	<tr>
		<td class="label">
			<label for="correo">Correo: </label>
		</td>
		<td >
			<input id="correo" name="correo" size="40" tabindex="7" />
			
		</td>
		<td class="separador"></td>
		<td class="label">
			<label for="Sucursal">Fecha Nacimiento</label>
		</td>
		<td nowrap="nowrap">
			<input id="fechaNac" name="fechaNac" size="12" tabindex="8" /> 
		</td>		
	</tr>
<tr>
		<td class="label">
			<label for="rol">Club Pertenece: </label>
		</td>
		<td nowrap="nowrap">
		<input id="clubPertenece" name="clubPertenece" size="40" tabindex="10" />    
		</td>
	</tr>
		

	 <tr>
		<td colspan="5" align="right">
			<input type="submit" id="agrega" name="agrega" class="submit" value="Guardar"/>
			<input type="submit" id="modifica" name="modifica" class="submit"  value="Modificar"/>
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