package edu.uic.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.uic.model.ReservedSeats;
import com.uic.model.User;

import edu.uic.dao.SeatReservationDao;

public class SeatReservationController {
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
	SeatReservationDao dao = new SeatReservationDao(prop.getProperty("dburl"), prop.getProperty("dbuser"),
			prop.getProperty("dbpwd"));

	public ReservedSeats loginandFetchSeats(User user) {

		int returnValue = dao.login(user.geteMailID(), user.getPassword());
		ReservedSeats rSeats = new ReservedSeats();
		if (returnValue == 1)// correct Login
		{
			getReservedSeats(user, rSeats);

		} else if (returnValue == 2)// Wrong Password
		{
			rSeats.setValidAuthentication(false);
			rSeats.setUserAvailable(true);
		} else if (returnValue == -1)// no User
		{
			rSeats.setValidAuthentication(false);
			rSeats.setUserAvailable(false);
		}
		rSeats.setUser(user);
		return rSeats;

	}

	public void printAllUsersAndTheirSeats() {

		List<String> allUsersAndTheirSeats = dao.fetchAllReservedSeatsandUsers();
		if (allUsersAndTheirSeats.isEmpty()) {
			System.out.println("No users registered !!!");
		} else {
			for (String users : allUsersAndTheirSeats) {

				System.out.println(users);

			}
		}
	}

	public ReservedSeats getReservedSeats(User user, ReservedSeats rSeats) {
		List<Integer> reservedSeats = dao.fetchAllReservedSeats(user.geteMailID());
		List<Integer> mySeats = dao.fetchMyReservedSeats(user.geteMailID());
		rSeats.setMySeats(mySeats);
		rSeats.setReservedSeats(reservedSeats);
		rSeats.setValidAuthentication(true);
		rSeats.setUserAvailable(true);
		return rSeats;
	}

	public boolean register(User user) {
		return dao.register(user.getfName(), user.getlName(), user.geteMailID(), user.getPassword());
	}

	public void addSeats(ReservedSeats rSeats) {
		if (rSeats.getToDeleteSeats() != null && !rSeats.getToDeleteSeats().isEmpty()) {
			dao.deleteSeats(rSeats.getToDeleteSeats());
		}
		dao.addSeats(rSeats.getUser().geteMailID(), rSeats.getMySeats());

	}

	public void printUsersSeats(String user) {
		List<Integer> allUsersAndTheirSeats = dao.fetchMyReservedSeats(user);
		if (allUsersAndTheirSeats.isEmpty()) {
			System.out.println(user + " is not registered !!!");
		} else {
			System.out.println(user);
			System.out.println("Seat Number ");
			for (Integer seats : allUsersAndTheirSeats) {
				System.out.print(seats + "      ");
			}
		}
	}
}
