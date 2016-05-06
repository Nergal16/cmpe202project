import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class RoomServiceRevews implements Review {
	JLabel name,ageLabel,ratingLabel,describe;
	JPanel panel;
	int y =0;
	public RoomServiceRevews() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void printItems(){
		HotelParis.frame.setTitle("Hotel Paris");
		HotelParis.frame.getContentPane().removeAll();
		HotelParis.frame.getContentPane().repaint();
//		panel=HotelParis.frame.getContentPane();
		panel=new JPanel();
		panel.setLayout(new GridBagLayout());
		displayData();
		JButton backButton=new JButton("Go Back");
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				HotelParis.createReviewGUI();
			}
			
		});
		panel.add(backButton, new GridBagConstraints(0,	y++, 1, 1, 1.0, 1.0, GridBagConstraints.LAST_LINE_START, GridBagConstraints.VERTICAL , new Insets(0,0,0,0), 1, 1));
		JScrollPane listScroller = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			      JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    HotelParis.frame.setVisible(true);
        HotelParis.frame.add(listScroller);      
	    HotelParis.frame.repaint();
		HotelParis.frame.setVisible(true);
	}
	public void displayData(){
		Connection con = null;  
		try {
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/review","root","root");
		} catch(Exception e) {
			e.printStackTrace();
		}
		Statement stmt = null;
		String query = 
				"select * from hotelreview where dept='rooms'";
		int count=0;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				String fname = rs.getString("username");
				int age = rs.getInt("age");
				int rating = rs.getInt("rating");				
				String description = rs.getString("description");
				
				JLabel review=new JLabel();				
				review.setText("<html>"+fname+", "+age+"<br> Rating: "+rating+"/5<br>"+description+"<br>");
				review.setFont(new Font("Serif", Font.PLAIN, 18));
				  
				if(count%2!=0){
					review.setBackground(Color.GRAY);
					review.setOpaque(true);
				}
				Border border = BorderFactory.createRaisedBevelBorder();
				review.setBorder(border);			
				panel.add(review, new GridBagConstraints(0,	y++, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, 1, new Insets(1,1,1,1), 1, 1));
				
				count++;
			}
		}catch(Exception se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
	}
}