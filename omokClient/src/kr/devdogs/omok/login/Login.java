package kr.devdogs.omok.login;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.JTextField;

import kr.devdogs.omok.ingame.Game;
import kr.devdogs.omok.login.listener.MemberEventListener;
import kr.devdogs.omok.net.Client;
import kr.devdogs.omok.net.Protocol;
import kr.devdogs.omok.net.ResponseCode;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JTextField id;
	private JPasswordField passwordRe;
	private JPasswordField passwordR;
	private JTextField nickName;
	public JFrame currentFrame;
	public JFrame joinFrame;
	public JFrame frame;
	private JTextField login;
	private JTextField textField;
	private JPasswordField password;
	Member member=new Member();
	
	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Client.getInstance().setMemberEventListener(new MemberEventListener() {
			@Override
			public void onLoginResult(String nickname) {
				if(nickname != null) {
					currentFrame.dispose();
					Game.gameStart();
				} else {
					showDialog("아이디나 비밀번호가 맞지 않습니다.");
				}
			}

			@Override
			public void onJoinResult(String responseCode) {
				if(ResponseCode.SUCCESS.equals(responseCode)) {
					showDialog("회원가입에 성공했습니다.");
					joinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					joinFrame.dispose();
				} else {
					showDialog("회원가입에 실패했습니다.");
				}
			}

			@Override
			public void onCheckIdResult(String responseCode) {
				if(ResponseCode.SUCCESS.equals(responseCode)) {
					showDialog("사용할 수 있는 아이디입니다.");
				} else {
					showDialog("중복된 아이디입니다.");
				}
			}

			@Override
			public void onCheckNicknameResult(String responseCode) {
				if(ResponseCode.SUCCESS.equals(responseCode)) {
					showDialog("사용할 수 있는 닉네임입니다.");
				} else {
					showDialog("중복된 닉네임입니다.");
				}
			}
		});
		
		frame = new JFrame();
		currentFrame = frame;
		frame.setBounds(100, 100, 709, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel idLabel = new JLabel("\uC544\uC774\uB514:");
		idLabel.setFont(new Font("����", Font.BOLD, 25));
		idLabel.setBounds(100, 136, 93, 32);
		frame.getContentPane().add(idLabel);
		
		JLabel passwordLabel = new JLabel("\uBE44\uBC00\uBC88\uD638:");
		passwordLabel.setFont(new Font("����", Font.BOLD, 25));
		passwordLabel.setBounds(76, 188, 109, 41);
		frame.getContentPane().add(passwordLabel);
		frame.setTitle("로그인");
		login = new JTextField();
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		login.setBounds(210, 139, 231, 32);
		frame.getContentPane().add(login);
		login.setColumns(10);
		
		password = new JPasswordField();
		password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		password.setColumns(10);
		password.setBounds(210, 189, 231, 32);
		frame.getContentPane().add(password);
		
		JButton loginButton = new JButton("\uB85C\uADF8\uC778");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!"".equals(login.getText().trim()) 
						&& !"".equals(password.getText().trim())) {
					StringBuilder sendData = new StringBuilder("300/")
							.append(login.getText().trim())
							.append("/")
							.append(password.getText().trim());
					
					Client.getInstance().dataSend(sendData.toString());
					
				} else {
					showDialog("\uB0B4\uC6A9\uC744 \uC785\uB825\uD574 \uC8FC\uC138\uC694.");
				}
				
				
				/*
				 // 기능축소로 인한 미사용 소스
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
				btnNewButton.setBounds(17, 286, 113, 34);
				frame.getContentPane().add(btnNewButton);
				
				JButton btnNewButton_2 = new JButton("\uBCF4\uB0B4\uAE30");
				btnNewButton_2.setBounds(317, 495, 125, 29);
				frame.getContentPane().add(btnNewButton_2);
				
				JButton btnNewButton_1 = new JButton("\uBC29 \uB4E4\uC5B4\uAC00\uAE30");
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
				
				JList list_1 = new JList();
				list_1.setBounds(17, 326, 425, 169);
				frame.getContentPane().add(list_1);
				frame.setVisible(true);
				*/
			}
		});
		loginButton.setFont(new Font("����", Font.BOLD, 18));
		loginButton.setBounds(469, 138, 125, 32);
		frame.getContentPane().add(loginButton);
		
		JButton signUpButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame = new JFrame();
				joinFrame = frame;
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
						} else {
							// 아이디 중복확인
							String sendingId = id.getText();
							Client.getInstance().dataSend(
									new StringBuilder(Protocol.CHECK_ID)
									.append("/")
									.append(sendingId)
									.toString()
									);
							
							
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
							
							// 회원가입 로직
							StringBuilder packet = new StringBuilder(Protocol.JOIN)
									.append("/")
									.append(member.getId())
									.append("/")
									.append(member.getPasswordR())
									.append("/")
									.append(member.getNickName());
							
							Client.getInstance().dataSend(packet.toString());
							
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
						else {
							// 닉네임 중복확인
							String sendingNickname = nickName.getText();
							Client.getInstance().dataSend(
									new StringBuilder(Protocol.CHECK_NICKNAME)
									.append("/")
									.append(sendingNickname)
									.toString()
									);
							
							
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
				frame.setVisible(true);
				
			}
		});
		
		signUpButton.setFont(new Font("����", Font.BOLD, 18));
		signUpButton.setBounds(469, 188, 125, 32);
		frame.getContentPane().add(signUpButton);
		
		JLabel lblNewLabel_2 = new JLabel("     \uC628\uB77C\uC778 \uC624\uBAA9");
		lblNewLabel_2.setFont(new Font("�ü�", Font.BOLD, 52));
		lblNewLabel_2.setBounds(84, 15, 498, 109);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
	private void showDialog(String msg) {
		JFrame frame2 = new JFrame();
		frame2.setBounds(100, 100, 450, 196);
		frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame2.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel(msg);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel.setBounds(83, 15, 337, 54);
		frame2.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 22));
		btnNewButton.setBounds(83, 84, 253, 29);
		frame2.getContentPane().add(btnNewButton);
		frame2.setVisible(true);
	}
}
