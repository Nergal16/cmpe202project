import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by cmpe 202 - group 4 on 4/12/16.
 */
public class StrategySimpleReceipt implements ReceiptFormatter {
    private double total;

    /**
     * prepares the header for the receipt
     * @return header to be displayed
     */
    @Override
    public String formatHeader() {
        total = 0;
        String message = "";
        message = "\n\t\t***Your Current Transaction Receipt***\n";
        message+= "\t\t-----------------------------------------------------\n\n";

        return message;

    }//formatHeader

    /**
     * prepares message containing the information about the current guest's
     * reservation(s)
     * @param acc the account for the current user
     * @param theTransactionID the transaction ID
     * @param theNowTransactionID the current transaction ID
     * @return the message to be displayed with amounts due
     */
    @Override
    public String formatItem(UserAccount acc, int theTransactionID, int theNowTransactionID) {
        String message = "";
        int i = 0;

        while(i < acc.enterDate.size()) {
            if (theTransactionID == acc.transactionID.get(i)) {
                total += acc.getPrice(i);
            }//if
            i++;
        }//while

        int count = 1;
        int nowTransactionID = 0;

        i = 0;
        while(i < acc.enterDate.size()) {
            if (nowTransactionID != acc.nowTransactionID.get(i)
                    && theTransactionID == acc.transactionID.get(i)) {

                message += " User ID:\t\t" + acc.getUserId();
                message += "\n User Name:\t\t" + acc.userName;
                message += "\n Reservation:\t\t" + (count);
                count++;
                nowTransactionID = acc.nowTransactionID.get(i);

                //format checkIn date
                DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
                String enterD = outputFormatter.format(acc.getEnterDate(i));
                message += "\n Check-In Date:\t\t" + enterD;

                //format checkOut date
                String exitD = outputFormatter.format(acc.getExitDate(i));
                message += "\n Check-Out Date:\t" + exitD;
                message += "\n Room Number:\t" + acc.getRoomNumber(i);
                message += "\n Price per Night:\t\t$" + String.format("%.2f\n", (double)acc.getPrice(i));

                int type = 0;
                if (acc.getRoomNumber(i) > 199) {
                    type = 200;
                }//if
                else {
                    type = 100;
                }//else

                try {
                    Date inDate = outputFormatter.parse(enterD);
                    Date outDate = outputFormatter.parse(exitD);
                    long duration  = outDate.getTime() - inDate.getTime();
                    int diffInDays = (int) TimeUnit.MILLISECONDS.toDays(duration) + 1;
                    double totalForThisTransaction = (diffInDays-1) * type;
                    message += " Number of nights:\t" + (diffInDays-1) + "\n";
                    message += " Total due for this transaction:\t$"
                            + String.format("%.2f\n", totalForThisTransaction) + "\n";
                } catch (ParseException ex) {}

                message += " ************************************************\n\n";
            }//if
            i++;
        }//while

        return message;

    }//formatItem

    /**
     * prepares the footer for the receipt
     * @return footer to be displayed
     */
    @Override
    public String formatFooter() {
        if (total == 0) return "\n\n There is no TOTAL DUE! \n\n You did not "
                + "place any transactions at this session! Goodbye\n";
        return (String.format("\n\n TOTAL AMOUNT:\t$%.2f\n", total));

    }//formatFooter

}
