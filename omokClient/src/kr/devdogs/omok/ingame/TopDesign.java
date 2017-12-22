package kr.devdogs.omok.ingame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TopDesign extends JPanel{
	private Graphics topGraphic;

	private Image topBar;
	private Image gameStartButton;
	private Image gameStartButtonOn;
	private Thread screenRepainter;
	public boolean mouseOn;

	public TopDesign() {
		this.screenRepainter = new Thread(new RepaintThread(this)); // 스크린 리페인터에 this를 생성자로 넘김.
		//	패널 사이즈 설정
		setPreferredSize(new Dimension(Game.TOPBAR_WIDTH,Game.TOPBAR_HEIGHT));
		//	이미지 로드
		topBar = new ImageIcon(System.getProperty("user.dir")+"/src/kr/devdogs/omok/images/top_design.png").getImage();
		gameStartButton = new ImageIcon(System.getProperty("user.dir")+"/src/kr/devdogs/omok/images/start_button.png").getImage();
		this.gameStartButtonOn = new ImageIcon(System.getProperty("user.dir")+"/src/kr/devdogs/omok/images/start_button_on.png").getImage();
		setBackground(Color.WHITE);
		
		this.addMouseListener(new TopBarMouseListener(this));
		this.addMouseMotionListener(new TopBarMouseListener(this));
		
		this.mouseOn = false;
		
		this.screenRepainter.start();
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);

		Graphics2D g = (Graphics2D)graphics;
		//	안티 엘리어스
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(topBar, 0, 0, null);
		
		if(mouseOn) {
			g.drawImage(gameStartButtonOn, Game.START_BUTTON_WIDTH, Game.START_BUTTON_HEIGHT, null);
		}
		else
			g.drawImage(gameStartButton, Game.START_BUTTON_WIDTH, Game.START_BUTTON_HEIGHT, null);
	}

}