package server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public static void main(String[] args) {
    	HssdrServer example = new HssdrServer();
        example.handleConnection();
    }

    public void handleConnection() {

        //
        // The server do a loop here to accept all connection initiated by the
        // client application.
        //
        while (true) {
            try {
            	System.out.println("Waiting for client message...");
                Socket socket = server.accept();
                new ConnectionHandler(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConnectionHandler implements Runnable {
    private Socket socket;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;

        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        try
        {
            //
            // Read a message sent by client application
            //
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            
            //
            // Send a response information to the client application
            //
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Hi. send me data.");

            
            if ("SKETCHUP_DATA".equals(message)){
            	
            	////TODO read all data into rooms list 
            	
            	//TODO create structure and voc from rooms list
            	 
//            	Controller.checkLayout(structure, vocabulary, fname);
            	
            	  
//            	Object [] parsingRes =  checkLayout(structure, vocabulary, fname);
//            	
//            	String  resultMessage = (String)parsingRes[0] ;
//            	ArrayList<String> roomsToHighlight =(ArrayList<String>)parsingRes[1];
            	
            	//TODOsend response roomsToHighlight
            	//TODOsend response resultMessage
            	
            }else if  ("REVIT_DATA".equals(message)){
            	
            }

//            graph = xmlhandler.processxml
//            Controller controller = new Controller();
//            res =controller.chechgraph
//            oos.writeObject(res);
            
           
            ois.close();
            oos.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
