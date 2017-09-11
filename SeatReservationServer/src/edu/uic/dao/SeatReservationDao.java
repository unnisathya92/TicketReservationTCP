package edu.uic.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatReservationDao {

	public static final String mysqlJdbcDriver = "com.mysql.jdbc.Driver";
	private java.sql.Connection connection;

	public SeatReservationDao(String url, String user, String pwd) {
		try {
			Class.forName(mysqlJdbcDriver);

			connection = DriverManager.getConnection(url, user, pwd);

		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());	
		} catch (SQLException e) {
			System.err.println(e.getMessage());	
		}
	}

	public int login(String eMailID, String pwd) {
		int returnValue = -1;
		try {
			java.sql.Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = stmt.executeQuery("select * from user where emailid ='" + eMailID+"'");
			while (resultSet.next()) {
				if (resultSet.getString(4).equals(pwd)) {
					returnValue = 1;
				} else
					returnValue = 0;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());	
			returnValue = 2;
		}
		return returnValue;
	}

	public List<Integer> fetchAllReservedSeats(String eMailID) {
		List<Integer> rSeats = new ArrayList<Integer>();
		try {
			java.sql.Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = stmt.executeQuery("select seatno from reservedseats where emailid <>'" + eMailID+"'");
			while (resultSet.next()) {
				rSeats.add(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());	
		}
		return rSeats;
	}

	public List<String> fetchAllReservedSeatsandUsers() {
		List<String> rSeats = new ArrayList<String>();
		try {
			java.sql.Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = stmt.executeQuery("select * from reservedseats");
			while (resultSet.next()) {
				rSeats.add(resultSet.getString(1)+"::::"+resultSet.getInt(2));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());	
		}
		return rSeats;
	}
	
	public List<Integer> fetchMyReservedSeats(String eMailID) {
		List<Integer> rSeats = new ArrayList<Integer>();
		try {
			java.sql.Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet resultSet = stmt.executeQuery("select seatno from reservedseats where emailid ='" + eMailID+"'");
			while (resultSet.next()) {
				rSeats.add(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());	
		}
		return rSeats;
	}

	public boolean register(String getfName, String getlName, String geteMailID, String password) {
		
		Boolean isDuplicate = true;
		String sql = "INSERT INTO user VALUES ('" + getfName + "', '" + getlName + "', '" + geteMailID + "', '"
				+ password + "')";
		java.sql.Statement stmt;
		try {
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			stmt.executeUpdate(sql);
			isDuplicate = false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());	
			isDuplicate = true;
		}
		return isDuplicate;
	}
	public void addSeats(String geteMailID,List<Integer> seats) {
		
		java.sql.Statement stmt;
		try {
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			for(int i : seats){
				try {
				String sql = "INSERT INTO reservedseats VALUES ('" + geteMailID + "', '" + i + "')";
				stmt.executeUpdate(sql);
				}
				catch (SQLException e) {
					System.err.println(e.getMessage());	
				}
				}
		} catch (SQLException e) {
			System.err.println(e.getMessage());	
		}
	}

	public void deleteSeats(List<Integer> toDeleteSeats) {
		java.sql.Statement stmt;
		try {
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			for(int i : toDeleteSeats){
				String sql = "DELETE FROM reservedseats where seatno = "+i;
				stmt.executeUpdate(sql);
				}
		} catch (SQLException e) {
			System.err.println(e.getMessage());	
		}
			}


}
