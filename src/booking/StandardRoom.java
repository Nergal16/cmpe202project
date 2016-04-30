package booking;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StandardRoom implements RoomVariety{
 int len=0;
	public StandardRoom() throws SQLException {
		String list = available();
		System.out.println("list is"+list);
		new BookingGUI(list,len);
	}

	@Override
	public String available() throws SQLException {
		String strlist="";
		String query= "Select room_number from availableroom where room_type='StandardRoom'";
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
