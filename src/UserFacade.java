public class UserFacade {
	private User user;
	private String userN;
	private String passD;
	
	public UserFacade(String userName, String password) {
		userN = userName;
		passD = password;
		user = new User(userN, passD);	
	}
	public static String matchGetPassword(String user){
		return UserData.getPassword(user);
	}
	public String userGetUserName() {
		return user.getUserName();
	}
	public String userGetPassword() {
		return user.getPassword();
	}


}
