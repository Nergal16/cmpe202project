package booking;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javafx.scene.control.CheckBox;

public class BookingGUI {
	
	
	// integration required from the other modules..
	    static JTextArea textAreaGuest = new JTextArea();
	    static JFrame frame =new JFrame ();
	   static Container pane;
	   static JLabel roomAvailable = new JLabel();
	   static String lastState ="";
	   static JButton confirmButt= new JButton("Book");
	   static ArrayList<Integer> num = new ArrayList<Integer>();
	   static  JComboBox ticketsNum= new JComboBox();
	  
	static CustomerDetails cust = new CustomerDetails();
	   public BookingGUI(String str, int len){
		   frame.repaint();
	        textAreaGuest.setText("Room available are :"+ str.split(" ")[0]);
	        cust.setRoomsAvailable(str);
		   System.out.println("pls yr"+textAreaGuest.getText());
		   roomAvailable.setText("Number of rooms available are: "+len);
		   if(len>0)
			   confirmButt.setEnabled(true);
		   else
	       confirmButt.setEnabled(false);
	 
		   
		  frame.repaint();
	   }
	   public BookingGUI() { 
		
	}
	   public void makeBookingGUI(){
		 
		  // BookingGUI obj = this;
		   createReservationOrViewGUI();
	   }
	   public static void createReservationOrViewGUI() {
		   if (pane != null) {
	            pane.removeAll();
	        }//if
	       frame.setLocation(500, 100); //open in center of screen
	       frame.setSize(680, 400);
	       frame.setResizable(false);
	       frame.setVisible(true);
	       frame.setTitle("Hotel Paris - Reservation");
	       pane = frame.getContentPane(); //content pane
	       pane.setLayout(null); //Apply null layout
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	       //create label for 'create an account' and 'sign in'
	       JLabel ReserveRoomLabel = new JLabel("Reserve your room now");
	       JLabel viewOrCancelReservation = new JLabel("Cancel Reservation");

	       JButton makeReservationButton = new JButton("View / Make a Reservation");
	       JButton viewOrCanceltButton = new JButton("Cancel a Reservation");
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
	       viewOrCanceltButton.addActionListener( 
		           new ActionListener() {
		               @Override
		               public void actionPerformed(ActionEvent event) {
		                 // HAVE TO CHANGE AS PER THE OBSERVER PATTERN
		            	   createViewOrCancelGUI();
		               }//actionPerformed
		           }//ActionListener
		       );
	       
	   }
	   public static void createMakeReservationGUI(){

	       pane.removeAll();
	       JButton transactionDoneButt = new JButton("Transaction Done");
	       JButton backButt = new JButton ("Home");
	       final JButton standardButt = new JButton ("Standard");
	       final JButton suitButt = new JButton ("Suit");
	       final JButton familyRButt = new JButton("Family Room");
	       
	       JLabel ticketsNumLabel = new JLabel("Number of Tickets");
	       JLabel checkInLabel = new JLabel("Check-in:");
	       JLabel checkOutLabel = new JLabel("Check-out:");
	       JLabel roomType = new JLabel("Room type:");
	       final JLabel errorMessage = new JLabel();
	       for(int i=1;i<= 3;i++)
	    	   num.add(new Integer(i));
	      cust.setNumberOfRooms(1);
		 System.out.println("num is"+num);
		  ticketsNum= new JComboBox(num.toArray());
		  confirmButt.setEnabled(false);
		  
	    //   ticketsNum= new JComboBox();

//get the selected item:
int selectedNum = (int) ticketsNum.getSelectedItem();
System.out.println("You selected : " + selectedNum);
	      
	       textAreaGuest.setEnabled(false);
	       textAreaGuest.setText("Room information will be printed here as \n"
	               + "you select the check-in and check-out \ndates and room type.");
	   
	       frame.setTitle("Hotel Paris - Make Transaction");
	      
	       UtilDateModel model = new UtilDateModel();
	       UtilDateModel modelO = new UtilDateModel();
	     //model.setDate(20,04,2014);
	     // Need this...
	     Properties p = new Properties();
	     p.put("text.today", "Today");
	     p.put("text.month", "Month");
	     p.put("text.year", "Year");
	     Date dateAndTime = Calendar.getInstance().getTime();
	     model.setValue(dateAndTime);
	     modelO.setValue(dateAndTime);
	     
	     model.setSelected(true);
	    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	     JDatePanelImpl datePanelO = new JDatePanelImpl(modelO, p);
	    JDatePickerImpl datePickerO = new JDatePickerImpl(datePanelO, new DateLabelFormatter());
	    cust.setStartDate(datePicker.getJFormattedTextField().getText());
 	    cust.setEndDate(datePickerO.getJFormattedTextField().getText());
 	       JLabel calendarErrorMessage = new JLabel();
	       errorMessage.setBounds(25, 200, 200, 50);
	       calendarErrorMessage.setBounds(30, 260, 250, 50);
	       calendarErrorMessage.setText("");
	       checkInLabel.setBounds(30, 140, 75, 25);
	       datePicker.setBounds(100, 150, 140, 25);
	       checkOutLabel.setBounds(30, 170, 75, 25);
	       datePickerO.setBounds(100, 180, 140, 25);
	      confirmButt.setBounds(280, 300, 150, 50);
	       transactionDoneButt.setBounds(480, 300, 150, 50);
	       backButt.setBounds(10, 300, 75, 50);
	       //checkInTextField.setBounds(50, 60, 80, 25);
	       roomAvailable.setBounds(25, 230, 300, 50);
	       roomAvailable.setText("Enter details to know room availability");
	       roomType.setBounds(30, 10, 100, 25);
	       standardButt.setBounds(30, 40, 150, 50);
	      suitButt.setBounds(180, 40, 130, 50);
	      familyRButt.setBounds(310, 40,150, 50);
	       textAreaGuest.setBounds(480, 40, 150, 100);
	       ticketsNumLabel.setBounds(30, 210, 75, 25);
	          ticketsNum.setBounds(150, 210, 80, 25);
	      
	       System.out.println("came here..");   
	       frame.repaint();      
	       //ChangeListener
	       ChangeListener listener = new
	           ChangeListener() {
	               @Override
	               public void stateChanged(ChangeEvent event) {
	            	   System.err.println("yess");
	            	   boolean out=false;boolean in= false;
	            	   cust.setStartDate(datePicker.getJFormattedTextField().getText());
	            	   cust.setEndDate(datePickerO.getJFormattedTextField().getText());
	            	   
// || (datePicker.getModel().getMonth()==dateAndTime.getMonth() && datePicker.getModel().getDay() > dateAndTime.getDay()))
         			  
	            	   if((datePicker.getModel().getDay() > dateAndTime.getDate() &&datePicker.getModel().getMonth()==dateAndTime.getMonth()) || datePicker.getModel().getMonth()>dateAndTime.getMonth() && datePicker.getModel().getYear()>dateAndTime.getYear())
	 	            	  in=true;
	            	 
	            	   if((datePickerO.getModel().getDay() > dateAndTime.getDate() &&datePickerO.getModel().getMonth()==dateAndTime.getMonth()) || datePickerO.getModel().getMonth()>dateAndTime.getMonth() && datePickerO.getModel().getYear()>dateAndTime.getYear())
	            	   out=true;
	            	  
	            	
	           if(in && out){
	        	   confirmButt.setEnabled(true);   
	        	   
	        	   calendarErrorMessage.setText("");   
	           }
	           else
	           { calendarErrorMessage.setText("Please select valid date");
	           confirmButt.setEnabled(false);
	           }
	       }
	        
	       };
	     
	       // dropdown change listener
	       //ChangeListener
	        ActionListener numchange= new
	           ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("selection of ticket number changed");
					cust.setNumberOfRooms(Integer.parseInt(ticketsNum.getSelectedItem().toString()));
				}
	               
	              
	       };
	       model.addChangeListener(listener);
	      modelO.addChangeListener(listener);
	      ticketsNum.addActionListener(numchange);
	      backButt.addActionListener( 
	              new ActionListener() {
	                  @Override
	                  public void actionPerformed(ActionEvent event) {
	                	  createReservationOrViewGUI(); //main menu
	                
	                  }});      
	       //add listener to text field -- serves as Controller in MVC pattern
	      //////////////////////factory////////////////////////////////////////////////////////
	    
	      familyRButt.addActionListener( 
	              new ActionListener() {
	                  @Override
	                  public void actionPerformed(ActionEvent event) {
	                	 
	                	  try {
	                		  cust.setRoomsType("FamilyRoom");
	                		  System.out.println("date chaneg"+(Date) datePicker.getModel().getValue());
	                		if(((Date) datePicker.getModel().getValue() != null) && ((Date) datePickerO.getModel().getValue() != null) )
	                		{
							    new HotelOperations().roomAvailable(cust.getRoomsType(),cust.getStartDate(),cust.getEndDate());
							     errorMessage.setText("");
	                		}
	                		else
	                			errorMessage.setText("Please select date");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                  }});    
	      standardButt.addActionListener( 
	              new ActionListener() {
	                  @Override
	                  public void actionPerformed(ActionEvent event) {
	                	  System.out.println(event.getActionCommand()+" Click");
	                	  cust.setRoomsType("Standard");
	                	
	                	  try {
	                		 
							new HotelOperations().roomAvailable(cust.getRoomsType(),cust.getStartDate(),cust.getEndDate());
						} catch (SQLException e) {
							e.printStackTrace();
						}
	                  }});    
	       suitButt.addActionListener( 
	              new ActionListener() {
	                  @Override
	                  public void actionPerformed(ActionEvent event) {
	                	  cust.setRoomsType("Suit");
	                	  try {
							new HotelOperations().roomAvailable(cust.getRoomsType(),cust.getStartDate(),cust.getEndDate());
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                  }});    
	      
	       confirmButt.addActionListener( 
		              new ActionListener() {
		                  @Override
		                  public void actionPerformed(ActionEvent event) {
		                	 
		                	
		                	  //MERGEWTH GEORGE AND REMOVE............TEMP SETTING CUSTOMERID........
		                	  cust.setCustomerID("8899");
		                	
		                	 //calling the observer client... to invoke and notify all users 
		                	  new BookingClient().bookNow(cust);
								System.out.println("call to observer ended");
							
		                  }});    
		      
	       
	      ////////////////////////////////////////////////////////////////////////////////////////////
	       frame.add(errorMessage);
	       frame.add(roomAvailable);
	       frame.add(confirmButt);
	       frame.add(transactionDoneButt);
	       frame.add(backButt);
	       frame.add(standardButt);
	       frame.add(familyRButt);
	       frame.add(suitButt);
	       frame.add(checkInLabel);
	       frame.add(checkOutLabel);
	       frame.add(datePicker);
	      frame.add(ticketsNumLabel);
	       //frame.add(checkOutTextField);
	       frame.add(ticketsNum);
	       frame.add(datePickerO);
	       frame.add(roomType);
	       frame.add(textAreaGuest);
	     
		frame.add(calendarErrorMessage);
	      
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.repaint();
	      
	   }

	   
	   public static void createViewOrCancelGUI() {
		   frame.repaint();
		   pane.removeAll();
	    	  frame.setLocation(500, 100); //open in center of screen
		       frame.setSize(680, 400);
		       frame.setResizable(false);
		       frame.setVisible(true);
	        final JButton cancelButt = new JButton("Cancel Reservation");
	        JButton homeButt = new JButton("Home");
	        JButton backButt = new JButton ("Back");
	        
	        JLabel label1 = new JLabel("Select a reservation you would like to cancel:");
	        JLabel label2 = new JLabel("Then press 'Cancel Reservation' button.");
	        final JLabel errorMessage = new JLabel();
	        
	        frame.setTitle("Hotel Paris - Cancel or View Reservations");
	        int numberCheckBox = 3;
	        	 
	        		for(int i = 0; i < numberCheckBox; i++) {
	        			 JCheckBox cb = new JCheckBox("New CheckBox");
	        	          cb.setBounds(10, 100+(30*i), 100, 30);
	        	          frame.add(cb);
	        		}
	        		
	       errorMessage.setBounds(25, 200, 200, 50);
	        label1.setBounds(15, 40, 300, 25);
	        label2.setBounds(15, 70, 300, 25);
	        cancelButt.setBounds(280, 300, 150, 50);
	        homeButt.setBounds(480, 300, 150, 50);
	        backButt.setBounds(10, 300, 75, 50);

	        cancelButt.setEnabled(false); 
	        backButt.addActionListener( 
		              new ActionListener() {
		                  @Override
		                  public void actionPerformed(ActionEvent event) {
		                	  createReservationOrViewGUI(); 
		                	
		                  }
		                  }); 
	        cancelButt.addActionListener( 
		              new ActionListener() {
		                  @Override
		                  public void actionPerformed(ActionEvent event) {
		                	  //MERGEWTH GEORGE AND REMOVE............TEMP SETTING CUSTOMERID........
		                	  cust.setCustomerID("8899");
		                	
		                	 //calling the observer client... to invoke and notify all users 
		                	  new BookingClient().cancelNow(cust);
								System.out.println("call to observer ended");
		                  }
		                  }); 
	        homeButt.addActionListener( 
		              new ActionListener() {
		                  @Override
		                  public void actionPerformed(ActionEvent event) {
		                	 // STILL MERGE......

		                	 
		                  }
		                  }); 
		      
	        //associate go home to its button
	      
	        frame.add(errorMessage);
	        frame.add(cancelButt);
	        frame.add(homeButt);
	        frame.add(backButt);
	        frame.add(label1);
	        frame.add(label2);
	       // frame.add(listScroller); 
	        frame.repaint();      
	        frame.revalidate();
	        
	    }//createViewOrCancelGUI
	   

	 }