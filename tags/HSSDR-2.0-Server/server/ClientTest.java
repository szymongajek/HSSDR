package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTest {
	
	public static void main(String[] args) {

		InetAddress host;
		try {
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
			return;
		}
		Socket socket;
		try {
			socket = new Socket(host.getHostName(), 7777);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		PrintWriter out = null;
		BufferedReader in = null;
		String fromServer, fromUser;

		BufferedReader userIn = new BufferedReader(new InputStreamReader(
				System.in));

		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			while ((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if (fromServer.equals("Bye."))
					break;
				fromUser = userIn.readLine();
				if (fromUser != null) {
					System.out.println("Client: " + fromUser);
					out.println(fromUser);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				userIn.close();
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