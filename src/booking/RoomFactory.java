package booking;

import javax.swing.JTextArea;

public class RoomFactory {

	public RoomVariety getRoom(String roomtype, JTextArea textAreaGuest){
		if(roomtype.equalsIgnoreCase("standard"))
			return new StandardRoom(textAreaGuest);
		if(roomtype.equalsIgnoreCase("suit"))
			return new Suit(textAreaGuest);
		if(roomtype.equalsIgnoreCase("family room"))
			return new FamilyRoom(textAreaGuest);
		
		return null;
		
		
	}
}
