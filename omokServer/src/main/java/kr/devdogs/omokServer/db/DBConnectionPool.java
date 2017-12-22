package kr.devdogs.omokServer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

public class DBConnectionPool {
	
	private int count; //���� ������� Connection ����
	private Vector<Connection> freeConnections = new Vector<Connection>(); //free connection list
	private int maxConn; //�ִ� Ŀ�ؼ� ����
	private int initConn; //�ʱ� Ŀ�ؼ� ���� 
	private int maxWait; //Ǯ�� Ŀ�ؼ��� ���� �� ��ٸ� �ð�
	private String name; //Ŀ�ؼ� Ǯ �̸�
	private String url; //DB url
	private String user; //DB user ID
	private String pw; //DB password
	
	public DBConnectionPool(String name, String url, String user, String pw, int maxConn, int initConn, int maxWait){
		this.name = name;
		this.url = url;
		this.pw = pw;
		this.user = user;
		this.maxConn = maxConn;
		this.maxWait = maxWait;
		
		for(int i=0; i<initConn; i++){
			freeConnections.addElement(newConnection());
		}
	}
	
	private Connection newConnection(){
		Connection con = null;
		try{
			if(user==null)	con = DriverManager.getConnection(url);
			else con = DriverManager.getConnection(url,user,pw);
			
			System.out.println("Created a new connection in pool " + name);
			
		}catch(SQLException e){
			e.printStackTrace();
			
			System.out.println("Can't create a new connection in pool " + name);
		}
		return con;
	}
	
	public synchronized Connection getConnection(){	//this ��ü ��ȣ
		Connection con = null;
		if(freeConnections.size()>0){
			con = (Connection)freeConnections.firstElement();
			freeConnections.removeElementAt(0);
			
			try {
                // DBMS�� ���� Connection�� close �Ǿ����� �ٽ� �䱸
                if (con.isClosed()) {
                    con = getConnection();
                    
                    System.out.println("Removed bad connection from " + name);
                }
            } // ����� Connection �߻��ϸ� �ٽ� �䱸
            catch (SQLException e) {
                con = getConnection();
                
                System.out.println("Removed bad connection from " + name);
            }
		}
		else if(maxConn==0 || count<maxConn){
			con = newConnection();
		}
		if(con != null){
			count++;
		}
		return con;
	}
	
	public synchronized Connection getConnection(long timeout) {
		long startTime = new Date().getTime();
		Connection con;
		while((con = getConnection())==null){
			try{
				wait(timeout*maxWait);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			if((new Date().getTime() - startTime) >= timeout) {
				return null;
			}
		}
		
		return con;
	}
	
	public synchronized void freeConnection(Connection con){
		freeConnections.addElement(con);
		count--;
		notifyAll();
	}
	
}
