public class Login {
	
	private UserFacade user;
	  
	public Login (UserFacade user) {
	  this.user = user;
	 }
 
    public boolean authenticate() {
    	
    	String chkUser; 
    	//chkUser = UserData.getPassword(user.getUserName());
     	chkUser = UserFacade.matchGetPassword(user.userGetUserName());
     	if (chkUser!=null)
     	{
    	  if(user.userGetPassword().equals(chkUser))
    	  {
            return true;
    	  }
        }
        return false;      

    }
}