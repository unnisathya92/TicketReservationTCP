package com.uic.model;

import java.io.Serializable;
import java.util.List;

public class ReservedSeats implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7356032705505390455L;
	private User user;
	private boolean isUserAvailable;
	List<Integer> reservedSeats;
	List<Integer> mySeats;
	private boolean isValidAuthentication;
	private boolean isRegisteredSuccessfully;
	private boolean isDuplicate;
	List<Integer> toDeleteSeats;
	List<Integer> othersCartSeats;

	public List<Integer> getOthersCartSeats() {
		return othersCartSeats;
	}

	public void setOthersCartSeats(List<Integer> othersCartSeats) {
		this.othersCartSeats = othersCartSeats;
	}

	public List<Integer> getToDeleteSeats() {
		return toDeleteSeats;
	}

	public void setToDeleteSeats(List<Integer> toDeleteSeats) {
		this.toDeleteSeats = toDeleteSeats;
	}

	public boolean isDuplicate() {
		return isDuplicate;
	}

	public void setDuplicate(boolean isDuplicate) {
		this.isDuplicate = isDuplicate;
	}

	List<Integer> selectedSeats;

	public List<Integer> getSelectedSeats() {
		return selectedSeats;
	}

	public void setSelectedSeats(List<Integer> selectedSeats) {
		this.selectedSeats = selectedSeats;
	}

	public boolean isRegisteredSuccessfully() {
		return isRegisteredSuccessfully;
	}

	public void setRegisteredSuccessfully(boolean isRegisteredSuccessfully) {
		this.isRegisteredSuccessfully = isRegisteredSuccessfully;
	}

	public boolean isValidAuthentication() {
		return isValidAuthentication;
	}

	public void setValidAuthentication(boolean isValidAuthentication) {
		this.isValidAuthentication = isValidAuthentication;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Integer> getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(List<Integer> reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public boolean isUserAvailable() {
		return isUserAvailable;
	}

	public void setUserAvailable(boolean isUserAvailable) {
		this.isUserAvailable = isUserAvailable;
	}

	public List<Integer> getMySeats() {
		return mySeats;
	}

	public void setMySeats(List<Integer> mySeats) {
		this.mySeats = mySeats;
	}

}
