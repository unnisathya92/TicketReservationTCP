package edu.uic.windows;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.uic.model.User;

import edu.uic.client.ClientSocketFrame;

/**
 * @author Administrator
 * @created February 6, 2017
 */
public class LoginWindow extends JFrame implements ActionListener {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	static LoginWindow theWin1;

	JPanel pnPanel0;

	JPanel pnPanel1;
	JLabel lbLoginLabel;
	JLabel lbEmailLabel;
	JLabel lbPasswordLabel;
	JTextField tfEmail;
	JPasswordField tfPwd;
	JButton btLoginBtn;
	JButton btRegister;
	public JTextArea txtS;

	/**
	 */
	public static void main(String args[]) {

		theWin1 = new LoginWindow();
		theWin1.launch();
	}

	/**
	 */
	public LoginWindow() {
		super("Online Ticket Reservation System");
	}

	public void launch() {

		pnPanel0 = new JPanel();
		pnPanel0.setBorder(BorderFactory.createTitledBorder(""));
		GridBagLayout gbPanel0 = new GridBagLayout();
		GridBagConstraints gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout(gbPanel0);

		pnPanel1 = new JPanel();
		GridBagLayout gbPanel1 = new GridBagLayout();
		GridBagConstraints gbcPanel1 = new GridBagConstraints();
		pnPanel1.setLayout(gbPanel1);

		lbLoginLabel = new JLabel("Login");

		gbcPanel1.gridx = 3;
		gbcPanel1.gridy = 0;
		gbcPanel1.gridwidth = 1;
		gbcPanel1.gridheight = 1;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 1;
		gbPanel1.setConstraints(lbLoginLabel, gbcPanel1);
		pnPanel1.add(lbLoginLabel);

		lbEmailLabel = new JLabel("Email");
		gbcPanel1.gridx = 0;
		gbcPanel1.gridy = 2;
		gbcPanel1.gridwidth = 2;
		gbcPanel1.gridheight = 1;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 1;
		gbPanel1.setConstraints(lbEmailLabel, gbcPanel1);
		pnPanel1.add(lbEmailLabel);

		lbPasswordLabel = new JLabel("Password");
		gbcPanel1.gridx = 0;
		gbcPanel1.gridy = 4;
		gbcPanel1.gridwidth = 2;
		gbcPanel1.gridheight = 1;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 1;
		gbPanel1.setConstraints(lbPasswordLabel, gbcPanel1);
		pnPanel1.add(lbPasswordLabel);

		tfEmail = new JTextField();
		tfEmail.setPreferredSize(new Dimension(120, 20));
		gbcPanel1.gridx = 2;
		gbcPanel1.gridy = 2;
		gbcPanel1.gridwidth = 3;
		gbcPanel1.gridheight = 1;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;
		gbPanel1.setConstraints(tfEmail, gbcPanel1);
		pnPanel1.add(tfEmail);

		tfPwd = new JPasswordField();
		tfPwd.setPreferredSize(new Dimension(120, 20));
		tfPwd.setEchoChar('*');
		gbcPanel1.gridx = 2;
		gbcPanel1.gridy = 4;
		gbcPanel1.gridwidth = 3;
		gbcPanel1.gridheight = 1;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;
		gbPanel1.setConstraints(tfPwd, gbcPanel1);
		pnPanel1.add(tfPwd);

		btLoginBtn = new JButton("Login");
		gbcPanel1.gridx = 1;
		gbcPanel1.gridy = 6;
		gbcPanel1.gridwidth = 2;
		gbcPanel1.gridheight = 2;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;
		gbPanel1.setConstraints(btLoginBtn, gbcPanel1);
		pnPanel1.add(btLoginBtn);
		btLoginBtn.addActionListener(this);

		btRegister = new JButton("Register");
		gbcPanel1.gridx = 4;
		gbcPanel1.gridy = 6;
		gbcPanel1.gridwidth = 1;
		gbcPanel1.gridheight = 1;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;
		gbPanel1.setConstraints(btRegister, gbcPanel1);
		pnPanel1.add(btRegister);
		btRegister.addActionListener(this);

		txtS = new JTextArea();

		gbcPanel1.gridx = 3;
		gbcPanel1.gridy = 8;
		gbcPanel1.gridwidth = 2;
		gbcPanel1.gridheight = 2;
		gbcPanel1.weightx = 1;
		gbcPanel1.weighty = 0;
		gbPanel1.setConstraints(txtS, gbcPanel1);
		pnPanel1.add(txtS);

		gbcPanel0.gridx = 0;
		gbcPanel0.gridy = 0;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.insets = new Insets(175, 175, 175, 175);

		gbPanel0.setConstraints(pnPanel1, gbcPanel0);
		pnPanel0.add(pnPanel1);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JScrollPane scpPanel0 = new JScrollPane(pnPanel0);
		setContentPane(scpPanel0);
		pack();
		setVisible(true);
		this.setSize(800, 700);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btRegister) {
			RegisterWindow registration = new RegisterWindow();
			registration.launch();
			dispose();
		}
		if (e.getSource() == btLoginBtn) {
			User user = new User(tfEmail.getText(), tfPwd.getText());
			if (user.geteMailID().equals("")) {
				txtS.setText("Please Enter EmailID and password to continue");
			} else {
				ClientSocketFrame client = new ClientSocketFrame();
				client.login(user, this);
			}
		}
	}

	
}
