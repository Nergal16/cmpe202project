package src.booking;

public class Operations {

	public Operations(){
		   HotelRoomSubject obj = new HotelRoom();
		   new Customer(obj);
		   new Manager(obj);
		   
		   //Booking.......that is removing the room from the list
		   obj.bookRoom();
		   
		   //Cancellation
		   obj.cancelRoom();
		
		
	}
}
