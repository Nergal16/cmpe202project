package RoomServicePkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static booking.BookingGUI.createReservationOrViewGUI;


/**
 * Created by cmpe 202 on 4/20/2016.
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
    static JButton addMushroomButton;
    static JButton billButton;
    static JButton clearBillButton;
    static JButton backButton;
    static JButton saladButton;

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
    public  void setFrame (JFrame frame, Container pane){
        this.pane = pane;
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
        fishButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.newMarketFish();
                    }
                }
        );

        billButton = new JButton("Total Bill");
        billButton.setBounds(100, 180, 100, 50);
        frame.add(billButton);
        billButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.finishOrder();
                    }
                }
        );

        clearBillButton = new JButton("Clear Bill");
        clearBillButton.setBounds(100, 260, 100, 50);
        frame.add(clearBillButton);
        clearBillButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.clearBill();
                    }
                }
        );

        saladButton = new JButton("Grilled Apple Salad");
        saladButton.setBounds(285, 100, 200, 50);
        frame.add(saladButton);
        saladButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.GrilledAppleSalad();
                    }
                }
        );

        burgerButton = new JButton("Mominette Burger");
        burgerButton.setBounds(520, 100, 200, 50);
        frame.add(burgerButton);
        burgerButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.newBurger();
                    }
                }
        );

        addBaconButton = new JButton("Add Bacon");
        addBaconButton.setBounds(570, 180, 100, 50);
        frame.add(addBaconButton);
        addBaconButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.addBacon();
                    }
                }
        );

        addEggButton = new JButton("Add Egg");
        addEggButton.setBounds(570, 260, 100, 50);
        frame.add(addEggButton);
        addEggButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.addEgg();
                    }
                }
        );

        addMushroomButton = new JButton("Add Shroom");
        addMushroomButton.setBounds(570, 340, 100, 50);
        frame.add(addMushroomButton);
        addMushroomButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.addMushroom();
                    }
                }
        );




        //associate go back function to its button
        backButton = new JButton("Back");
        backButton.setBounds(100, 400, 100, 50);
        frame.add(backButton);
        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        createReservationOrViewGUI();
                    }//actionPerformed
                }//ActionListener
        );


        pane.add(panel);
        frame.setVisible(true);
        frame.repaint();
    }
}
