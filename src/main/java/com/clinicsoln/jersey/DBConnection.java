package com.clinicsoln.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	boolean insertStatus = false;
	static Connection dbConn = null;
	static ResultSet rs;
	static Statement stmt;
	/**
	 * Method to create DB Connection
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Inside createConnection catch e ");
			throw e;
		} finally {
			return con;
		}
	}
	
	public static ResultSet getPatient(String patientName) throws SQLException, Exception {
		
		try {
			
				dbConn = DBConnection.createConnection();
			
				// TODO Auto-generated catch block
				
			
			stmt = dbConn.createStatement();
			String query = "SELECT * FROM patient ";
			
			rs = stmt.executeQuery(query);
			System.out.println("Inside getPatient Detail");
			/**while (rs.next()) {
				System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
				
			} */
			 
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
			//	dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
			//	dbConn.close();
			}
		}
		return rs;
	}
	
	public static void closeAllConnection() throws Exception{
			if(rs!=null) {
			   rs.close();
			}
			if(stmt!=null) {
			   stmt.close();
			}
	       if(dbConn!=null) {
	    	   dbConn.close();
	       }	
	}

    /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     
	public static boolean checkLogin(String uname, String pwd) throws Exception {
		boolean isUserAvailable = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM user WHERE username = '" + uname
					+ "' AND password=" + "'" + pwd + "'";
			//System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				//System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
				isUserAvailable = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return isUserAvailable;
	} 
	*/
	/**
	 * Method to insert uname and pwd in DB
	 * 
	 * @param name
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	
}