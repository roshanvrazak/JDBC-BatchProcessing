package org.rvr.batchApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class JdbcBatch {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		String inqry = "insert into students_info values(" + "10,'stmt','batch','y','root')";
		String upqry = "update students_info set password='abcd' where sid=5";

		ResultSet rs = null;
		try {
			/*
			 * 1. Load the Driver
			 */
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");
			/*
			 * 2. Get the DB Connection via Driver
			 */
			String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db" + "?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			con.setAutoCommit(false);
			System.out.println("Connected...");
			/*
			 * 3. Issue the SQL query via connection
			 */

			stmt = con.createStatement();
			stmt.addBatch(inqry);
			stmt.addBatch(upqry);
			int ar[] = stmt.executeBatch();

			/*
			 * 4. Process the results
			 */

			for (int i : ar) {
				System.out.println(i);
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			/*
			 * 5. Close all the JDBC Objects
			 */

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
