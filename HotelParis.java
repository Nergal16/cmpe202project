import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import RoomServicePkg.RoomServiceController;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


/**
 * Created by Nergal Issaie on 4/11/16.
 */
public class HotelParis implements Serializable {
    static Container pane;
    static JFrame frame;
    static JPanel panel;
    static JTextArea textArea;
    static JButton guestButton;
    static JButton managerButton,reviewButton, backButton;
    static JLabel select;
  	static JRadioButton food, rooms, hotel;
    static JTextField textField;
    static JTextArea textAreaFormat;
    static TreeMap<String, UserAccount> treeMapGuest; //user account
    static UserAccount userAccount;
    static TreeMap<Date, Room> treeMapRoom; //room
    static Model model;
    static GregorianCalendar gcalendar;
    static ArrayList<ChangeListener> changeListener;
    static int transactionID;
    static JScrollPane scrollBar; //textArea scrollbar
    static InvoicePreparer invoice;
    static int nowTransactionID;
    static String userID;

    public static void main (String args[]) throws IOException, ClassNotFoundException {
        textAreaFormat = new JTextArea(20, 40);
        treeMapGuest = new TreeMap<String, UserAccount>();
        userAccount = new UserAccount();
        treeMapRoom = new TreeMap<Date, Room>();
        readFromDisk();
        model = new Model(treeMapRoom, treeMapGuest, transactionID);

        gcalendar = new GregorianCalendar();
        Date trialTime = new Date();
        gcalendar.setTime(trialTime);
        gcalendar.set(2014, 10, 1, 0, 0, 0);
        gcalendar.set(Calendar.MILLISECOND, 0);
        gcalendar.add(Calendar.DAY_OF_YEAR, 1);

        //listeners to be used in MVC
        changeListener = new ArrayList<ChangeListener>();

        //set Look and feel
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}

      frame = new JFrame ();
        frame.setLocation(500, 100); //open in center of screen
       //increased height of frame --Mandeep Kaur 04/23/2016
        frame.setSize(680, 550);
        frame.setResizable(false);
        frame.getContentPane();
        frame.setVisible(true);

        // set frame for RoomServiceController
        RoomServiceController.getInstance().setMenuScreenFrame(frame);
        //create main GUI for guest and manager
        createMainGUI();

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
		backButton=new JButton("Go Back");
		
		pane=frame.getContentPane();
		
