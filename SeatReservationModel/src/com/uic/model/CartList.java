package com.uic.model;

import java.io.Serializable;
import java.util.List;

public class CartList implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 5276555593082784579L;
private List<Integer> cartList;
String user ;
public List<Integer> getCartList() {
	return cartList;
}
public void setCartList(List<Integer> cartList) {
	this.cartList = cartList;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
}
