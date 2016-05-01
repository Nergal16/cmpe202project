import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cmpe 202 - group 4 on 4/12/16.
 */
public class Room implements Serializable {
    ArrayList<Boolean> roomArr;
    ArrayList<String> userIDArr;
    int numberOfDaysReserved;

    /**
     * adds room to the hotel and initializes them
     * @return array list of boolean representing 20 empty rooms for this
     * date
     */
    public ArrayList<Boolean> addRoom() {
        roomArr = new ArrayList<Boolean>(20);
        userIDArr = new ArrayList<String>(20);
        for (int i = 0; i < 20; i++) {
            roomArr.add(i, true);
            userIDArr.add(i, "");
        }//for
        return roomArr;

    }//addRoom

    /**
     * associates a user with the room
     * @return array list of string to enable it inputing user id later
     * to the room
     */
    public ArrayList<String> addUserToRoom() {
        userIDArr = new ArrayList<String>(20);
        for (int i = 0; i < 20; i++) {
            userIDArr.add(i, "");
        }//for
        return userIDArr;

    }//addUserToRoom

    /**
     * get the array list of user id
     * @return array list of string representing user id
     */
    public ArrayList<String> getUser() {
        return userIDArr;

    }//getUser

    /**
     * sets the total number that the user wants to stay for this reservation
     * @param num the number of staying
     */
    public void setNumberofSatyingDays (int num) {
        numberOfDaysReserved = num;

    }//setNumberofSatyingDays

    /**
     * gets the array list of boolean representing the rooms for this date
     * @return array list of boolean
     */
    public ArrayList<Boolean> getRoom() {
        return roomArr;

    }//getRoom

    /**
     * checks if this room is empty
     * @param i the index of the room to be retrieved
     * @return the boolean representing room availability. true for empty,
     * false for full
     */
    public boolean isRoomEmpty(int i) {
        return roomArr.get(i);

    }//isRoomEmpty
}
