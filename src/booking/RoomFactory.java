package booking;

import java.sql.SQLException;


public class RoomFactory {
//add id while login..
	public RoomVariety getRoom(String roomtype, String checkin, String checkout) throws SQLException{
		System.out.println("IN FACTORT METHOD.. "+checkin+roomtype+checkout);
		if(roomtype.equalsIgnoreCase("standard"))
			return new StandardRoom(checkin,checkout);
		if(roomtype.equalsIgnoreCase("suit"))
			return new Suit(checkin,checkout);
		if(roomtype.equalsIgnoreCase("familyroom"))
			return new FamilyRoom(checkin,checkout);
		
		return null;
		
		
	}
}
