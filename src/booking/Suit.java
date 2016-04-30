package booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Suit implements RoomVariety {
	 int len=0;
 public Suit(String checkin, String checkout) throws SQLException {
	 String query= "SELECT room_number FROM availableroom WHERE availablefrom < '"+checkin+"' AND availabletill > '"+checkout+"' AND room_type='Suit'";
 int cost= 1500;
		String list = available(query);
		System.out.println("list is"+list);
		new BookingGUI(list,len);
	}

	@Override
	public String available(String query) throws SQLException {

		//String query= "Select room_number from availableroom where room_type='Suit'";
		ResultSet res= new DatabaseConnector().query(query);
		String strlist="";
		while(res.next())
			{strlist += "\n"+res.getInt("room_number");len++;}
		return strlist;
	}

}
