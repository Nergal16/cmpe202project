package booking;

public class BookingClient {
	HotelRoomSubject obj = new HotelRoom();
	public BookingClient(){
	
      new Customer(obj);
	  new Manager(obj);
		 //  obj.notifyUser("booked");
		   //Booking.......that is removing the room from the list
	}
	
	public void bookNow(){
	obj.notifyUser("booked");
		
	}
	
}
