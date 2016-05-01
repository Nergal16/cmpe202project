
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HotelReservation {
	 static JTextArea textAreaGuest;
	  static JFrame frame;
	   static Container pane;

	public void makeReservationGUI(){
		
	        
	        final JButton confirmButt = new JButton("Confirmed");
	        confirmButt.setEnabled(false);
	        JButton transactionDoneButt = new JButton("Transaction Done");
	        JButton backButt = new JButton ("Home");
	        final JButton luxButt = new JButton ("$200");
	        final JButton econButt = new JButton ("$100");
	        
	        JLabel checkInLabel = new JLabel("Check-in:");
	        JLabel checkOutLabel = new JLabel("Check-out:");
	        JLabel roomType = new JLabel("Room type:");
	        final JLabel errorMessage = new JLabel();
	        
	        textAreaGuest = new JTextArea();
	        textAreaGuest.setEnabled(false);
	        textAreaGuest.setText("Room information will be printed here as \n"
	                + "you select the check-in and check-out \ndates and room type.");
	        
	        final JTextField checkInTextField = new JTextField(10);
	        final JTextField checkOutTextField = new JTextField(10);
	        
	        frame.setTitle("ChampsElysees Hotel - Make Transaction");
	        pane = frame.getContentPane(); //Get content pane
	        pane.setLayout(null); //Apply null layout
	        
	        errorMessage.setBounds(25, 200, 200, 50);
	        checkInLabel.setBounds(50, 40, 75, 25);
	        checkOutLabel.setBounds(150, 40, 75, 25);
	        confirmButt.setBounds(280, 300, 150, 50);
	        transactionDoneButt.setBounds(480, 300, 150, 50);
	        backButt.setBounds(10, 300, 75, 50);
	        checkInTextField.setBounds(50, 60, 80, 25);
	        checkOutTextField.setBounds(150, 60, 80, 25);
	        roomType.setBounds(50, 100, 80, 25);
	        luxButt.setBounds(50, 120, 80, 50);
	        econButt.setBounds(150, 120, 80, 50);
	        textAreaGuest.setBounds(280, 40, 350, 250);
	                
	        
	        
	        //add listener to text field -- serves as Controller in MVC pattern
	        checkInTextField.addActionListener( 
	            new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent event) {
	               
	                }//actionPerformed
	            }//ActionListener
	        );
	             
	        //add listener to text field -- serves as Controller in MVC pattern
	        checkOutTextField.addActionListener( 
	            new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent event) {
	             
	                }//actionPerformed
	            }//ActionListener
	        );    
	        
	        //add action listener -- serves as Controller in MVC pattern
	        luxButt.addActionListener( 
	            new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent event) {
	                    //luxury room
	                    //set today's date to compare against user selection dats
	                  
	                }});
	        
	        //add action listener -- serves as Controller in MVC pattern
	        //textAreaGuest is updated upon changes -- serves as View in MVC
	        econButt.addActionListener( 
	            new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent event) {
	                    //set today's date to compare against user selection dats
	                   
	                }});
	        
	        //add action listener -- serves as Controller in MVC pattern
	        //textAreaGuest is updated upon changes -- serves as View in MVC
	        confirmButt.addActionListener( 
	            new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent event) {
	                    //confirm transaction
	                     
	                    }
	                });
	        
	        //add action listener
	        transactionDoneButt.addActionListener( 
	            new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent event) {
	                    //transaction done
	                  
	                }});        
	        
	        //associate go back to its button
	        backButt.addActionListener( 
	            new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent event) {
	                   //action performed
	                }});        
	        
	        frame.add(errorMessage);
	        frame.add(confirmButt);
	        frame.add(transactionDoneButt);
	        frame.add(backButt);
	        frame.add(luxButt);
	        frame.add(econButt);
	        frame.add(checkInLabel);
	        frame.add(checkOutLabel);
	        frame.add(checkInTextField);
	        frame.add(checkOutTextField);
	        frame.add(roomType);
	        frame.add(textAreaGuest);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.repaint();

	}
}
