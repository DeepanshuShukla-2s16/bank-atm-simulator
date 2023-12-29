package bank.managemant.system;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
	public Connection con;
	public Connection getConnection() {
		if(con==null) {
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShuklaDB","root","W@jkl#123Mnb");
				return con;
			}catch(Exception e) {
				System.out.println(e);
			}
			
		}
		else
		return con;
		return con;
	}
}
