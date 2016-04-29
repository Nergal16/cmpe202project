import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
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

    /**
     * executes the strategy
     * @param theTransactionID the transaction ID for the entire session
     * @param theNowTransactionID the transaction ID for this transaction
     * @return message the message to be printed on as the receipt
     */
    public String executeStrategy(int theTransactionID, int theNowTransactionID) {
        String message= "";
        message = this.receiptFormatter.formatHeader();

        //Iterator to iterate the treeMapGuest
        Iterator<UserAccount> iterator = treeMapGuest.values().iterator();
        while(iterator.hasNext()) {
            UserAccount u = iterator.next();
            if (userID.equals(u.getUserId()))
                message += receiptFormatter.formatItem(u, theTransactionID, theNowTransactionID);
        }//while
        message += this.receiptFormatter.formatFooter();

        return message;

    }//executeStrategy

    /**
     * attaching the change listeners to the array list of listeners
     * @param listener to be added
     */
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }//addChangeListener

}
