package kr.devdogs.omokServer.net;

import java.util.ArrayList;

public class GameRoom {
	private static int num = 1;

	private int roomId; //방 ID
	private boolean isStarted; //방의 게임이 시작이 되었는지 아닌지
	private ArrayList<User> userList; //방에 들어간 사람들의 이름
	//방장 설정은 안해두고 룸의 userList의 처음 유저를 방장으로 설정
	private omokBoard board;

	public GameRoom() {
		this.roomId = num++;
		this.board = new omokBoard();
		this.isStarted = false;
		this.userList = new ArrayList<>();
	}
	
	//유저 입장
	public void addUser(User user) {
		if(userList.size() < 2)
			userList.add(user);
	}
	
	//유저 퇴장
	public void subUser(User user) {
		userList.remove(user);
		
		if(userList.size() < 1) { //모든 인원이 다 방을 나갔다면 이 방을 제거한
			RoomManager.subRoom(this);
		}
	}
	
	public void newGame() {
		board = new omokBoard();
	}
	
	//회원 번호의 순서를 알아옴 0 : 방장, 1 : 일반 유저
	public int getUserOrder(int userId) {
		
		//순서 리턴
		for(int i = 0; i < userList.size(); ++i) {
			if(userList.get(i).getUserId() == userId)
				return i;
		}
		
		//그 방에 없음
		return -9;

	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public omokBoard getBoard() {
		return board;
	}

	public void setBoard(omokBoard board) {
		this.board = board;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

}
