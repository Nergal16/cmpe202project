package booking;

import java.sql.SQLException;

public class Manager extends HotelObserver{

	public Manager(HotelRoomSubject subject){
		subject.addUsers(this);
	}
	@Override
	public void update(String str, CustomerDetails cust) {
		System.out.println("manager notified");	 
		String query = null;
		for(int i=0; i< cust.getNumberOfRooms();i++)
		{
			query = "INSERT INTO managerdata VALUES('"+ cust.getCustomerID()+"','"+cust.getRoomsAvailable().split(" ")[i]+"','"+str+"' )";
		action(query);
		}
		
	}

	@Override
	public void action(String str) {
		System.out.println("query in manager action class" + str);
		try {
			new DatabaseConnector().update(str);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
