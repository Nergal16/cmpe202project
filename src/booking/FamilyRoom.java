package booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FamilyRoom implements RoomVariety{

	@Override
	public List<Integer> available() throws SQLException{
		
		String query= "Select room_number from availableroom where room_type='FamilyRoom'";
		ResultSet res= new DatabaseConnector().query(query);
		while(res.next())
			list.add(res.getInt("room_number"));
		return list;
	}


}
