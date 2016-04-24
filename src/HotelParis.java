import RoomServicePkg.RoomServiceController;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

/**
 * Created by Nergal Issaie on 4/11/16.
 */
public class HotelParis {
    static Container pane;
    static JFrame frame;
    static JPanel panel;
    static JTextArea textArea;
    static JButton guestButton;
    static JButton managerButton;
    static JTextField textField;
    static JTextArea textAreaFormat;
    static TreeMap<String, UserAccount> treeMapGuest; //user account
    static UserAccount userAccount;
    static TreeMap<Date, Room> treeMapRoom; //room
    static Model model;
    static GregorianCalendar gcalendar;
    static ArrayList<ChangeListener> changeListener;
    static int transactionID;

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
        frame.setSize(680, 400);
        frame.setResizable(false);
        frame.getContentPane().add(new BackgroundImage("Paris.jpg"));
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
        guestButton.setBounds(175, 100, 310, 50);
        managerButton.setBounds(175, 200, 310, 50);

        frame.add(guestButton);
        frame.add(managerButton);

        //add button
        JButton contButton = new JButton("Continue");
        contButton.setEnabled(true);


        pane.add(panel);
        frame.setVisible(true);
        frame.repaint();

    }//createMainGUI
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
    
}
