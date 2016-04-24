/**
 * Created by cmpe 202 - group 4 on 4/12/16.
 */
public interface ReceiptFormatter {
    String formatHeader();
    String formatItem(UserAccount acc, int theTransactionID, int theNowTransactionID);
    String formatFooter();
}
