package kr.devdogs.omok.ingame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameBoardDesign extends JPanel{
	private Graphics GameBoardGraphic;

	private Image gameBoard;
	private Thread screenRepainter;
	public int[] mouseOnCor;	//	마우스가 올라가 있는 실좌표 값
	public int[] mouseOnMrx;	//	마우스가 올라가 있는 행렬좌표 값.

	public GameBoardDesign() {
		this.screenRepainter = new Thread(new RepaintThread(this)); // 스크린 리페인터에 this를 생성자로 넘김.
		mouseOnCor = new int[2];
		mouseOnMrx = new int[2];

		//	패널 사이즈 설정
		setPreferredSize(new Dimension(Game.GAMEBOARD_WIDTH,Game.GAMEBOARD_HEIGHT));
		//	이미지 로드
		gameBoard = new ImageIcon(System.getProperty("user.dir")+"/src/kr/devdogs/omok/images/game_board.png").getImage();
		setBackground(Color.WHITE);

		this.addMouseListener(new GameBoardMouseListener(this));
		this.addMouseMotionListener(new GameBoardMouseListener(this));

		this.screenRepainter.start();
	}


	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);

		Graphics2D g = (Graphics2D)graphics;
		//	안티엘리어스
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(gameBoard, 0, 0, null);

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//	가로 라인
		for(float i = Game.BOARDLINE_START_HEIGHT ; i<Game.BOARDLINE_END_HEIGHT ; i = i + Game.LINE_SPACE ) {
			Rectangle2D.Float rect = new Rectangle.Float(Game.BOARDLINE_START_WIDTH, i, Game.BOARDLINE_WIDTH, 0);

			g.setPaint(Color.BLACK);
			g.setStroke(new BasicStroke(3));
			g.draw(rect);
		}

		//	세로 라인
		for(float i = Game.BOARDLINE_START_WIDTH ; i< Game.BOARDLINE_END_WIDTH ; i = i + Game.LINE_SPACE) {
			Rectangle2D.Float rect = new Rectangle.Float(i, Game.BOARDLINE_START_HEIGHT, 0, Game.BOARDLINE_HEIGHT);

			g.setPaint(Color.BLACK);
			g.setStroke(new BasicStroke(3));
			g.draw(rect);
		}

		//	검은돌 그리기

		for(int i = 0 ; i< Math.abs(Game.CROSS_POINT) ; i++) {
			for(int j = 0 ; j <Math.abs(Game.CROSS_POINT) ; j++) {
				//	검은돌 그리기
				if(Stone.stone[i][j] == 1) {
					Ellipse2D.Float elp = new Ellipse2D.Float(Stone.getCoordinateX(i), Stone.getCoordinateY(j), Game.STONE_SIZE, Game.STONE_SIZE);

					g.setPaint(Color.BLACK);
					g.fill(elp);
				}
				//	흰돌 그리기
				else if(Stone.stone[i][j] == 2) {
					Ellipse2D.Float elp = new Ellipse2D.Float(Stone.getCoordinateX(i), Stone.getCoordinateY(j), Game.STONE_SIZE, Game.STONE_SIZE);

					g.setPaint(Color.WHITE);
					g.fill(elp);
				}
			}
		}

		if(isMouseInside()) {
			if(Stone.stone[this.mouseOnMrx[0]][this.mouseOnMrx[1]]==0&&Stone.getMyColor() != Stone.getColor()) {
				if(!Stone.getMyColor()) {
					Ellipse2D.Float elp = new Ellipse2D.Float(this.mouseOnCor[0], this.mouseOnCor[1], Game.STONE_SIZE, Game.STONE_SIZE);

					g.setPaint(new Color(1f, 1f, 1f, 0.3f));
					g.fill(elp);
				}
				else {
					Ellipse2D.Float elp = new Ellipse2D.Float(this.mouseOnCor[0], this.mouseOnCor[1], Game.STONE_SIZE, Game.STONE_SIZE);

					g.setPaint(new Color(0f, 0f, 0f, 0.3f));
					g.fill(elp);
				}
			}
		}

	}

	private boolean isMouseInside() {	//	 마우스 포인터가 현재 게임 보드의 유효 라인 안에 있는지 확인.
		if(this.mouseOnCor[0]>(Game.BOARDLINE_START_WIDTH-Game.LINE_SPACE)&&this.mouseOnCor[0]<(Game.BOARDLINE_END_WIDTH-Game.LINE_SPACE)
				&&this.mouseOnCor[1]>(Game.BOARDLINE_START_HEIGHT-Game.LINE_SPACE)&&this.mouseOnCor[1]<(Game.BOARDLINE_END_HEIGHT-Game.LINE_SPACE))
			return true;
		else
			return false;
	}

}