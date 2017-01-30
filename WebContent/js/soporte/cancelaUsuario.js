$(document).ready(function() {
	esTab = true;
	//validaCap=false;
	//Definicion de Constantes y Enums  
	var catTipoActualizaUsuario = {
  		'actualizaCancel':3,
  		'resetPassword':4,       
	};
	var catTipoConsultaUsuario = { 
  		'cancela':5, 
	};
	var cancelado ='C';
	var activo = 'A';
	var bloqueado = 'B';
	var parametroBean = consultaParametrosSession(); 
	
	//------------ Metodos y Manejo de Eventos -----------------------------------------

   deshabilitaBoton('cancelar', 'submit');
	
	$(':text').focus(function() {	
	 	esTab = false;
	});
	
	$.validator.setDefaults({
            submitHandler: function(event) { 
                    grabaFormaTransaccion(event, 'formaGenerica', 'contenedorForma', 'mensaje','true','usuarioID'); 
            }
    });	
	    
	$(':text').bind('keydown',function(e){
		if (e.which == 9 && !e.shiftKey){
			esTab= true;
		}
	});
	
/*	$('#cancelar').click(function() {		
		$('#tipoTransaccion').val(catTipoActualizaUsuario.actualizaCancel);  
	});*/
	$('#actualizar').click(function() {		
		$('#tipoTransaccion').val(catTipoActualizaUsuario.resetPassword);  
	});
	
	$('#usuarioID').bind('keyup',function(e){
		//TODO Agregar Libreria de Constantes Tipo Enum
		lista('usuarioID', '2', '1', 'nombreCompleto', $('#usuarioID').val(), 'listaUsuarios.htm');
	});
		
	$('#usuarioID').blur(function() {
  		consultaUsuario(this.id);
	});
	
			
	//------------ Validaciones de la Forma -------------------------------------
	
	$('#formaGenerica').validate({
				
		rules: {
			
			usuarioID: {
				required: true
				
			},
					
		},
		messages: {
			
		
			usuarioID: {
				required: 'Especificar Numero'
			
			},
			
			
		}		
	});
	
	//------------ Validaciones de Controles -------------------------------------
	function consultaUsuario(idControl) {
		var jqUsuario = eval("'#" + idControl + "'");
		var numUsuario = $(jqUsuario).val();	
		var usuarioBeanCon = {  
  				'usuarioID':numUsuario 
				};
		setTimeout("$('#cajaLista').hide();", 200);		
		if(numUsuario != '' && !isNaN(numUsuario) && esTab){
			usuarioServicio.consulta(catTipoConsultaUsuario.cancela,usuarioBeanCon,function(usuario) {  
						if(usuario!=null){
						dwr.util.setValues(usuario); 
						var status = usuario.estatus; 
						if(status == activo){
						 $('#fechaCancel').val(parametroBean.fechaSucursal);
						}  
						if(status == bloqueado){ 
						 $('#fechaCancel').val(parametroBean.fechaSucursal);
						} 
					habilitaBoton('cancelar', 'submit');		 
						if(status == cancelado){  
						alert('El usuario ya esta Cancelado');	  
						deshabilitaBoton('cancelar', 'submit'); 	 
						}
								 			
						}else{ 
							alert("No Existe el Usuario");
						}    						
				});
			}
		}

});