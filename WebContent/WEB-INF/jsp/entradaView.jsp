<HTML>
<HEAD>
<title>FACTURACION</title>
	<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />

	<link href="css/login-1.css" media="all"  rel="stylesheet" type="text/css">
	<link href="css/slide.css" media="screen" rel="stylesheet" type="text/css"   />
	<link href="css/input.css" media="screen" rel="stylesheet" type="text/css"   />
	
	<script type='text/javascript' src='js/jquery-1.5.1.min.js'></script>
	<script type="text/javascript" src="js/jquery.inlineFieldLabel.js"></script>
	<script type="text/javascript" src="js/slide.js" ></script>
	
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/interface/usuarioServicio.js"></script>                                                                       
	<script type="text/javascript" src="dwr/util.js"></script>    

	<script type="text/javascript" src="js/forma.js" ></script>
	<script type="text/javascript" src="js/soporte/entrada.js" ></script>
	
</HEAD>
<BODY style=" text-align:center">
<div id="PrincipalHeather" style= "margin-left: auto; margin-right: auto; height: 75px; border: 1;  background-repeat: no-repeat; position:relative; width: 1024px;" align="center"></div>

<div id="toppanel" style="width:1000px;">
	<div id="panel">
		<div class="content clearfix">
			<div id="imagenLogin"  style="margin-top: 0; margin-left: 0; position:relative; width:450px; height:500px; background-image:url(images/ImgLogin1.jpg); background-repeat: no-repeat;"></div>
				<DIV id="bodyLogin" class="loginView" >
					<DIV id="bodyDivLogin">
                    	<DIV id="loginContainer">
                        	<P>Favor de Especificar Numero de Usuario y Password:</P>
                    		<DIV id="formContainer">
                            	<form method="POST" id="loginForm" name="loginForm" action="MEDI_SecurityCheck">
                               	<DIV class="divHeader">
                                    <input type="text" name="medi_username" id="loginName" value="" tabindex="1"/>
                                </DIV>
                                <DIV class="divHeader">
                                    <input type="password" name="medi_password" id="contraseniaId" value="" tabindex="2"/>
                                </DIV>
                                <DIV class="divHeader">
                        			<input type="submit" name="submit" id="submitBtn" value="Entrar"
                                   	class="submit"" tabindex="3"/>
                        			<DIV class="fr smallfont" style="margin-top: 3px;">
                                        <A href="olvidoUsuario.htm" class = "e_parent_redirect">
                                            Olvid&oacute; su Usuario??
                                        </A> 
                                    </DIV>
                                </DIV>
                            	</form>                            	
                    		</DIV>
                    		</BR>                    		
                    		Por Seguridad de la Aplicaci&oacute;n, recuerda que cuentas con 3 oportunidades para teclear tu Usuario y Password correctamente, en caso contrario ser&aacute; bloqueado.
                    		<div id="mensajeLogin" style="display: none;">
                    		</DIV>
         				</DIV>
                    </DIV>
				</div>
			</div>
		</div>
    	<div class="tab">
            <ul class="login">
                <li class="left">&nbsp;</li>
                <li>Bienvenido!</li>
                <li class="sep">|</li>
                <li id="toggle">
                    <a id="open" class="open" href="#">Login</a>
                    <a id="close" class= "close" style="display: none;"  href="#">Close Panel</a>			
                </li>
                <li class="right">&nbsp;</li>
            </ul> 
        </div>
	</div>
</DIV>
</BODY>
</HTML>