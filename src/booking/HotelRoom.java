package booking;

import java.util.ArrayList;


public class HotelRoom implements HotelRoomSubject{
//concrete Subject
	  private ArrayList<Observers> obsList= new ArrayList<>() ;

	@Override
	public void addUsers(Observers obs) {
		//adding the observers
		obsList.add(obs);
		
	}

	@Override
	public void notifyUser(String s) {
		for(Observers eachobs : obsList)
			eachobs.update(s);
		//update user and manager
		
	}

	@Override
	public void bookRoom() {
	// add a query to remove the room from the room-available list
		notifyUser("booked");
	}

	@Override
	public void cancelRoom() {
	//add a query to add the room on the room-available list
		notifyUser("cancel");
	}

}
