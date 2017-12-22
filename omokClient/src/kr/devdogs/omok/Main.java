package kr.devdogs.omok;

import java.awt.EventQueue;

import kr.devdogs.omok.login.Login;
import kr.devdogs.omok.net.Client;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
