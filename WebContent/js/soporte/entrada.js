$(document).ready(function() {
	esTab = true;
	var parametroBean = consultaParametrosSession();
							//alert('par nom '+parametroBean.nombreUsuario); 
							
	var codeHtmlUsConcur = "</BR><label class='error'>El Usuario ya tiene una sesion abierta:</label>"; 
	var codeHtmlSesAbierta = "</BR><label class='error'>Ya hay una sesion abierta en esta Computadora:</label>";						
	var codeHtmlLoginFailed = "</BR><label class='error'>Usuario Incorrecto</label>";
	var codeHtmlUserBlock = "</BR><label class='error'>Usuario Bloqueado</label>";
	var codeHtmlUserCancel = "</BR><label class='error'>Usuario Cancelado</label>";
	var codeHtmlIntentos = "</BR><label class='error'>No. Accesos Incorrectos:  ";
	
	
	var catTipoConsultaUsuario = {
  		'clave':3,
	};	

	var estatusUsuario = {
  		'Activo':'A',
		'Bloqueado':'B',
		'Cancelado':'C',
		'Inactivo':'I'
	};
	
	deshabilitaBoton('submitBtn', 'submit');
	
	$('#loginName').inlineFieldLabel({label:'Numero de Usuario'});
		
	$(':text').focus(function() {	
	 	esTab = false;
	});
	
	$(':text').bind('keydown',function(e){
		if (e.which == 9 && !e.shiftKey){
			esTab= true;
		}
	});	
	
	$('#loginName').blur(function() {
  		validaUsuario(this);
	});	
	
	function validaUsuario(control) {
		var claveUsuario = $('#loginName').val();
		if(claveUsuario != '' && esTab){
			var usuarioBean = {
  				'clave':claveUsuario
			};
										
			usuarioServicio.consulta(catTipoConsultaUsuario.clave, usuarioBean, function(usuario) {
						if(usuario!=null){ 
							
						 if(usuario.estatusSesion==estatusUsuario.Activo){ 
								$('#mensajeLogin').html(codeHtmlUsConcur);
						 }else if(parametroBean.nombreUsuario!=''){ 
								$('#mensajeLogin').html(codeHtmlSesAbierta);
						 }else if(usuario.estatus==estatusUsuario.Bloqueado){
								$('#mensajeLogin').html(codeHtmlUserBlock);
							}else if(usuario.estatus==estatusUsuario.Cancelado){
								$('#mensajeLogin').html(codeHtmlUserCancel);
							
							}else if(usuario.estatus==estatusUsuario.Activo){									
								if(usuario.loginsFallidos > 0){
									$('#mensajeLogin').html(codeHtmlIntentos + usuario.loginsFallidos +
																	" " + "</label>");									
								} else{
									$('#mensajeLogin').html("");
								}
								habilitaBoton('submitBtn', 'submit');								
							}
							$('#mensajeLogin').show();
						}else{
							deshabilitaBoton('submitBtn', 'submit');
														
							$('#mensajeLogin').html(codeHtmlLoginFailed);
							$('#mensajeLogin').show();
													
							$('#loginName').focus();
							$('#loginName').select();
						}
			});
		}
	}
		
	
});