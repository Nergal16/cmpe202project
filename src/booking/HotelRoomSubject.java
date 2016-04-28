package src.booking;

public interface HotelRoomSubject {
// Subject
	
    public abstract void notifyUser(String s);
    public abstract void addUsers(Observers observer);
    public abstract void bookRoom();
    public abstract void cancelRoom();
	

}
