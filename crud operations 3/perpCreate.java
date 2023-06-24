package jdbcpacks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class perpCreate {
	
	public static void createTable(String tablename) {
		Connection c=null;
		PreparedStatement pstmt=null;
		String name=null;
		Scanner sc=new Scanner(System.in);
		int id=0;
		
		System.out.println("enter emp name:");
		name=sc.next();
		System.out.println("enter emp id:");
		id=sc.nextInt();
		String query = "insert into " + tablename + " values (?, ?)";

		try {
			c=JdbcConnection.getConnected();
			if(c!=null) {
				pstmt=c.prepareStatement(query);
				pstmt.setInt(1,id);
				pstmt.setString(2,name);
				if(pstmt!=null) {
					int rows=pstmt.executeUpdate();
					if(rows!=0) {
						System.out.println("rows successfully inserted:"+rows);
					}
					else {
						System.out.println("not created!!");
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcConnection.closeConnection(null, pstmt, c);
				sc.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
	

}
