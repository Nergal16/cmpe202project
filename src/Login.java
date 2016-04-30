public class Login {
	
	private User user;
	  
	public Login (User user) {
	  this.user = user;
	 }
 
    public boolean authenticate() {
    	String chkUser; 
     	chkUser = UserData.getPassword(user.getUserName());
     	if (chkUser!=null)
     	{
    	  if(user.getPassword().equals(chkUser))
    	  {
            return true;
    	  }
        }
        return false;      

    }
}