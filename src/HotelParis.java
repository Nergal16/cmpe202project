import javax.swing.*;
import java.awt.*;
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

}