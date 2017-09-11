package edu.uic.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uic.model.CartList;
import com.uic.model.ReservedSeats;
import com.uic.model.User;

import edu.uic.controller.SeatReservationController;

public class SeatReservationServerStub implements Runnable {
	SeatReservationController controller;
	private static Map<String, List<Integer>> cartList = new HashMap<String, List<Integer>>();

	ServerSocket s = null;

	public SeatReservationServerStub(ServerSocket s) {
		this.s = s;
	}

	@Override
	public void run() {

		while (true) {
			Socket socket;
			try {
				socket = s.accept();

				System.out.println("server connected");
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

				ObjectOutputStream output;

				output = new ObjectOutputStream(socket.getOutputStream());

				processMethodToCall(socket, input, output);
				input.close();
				output.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void processMethodToCall(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
		Object obj = null;
		try {
			ReservedSeats rSeats = new ReservedSeats();
			boolean isRegisterLoginFlag = true;

			try {
				obj = input.readObject();
				User user = (User) obj;
				controller = new SeatReservationController();
				if (user != null && (user.getfName() == null || user.getfName().equals(""))) {
					rSeats = controller.loginandFetchSeats(user);
					rSeats.setOthersCartSeats(returnOthersSeats(user.geteMailID()));
				} else if (user != null) {
					boolean isDuplicate = controller.register(user);
					rSeats.setUser(user);
					rSeats.setDuplicate(isDuplicate);
				}
				output.writeObject(rSeats);
			} catch (ClassCastException e) {
				System.err.println(e.getMessage());
				isRegisterLoginFlag = false;
			}
			if (!isRegisterLoginFlag) {
				try {
					rSeats = (ReservedSeats) obj;
					controller = new SeatReservationController();
					controller.addSeats(rSeats);
					rSeats = controller.getReservedSeats(rSeats.getUser(), rSeats);
					output.writeObject(rSeats);
				} catch (ClassCastException e) {
					processInfoInCarts(obj, output);
					System.err.println(e.getMessage());
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void processInfoInCarts(Object obj, ObjectOutputStream output) throws IOException {
		CartList c = (CartList) obj;
		cartList.put(c.getUser(), c.getCartList());
		List<Integer> othersCartSeats = returnOthersSeats(c.getUser());
		c.setCartList(othersCartSeats);
		output.writeObject(c);
	}

	private List<Integer> returnOthersSeats(String userName) {
		List<Integer> othersCartSeats = new ArrayList<Integer>();
		
		for (String user : cartList.keySet()) {
			if (!user.equals(userName)) {
				othersCartSeats.addAll(cartList.get(user));
			}
		}

		return othersCartSeats;
	}

}