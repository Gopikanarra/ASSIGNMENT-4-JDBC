package jdbcpacks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PrepDelete {
	
	public static void Deletion(String tablename) {
		Connection c=null;
		PreparedStatement pstmt=null;
		Scanner sc=new Scanner(System.in);
		String query="delete from "+tablename+" where emp_id=?";
		
		System.out.println("enter the id to delete");
		int id=sc.nextInt();
		try {
			c=JdbcConnection.getConnected();
			if(c!=null) {
				pstmt=c.prepareStatement(query);
				pstmt.setInt(1,id);
				if(pstmt!=null) {
					int rows=pstmt.executeUpdate();
					if(rows>0) {
						System.out.println("rows deleted="+rows);
					}
					else {
						System.out.println("0 rows are deleted!!");
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
