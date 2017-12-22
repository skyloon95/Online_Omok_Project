package kr.devdogs.omok.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.plaf.synth.SynthScrollBarUI;

import kr.devdogs.omok.ingame.ChatView;
import kr.devdogs.omok.ingame.Game;
import kr.devdogs.omok.ingame.GameBoardMouseListener;
import kr.devdogs.omok.ingame.Stone;
import kr.devdogs.omok.login.listener.MemberEventListener;


//싱글톤으로 만들어 
public class Client {

	//서버와 클라이언트는 1:다 이니 List로 만들 필요 X
	private Socket clientSocket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private int myId; //클라이언트의 고유번호임
	private ChatView ccd;
	
	// 리스너
	private MemberEventListener memberEventListener;

	public int getMyId() {
		return myId;
	}

	//싱글톤 어찌구
	public static Client getInstance() {
		return LazyHolder.INSTANCE;
	}

	//싱글톤 어쩌구
	private static class LazyHolder {
		private static final Client INSTANCE = new Client();
	}

	//


	public void dataRecv() {

		new Thread(new Runnable() {
			boolean isThread = true;
			@Override
			public void run() {
				while(isThread) {
					try {
						String recvData = dataInputStream.readUTF(); //받은 데이터 저장

						//a[0] : 클라이언트 번호, a[1] : 코드, a[2] : 방번호, a[3] : 회원번호, a[4] : x축 위치, a[5] : y축 위치
						String[] datas = recvData.split("/"); //받은 데이터를 구분할거임

						switch(datas[1]) {
						//105 : 흑돌 배치
						case "105" :
							Stone.putStone(Integer.parseInt(datas[4]), Integer.parseInt(datas[5]), true);
							break;
							//106 : 백돌 배치
						case "106" :
							Stone.putStone(Integer.parseInt(datas[4]), Integer.parseInt(datas[5]), false);
							break;
							//115 : 게임 시작 승인
						case "115" :
							GameBoardMouseListener.setIsStarted(true);
							Stone.resetMatrix();
							break;
							//116 : 게임 시작 실패
						case "116" :

							break;
							//117 : 33 조건 실패
						case "117" :

							break;
							//118 : 직전에 바둑돌을 놓은 사람이 승리
						case "118" :
							//여기 수정
							if(Integer.parseInt(datas[0]) % 2 == 0) {
								Stone.putStone(Integer.parseInt(datas[4]), Integer.parseInt(datas[5]), true);
								Game.screen.chatView.receiveMessage("흑돌 승리");
							}
							else{
								Stone.putStone(Integer.parseInt(datas[4]), Integer.parseInt(datas[5]), false);
								Game.screen.chatView.receiveMessage("백돌 승리");
							}
							Game.screen.chatView.receiveMessage("다시 시작하시려면 스타트를 눌러주세연 by. 소프요정 이종석");
							GameBoardMouseListener.setIsStarted(false);
							break;

						case Protocol.CHAT:
							String nickname = datas[2];
							String msg = datas[3];
							Game.screen.chatView.receiveMessage(nickname + " : " + msg);
							break;

						case Protocol.LOGIN:
							if("1".equals(datas[2])) {
								memberEventListener.onLoginResult(datas[3]);
							} else {
								memberEventListener.onLoginResult(null);
							}
							break;
						case Protocol.JOIN:
							memberEventListener.onJoinResult(datas[2]);
							break;
						case Protocol.CHECK_ID:
							memberEventListener.onCheckIdResult(datas[2]);
							break;
						case Protocol.CHECK_NICKNAME:
							memberEventListener.onCheckNicknameResult(datas[2]);
							//500 : 바둑돌을 둘 차례가 아님
						case "500" :
							break;
						}
					} catch (Exception e) {
					}
				}
			}
		}).start();

	}
	/*
	public void dataSend(String sendData) {
		try {
			//전송 코드에 클라이언트 아이디 들어가야함! 그거 추가해야함
			//지금 코드 양식 -> 코드/방번호/회원번호/x축위치/y축위치
			//전송 코드에 클라이언트 아이디 들어가야함
			//변경 코드 양식 -> 클라이언트아이디/코드/방번호/회원번호/x축위치/y축위치
			sendData = this.myId + "/" +sendData;
			System.out.println("전송 코드 : "+sendData);
			dataOutputStream.writeUTF(sendData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 */

	public String dataSend(String sendData) {
		try {
			sendData = this.myId + "/" +sendData;
			dataOutputStream.writeUTF(sendData);
			
		} catch (Exception e) {
		}
		return null;
	}

	private Client() {

		try {
			clientSocket = new Socket("localhost", 12347);
			dataInputStream = new DataInputStream(clientSocket.getInputStream());
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
			myId = dataInputStream.readInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataRecv();
	}

	public void setMemberEventListener(MemberEventListener memberEventListener) {
		this.memberEventListener = memberEventListener;
	}
}