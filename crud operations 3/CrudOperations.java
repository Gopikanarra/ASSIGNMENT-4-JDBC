package jdbcpacks;
import java.util.Scanner;
public class CrudOperations {
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean flag=true;
		while(flag) {
			System.out.println("menu\n1.create\n2.Read\n3.update\n4.delete\n5.exit");
			System.out.println("enter your choice:");
			sc.nextLine();
			String choice=sc.nextLine();
			if(choice=="\n") {
				continue;
			}
			else {
                switch (choice) {
                    case "1":
                        perpCreate.createTable("employee");
                        
                        break;
                    case "2":
                        int counting = CountDemo.count("employee");
                        PrepRead.retreving("employee", counting);
                      
                        break;
                    case "3":
                        PrepUpdate.updation("employee");
                        
                        break;
                    case "4":
                        PrepDelete.Deletion("employee");
                        
                        break;
                    case "5":System.out.println("u r exiting !! byeee");
    							flag=false;
    							
                    	
                }
            }
		}
        }
    }