		 backButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					createMainGUI();
				}
			});
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
		
		pane.add(backButton);
		
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

    /**
     * GUI for create an account
     */
    public static void createAccountGUI() {
        pane.removeAll();

        JLabel generalLabel = new JLabel("Create your account:");
        JLabel idLabel = new JLabel("Enter User ID:");
        JLabel useNameLabel = new JLabel("Enter User name:");
        final JTextField userIDtextField = new JTextField();
        final JTextField userNametextField = new JTextField();
        JButton submitButt = new JButton("Submit");
        JButton backButt = new JButton ("Back");

        frame.setTitle("ChampsElysees Hotel - Create an Account");
        pane = frame.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        generalLabel.setBounds(275, 40, 310, 25);
        userIDtextField.setBounds(175, 100, 310, 25);
        userNametextField.setBounds(175, 150, 310, 25);
        idLabel.setBounds(175, 75, 310, 25);
        useNameLabel.setBounds(175, 125, 310, 25);
        submitButt.setBounds(175, 200, 310, 50);
        backButt.setBounds(10, 300, 75, 50);

        //TODO add action listener


        //associate go back with its button
        backButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createGuestMenu();
                    }//actionPerformed
                }//ActionListener
        );

        frame.add(generalLabel);
        frame.add(userIDtextField);
        frame.add(userNametextField);
        frame.add(idLabel);
        frame.add(useNameLabel);
        frame.add(submitButt);
        frame.add(backButt);
        frame.repaint();

    }//createAccountGUI

    /**
     * GUI to show when guest successfully creates an account
     */
    public static void createCongratsGUI() {
        pane.removeAll();

        JButton submitButt = new JButton("Go to Sign In");
        JButton backButt = new JButton ("Home");
        JLabel signInLabel = new JLabel("Congratulations! Your account has "
                + "been created successfully. \nPlease sign In");

        frame.setTitle("ChampsElysees Hotel - Account Created!");
        pane = frame.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout

        signInLabel.setBounds(140, 75, 500, 25);
        submitButt.setBounds(175, 200, 310, 50);
        backButt.setBounds(10, 300, 75, 50);

        //add action listener
        submitButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //signin
                        createSignInGUI();
                    }//actionPerformed
                }//ActionListener
        );

        //associate go back with its button
        backButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createMainGUI();
                    }//actionPerformed
                }//ActionListener
        );

        frame.add(submitButt);
        frame.add(backButt);
        frame.add(signInLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.repaint();

    }//createCongratsGUI

    public static void readFromDisk() throws FileNotFoundException,
            IOException, ClassNotFoundException {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream("treeMapRoom.data"));
            treeMapRoom = (TreeMap<Date, Room>)in.readObject();
            in.close();

            ObjectInputStream in2 = new ObjectInputStream(
                    new FileInputStream("treeMapGuest.data"));
            treeMapGuest = (TreeMap<String, UserAccount>)in2.readObject();
            in2.close();
        }//try
        catch (IOException | ClassNotFoundException e) {}

    }//readFromDisk

    /**
     * creates a GUI to ask user to select a receipt format
     */
    public static void createMainReceiptGUI() {
        //clear pane if not null
        if (pane != null) {
            pane.removeAll();
        }//if
        frame.setTitle("Main Receipt");
        pane = frame.getContentPane(); //get content pane
        pane.setLayout(null); //apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();

        textArea = new JTextArea();
        JLabel textLabel = new JLabel("Please select a receipt format:");
        textArea.setEnabled(false);
        frame.add(textLabel);
        textLabel.setBounds(265, 50, 310, 25);

        //create two radio buttons and associate action listeners
        JButton SimpleBut = new JButton("Simple Format Receipt");
        JButton ComprehensiveBut = new JButton("Comprehensive Format Receipt");
        //back button
        JButton backButt = new JButton("Back");

        SimpleBut.setBounds(175, 100, 310, 50);
        ComprehensiveBut.setBounds(175, 200, 310, 50);
        backButt.setBounds(10, 300, 75, 50);
        frame.setSize(680, 400);

        frame.add(SimpleBut);
        frame.add(ComprehensiveBut);
        frame.add(backButt);
        pane.add(panel);
        frame.setVisible(true);
        frame.repaint();

        //set actionListener for guest button
        SimpleBut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //simple receipt
                        simpleFormatGUI();
                    }//actionPerformed
                }//ActionListener
        );

        //set actionListener for manager button
        ComprehensiveBut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //comprehensive receipt
                        comprehensiveFormatGUI();
                    }//actionPerformed
                }//ActionListener
        );

        //back button
        backButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createReservationOrViewGUI();
                    }//actionPerformed
                }//ActionListener
        );

        frame.setVisible(true);
        frame.repaint();

    }//createMainReceiptGUI

    /**
     * creates the GUI for the comprehensive receipt
     */
    public static void comprehensiveFormatGUI() {
        if (pane != null) {
            pane.removeAll();
        }//if
        frame.setTitle("Comprehensive Receipt");
        pane = frame.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
        panel = new JPanel();

        invoice = new InvoicePreparer(new StrategyComprehensiveReceipt(), treeMapGuest, userID);
        String resultA = invoice.executeStrategy(transactionID, nowTransactionID);
        JButton backBut = new JButton("Back");
        textAreaFormat.setText(resultA);
        textAreaFormat.setEditable(false);

        //create Scrollbar for textArea
        scrollBar = new JScrollPane(textAreaFormat,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setEnabled(true);
        scrollBar.setVisible(true);
        scrollBar.setBounds(5,5,665, 695);
        frame.setBounds(5, 5, 680, 800);
        textAreaFormat.setBounds(10,10,660,700);
        backBut.setBounds(285,708,100,50);
        frame.add(scrollBar);

        backBut.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event){
                createMainReceiptGUI();
            }
        });

        frame.add(backBut);
        frame.add(scrollBar);
        frame.setVisible(true);
        frame.repaint();
        frame.setLocation(500, 100); //open in center of screen

    }//comprehensiveFormatGUI

    /**
     * creates the GUI for the simple receipt
     */
    public static void simpleFormatGUI() {
        if (pane != null) {
            pane.removeAll();
        }//if

        frame.setTitle("Simple Receipt");
        pane = frame.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
        panel = new JPanel();

        invoice = new InvoicePreparer(new StrategySimpleReceipt(), treeMapGuest, userID);
        String resultA = invoice.executeStrategy(transactionID, nowTransactionID);
        JButton backBut = new JButton("Back");
        textAreaFormat.setText(resultA);
        textAreaFormat.setEditable(false);

        //create Scrollbar for textArea
        scrollBar = new JScrollPane(textAreaFormat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setEnabled(true);
        scrollBar.setVisible(true);
        scrollBar.setBounds(5,5,665, 695);
        frame.setBounds(5, 5, 680, 800);
        textAreaFormat.setBounds(10,10,660,700);
        backBut.setBounds(285,708,100,50);
        frame.add(scrollBar);

        backBut.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event){
                createMainReceiptGUI();
            }
        });

        frame.add(backBut);
        frame.add(scrollBar);
        frame.setVisible(true);
        frame.repaint();
        frame.setLocation(500, 100); //open in center of screen

    }//simpleFormatGUI
    
}
