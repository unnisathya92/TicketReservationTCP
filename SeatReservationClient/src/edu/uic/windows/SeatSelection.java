package edu.uic.windows;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.uic.model.CartList;
import com.uic.model.ReservedSeats;
import com.uic.timer.Timer;

import edu.uic.client.ClientSocketFrame;

/**
 * @author Administrator
 * @created February 6, 2017
 */
public class SeatSelection extends JFrame implements ActionListener {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	static SeatSelection theWin1;
	static Thread t;
	JPanel pnPanel0;

	JPanel pnPanel1;
	JPanel pnPanel2;
	JPanel pnPanel3;
	JPanel pnPanel4;
	JLabel lbLoginLabel;
	JLabel lbEmailLabel;
	public static JLabel timerLabel;
	public static List<JButton> buttons;
	JLabel lbPasswordLabel;
	JTextField tfEmail;
	JTextField tfPwd;
	JButton btLoginBtn;
	JButton chooseSeats;
	JButton logout;
	public static boolean isFirstTime = true;

	public static JTextArea txtCart;
	public static JTextArea txtTimer;

	/**
	 */
	Icon selectedIcon = new ImageIcon("selected.jpg");
	Icon newSelectedIcon = new ImageIcon("newSelected.jpg");
	Icon existingIcon = new ImageIcon("existing.jpg");
	public static List<Integer> cartList = new ArrayList<Integer>();
	public static CartList myCart;
	/**
	 */
	ReservedSeats rs;

	public SeatSelection(ReservedSeats rs) {
		super("Online Ticket Reservation System");
		this.rs = rs;

	}

	public void launch() {

		pnPanel0 = new JPanel();
		pnPanel0.setBorder(BorderFactory.createTitledBorder(""));
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout(gbPanel0);
		GridBagLayout gbPanel1 = new GridBagLayout();

		pnPanel0.setLayout(gbPanel1);
		pnPanel1 = new JPanel();
		pnPanel2 = new JPanel();
		pnPanel3 = new JPanel();
		pnPanel4 = new JPanel();
		GridBagConstraints gbcPanel1 = new GridBagConstraints();

		pnPanel1.setLayout(gbPanel1);
		int j = 1, k = 0;
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < 100; i++) {
			JButton c = new JButton();
			c.setPreferredSize(new Dimension(25, 22));
			c.setIcon(existingIcon);
			c.setName(i + "");
			gbcPanel1.gridx = j;
			gbcPanel1.gridy = k;
			gbcPanel1.gridwidth = 1;
			gbcPanel1.gridheight = 1;

			gbcPanel1.weightx = 1;
			gbcPanel1.weighty = 0;
			if (rs != null && rs.getMySeats() != null) {
				for (int mySeatNumber : rs.getMySeats()) {
					if (mySeatNumber == i)
						c.setIcon(newSelectedIcon);
				}
			}
			if (rs != null && rs.getReservedSeats() != null) {
				for (int seatNumber : rs.getReservedSeats()) {
					if (seatNumber == i) {
						c.setEnabled(false);
						Icon selectedIcon = new ImageIcon("selected.jpg");
						c.setDisabledIcon(selectedIcon);
					}
				}
			}
			if (rs != null && rs.getOthersCartSeats() != null) {

				if (rs.getOthersCartSeats().contains(i)) {
					c.setEnabled(false);
					Icon selectedIcon = new ImageIcon("selected.jpg");
					c.setDisabledIcon(selectedIcon);
				}
			}

			gbPanel1.setConstraints(c, gbcPanel1);
			pnPanel1.add(c);
			if (j % 20 == 0) {
				j = 0;
				k++;
			}
			j++;
			c.setName(i + "");
			c.addActionListener(this);
			buttons.add(c);
		}

		// pnPanel2 = new JPanel();
		GridBagLayout gbPanel2 = new GridBagLayout();
		GridBagConstraints gbcPanel2 = new GridBagConstraints();
		pnPanel2.setLayout(gbPanel2);

		chooseSeats = new JButton("Choose Seats");
		chooseSeats.setPreferredSize(new Dimension(120, 20));
		chooseSeats.addActionListener(this);
		gbcPanel2.gridx = 5;
		gbcPanel2.gridy = 20;
		gbcPanel2.gridwidth = 6;
		gbcPanel2.gridheight = 2;
		gbcPanel2.weightx = 1;
		gbcPanel2.weighty = 0;
		// gbPanel2.setConstraints(chooseSeats, gbcPanel2);
		pnPanel2.add(chooseSeats, gbcPanel2);

		logout = new JButton("Logout");
		logout.setPreferredSize(new Dimension(120, 20));

		gbcPanel2.gridx = 20;
		gbcPanel2.gridy = 20;
		gbcPanel2.gridwidth = 6;
		gbcPanel2.gridheight = 2;
		gbcPanel2.weightx = 1;
		gbcPanel2.weighty = 0;
		// gbPanel2.anchor=GridBagConstraints.CENTER;
		// gbPanel2.setConstraints(logout, gbcPanel2);
		pnPanel2.add(logout, gbcPanel2);
		logout.addActionListener(this);

		GridBagLayout gbPanel3 = new GridBagLayout();
		GridBagConstraints gbcPanel3 = new GridBagConstraints();
		pnPanel3.setLayout(gbPanel3);

