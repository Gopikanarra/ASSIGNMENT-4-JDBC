package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;
public class JdbcOperations {
	static Scanner sc=new Scanner(System.in);
	static String table_name;
	static String url="jdbc:mysql://localhost:3306/ineuron";
	static String user="root";
	static String password="gopikapassword";
	
	
	public void createTable(String table_name) throws SQLException {
				
		String query=String.format("create table %s ("+
				"s_id INT PRIMARY KEY,"+
				"s_name varchar(20),"+
				"s_dept varchar(20),"+
				"s_marks INT"+
				")",table_name);

		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 Connection connection = DriverManager.getConnection(url, user, password); 
		 
	            // Create a statement object
	            Statement statement = connection.createStatement();

	            // Execute the create table query
	            statement.executeUpdate(query);

	            System.out.println("Table created successfully.");
	        }  catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		  	 
		
	}
	
		public void insertData(String table_name) throws SQLException{
		System.out.println("enter student id:");
		int s_id=sc.nextInt();
		System.out.println("enter student name");
		String s_name=sc.next();
		System.out.println("enter student dept :");
		String s_dept=sc.next();
		System.out.println("enter student marks:");
		int s_marks=sc.nextInt();
		String query=String.format("insert into %s values(%d,'%s','%s',%d)",table_name,s_id,s_name,s_dept,s_marks);
		
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 Connection connection = DriverManager.getConnection(url, user, password);
	            // Create a statement object
	            Statement statement = connection.createStatement();

	            // Execute the create table query
	            int status=statement.executeUpdate(query);

	            if(status!=0) {
					System.out.println("no.of rows updated="+status);
				}
				else {
					System.out.println("no rows are updated !!");
				}
	        }catch (SQLIntegrityConstraintViolationException e) {
		        
		        System.out.println("Error: Duplicate entry found for primary key.");
	        } 
		 catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		 
		}
	public void updateData(String table_name)throws SQLException{
//		System.out.println("enter the id to update");
//		int num=sc.nextInt();
//		System.out.println("enter the column for the update:");
//		String sname=sc.next();
//		String query=String.format("update %s set s_name=%s WHERE s_id=%d",table_name,sname,num);
//		
		  System.out.println("Enter the id to update:");
	        int num = sc.nextInt();
	        System.out.println("Enter the column for the update:");
	        String columnName = sc.next();
	        System.out.println("Enter the new value:");
	        String newValue = sc.next();

	        String query = String.format("UPDATE %s SET %s = '%s' WHERE s_id = %d",
	                table_name, columnName, newValue, num);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
		
            // Create a statement object
            Statement statement = connection.createStatement();

            // Execute the create table query
            int status=statement.executeUpdate(query);

            if (status > 0) {
                System.out.println("Data updated successfully.");
            } else {
                System.out.println("No data found for the given ID.");
            }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	  	 catch(Exception e) {
	  		 e.printStackTrace();
	  	 }
	}
	public void deleteData(String table_name) throws SQLException {
		System.out.println("enter the id to delete");
		int num=sc.nextInt();
		String query=String.format("DELETE FROM %s WHERE s_id=%d",table_name,num);
		
	    
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);
		
            // Create a statement object
            Statement statement = connection.createStatement();

            // Execute the create table query
            int status=statement.executeUpdate(query);

            if (status > 0) {
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("No data found for the given ID.");
            }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	  	 catch(Exception e) {
	  		 e.printStackTrace();
	  	 }
	  	   
	
}
	
public void displayData(String table_name) throws SQLException {
		
		String query=String.format("select * from %s", table_name);
		
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection connection = DriverManager.getConnection(url, user, password);
		 
	            // Create a statement object
	            Statement statement = connection.createStatement();

	            // Execute the create table query
	            ResultSet rs = statement.executeQuery(query);

	            System.out.println("s_id\ts_name\ts_dept\ts_marks");
	    		while(rs.next()) {
	    			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4));
	    		}

	            
	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	}

	
	public static void main(String[] args) throws SQLException {
		JdbcOperations j=new JdbcOperations();
		int repeat=1;
		System.out.println("enter the table name");
		   table_name=sc.next();
		while(repeat==1) {
		System.out.println("1.create\n2.insert\n3.update\n4.delete\n5.display\n6.exit");
		System.out.println("enter your choice:");
		int  ch=sc.nextInt();
		switch(ch) {
		case 1:
			   j.createTable(table_name);
				break;
		case 2:j.insertData(table_name);
				break;
		  	   
		case 3:	j.updateData(table_name);  
				break;
		case 4:j.deleteData(table_name);
				break;
		case 5:j.displayData(table_name);
				break;
		case 6:System.out.println("u r exiting !! byeee");
				repeat=0;
				System.exit(0);
			
		}
		}
		
	}
}


