package kr.devdogs.omok.ingame;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class ScreenDesign extends JFrame{
	public GameBoardDesign gameBoardDesign;
	public TopDesign topDesign;
	public ChatView chatView;
	
	public ScreenDesign () {
		gameBoardDesign = new GameBoardDesign();
		topDesign = new TopDesign();
		chatView = new ChatView();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"/src/kr/devdogs/omok/images/thumbnail_img.png"));
		setTitle("Omok_Online");	//	프로그램 타이틀
		setResizable(false);	//	사용자 사이즈 변경 불가
		setSize(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);	//	창 사이즈 지정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//	게임창 종료시 프로그램 종료
		setLocationRelativeTo(null);	//	실행시 화면 중앙에서 실행

		getContentPane().add(gameBoardDesign, BorderLayout.WEST);
		getContentPane().add(topDesign, BorderLayout.NORTH);
		getContentPane().add(chatView, BorderLayout.EAST);
		setVisible(true);	//	창 가시성 true/false
	}
}