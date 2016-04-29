package RoomServicePkg;

import javax.swing.*;
import java.awt.*;

/**
 * Created by home on 4/20/2016.
 */
public class RoomServiceScreen {
    static Container pane;
    static JFrame frame;
    static JPanel panel;
    static JTextArea textArea;
    static JButton fishButton;
    static JButton burgerButton;
    static JButton addBaconButton;
    static JButton addEggButton;
    static JButton billButton;
    static JButton clearBillButton;

    static JTextField textField;
    private RoomServiceController controller;
    public RoomServiceScreen( RoomServiceController controller){
        this.controller = controller;
        //set Look and feel
        try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}


    }
    // the frame is passed from the main UI
    public  void setFrame (JFrame frame){
        this.frame = frame;
        frame.setLocation(500, 100); //open in center of screen
        frame.setSize(680, 400);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void draw (){
        //clear pane if not null
        if (pane != null) {
            pane.removeAll();
        }//if
        frame.setSize(780, 500);
        frame.setTitle("Room Service Menu");
        pane = frame.getContentPane(); //get content pane
        pane.setLayout(null); //apply null layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();

        textArea = new JTextArea();
        JLabel textLabel = new JLabel("Please select options:");
        textArea.setEnabled(false);
        frame.add(textLabel);
        textLabel.setBounds(275, 50, 310, 25);

        //create food buttons and associate action listeners
        fishButton = new JButton("Wild Market Fish");
        fishButton.setBounds(50, 100, 200, 50);
        frame.add(fishButton);

        billButton = new JButton("Total Bill");
        billButton.setBounds(100, 180, 100, 50);
        frame.add(billButton);

        clearBillButton = new JButton("Clear Bill");
        clearBillButton.setBounds(100, 260, 100, 50);
        frame.add(clearBillButton);

        burgerButton = new JButton("Mominette Burger");
        burgerButton.setBounds(420, 100, 200, 50);
        frame.add(burgerButton);

        addBaconButton = new JButton("Add Bacon");
        addBaconButton.setBounds(470, 180, 100, 50);
        frame.add(addBaconButton);

        addEggButton = new JButton("Add Egg");
        addEggButton.setBounds(470, 260, 100, 50);
        frame.add(addEggButton);




        //add button
        JButton contButton = new JButton("Finish Order");
        contButton.setEnabled(true);


        pane.add(panel);
        frame.setVisible(true);
        frame.repaint();
    }
}
