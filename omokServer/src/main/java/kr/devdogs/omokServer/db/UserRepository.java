package kr.devdogs.omokServer.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {

	static public String login(ConnectionManager conMgr, String user_id, String user_pw){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			con = conMgr.getConnection();
			String sql = "SELECT user_id, user_pw, user_nickname FROM user WHERE user_id=? AND user_pw=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pw);
			rs = pstmt.executeQuery();
			if(rs.next()){
				//session.setAttribute("id",rs.getInt(1));
				//session.setAttribute("nickname",rs.getString(4));
				result = rs.getString("user_nickname");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			conMgr.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	static public boolean checkExistId(ConnectionManager conMgr, String user_id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try{
			con = conMgr.getConnection();
			String sql = "SELECT user_id FROM user WHERE user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) result = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			conMgr.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	static public boolean checkExistNickname(ConnectionManager conMgr, String nickname){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try{
			con = conMgr.getConnection();
			String sql = "SELECT user_nickname FROM user WHERE user_nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if(rs.next()) result = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			conMgr.freeConnection(con, pstmt, rs);
		}
		return result;
	}
	
	static public boolean addUser(ConnectionManager conMgr, String user_id, String user_pw, String nickname){
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try{
			con = conMgr.getConnection();
			if(checkExistId(conMgr, user_id)) result = false;
			if(checkExistNickname(conMgr, nickname)) result = false;
			else{
				String sql = "INSERT INTO user(user_id, user_pw, user_nickname) VALUES(?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setString(2, user_pw);
				pstmt.setString(3, nickname);
				result = pstmt.executeUpdate() == 1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			conMgr.freeConnection(con, pstmt);
		}
		return result;
	}
	
	static public boolean setUserRecord(ConnectionManager conMgr, String game, int id){
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try{
			con = conMgr.getConnection();
			String sql = null;
			if(game.equals("win")){
				sql = "UPDATE user SET win=win+1 WHERE id=?";
			}
			else if(game.equals("loose")){
				sql = "UPDATE user SET loose=loose+1 WHERE id=?";
			}
			else if(game.equals("draw")){
				sql = "UPDATE user SET draw=draw+1 WHERE id=?";
			}
			else return result;
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			result = true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			conMgr.freeConnection(con, pstmt);
		}
		return result;
	}
	
	static public int[] getUserRecord(ConnectionManager conMgr, int id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int[] result = new int[3];
		try{
			con = conMgr.getConnection();
			String sql = "SELECT win, loose, draw FROM user WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result[0] = rs.getInt(1);
				result[1] = rs.getInt(2);
				result[2] = rs.getInt(3);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			conMgr.freeConnection(con, pstmt);
		}
		return result;
	}

}
