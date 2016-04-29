package booking;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Suit implements RoomVariety {
 public Suit() throws SQLException {
		String list = available();
		System.out.println("list is"+list);
		new BookingGUI(list);
	}

	@Override
	public String available() throws SQLException {

		String query= "Select room_number from availableroom where room_type='Suit'";
		ResultSet res= new DatabaseConnector().query(query);
		String strlist="";
		while(res.next())
			strlist += "\n"+res.getInt("room_number");
		return strlist;
	}

}
