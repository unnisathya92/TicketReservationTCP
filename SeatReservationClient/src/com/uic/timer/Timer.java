package com.uic.timer;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.uic.model.CartList;
import com.uic.model.ReservedSeats;

import edu.uic.client.ClientSocketFrame;
import edu.uic.windows.LoginWindow;
import edu.uic.windows.SeatSelection;

public class Timer implements Runnable {
	SeatSelection seatSelection;
	ReservedSeats rs;

	public Timer(SeatSelection seatSelection, ReservedSeats rs) {
		this.seatSelection = seatSelection;
		this.rs = rs;
	}
List<JButton> jButtons = new ArrayList<JButton>();
	@Override
	public void run() {
		int i = 20;
		while (i >= 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			SeatSelection.txtTimer.setText("Remaining Time " + i + " seconds");
			i--;
			update();
		}
		clear();
	}

	private void clear() {
		CartList m = new CartList();
		m.setUser(SeatSelection.myCart.getUser());
		m.setCartList(new ArrayList<Integer>());
		SeatSelection.txtCart.setText("");
		SeatSelection.txtTimer.setText("");
		SeatSelection.cartList = new  ArrayList<Integer>();
		SeatSelection.ok("Sorry!! Your Session has expired ");
		List cartList = ClientSocketFrame.cartUpdate(m);
		SeatSelection.isFirstTime = true;
		for (JButton c : jButtons) {
			Icon existing = new ImageIcon("existing.jpg");
			c.setIcon(existing);
		}
	}

	void update() {
		List cartList = ClientSocketFrame.cartUpdate(SeatSelection.myCart);
		for (JButton c : SeatSelection.buttons) {
			if (cartList.contains(Integer.parseInt(c.getName())) || (rs != null && rs.getReservedSeats() != null
					&& rs.getReservedSeats().contains(Integer.parseInt(c.getName())))) {
				Icon selected = new ImageIcon("selected.jpg");
				c.setIcon(selected);
				jButtons.add(c);

			} else if (SeatSelection.cartList != null && !SeatSelection.cartList.contains(Integer.parseInt(c.getName()))) {
				Icon existing = new ImageIcon("existing.jpg");
				c.setIcon(existing);
			}
		}

	}

}
