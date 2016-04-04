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

public class HssdrServerTest {
    private ServerSocket server;
    private int port = 7777;

    public HssdrServerTest() {
        try {
            server = new ServerSocket(port); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	HssdrServerTest example = new HssdrServerTest();
        example.handleConnection();
    }

    public void handleConnection() {

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
                new SketchupConnectionHandlerTest(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


class SketchupConnectionHandlerTest implements Runnable {
    private Socket socket;

    public SketchupConnectionHandlerTest(Socket socket) {
        this.socket = socket;

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

			// initiate conversation with client
			outputLine = "hello1";
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				Logger.LOGGER.debug("server got: "+inputLine);
//				outputLine = "ok";
//				out.println(outputLine);
				if (inputLine.equals("EXIT")){
					Logger.LOGGER.debug("got exit signal, exiting...");
					break;
				}
			}
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
