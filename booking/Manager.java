package booking;

public class Manager extends HotelObserver{

	public Manager(HotelRoomSubject subject){
		subject.addUsers(this);
	}
	@Override
	public void update(String str) {
		// do nothing
		action(str);
		
	}

	@Override
	public void action(String str) {
		//Send message on managers portal that Room is booked/cancelled
		
	}

}
