package edu.uic.windows;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.uic.model.User;

import edu.uic.client.ClientSocketFrame;

/**
 * @author Sathya
 * @created February 6, 2017
 */
public class RegisterWindow extends JFrame implements ActionListener {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	static RegisterWindow theWin3;

	JPanel pnPanel0;

	JPanel pnPanel1;
	JLabel lbFirst;
	JLabel lbLabel1;
	JLabel lbLabel2;
	JLabel lbLabel3;
	JTextField tfFName;
	JTextField tfLName;
	JTextField tfEmail;
	JPasswordField tfPwd;
	JButton register;
	public JTextArea txtS;

	/**
	 */
	public static void main(String args[]) {

		theWin3 = new RegisterWindow();
		theWin3.launch();
	}

	public RegisterWindow() {
		super("Online Ticket Reservation System");
	}

	/**
	 */
	public void launch() {

		pnPanel0 = new JPanel();
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout(gbPanel0);

		pnPanel1 = new JPanel();
		GridBagLayout gbPanel1 = new GridBagLayout();
		GridBagConstraints gbcPanel1 = new GridBagConstraints();
		pnPanel1.setLayout(gbPanel1);

		lbFirst = new JLabel("First Name");
		gbcPanel1.gridx = 0;
		gbcPanel1.gridy = 0;
		gbcPanel1.gridwidth = 2;
		gbcPanel1.gridheight = 1;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 1;
		gbPanel1.setConstraints(lbFirst, gbcPanel1);
		pnPanel1.add(lbFirst);

		lbLabel1 = new JLabel("Last Name");
		gbcPanel1.gridx = 0;
		gbcPanel1.gridy = 1;
		gbcPanel1.gridwidth = 2;
		gbcPanel1.gridheight = 1;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 1;
		gbPanel1.setConstraints(lbLabel1, gbcPanel1);
		pnPanel1.add(lbLabel1);

		lbLabel2 = new JLabel("EmailID");
		gbcPanel1.gridx = 0;
		gbcPanel1.gridy = 2;
		gbcPanel1.gridwidth = 2;
		gbcPanel1.gridheight = 1;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 1;
		gbPanel1.setConstraints(lbLabel2, gbcPanel1);
		pnPanel1.add(lbLabel2);

		lbLabel3 = new JLabel("Password");
		gbcPanel1.gridx = 0;
		gbcPanel1.gridy = 3;
		gbcPanel1.gridwidth = 2;
		gbcPanel1.gridheight = 1;

		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 1;

		gbPanel1.setConstraints(lbLabel3, gbcPanel1);
		pnPanel1.add(lbLabel3);

		tfFName = new JTextField();
		tfFName.setPreferredSize(new Dimension(120, 20));
		gbcPanel1.gridx = 2;
		gbcPanel1.gridy = 0;
		gbcPanel1.gridwidth = 5;
		gbcPanel1.gridheight = 1;

		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;

		gbPanel1.setConstraints(tfFName, gbcPanel1);
		pnPanel1.add(tfFName);

		tfLName = new JTextField();
		tfLName.setPreferredSize(new Dimension(120, 20));
		gbcPanel1.gridx = 2;
		gbcPanel1.gridy = 1;
		gbcPanel1.gridwidth = 9;
		gbcPanel1.gridheight = 1;

		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;

		gbPanel1.setConstraints(tfLName, gbcPanel1);
		pnPanel1.add(tfLName);

		tfEmail = new JTextField();
		tfEmail.setPreferredSize(new Dimension(120, 20));
		gbcPanel1.gridx = 2;
		gbcPanel1.gridy = 2;
		gbcPanel1.gridwidth = 9;
		gbcPanel1.gridheight = 1;

		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;

		gbPanel1.setConstraints(tfEmail, gbcPanel1);
		pnPanel1.add(tfEmail);

		tfPwd = new JPasswordField();
		tfPwd.setEchoChar('*');
		tfPwd.setPreferredSize(new Dimension(120, 20));
		gbcPanel1.gridx = 2;
		gbcPanel1.gridy = 3;
		gbcPanel1.gridwidth = 9;
		gbcPanel1.gridheight = 1;

		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;

		gbPanel1.setConstraints(tfPwd, gbcPanel1);
		pnPanel1.add(tfPwd);
		register = new JButton("Register");
		register.addActionListener(this);
		gbcPanel1.gridx = 4;
		gbcPanel1.gridy = 15;
		gbcPanel1.gridwidth = 6;
		gbcPanel1.gridheight = 2;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;
		gbPanel1.setConstraints(register, gbcPanel1);
		pnPanel1.add(register);

		txtS = new JTextArea();

		gbcPanel1.gridx = 3;
		gbcPanel1.gridy = 17;
		gbcPanel1.gridwidth = 2;
		gbcPanel1.gridheight = 2;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;
		gbPanel1.setConstraints(txtS, gbcPanel1);
		pnPanel1.add(txtS);

		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 3;
		gbcPanel0.gridwidth = 15;
		gbcPanel0.gridheight = 11;

		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;

		gbPanel0.setConstraints(pnPanel1, gbcPanel0);
		pnPanel0.add(pnPanel1);

		gbcPanel0.gridx = 3;
		gbcPanel0.gridy = 3;
		gbcPanel0.gridwidth = 15;
		gbcPanel0.gridheight = 11;

		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;

		gbPanel0.setConstraints(pnPanel1, gbcPanel0);
		pnPanel0.add(pnPanel1);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(pnPanel0);
		pack();
		setVisible(true);
		this.setSize(800, 700);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean flag = true;
		if (e.getSource() == register) {
			if (tfEmail.getText().length() < 6 || tfEmail.getText().length() > 12) {
				txtS.setText("Please enter a valid Email ID between 6-12 characters");
				flag = false;
			} else if (tfPwd.getText().length() < 6 || tfPwd.getText().length() > 12) {
				txtS.setText("Please enter a valid password between 6-12 characters");
				flag = false;
			}
			if (flag) {
				User user = new User(tfFName.getText(), tfLName.getText(), tfEmail.getText(), tfPwd.getText());
				ClientSocketFrame client = new ClientSocketFrame();
				client.register(user, this);
			}

		}
	}
}
