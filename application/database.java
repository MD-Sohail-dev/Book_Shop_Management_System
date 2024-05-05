package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class database 
{
   public static Connection ConnectDb()
   {
	   try
	   {
		 Class.forName("com.mysql.cj.jdbc.Driver");
Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Book?useSSL=false","root","system"); 
return connect;


	   }catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return null;
   }
}
