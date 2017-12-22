package kr.devdogs.omokServer.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import kr.devdogs.omokServer.db.UserRepository;

public class ClientThread extends Thread{
   private int clientId; //클라이언트 번호
   boolean isThread = true;
   
   String code;
   String nickname;
   int roomId; //방 번호
   int userId; //유저 번호
   int x; //x축 위치
   int y; //y축 위치
   GameRoom curRoom;
   
   StringBuilder sendData = new StringBuilder();
   DataInputStream dataInputStream;
   DataOutputStream dataOutputStream;
   private Server server;

   public ClientThread(int clientId, Socket clientSocket, Server server) {
      this.clientId = clientId;
      this.server = server;
      try {
         dataInputStream = new DataInputStream(clientSocket.getInputStream());
         dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Override
   public void run() {
      while(isThread) {
         try {
            String recvData = dataInputStream.readUTF(); //받은 데이터 저장

           // System.out.println("서버 수신 코드 : "+recvData);

            //a[0] : 클라이언트 번호, a[1] : 코드, a[2] : 회원번호, a[3] : 방번호, a[4] : x축 위치, a[5] : y축 위치
            String[] datas = recvData.split("/"); //받은 데이터를 구분할거임
            clientId = Integer.parseInt(datas[0]); //클라이언트 번호
            code = datas[1]; //받은 코드
            if(code.startsWith("1")) {
	            userId = Integer.parseInt(datas[2]); //유저 번호
	            roomId = Integer.parseInt(datas[3]); //방 번호
	            x = Integer.parseInt(datas[4]); //x축 위치
	            y = Integer.parseInt(datas[5]); //y축 위치
            }

            /**
             * 		100의 자리
             * 		1 : 게임
             * 		2 : 채팅
             * 		3 : 회원
             */
            switch(datas[1]) {
            //100 : 사람이 들어온다. room.size 판단해서 방 만들어져 있는지 아닌지 여부 따지고 해줘야함
            case "100" :
               //사람 넣는 거 해줘야함! room에서 메소드 잘 해주셈
               curRoom = RoomManager.getRoomList().get(roomId);
               int count = RoomManager.getRoomList().size();


               //방이 없을 때 = 방장이 없는 거. 방을 만들고 회원을 넣어준다.
               if(count == 0) {
                  GameRoom room = new GameRoom();
                  room.setRoomId(roomId);
                  room.addUser(new User(userId));

                  RoomManager.addRoom(room);
                  curRoom = room;

                  sendData.setLength(0);
                  sendData.append(datas[0]).append("/113").append("/"+datas[2]).append("/"+datas[3]).append("/"+datas[4]).append("/"+datas[5]);

                  dataSend(sendData.toString());
               }
               //방이 있을 때. 
               else {
                  User inUser = new User(userId);
                  
                  curRoom.addUser(inUser);


                  sendData.setLength(0);
                  sendData.append(datas[0]).append("/114").append("/"+datas[2]).append("/"+datas[3]).append("/"+datas[4]).append("/"+datas[5]);
                  dataSend(sendData.toString());
               }


               sendData.setLength(0);
               sendData.append("/201/System/");
               sendData.append(this.nickname);
               sendData.append("님이 들어오셨습니다.");
               broadCast(sendData.toString());
               break;
            case "110" :
               //isStarted가 true면 초기화 하지 않음
               GameRoom curRoom = RoomManager.getRoomList().get(roomId);
               
               if(curRoom.isStarted()) {
                  //System.out.println("게임 중간에 게임 시작하지마");
               }
               //게임시작
               else {
                  //유저가 2명 이상일 때만 게임 시작
                  if(curRoom.getUserList().size() == 2) {
                     curRoom.getBoard().reset();

                     curRoom.setStarted(true);
                     
                     sendData.setLength(0);
                     sendData.append(datas[0]).append("/115").append("/"+datas[2]).append("/"+datas[3]).append("/"+datas[4]).append("/"+datas[5]);
                     broadCast(sendData.toString());
                     
                     sendData.setLength(0);
                     sendData.append("/201/System/게임이 시작되었습니다.");
                     broadCast(sendData.toString());
                  }
                  else {
                     sendData.setLength(0);
                     sendData.append(datas[0]).append("/116").append("/"+datas[2]).append("/"+datas[3]).append("/"+datas[4]).append("/"+datas[5]);
                     dataSend(sendData.toString());
                  }
               }

               break;
               //돌을 놓는다.
            case "111" :
               curRoom = RoomManager.getRoomList().get(roomId);
               
               //돌을 놓을 위치 배열
               int[] stoneLocation = new int[2];
               //x축 위치, y축 위치
               stoneLocation[0] = Integer.parseInt(datas[4]);
               stoneLocation[1] = Integer.parseInt(datas[5]);

               //방 번호
               roomId = Integer.parseInt(datas[3]);
               //회원번호
               userId = Integer.parseInt(datas[2]);

               //흑이면 true, 백이면 false
               boolean userOrder /*= RoomManager.findUserOrder(userId, roomId)*/;

               if(clientId == 0)
                  userOrder = true;
               else
                  userOrder = false;

               //비어 있지 않으면 -2 리턴, 승리했으면 1리턴 승리하지 않았으면 0리턴, 차례가 아닌 사람이 돌을 둔다면 -1 리턴
               //true면 
               int state = curRoom.getBoard().addStone(stoneLocation, userOrder);

               sendData.setLength(0);

               switch(state) {
               case -2 :
                  //선택한 위치에 돌을 놓지 못합니다. 돌 배치 못함
                  sendData.append(datas[0]).append("/120").append("/"+datas[2]).append("/"+datas[3]).append("/"+datas[4]).append("/"+datas[5]);
                  dataSend(sendData.toString());
                  break;
               case -1 :
                  //차례가 아닌 사람이 돌을 놨을 때 입니당 돌 배치 못함
                  sendData.append(datas[0]).append("/500").append("/"+datas[2]).append("/"+datas[3]).append("/"+datas[4]).append("/"+datas[5]);
                  dataSend(sendData.toString());
                  break;
               case 1 :
                  //승리했어요. 돌 배치 함.
                  curRoom.setStarted(false);
                  sendData.append(datas[0]).append("/118").append("/"+datas[2]).append("/"+datas[3]).append("/"+datas[4]).append("/"+datas[5]);
                  broadCast(sendData.toString());
                  break;
               case 0 :
                  //승리하지 않았어용(다른 조건은 위배하지 않음. 게임 계속 진행), 돌 배치 함.
                  if(userOrder == true) //흑 : true, 백 : false
                     sendData.append(datas[0]).append("/105").append("/"+datas[2]).append("/"+datas[3]).append("/"+datas[4]).append("/"+datas[5]);
                  else
                     sendData.append(datas[0]).append("/106").append("/"+datas[2]).append("/"+datas[3]).append("/"+datas[4]).append("/"+datas[5]);
                  broadCast(sendData.toString());
                  break;
               }

               break;
               //사람이 나간다.
            case "120" :
               //사람이 나가는 것을 해야함
               //방장이 나가면 어찌구 하는 것도 필요하고 ㅇㅋ?
               //Room에서 User들은 list로 되어 있기 때문에 방장 따로 설정하지 않고 0번 째 아이가 방장으로 자동 설정
               RoomManager.getRoomList().get(roomId).subUser(null);

               sendData.setLength(0);
               sendData.append(datas[0]).append("200").append(datas[2]).append(datas[3]).append(datas[4]).append(datas[5]);

               dataSend(sendData.toString());
               break;
               
            case "201":
            		sendData.setLength(0);
            		sendData.append("/201/");
            		sendData.append(this.nickname);
            		sendData.append("/");
            		sendData.append(datas[2]);
	        		broadCast(sendData.toString());
	        		break;
	        		
            case "300": // Login
            		String id = datas[2];
            		String pw = datas[3];
            		sendData.setLength(0);
            		sendData.append("/300/");
            		
            		if((nickname = UserRepository.login(server.getConMgr(), id, pw)) != null) {
            			sendData.append("1/")
            				.append(nickname);
            		} else {
            			sendData.append("0/");
            		}
            		dataSend(sendData.toString());
            		break;
            		
            case "301": // Join
	            	String id2 = datas[2];
	        		String pw2 = datas[3];
	        		String nickname = datas[4];
	        		
	        		sendData.setLength(0);
	        		sendData.append("/301/");
	        		if(UserRepository.addUser(server.getConMgr(), id2, pw2, nickname)) {
	        			sendData.append("1");
	        		} else {
	        			sendData.append("0");
	        		}
	        		dataSend(sendData.toString());
            		break;
            case "302": // Duplicate ID
	            	String id3 = datas[2];
	        		
	        		sendData.setLength(0);
	        		sendData.append("/302/");
	        		if(!UserRepository.checkExistId(server.getConMgr(), id3)) {
	        			sendData.append("1");
	        		} else {
	        			sendData.append("0");
	        		}
	        		dataSend(sendData.toString());
	        		break;
            case "303": // Duplicate Nickname
	            	String nickname2 = datas[2];
	        		
	        		sendData.setLength(0);
	        		sendData.append("/303/");
	        		if(!UserRepository.checkExistNickname(server.getConMgr(), nickname2)) {
	        			sendData.append("1");
	        		} else {
	        			sendData.append("0");
	        		}
	        		dataSend(sendData.toString());
	        		break;
            }

            if(datas[0].equals(""))
               isThread = false;
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   public void dataSend(String sendData) {
      try {
         //전송 코드에 클라이언트 아이디 들어가야함! 그거 추가해야함
         //지금 코드 양식 -> 코드/방번호/회원번호/x축위치/y축위치
         //전송 코드에 클라이언트 아이디 들어가야함
         //변경 코드 양식 -> 클라이언트아이디/코드/방번호/회원번호/x축위치/y축위치
         //sendData에 있는 클라이언트 아이디 split 가져와서 아이디 가져옴
         //System.out.println("서버 발신 코드 : "+sendData);
         dataOutputStream.writeUTF(sendData);

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void broadCast(String sendData) {
      for(ClientThread c : server.getClients())
         c.dataSend(sendData);
   }



   public DataInputStream getDataInputStream() {
      return dataInputStream;
   }

   public void setDataInputStream(DataInputStream dataInputStream) {
      this.dataInputStream = dataInputStream;
   }

   public DataOutputStream getDataOutputStream() {
      return dataOutputStream;
   }

   public void setDataOutputStream(DataOutputStream dataOutputStream) {
      this.dataOutputStream = dataOutputStream;
   }


}