$(document).ready(function() {
		esTab = true;
	 
	//Definicion de Constantes y Enums  
	var catTipoTransaccionRol = {  
  		'agrega':'1',
  		'modifica':'2'	};
	
	var catTipoConsultaRol = {
  		'principal'	: 1,
  		'foranea'	: 2
	};	
	
		//------------ Metodos y Manejo de Eventos -----------------------------------------
	 deshabilitaBoton('modifica', 'submit');
    deshabilitaBoton('agrega', 'submit');

	
	$(':text').focus(function() {	
	 	esTab = false;
	});
	

	$.validator.setDefaults({
            submitHandler: function(event) { 
                    grabaFormaTransaccion(event, 'formaGenerica', 'contenedorForma', 'mensaje','true','rolID'); 
            }
    });				
	    
	$(':text').bind('keydown',function(e){
		if (e.which == 9 && !e.shiftKey){
			esTab= true;
		}
	});
	
	$('#agrega').click(function() {		
		$('#tipoTransaccion').val(catTipoTransaccionRol.agrega);
	});
	
	$('#modifica').click(function() {		
		$('#tipoTransaccion').val(catTipoTransaccionRol.modifica);
	});	

	$('#rolID').bind('keyup',function(e){
		lista('rolID', '2', '1', 'nombreRol', $('#rolID').val(), 'listaRoles.htm');
	});
	
	$('#rolID').blur(function() { 
  		validaRol(this.id); 
	});
	
	
	//------------ Validaciones de la Forma -------------------------------------
	
	$('#formaGenerica').validate({
				
		rules: {
			
			rolID: {
				required: true
			},
	
			nombreRol: {
				required: true
			},	
			
			descripcion: { 
				required: true
			},	
		},
		messages: {
			
		
			rolID: {
				required: 'Especificar No.'
			},
			
			nombreRol: {
				required: 'Especificar Nombre'
			},
			
			descripcion: {
				required: 'Especificar Descripcion',
			}
		}		
	});
	
	//------------ Validaciones de Controles -------------------------------------
	
		function validaRol(control) {
		var numRol = $('#rolID').val();
		setTimeout("$('#cajaLista').hide();", 200);
		if(numRol != '' && !isNaN(numRol) && esTab){
			
			if(numRol=='0'){
				habilitaBoton('agrega', 'submit');		
				deshabilitaBoton('modifica', 'submit');
				inicializaForma('formaGenerica','clienteID' )

			} else {
				deshabilitaBoton('agrega', 'submit');
				habilitaBoton('modifica', 'submit');
				var rolBeanCon = { 
  				'rolID':numRol
  				
				};
				
				rolesServicio.consultaRoles(catTipoConsultaRol.principal,rolBeanCon,function(rol) {
						if(rol!=null){
							dwr.util.setValues(rol);	
								
							deshabilitaBoton('agrega', 'submit');
							habilitaBoton('modifica', 'submit');													
						}else{
							
							alert("No Existe el rol");
							inicializaForma('formaGenerica','rolID' )
							deshabilitaBoton('modifica', 'submit');
   						deshabilitaBoton('agrega', 'submit');
								$('#rolID').focus();
								$('#rolID').select();	
																			
							}
				});
						
			}
												
		}
	}

		
});
	