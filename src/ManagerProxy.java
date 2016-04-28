public class ManagerProxy implements IManager{

 private Manager manager;
 private User user;
  
 public ManagerProxy(User user) {
  this.user = user;
 }

 public void managerOperations() {
 	String chkUser; 
 	chkUser = UserData.getPassword(user.getUserName());
 	if (chkUser!=null)
 	{
	  if(user.getPassword().equals(chkUser))
	  {
	   manager=new Manager();
	   manager.managerOperations();
	  }
 	}  
 }
} 