$(document).ready(function() {
	esTab = true;

	//Definicion de Constantes y Enums  
	var catTipoTransaccionFactura = {
			'genera':'1',
			'cancelar':'2'
	};
	var catTipoConsultaFactura= {
			'principal':1,
			'foranea':2
	};	
	var catTipoListaFactura = {
			'principal':1,
			'foranea':2
	};


	//------------ Metodos y Manejo de Eventos -----------------------------------------
	agregaFormatoControles('formaGenerica');
	deshabilitaBoton('generar', 'submit'); 
	

	$(':text').focus(function() {	
		esTab = false;
	});

	$.validator.setDefaults({
		submitHandler: function(event) { 
			grabaFormaTransaccion(event, 'formaGenerica', 'contenedorForma', 'mensaje','true', 'receptor');
		}
	});

	$(':text').bind('keydown',function(e){
		if (e.which == 9 && !e.shiftKey){
			esTab= true;
		}
	});

	$('#generar').click(function() {		
		$('#tipoTransaccion').val(catTipoTransaccionFactura.genera);
		operacion="1";
	});

	$('#generar').attr('tipoTransaccion', '1');
	
	$('#montoSuperior').blur(function() {
		if($('#montoInferior').asNumber() >= $('#montoSuperior').asNumber()){
			alert("El Monto Superior debe de ser Mayor al Monto Inferior");
			$('#montoSuperior').focus();
		}
	});
	$('#gridConceptos').show();
	consultaConceptos();
	consultaReceptor();
	consultaEmisor();
	consultaMetodoPago();
	//consultaReceptor();
	//consultaEmisor();
	
	//consultaCondicionPago();


	//------------ Validaciones de la Forma -------------------------------------
	$('#formaGenerica').validate({
		rules: {
			receptor : 	'required',
			emisor	 : 	'required',
			metodoPago: 	'required',
			lugarExp: 	'required',
			condicionPago : 	'required',
			formaPago: 	'required'
		},
		messages: {
			receptor : 	'Especifique el Receptor',
			emisor	 : 	'Especifique el Remisor',
			metodoPago: 	'Especifique el Metodo de Pago',
			lugarExp: 	'Especifique el Lugar de Expedicion',
			condicionPago : 	'Especifique la Condicion de Pago',
			formaPago: 	'Especifique la Forma de Pago'
		}
	});

	//------------ Validaciones de Controles -------------------------------------
	function consultaConceptos(){
		var params = {};
		params['tipoLista'] = 2;
		params['numFactura'] = "1";
		$.post("conceptosFactGrid.htm", params, function(data){
			if(data.length >0) {
				$('#gridConceptos').html(data);
				$('#gridConceptos').show();
				/*var numFilas = consultaFilas();
				if(numFilas>0){
					$('#gridConceptos').html(data);
					$('#gridConceptos').show();
				}else{
					$('#gridConceptos').html("");
					$('#gridConceptos').hide();
				}*/
			}else{
				$('#gridConceptos').html(data);
				$('#gridConceptoss').show();
			}
		});
	}
	
	function consultaReceptor() {
		dwr.util.removeAllOptions('receptor');
		dwr.util.addOptions('receptor', {0:'Seleciona'});
		clienteServicio.listaCombo(2, function(receptorFact){
			dwr.util.addOptions('receptor', receptorFact, 'receptorID', 'razonSocial');
		});
	}
	function consultaEmisor() {
		dwr.util.removeAllOptions('emisor');
		dwr.util.addOptions('emisor', {0:'Seleciona'});
		emisorServicio.listaCombo(2, function(emisores){
			dwr.util.addOptions('emisor', emisores, 'emisorID', 'razonSocial');
		});
	}
	
	function consultaMetodoPago() {
		dwr.util.removeAllOptions('metodoPago');
		dwr.util.addOptions('metodoPago', {0:'Seleciona'});
		metodoPagoServicio.listaCombo(2, function(metodoPago){
			dwr.util.addOptions('metodoPago', metodoPago, 'metodoPagoID', 'descripcion');
		});
	}
});