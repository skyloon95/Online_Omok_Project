package kr.devdogs.omok.ingame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import kr.devdogs.omok.net.Client;

public class GameBoardMouseListener implements MouseListener,MouseMotionListener{

	private GameBoardDesign GameBoard;
	private static boolean isStarted;

	public GameBoardMouseListener(GameBoardDesign g) {
		this.GameBoard = g;
		isStarted = false;
	}

	//	클릭한 상태로 움직이는 경우 돌 미리보기가 따라오지 않는 경우를 방지하기 위해 드래그에도 추가.
	@Override
	public void mouseDragged(MouseEvent e) {
		int x;
		int y;

		if(isStarted) {
			x = averageFinderX(e.getX());
			y  = averageFinderY(e.getY());

			effect2(x,y);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x;
		int y;

		if(isStarted) {
			x = averageFinderX(e.getX());
			y  = averageFinderY(e.getY());

			effect2(x,y);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	//	 클릭시에 돌을 놓는 경우 마우스가 조금만 움직이고 있어도 drag로 해석되어버리는 경우가 많아서 실행이 되지 않는 버그가 발생하기 때문에 마우스를 뗄 때 실행되도록 함.
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int x;
		int y;

		if(isStarted) {
			x = averageFinderX(e.getX());
			y = averageFinderY(e.getY());

			//	의미있는 클릭인 경우
			if(isInside(x,y)) {
				effect(x, y);
			}
		}
	}

	//	좌표 n을 파라미터로 받아 가까운 포인트를 반환해줄 메소드
	public int averageFinderX(int n) {
		int tmp = n - (int)(Game.BOARDLINE_START_WIDTH);
		int divvy = tmp%(int)(Game.LINE_SPACE);	//	나머지
		int residuum = tmp/(int)(Game.LINE_SPACE);	//	몫

		int nearPoint;

		nearPoint = (divvy < ((int)(Game.LINE_SPACE)/2)) ? residuum : residuum+1;

		return nearPoint;
	}

	public int averageFinderY(int n) {
		int tmp = n - (int)(Game.BOARDLINE_START_HEIGHT);
		int divvy = tmp%(int)(Game.LINE_SPACE);	//	몫
		int residuum = tmp/(int)(Game.LINE_SPACE);	//	나머지

		int nearPoint;

		nearPoint = (divvy < ((int)(Game.LINE_SPACE)/2)) ? residuum : residuum+1;

		return nearPoint;
	}

	public boolean isInside(int x, int y) {
		return x>=0&&x<(Game.LINE)&&y>=0&&y<(Game.LINE);
	}

	
	//	돌 놓을 위치를 전달받아서 원하는 코드를 실행할 메소드. 이 메소드에 넣은 내용이 클릭시 실행됩니다.
	private void effect(int x, int y) {
			
		String packet = 111 + "/" + 0 + "/" + 0 + "/" + x + "/" + y;
		
		
		Client.getInstance().dataSend(packet);
	}
	
	//	돌 미리보
	private void effect2(int x, int y) {
		this.GameBoard.mouseOnMrx[0] = x;
		this.GameBoard.mouseOnMrx[1] = y;
		this.GameBoard.mouseOnCor[0] = getCoordinateX(x);
		this.GameBoard.mouseOnCor[1] = getCoordinateY(y);
	}

	private int getCoordinateX(int x) {
		return (int)(Game.BOARDLINE_START_WIDTH + (x * Game.LINE_SPACE)-(Game.STONE_SIZE/2));
	}

	private int getCoordinateY(int y) {
		return (int)(Game.BOARDLINE_START_HEIGHT + (y * Game.LINE_SPACE)-(Game.STONE_SIZE/2));
	}
	
	public static void setIsStarted(boolean s) {
		isStarted = s;
	}
}