package booking;

import java.sql.SQLException;

public class Customer extends HotelObserver {

	public Customer(HotelRoomSubject subject){
		subject.addUsers(this);
	}
	@Override
	public void update(String str, CustomerDetails cust){
	System.out.println("ticket booked successfully"+str);
	String rooms = cust.getRoomsAvailable();
	String query ="";
	String statusMessage ="";
	//ALSO UPDATE THE AVAILABLEROOM AND CUSTOMERDETAIL TABLE
	if(str.equals("book")){
		System.out.println("in book");
		
		for(int i=0; i< cust.getNumberOfRooms();i++)
		query = "INSERT INTO customerdetails VALUES ('"+cust.getRoomsAvailable().split(" ")[i]+"','"+cust.getCustomerID()+"','"+cust.getRoomsType()+"','"+cust.getStartDate()+"','"+cust.getEndDate()+"')";
		 action(query);
		 query = "DELETE from availableroom where roomNumber ="+cust.getRoomsType();
		 action(query);
		 statusMessage = "Congrats! Your room is booked";
	}
		else{
		 query = "DELETE from customerdetails where customerID ="+cust.getCustomerID()+" and roomNumber ="+cust.getRoomsType();
		 action(query);
		 query = "INSERT INTO availableroom VALUES ('"+cust.getRoomsAvailable().split(" ")[0]+"','"+cust.getRoomsType()+"','"+cust.getStartDate()+"','"+cust.getEndDate()+"')";
		 action(query);
		 statusMessage = "Your room is cancelled";
		}
	
	cust.setStatusMessage(statusMessage);
	System.out.println("customer notified");	 
	
		
	}

	@Override
	public void action(String str) {
		System.out.println("query in customer action class" + str);
		try {
			new DatabaseConnector().update(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
