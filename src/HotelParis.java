import RoomServicePkg.RoomServiceController;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Nergal Issaie on 4/11/16.
 */
public class HotelParis implements Serializable {
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
    static JScrollPane scrollBar; //textArea scrollbar
    static InvoicePreparer invoice;
    static int nowTransactionID;
    static String userID;
    static DefaultListModel defaultListModel;
    static JList list;
    static Date checkInDate;
    static Date checkOutDate;
    static int roomTypeSelection = 1;

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

        viewOrCanceltButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    createMakeReservationGUI();
                }//actionPerformed
            }//ActionListener
        );

        //set actionListener for button
        makeReservationButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createMakeReservationGUI();
                    }//actionPerformed
                }//ActionListener
        );

        //associate view/cancel functionality with its button
        viewOrCanceltButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        ;
                        frame.repaint();
                        createViewOrCancelGUI();
                        frame.repaint();
                    }//actionPerformed
                }//ActionListener
        );

        //associate go back function to its button
        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createMainGUI();
                    }//actionPerformed
                }//ActionListener
        );

        frame.repaint();


    }//createReservationOrViewGUI

    /**
     * creates GUI for view/cancel reservations
     */
    public static void createViewOrCancelGUI() {
        pane.removeAll();

        final JButton cancelButt = new JButton("Cancel Reservation");
        JButton homeButt = new JButton("Home");
        JButton backButt = new JButton ("Back");

        JLabel label1 = new JLabel("Select a reservation you would like to cancel:");
        JLabel label2 = new JLabel("Then press 'Cancel Reservation' button.");
        final JLabel errorMessage = new JLabel();

        frame.setTitle("Hotel Paris - Cancel or View Reservations");

        errorMessage.setBounds(25, 200, 200, 50);
        label1.setBounds(15, 40, 300, 25);
        label2.setBounds(15, 70, 300, 25);
        cancelButt.setBounds(280, 300, 150, 50);
        homeButt.setBounds(480, 300, 150, 50);
        backButt.setBounds(10, 300, 75, 50);

        //initialize a list of reserved items
        defaultListModel = new DefaultListModel();
        String message = "";
        String enterDate = "";
        String exitDate = "";
        DateFormat fromatter;
        //file-in the List from treeMapGuest
        int theTransactionID = 0;
        if (!treeMapGuest.isEmpty()) {
            //if user are in the system
            if (treeMapGuest.containsKey(userID)) {
                int i = 0;
                //while user have reservations
                while (i < treeMapGuest.get(userID).enterDate.size()) {
                    if (theTransactionID !=
                            treeMapGuest.get(userID).nowTransactionID.get(i)) {
                        theTransactionID =
                                treeMapGuest.get(userID).nowTransactionID.get(i);
                        //format the date object as string
                        fromatter = new SimpleDateFormat("MM/dd/yyyy");
                        enterDate = fromatter.format(treeMapGuest.get(userID).getEnterDate(i));
                        exitDate = fromatter.format(treeMapGuest.get(userID).getExitDate(i));
                        message = "Room#: "
                                + treeMapGuest.get(userID).getRoomNumber(i)
                                + ",  Check-in: " + enterDate
                                + ",  Check-out: " + exitDate;
                        defaultListModel.addElement(message);
                    }//if
                    i++;
                }//while
            }//if
        }//if

        list = new JList(defaultListModel);
        list.setBounds(280, 25, 370, 250);
        list.setVisible(true);

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setVisible(true);
        listScroller.setBounds(280, 25, 370, 250);
        listScroller.repaint();
        cancelButt.setEnabled(false); //disable the cancel button as default
        //enable cancel button only if user selects a reservation
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                cancelButt.setEnabled(true);
            }//valueChanged
        });

        //remove/cancel reservation from both JList and internal data structure
        cancelButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //remove room from both treeMapRoom and treeMapGuest

                        int index = list.getSelectedIndex(); //index of item to be remvoed

                        //make sure there is at least one item in JList before delete
                        if (index != -1) {
                            String str = defaultListModel.get(index).toString();
                            int roomNum = Integer.parseInt(str.substring(7, 10));
                            String in = str.substring(23, 33);
                            String out = str.substring(47, 57);
                            DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
                            Date inDate = null;
                            Date outDate = null;
                            try {
                                inDate = outputFormatter.parse(in);
                                outDate = outputFormatter.parse(out);
                            } catch (ParseException ex) {
                                Logger.getLogger(HotelParis.class.getName()).log(Level.SEVERE, null, ex);
                            }//try-catch

                            //remove from treeMapRoom
                            Date copyInDate = inDate;
                            int userRoomAdd = 0;
                            if (roomNum > 99 && roomNum < 200) userRoomAdd = 100;
                            if (roomNum > 199) userRoomAdd = 190;
                            int i = 0;

                            //handle the transactions with multi-days
                            while (treeMapRoom.get(copyInDate)!=null
                                    && i < treeMapRoom.get(copyInDate).roomArr.size()) {
                                //check date, user ID and room number before deleting
                                if (treeMapRoom.get(inDate).getRoom().get(i) == false
                                        && treeMapRoom.get(inDate).getUser().get(i).equals(userID)
                                        && (userRoomAdd + i) == roomNum ){

                                    //handle all days within one transaction
                                    while (!(copyInDate).equals(outDate)) {
                                        treeMapRoom.get(copyInDate).roomArr.set(i, true);
                                        treeMapRoom.get(copyInDate).userIDArr.set(i, "");

                                        //set the calendar to increment dates
                                        GregorianCalendar g = new GregorianCalendar();
                                        g.setTime(copyInDate);
                                        g.add(Calendar.DAY_OF_YEAR, 1); //increment day
                                        copyInDate = g.getTime();
                                    }//while
                                }//if
                                i++;
                            }//while

                            //remove from treeMapGuest
                            i = 0;
                            i = treeMapGuest.get(userID).enterDate.size() - 1;
                            while (i >= 0) {
                                //check date, user ID, and room number
                                if (treeMapGuest.get(userID).getEnterDate(i).equals(inDate)
                                        && treeMapGuest.get(userID).getExitDate(i).equals(outDate)
                                        && treeMapGuest.get(userID).getRoomNumber(i) == roomNum) {
                                    treeMapGuest.get(userID).enterDate.remove(i);
                                    treeMapGuest.get(userID).exitDate.remove(i);
                                    treeMapGuest.get(userID).roomNumber.remove(i);
                                    treeMapGuest.get(userID).nowTransactionID.remove(i);
                                    treeMapGuest.get(userID).price.remove(i);
                                    treeMapGuest.get(userID).transactionID.remove(i);
                                }//if
                                i--;
                            }//while

                            //remove item from the JList
                            defaultListModel.remove(index);
                            int size = defaultListModel.getSize();

                            if (size == 0) { //nobody is left, disable the cancel button
                                cancelButt.setEnabled(false);
                            }//if
                            else { //select an index.
                                if (index == defaultListModel.getSize()) {
                                    //removed item in last position
                                    index--;
                                }//if
                                list.setSelectedIndex(index);
                                list.ensureIndexIsVisible(index);
                            }//else
                        }//if
                    }});

        //associate go home to its button
        homeButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createMainGUI(); //main menu
                    }});

        //associate go back to its button
        backButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createReservationOrViewGUI(); //create/view GUI
                    }});

        frame.add(errorMessage);
        frame.add(cancelButt);
        frame.add(homeButt);
        frame.add(backButt);
        frame.add(label1);
        frame.add(label2);
        frame.add(listScroller);
        frame.repaint();
        frame.revalidate();

    }//createViewOrCancelGUI

    /**
     * conducts the info messages that needs to be displayed to user
     * @param count the list of available rooms
     * @return the info message to be displayed on GUI
     */
    public static String calculateMessage (int[] count) {
        long duration  = checkOutDate.getTime() - checkInDate.getTime();
        int diffInDays= (int) TimeUnit.MILLISECONDS.toDays(duration) + 1;
        int lowerRange = 0;
        int upperRange = 20;
        int addition = 0;

        if (roomTypeSelection == 1) {
            lowerRange = 0;
            upperRange = 10;
            addition = 100;
        }//if

        if (roomTypeSelection == 2) {
            lowerRange = 10;
            upperRange = 20;
            addition = 190;
        }//if

        String message = "Available rooms: \n";
        //calculate available rooms
        for (int i = lowerRange; i < upperRange; i++)
            if (count[i] == diffInDays) {
                message += "\t" + (addition + i) + "\n";
            }//if

        return message;

    }//calculateMessage

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
