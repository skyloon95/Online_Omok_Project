package kr.devdogs.omok.ingame;

public class Stone {
	public static byte[][] stone = new byte[Game.CROSS_POINT][Game.CROSS_POINT];	//	바둑판 매트릭스
	private static boolean color = false;	//	 직전에 놓인 돌의 
	private static boolean myColor = false;	//	내 스톤컬

	//	실좌표로 변경 리턴
	public static float getCoordinateX(int x) {
		float corX;

		corX = Game.BOARDLINE_START_WIDTH + Game.LINE_SPACE*x - Game.STONE_SIZE/2;

		return corX;
	}

	//	실좌표로 변경 리턴
	public static float getCoordinateY(int y) {
		float corY;

		corY = Game.BOARDLINE_START_HEIGHT + Game.LINE_SPACE*y - Game.STONE_SIZE/2;

		return corY;
	}

	public static boolean getColor() {
		return color;
	}
	
	public static boolean getMyColor() {
		return myColor;
	}

	public static void setColor(int myId) {
		if(myId % 2 == 0) {
			myColor = true;
			color = false;
		}
		else {
			myColor = false;
			color = false;
		}
	}

	//	돌 놓기
	public static boolean putStone(int x, int y, boolean clr) {
		color = clr;
		//	비어있는 경우 돌 놓기 실행
		if(isEmpty(x,y)){
			if(color) {
				stone[x][y] = 1;
			}
			else {
				stone[x][y] = 2;
			}
			return true;
		}
		else
			return false;
	}

	//	해당 위치가 비었는지 확인
	private static boolean isEmpty(int x, int y) {
		if(stone[x][y]==0)
			return true;
		else
			return false;
	}

	public static void resetMatrix() {
		for(int i = 0 ; i < stone.length ; ++i) {
			for(int j = 0 ; j < stone.length ; ++j)
				stone[i][j]=0;
		}
	}
}