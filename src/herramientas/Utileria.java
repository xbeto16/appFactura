package herramientas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import soporte.bean.MonedasBean;


public class Utileria {
	

	
	public static String completaCerosIzquierda(String cadena, int longitud) {
		String strPivote = "";

		for (int i = cadena.length(); i < longitud; i++) {
			strPivote = strPivote + "0";
		}

		return strPivote + cadena;
	}
	
	public static String completaCerosIzquierda(int cadena, int longitud) {
		return completaCerosIzquierda(String.valueOf(cadena), longitud);
	}	

	public static String completaCerosIzquierda(Long cadena, int longitud) {
		return completaCerosIzquierda(String.valueOf(cadena), longitud);
	}	
	
	public static String[] divideString(String cadena, String caracterDivision) {
		
		
		return cadena.split(caracterDivision);
	}	
	
	
	public static void escribelog(String msgLog){
		
		String Ren ;         
		ArrayList Arreglo;		
				
		Arreglo = new ArrayList();
		String archivo = System.getProperty("user.home") +
						 System.getProperty("file.separator")+	
						 "PROYECTO.txt";
		 
		try {       		 
		 
			BufferedReader BuR = new BufferedReader(new FileReader(archivo));                          
			Ren = BuR.readLine();
			
			while (Ren != null) {
				Arreglo.add(Ren);
				Ren = BuR.readLine();               
			}
			BuR.close();
		} catch (IOException e) {
			System.out.println("Error al cargar archivo.");
		} 
			
		try {
			BufferedWriter BuW = new BufferedWriter(new java.io.FileWriter(archivo));
			for( int i = 0 ; i < Arreglo.size() ; i ++) 
			{   
				BuW.write((String)Arreglo.get(i));
				BuW.newLine();  
			}			
			BuW.newLine();
			BuW.write(msgLog);
			BuW.close(); 
		} catch (IOException e) {
			System.out.println("Error al crear archivo de salida.");
		}	
	}	
	
	public static int convierteEntero(String valorStr){
		int valorInt = 0;
		
		try{
			if (!valorStr.equalsIgnoreCase("")){
				valorInt = Integer.parseInt(valorStr);
			}			
		}catch(Exception error){
			valorInt = 0;
		}
		
		return valorInt; 
		
	}
	public static long convierteLong(String valorStr){
		long valorLong = 0;
		
		try{
			if (!valorStr.equalsIgnoreCase("")){
				valorLong = Long.parseLong(valorStr);
			}			
		}catch(Exception error){
			valorLong = 0;
		}
		
		return valorLong; 
		
	}
	
	public static float convierteFlotante(String valorStr){
		float valorFlotante = 0;
		
		try{
			if (!valorStr.equalsIgnoreCase("")){
				valorFlotante = Float.parseFloat(valorStr);
			}			
		}catch(Exception error){
			valorFlotante = 0;
		}
		
		return valorFlotante; 
		
	}	

	public static double convierteDoble(String valorStr){
		double valorDoble = 0.0;
		
		try{
			if (!valorStr.equalsIgnoreCase("")){
				valorDoble = Double.parseDouble(valorStr);
			}			
		}catch(Exception error){
			valorDoble = 0.0;
		}
		
		return valorDoble; 
		
	}	
	
	public static String convierteFecha(String valorStr){
		String valorFecha = "1900-01-01" ;
		
		try{
			if (!valorStr.equalsIgnoreCase("")){
			valorFecha =valorStr;	}
		}catch(Exception error){
			valorFecha ="1900-01-01";	

		}
		
		return valorFecha; 
		
	}
	
	// Metodo de Convierte una Cantidad(En String) a Su correspondiente representacion
	// En Letras  
	public static String cantidadEnLetras(String cantidad, int monedaID,
			   							  String simboloMoneda, String monedaDescripcion) {
		String cifra = "";
		
		switch(monedaID){
			case(MonedasBean.MONEDA_BASE_LOCAL):
				cifra = convierteALetras(Double.parseDouble(cantidad), monedaID, simboloMoneda, monedaDescripcion);
				break;
			case(MonedasBean.MONEDA_BASE_EXTRANJERA): 
				cifra = convierteALetrasIngles(Double.parseDouble(cantidad), monedaID, simboloMoneda, monedaDescripcion);
				break;
		}
		
		return cifra;
	}
	public static String cantidadEnLetras(float cantidad, int monedaID,
										  String simboloMoneda, String monedaDescripcion) {
		return(cantidadEnLetras(String.valueOf(cantidad), monedaID, simboloMoneda, monedaDescripcion));
		
	}
	public static String cantidadEnLetras(double cantidad, int monedaID,
			  							  String simboloMoneda, String monedaDescripcion) {
		return(cantidadEnLetras(String.valueOf(cantidad), monedaID, simboloMoneda, monedaDescripcion));
		
	}
	public static String cantidadEnLetras(int cantidad, int monedaID,
			  String simboloMoneda, String monedaDescripcion) {
		return(cantidadEnLetras(String.valueOf(cantidad), monedaID, simboloMoneda, monedaDescripcion));
		
	}
		
