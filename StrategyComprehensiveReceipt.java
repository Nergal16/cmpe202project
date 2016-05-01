
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by cmpe 202 - group 4 on 4/12/16.
 */
public class StrategyComprehensiveReceipt implements ReceiptFormatter {
    private double total;

    /**
     * prepares the header for the receipt
     * @return header to be displayed
     */
    @Override
    public String formatHeader() {
        total = 0;
        String message = "";
        message = "\n\t\t***All of Your Valid Transactions Receipt***\n";
        message+= "\t\t-----------------------------------------------------------\n\n";

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
        int transactionID = 0;
        int i = 0;

        GregorianCalendar gcalendar = new GregorianCalendar();
        gcalendar.setTime(new Date());
        gcalendar.set(Calendar.HOUR_OF_DAY, 0);
        gcalendar.set(Calendar.MINUTE, 0);
        gcalendar.set(Calendar.SECOND, 0);
        gcalendar.set(Calendar.MILLISECOND, 0);
        Date todayDate = gcalendar.getTime();

        //calculate total amount due
        while(i < acc.enterDate.size()) {
            if ((todayDate.before(acc.getEnterDate(i))
                    || todayDate.equals(acc.getEnterDate(i)))) {
                total += acc.getPrice(i);
            }//if
            i++;
        }//while

        int count = 1;

        i = 0;
        transactionID =0;
        while(i < acc.enterDate.size()) {
            if ((todayDate.before(acc.getEnterDate(i))
                    || todayDate.equals(acc.getEnterDate(i)))
                    && transactionID != acc.nowTransactionID.get(i)) {
                transactionID = acc.nowTransactionID.get(i);
                message += " User ID:\t\t" + acc.getUserId();
                message += "\n User Name:\t\t" + acc.userName;
                message += "\n Reservation:\t\t" + (count);
                count++;

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
        if (total == 0) return "\n\n There is no TOTAL DUE! \n\n You have no "
                + "valid transactions! \n Note: valid transactions means "
                + "any transactions that are not prior to this date. \n\n Goodbye\n";
        return (String.format("\n\n TOTAL AMOUNT:\t$%.2f\n", total));

    }//formatFooter
}
