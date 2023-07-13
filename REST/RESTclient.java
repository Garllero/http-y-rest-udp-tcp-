package xai.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;
//import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
//import lsim.library.api.LSimLogger;
import xai.rest.jettyserver.api.Volum;

/**
 * @author Joan-Manuel Marques
 *
 */



public class RESTclient {

	//Creamos una función auxiliar para no repetir código
	private <A> A request_aux (String request, MediaType Mediatype_Type, Class <A> returned_Type) {
		
		//Creamos el cliente 
		Client newClient = ClientBuilder.newClient();
		String client;
		client = newClient.target(request).request().accept(Mediatype_Type).get(String.class);
		
		//Cerramos cliente
		newClient.close();
		
		//parse de respuesta
		return new Gson().fromJson(client, returned_Type);
	}

	public double sup(String address, int port, float value1, float value2) {
		LSimLogger.log(Level.INFO, "http://"+address+":"+port+"/cil/sup/"+value1+"/"+value2);
		LSimLogger.log(Level.INFO, "media type: text/plain");
		
		/* COMPLETAR */
		//Creamos solicitud, la mostramos y devolvemos el resultado
		String URL;
		URL = "http://"+address+":"+port+"/cil/sup/"+value1+"/"+value2;
		LSimLogger.log(Level.INFO, "Requested URL: "+URL);
		
		return request_aux(URL,MediaType.TEXT_PLAIN_TYPE, Double.class);
		
	
	}
	
	public double per(String address, int port, float value1, float value2) {
		LSimLogger.log(Level.INFO, "http://"+address+":"+port+"/cil/per/"+value1+"/"+value2);
		LSimLogger.log(Level.INFO, "media type: application/json");

		/* COMPLETAR */
		String URL;
		URL = "http://"+address+":"+port+"/cil/per/"+value1+"/"+value2;
		LSimLogger.log(Level.INFO, "Requested URL: "+URL);
		
		return request_aux(URL,MediaType.APPLICATION_JSON_TYPE, Double.class);
		
		
	}

	public Volum vol(String address, int port, float value1, float value2) {
		LSimLogger.log(Level.INFO, "http://"+address+":"+port+"/cil/vol/"+value1+"/"+value2);
		LSimLogger.log(Level.INFO, "media type: application/json");

		/* COMPLETAR */
		String URL;
		URL = "http://"+address+":"+port+"/cil/vol/"+value1+"/"+value2;
		LSimLogger.log(Level.INFO, "Requested URL: "+URL);
		
		return request_aux(URL,MediaType.APPLICATION_JSON_TYPE, Volum.class);
	
	}
}
