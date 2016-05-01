package booking;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CancellationGUI {

    /**
     * creates GUI for view/cancel reservations
     */
    static JFrame frame = new JFrame();
    static Container pane;
    public static void createViewOrCancelGUI() {
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
        
        frame.setTitle("Hotel ParisS - Cancel or View Reservations");
        
        errorMessage.setBounds(25, 200, 200, 50);
        label1.setBounds(15, 40, 300, 25);
        label2.setBounds(15, 70, 300, 25);
        cancelButt.setBounds(280, 300, 150, 50);
        homeButt.setBounds(480, 300, 150, 50);
        backButt.setBounds(10, 300, 75, 50);


		//JScrollPane listScroller = new JScrollPane(list);
       // listScroller.setVisible(true);
       // listScroller.setBounds(280, 25, 370, 250);
       // listScroller.repaint();
        cancelButt.setEnabled(false); //disable the cancel button as default
        //enable cancel button only if user selects a reservation
     

     
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
