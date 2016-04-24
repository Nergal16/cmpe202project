package src;
/**
 * Created by cmpe 202 - group 4 on 4/12/16.
 * Updated 4/22/16
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;


class UserAccount implements Serializable {
    TreeMap<String, UserAccount> treeMapGuest;
    String userName;
    ArrayList<Date> enterDate;
    ArrayList<Date> exitDate;
    ArrayList<Integer> price;
    ArrayList<Integer> roomNumber;
    ArrayList<Integer> transactionID;
    ArrayList<Integer> nowTransactionID;
    String userID;
    
    /**
     * constructor for the UserAccount
     */    
    public UserAccount() {
        enterDate = new ArrayList<Date>();
        exitDate = new ArrayList<Date>();
        price = new ArrayList<Integer>();
        roomNumber = new ArrayList<Integer>();
        transactionID = new ArrayList<Integer>();
        nowTransactionID = new ArrayList<Integer>();
    }//constructor
    

    public boolean createGuestAccount(String userID, String userName, TreeMap<String, UserAccount> theTreeMapGuest) {
        treeMapGuest = theTreeMapGuest;
        UserAccount userAccount = new UserAccount();
        userAccount.setUserID(userID);
        userAccount.setName(userName);
        //user account was successfully created
        if ((userID.trim().length() > 0) && (userName.trim().length() > 0)) {
            if (!(treeMapGuest.containsKey(userID))) {
                treeMapGuest.put(userID, userAccount);
                return true;
            }//if
        }//if

        //userID already exists
        return false;
        
    }//createGuestAccount    
    
    /**
     * sets the price for this room
     * @param thePrice price of the room
     */     
    public void setPrice(int thePrice) {
        price.add(thePrice);
    }//setPrice
    
    /**
     * gets the price for this room
     * @param index the index of the room to be examined
     * @return the price of the room
     */       
    public int getPrice(int index) {
        return price.get(index);
    }//getPrice
    
    /**
     * sets the user id
     * @param id the user id
     */      
    public void setUserID(String id) {
        userID = id;
    }//setUserID
    
    /**
     * gets the user id
     * @return the user id
     */      
    public String getUserId() {
        return userID;
    }//getUserId
    
    /**
     * sets the room number
     * @param number this room number
     */       
    public void setRoomNumber (int number) {
        roomNumber.add(number);
    }//setRoomNumber
    
    /**
     * sets the room number
     * @param index the index of the room
     * @return this room number
     */           
    public int getRoomNumber (int index) {
        return roomNumber.get(index);
    }//getRoomNumber
    
    /**
     * sets the check-in date
     * @param date the check-in date
     */      
    public void serEnterDate(Date date) {//no index is udes!
        enterDate.add(date);
    }//serEnterDate
    
    /**
     * sets the check-in date
     * @param index the index of the room
     * @return the check-in date
     */      
    public Date getEnterDate(int index) {//no index is udes!
        return enterDate.get(index);
    }//getEnterDate
    
    /**
     * sets the check-out date
     * @param date the check-out date
     */    
    public void serExitDate(Date date) {//no index is udes!
        exitDate.add(date);
    }//serExitDate
    
    /**
     * sets the check-out date
     * @param index the index of the room
     * @return the check-out date
     */       
    public Date getExitDate(int index) {
        return exitDate.get(index);
    }//getExitDate
    
    /**
     * sets the transaction id for the entire user reservations
     * @param theTransactionID the transaction id
     */      
    public void setTransactionID(int theTransactionID) {
        this.transactionID.add(theTransactionID);
    }//setTransactionID
    
    /**
     * sets the transaction id for this reservation
     * @param theTransactionID this transaction id
     */         
    public void setThisTransactionID(int theTransactionID) {
        this.nowTransactionID.add(theTransactionID);
    }//setThisTransactionID 
    
    /**
     * gets this transaction id for this reservation
     * @param index this index of this transaction
     * @return this transaction id
     */         
    public int getThisTransactionID(int index) {
        return nowTransactionID.get(index);
    }//getThisTransactionID
    
    /**
     * gets the transaction id for the entire reservations for the user
     * @param index this index of this transaction
     * @return the transaction id for the entire reservations 
     */        
    public int getTransactionID(int index) {
        return this.transactionID.get(index);
    }//getTransactionID
    
    /**
     * gets the name of the user
     * @return the name of the user
     */        
    public String getName() {
        return userName;
    }//getName
    
    /**
     * sets the name of the user
     * @param theUserName the name of the user
     */        
    public void setName(String theUserName) {
        this.userName = theUserName;
    }//setName
    
}//UserAccount class
