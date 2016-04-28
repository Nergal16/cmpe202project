package booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextArea;

public class StandardRoom implements RoomVariety{

	public StandardRoom(JTextArea textAreaGuest) throws SQLException {
		List<Integer> list = available();
	
		//textAreaGuest.setText(available());
	}

	@Override
	public List<Integer> available() throws SQLException {
		String strlist="";
		String query= "Select room_number from availableroom where room_type='FamilyRoom'";
		ResultSet res= new DatabaseConnector().query(query);
		while(res.next())
			{list.add(res.getInt("room_number"));
			// strlist = strlist.append("")
			}
		return list;
	
		
	}

}
