package booking;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DatabaseConnector {

	public Connection connect() throws SQLException{
		
		Connection conn =null;
		conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/reservations","root","admin");
		if(conn!=null)
		{
			System.out.println("database connection succeessfull");
			
		}
		return conn;
	}
	 public ResultSet query(String qu) throws SQLException{
		  Connection connection=new DatabaseConnector().connect();
		   Statement statement;
		   ResultSet res = null;
		   
		            statement = (Statement) connection.createStatement();
		            res = statement.executeQuery(qu);
		         if(res!=null)
		        	 return res;
				return null;
		
	}
}
