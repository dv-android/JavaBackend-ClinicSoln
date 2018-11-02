package com.clinicsoln.jersey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
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
	public static ResultSet getPatient(String patientName) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		ResultSet rs;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM patient where patient_id = 121";
			
			rs = stmt.executeQuery(query);
			/**while (rs.next()) {
				System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
				
			}*/
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			//e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				//dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				//dbConn.close();
			}
		}
		return rs;
	}

}