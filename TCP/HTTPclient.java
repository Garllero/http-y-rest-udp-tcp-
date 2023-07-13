/*

* Copyright (c) Joan-Manuel Marques 2013. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This file is part of the practical assignment of Distributed Systems course.
*
* This code is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This code is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this code.  If not, see <http://www.gnu.org/licenses/>.
*/

package tcp.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;


/**
 * @author Joan-Manuel Marques
 *
 */

public class HTTPclient {

	public HTTPclient() {
	}
			
	public String get(String http_server_address, int http_server_port){
		LSimLogger.log(Level.INFO, "inici HTTPclient.get ");
		LSimLogger.log(Level.INFO, "HTTP server_address: " + http_server_address);
		LSimLogger.log(Level.INFO, "HTTP server_port: " + http_server_port);

		
		/* ENVIAR LA PETICIÓ I REBRE LA RESPOSTA / SEND REQUEST AND RECEIVE THE ANSWER / ENVIAR LA PETICIÓN Y RECIBIR LA RESPUESTA */
		
		//declaración de peticion
		String peticio ="";
		peticio = "GET /xai/xai.html HTTP/1.0\r\n";
		
		//creación del string para la respuesta
		String resposta = "";
		
		try {
		
			//cliente conecta con servidor
			Socket socket = new Socket(http_server_address,http_server_port);
			
			//Se envia la petición GET
			PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
			System.out.println(peticio);
			output.println(peticio);
			
			//llega la respuesta del servidor y se convierte en string para visualizarlo
			InputStreamReader reader_server = new InputStreamReader(socket.getInputStream());
			
			for (int c = reader_server.read();c!=-1; c = reader_server.read()) {
				resposta = resposta +((char)c);
			}
			
			//Añadimos los caracteres CRLF
			resposta = resposta + "\r\n";
			
			//cerramos el socket
			socket.close();
			
			
		} catch(UnknownHostException e) {
			//No se reconoce la Ip
			System.err.println("Remote host"+ http_server_address +"cannot be resolved");
			
		}catch(SecurityException e) {
			//error de seguridad
			System.err.println("Firewall is blocking the connection");
			
			
		}catch(IllegalArgumentException e) {
			//Incorrect port
			System.err.println("Incorrect port"+ http_server_port+"number");
			
		}catch(IOException e) {
			//IO error
			System.err.println("IO error");
		}
		return resposta;
		
	}
}
