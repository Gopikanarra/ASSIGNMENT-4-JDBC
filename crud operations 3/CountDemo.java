package jdbcpacks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountDemo {
	
	public static int count(String tablename) {
		Connection c = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM " + tablename;
		try {
			c = JdbcConnection.getConnected();
			if (c != null) {
				pstmt = c.prepareStatement(query);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					int count = rs.getInt(1);
					return count;
				} else {
					return 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcConnection.closeConnection(rs, pstmt, c);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
