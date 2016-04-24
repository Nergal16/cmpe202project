package reviews;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class HotelReviews extends MainReviews {
	JLabel name,ageLabel,ratingLabel,describe;
	public HotelReviews() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void printItems(){
		Main.frame.setTitle("Hotel Paris");
		Main.frame.getContentPane().removeAll();
		Main.frame.getContentPane().repaint();
		Main.pane=Main.frame.getContentPane();
		displayData();
	}
	public void displayData(){
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
				"select * from hotelreview where dept='hotel'";
		int count=0;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				String fname = rs.getString("username");
				int age = rs.getInt("age");
				int rating = rs.getInt("rating");
				
				String description = rs.getString("description");
				
				//System.out.println(fname+" "+age+" "+rating+" "+description);
		
				JLabel review=new JLabel();

				//String rev=fname+",  "+age+", Rating:   "+rating+", "+description;
				//review.setText(rev);
				
				review.setText("<html>"+fname+", "+age+"<br> Rating: "+rating+"/5<br>"+description+"<br>");
				review.setFont(new Font("Serif", Font.PLAIN, 40));
	
				if(count%2!=0){
					review.setBackground(Color.GRAY);
					review.setOpaque(true);
				}
				Border border = BorderFactory.createRaisedBevelBorder();
				review.setBorder(border);
			    
				Main.pane.add(review);
				
				Main.frame.setLayout(new BoxLayout(Main.pane, BoxLayout.Y_AXIS));
				Main.frame.setSize(800, 500);
				Main.frame.setVisible(true);
				count++;
			}
		}catch(Exception se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
	}
}