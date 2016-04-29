import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

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
}
