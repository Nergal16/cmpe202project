package booking;

import java.sql.SQLException;

public class RoomFactory {

	public RoomVariety getRoom(String roomtype) throws SQLException{
		if(roomtype.equalsIgnoreCase("standard"))
			return new StandardRoom();
		if(roomtype.equalsIgnoreCase("suit"))
			return new Suit();
		if(roomtype.equalsIgnoreCase("family room"))
			return new FamilyRoom();
		
		return null;
		
		
	}
}
