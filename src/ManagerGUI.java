import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by cmpe 202 - group 4 on 4/30/16.
 */
public class ManagerGUI extends HotelParis {
    static SpinnerModel sm;
    private static JSpinner spinner;
    private static JComboBox<String> comboYear, comboMonth;
    private static JButton[][] view;
    private static JPanel cPanel;
    static int realYear, realMonth, realDay, currentYear, currentMonth;
    static String selectedDateOnManagerGUI = "";
    static JScrollPane scrollBar;

    /**
     * GUI for creating calendar on the Manager's GUI
     */
    public static void createCalendarGUI() {
        String[] months =  {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November",
                "December"};

        //Prepare frame
        pane.removeAll();
        frame.setTitle("Hotel Paris - Manager App");
        panel = new JPanel(null);
        pane = frame.getContentPane(); //Get content pane
        pane.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //save both TreeMaps onto Disk upon manager pressing the button
        JButton saveAndQuitButton = new JButton("Save and Quit");
        saveAndQuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    //save both treeMapRoom and treeMapGuest to Disk
                    saveToDisk();
                    //exit the program
                    System.exit(0);
                } catch (IOException ex) {
                    System.out.println("Error occured when saving files onto disk");
                }//try-catch

            }//actionPerformed
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    //go back to main
                    createMainGUI();
                } catch (Exception ex) {
                    System.out.println("Error! Could not go back!");
                }//try-catch

            }//actionPerformed
        });

        textArea = new JTextArea();
        textArea.setEnabled(false);
        textField = new JTextField();
        textField.setEnabled(true);

        //spinner for year menu
        //default value,lower bound,upper bound,increment by
        sm = new SpinnerNumberModel(2016, 1914, 2114, 1);
        spinner = new JSpinner(sm);
        //disable comma from the number
        spinner.setEditor(new JSpinner.NumberEditor(spinner,"#"));
        spinner.setEnabled(true);

        //create combo year and combo month
        comboYear = new JComboBox<String>();
        comboMonth = new JComboBox<String>();

        //create calendar
        view = new JButton[7][7];
        cPanel = new JPanel();

        cPanel.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                final JButton button = new JButton("");
                button.setEnabled(false);
                button.setFont(new Font("Serif", 1, 10));
                view[i][j] = button;
                cPanel.add(view[i][j]);
                cPanel.setBackground(Color.white);
                cPanel.setForeground(Color.white);
            }//for
        }//for

        panel.setBorder(BorderFactory.createTitledBorder(""));

        //register action listeners
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                Integer currentValue = (Integer)spinner.getValue();
                if (currentValue != null){
                    currentYear = currentValue;
                    refreshCalendar(currentMonth, currentYear);
                }//if
            }//stateChanged
        });

        //fill-in Jcombobox for months
        comboMonth.addActionListener(new ActionListener() {
            String[] months =  {"January", "February", "March", "April", "May",
                    "June", "July", "August", "September", "October", "November",
                    "December"};
            String theMonth = "";
            @Override
            public void actionPerformed (ActionEvent e) {
                int i;
                if (comboMonth.getSelectedItem() != null) {
                    theMonth = comboMonth.getSelectedItem().toString();
                    for (i = 0; i < 12; i++) {
                        if (months[i].equals(theMonth)) {
                            break;
                        }//if
                    }//for

                    currentMonth = i;
                    //refresh calendar each time the month is changed
                    refreshCalendar(currentMonth, currentYear);
                    //refresh the color for the selected date
                    refresh();
                }//if
            }//actionPerformed
        });

        //Decorator Pattern to create Scrollbar for textArea that is used on the manager GUI to show available rooms
        IComponent obj = new ConcreteDecorator(new ConcreteComponent()) ;
        obj.operation() ;

        frame.add(saveAndQuitButton);
        frame.add(backButton);

        //Add controls to pane
        pane.add(panel);
        panel.add(spinner);
        panel.add(comboMonth);

        //panel.add(stableCalendar);
        panel.add(cPanel);

        //Set bounds
        panel.setBounds(0, 0, 320, 335);
        comboYear.setBounds(230, 305, 80, 20);
        comboMonth.setBounds(10, 25, 150, 25);
        spinner.setBounds(170, 25, 140, 25);
        textField.setBounds(350, 50, 300, 250);
        textArea.setBounds(350, 25, 300, 283);
        scrollBar.setBounds(350, 25, 300, 283);
        cPanel.setBounds(10, 50, 300, 258);
        ///saveAndQuitBut.setBounds(390, 320, 220, 30);
        saveAndQuitButton.setBounds(390, 320, 120, 30);
        backButton.setBounds(520, 320, 120, 30);

        frame.setVisible(true);
        frame.repaint();

        //ddd week's headers to the calendar
        String[] headers = {"S", "M", "T", "W", "T", "F", "S"};

        //header for JLabel
        for (int i = 0; i < 7; i++) {
            view[0][i].setText(headers[i]);
            view[0][i].setForeground(Color.black);
        }//for
        frame.repaint();

        //populate combo year
        for (int i = realYear - 100; i <= realYear + 100; i++){
            comboYear.addItem(String.valueOf(i));
        }//for

        //add items to comboMonth
        for (int i = 0; i < 12; i++){
            comboMonth.addItem(months[i]);
        }//for

        //refresh calendar and its color
        refresh();
        refreshCalendar (realMonth, realYear); //Refresh calendar

        frame.repaint();

    }//createCalendarGUI

    /**
     * saves the treeMapGuest and treeMapRoom onto disk for later recovery
     * @throws FileNotFoundException file not found exception
     * @throws IOException IO exception
     */
    public static void saveToDisk() throws FileNotFoundException, IOException {
        //STORE treeMapGuest into disk
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("treeMapRoom.data"));
        out.writeObject(treeMapRoom);
        out.close();

        //STORE treeMapRoom into disk
        ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("treeMapGuest.data"));
        out2.writeObject(treeMapGuest);
        out2.close();

    }//saveToDisk

    /**
     * sets the text area field for the manager GUI
     * @throws ParseException throws parse exception
     */
    public static void setTextAreaForManager() throws ParseException {
        String message = ""; //message to be printed
        DateFormat fromat = new SimpleDateFormat("MM/dd/yyyy"); //to be used for ptinting purposes
        Date date = fromat.parse(selectedDateOnManagerGUI);  //to be used as key
        int roomNumber = 0;
        int price = 0;

        if (!treeMapRoom.isEmpty()) {
            //check the available rooms
            int flag = 0;
            for (int j = 0; treeMapRoom.get(date)!= null && j < treeMapRoom.get(date).roomArr.size(); j++)  {
                if (treeMapRoom.get(date).roomArr.get(j) == false) flag++;
            }//for

            if (flag > 0) {
                ArrayList<Boolean> room = treeMapRoom.get(date).getRoom();
                message += "Reserved room information:\n";
                message += "=============================\n";
                for (int i = 0; i < 20; i++) {
                    if(room.size() > i && room.get(i) == false) {
                        if(i < 10) { roomNumber = (i + 100); price = 100; }
                        else { roomNumber = (i + 190); price = 200; }
                        message += "Room number: " + roomNumber + "\n";
                        message += "Guest: " + treeMapRoom.get(date).getUser().get(i) + "\n";
                        message += "Room Price: $" + price + "\n\t*****\n\n";
                    }//if
                }//for

                //list of available room for this day
                message += "=============================\n";
                message += "Available rooms for this day:\n";
                message += "=============================\n";
                for (int i = 0; i < 20; i++) {
                    if(room.size() > i && room.get(i) == true) {
                        if(i < 10) { roomNumber = (i + 100); price = 100; }
                        else { roomNumber = (i + 190); price = 200; }
                        message += "\t" + roomNumber + "\n";
                    }//if
                }//for
            }//if

            //room is empty for this day
            else {
                message += "No reservation for this day!\n\n\n";
                message += "=============================\n";
                message += "Available rooms for this day:\n";
                message += "=============================\n";
                if (treeMapRoom.get(date) != null) {
                    ArrayList<Boolean> room = treeMapRoom.get(date).getRoom();
                    room = treeMapRoom.get(date).getRoom();
                    for (int i = 0; i < 20; i++) {
                        if(room.get(i) == true) {
                            if(i < 10) { roomNumber = (i + 100); price = 100; }
                            else { roomNumber = (i + 190); price = 200; }
                            message += "\t" + roomNumber + "\n";
                        }//if
                    }//for
                }//if
                //There is no Room associated with this dday
                else {
                    for (int i = 0; i < 20; i++) {
                        if(i < 10) { roomNumber = (i + 100); price = 100; }
                        else { roomNumber = (i + 190); price = 200; }
                        message += "\t" + roomNumber + "\n";
                    }//for
                }//else
            }//else
        }//if

        //TreeMap is empty
        else {
            message += "No reservation for this day!\n\n\n";
            message += "=============================\n";
            message += "Available rooms for this day:\n";
            message += "=============================\n";
            for (int i = 0; i < 20; i++) {
                if(i < 10) { roomNumber = (i + 100); price = 100; }
                else { roomNumber = (i + 190); price = 200; }
                message += "\t" + roomNumber + "\n";
            }//for
        }//else

        textArea.setText(message);

    }//setTextAreaForManager

    /**
     * refreshes calendar when making any changes on calendar
     * @param month the current month
     * @param year the current year
     */
    public static void refreshCalendar(final int month, final int year){
        refresh(); //refresh the date color

        String[] months =  {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November",
                "December"};
        int numberOfDays, startOfMonth; //number Of days, start Of month

        //refresh the month label (at the top)
        comboMonth.setToolTipText(months[month]);
        comboMonth.setSelectedItem(months[month]);
        //select the correct year in the combo box
        comboYear.setSelectedItem(String.valueOf(year));

        //clear calendar
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j++){
                view[i+1][j].setText("");
            }//for
        }//for

        //get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        numberOfDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        startOfMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //draw JLabel
        for (int i = 1; i <= numberOfDays; i++) {
            final int row = (i + startOfMonth - 2) / 7;
            final int column = (i + startOfMonth - 2) % 7;
            view[row+1][column].setText(i + "");
            view[row+1][column].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    refresh();
                    if (!view[row+1][column].getText().isEmpty() ) {
                        view[row+1][column].setBackground(new Color(0, 84, 227));
                        view[row+1][column].setForeground(Color.red);
                        selectedDateOnManagerGUI = (currentMonth + 1) + "/" + view[row+1][column].getText() + "/"+ currentYear;
                        try {
                            setTextAreaForManager();
                        } catch (ParseException ex) {
                            Logger.getLogger(HotelParis.class.getName()).log(Level.SEVERE, null, ex);
                        }//try-catch
                        frame.repaint();
                    }//if
                    view[row+1][column].setBorder(null);
                }//mouseClicked
            });
        }//for

        frame.repaint();
        frame.revalidate();

    }//refreshCalendar

    /**
     * refreshes the calendar when there is a need to call it
     */
    public static void refresh () {

        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                view[i][j].setBackground(Color.white);
                view[i][j].setForeground(Color.black);
            }//for
        }//for

        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                //it's TODAY
                if (!view[i][j].getText().isEmpty() &&
                        view[i][j].getText().trim().length() > 0 &&
                        Integer.parseInt(view[i][j].getText()) == realDay &&
                        currentMonth == realMonth && currentYear == realYear) {
                    //this is current day, make the background and text blue
                    view[i][j].setBackground(new Color(0, 84, 227));
                    view[i][j].setForeground(Color.blue);
                }//if
            }//for
        }//for

    }//refresh


}
