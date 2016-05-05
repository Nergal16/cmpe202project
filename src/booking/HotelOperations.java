package booking;

import java.util.Date;
import java.sql.SQLException;

public class HotelOperations {
	
   public static void main(String[] args) throws SQLException{
	   //following will be removed one integrated
	 BookingGUI gui =new BookingGUI();
	 //called when Guest enters from main screen
	 gui.makeBookingGUI();
  
   }
     
   public void roomAvailable(String str, String checkin ,String checkout) throws SQLException{
	   
	RoomFactory rmfactory= new RoomFactory();
	
	rmfactory.getRoom(str,checkin ,checkout);
	   System.out.println("here it is.ready for testing.");
	   
   }

}
