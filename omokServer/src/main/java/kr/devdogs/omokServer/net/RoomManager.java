package kr.devdogs.omokServer.net;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class RoomManager {

	//방 번호를 통해 방에 대응
	private static HashMap<Integer, GameRoom> roomList= new HashMap<Integer, GameRoom>();

	//방장 이름을 통해 방에 대응
	//지금 쓰지는 않았지만 쓰일 수도 있을 것 같아서 넣었음
	//방 이름을 설정하게 된다면 뺄 수도 있을 것 같아서..
	//private HashMap<String, GameRoom> roomListByOwnerName;

	public RoomManager() {
		//roomListByOwnerName = new HashMap<String, GameRoom>();
	}

	public static void addRoom(GameRoom room) {
		roomList.put(room.getRoomId(), room);
	}

	//룸을 받아서 삭제함
	public static void subRoom(GameRoom room) {
		int id = room.getRoomId();

		roomList.remove(id);
	}

	//삭제할 때 방 번호만을 줘서 삭제를 하면 이 메소드 이용
	public static void subRoom(int roomId) {
		roomList.remove(roomId);
	}

	//필요하다면 방 이름을 받아서 삭제할 것
	public static void subRoom(String roomName) {

	}

//	public static Vector<String> getStringList() {
//		Vector<String> roomList = new Vector<String>();
//
//		for(int i = 0; i < roomListByRoomId.size(); i++) {
//			roomList.add(roomListByRoomId.get(i).getRoomName());
//		}
//
//		return roomList;
//	}

	//방이 얼마나 있나 
	public static int size() {
		return roomList.size();
	}
	
	//회원 번호와 방 번호로 게임 방의 유저를 가져옴(순서, 0 or 1)
	public static boolean findUserOrder(int userId, int roomId) {
		GameRoom tempRoom = roomList.get(1);
		
		//순서를 알아옴
		int order = tempRoom.getUserOrder(userId);
		
		//흑돌 0, 백돌 1
		if(order == 0)
			return false;
		else
			return true;
	}

	public static HashMap<Integer, GameRoom> getRoomList() {
		return roomList;
	}

	public static void setRoomList(HashMap<Integer, GameRoom> roomList) {
		RoomManager.roomList = roomList;
	}

}
