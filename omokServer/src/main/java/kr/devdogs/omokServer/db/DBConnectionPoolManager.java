package kr.devdogs.omokServer.db;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.Vector;

public class DBConnectionPoolManager {
	static private DBConnectionPoolManager instance;
	private Vector<String> drivers = new Vector<String>();
	private Hashtable<String, DBConnectionPool> pools = new Hashtable<String, DBConnectionPool>();
	
	/*
	static synchronized public DBConnectionPoolManager getInstance(){
		if(instance == null){
			instance = new DBConnectionPoolManager();
		}
		
		return instance;
	}
	���ҿ� ���� ����ȭ ������尡 ���ϴ�*/ 
	
	private DBConnectionPoolManager(){}
	
	//singleton lazyHolder�� ����
	public static DBConnectionPoolManager getInstance(){
		return LazyHolder.INSTANCE;
	}
	
	private static class LazyHolder {
		private static final DBConnectionPoolManager INSTANCE = new DBConnectionPoolManager();
	}
	
	public void freeConnection(String name, Connection con){
		DBConnectionPool pool = (DBConnectionPool)pools.get(name);
		if(pool!=null) pool.freeConnection(con);
		
		System.out.println("One Connection of "+ name +" was freed");
	}
	
	public Connection getConnection(String name){
		DBConnectionPool pool = (DBConnectionPool)pools.get(name);
		if(pool!=null){

			System.out.println("Get One Connection of "+ name);
			
			return pool.getConnection(10);
		}
		
		return null;
	}
	
	private void createPools(String name, String url, String user, String pw, int maxConn, int initConn, int maxWait){
		DBConnectionPool pool = new DBConnectionPool(name, url, user, pw, maxConn, initConn, maxWait);
		pools.put(name, pool);
		
		System.out.println("Initialized pool " + name);
	}
	
	public void loadDrivers(String driver){
		try{
			Class.forName(driver);
			drivers.addElement(driver);
			
			System.out.println("Registered JDBC driver " + driver);
		}catch(Exception e){
			e.printStackTrace();
			
			System.out.println("Can't register JDBC driver: " + driver);
		}
	}
	
	public void init(String name, String driver, String url, String user, String pw, int maxConn, int initConn, int maxWait){
		loadDrivers(driver);
		createPools(name, url, user, pw, maxConn, initConn, maxWait);
	}
	
	public Hashtable<String, DBConnectionPool> getPools(){
		return pools;
	}
	
	public int getDriverNumber(){
		return drivers.size();
	}
	
}
