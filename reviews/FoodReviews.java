package reviews;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FoodReviews extends MainReviews{

	public FoodReviews() {
		//super(item);
		super();
		// TODO Auto-generated constructor stub
	}
	public void printItems(){
		Connection con = null;  
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/review","root","root");
			//System.out.println("Connection established");
		} catch(Exception e) {
			e.printStackTrace();
		}
		Statement stmt = null;
		String query = 
				"select * from hotelreview where dept='food'";

		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String fname = rs.getString("username");
				int age = rs.getInt("age");
				int rating = rs.getInt("rating");
				
				String description = rs.getString("description");
				
				System.out.println(fname+" "+age+" "+rating+" "+description);
			}
		}catch(Exception se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
	}
}