package jdbcnew;
import java.sql.*;
public class jdbcexample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("welcome");
try{  
Class.forName("com.mysql.jdbc.Driver");  
System.out.println("Driver created");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo?characterEncoding=latin1","root","Niraj@123");  
System.out.println("connection created");
//here sonoo is database name, root is username and password  
Statement stmt=con.createStatement();  
System.out.println("statement created");
ResultSet rs=stmt.executeQuery("select * from emp");  
while(rs.next())  
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
con.close();  
}catch(Exception e){ System.out.println(e);}  

	}

}
