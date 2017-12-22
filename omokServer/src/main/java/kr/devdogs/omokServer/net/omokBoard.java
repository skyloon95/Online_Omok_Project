package kr.devdogs.omokServer.net;

public class omokBoard {

	private int[][] board;
	
	//검은 돌은 0의 값으로 흰 알은 1의 값으로
	private final int BLACK = 0;
	private final int WHITE = 1;
	
	private final int LINE = 19;

	
	//현재 바둑알을 둘 사람의 차례를 저장한 변수. 흑이 먼저임
	//흑이 둘 차례 0, 백 1
	private int order = 0;

	//놓은 아이들의 방향을 지정해둔 상수들! 이것을 통해 어찌구함
	private final int[] UP = {0, -1};
	private final int[] RIGHT_UP = {1, -1};
	private final int[] RIGHT = {1, 0};
	private final int[] RIGHT_DOWN = {1, 1};
	private final int[] DOWN = {0, 1};
	private final int[] LEFT_DOWN = {-1, 1};
	private final int[] LEFT = {-1, 0};
	private final int[] LEFT_UP = {-1, -1};



	public omokBoard() {
		this.board = new int[LINE][LINE]; //바둑판 생성 16X16 짜리

		for(int i = 0; i < LINE; i++) { //바둑판 생성할 때 바둑판의 모든 칸을 -1로 설정 -> 아무도 바둑알을 그 칸에 놓지 않았다는 뜻
			for(int j = 0; j < LINE; j++) {
				board[i][j] = -1; //흑이 두면 0으로, 백이 두면 1로 저장하는 것
			}
		}
	}
	
	//바둑판 초기화\
	public void reset() {
		for(int i = 0; i < LINE; i++) { //바둑판 생성할 때 바둑판의 모든 칸을 -1로 설정 -> 아무도 바둑알을 그 칸에 놓지 않았다는 뜻
			for(int j = 0; j < LINE; j++) {
				board[i][j] = -1; //흑이 두면 0으로, 백이 두면 1로 저장하는 것
			}
		}
	}

	//바둑판에 바둑알 두기
	public int addStone(int [] stoneLocation, boolean isBlack) {
		//지금 놓을 바둑알
		int curStone;

		//지금 놓은 알이 검은 색이면 현재 바둑알을 나타내는 것에 0을 아니면 1을(흰색)
		if(isBlack == true)
			curStone = BLACK;
		else curStone = WHITE;
		
		//순서랑 같아야함
		if(curStone != order) {
			return -1;
		}
		
		int x = stoneLocation[0]; //x 좌표위취
		int y = stoneLocation[1]; //y 좌표위치

		
		//비어있지 않으면 바둑판에 돌을 넣지 않고 -2를 리턴함
		if(this.board[x][y] != -1) 
			return -2;

		if(isBlack)
			this.board[x][y] = curStone;
		else 
			this.board[x][y] = curStone;

		order = ( order + 1 ) % 2;
		//승리 했으면 1 리턴 승리하지 않았으면 0 리턴 
		return checkVictory(x, y, curStone);
	}

	//놓은 바둑알을 기준으로 승리 조건을 검사한다.
	//바둑알을 놓은 곳을 기준으로 각 방향으로 4개의 바둑알이 존재하면 승리 조건 완료!
	//최종으로 1을 리턴하면 승리조건 만족, 2를 리턴하면 승리조건 불만족
	//다른 바둑알을 만나면 그 순간 세는 것을 멈추면 됨.
	//analysis
	public int checkVictory(int x, int y, int curStone) { //x, y는 놓은 바둑알의 위치값

		int[] result = new int[4];

		int up = 0;
		int rightUp = 0;
		int right = 0;
		int rightDown = 0;
		int down = 0;
		int leftDown = 0;
		int left = 0;
		int leftUp = 0;

		up  = countSameStone(x, y, curStone, UP);
		rightUp  = countSameStone(x, y, curStone, RIGHT_UP);
		right  = countSameStone(x, y, curStone, RIGHT);
		rightDown  = countSameStone(x, y, curStone, RIGHT_DOWN);
		down  = countSameStone(x, y, curStone, DOWN);
		leftDown  = countSameStone(x, y, curStone, LEFT_DOWN);
		left  = countSameStone(x, y, curStone, LEFT);
		leftUp  = countSameStone(x, y, curStone, LEFT_UP);

		result[0] = up + down;
		result[1] = rightUp + leftDown;
		result[2] = right + left;
		result[3] = rightDown + leftUp;

		//return 1 : 승리 조건은 리턴값이 1
		for(int i = 0; i < 4; i++) {
			if(result[i] == 4) return 1;
		}
		
		//승리안했으면 0 리턴
		return 0;
	}

	public int countSameStone(int x, int y, int curStone, int[] direction) {
		int count = 0;

		//체크할 바둑알 위치
		int tempX = x;
		int tempY = y;

		while(true) { //바둑판을 벗어나면 안됨!
			tempX += direction[0];
			tempY += direction[1];
			
			if (tempX < 0 || tempY < 0)       // pointer reach edge of board
				break;
			else if (tempX > LINE-1 || tempY > LINE-1)
				break;

			if(this.board[tempX][tempY] == curStone) 
				count++;
			else break; //지금 놓은 바둑알의 색깔과 같은 색이 아니라면 세는 것을 멈춤
		}

		return count;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < LINE; i++) {
			for (int j = 0; j < LINE; j++) {
				str.append(String.format("%-3d ", board[i][j]));
			}
			str.append("\n");

		}
		
		return str.toString();
	}
}
