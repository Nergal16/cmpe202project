import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    static JButton managerButton;
    static JTextField textField;
    public static void main (String args[]) throws IOException, ClassNotFoundException {
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
        frame.setVisible(true);
        createMainGUI(); //create main GUI for guest and manager

    }//main
    public static void createMainGUI() {
        //clear pane if not null
        if (pane != null) {
            pane.removeAll();
        }//if
        frame.setTitle("Welcome to ChampsElysees Hotel");
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
        makeReservationButt.addActionListener( 
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    createMakeReservationGUI();
                }//actionPerformed
            }//ActionListener
        );

        
    }//createReservationOrViewGUI
    public static void createGuestMenu() {
    
        frame.setTitle("Hotel Paris - Guest system");
        
    }
    public static void createMakeReservationGUI() {
    	//code for reservation process
    	
    }
    
}
