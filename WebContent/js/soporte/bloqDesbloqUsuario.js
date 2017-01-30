$(document).ready(function() {
	esTab = true;
	//validaCap=false;
	//Definicion de Constantes y Enums  
	var catTipoActualizaUsuario = {
  		'actualizaBloDes':2,
	};
	var catTipoConsultaUsuario = {
  		'bloqueadoDesbloq':4, 
	};
	var bloqueado ='B';
	var parametroBean = consultaParametrosSession(); 
	//------------ Metodos y Manejo de Eventos -----------------------------------------

   deshabilitaBoton('actualiza', 'submit');
	
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
	
	$('#actualiza').click(function() {		
		$('#tipoTransaccion').val(catTipoActualizaUsuario.actualizaBloDes);  
	});
	
	$('#usuarioID').bind('keyup',function(e){
		//TODO Agregar Libreria de Constantes Tipo Enum
		lista('usuarioID', '2', '1', 'nombreCompleto', $('#usuarioID').val(), 'listaUsuarios.htm');
	});
		
	$('#usuarioID').blur(function() {
  		consultaUsuario(this.id);
	});
	$('#estatus').blur(function() {
	var status = $('#estatus').val();
  		if(status == bloqueado){
  		$('#bloqueo').show(500);
  		$('#fechaBloq').val(parametroBean.fechaSucursal);  
  		}else{
  		$('#bloqueo').hide(500);
  		}  
	});		
			
	//------------ Validaciones de la Forma -------------------------------------
	
	$('#formaGenerica').validate({
				
		rules: {
			
			estatus: {
				required: true
				
			},
					
		},
		messages: {
			
		
			estatus: {
				required: 'Especificar Estatus'
			
			},
			
			
		}		
	});
	
	//------------ Validaciones de Controles -------------------------------------
	
	function validaUsuario(control) {
		var numUsuario = $('#usuarioID').val();
		setTimeout("$('#cajaLista').hide();", 200);
		
		
		if(numUsuario != '' && !isNaN(numUsuario) && esTab){
			
			if(numUsuario=='0'){
				$('#contrasenia').attr("readOnly",false); 
				habilitaBoton('agrega', 'submit');		
				deshabilitaBoton('modifica', 'submit');
				limpiaForm($('#formaGenerica'));
				
			} else {
				deshabilitaBoton('agrega', 'submit');
				habilitaBoton('modifica', 'submit');
				var usuarioBeanCon = {
  				'usuarioID':numUsuario 
				};	
				usuarioServicio.consulta(1,usuarioBeanCon,function(usuario) {
							if(usuario!=null){
								dwr.util.setValues(usuario);
								//$('#contrasenia').attr("disabled",true);
								$('#contrasenia').attr("readOnly",true);
								
								deshabilitaBoton('agrega', 'submit');
								habilitaBoton('modifica', 'submit');								
							}else{
								limpiaForm($('#formaGenerica'));
								alert("No Existe el Usuario");
								deshabilitaBoton('modifica', 'submit');
   							deshabilitaBoton('agrega', 'submit');
								$('#usuarioID').focus();
								$('#usuarioID').select();																
							}
				});
								
			}
												
		}
	}
	//catTipoConsultaUsuario.bloqueadoDesbloq 
	function consultaUsuario(idControl) {
		var jqUsuario = eval("'#" + idControl + "'");
		var numUsuario = $(jqUsuario).val();	
		var conUsuario=2;
		var usuarioBeanCon = {
  				'usuarioID':numUsuario 
				};
		setTimeout("$('#cajaLista').hide();", 200);		
		if(numUsuario != '' && !isNaN(numUsuario) && esTab){
			usuarioServicio.consulta(catTipoConsultaUsuario.bloqueadoDesbloq,usuarioBeanCon,function(usuario) {  
						if(usuario!=null){		
						dwr.util.setValues(usuario);
						var status = usuario.estatus;     
						
				  		if(status == bloqueado){
				  		$('#bloqueo').show(500);
				  		}else{ 
				  		$('#bloqueo').hide(500);
				  		$('#motivoBloqueo').val('');
						$('#fechaBloqueo').val('');
				  		}  
						habilitaBoton('actualiza', 'submit');
						var cancel= 'C';
				  		if(status == cancel){ 
				  		alert('El usuario no se puede bloquear está Cancelado');
				  		$('#estatus').attr("disabled",true);   
				  		deshabilitaBoton('actualiza', 'submit');		   	
				  		}		 			
						}else{
							alert("No Existe el Usuario");
						}    						
				});
			}
		}
	
});