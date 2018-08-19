package org.rvr.batchApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Driver;

public class JdbcPrepBatch {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String inqry = "insert into students_info values(?,?,?,?,?)";
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		try {

			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");

			String dbUrl = "jdbc:mysql://localhost:3306/capsV3_db" + "?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(inqry);

			pstmt.setInt(1, 8);
			pstmt.setString(2, "pstmt1");
			pstmt.setString(3, "pstmt1");
			pstmt.setString(4, "y");
			pstmt.setString(5, "root");
			pstmt.addBatch();

			pstmt.setInt(1, 9);
			pstmt.setString(2, "pstmt2");
			pstmt.setString(3, "pstmt2");
			pstmt.setString(4, "n");
			pstmt.setString(5, "root");
			pstmt.addBatch();

			int ar[] = pstmt.executeBatch();

			for (int i : ar) {
				System.out.println(i);
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			sc.close();
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
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
