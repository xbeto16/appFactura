<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html"%> 
<%@page pageEncoding="UTF-8"%>
<html>
	<head>
		<script type="text/javascript" src="dwr/interface/facturaServicio.js"></script>
		<script type="text/javascript" src="dwr/interface/clienteServicio.js"></script>
		<script type="text/javascript" src="dwr/interface/emisorServicio.js"></script>
		<script type="text/javascript" src="dwr/interface/metodoPagoServicio.js"></script>
      	<script type="text/javascript" src="js/facturacion/generaFactura.js"></script>
	</head>
	<body>
		<div id="contenedorForma">
			<form:form id="formaGenerica" name="formaGenerica" method="POST" commandName="facturaBean">
				<fieldset class="ui-widget ui-widget-content ui-corner-all">
					<legend class="ui-widget ui-widget-content ui-widget-header ui-corner-all">Nueva Factura</legend>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr>
							<td class="label">
								<label for="lblreceptor">Receptor: </label>
							</td>
							<td>
								<form:select id="receptor" name="receptor" path="receptor" tabindex="1">
									<form:option value="">Selecciona </form:option> 
								</form:select>
							</td>
							<td class="separador"></td>
							<td class="label">
								<label for=lblemisor>Emisor: </label>
							</td>
							<td nowrap="nowrap">
								<form:select id="emisor" name="emisor" path="emisor" tabindex="2">
									<form:option value="">Selecciona </form:option> 
								</form:select>
							</td>
						</tr>
						<tr>
							<td class="label" id="lblMetodo">
								<label for="forMetodo">Metodo de pago: </label>
							</td>
							<td>
								<form:select id="metodoPago" name="metodoPago" path="metodoPago" tabindex="3">
									<form:option value="">Selecciona</form:option> 
								</form:select>
					</td>
					<td class="separador"></td>
					<td class="lavel">
						<label for="lbllugar">Lugar de Expedición:</label>
					</td>
					<td>
						<form:input type="text" id="lugarExp" name="lugarExp" path="lugarExp" tabindex="4" />
					</td>
					
				</tr>
				<tr>
					<td class="lavel">
						<label>Numero de Cuenta de Pago:</label>
					</td>
					<td>
						<form:input id="numPago" name="numCuenta" path="numCuenta" tabindex="5"/>
					</td>
					<td class="separador"></td>
					<td class="label">
						<label>Serie:</label>
					</td>
					<td>	
						<form:input id="serie" name="serie" path="serie" tabindex="6"/>
					</td>
				</tr>
				<tr>
					<td class="Label">
						<label>Condicion de Pago:</label>
					</td>
					<td>
						<form:select id="condicionPago" name="condicionPago" path="condicionPago" tabindex="7">
							<form:option value="">Selecciona</form:option>
							<form:option value="1">Contado</form:option> 
							<form:option value="2">Credito</form:option>  
						</form:select>
					</td>
					<td class="separador"></td>
					<td class="label">
						<label>Folio:</label>
					</td>
					<td>
						<form:input id="folio" name="folio" path="folio" taxindex="8" />
					</td>
				</tr>
				<tr>
					<td class="label">
						<label>Forma de Pago:</label>
					</td>
					<td>
						<form:select id="formaPago" name="formaPago" path="formaPago" tabindex="9">
							<form:option value="">Selecciona</form:option>
							<form:option value="1">Pago en una sola exhibicion</form:option>
						</form:select>
					</td>
				</tr>
			</table>
		 	</br>
		 	<div id ="gridConceptos"></div>
			<table width="100%" >
				<tr>
					<td align="right">
						<input type="button" id="calculaTotal" name="calculaTotal" value="Calcular Total" class="botonGral" />
					</td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td align="right" class="label">
						<label>Subtotal: $</label>
					</td>
					<td>
						<input type="text" id="subtotal" name="subtotal" size="10" readonly="true" />
					</td>
					<td align="right" class="label">
						<label>IVA: $</label>
					</td>
					<td>
						<input type="text" id="total" name="total" size="10" readonly="true" />
					</td>
					<td align="right" class="label">
						<label>Total: $</label>
					</td>
					<td>
						<input type="text" id="total" name="total" size="10" readonly="true" />
					</td>
				</tr>
			</table>
		</fieldset>	
		<table width="100%">
			<tr>
				<td align="right">
					<input type="submit" id="generar" name="generar" class="submit" value="Genera Factura"  tabindex=""/>
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