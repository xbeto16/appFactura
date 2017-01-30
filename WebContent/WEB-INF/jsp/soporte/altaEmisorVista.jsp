<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<html>
	<head>
		<script type="text/javascript" src="dwr/interface/emisorServicio.js"></script>
      	<script type="text/javascript" src="js/soporte/altaEmisor.js"></script>
	</head>
	<body>
		<div id="contenedorForma">
			<form:form id="formaGenerica" name="formaGenerica" method="POST" commandName="emisorBean">
				<fieldset class="ui-widget ui-widget-content ui-corner-all">
					<legend class="ui-widget ui-widget-content ui-widget-header ui-corner-all">Alta Emisor</legend>
					<table border="0" cellpadding="0" cellspacing="5" width="100%">
						<tr>
							<td class="label">
								<label for="lblCliente">Emisor: </label>
							</td>
							<td>
								<form:input type="text" id="emisorID" name="emisorID" path="emisorID" size="5" onkeypress="" tabindex="1"/>
							</td>
							<td class="separador"></td>
							<td class="label">
								<label for=lblTipoPersona>Tipo Persona: </label>
							</td>
							<td nowrap="nowrap">
								<form:select id="tipoPersona" name="tipoPersona" path="tipoPersona" tabindex="2">
									<form:option value="">Selecciona </form:option>
									<form:option value="1">Fisica</form:option>
									<form:option value="2">Moral</form:option>
								</form:select>
							</td>
						</tr>
						<tr>
							<td class="label" id="lblRfc">
								<label for="lblrfc">RFC: </label>
							</td>
							<td colspan="2">
								<form:input type="text" id="RFC" name="RFC" path="RFC" size="20" onblur="ponerMayusculas(this)" tabindex="3" />
							</td>
						</tr>
						<tr>
							<td class="label" id="lblMetodo">
								<label for="lblRazonSocial">Razon Social: </label>
							</td>
							<td colspan="4">
								<form:input type="text" id="razonSocial" name="razonSocial" path="razonSocial" size="53" onblur="ponerMayusculas(this)" tabindex="4" />
							</td>
						</tr>
						<tr>
							
							<td class="lavel">
								<label for="lblCalle">Calle:</label>
							</td>
							<td colspan="2">
								<form:input type="text" id="calle" name="calle" path="calle" size="30" tabindex="5" />
							</td>

							<td class="label">
								<label>Num. Int.:</label>
							</td>
							<td>
								<form:input type="text" id="numInt" name="numInt" path="numInt" size="8" tabindex="6"/>
							</td>
						</tr>
						<tr>
							<td class="label">
								<label>Num. Ext.:</label>
							</td>
							<td>	
								<form:input type="text" id="numExt" name="numExt" path="numExt" size="8" tabindex="7"/>
							</td>
							<td class="separador"></td>
							<td class="Label">
								<label>Colonia:</label>
							</td>
							<td>
								<form:input type="text" id="colonia" name="colonia" path="colonia" size="20" tabindex="8" />
							</td>
						</tr>
						<tr>
							<td class="label">
								<label>Localidad:</label>
							</td>
							<td>
								<form:input type="text" id="localidad" name="localidad" path="localidad" size="20" tabindex="9" />
							</td>
							<td class="separador"></td>
							<td class="label">
								<label>Municipio:</label>
							</td>
							<td>
								<form:input type="text" id="municipio" name="municipio" path="municipio" size="20" tabindex="10" />
							</td>
						</tr>
						<tr>
							<td class="label">
								<label>Estado:</label>
							</td>
							<td>
								<form:input type="text" id="estado" name="estado" path="estado" size="20" tabindex="11" />
							</td>
							<td class="separador"></td>
							<td class="label">
								<label>Codigo Postal:</label>
							</td>
							<td>
								<form:input type="text" id="codigoPostal" name="codigoPostal" path="codigoPostal" size="8" tabindex="12" />
							</td>
						</tr>
						<tr>
							<td class="label">
								<label>Telefono Fijo:</label>
							</td>
							<td>
								<form:input type="text" id="telefono" name="telefono" path="telefono" size="15" tabindex="13" />
							</td>
							<td class="separador"></td>
							<td class="label">
								<label>Telefono Celular:</label>
							</td>
							<td>
								<form:input type="text" id="celular" name="celular" path="celular" size="15" tabindex="14" />
							</td>
						</tr>
						<tr>
							<td class="label">
								<label>Correo:</label>
							</td>
							<td>
								<form:input type="text" id="correo" name="correo" path="correo" size="30" tabindex="15" />
							</td>
							<td class="separador"></td>
							<td class="label">
								<label>Estatus:</label>
							</td>
							<td>
								<form:select id="estatus" name="estatus" path="estatus" tabindex="16">
									<form:option value="1">Activo</form:option>
									<form:option value="2">Inactivo</form:option>
								</form:select>
							</td>
						</tr>
					</table>
				</fieldset>
				<table width="100%">
					<tr>
						<td align="right">
							<input type="submit" id="guardar" name="guardar" class="submit" value="Guardar"  tabindex="16"/>
							<input type="submit" id="modificar" name="modificar" class="submit" value="Modificar"  tabindex="17"/>
							<input type="submit" id="eliminar" name="eliminar" class="submit" value="Eliminar"  tabindex="18"/>
							<input type="hidden" id="tipoTransaccion" name="tipoTransaccion"/>
						</td>
					</tr>
				</table>		
			</form:form>
		</div>

<div id="cargando" style="display: none;"></div>
<div id="cajaLista" style="display: none;overflow:">
	<div id="elementoLista"></div>
</div>
<div id="cajaListaCte" style="display: none;overflow-y: scroll;height:200px;">
	<div id="elementoListaCte"></div>
</div>
</body>
<div id="mensaje" style="display: none;"></div>
</html>