package kr.devdogs.omok.ingame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import kr.devdogs.omok.net.Client;

public class ChatView extends JPanel{
	JFrame frame;
	JLabel topLine;     // ��ܺ�
	JTextArea showArea;   // ��ȭâ
	JPanel bottomLine;    // �ϴܺ�
	JTextField inputBox;    // �Է�â
	JButton sendButton;   // '������'��ư

	BufferedReader br;
	PrintWriter pw;
	Socket chatSocket;

	String userId;
	String sendString;

	public ChatView(){
		setPreferredSize(new Dimension(Game.CHAT_WIDTH,Game.CHAT_HEIGHT));
		design();	
	}

	public void design() {
		this.setLayout(new BorderLayout());

		topLine = new JLabel("채팅");
		this.add(topLine, BorderLayout.PAGE_START);

		showArea = new JTextArea("");
		showArea.setLineWrap(true);     // �ڵ� �ٹٲ�
		this.add(new JScrollPane(showArea), BorderLayout.CENTER); // scroll-bar ����

		// �ϴܺ�
		bottomLine = new JPanel();
		inputBox = new JTextField(15);
		sendButton = new JButton("전송");
		bottomLine.add(inputBox);
		bottomLine.add(sendButton);
		this.add(bottomLine, BorderLayout.PAGE_END);

		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = inputBox.getText();
				if(!msg.equals("")) {
					sendMessage(msg);
					inputBox.setText("");
				}
			}
		};

		// Action
		inputBox.addActionListener(actionListener);     // JTextField�� EnterŰ �̺�Ʈ �߻�
		sendButton.addActionListener(actionListener);
	}

	public void sendMessage(String msg) {
		String packet = "201/" + msg;
		Client.getInstance().dataSend(packet);
	}

	public void receiveMessage(String msg) {
		showArea.append(msg+"\n");
		showArea.setCaretPosition(showArea.getText().length());
	}
}
