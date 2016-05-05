package booking;

public interface HotelRoomSubject {
// Subject
	
    public abstract void notifyUser(String s, CustomerDetails cust);
    public abstract void addUsers(Observers observer);
   
    public abstract void cancelRoom();
	void bookRoom(CustomerDetails cust);
	

}
