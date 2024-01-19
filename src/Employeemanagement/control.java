package Employeemanagement;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class control 
{
	Scanner s1=new Scanner(System.in);
public  int start() 
{
System.out.println("------Navigation-----");
System.out.println("press 1 for  ---> CREATE");
System.out.println("press 2 for  ---> READ");
System.out.println("press 3 for  ---> UPDATE");
System.out.println("press 4 for  ---> DELETE");
int a=s1.nextInt();
 return a;
}	
public static void main(String[] args) throws ClassNotFoundException, SQLException 
{
    control c1=new control();
	int operation=c1.start();
Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con= DriverManager.getConnection("jdbc:mysql:///database1?user=root&password=root");
	switch(operation)
	{
	case 1:
	{
		PreparedStatement pst=con.prepareStatement("insert into employee_management values(?,?,?,?)");
		System.out.print("Enter the EMPID to insert : ");
		int id=c1.s1.nextInt();
		System.out.print("Enter the EMPLOYEE_NAME to insert : ");
		String name=c1.s1.next();
		c1.s1.nextLine();
		System.out.print("Enter the EMPROLL to insert : ");
		String roll=c1.s1.nextLine();
		System.out.print("Enter the EMP_CONTACTNUMBER to insert : ");
		long number=c1.s1.nextLong();
		pst.setInt(1, id);
		pst.setString(2, name);
		pst.setString(3, roll);
		pst.setLong(4, number);
		int result=pst.executeUpdate();
		System.out.println("CONGRATULATION"+" "+name+"!!!"+" YOUR DATA WAS ADDED SUCESSFULLY");
		break;
	}
	case 2:
	{
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from employee_management");
		while(rs.next())
		{
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getLong(4));	
		}
		break;
	}
	case 3:
	{
		 
		PreparedStatement pst= con.prepareStatement("update employee_management set EMP_CONTACT=? where EMPID=?");
		System.out.println("which Employee's Number want to update enter those details");
		System.out.println("EMPLOYEE_NAME: ");
		String name=c1.s1.nextLine();
		System.out.println("EMPID: ");
		int id=c1.s1.nextInt();
		System.out.print("Enter the contact number to update : ");
		long number=c1.s1.nextLong();
		pst.setInt(2, id);
		pst.setLong(1, number);
	   pst.executeUpdate();
	   System.out.println("CONGRATULATION"+" "+name+"!!!"+" YOUR DATA WAS ADDED SUCESSFULLY");
	   break;
	}
	case 4:
	{
		PreparedStatement pst=con.prepareStatement("delete from students where id=?");
		System.out.println("which Employee's data want to delete. enter those details");
		System.out.println("EMPLOYEE_NAME: ");
		String name=c1.s1.nextLine();
		System.out.println("EMPID: ");
		int id=c1.s1.nextInt();
		pst.setInt(1, id);
		pst.executeUpdate();
		 System.out.println("HELLO"+" "+name+"!!!"+" YOUR DATA WAS BEEN DELETED");
		 break;
	}
	default:
	{
		System.out.println("YOUR INPUT "+operation+" IS NOT VALID");
		System.out.println("PLEAE ENTER THE INPUT BETWEEN 1-4");
		main(args);
	}
	
	}
	
}

}
