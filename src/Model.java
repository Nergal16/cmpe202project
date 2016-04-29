import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by cmpe 202 - group 4 on 4/12/16.
 */
public class Model {
    public TreeMap<Date, Room> treeMapRoom;
    private ArrayList<ChangeListener> listeners;
    private ArrayList<ChangeListener> strategyListeners;
    private String userID;
    public TreeMap<String, UserAccount> treeMapGuest;
    public int transactionID;
    public int nowTransactionID;

    /**
     * constructor for Model class
     * @param treeMapRoom the room information treeMap
     * @param treeMapGuest the guest information treeMap
     * @param transactionID the transaction ID for the reservations
     */
    public Model(TreeMap<Date, Room> treeMapRoom, TreeMap<String, UserAccount> treeMapGuest , int transactionID) {
        this.treeMapGuest = treeMapGuest;
        this.treeMapRoom = treeMapRoom;
        this.transactionID = transactionID;
        listeners = new ArrayList<ChangeListener>();
        strategyListeners = new ArrayList<ChangeListener>();
    }//constructor

    /**
     * accessors to get the list of available rooms
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @return the list of available rooms
     */
    public int[] calculateAvailableRooms(Date checkInDate, Date checkOutDate) {
        long duration  = checkOutDate.getTime() - checkInDate.getTime();
        int diffInDays = (int) TimeUnit.MILLISECONDS.toDays(duration) + 1;

        //sets the calendar to be used to get the available rooms for the date
        GregorianCalendar newGcalendar = new GregorianCalendar();
        newGcalendar.setTime(checkInDate);
        newGcalendar.set(Calendar.HOUR_OF_DAY, 0);
        newGcalendar.set(Calendar.MINUTE, 0);
        newGcalendar.set(Calendar.SECOND, 0);
        newGcalendar.set(Calendar.MILLISECOND, 0);

        Room myroom = null;

        int [][] arr = new int [diffInDays][20];
        for (int i = 0; i < diffInDays; i++) {
            if (treeMapRoom.containsKey(newGcalendar.getTime())) {
                myroom = treeMapRoom.get(newGcalendar.getTime());
            }//if
            else {
                myroom = new Room();
                myroom.addRoom();
            }//else

            for (int j = 0; j < 20; j++) {
                if (myroom.isRoomEmpty(j)) {
                    arr[i][j] = 1;
                }//if
                else arr[i][j] = 0;
            }//for
            newGcalendar.add(Calendar.DAY_OF_YEAR, 1);
        }//for

        //print
        int []count;
        count = new int[20];
        for (int i = 0; i < 20; i++) count[i] = 0;

        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < diffInDays; i++) {
                count[j] += arr[i][j];
            }//for
        }//for

        return count;
    }//calculateAvailableRooms

    /**
     * adds change listener to array list of changeListeners
     * @param listener the listener to be added
     */
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }//addChangeListener

    /**
     * sets the user ID
     * @param uid the user ID
     */
    public void setUserID (String uid) {
        userID = uid;
    }//setUserID

    /**
     * sets this transaction ID
     * @param theTransactionID this transaction ID to be added
     */
    public void setThisTransactionID (int theTransactionID) {
        nowTransactionID = theTransactionID;
    }//setThisTransactionID

    /**
     * mutator to add a reservation
     * @param count the list of available rooms
     * @param checkInDate the check-in date
     * @param checkOutDate the check-out date
     * @param roomTypeSelection the type of the room (luxury or economy)
     */
    public void reserveRoom(int[] count, Date checkInDate, Date checkOutDate, int roomTypeSelection) {
        int lowerRange = 0;
        int upperRange = 20;
        int roomNumber = 0;

        //calculate difference between checkout and checkin dates
        long duration  = checkOutDate.getTime() - checkInDate.getTime();
        int diffInDays= (int)TimeUnit.MILLISECONDS.toDays(duration) + 1;

        GregorianCalendar newGcalendar = new GregorianCalendar();
        newGcalendar.setTime(checkInDate);

        GregorianCalendar outCalendar = new GregorianCalendar();
        outCalendar.setTime(checkOutDate);

        //set indecies
        if (roomTypeSelection == 1) {
            lowerRange = 0;
            upperRange = 10;
            roomNumber = 100;
        }//if

        if (roomTypeSelection == 2) {
            lowerRange = 10;
            upperRange = 20;
            roomNumber = 190;
        }//if
        int i = 0;
        for (i = lowerRange; i < upperRange; i++) {
            if (diffInDays == count[i])
                break;
        }//for
        while (newGcalendar.getTime().before(outCalendar.getTime())) {
            if (!treeMapRoom.containsKey(newGcalendar.getTime())) {
                Room room = new Room();
                ArrayList<Boolean> roomBol = room.addRoom();
                roomBol.set(i, Boolean.FALSE);
                ArrayList<String> roomStr = room.addUserToRoom();
                roomStr.add(i, userID);
                treeMapRoom.put(newGcalendar.getTime(), room);

                //put this room into guest account too
                treeMapGuest.get(userID).setPrice(roomTypeSelection * 100);
                treeMapGuest.get(userID).setRoomNumber(i + roomNumber);
                treeMapGuest.get(userID).serEnterDate(checkInDate);
                treeMapGuest.get(userID).serExitDate(checkOutDate);
                treeMapGuest.get(userID).setTransactionID(transactionID);
                treeMapGuest.get(userID).setThisTransactionID(nowTransactionID);
                treeMapGuest.get(userID).setUserID(userID);

                //notify observers of the change to the treeMapRoom
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : listeners)
                    listener.stateChanged(event);

                //strategy pattern listeners
                ChangeEvent strategyEvent = new ChangeEvent(this);
                for (ChangeListener listener : strategyListeners)
                    listener.stateChanged(strategyEvent);
            }//if
            //already there is room array for this day
            else {
                //check which index to insert
                //add room here
                treeMapRoom.get(newGcalendar.getTime()).getRoom().set(i, Boolean.FALSE);
                treeMapRoom.get(newGcalendar.getTime()).getUser().set(i, userID);

                //put this room into guest account too
                treeMapGuest.get(userID).setPrice(roomTypeSelection * 100);
                treeMapGuest.get(userID).setRoomNumber(i + roomNumber);
                treeMapGuest.get(userID).serEnterDate(checkInDate);
                treeMapGuest.get(userID).serExitDate(checkOutDate);
                treeMapGuest.get(userID).setTransactionID(transactionID);
                treeMapGuest.get(userID).setThisTransactionID(nowTransactionID);
                treeMapGuest.get(userID).setUserID(userID);

                //notify observers of the change to the treeMapRoom
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : listeners)
                    listener.stateChanged(event);

                //strategy pattern listeners
                ChangeEvent strategyEvent = new ChangeEvent(this);
                for (ChangeListener listener : strategyListeners)
                    listener.stateChanged(strategyEvent);
            }//else

            newGcalendar.add(Calendar.DAY_OF_YEAR, 1); //increment day
        }//while

    }//reserveRoom
}
