package server;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.ClassNotFoundException;
import java.lang.Runnable;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import util.Logger;

import controller.Controller;

public class HssdrServer {
    private ServerSocket server;
    private int port = 7777;
	
    public HssdrServer() {
        try {
            server = new ServerSocket(port); 
        } catch (IOException e) {
        	Logger.LOGGER.error("", e); 
        }
    }


    public void handleConnection( MessageHandler handler) {

        //
        // The server do a loop here to accept all connection initiated by the
        // client application.
        //
        while (true) {
            try {
            	Logger.LOGGER.debug("Waiting for client message...");
                Socket socket = server.accept();
                Logger.LOGGER.debug("Incoming connection");
//                new ConnectionHandler(socket);
                new SketchupConnectionHandler(socket, handler);
            } catch (IOException e) {
            	Logger.LOGGER.error("", e); 
            }
        }
    }
}


class SketchupConnectionHandler implements Runnable {
    private Socket socket;
    MessageHandler handler;


    public SketchupConnectionHandler(Socket socket, MessageHandler handler) {
        this.socket = socket;
    	this.handler=handler;

        Thread t = new Thread(this);
        t.start();
    }

	public void run() {
		PrintWriter out=null;
		BufferedReader in=null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			  in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String inputLine, outputLine;

			String message="";

			while ((inputLine = in.readLine()) != null) {
				Logger.LOGGER.debug("server got: "+inputLine);
				if (inputLine.equals("END_OF_MESSAGE")){
					Logger.LOGGER.debug("got exit signal, exiting...");
					break;
				}
				message+=inputLine;
			}
			outputLine = handler.handle(message);
			
			out.println(outputLine);
			
		} catch (IOException e) {
			Logger.LOGGER.error("", e); 
		} finally{
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				Logger.LOGGER.error("", e); 
			}
			try {
				socket.close();
			} catch (IOException e) {
				Logger.LOGGER.error("", e); 
			}
		}
		
	}
}
