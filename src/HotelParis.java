import javax.swing.*;
import java.io.IOException;

/**
 * Created by Nergal Issaie on 4/11/16.
 */
public class HotelParis {
    static JFrame frame;
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


    }//main


}