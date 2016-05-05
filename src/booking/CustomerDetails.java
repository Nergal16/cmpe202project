package booking;

public class CustomerDetails {
	private String customerID;
	private String roomsAvailable;
	private String roomsType;
	private String startDate;
	private String endDate;
	private int numberOfRooms;
	private String statusMessage;
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public String getRoomsType() {
		return roomsType;
	}
	public void setRoomsType(String roomsType) {
		this.roomsType = roomsType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getRoomsAvailable() {
		return roomsAvailable;
	}
	public void setRoomsAvailable(String roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}
}
