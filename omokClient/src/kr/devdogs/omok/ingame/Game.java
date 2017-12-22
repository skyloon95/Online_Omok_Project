/**
 *
 */
package kr.devdogs.omok.ingame;

import javax.swing.JFrame;

import kr.devdogs.omok.net.Client;

/**
 * @author snow
 *
 */
public class Game {
	//	창 크기
	public static final int SCREEN_WIDTH=1280;
	public static final int SCREEN_HEIGHT=960;

	//	게임보드 크기
	public static final int GAMEBOARD_WIDTH = 800;
	public static final int GAMEBOARD_HEIGHT = 800;

	//	상단바 크기
	public static final int TOPBAR_WIDTH = SCREEN_WIDTH;
	public static final int TOPBAR_HEIGHT = SCREEN_HEIGHT - GAMEBOARD_HEIGHT-20;
	public static final int START_BUTTON_WIDTH = 1145-20;
	public static final int START_BUTTON_HEIGHT = 0;

	public static final int CHAT_WIDTH = SCREEN_WIDTH-GAMEBOARD_WIDTH-10;
	public static final int CHAT_HEIGHT = SCREEN_HEIGHT-TOPBAR_HEIGHT;

	//	바둑무늬
	public static final float BOARDLINE_START_WIDTH = GAMEBOARD_WIDTH/8f;
	public static final float BOARDLINE_WIDTH = GAMEBOARD_WIDTH - BOARDLINE_START_WIDTH*2f;
	public static final float BOARDLINE_START_HEIGHT = GAMEBOARD_HEIGHT/8f;
	public static final float BOARDLINE_HEIGHT = GAMEBOARD_HEIGHT - BOARDLINE_START_HEIGHT*2f;
	public static final int LINE = 19;
	public static final float LINE_SPACE = BOARDLINE_WIDTH/(LINE-1);
	public static final int CROSS_POINT = (LINE*LINE);
	public static final int BOARDLINE_END_WIDTH = (int)(BOARDLINE_START_WIDTH + LINE*LINE_SPACE);
	public static final int BOARDLINE_END_HEIGHT = (int)(BOARDLINE_START_HEIGHT + LINE*LINE_SPACE);

	//	바둑돌 크기(반지름)
	public static final float STONE_SIZE = LINE_SPACE-3f;

	public static ScreenDesign screen;

	/**
	 * @param args
	 */
	public static void gameStart() {
		// TODO Auto-generated method stub
		screen = new ScreenDesign();
		//클라이언트 만들면 방만들어야함! 그러니까 이거 보내야함
		
		Stone.setColor(Client.getInstance().getMyId());
		
		//클라이언트 번호, 코드번호, 회원 번호, 방 번호, x축, y축
		String data = "100" + "/" + Client.getInstance().getMyId() + "/" + "0" + "/" + "0" + "/" + "0";
		
		Client.getInstance().dataSend(data);
	}

}
