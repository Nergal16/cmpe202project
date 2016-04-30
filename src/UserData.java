import java.util.HashMap;
public class UserData {

	private static HashMap<String, String> ud;
	
	private static void setData(){
		 ud = new HashMap<String, String>(); 
		 ud.put("manager", "password");
		 ud.put("admin", "password2");
		 ud.put("ceo", "password3");
	}	
	
	public static String getPassword(String user) {
		setData();
		 if(ud.containsKey(user)){
			 return ud.get(user);
		 }
		 return null;
	 }   
}