package kr.devdogs.omok.login;
import java.awt.BorderLayout;
import java.awt.BorderLayout; 
import java.awt.Button; 
import java.awt.Color; 
import java.awt.Frame; 
import java.awt.Panel; 
import java.awt.TextArea; 
import java.awt.TextField; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.ConnectException; 
import java.net.Socket; 
import java.util.Scanner; 


import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Frame;

import java.awt.GridLayout;

import java.awt.List;

import java.awt.Panel;

import java.awt.TextField;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.OutputStream;

import java.io.OutputStreamWriter;

import java.net.Socket;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.JTextArea;
public class WaitingRoom  implements ActionListener{
	private JFrame frame;
	private JTextField textField;
	

/**
 * Launch the application.
 */
public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				WaitingRoom window = new WaitingRoom();
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
public WaitingRoom() {
	initialize();
}

/**
 * Initialize the contents of the frame.
 */
private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 475, 595);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	frame.setTitle("����");
	JLabel label = new JLabel("\uBC88\uD638       \uBC29 \uC774\uB984");
	label.setBounds(17, 15, 425, 39);
	label.setFont(new Font("����", Font.BOLD, 20));
	frame.getContentPane().add(label);

	JList list = new JList();
	list.setBounds(17, 53, 425, 233);
	frame.getContentPane().add(list);

	JButton btnNewButton = new JButton("\uBC29\uB9CC\uB4E4\uAE30");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		}
	});
	btnNewButton.setBounds(17, 286, 113, 34);
	frame.getContentPane().add(btnNewButton);

	JButton btnNewButton_2 = new JButton("\uBCF4\uB0B4\uAE30");
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	btnNewButton_2.setBounds(317, 495, 125, 29);
	frame.getContentPane().add(btnNewButton_2);

	JButton btnNewButton_1 = new JButton("\uBC29 \uB4E4\uC5B4\uAC00\uAE30");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	btnNewButton_1.setBounds(132, 286, 129, 34);
	frame.getContentPane().add(btnNewButton_1);

	JButton button = new JButton("\uB098\uAC00\uAE30");
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			frame.setVisible(false);
		}
	});
	button.setBounds(317, 286, 125, 34);
	frame.getContentPane().add(button);

	textField = new JTextField();
	textField.setBounds(17, 495, 301, 29);
	frame.getContentPane().add(textField);
	textField.setColumns(10);

	JTextArea textArea = new JTextArea();
	textArea.setBounds(17, 323, 425, 169);
	frame.getContentPane().add(textArea);
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub

}
}
