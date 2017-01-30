<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<html>
	<head>
		<script type="text/javascript" src="dwr/interface/estadosServicio.js"></script>
		<script type="text/javascript" src="dwr/interface/municipiosServicio.js"></script>
		<script type="text/javascript" src="dwr/interface/usuarioServicio.js"></script>     	          
		<script type="text/javascript" src="dwr/interface/sucursalesServicio.js"></script>
        <script type="text/javascript" src="js/soporte/sucursalesCatalogo.js"></script>   
		
	</head>
<body>
	<div id="contenedorForma">
	<fieldset class="ui-widget ui-widget-content ui-corner-all">		
	<legend class="ui-widget ui-widget-header ui-corner-all">Sucursales</legend>	
	<form:form id="formaGenerica" name="formaGenerica" method="POST" commandName="sucursal">
					
	<table cellpadding="0" cellspacing="0" border="0" width="950px">
		<tr>
			<td class="label">
				<label for="numero">Número: </label>
			</td>
			<td >
				<form:input id="sucursalID" name="sucursalID" path="sucursalID" size="15" tabindex="1" size="8" /> 
			</td>
			<td class="separador"></td>
			<td class="label">
				<label for="nombre">Nombre:</label>
			</td>
			<td>
				<form:input id="nombreSucurs" name="nombreSucurs" path="nombreSucurs" tabindex="2" size="25"/>
			</td>		
		</tr>

		<tr>
			<td class="label">
				<label for="tipoSucursal">Tipo de Sucursal</label>
			</td>
			<td >
					<form:select id="tipoSucursal" name="tipoSucursal" path="tipoSucursal" tabindex="3" >
					<form:option value="">Seleccionar</form:option> 
					<form:option value="C">Corporativa</form:option>
				 	<form:option value="A">Atenci&oacute;n a clientes</form:option>
					</form:select>
			</td>
			<td class="separador"></td>
			<td class="label">
				<label for="iva">IVA: </label>
			</td>
			<td >
				<form:input id="IVA" name="IVA" path="IVA" size="7" tabindex="8" />
			</td>						
		</tr>		
		<tr>
			<td class="label">
				<label for="isr">Tasa ISR:</label>
			</td>
			<td>
				<form:input id="tasaISR" name="tasaISR" path="tasaISR" tabindex="9" size="7"/>
			</td>	
			<td class="separador"></td>
			<td class="label">
				<label for="nombreGerente">Gerente: </label>
			</td>
			<td >
				<form:input id="nombreGerente" name="IVAnombreGerente" path="nombreGerente" size="7" tabindex="10" />
				<input id="nombGerente" name="nombGerente" tabindex="11" size="40" disabled="true" readOnly="true"/>
			</td>	
		</tr>
		<tr>
			<td class="label">
				<label for="subgerente">SubGerente:</label>
			</td>
			<td>
				<form:input id="subGerente" name="subGerente" path="subGerente" tabindex="12" size="7"/>
			<input id="nomSubGerente" name="nomSubGerente" tabindex="13" size="40" disabled="true" readOnly="true"/>
			</td>	
			<td class="separador"></td>
			<td class="label">
				<label for="entidadFederativa">Entidad Federativa</label>
			</td>
			<td>
				<form:input id="estadoID" name="estadoID" path="estadoID"size="7" tabindex="14" />
	         <input type="text" id="nombreEstado" name="nombreEstado" size="35" tabindex="15" disabled="true" 
	          readOnly="true"/>   
			</td>	
		</tr>
		<tr>
		
			<td class="label"> 
	         <label for="municipio">Municipio: </label> 
	     </td> 
	     <td> 
	         <form:input id="municipioID" name="municipioID" path="municipioID" size="7" tabindex="16" /> 
	         <input type="text" id="nombreMuni" name="nombreMuni" size="35" tabindex="17" disabled="true"
	          readOnly="true"/>   
	     </td> 
			<td class="separador"></td>
			<td class="label"> 
	    	 <label for="calle">Calle: </label> 
	     </td> 
	     <td> 
	         <form:input id="calle" name="calle" path="calle" size="50" tabindex="18" /> 
	     </td> 
		</tr>
		<tr> 
		   <td class="label"> 
	         <label for="numero">Número: </label> 
	     </td> 
	     <td> 
	         <form:input id="numero" name="numero" path="numero" size="5" tabindex="19" />
	     </td> 
	          <td class="separador"></td> 
	       <td class="label"> 
	         <label for="colonia">Colonia: </label> 
	     </td> 
	     <td> 
	         <form:input id="colonia" name="colonia" path="colonia" size="45" tabindex="20" /> 
	     </td>  
		</tr> 
		<tr> 
			<td class="label"> 
	       	<label for="CP">C.P.: </label> 
	   	</td> 
	  	 <td> 
	       <form:input id="CP" name="CP" path="CP" size="15" tabindex="21" /> 
	   	</td>  
			<td class="separador"></td> 
			<td class="label">
				<label for="telefono">Tel&eacute;fono:</label>
			</td>
			<td>
				<form:input id="telefono" name="telefono" path="telefono" tabindex="22" size="20"/>
			</td>
			</tr>
			
			
		</tr> 
		<tr>
			<td class="label"> 
	       	<label for="difhorMat">Diferencia Horaria Matriz: </label> 
	   	</td> 
	  	 <td> 
	       <form:input id="difHorarMatriz" name="difHorarMatriz" path="difHorarMatriz" size="15" tabindex="23" /> 
	   	</td> 
			<td class="separador"></td>  
			<td class="label">
			<label>			Fecha Apertura:</label>
			</td>
			<td>
				<form:input name="fechaSucursal" id="fechaSucursal" path="fechaSucursal"  
				 tabindex="24" iniforma="false" readOnly="true"/> 
		 	</td>
		</tr>
 	<table>
	  <tr>
			<td>
			
			<form:textarea id="direcCompleta" name="direcCompleta" path="direcCompleta" COLS="60" ROWS="3" tabindex="26" 
				readOnly="true"/> 
			</td> 
	 </tr> 
	 </table>
		<tr>
			<td colspan="5">
				<table align="right">
					<tr>
						<td align="right">
							<input type="submit" id="agrega" name="agrega" class="submit" value="Agrega"/>
							<input type="submit" id="modifica" name="modifica" class="submit"  value="Modifica"/>
							<input type="hidden" id="tipoTransaccion" name="tipoTransaccion"/>
						</td>
					</tr>
				</table>		
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
</body>
<div id="mensaje" style="display: none;"/>
</html>