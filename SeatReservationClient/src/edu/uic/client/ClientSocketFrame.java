package edu.uic.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import com.uic.model.CartList;
import com.uic.model.ReservedSeats;
import com.uic.model.User;

import edu.uic.windows.LoginWindow;
import edu.uic.windows.RegisterWindow;
import edu.uic.windows.SeatSelection;

public class ClientSocketFrame {
	static Properties prop = new Properties();
	static InputStream input = null;
	static {
		try {
			input = new FileInputStream("application.properties");
			prop.load(input);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());	
		} catch (IOException e) {
			System.err.println(e.getMessage());	
		}
	}
	public static List<Integer> otherCarts ;
	public void login(User user,LoginWindow loginWindow) {
		Socket socket = null;
		ObjectOutputStream out = null;
		try {
			socket = new Socket(prop.getProperty("serverurl"),  Integer.parseInt(prop.getProperty("serverport")));
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(user);
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			ReservedSeats rs = (ReservedSeats)input.readObject();
			if(!rs.isUserAvailable())
				loginWindow.txtS.setText("User is not registered");
			else if(!rs.isValidAuthentication())
				loginWindow.txtS.setText("Username/Password wrong. Please check and re-login");
			else{
			SeatSelection sr = new SeatSelection(rs);
			sr.launch();
			loginWindow.dispose();
			}
			input.close();
		}catch (java.net.ConnectException e) {
			loginWindow.txtS.setText("Unable to connect to the server");
			System.err.println(e.getMessage());	
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());	
		} finally {
			executeCommon(socket, out);

		}
	}
	
	private void executeCommon(Socket socket, ObjectOutputStream out) {
		try {
			out.flush();
			out.close();
			socket.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());	
		}
	}
	public void register(User user, RegisterWindow registerWindow) {
		Socket socket = null;
		ObjectOutputStream out = null;
		try {
			socket = new Socket(prop.getProperty("serverurl"), Integer.parseInt(prop.getProperty("serverport")));
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(user);
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			ReservedSeats rs = (ReservedSeats)input.readObject();
			if(!rs.isDuplicate())
			{LoginWindow loginWindow = new LoginWindow();
			loginWindow.launch();
			registerWindow.dispose();
			input.close();}
			else{
				registerWindow.txtS.setText("User Already exists");
			}
		}catch (java.net.ConnectException e) {
			registerWindow.txtS.setText("Unable to connect to the server");
			System.err.println(e.getMessage());		
		} catch (IOException e) {
			System.err.println(e.getMessage());	
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());	
		} finally {
			executeCommon(socket, out);

		}
	}

	public void seatSelection(ReservedSeats rs, SeatSelection seatSelection) {
		Socket socket = null;
		ObjectOutputStream out = null;
		try {
			socket = new Socket(prop.getProperty("serverurl"), Integer.parseInt(prop.getProperty("serverport")));
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(rs);
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			ReservedSeats rs1 = (ReservedSeats)input.readObject();
			SeatSelection sr = new SeatSelection(rs1);
			sr.launch();
			input.close();
		}catch (java.net.ConnectException e) {
			seatSelection.txtCart.setText("Unable to connect to the server");
			System.err.println(e.getMessage());	
		} catch (IOException e) {
			System.err.println(e.getMessage());	
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());	
		} finally {
			executeCommon(socket, out);

		}
	}

	public static List cartUpdate(CartList c) {
		
		Socket socket = null;
		ObjectOutputStream out = null;
		CartList carts =null;
		try {
			socket = new Socket(prop.getProperty("serverurl"), Integer.parseInt(prop.getProperty("serverport")));
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(c);
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			 carts = (CartList)input.readObject();
			otherCarts = carts.getCartList();
		}catch (java.net.ConnectException e) {
			e.printStackTrace();

			System.err.println(e.getMessage());	
		} catch (IOException e) {
			e.printStackTrace();

			System.err.println(e.getMessage());	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());	
		} finally {
			try {
				out.flush();
				out.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();

				System.err.println(e.getMessage());	
			}
		}
		return otherCarts;
	}
	}