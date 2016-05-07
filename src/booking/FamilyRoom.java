package booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FamilyRoom implements RoomVariety{
	 int len=0;
	public FamilyRoom(String checkin, String checkout) throws SQLException {
		String query= "SELECT room_number FROM availableroom WHERE availablefrom < '"+checkin+"' AND availabletill > '"+checkout+"' AND room_type='FamilyRoom'";
String list = available(query);
int cost = 1200;
		System.out.println("list is"+list);
		new BookingGUI(list,len);
	}

	
	@Override
	public String available(String query) throws SQLException{
		String strlist="";
		//String query= "Select room_number from availableroom where room_type='FamilyRoom'";
		ResultSet res= new DatabaseConnector().query(query);
		while(res.next()){
			strlist += res.getInt("room_number")+" ";
			len++;
			}
		return strlist;
	}


}
