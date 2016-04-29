package booking;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StandardRoom implements RoomVariety{

	public StandardRoom() throws SQLException {
		String list = available();
		System.out.println("list is"+list);
		new BookingGUI(list);
	}

	@Override
	public String available() throws SQLException {
		String strlist="";
		String query= "Select room_number from availableroom where room_type='StandardRoom'";
		ResultSet res= new DatabaseConnector().query(query);
		while(res.next())
			{
			list.add(res.getInt("room_number"));
		strlist += "\n"+res.getInt("room_number");
		
		}
		System.out.println("strlist"+strlist);	
		return strlist;
	
		
	}

}
