package cg;

import java.sql.*;
import java.util.Scanner;

public class Demo6 {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement deleteSt=null;
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="hr";
			String pass="hr";
			
			con=DriverManager.getConnection(url, user, pass);
			System.out.println("Connected");
			con.setAutoCommit(false);	// tells that do not commit after every dml statement
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Account ID you want to delete");
			int id=sc.nextInt();
			
			// dynamic query
			String sqlQuery="delete from account where aid=?";
			
			deleteSt=con.prepareStatement(sqlQuery);
			deleteSt.setInt(1, id);
			
			
			int deletedRec=deleteSt.executeUpdate();
			System.out.println("Deleted Records "+deletedRec);
			
			con.commit();
			con.close();
			sc.close();
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage()+" "+e.getErrorCode()+" "+e.getSQLState());
			e.printStackTrace();
		}
	}

}
