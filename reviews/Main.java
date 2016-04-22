package reviews;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class Main {

	static JFrame frame;
	static Container pane;
	static JPanel panel;
    static JLabel select;
 
	static JRadioButton food, rooms, hotel;
/*
 * review button will be added to main screen of application
 */
	public static void main(String[] args) {
		frame=new JFrame("Reviews");
		frame.setSize(800, 500);
		frame.setLayout(new BorderLayout());
		JButton reviewButton=new JButton("Reviews");
		pane=frame.getContentPane();
		pane.add(reviewButton);
		/*
		 * adding action listener on review button
		 * on click it will ask to select the area of hotel for which guest wants to view the reviews 
		 * before proceeding for booking section 
		 */
		reviewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				createReviewGUI();
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}//createReviewGUI
	public static void createReviewGUI(){
		frame.setTitle("Hotel Paris Reviews");
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
      
		select= new JLabel("Select one Option:");
		hotel=new JRadioButton("Hotel");
		food=new JRadioButton("Food");
		rooms=new JRadioButton("Rooms");
		pane=frame.getContentPane();
		
		hotel.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				
			}		
		});
		
		food.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("state changed");
			}
		});
		rooms.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				
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
		
		frame.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		frame.setSize(800, 500);
		frame.setVisible(true);
	}
}