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

import controller.Controller;

public class HssdrServer {
    private ServerSocket server;
    private int port = 7777;
	
    public HssdrServer() {
        try {
            server = new ServerSocket(port); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleConnection( MessageHandler handler) {

        //
        // The server do a loop here to accept all connection initiated by the
        // client application.
        //
        while (true) {
            try {
            	System.out.println("Waiting for client message...");
                Socket socket = server.accept();
                System.out.println("Incoming connection");
//                new ConnectionHandler(socket);
                new SketchupConnectionHandler(socket, handler);
            } catch (IOException e) {
                e.printStackTrace();
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
				System.out.println("server got: "+inputLine);
				if (inputLine.equals("END_OF_MESSAGE")){
					System.out.println("got exit signal, exiting...");
					break;
				}
				message+=inputLine;
			}
			outputLine = handler.handle(message);
			
			out.println(outputLine);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
