package reviews;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;

import src.HotelParis;

public class FoodReviews extends MainReviews{
	JLabel name,ageLabel,ratingLabel,describe;
	public FoodReviews() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void printItems(){
		HotelParis.frame.setTitle("Hotel Paris");
		HotelParis.frame.getContentPane().removeAll();
		HotelParis.frame.getContentPane().repaint();
		HotelParis.pane=HotelParis.frame.getContentPane();
		JButton backButton=new JButton("Go Back");
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				HotelParis.createReviewGUI();
			}
			
		});
		backButton.setBounds(175,800,310,50);
		
		HotelParis.pane.add(backButton);
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
				"select * from hotelreview where dept='food'";
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
			/*	name=new JLabel();
				ageLabel=new JLabel();
				ratingLabel=new JLabel();
				describe= new JLabel();
				
				name.setText(fname);
				ageLabel.setText(String.valueOf(age));
				ratingLabel.setText(String.valueOf(rating));
				describe.setText(description);
			
				Main.pane.add(name);
				Main.pane.add(ageLabel);		
				Main.pane.add(ratingLabel);
				Main.pane.add(describe);
				*/
				JLabel review=new JLabel();

				//String rev=fname+",  "+age+", Rating:   "+rating+", "+description;
				//review.setText(rev);
				
				review.setText("<html>"+fname+", "+age+"<br>"+rating+"<br>"+description+"<br>");
				review.setFont(new Font("Serif", Font.PLAIN, 40));
	
				if(count%2!=0){
					review.setBackground(Color.GRAY);
					review.setOpaque(true);
				}
			
				Border border = BorderFactory.createRaisedBevelBorder();
			    review.setBorder(border);
				HotelParis.pane.add(review);
				HotelParis.frame.setLayout(new BoxLayout(HotelParis.pane, BoxLayout.Y_AXIS));
				HotelParis.frame.setVisible(true);
				count++;
			}
		}catch(Exception se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
	}
}
