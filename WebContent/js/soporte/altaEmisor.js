$(document).ready(function() {
	esTab = true;

	//Definicion de Constantes y Enums  
	var catTipoTransacEmisor= {
			'guardar':'1',
			'modificar':'2',
			'eliminar':'3'
				
	};
	var catTipoConEmisor= {
			'principal':1,
			'foranea':2
	};	
	var catTipoListaEmisor= {
			'principal':1,
			'foranea':2
	};


	//------------ Metodos y Manejo de Eventos -----------------------------------------
	agregaFormatoControles('formaGenerica');
	deshabilitaBoton('guardar', 'submit'); 
	deshabilitaBoton('modificar', 'submit'); 
	deshabilitaBoton('eliminar', 'submit'); 
	

	$(':text').focus(function() {	
		esTab = false;
	});

	$.validator.setDefaults({
		submitHandler: function(event) { 
			grabaFormaTransaccion(event, 'formaGenerica', 'contenedorForma', 'mensaje','true', 'emisorID');
		}
	});

	$(':text').bind('keydown',function(e){
		if (e.which == 9 && !e.shiftKey){
			esTab= true;
		}
	});

	$('#emisorID').bind('keyup',function (){
            this.value = (this.value + '').replace(/[^0-9]/g, '');
    });
	
	$('#guardar').click(function() {
		$('#tipoTransaccion').val(catTipoTransacEmisor.guardar);
	});
	$('#modificar').click(function() {
		$('#tipoTransaccion').val(catTipoTransacEmisor.modificar);
	});
	$('#eliminar').click(function() {
		$('#tipoTransaccion').val(catTipoTransacEmisor.eliminar);
	});
	
	$('#emisorID').blur(function(){
		if ($(this).val() == '0'){
			$('#tipoPersona').val('')
			$('#razonSocial').val('')
			$('#RFC').val('')
			$('#calle').val('')
			$('#numInt').val('')
			$('#numExt').val('')
			$('#colonia').val('')
			$('#localidad').val('')
			$('#municipio').val('')
			$('#estado').val('')
			$('#codigoPostal').val('')
			$('#telefono').val('')
			$('#celular').val('')
			$('#correo').val('')
			habilitaBoton('guardar');
			deshabilitaBoton('modificar');
			deshabilitaBoton('eliminar');
			
		}else{
			consultaEmisor(this.id);
			
		}
	});
	

	//------------ Validaciones de la Forma -------------------------------------
	$('#formaGenerica').validate({
		rules: {
			emisorID: 	'required',
			tipoPersona: 'required',
			razonSocial: 'required',
			rfc: 		'required',
			calle: 		'required',
			numInt: 	'required',
			colonia: 	'required',
			localidad: 	'required',
			municipio: 	'required',
			estado: 	'required',
			codigoPostal:'required',
			telefono: 	'required',
			correo: 	'required'
		},
		messages: {
			emisorID: 	'Especifique el Emisor',
			tipoPersona: 'Seleccione el Tipo de Persona',
			razonSocial: 'Especifique la Razon Social',
			rfc: 		'Especifique el RFC',
			calle: 		'Especifique la Calle',
			numInt: 	'Especifique el Numero Interior',
			colonia: 	'Especifique la Colonia',
			localidad: 	'Especifique la Localidad',
			municipio: 	'Especifique el Municipio',
			estado: 	'Especifique el Estado',
			codigoPostal:'Especifique el Codigo Postal',
			telefono: 	'Especifique el Telefono ',
			correo: 	'Especifique el Correo '
		}
	});

	//------------ Validaciones de Controles -------------------------------------
	function consultaEmisor(idControl){
		var jqUsuario = eval("'#" + idControl + "'");
		var numEmisor = $(jqUsuario).val();
		setTimeout("$('#cajaLista').hide();", 200);
		var emisorBean = {  
				'emisorID': numEmisor
		};
		if (numEmisor != '' && !isNaN(numEmisor) && esTab){
			emisorServicio.consulta(catTipoConEmisor.principal, emisorBean, function(emisor){
				if(emisor != null){
					dwr.util.setValues(emisor);
					deshabilitaBoton('guardar');
					habilitaBoton('modificar');
					habilitaBoton('eliminar');
				}else{
					alert('No Existe el Emisor')
					$('#emisorID').focus();
				}
			})
		}	
	}
	
	function consultaGestor(idControl) {
		var jqUsuario = eval("'#" + idControl + "'");
		var numUsuario = $(jqUsuario).val();
		var gestor = 12;
		var usuarioBeanCon = {  
				'usuarioID':numUsuario 
		};
		setTimeout("$('#cajaLista').hide();", 200);		
		if(numUsuario != '' && !isNaN(numUsuario)){
			usuarioServicio.consulta(gestor,usuarioBeanCon,function(usuario) {  
				if(usuario!=null){
					$('#gestorID').val(usuario.usuarioID);
					$('#nombreGestor').val(usuario.nombreCompleto);			
				}else{ 
					alert("El Usuario Seleccionado No es Gestor");
					$('#gestorID').val('');
					$('#gestorID').focus();
					$('#nombreGestor').val('');
					$('#tipoGestionID').val('');
					$('#nombreTipoGestion').val('');
					$('#tipoAmbito').val('');
		            $('#gridSucursal').hide();
		        	$('#gridZona').hide();
		        	$("#tableZonaGeografica").html("");
		    		$("#tableGestorSucursal").html("");
		    		$("#tableGestorPromotor").html("");
		    		$('#tipoAmbito1').attr('checked',false);
					$('#tipoAmbito2').attr('checked',false);
					$('#tipoAmbito3').attr('checked',false);
					$('#tipoAmbito4').attr('checked',false);
					deshabilitaBoton('agrega','submit');
					
				}    						
			});
		}
		else{
			$('#gestorID').val("");
			$('#nombreGestor').val('');
		}
	}

	
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
		dwr.util.removeAllOptions('tipoCuentaID'); 
		dwr.util.addOptions('tipoCuentaID', {0:'Seleciona'}); 
		tiposCuentaServicio.listaCombo(2, function(tiposCuenta){
			dwr.util.addOptions('tipoCuentaID', tiposCuenta, 'tipoCuentaID', 'descripcion');		
		});
	}
});