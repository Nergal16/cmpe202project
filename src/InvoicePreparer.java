package src;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by cmpe 202 - group 4 on 4/12/16.
 */
public class InvoicePreparer {
    public TreeMap<String, UserAccount> treeMapGuest;
    public ArrayList<ChangeListener> listeners;
    public ReceiptFormatter receiptFormatter;
    public String userID;

    /**
     * Constructor for the InvoicePreparer
     * @param receiptFormatter interface for receipt
     * @param treeMapGuest the treeMapGuest
     * @param id the user ID
     */
    public InvoicePreparer(ReceiptFormatter receiptFormatter,
                           TreeMap<String, UserAccount> treeMapGuest, String id) {
        this.treeMapGuest = treeMapGuest;
        listeners = new ArrayList<ChangeListener>();
        this.receiptFormatter = receiptFormatter;
        userID = id;
    }//Constructor

}
