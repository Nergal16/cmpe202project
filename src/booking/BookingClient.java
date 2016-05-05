package booking;

public class BookingClient {
	HotelRoomSubject obj = new HotelRoom();
	public BookingClient(){
	//this is observer paatter
      new Customer(obj);
	  new Manager(obj);
		 //  obj.notifyUser("booked");
		   //Booking.......that is removing the room from the list
	}
	
	public void bookNow(CustomerDetails cust){
		//move this call to..............................................
	obj.notifyUser("book",cust);
		
	}
	public void cancelNow(CustomerDetails cust){
		//move this call to..............................................
	obj.notifyUser("cancelled",cust);
		
	}
	
}
