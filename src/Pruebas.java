import java.lang.Runtime;
import java.io.*;

public class Pruebas {

	
	public static void main(String args[]){
		  try{
		   Runtime.getRuntime().exec("wscript C:\\GeneraXML.vbs");
		  }catch(Exception ex){
		   ex.printStackTrace(System.err);
		  }
		 }
}
