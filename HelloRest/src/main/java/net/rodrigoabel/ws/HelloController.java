package net.rodrigoabel.ws;



import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.rodrigoabel.response.HelloResponse;



@RestController
public class HelloController {

	@RequestMapping("/hello")
	@ResponseBody
	public HelloResponse hello(@RequestBody HelloResponse recibido) throws IOException {
				
		
		System.out.println("logs =" + recibido.getId());
		int name = recibido.getId();
		
		 File file = new File("OperacionesBinarias.bin");
	     RandomAccessFile data = new RandomAccessFile(file, "r");
	     
	    //int name = recivido.getId();
	     byte[] datos = new byte[9];
	     
	     int operacion = -10;
	     for (int i = 0; i < data.length(); i=i+9) {
	     
	    	 data.seek(i);
	    	 data.read(datos);
	    	 
			if (name == ID(bytesToHex(datos)).intValue()) {  
	    	  operacion = OP(bytesToHex(datos)).intValue();
	    	  break;
			}
	     }
	     if (operacion == -10) {
	    	 recibido.setEstado("NOK");
	    	 recibido.setMensaje("Id no encontrada");
	     }
	     else { 
	    	 recibido.setEstado("OK");
	    	 recibido.setMensaje("Id encontrada");
	     }
	     
	    data.close();
	    recibido.setOperacion(operacion);
	    //return respuesta +  "Numero buscado = " + name + "---- Hex = " + Integer.toHexString(name) + " ---- ID = " +ID(bytesToHex(datos))  + " ---- OP = " +OP(bytesToHex(datos));
	    //return respuesta + "Registro = " + name + " Opreación =" + operacion;
	   // HelloResponse  helloResponse = new HelloResponse(name, operacion);
		return recibido;
	     	   	     	     	     
	          
	}
	
	@Value("$app.name:Default Demo app")
	private String appName;
	
	@GetMapping("/get")
	public HelloResponse getRespuesta() {
		HelloResponse  helloResponse = new HelloResponse(200, 1725);
		return helloResponse;
	}
	
	
	
	
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static BigInteger ID(String total) {
		String id1 = total.substring(0,14);
		//return id1;
		int largo = id1.length();
		char[] temp = new char[largo];
		for (int i = 0; i < largo; i=i+2) {
            temp[largo - i - 1] = id1.charAt(i+1);
            temp[largo - i - 2] = id1.charAt(i);
        }
		
		String hexValue = String.copyValueOf(temp);
		BigInteger result = new BigInteger(hexValue, 16);
		return result;
	}
	
	public static BigInteger OP(String total) {
		String id2 = total.substring(14,18);
		//return id1;
		int largo = id2.length();
		char[] temp = new char[largo];
		for (int i = 0; i < largo; i=i+2) {
            temp[largo - i - 1] = id2.charAt(i+1);
            temp[largo - i - 2] = id2.charAt(i);
        }
		String hexValue = String.copyValueOf(temp);
		BigInteger result = new BigInteger(hexValue, 16);
		return result;
	}
	
	
	
	
}
