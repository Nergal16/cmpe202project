package src.booking;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class BookingGUI {
	
	// intergration required from the other modules..
	    static JTextArea textAreaGuest;
	    static JFrame frame;
	   static Container pane;
	   public static void createReservationOrViewGUI() {
		   frame = new JFrame ();
	       frame.setLocation(500, 100); //open in center of screen
	       frame.setSize(680, 400);
	       frame.setResizable(false);
	       frame.setVisible(true);
	       frame.setTitle("Hotel Paris - Reservation");
	       pane = frame.getContentPane(); //content pane
	       pane.setLayout(null); //Apply null layout
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	       //create label for 'create an acount' and 'sign in'
	       JLabel ReserveRoomLabel = new JLabel("Reserve your room now");
	       JLabel viewOrCancelReservation = new JLabel("View your current "
	               + "reservation or Cancel a reservation");

	       JButton makeReservationButton = new JButton("Make a Reservation");
	       JButton viewOrCanceltButton = new JButton("View/Cancel a Reservation");
	       JButton backButton = new JButton ("Back");

	       frame.add(ReserveRoomLabel);
	       frame.add(viewOrCancelReservation);
	       frame.add(makeReservationButton);
	       frame.add(viewOrCanceltButton);
	       frame.add(backButton);
	       ReserveRoomLabel.setBounds(175, 75, 310, 25);
	       viewOrCancelReservation.setBounds(175, 175, 310, 25);
	       makeReservationButton.setBounds(175, 100, 310, 50);
	       viewOrCanceltButton.setBounds(175, 200, 310, 50);
	       backButton.setBounds(10, 300, 75, 50);
	       //set actionListener for button
	       makeReservationButton.addActionListener( 
	           new ActionListener() {
	               @Override
	               public void actionPerformed(ActionEvent event) {
	                   createMakeReservationGUI();
	               }//actionPerformed
	           }//ActionListener
	       );

	       
	   }
	   public static void createMakeReservationGUI() {

	       pane.removeAll();
	       
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
	   
	       frame.setTitle("Hotel Paris - Make Transaction");
	       pane = frame.getContentPane(); //Get content pane
	       pane.setLayout(null); //Apply null layout
	       UtilDateModel model = new UtilDateModel();
	       UtilDateModel modelO = new UtilDateModel();
	     //model.setDate(20,04,2014);
	     // Need this...
	     Properties p = new Properties();
	     p.put("text.today", "Today");
	     p.put("text.month", "Month");
	     p.put("text.year", "Year");
	     JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	     // Don't know about the formatter, but there it is...
	     JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
	     JDatePanelImpl datePanelO = new JDatePanelImpl(modelO, p);
	     // Don't know about the formatter, but there it is...
	     JDatePickerImpl datePickerO = new JDatePickerImpl(datePanelO, new DateComponentFormatter());
	      
	   //    Date selectedDate = (Date) datePicker.getModel().getValue();
	       
	       errorMessage.setBounds(25, 200, 200, 50);
	      checkInLabel.setBounds(30, 40, 75, 25);
	      datePicker.setBounds(100, 40, 140, 25);
	       checkOutLabel.setBounds(30, 80, 75, 25);
	       datePickerO.setBounds(100, 80, 140, 25);
	       confirmButt.setBounds(280, 300, 150, 50);
	       transactionDoneButt.setBounds(480, 300, 150, 50);
	       backButt.setBounds(10, 300, 75, 50);
	       //checkInTextField.setBounds(50, 60, 80, 25);
	     
	       roomType.setBounds(30, 120, 80, 25);
	       luxButt.setBounds(30, 140, 80, 50);
	       econButt.setBounds(150, 140, 80, 50);
	       textAreaGuest.setBounds(280, 40, 350, 250);
	  
	       
	       System.out.println("came here..");   
	       frame.repaint();      
	       //ChangeListener
	       ChangeListener listener = new
	           ChangeListener() {
	               @Override
	               public void stateChanged(ChangeEvent event) {
	            	   System.err.println("yess");
	            	 
	                 //  int arr[] = model.calculateAvailableRooms(checkInDate, checkOutDate);
	                  // String textToDisplay = calculateMessage(arr);
	                //   textAreaGuest.setText(textToDisplay);
	                  // frame.repaint(); 
	       }
	        
	       };
	      model.addChangeListener(listener);
	      modelO.addChangeListener(listener);
	    /**** model.addChangeListener(new
	               ChangeListener()  {
	           public void keyPressed(KeyEvent e) {
	             if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	               model.setSelected(false);
	             }
	           }

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	         });**/
	       
	       //add listener to text field -- serves as Controller in MVC pattern
	      
	       frame.add(errorMessage);
	       frame.add(confirmButt);
	       frame.add(transactionDoneButt);
	       frame.add(backButt);
	       frame.add(luxButt);
	       frame.add(econButt);
	       frame.add(checkInLabel);
	       frame.add(checkOutLabel);
	       frame.add(datePicker);
	      // frame.add(checkInTextField);
	       //frame.add(checkOutTextField);
	       frame.add(datePickerO);
	       frame.add(roomType);
	       frame.add(textAreaGuest);
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.repaint();
	      
	   }          
	}
