package kr.devdogs.omok.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class errorMessage {

	private JFrame frame;
	private JTextField id;
	private JTextField passwordR;
	private JTextField passwordRe;
	private JTextField nickName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					errorMessage window = new errorMessage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public errorMessage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 449, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 26));
		btnNewButton.setBounds(31, 117, 361, 70);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("\uB0B4\uC6A9\uC744\uC785\uB825\uD558\uC138\uC694");
		label.setFont(new Font("����", Font.BOLD, 40));
		label.setBounds(41, 30, 380, 86);
		frame.getContentPane().add(label);
	}
}
