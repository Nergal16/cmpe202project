package booking;

public class Customer extends HotelObserver {

	public Customer(HotelRoomSubject subject){
		subject.addUsers(this);
	}
	@Override
	public void update(String str){
	String todo=  str.equalsIgnoreCase("booked") ? "query to del room from list" : "add query to add room in availability list" ;
		 
	 action(str);
		
	}

	@Override
	public void action(String str) {
		System.out.println("The room in Hoel Paris is" + str);
		
	}

}
