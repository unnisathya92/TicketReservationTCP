package edu.uic.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

import edu.uic.controller.SeatReservationController;

public class SeatReservationServer {
	SeatReservationController controller;

	public static void main(String[] argv) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
			System.out.println("Press 1 to start to server .");
			System.out.println("Press 2 to retreive registered users and their seats.");
			System.out.println("Press 3 to retreive registered seats for a selected user.");

			String input = br.readLine();
			int inp = Integer.parseInt(input);
			switch (inp) {
			case 1:
				new SeatReservationServer().startServer();
				break;

			case 2:
				new SeatReservationController().printAllUsersAndTheirSeats();
				break;
			case 3: {
				System.out.println("Enter emailID to retrieve selected seats of that user.");
				String user = br.readLine();
				new SeatReservationController().printUsersSeats(user);
			}
				break;

			default:
				new SeatReservationServer().startServer();
				break;
			}
			System.out.println();
		

	}

	private void startServer() throws IOException {
		@SuppressWarnings("resource")
		ServerSocket s = new ServerSocket(1050);
		System.out.println("Server started");
		 for (int i=0;i<10;i++){
		SeatReservationServerStub stub = new SeatReservationServerStub(s);
		Thread t = new Thread(stub);
		t.start();
		}
	}

	
}