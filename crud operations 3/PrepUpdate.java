package jdbcpacks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PrepUpdate {
	
	public static void updation(String tablename) {
		Connection c=null;
		PreparedStatement pstmt=null;
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the id to update :");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("entre the name:");
		String name=sc.nextLine();
		
		String query="update "+tablename+" set emp_name=? where emp_id=?";
		try {
			c=JdbcConnection.getConnected();
			if(c!=null) {
				pstmt=c.prepareStatement(query);
				pstmt.setString(1,name);
				pstmt.setInt(2,id);
				if(pstmt!=null) {
					int rows=pstmt.executeUpdate();
					if(rows>0) {
						System.out.println("the no of rows updated are:"+rows);
					}
					else {
						System.out.println("no rows are updated!!");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
