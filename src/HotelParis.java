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
    static JButton guestBut;
    static JButton managerBut;
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
        guestBut = new JButton("Guest");
        managerBut = new JButton("Manager");
        guestBut.setBounds(175, 100, 310, 50);
        managerBut.setBounds(175, 200, 310, 50);

        frame.add(guestBut);
        frame.add(managerBut);

        //add button
        JButton contBut = new JButton("Continue");
        contBut.setEnabled(true);


        pane.add(panel);
        frame.setVisible(true);
        frame.repaint();

    }//createMainGUI
    
    public static void createReservationOrViewGUI() {
      
        frame.setTitle("HotelParis - Reservation");
        pane = frame.getContentPane(); //content pane
        pane.setLayout(null); //Apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //create label for 'create an acount' and 'sign in'
        JLabel ReserveRoomLabel = new JLabel("Reserve your room now");
        JLabel viewOrCancelReservation = new JLabel("View your current "
                + "reservation or Cancel a reservation");

        JButton makeReservationButton = new JButton("Make a Reservation");
        JButton viewOrCanceltButton = new JButton("View/Cancel a Reservation");
        JButton backButt = new JButton ("Back");

        frame.add(ReserveRoomLabel);
        frame.add(viewOrCancelReservation);
        frame.add(makeReservationButton);
        frame.add(viewOrCanceltButton);
        frame.add(backButt);
        ReserveRoomLabel.setBounds(175, 75, 310, 25);
        viewOrCancelReservation.setBounds(175, 175, 310, 25);
        makeReservationButt.setBounds(175, 100, 310, 50);
        viewOrCanceltButt.setBounds(175, 200, 310, 50);
        backButt.setBounds(10, 300, 75, 50);

        
    }//createReservationOrViewGUI

}