package kr.devdogs.omokServer.net;

import java.net.Socket;

public class User {
	
	private int userId; //유저 아이디(unique)
	private String name; //유저 네임
	private Socket socket; //소켓 object
	//private GameRoom room; //유저가 속한 게임 룸 아이디
	
	public User(int userId) {
		this.userId = userId;
		this.name = "닉네임" + 100 + userId;
		
	}
	
	//ID, 네임을 가지고 유저 정보를 생성
	public User(int userId, String name) {
		this.userId = userId;
		this.name = name;
	}
	
//	public void enterRoome(GameRoom room) {
//		room.addUser(this); //룸에 입장시킨다.
//		this.room = room; //유저가 속한 게임룸을 변경함
//	}
	
//	public void exitRoom(GameRoom room) {
//		this.room = null; //퇴장처리
//		//화면은 알아서 구성
//	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

//	public GameRoom getRoom() {
//		return room;
//	}

//	public void setRoom(GameRoom room) {
//		this.room = room;
//	}
	
	
}