	private static String convierteALetras(double cantidad, int monedaID,
										   String simboloMoneda, String monedaDescrpcion) {

		String cantidadEnLetras = herramientas.Constantes.STRING_VACIO;

		String strCien = null;
		String strDiez = null;
		String strUno = null;

		long centavos = 0;
		long unidades = 0;
		long diez = 0;
		long cien = 0;
		long milesMill = 0;

		int i = 0;
		long numero = 0;
		try {
			if (cantidad > 999999999999.99) {
				cantidadEnLetras = "Error numero muy Grande";
				return cantidadEnLetras;
			}

			numero = (long) (cantidad / 1000000000);

			cantidadEnLetras = herramientas.Constantes.ASTERISCO+ herramientas.Constantes.PARENTESIS_ABRE;

			while (i < 4) {
				i++;
				strCien = Constantes.STRING_VACIO;
				strDiez = herramientas.Constantes.STRING_VACIO;
				strUno = herramientas.Constantes.STRING_VACIO;
				if (numero != 0) {
					cien = (numero / 100);
					diez = (numero - (cien * 100)) / 10;
					unidades = numero - ((numero / 10) * 10);

					strCien = herramientas.Constantes.STRING_VACIO;

					switch ((int) cien) {
					case 0:
						strCien = herramientas.Constantes.STRING_VACIO;
						break;
					case 1:
						if (diez == 0 && unidades == 0) {
							strCien = "CIEN ";
						} else {
							strCien = "CIENTO ";
						}
						break;

					case 2:
						strCien = "DOSCIENTOS ";
						break;
					case 3:
						strCien = "TRESCIENTOS ";
						break;
					case 4:
						strCien = "CUATROCIENTOS ";
						break;
					case 5:
						strCien = "QUINIENTOS ";
						break;
					case 6:
						strCien = "SEISCIENTOS ";
						break;
					case 7:
						strCien = "SETECIENTOS ";
						break;
					case 8:
						strCien = "OCHOCIENTOS ";
						break;
					case 9:
						strCien = "NOVECIENTOS ";
						break;
					}

					strDiez = herramientas.Constantes.STRING_VACIO;

					switch ((int) diez) {
					case 0:
						strDiez = herramientas.Constantes.STRING_VACIO;
						break;
					case 1:
						if (unidades <= 5 && unidades > 0) {
							switch ((int) unidades) {
							case 1:
								strDiez = "ONCE ";
								break;
							case 2:
								strDiez = "DOCE ";
								break;
							case 3:
								strDiez = "TRECE ";
								break;
							case 4:
								strDiez = "CATORCE ";
								break;
							case 5:
								strDiez = "QUINCE ";
								break;
							}
						} else {
							strDiez = "DIEZ ";
						}
						break;
					case 2:
						if (unidades > 0) {
							strDiez = "VEINTI";
						} else {
							strDiez = "VEINTE ";
						}
						break;
					case 3:
						strDiez = "TREINTA ";
						break;
					case 4:
						strDiez = "CUARENTA ";
						break;
					case 5:
						strDiez = "CINCUENTA ";
						break;
					case 6:
						strDiez = "SESENTA ";
						break;
					case 7:
						strDiez = "SETENTA ";
						break;
					case 8:
						strDiez = "OCHENTA ";
						break;
					case 9:
						strDiez = "NOVENTA ";
						break;
					}

					if (diez == 1 && unidades > 5) {
						strDiez = strDiez + "Y ";
					}

					if (diez > 2 && unidades != 0) {
						strDiez = strDiez + "Y ";
					}

					strUno = herramientas.Constantes.STRING_VACIO;

					if (diez == 1 && unidades <= 5 && unidades > 0) {
						strUno = herramientas.Constantes.STRING_VACIO;
					} else {

						switch ((int) unidades) {
						case 0:
							strUno = herramientas.Constantes.STRING_VACIO;
							break;
						case 1:
							strUno = "UN ";
							break;
						case 2:
							strUno = "DOS ";
							break;
						case 3:
							strUno = "TRES ";
							break;
						case 4:
							strUno = "CUATRO ";
							break;
						case 5:
							strUno = "CINCO ";
							break;
						case 6:
							strUno = "SEIS ";
							break;
						case 7:
							strUno = "SIETE ";
							break;
						case 8:
							strUno = "OCHO ";
							break;
						case 9:
							strUno = "NUEVE ";
							break;
						}
					}
				}

				switch (i) {
				case 1:
					if (numero > 0) {
						cantidadEnLetras = strCien + strDiez + strUno + "MIL ";
						milesMill = 1;
					}

					cantidad = cantidad - (numero * 1000000000);
					numero = (long) cantidad / 1000000;

					break;

				case 2:
					if (numero > 0 || milesMill == 1) {
						
						cantidadEnLetras = cantidadEnLetras + strCien + strDiez + strUno + "MILLON";
						
						if (numero > 1 || milesMill == 1) {
							if (((numero * 1000000) - cantidad) != 0) {
								cantidadEnLetras = cantidadEnLetras + "ES ";
							} else {
								cantidadEnLetras = cantidadEnLetras + "ES DE ";
							}
						} else {
							if (((numero * 1000000) - cantidad) != 0) {
								cantidadEnLetras = cantidadEnLetras + " ";
							} else {
								cantidadEnLetras = cantidadEnLetras + " DE ";
							}
						}
					}

					cantidad = cantidad - (numero * 1000000);
					numero = (long) (cantidad / 1000);
					break;

				case 3:
					if (numero > 0) {
						cantidadEnLetras = cantidadEnLetras + strCien + strDiez + strUno + "MIL ";
					}
					cantidad = cantidad - (numero * 1000);
					numero = (long) (cantidad / 1);
					break;

				case 4:
					cantidadEnLetras = cantidadEnLetras + strCien + strDiez + strUno + monedaDescrpcion + " ";
					cantidad = cantidad - numero;
					centavos = (long) (Math.round((cantidad * 100) * Math.pow(10, 0)) / Math.pow(10, 0));

					break;
				}
			}

			if (centavos == 0) {
				cantidadEnLetras = cantidadEnLetras + "00/100 " + simboloMoneda;
			} else {
				cantidadEnLetras = cantidadEnLetras
						+ Utileria.completaCerosIzquierda((long) centavos, 2) + "/100 " + simboloMoneda;
			}
				
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		cantidadEnLetras += herramientas.Constantes.PARENTESIS_CIERRA + herramientas.Constantes.ASTERISCO;

		return cantidadEnLetras;
	}

	/**************** En Ingles *************************/
	private static String convierteALetrasIngles(double cantidad, int monedaID,
			   									 String simboloMoneda, String monedaDescrpcion) {
		
		String cantidadEnLetras = herramientas.Constantes.STRING_VACIO;

		String strCien = null;
		String strDiez = null;
		String strUno = null;

		long centavos = 0;
		long unidades = 0;
		long diez = 0;
		long cien = 0;
		long milesMill = 0;

		int i = 0;
		long numero = 0;
		try {
			if (cantidad > 999999999999.99) {
				cantidadEnLetras = "Error numero muy Grande";
				return cantidadEnLetras;
			}

			numero = (long) (cantidad / 1000000000);

			cantidadEnLetras = herramientas.Constantes.ASTERISCO + herramientas.Constantes.ASTERISCO +
							   herramientas.Constantes.ASTERISCO + herramientas.Constantes.PARENTESIS_ABRE;

			while (i < 4) {
				i++;
				strCien = herramientas.Constantes.STRING_VACIO;
				strDiez = herramientas.Constantes.STRING_VACIO;
				strUno = herramientas.Constantes.STRING_VACIO;
				if (numero != 0) {
					cien = (numero / 100);
					diez = (numero - (cien * 100)) / 10;
					unidades = numero - ((numero / 10) * 10);
					strCien = herramientas.Constantes.STRING_VACIO;

					switch ((int) cien) {
					case 0:
						strCien = herramientas.Constantes.STRING_VACIO;
						break;
					case 1:
						strCien = "Hundred ";
						break;
					case 2:
						strCien = "Two Hundred ";
						break;
					case 3:
						strCien = "Three Hundred ";
						break;
					case 4:
						strCien = "Four Hundred ";
						break;
					case 5:
						strCien = "Five Hundred ";
						break;
					case 6:
						strCien = "Six Hundred ";
						break;
					case 7:
						strCien = "Seven Hundred ";
						break;
					case 8:
						strCien = "Eight Hundred ";
						break;
					case 9:
						strCien = "Nine Hundred ";
						break;
					}

					strDiez = herramientas.Constantes.STRING_VACIO;

					switch ((int) diez) {
					case 0:
						strDiez = herramientas.Constantes.STRING_VACIO;
						break;
					case 1:
						switch ((int) unidades) {
						case 1:
							strDiez = "Eleven ";
							break;
						case 2:
							strDiez = "Twelve ";
							break;
						case 3:
							strDiez = "Thirteen ";
							break;
						case 4:
							strDiez = "Fourteen ";
							break;
						case 5:
							strDiez = "Fifteen ";
							break;
						case 6:
							strDiez = "Sixteen ";
							break;
						case 7:
							strDiez = "Seventeen ";
							break;
						case 8:
							strDiez = "Eighteen ";
							break;
						case 9:
							strDiez = "Nineteen ";
							break;
						}
						break;
					case 2:
						strDiez = "Twenty";
						break;
					case 3:
						strDiez = "Thirty";
						break;
					case 4:
						strDiez = "Forty";
						break;
					case 5:
						strDiez = "Fifty";
						break;
					case 6:
						strDiez = "Sixty";
						break;
					case 7:
						strDiez = "Seventy";
						break;
					case 8:
						strDiez = "Eighty";
						break;
					case 9:
						strDiez = "Ninety";
						break;
					}

					if (diez > 2 && unidades != 0)
						strDiez = strDiez + "- ";
					else
						strDiez = strDiez + " ";

					strUno = herramientas.Constantes.STRING_VACIO;

					if (diez == 1) {
						strUno = herramientas.Constantes.STRING_VACIO;
					} else {

						switch ((int) unidades) {
						case 0:
							strUno = herramientas.Constantes.STRING_VACIO;
							break;
						case 1:
							strUno = "One ";
							break;
						case 2:
							strUno = "Two ";
							break;
						case 3:
							strUno = "Three ";
							break;
						case 4:
							strUno = "Four ";
							break;
						case 5:
							strUno = "Five ";
							break;
						case 6:
							strUno = "Six ";
							break;
						case 7:
							strUno = "Seven ";
							break;
						case 8:
							strUno = "Eight ";
							break;
						case 9:
							strUno = "Nine ";
							break;
						}
					}
				}

				switch (i) {
				case 1:
					if (numero > 0) {
						cantidadEnLetras = strCien + strDiez + strUno + "Thousand";
						milesMill = 1;
					}

					cantidad = cantidad - (numero * 1000000000);
					numero = (long) cantidad / 1000000;
					break;
				case 2:
					if (numero > 0 || milesMill == 1) {
						cantidadEnLetras = cantidadEnLetras + strCien + strDiez + strUno + "Milion";
					}
					cantidad = cantidad - (numero * 1000000);
					numero = (long) (cantidad / 1000);
					break;
				case 3:
					if (numero > 0) {
						cantidadEnLetras = cantidadEnLetras + strCien + strDiez + strUno + "Thousand ";
					}
					cantidad = cantidad - (numero * 1000);
					numero = (long) (cantidad / 1);
					break;
				case 4:
					cantidadEnLetras = cantidadEnLetras + strCien + strDiez + strUno + monedaDescrpcion + " ";					
					cantidad = cantidad - numero;
					centavos = (long) (Math.round((cantidad * 100) * Math.pow(10, 0)) / Math.pow(10, 0));
					break;
				}
			}
			
			
			
				if (centavos == 0) {
					cantidadEnLetras = cantidadEnLetras + "00/100 " + simboloMoneda;
				} else {
					cantidadEnLetras = cantidadEnLetras
							+ Utileria.completaCerosIzquierda((long) centavos, 2) + "/100 " + simboloMoneda;
				}
				
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		cantidadEnLetras += herramientas.Constantes.PARENTESIS_CIERRA + herramientas.Constantes.ASTERISCO +
							herramientas.Constantes.ASTERISCO + herramientas.Constantes.ASTERISCO;

		return cantidadEnLetras;
	}
	
	/* Metodos para generar espacios en blanco a la izquierda*/
	public static String agregaEspacioIzq(String cadenaStr, int longitud){
		String cadenaCompuesta = String.format("%1$#" + longitud + "s", cadenaStr);
		
		return cadenaCompuesta;
	}
	
	/* Metodos para generar espacios en blanco a la Derecha*/
	public static String agregaEspacioDer(String cadenaStr, int longitud){
		String cadenaCompuesta = String.format("%1$-" + longitud + "s", cadenaStr);
		
		return cadenaCompuesta; 
	}
	
	public static String formatoFecha(String fechaStr){
		String fecha = "";
		String[] arreglo = fechaStr.split("-");
		
		fecha = arreglo[2] + arreglo[1] + arreglo[0];
		
		return fecha;
	}
}
