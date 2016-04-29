package booking;

import java.sql.SQLException;

public class HotelOperations {
	
   public static void main(String[] args) throws SQLException{
	 BookingGUI gui =new BookingGUI();
	gui.makeBookingGUI();
	   HotelRoomSubject obj = new HotelRoom();
	   new Customer(obj);
	   new Manager(obj);
	 //  obj.notifyUser("booked");
	   //Booking.......that is removing the room from the list
	 obj.bookRoom();
	   
	   //Cancellation
	 //  obj.cancelRoom();
	  //To test the factory method
	 //System.out.println("for factory test...."+gui.textAreaGuest.getText());
	 new HotelOperations().roomAvailable("standard");
	   
   }
     
   public void roomAvailable(String str) throws SQLException{
	   
	RoomFactory rmfactory= new RoomFactory();
	
	rmfactory.getRoom(str);
	   System.out.println("here it is.ready for testing.");
	   
   }
}
