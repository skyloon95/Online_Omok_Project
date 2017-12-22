package kr.devdogs.omokServer.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import kr.devdogs.omokServer.db.ConnectionManager;
import kr.devdogs.omokServer.db.UserRepository;

public class Server {
	private ServerSocket serverSocket;
	private List<Socket> clientSocket;
	private List<ClientThread> clients;
	private ConnectionManager conMgr ;

	public void serverSetting() {
		try {
			serverSocket = new ServerSocket(12347); // 바인드

			while(true) {
				if(clientSocket.size() < 2) {
					Socket tempSocket = serverSocket.accept();
					clientSocket.add(tempSocket);

					int socketId = clientSocket.size()-1;
					//접속한 클라이언트마다 쓰레드를 생성해준다.
					ClientThread client = new ClientThread(socketId, tempSocket, this);
					client.start();
					clients.add(client);
					client.getDataOutputStream().writeInt(socketId);
					//소켓 접속 완료 된 부분
				}

				if(clientSocket.size() == 2) {
					break;
				}
			}

		} catch (Exception e) {

		} 
	}

	public void closeAll(){
		try {
			for(Socket temp : clientSocket)
				temp.close();
			serverSocket.close();
		} catch (Exception e) {

		}
	}

	public Server() {
		conMgr = new ConnectionManager("mysql");
		clientSocket = new ArrayList<>();
		clients = new ArrayList<>();
		serverSetting();
	}

	public ConnectionManager getConMgr() {
		return this.conMgr;
	}

	public List<ClientThread> getClients() {
		return clients;
	}
}