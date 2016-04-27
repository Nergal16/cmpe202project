
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

/**
 * Created by Nergal Issaie on 4/11/16.
 */
public class HotelParis {
    static Container pane;
    static JFrame frame;
    static JPanel panel;
    static JTextArea textArea;
    static JButton guestButton;
    static JButton managerButton,reviewButton;
    static JLabel select;
  	static JRadioButton food, rooms, hotel;
    static JTextField textField;
    public static void main (String args[]) {
        frame = new JFrame ();
        frame.setLocation(500, 100); //open in center of screen
       //increased height of frame --Mandeep Kaur 04/23/2016
        frame.setSize(680, 500);
        frame.setResizable(false);
        frame.getContentPane();
        frame.setVisible(true);
        createMainGUI(); //create main GUI for guest and manager

    }//main
    public static void createMainGUI() {
        //clear pane if not null
        if (pane != null) {
            pane.removeAll();
        }//if
        frame.setTitle("Welcome to Hotel Paris");
        pane = frame.getContentPane(); //get content pane
        pane.setLayout(null); //apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();

        textArea = new JTextArea();
        JLabel textLabel = new JLabel("Please select an option:");
        textArea.setEnabled(false);
        frame.add(textLabel);
        textLabel.setBounds(275, 50, 310, 25);

        //create two radio buttons and associate action listeners
        guestButton = new JButton("Guest");
        managerButton = new JButton("Manager");
        
        //created new button to view reviews -Mandeep Kaur 04/23/2016
        reviewButton= new JButton("View Reviews");
        
        guestButton.setBounds(175, 100, 310, 50);
        managerButton.setBounds(175, 200, 310, 50);
        reviewButton.setBounds(175,300,310,50);
       
        frame.add(guestButton);
        frame.add(managerButton);
        frame.add(reviewButton);
        //add button
        JButton contButton = new JButton("Continue");
        contButton.setEnabled(true);
        //adding action listener
        reviewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createReviewGUI();
			}
		});

        pane.add(panel);
        frame.setVisible(true);
        frame.repaint();

    }//createMainGUI
    /*
     * created by Mandeep Kaur on 04/24/2016
     */
    public static void createReviewGUI(){
		frame.setTitle("Hotel Paris Reviews");
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
      
		select= new JLabel("Select one Option:");
		hotel=new JRadioButton("Hotel");
		food=new JRadioButton("Food");
		rooms=new JRadioButton("Rooms");
		pane=frame.getContentPane();
		
		hotel.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				HotelReviews hotel=new HotelReviews();
				hotel.printItems();
			}		
		});
		
		food.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				FoodReviews food=new FoodReviews();
				food.printItems();
			}
		});
		rooms.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				RoomServiceRevews room=new RoomServiceRevews();
				room.printItems();
			}		
		});
	
		ButtonGroup group=new ButtonGroup();
		group.add(food);
		group.add(hotel);
		group.add(rooms);
		
		pane.add(select);
		pane.add(hotel);
		pane.add(food);
		pane.add(rooms);
		
		frame.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		frame.setSize(800, 500);
		frame.setVisible(true);
	}
/**
 * Created by Mandeep Kaur on 4/12/16.
 */
    public static void createSignInGUI() {
        pane.removeAll();
        
        textField = new JTextField();
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton ("Back");        
        JLabel signInLabel = new JLabel("Enter User ID:");
        
        frame.setTitle("Hotel Paris - Sign In");
        pane = frame.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        
        signInLabel.setBounds(175, 75, 310, 25);
        submitButton.setBounds(175, 200, 310, 50);
        textField.setBounds(175, 100, 310, 25);
        backButton.setBounds(10, 300, 75, 50);

        //added on 4/14/2016 by Mandeep Kaur
        //add action listener 
        
        submitButton.addActionListener( 
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    //signin
                    createSignInGUI();
                }//actionPerformed
            }//ActionListener
        );
        
        //added on 4/15/2016 by Mandeep Kaur
        //associate go back with its button
        backButton.addActionListener( 
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    createMainGUI();
                }//actionPerformed
            }//ActionListener
        );        
                
        frame.add(submitButton);
        frame.add(backButton);
        frame.add(signInLabel);
        frame.add(textField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.repaint();

    }//createSignInGUI
    
    public static void createReservationOrViewGUI() {
      
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
      //adding a button click listener
        viewOrCanceltButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    createMakeReservationGUI();
                }//actionPerformed
            }//ActionListener
        );

        
    }//createReservationOrViewGUI

    /**
     * GUI for guest menu is created
     */
    public static void createGuestMenu() {
        pane.removeAll();
        frame.setTitle("Hotel Paris - Guest system");

        pane = frame.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create label for 'create an acount' and 'sign in'
        JLabel userIDLabel = new JLabel("Have an Account? Log in");
        JLabel createAccountTextArea = new JLabel("Not a user? Create your account");

        JButton signInButt = new JButton("Sign In");
        JButton createAccountButt = new JButton("Create Account");
        JButton backButt = new JButton ("Back");

        frame.add(userIDLabel);
        frame.add(createAccountTextArea);
        frame.add(signInButt);
        frame.add(createAccountButt);
        frame.add(backButt);
        userIDLabel.setBounds(175, 75, 310, 25);
        createAccountTextArea.setBounds(175, 175, 310, 25);
        signInButt.setBounds(175, 100, 310, 50);
        createAccountButt.setBounds(175, 200, 310, 50);
        backButt.setBounds(10, 300, 75, 50);

        //set actionListener for button
        signInButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createSignInGUI();
                    }//actionPerformed
                }//ActionListener
        );
    }
    public static void createMakeReservationGUI() {
    	//code for reservation process
    	
    }
    
}