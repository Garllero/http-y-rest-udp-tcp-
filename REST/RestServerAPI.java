package xai.rest.jettyserver.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;

/**
 * @author Joan-Manuel Marques
 *
 */
@Path("/cil")
public class RestServerAPI {

	private static final double PI = 3.1416;
	
	/**
	 * Calculates cilinder's surface
	 *
	 * @param num_1
	 * @param num_2
	 * @return
	 */
	@GET
	@Path("/sup/{num_1}/{num_2}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sup(@PathParam("num_1") float rad, @PathParam("num_2") float alt) {
		LSimLogger.log(Level.INFO, "sup");
		LSimLogger.log(Level.INFO, "rad (num_1): "+rad);
		LSimLogger.log(Level.INFO, "alt (num_2): "+alt);

		double result = Double.MIN_VALUE;

		/* Calculate surface and return it */
		result = (2 * PI * rad) * (rad + alt);
		//Imprimir y retornar respuesta
		LSimLogger.log(Level.INFO, "sup: " + (new Double(result)).toString());
		return (new Double(result)).toString();
		
	}

	/**
	 * Calculates cilinder's perimeter
	 *
	 * @param num_1
	 * @param num_2
	 * @return
	 */
	@GET
	@Path("/per/{num_1}/{num_2}")
	@Produces(MediaType.APPLICATION_JSON)
	public String per(@PathParam("num_1") float rad, @PathParam("num_2") float alt) {

		LSimLogger.log(Level.INFO, "per");
		LSimLogger.log(Level.INFO, "rad (num_1): "+rad);
		LSimLogger.log(Level.INFO, "alt (num_2): "+alt);

		double result = Integer.MIN_VALUE;

		/* Calculate result & create json value with GSON library */

		result = (2*alt)+(4*PI*rad);
		//Imprimir y retornar respuesta
		LSimLogger.log(Level.INFO, "per: "+(new Double(result)).toString());
		return ""+ result;
	}

	/**
	 * Calculates cilinder's volume
	 *
	 * @param num_1
	 * @param num_2
	 * @return a json object containing the parameters and the result
	 */
	@GET
	@Path("/vol/{num_1}/{num_2}")
	@Produces(MediaType.APPLICATION_JSON)
	public String vol (@PathParam("num_1") float rad, @PathParam("num_2") float alt) {
		LSimLogger.log(Level.INFO, "vol");
		LSimLogger.log(Level.INFO, "rad (num_1): "+rad);
		LSimLogger.log(Level.INFO, "alt (num_2): "+alt);

		/* Call Volum class and send json value with GSON library */
		
		Object result = new Volum(rad,alt);
		//Imprimir y retornar respuesta
		LSimLogger.log(Level.INFO, "Vol: "+result.toString());
		String parse = new Gson().toJson(result);
		return parse;
	}
}
