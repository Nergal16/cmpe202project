package booking;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
	
	// integration required from the other modules..
	    static JTextArea textAreaGuest = new JTextArea();
	    static JFrame frame =new JFrame ();
	   static Container pane;
	   static JLabel roomAvailable = new JLabel();
	   static String lastState ="";
	   static JButton confirmButt= new JButton("Book");
	   static ArrayList<Integer> num = new ArrayList<Integer>();
	   static  JComboBox ticketsNum= new JComboBox();
	
	   public BookingGUI(String str, int len){
		   frame.repaint();
	        textAreaGuest.setText("Room Number available are :\n"+ str);
		   System.out.println("pls yr"+textAreaGuest.getText());
		   roomAvailable.setText("Number of rooms available are: "+str.length());
		   if(len>0)
		   { 
			confirmButt.setText("Book "+lastState);
	       confirmButt.setEnabled(true);
	 
		   }
		  frame.repaint();
	   }
	   public BookingGUI() { 
		// TODO Auto-generated constructor stub
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
	   public static void createMakeReservationGUI(){

	      
	       
	      // confirmButt.setText("Book");
	       confirmButt.setEnabled(false);
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
	      
		 System.out.println("num is"+num);
		  ticketsNum= new JComboBox(num.toArray());
	   
	    //   ticketsNum= new JComboBox();

//get the selected item:
int selectedNum = (int) ticketsNum.getSelectedItem();
System.out.println("You selected : " + selectedNum);
	      
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
	     Date dateAndTime = Calendar.getInstance().getTime();
	     model.setValue(dateAndTime);
	     modelO.setValue(dateAndTime);
	     model.setSelected(true);
	    JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	    JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	     JDatePanelImpl datePanelO = new JDatePanelImpl(modelO, p);
	    JDatePickerImpl datePickerO = new JDatePickerImpl(datePanelO, new DateLabelFormatter());
	 //   datePicker.getMonthView().setLowerBound(dateAndTime);
	  //  datePicker.getJFormattedTextField().setT   .setText(dateAndTime);
	   //    Date selectedDate = (Date) datePicker.getModel().getValue();
	 //      System.out.println("date panel.."+ datePicker.getModel().getValue());
	    
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
	      model.addChangeListener(listener);
	      modelO.addChangeListener(listener);
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
	                	  String str= event.getActionCommand();
	                	  try {
	                		  lastState =str;
	                		  System.out.println("date chaneg"+(Date) datePicker.getModel().getValue());
	                		if(((Date) datePicker.getModel().getValue() != null) && ((Date) datePickerO.getModel().getValue() != null) )
	                		{
							    new HotelOperations().roomAvailable(str,datePicker.getJFormattedTextField().getText(),datePickerO.getJFormattedTextField().getText());
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
	                	  String str= event.getActionCommand();
	                	  try {
	                		  lastState =str;
							new HotelOperations().roomAvailable(str,datePicker.getJFormattedTextField().getText(),datePickerO.getJFormattedTextField().getText());
						} catch (SQLException e) {
							e.printStackTrace();
						}
	                  }});    
	       suitButt.addActionListener( 
	              new ActionListener() {
	                  @Override
	                  public void actionPerformed(ActionEvent event) {
	                	  String str= event.getActionCommand();
	                	  try {
							new HotelOperations().roomAvailable(str,datePicker.getJFormattedTextField().getText(),datePickerO.getJFormattedTextField().getText());
							 lastState =str;
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
  
	 }