		txtCart = new JTextArea();
		gbcPanel3.gridx = 0;
		gbcPanel3.gridy = 10;
		gbcPanel3.gridwidth = 10;
		gbcPanel3.gridheight = 1;
		gbcPanel3.weightx = 1;
		gbcPanel3.weighty = 0;
		// gbPanel2.setConstraints(txtCart, gbcPanel2);
		pnPanel3.add(txtCart, gbcPanel3);
		txtCart.setText("error message");

		GridBagLayout gbPanel4 = new GridBagLayout();
		GridBagConstraints gbcPanel4 = new GridBagConstraints();
		pnPanel4.setLayout(gbPanel4);
		txtTimer = new JTextArea();
		gbcPanel4.gridx = 10;
		gbcPanel4.gridy = 10;
		gbcPanel4.gridwidth = 10;
		gbcPanel4.gridheight = 1;
		gbcPanel4.weightx = 1;
		gbcPanel4.weighty = 0;
		// gbPanel2.setConstraints(txtCart, gbcPanel2);
		pnPanel4.add(txtTimer, gbcPanel4);
		// txtTimer.setText("Shopping Cart");

		// constraints to relatively position the 2 panels - seat panel & Button
		// panel

		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 0;
		gbcPanel0.gridwidth = 12;
		gbcPanel0.gridheight = 6;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		// gbPanel0.setConstraints(pnPanel2, gbcPanel0);
		pnPanel0.add(pnPanel1, gbcPanel0);
		gbcPanel0.gridx = 5;
		gbcPanel0.gridy = 25;
		gbcPanel0.gridwidth = 12;
		gbcPanel0.gridheight = 6;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		pnPanel0.add(pnPanel2, gbcPanel0);
		gbcPanel0.gridx = 8;
		gbcPanel0.gridy = 10;
		gbcPanel0.gridwidth = 12;
		gbcPanel0.gridheight = 6;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		pnPanel0.add(pnPanel3, gbcPanel0);
		gbcPanel0.gridx = 12;
		gbcPanel0.gridy = 10;
		gbcPanel0.gridwidth = 12;
		gbcPanel0.gridheight = 6;
		// gbcPanel0.anchor = GridBagConstraints.CENTER;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		pnPanel0.add(pnPanel4, gbcPanel0);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(pnPanel0);
		pack();
		setVisible(true);
		this.setSize(800, 700);
		myCart = new CartList();
		myCart.setUser(rs.getUser().geteMailID());
		myCart.setCartList(new ArrayList<Integer>());
		Timer timer = new Timer(this,rs);
		t = new Thread(timer);
		t.start();
		isFirstTime = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == chooseSeats) {
			int i = okcancel("Are you sure you want to book these seats?");
			if (i == 0) {
				ClientSocketFrame client = new ClientSocketFrame();
				client.seatSelection(rs, this);
			//	t.stop();
				isFirstTime=true;
				this.dispose();
			}
		} else if (e.getSource() == logout) {
			int i = okcancel("Are you sure you want to log out?");
			if (i == 0) {
				LoginWindow loginWindow = new LoginWindow();
				loginWindow.launch();
				t.stop();
				isFirstTime=true;
				this.dispose();
			}
		} else {
			if (isFirstTime) {
				/*Timer timer = new Timer(this,rs);
				t = new Thread(timer);
				t.start();
				isFirstTime = false;*/
			}

			JButton j = ((javax.swing.JButton) e.getSource());
			if (rs.getMySeats() == null) {
				rs.setSelectedSeats(new ArrayList<Integer>());
			}
			if (rs.getMySeats() != null && rs.getMySeats().size() < 4) {
				j.setIcon(newSelectedIcon);
				int seat = Integer.parseInt(j.getName());

				if (!rs.getMySeats().contains(seat)) {
					rs.getMySeats().add(seat);
					cartList.add(seat);
				} else {
					if (rs.getToDeleteSeats() == null) {
						rs.setToDeleteSeats(new ArrayList<Integer>());
					}
					Icon existingIcon = new ImageIcon("existing.jpg");
					j.setIcon(existingIcon);
					rs.getMySeats().remove(Integer.valueOf(seat));
					cartList.remove(Integer.valueOf(seat));
					rs.getToDeleteSeats().add(Integer.valueOf(seat));

				}
			} else {
				int seat = Integer.parseInt(j.getName());

				if (rs.getMySeats().contains(seat)) {
					if (rs.getToDeleteSeats() == null) {
						rs.setToDeleteSeats(new ArrayList<Integer>());
					}
					Icon existingIcon = new ImageIcon("existing.jpg");
					j.setIcon(existingIcon);
					rs.getMySeats().remove(Integer.valueOf(seat));
					cartList.remove(Integer.valueOf(seat));
					rs.getToDeleteSeats().add(Integer.valueOf(seat));
					txtCart.setText("");

				} else
				ok("Sorry you can select a maximum of 4 seats");
			}
		}
		String s = "";
		for (int seat : cartList) {
			s = s + "   " + seat;
		}
		txtCart.setText("Shopping Cart : " + s);
		myCart = new CartList();
		myCart.setCartList(cartList);
		myCart.setUser(rs.getUser().geteMailID());
	}

	public static int okcancel(String theMessage) {
		int result = JOptionPane.showConfirmDialog((Component) null, theMessage, "alert", JOptionPane.OK_CANCEL_OPTION);
		return result;
	}

	public static void ok(String theMessage) {
		int result = JOptionPane.showConfirmDialog((Component) null, theMessage, "alert", JOptionPane.PLAIN_MESSAGE);
	}
}
