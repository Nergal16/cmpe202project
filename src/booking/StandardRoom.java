package booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class StandardRoom implements RoomVariety{
 int len=0;
	public StandardRoom(String checkin, String checkout) throws SQLException {
		String query= "SELECT room_number FROM availableroom WHERE availablefrom < '"+checkin+"' AND availabletill > '"+checkout+"' AND room_type='StandardRoom'";
System.out.println("dd"+checkin+"  "+checkout);
		String list = available(query);
		int cost = 800;
		System.out.println("list is"+list);
		new BookingGUI(list,len);
	}

	@Override
	public String available(String query) throws SQLException {
		String strlist="";
		//SELECT roomNumber FROM availableroom WHERE availablefrom >'2016-02-22' AND availabletill <'';
		//String query= "Select room_number from availableroom where room_type='StandardRoom'";
		ResultSet res= new DatabaseConnector().query(query);
		while(res.next())
			{
		strlist += "\n"+res.getInt("room_number");
		len++;
		
		}
		System.out.println("strlist"+strlist);	
		return strlist;
	
		
	}

}
