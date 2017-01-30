$(document).ready(function() {
	parametros = consultaParametrosSession();
	
	
	var catTipoTransaccionParametros = {
		'alta' : '1',
		'modificacion' : '2'			
	};

	
			
	$(':text').bind('keydown', function(e) {
		if (e.which == 9 && !e.shiftKey) {			
		}
	});
		// ------------ Metodos y Manejo de Eventos
	deshabilitaBoton('modificar', 'submit');
	agregaFormatoControles('formaGenerica');
		
		
	$.validator.setDefaults({
	    submitHandler: function(event) { 
	    		grabaFormaTransaccion(event, 'formaGenerica', 'contenedorForma', 'mensaje','true','empresaID'); 
	      }
	 });
		   				
	$('#modificar').click(function() {		
		$('#tipoTransaccion').val(catTipoTransaccionParametros.modificacion);
	});

	$('#empresaID').blur(function() {
		validaEmpresaID(this);
	});
	
	$('#empresaID').bind('keyup',function(e){	
		var camposLista = new Array();
		var parametrosLista = new Array();
		camposLista[0] = "nombreInstitucion";
		parametrosLista[0] = $('#empresaID').val();
		lista('empresaID', '1', '1', camposLista,parametrosLista, 'listaParametrosSis.htm');	
	});
	
	 

	 
	 
	
	  						
			//------------ Validaciones de la Forma -------------------------------------
				$('#formaGenerica').validate({			
					rules: {			
						empresaID: {
							required: true,
							minlength: 1,
							number: true
						},						
						
						
						
						
						diasCambioPass: {
							required: true,
							minlength: 1,
							number: true,
							numeroPositivo: true
						},
						lonMinCaracPass: {
							required: true,
							minlength: 1,
							number: true,
							numeroPositivo: true
						},			
					},		
					messages: {
						empresaID: {
							required: 'Especificar la empresa',
							minlength: 'Al menos 1 Caracteres',
							number: 'solo números'
						},								
						
						
						
						
						
						
						diasCambioPass: {
							required: 'Especifique los días de crédito',
							minlength: 'mínimo 1 caracter',
							number: 'solo números',
							numeroPositivo:'Números positivos'
						},						
						lonMinCaracPass: {
							required: 'Especifique la longitud mínima de caracteres',
							minlength: 'mínimo 1 caracter',
							number: 'solo números',
							numeroPositivo:'Números positivos'
						},
						
					}		
				});
				
//-------------------- Métodos------------------	
	function validaEmpresaID(control) {
		var numEmpresaID = $('#empresaID').val();
		setTimeout("$('#cajaLista').hide();", 200);
		var tipoCon = 1;
		var ParametrosSisBean = {
				'empresaID'	:numEmpresaID
		};
		if (numEmpresaID != '' && !isNaN(numEmpresaID)) {
			if (numEmpresaID == '0') {			
				deshabilitaBoton('modificar', 'submit');
				inicializaForma('formaGenerica', 'empresaID');				
			} else {								
				habilitaBoton('modificar', 'submit');
				parametrosSisServicio.consulta(tipoCon,ParametrosSisBean,function(parametrosSisBean) {
					if (parametrosSisBean != null) {
						dwr.util.setValues(parametrosSisBean);						
						
							
				
							
							habilitaBoton('modificar','submit');
					} else {
						limpiaForm($('#formaGenerica'));						
						deshabilitaBoton('modificar','submit');						
						$('#empresaID').focus();
						$('#empresaID').select();
					}
				});
			}//else
		}//if
	}//validaEmpresaID
	
		
	
	  

		
	
		
	
});