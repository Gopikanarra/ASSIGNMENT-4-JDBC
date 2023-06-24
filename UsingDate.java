import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import jdbcpacks.JdbcConnection;

import java.text.*;
public class UsingDate {
	static Scanner sc=new Scanner(System.in);
	String name=null;
	String address=null;
	String gender=null;
	String dob=null;
	String doj=null;
	String dom=null;
	Connection c=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	public static void main(String[] args) throws ParseException {
		UsingDate as=new UsingDate();
		System.out.println("enter your choice :-\n 1.insertion()\n2.retreival\n3.exit");
		boolean flag=true;
		
		while(flag) {
			System.out.println("enter u r choicw:");
			int ch=sc.nextInt();
		switch(ch) {
		case 1:as.insertion();
				break;
		case 2:as.retreival();
					break;
		case 3:System.out.println("u r exiting!!!bye:)");
				flag=false;
				System.exit(1);
		default:System.out.println("enter a valid one!!");
		break;
		}
		}

	}
	public void insertion() throws ParseException {
		System.out.println("enter the name:");
		 name=sc.next();
		 
		System.out.println("enter the addresss:");
		 address=sc.next();
		System.out.println("enter the gender:");
		 gender=sc.next();
		System.out.println("enter the date of birth:");
		 dob=sc.next();
		 System.out.println("enter the date of joining:");
		 doj=sc.next();
		 System.out.println("enter the date of marriage:");
		 dom=sc.next();
		 
		 SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		 java.util.Date d1=sdf1.parse(dob);
		 SimpleDateFormat sdf2=new SimpleDateFormat("MM-dd-yyyy");
		 java.util.Date d2=sdf2.parse(doj);
		 
		 long time1=d1.getTime();
		 long time2=d2.getTime();
		 
		 java.sql.Date sd1=new java.sql.Date(time1);
		 java.sql.Date sd2=new java.sql.Date(time2);
		 java.sql.Date sd3=java.sql.Date.valueOf(dom);
		 
		 String query="insert into details(name,address,gender,dob,doj,dom) values(?,?,?,?,?,?)";
		 try {
			 c=JdbcConnection.getConnected();
			 if(c!=null) {
				
				 pstmt=c.prepareStatement(query);
				 if(pstmt!=null) {
					 pstmt.setString(1,name);
					 pstmt.setString(2,address);
					 pstmt.setString(3,gender);
					 pstmt.setDate(4,sd1);
					 pstmt.setDate(5,sd2);
					 pstmt.setDate(6,sd3);
					
					int rows= pstmt.executeUpdate();
					System.out.println("no of rows updated ="+rows);
				 }
				 else {
					 System.out.println("cn't updated!!");
				 }
			 }
		 
		 }catch(Exception e) {
			 e.printStackTrace();
			 
		 }
		 finally{
			 try {
				JdbcConnection.closeConnection(null, pstmt, c);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }	 
	}
	
	public void retreival() {
		String query="select * from details where name=?";
		
		System.out.println("enter the name to retreive the detais:");
		String n=sc.next();
		try {
			c=JdbcConnection.getConnected();
			if(c!=null) {
				
				pstmt=c.prepareStatement(query);
				if(pstmt!=null) {
					pstmt.setString(1, n);
				 rs=pstmt.executeQuery();
				 if(rs!=null) {
					 System.out.println("name\taddress\t\tgender\tdob\t\tdoj\t\tdom");
				 while( rs.next()) {
					 java.sql.Date d=rs.getDate(4);
					 SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
					 String dob=sdf.format(d);
					 java.sql.Date d2=rs.getDate(5);
					 SimpleDateFormat sdf2=new SimpleDateFormat("MM-dd-yyy");
					 String doj=sdf2.format(d2);
					 java.sql.Date d3=rs.getDate(6);
					 
					 SimpleDateFormat sdf3=new SimpleDateFormat("yyy-MM-dd");
					 String dom=sdf2.format(d3);
					 System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t"+dob+"\t"+doj+"\t"+dom);
					 
				 }
				 }
				 else {
					 System.out.println("no data found!!");
				 }
				}
				
			}
		}catch(Exception e) {
			
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
