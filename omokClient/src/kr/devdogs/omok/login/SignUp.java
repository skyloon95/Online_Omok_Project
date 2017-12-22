package kr.devdogs.omok.login;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import kr.devdogs.omok.net.Client;
import kr.devdogs.omok.net.Protocol;
import kr.devdogs.omok.net.ResponseCode;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
public class SignUp extends JFrame implements ActionListener {

	private JFrame frame;
	private JTextField id;
	private JTextField nickName;
	private JPasswordField passwordR;
	private JPasswordField passwordRe;
	Member member=new Member();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
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
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 558, 422);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("회원가입");
		JLabel label = new JLabel("\uC544\uC774\uB514:");
		label.setFont(new Font("����", Font.BOLD, 18));
		label.setBounds(35, 29, 106, 43);
		frame.getContentPane().add(label);

		id = new JTextField();
		id.setBounds(113, 37, 184, 27);
		frame.getContentPane().add(id);
		id.setColumns(25);

		JLabel label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638:");
		label_1.setFont(new Font("����", Font.BOLD, 18));
		label_1.setBounds(17, 128, 96, 41);
		frame.getContentPane().add(label_1);

		JButton overlapCheck1 = new JButton("\uC911\uBCF5 \uCCB4\uD06C");
		overlapCheck1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("".equals(id.getText())==true)
				{
					frame = new JFrame();
					frame.setBounds(100, 100, 450, 196);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JLabel lblNewLabel = new JLabel("\uB0B4\uC6A9\uC744 \uC785\uB825\uD574 \uC8FC\uC138\uC694.");
					lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
					lblNewLabel.setBounds(83, 15, 337, 54);
					frame.getContentPane().add(lblNewLabel);

					JButton btnNewButton = new JButton("\uD655\uC778");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(false);
						}
					});
					btnNewButton.setFont(new Font("����", Font.BOLD, 22));
					btnNewButton.setBounds(83, 84, 253, 29);
					frame.getContentPane().add(btnNewButton);
					frame.setVisible(true);


				}
				else if(member.idCheck()==true)
				{
					frame = new JFrame();
					frame.setBounds(100, 100, 450, 196);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JLabel lblNewLabel = new JLabel("\uC0AC\uC6A9\uAC00\uB2A5\uD55C \uC544\uC774\uB514\uC785\uB2C8\uB2E4.");
					lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
					lblNewLabel.setBounds(60, 15, 342, 39);
					frame.getContentPane().add(lblNewLabel);

					JButton btnNewButton = new JButton("\uD655\uC778");

					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(false);
						}
					});
					btnNewButton.setFont(new Font("����", Font.BOLD, 23));
					btnNewButton.setBounds(98, 69, 200, 29);
					frame.getContentPane().add(btnNewButton);
					frame.setVisible(true);
				}
				else
				{
					frame = new JFrame();
					frame.setBounds(100, 100, 450, 196);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JLabel lblNewLabel = new JLabel("\uC774\uBBF8 \uC0AC\uC6A9\uC911\uC778 \uC544\uC774\uB514\uC785\uB2C8\uB2E4.");
					lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
					lblNewLabel.setBounds(50, 15, 337, 54);
					frame.getContentPane().add(lblNewLabel);

					JButton btnNewButton = new JButton("\uD655\uC778");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(false);
						}
					});
					btnNewButton.setFont(new Font("����", Font.BOLD, 22));
					btnNewButton.setBounds(83, 84, 253, 29);
					frame.getContentPane().add(btnNewButton);
					frame.setVisible(true);
				}

			}
		});

		overlapCheck1.setBounds(318, 36, 125, 29);
		frame.getContentPane().add(overlapCheck1);

		JLabel lblNewLabel = new JLabel("\uBE44\uBC00\uBC88\uD638 \uC7AC\uC785\uB825:");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel.setBounds(17, 184, 152, 43);
		frame.getContentPane().add(lblNewLabel);

		JButton signUpButton= new JButton("\uD68C\uC6D0\uAC00\uC785");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if("".equals(id.getText())==false &&  "".equals(new String(passwordR.getPassword()))==false && "".equals(new String(passwordRe.getPassword()))==false && "".equals(nickName.getText())==false && new String(passwordR.getPassword()).equals(new String(passwordRe.getPassword()))==true)
				{
					member.setId(id.getText());
					member.setPasswordR(new String(passwordR.getPassword()));
					member.setPasswordRe(new String(passwordRe.getPassword()));
					member.setNickName(nickName.getText());
					member.saveId();
					member.savePasswordR();
					member.saveNickName();
					
					StringBuilder packet = new StringBuilder(Protocol.JOIN)
							.append("/")
							.append(member.getId())
							.append("/")
							.append(member.getPasswordR())
							.append("/")
							.append(member.getNickName());
					
					String result = Client.getInstance().dataSend(packet.toString());
					if(ResponseCode.SUCCESS.equals(result)) {
						showDialog("회원가입에 성공했습니다.");
						SignUp.this.setVisible(false);
					} else {
						showDialog("회원가입에 실패했습니다.");
					}
					
					/*
					frame = new JFrame();
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JLabel label = new JLabel("\uD68C\uC6D0\uAC00\uC785\uC774 \uC644\uB8CC\uB418\uC5C8\uC2B5\uB2C8\uB2E4.");
					label.setFont(new Font("����", Font.BOLD, 27));
					label.setBounds(34, 15, 394, 114);
					frame.getContentPane().add(label);

					JButton btnNewButton = new JButton("\uD655\uC778");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							frame.setVisible(false);
						}
					});
					btnNewButton.setBounds(34, 146, 348, 42);
					frame.getContentPane().add(btnNewButton);
					frame.setVisible(true);
					*/

				}
				else if("".equals(id.getText())==false && "".equals(new String(passwordR.getPassword()))==false &&"".equals(new String(passwordRe.getPassword()))==false && "".equals(nickName.getText())==false  && new String(passwordR.getPassword()).equals(new String(passwordRe.getPassword()))==false)
				{

					frame = new JFrame();
					frame.setBounds(100, 100, 436, 243);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JLabel label = new JLabel("\uBE44\uBC00\uBC88\uD638\uAC00 \uC798\uBABB \uC785\uB825\uB418\uC5C8\uC2B5\uB2C8\uB2E4.");
					label.setFont(new Font("����", Font.BOLD, 20));
					label.setBounds(59, 46, 394, 52);
					frame.getContentPane().add(label);

					JButton btnNewButton = new JButton("\uD655\uC778");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							frame.setVisible(false);
						}
					});
					btnNewButton.setFont(new Font("����", Font.BOLD, 20));
					btnNewButton.setBounds(59, 111, 319, 29);
					frame.getContentPane().add(btnNewButton);
					frame.setVisible(true);
				}
				else if("".equals(id.getText())==true || "".equals(new String(passwordR.getPassword()))==true ||"".equals(new String(passwordRe.getPassword()))==true || "".equals(nickName.getText())==true)
				{

					frame = new JFrame();
					frame.setBounds(100, 100, 450, 196);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JLabel lblNewLabel = new JLabel("\uB0B4\uC6A9\uC744 \uC785\uB825\uD574 \uC8FC\uC138\uC694.");
					lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
					lblNewLabel.setBounds(83, 15, 337, 54);
					frame.getContentPane().add(lblNewLabel);

					JButton btnNewButton = new JButton("\uD655\uC778");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(false);
						}
					});
					btnNewButton.setFont(new Font("����", Font.BOLD, 22));
					btnNewButton.setBounds(83, 84, 253, 29);
					frame.getContentPane().add(btnNewButton);
					frame.setVisible(true);
				}

			}
		});
		signUpButton.setBounds(17, 263, 152, 43);
		frame.getContentPane().add(signUpButton);

		JButton cancelButton = new JButton("\uCDE8\uC18C");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);

			}
		});
		cancelButton.setBounds(291, 263, 152, 43);

		frame.getContentPane().add(cancelButton);


		JLabel label_2 = new JLabel("\uB2C9\uB124\uC784:");
		label_2.setFont(new Font("����", Font.BOLD, 18));
		label_2.setBounds(35, 84, 106, 29);
		frame.getContentPane().add(label_2);

		nickName = new JTextField();
		nickName.setBounds(113, 87, 184, 27);
		frame.getContentPane().add(nickName);
		nickName.setColumns(10);

		JButton overlapCheck2 = new JButton("\uC911\uBCF5 \uCCB4\uD06C");
		overlapCheck2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("sec");
				if("".equals(nickName.getText()))
				{
					frame = new JFrame();
					frame.setBounds(100, 100, 450, 196);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JLabel lblNewLabel = new JLabel("\uB0B4\uC6A9\uC744 \uC785\uB825\uD574 \uC8FC\uC138\uC694.");
					lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
					lblNewLabel.setBounds(83, 15, 337, 54);
					frame.getContentPane().add(lblNewLabel);

					JButton btnNewButton = new JButton("\uD655\uC778");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(false);
						}
					});
					btnNewButton.setFont(new Font("����", Font.BOLD, 22));
					btnNewButton.setBounds(83, 84, 253, 29);
					frame.getContentPane().add(btnNewButton);
					frame.setVisible(true);
				}
				else if(member.nickNameCheck()==true)
				{
					frame = new JFrame();
					frame.setBounds(100, 100, 450, 196);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JLabel lblNewLabel = new JLabel("\uC0AC\uC6A9\uAC00\uB2A5\uD55C \uB2C9\uB124\uC784\uC785\uB2C8\uB2E4.");
					lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
					lblNewLabel.setBounds(60, 15, 342, 39);
					frame.getContentPane().add(lblNewLabel);

					JButton btnNewButton = new JButton("\uD655\uC778");

					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(false);
						}
					});
					btnNewButton.setFont(new Font("����", Font.BOLD, 23));
					btnNewButton.setBounds(98, 69, 200, 29);
					frame.getContentPane().add(btnNewButton);
					frame.setVisible(true);
				}
				else
				{
					frame = new JFrame();
					frame.setBounds(100, 100, 450, 196);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.getContentPane().setLayout(null);

					JLabel lblNewLabel = new JLabel("\uC774\uBBF8 \uC0AC\uC6A9\uC911\uC778 \uB2C9\uB124\uC784\uC785\uB2C8\uB2E4.");
					lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
					lblNewLabel.setBounds(50, 15, 337, 54);
					frame.getContentPane().add(lblNewLabel);

					JButton btnNewButton = new JButton("\uD655\uC778");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setVisible(false);
						}
					});
					btnNewButton.setFont(new Font("����", Font.BOLD, 22));
					btnNewButton.setBounds(83, 84, 253, 29);
					frame.getContentPane().add(btnNewButton);
					frame.setVisible(true);
				}
			}
		});
		overlapCheck2.setBounds(318, 84, 125, 29);
		frame.getContentPane().add(overlapCheck2);

		passwordR = new JPasswordField();
		passwordR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		passwordR.setColumns(10);
		passwordR.setBounds(113, 135, 184, 27);
		frame.getContentPane().add(passwordR);

		passwordRe = new JPasswordField();
		passwordRe.setColumns(10);
		passwordRe.setBounds(174, 192, 184, 27);
		frame.getContentPane().add(passwordRe);

		JLabel label_3 = new JLabel("\u25CF\uBE44\uBC00\uBC88\uD638\uB294 10\uC790 \uC774\uD558");
		label_3.setFont(new Font("����", Font.PLAIN, 12));
		label_3.setBounds(318, 134, 201, 27);
		frame.getContentPane().add(label_3);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	private void showDialog(String msg) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 196);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel(msg);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel.setBounds(83, 15, 337, 54);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 22));
		btnNewButton.setBounds(83, 84, 253, 29);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}
}
