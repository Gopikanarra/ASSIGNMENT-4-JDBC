package jdbcpacks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepRead {
	
	public static void retreving(String tablename,int count) {
		Connection c=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int loopcount=0;
		String query = "SELECT * FROM " + tablename;

		try {
			c=JdbcConnection.getConnected();
			if(c!=null) {
				pstmt=c.prepareStatement(query);
				if(pstmt!=null) {
					rs=pstmt.executeQuery();
					
					if(rs!=null) {
						System.out.println("e_id\te_name");
						while(rs.next() && loopcount<count) {
							System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
							loopcount++;
						}
					}
					else {
						System.out.println("data can't be fetched!!!");
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcConnection.closeConnection(rs, pstmt, c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
