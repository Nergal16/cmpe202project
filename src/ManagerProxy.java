public class ManagerProxy implements IManager{

 private Manager manager;
 private UserFacade user;
  
 public ManagerProxy(UserFacade user) {
  this.user = user;
 }

 public void managerOperations() {
 	String chkUser; 
 	//chkUser = UserData.getPassword(user.getUserName());
 	chkUser = UserFacade.matchGetPassword(user.userGetUserName());
 	if (chkUser!=null)
 	{
	  if(user.userGetPassword().equals(chkUser))
	  {
	   manager=new Manager();
	   manager.managerOperations();
	  }
 	}  
 }
} 