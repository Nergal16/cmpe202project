import RoomServicePkg.RoomServiceController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by cmpe 202 on 4/11/16.
 * CMPE 202
 * Hotel Paris Swing Application April ~ May 2016
 * Team members:
 * Haroldo Filho,
 * Mandeep Kaur,
 * Mukul Bajpai,
 * Ruiqi Cheng ,
 * George Tulakyan,
 * Nergal Issaie
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
    static JButton viewButton;
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
    static JTextArea textAreaGuest;
    static boolean dateCheckIn = false;
    static boolean dateCheckOut = false;
    static String label = "";
    static int realYear, realMonth, realDay, currentYear, currentMonth;

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

        frame.repaint();
        frame.setVisible(true);

        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;
        //create main GUI for guest and manager
        createMainGUI();

        // set frame for RoomServiceController
        RoomServiceController.getInstance().setMenuScreenFrame(frame, pane);
       // RoomServiceController.getInstance().startNewOrder(101);  //tODO delete test only


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
        reviewButton= new JButton("View Reviews");
        viewButton = new JButton("View Hotel");
        guestButton.setBounds(175, 100, 310, 50);
        managerButton.setBounds(175, 160, 310, 50);
        viewButton.setBounds(175, 220, 310, 50);
        reviewButton.setBounds(175,280,310,50);

        frame.add(guestButton);
        frame.add(managerButton);
        frame.add(viewButton);
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
        //set actionListener for guest button
        guestButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createGuestMenu();
                    }//actionPerformed
                }//ActionListener
        );

        //set actionListener for manager button
        managerButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                    	LoginDialog loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);
                       // createCalendarGUI();
                    	//User user=new User("manager","password");
                    	//ManagerProxy managerProxy=new ManagerProxy(user);
                    	//managerProxy.managerOperations();
                    	//IManager manager = new Manager();
                    	//manager.managerOperations();
                    }//actionPerformed
                }//ActionListener
            );

        //set actionListener for view button
        viewButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //TODO hook this to ViewHotel class

                    }//actionPerformed
                }//ActionListener
        );

        pane.add(panel);
        frame.setVisible(true);
        frame.repaint();

    }//createMainGUI

    ///
    public static void createCalendarGUI() {
        ManagerGUI.createCalendarGUI();
    }
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
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    /**
     *  create Sign In GUI
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

        //add action listener
        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //sign-in
                        if (treeMapGuest.containsKey(textField.getText())) {
                            //user profile GUI
                            createReservationOrViewGUI();
                            userID = textField.getText(); //get user ID
                        }//if
                        //userID is incorrect
                        else {
                            signInTryAgainGUI(); //show error in sign in GUI
                        }//else
                    }//actionPerformed
                }//ActionListener
        );

        //associate go back with its button
        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createGuestMenu();
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
        transactionID = (int) Math.floor(Math.random() * 900000000) + 100000000;
        model.setTransactionID(transactionID);
        pane.removeAll();

        frame.setTitle("Hotel Paris - Reservation");
        pane = frame.getContentPane(); //content pane
        pane.setLayout(null); //Apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton makeReservationButton = new JButton("Make a Reservation");
        JButton viewOrCanceltButton = new JButton("View/Cancel a Reservation");
        JButton orderRoomServiceButton = new JButton("Order Room Service");
        JButton backButton = new JButton ("Back");

        frame.add(makeReservationButton);
        frame.add(viewOrCanceltButton);
        frame.add(backButton);
        frame.add(orderRoomServiceButton);
        makeReservationButton.setBounds(175, 50, 310, 50);
        viewOrCanceltButton.setBounds(175, 130, 310, 50);
        orderRoomServiceButton.setBounds(175, 210, 310, 50);
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

        //hook view/cancel button with its method
        viewOrCanceltButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        frame.repaint();
                        createViewOrCancelGUI();
                        frame.repaint();
                    }//actionPerformed
                }//ActionListener
        );

        //hook order room service button with its method
        orderRoomServiceButton.addActionListener(
                new ActionListener() {
                    private Integer roomNumber = 0;
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        frame.repaint();
                        // get room number
                        int theTransactionID = 0;
                        if (!treeMapGuest.isEmpty()) {
                            //if user is in the system
                            if (treeMapGuest.containsKey(userID)) {
                                int i = 0;
                                //while user have reservations
                                while (i < treeMapGuest.get(userID).enterDate.size()) {
                                    if (theTransactionID !=
                                            treeMapGuest.get(userID).nowTransactionID.get(i)) {
                                        theTransactionID =
                                                treeMapGuest.get(userID).nowTransactionID.get(i);
                                        roomNumber = treeMapGuest.get(userID).getRoomNumber(i);
                                    }//if
                                    i++;
                                }//while
                            }//if
                        }//if
                        RoomServiceController.getInstance().startNewOrder(roomNumber);
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

        JButton signInButton = new JButton("Sign In");
        JButton createAccountButton = new JButton("Create Account");
        JButton backButton = new JButton ("Back");

        frame.add(userIDLabel);
        frame.add(createAccountTextArea);
        frame.add(signInButton);
        frame.add(createAccountButton);
        frame.add(backButton);
        userIDLabel.setBounds(175, 75, 310, 25);
        createAccountTextArea.setBounds(175, 175, 310, 25);
        signInButton.setBounds(175, 100, 310, 50);
        createAccountButton.setBounds(175, 200, 310, 50);
        backButton.setBounds(10, 300, 75, 50);

        //set actionListener for button
        signInButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createSignInGUI();
                    }//actionPerformed
                }//ActionListener
        );

        //associate create account function with its button
        createAccountButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createAccountGUI();
                    }//actionPerformed
                }//ActionListener
        );

        //associate go back function with its button
        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createMainGUI();
                    }//actionPerformed
                }//ActionListener
        );

        frame.repaint();

    }

    /**
     * creates GUI for reservation with all ActionListeners components
     */
    public static void createMakeReservationGUI() {
        checkInDate = null;
        checkOutDate = null;
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

        final JTextField checkInTextField = new JTextField(10);
        final JTextField checkOutTextField = new JTextField(10);

        frame.setTitle("Hotel Paris - Make Transaction");
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

        //ChangeListener
        ChangeListener listener = new
                ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent event) {
                        int arr[] = model.calculateAvailableRooms(checkInDate, checkOutDate);
                        String textToDisplay = calculateMessage(arr);
                        textAreaGuest.setText(textToDisplay);
                        frame.repaint();
                    }};
        model.addChangeListener(listener);


        //add listener to text field -- serves as Controller in MVC pattern
        checkInTextField.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        String dateStr = "";
                        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        formatter.setLenient(false);
                        if ((checkInTextField.getText().trim().length() > 0)) {
                            dateStr = checkInTextField.getText();
                            try {
                                checkInDate = formatter.parse(dateStr);
                                gcalendar.setTime(checkInDate);
                                gcalendar.set(Calendar.HOUR_OF_DAY, 0);
                                gcalendar.set(Calendar.MINUTE, 0);
                                gcalendar.set(Calendar.SECOND, 0);
                                gcalendar.set(Calendar.MILLISECOND, 0);
                                checkInDate = gcalendar.getTime();
                                dateCheckIn = true;
                            } catch (Exception ex) {dateCheckIn = false;}//try-catch
                        }//if

                        frame.repaint();
                    }//actionPerformed
                }//ActionListener
        );

        //add listener to text field -- serves as Controller in MVC pattern
        checkOutTextField.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        String dateStr = "";

                        dateStr = checkOutTextField.getText();
                        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        formatter.setLenient(false);
                        if ((checkInTextField.getText().trim().length() > 0)) {
                            dateStr = checkOutTextField.getText();
                            try {
                                checkOutDate = formatter.parse(dateStr);
                                gcalendar.setTime(checkOutDate);
                                gcalendar.set(Calendar.HOUR_OF_DAY, 0);
                                gcalendar.set(Calendar.MINUTE, 0);
                                gcalendar.set(Calendar.SECOND, 0);
                                gcalendar.set(Calendar.MILLISECOND, 0);
                                checkOutDate = gcalendar.getTime();
                                dateCheckOut = true;
                            } catch (Exception ex) {dateCheckOut = false;}//try-catch
                        }//if
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
                        Date date = new Date();
                        GregorianCalendar g = new GregorianCalendar();
                        g.setTime(date);
                        g.set(Calendar.HOUR_OF_DAY, 0);
                        g.set(Calendar.MINUTE, 0);
                        g.set(Calendar.SECOND, 0);
                        g.set(Calendar.MILLISECOND, 0);
                        date = g.getTime();

                        luxButt.setBackground(Color.blue);
                        econButt.setBackground(new JButton().getBackground());
                        roomTypeSelection = 2; // 2 is luxury
                        String mess = "";
                        boolean b = true;
                        if (dateCheckIn == false || dateCheckOut == false) {
                            if (dateCheckIn == false)  mess = "Sorry! Please enter "
                                    + "a valid Check-in date!\n";
                            if (dateCheckOut == false) mess += "Sorry! Please enter "
                                    + "a valid Check-out date!";
                            textAreaGuest.setText(mess);
                            b = false;
                        }//if

                        if (dateCheckIn && checkInDate != null && checkOutDate!= null
                                && checkInDate.before(date)
                                && (!checkInDate.equals(date))) {
                            textAreaGuest.setText("Sorry! Check-in date cannot "
                                    + "be prior\nto today's date.\n\nPlease "
                                    + "try again!");
                            b = false;
                        }//if

                        if (dateCheckOut && checkInDate != null && checkOutDate!= null
                                && checkOutDate.before(date)) {
                            textAreaGuest.setText("Sorry! Check-out date cannot be "
                                    + "prior\nto today's date.\n\nPlease try again!");
                            b = false;
                        }//if

                        else if (b && checkInDate != null && checkOutDate != null
                                && checkInDate.before(checkOutDate)
                                && (!checkInDate.before(date))
                                && (!checkInDate.equals(checkOutDate))) {
                            confirmButt.setEnabled(true);
                            int arr[] = model.calculateAvailableRooms(checkInDate, checkOutDate);
                            String textToDisplay = calculateMessage(arr);
                            textAreaGuest.setText(textToDisplay);
                            b = false;
                        }//else-if

                        if (b) {
                            textAreaGuest.setText("Sorry! Check-out date cannot "
                                    + "be prior\nor equal to Check-in date."
                                    + "\n\nPlease try again!");
                        }//if
                    }});

        //add action listener -- serves as Controller in MVC pattern
        //textAreaGuest is updated upon changes -- serves as View in MVC
        econButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //set today's date to compare against user selection dats
                        Date date = new Date();
                        GregorianCalendar g = new GregorianCalendar();
                        g.setTime(date);
                        g.set(Calendar.HOUR_OF_DAY, 0);
                        g.set(Calendar.MINUTE, 0);
                        g.set(Calendar.SECOND, 0);
                        g.set(Calendar.MILLISECOND, 0);
                        date = g.getTime();

                        econButt.setBackground(Color.blue);
                        luxButt.setBackground(new JButton().getBackground());
                        roomTypeSelection = 1; // 1 is economy
                        String mess = "";
                        boolean b = true;
                        if (dateCheckIn == false || dateCheckOut == false) {
                            if (dateCheckIn == false)  mess = "Sorry! Please enter "
                                    + "a valid Check-in date!\n";
                            if (dateCheckOut == false) mess += "Sorry! Please enter "
                                    + "a valid Check-out date!";
                            textAreaGuest.setText(mess);
                            b = false;
                        }//if

                        if (dateCheckIn && checkInDate != null && checkOutDate!= null
                                && checkInDate.before(date)
                                && (!checkInDate.equals(date))) {
                            textAreaGuest.setText("Sorry! Check-in date cannot be "
                                    + "prior\nto today's date.\n\nPlease try again!");
                            b = false;
                        }//if

                        if (dateCheckOut && checkInDate != null && checkOutDate!= null
                                && checkOutDate.before(date)) {
                            textAreaGuest.setText("Sorry! Check-out date cannot be "
                                    + "prior\nto today's date.\n\nPlease try again!");
                            b = false;
                        }//if

                        else if (b && checkInDate != null && checkOutDate!= null
                                && checkInDate.before(checkOutDate)
                                && (!checkInDate.before(date))
                                && (!checkInDate.equals(checkOutDate))) {
                            confirmButt.setEnabled(true);
                            int arr[] = model.calculateAvailableRooms(checkInDate, checkOutDate);
                            String textToDisplay = calculateMessage(arr);
                            textAreaGuest.setText(textToDisplay);
                            b = false;
                        }//else-if

                        if (b) {
                            textAreaGuest.setText("Sorry! Check-out date cannot be "
                                    + "prior\nor equal to Check-in date."
                                    + "\n\nPlease try again!");
                        }//if
                    }});

        //add action listener -- serves as Controller in MVC pattern
        //textAreaGuest is updated upon changes -- serves as View in MVC
        confirmButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //confirm transaction
                        int arr[] = model.calculateAvailableRooms(checkInDate, checkOutDate);
                        Date date = new Date();
                        GregorianCalendar g = new GregorianCalendar();
                        g.setTime(date);
                        g.set(Calendar.HOUR_OF_DAY, 0);
                        g.set(Calendar.MINUTE, 0);
                        g.set(Calendar.SECOND, 0);
                        g.set(Calendar.MILLISECOND, 0);
                        date = g.getTime();

                        //check if there is available room to reserve
                        int count = 0;
                        int lowerRange=0;
                        int uppeRange =0;
                        if (roomTypeSelection == 1) {lowerRange=0; uppeRange=10;}
                        if (roomTypeSelection == 2) {lowerRange=10; uppeRange=20;}
                        for (int i = lowerRange; i < uppeRange; i++) {
                            if (treeMapRoom.isEmpty() || treeMapRoom.get(checkInDate) == null
                                    || treeMapRoom.get(checkInDate).getRoom().get(i) == true)
                                count++;
                        }//for
                        boolean flag = true;
                        long duration  = checkOutDate.getTime() - checkInDate.getTime();
                        int diffInDays = (int)TimeUnit.MILLISECONDS.toDays(duration) + 1;
                        if (diffInDays > 60) {label = "Sorry! You cannot reserve a "
                                + "room for more than 60 nights!"; flag = false;}
                        else if (checkInDate.after(checkOutDate)) {
                            label = "Sorry! Check-out date cannot be prior to "
                                    + "check-in date!";
                            flag = false;
                        }//else-if

                        else if (checkInDate.before(date)) {
                            label = "Sorry! You cannot reserve room prior to today's date!";
                            flag = false;
                        }//else-if
                        else if (count == 0){
                            label = "Sorry! All rooms for this type are sold out!\n "
                                    + "Please try another room type!";
                            flag = false;
                        }//else-if

                        if (flag) {
                            model.setUserID(userID);
                            nowTransactionID = (int) Math.floor(Math.random() * 900000000) + 100000000;
                            model.setThisTransactionID(nowTransactionID);
                            model.reserveRoom(arr, checkInDate, checkOutDate, roomTypeSelection);
                            createContinueTransactionGUI();
                        }//if
                        else {
                            createNoRoomAvailableGUI(label);
                        }
                    }});

        //add action listener
        transactionDoneButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //transaction done
                        createMainReceiptGUI();
                    }});

        //associate go back to its button
        backButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createMainGUI();
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

    /**
     * creates a GUI to help user to continue the reservation
     */
    public static void createContinueTransactionGUI() {
        //clear pane if not null
        if (pane != null) {
            pane.removeAll();
        }//if
        frame.setTitle("Confirmation of Reservation");
        pane = frame.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();

        textArea = new JTextArea();
        JLabel textLabel = new JLabel("Please select an option:");
        textArea.setEnabled(false);
        frame.add(textLabel);
        textLabel.setBounds(275, 50, 310, 25);

        //create two radio buttons and associate action listeners
        JButton continueBut = new JButton("Make another reservaion");
        JButton doneBut = new JButton("Transaction Done");
        continueBut.setBounds(175, 100, 310, 50);
        doneBut.setBounds(175, 200, 310, 50);

        frame.add(continueBut);
        frame.add(doneBut);

        //set actionListener for guest button
        continueBut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createMakeReservationGUI();
                    }//actionPerformed
                }//ActionListener
        );

        //set actionListener for manager button
        doneBut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //go to receipts GUI
                        createMainReceiptGUI();
                    }//actionPerformed
                }//ActionListener
        );

        pane.add(panel);
        frame.setVisible(true);
        frame.repaint();
    }

    /**
     * In case no rooms are available, the corresponding meesage is displayed
     * @param text the text to be displayed on the GUI as a label
     */
    public static void createNoRoomAvailableGUI(String text) {
        pane.removeAll();

        frame.setTitle("Hotel Paris - Error Message");
        pane = frame.getContentPane(); //get content pane
        pane.setLayout(null); //apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create label for 'create an acount' and 'sign in'
        JLabel ReserveRoomLabel = new JLabel(text);
        JLabel viewOrCancelReservation = new JLabel("Want to Go Back to Home?");

        JButton makeReservationButt = new JButton("Try again");
        JButton homeButt = new JButton("Home");

        frame.add(ReserveRoomLabel);
        frame.add(viewOrCancelReservation);
        frame.add(makeReservationButt);
        frame.add(homeButt);
        ReserveRoomLabel.setBounds(155, 50, 500, 25);
        viewOrCancelReservation.setBounds(175, 175, 310, 25);
        makeReservationButt.setBounds(175, 100, 310, 50);
        homeButt.setBounds(175, 200, 310, 50);

        //set actionListener for button
        makeReservationButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createMakeReservationGUI();
                    }//actionPerformed
                }//ActionListener
        );

        //associate go home to its button
        homeButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createMainGUI();
                    }//actionPerformed
                }//ActionListener
        );

        frame.repaint();
        frame.setLocation(500, 100); //open in center of screen

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
        JButton backButton = new JButton ("Back");

        frame.setTitle("Hotel Paris - Create an Account");
        pane = frame.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        generalLabel.setBounds(275, 40, 310, 25);
        userIDtextField.setBounds(175, 100, 310, 25);
        userNametextField.setBounds(175, 150, 310, 25);
        idLabel.setBounds(175, 75, 310, 25);
        useNameLabel.setBounds(175, 125, 310, 25);
        submitButt.setBounds(175, 200, 310, 50);
        backButton.setBounds(10, 300, 75, 50);

        //add action listener
        submitButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        //store user account info
                        boolean existed = userAccount.createGuestAccount(userIDtextField.getText(),
                                userNametextField.getText(), treeMapGuest);
                        if (existed == true) {
                            createCongratsGUI();
                            userID = userIDtextField.getText();
                            model.setUserID(userID);
                        }//if
                        else
                            createAccountTryAgainGUI();
                    }//actionPerformed
                }//ActionListener
        );

        //associate go back with its button
        backButton.addActionListener(
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
        frame.add(backButton);
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

        frame.setTitle("Hotel Paris - Account Created!");
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
        JButton backButton = new JButton("Back");
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
        backButton.setBounds(285,708,100,50);
        frame.add(scrollBar);

        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event){
                createMainReceiptGUI();
            }
        });

        frame.add(backButton);
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
        JButton backButton = new JButton("Back");
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
        backButton.setBounds(285,708,100,50);
        frame.add(scrollBar);

        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event){
                createMainReceiptGUI();
            }
        });

        frame.add(backButton);
        frame.add(scrollBar);
        frame.setVisible(true);
        frame.repaint();
        frame.setLocation(500, 100); //open in center of screen

    }//simpleFormatGUI

    /**
     * GUI to shows error message in sign in
     */
    public static void signInTryAgainGUI() {
        pane.removeAll();

        JButton submitButt = new JButton("Try again");
        JButton backButt = new JButton ("Back");
        JLabel signInLabel = new JLabel("Sorry! Wrong UserID. \nPlease try again");

        frame.setTitle("Hotel Paris - Account not accessed!");
        pane = frame.getContentPane(); //get content pane
        pane.setLayout(null); //apply null layout

        signInLabel.setBounds(220, 75, 500, 25);
        submitButt.setBounds(175, 200, 310, 50);
        backButt.setBounds(10, 300, 75, 50);

        //add action listener
        submitButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createSignInGUI();
                    }//actionPerformed
                }//ActionListener
        );

        //associate go back with its button
        backButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createGuestMenu(); //go to guest menu
                    }//actionPerformed
                }//ActionListener
        );

        frame.add(submitButt);
        frame.add(backButt);
        frame.add(signInLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.repaint();

    }//signInTryAgainGUI

    /**
     * This GUI throws an error when account was not successfully created
     */
    public static void createAccountTryAgainGUI() {
        pane.removeAll();

        JButton submitButt = new JButton("Try again");
        JButton backButt = new JButton ("Home");
        JLabel createAccountErrorLabel = new JLabel("Sorry! The userID is not "
                + "valid. \nPlease try again!");

        frame.setTitle("Hotel Paris - Account not created!");
        pane = frame.getContentPane(); //Get content pane
        pane.setLayout(null); //Apply null layout

        createAccountErrorLabel.setBounds(200, 75, 500, 25);
        submitButt.setBounds(175, 200, 310, 50);
        backButt.setBounds(10, 300, 75, 50);

        //add action listener
        submitButt.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createAccountGUI();
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
        frame.add(createAccountErrorLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.repaint();

    }//createAccountTryAgainGUI
}
