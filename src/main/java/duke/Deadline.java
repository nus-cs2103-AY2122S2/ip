package duke;
/**
 * Represents the deadline tasks input by the user
 */
public class Deadline extends Task {
    /**
     * Super constructor to the task class
     *
     * @param description the description of the deadline task
     * @return
     * @throws
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Super constructor to the task class
     *
     * @param digitMonth the month represented numerically
     * @return corresponding month in words if valid, else returns notValidMonth
     * @throws
     */
    public String digitMonth_to_AlphabeticalMonth(String digitMonth) {
        if (digitMonth.equals("01")) {
            return "January";
        } else if (digitMonth.equals("02")) {
            return "February";
        } else if (digitMonth.equals("03")) {
            return "March";
        } else if (digitMonth.equals("04")) {
            return "April";
        } else if (digitMonth.equals("05")) {
            return "May";
        } else if (digitMonth.equals("06")) {
            return "June";
        } else if (digitMonth.equals("07")) {
            return "July";
        } else if (digitMonth.equals("08")) {
            return "August";
        } else if (digitMonth.equals("09")) {
            return "September";
        } else if (digitMonth.equals("10")) {
            return "October";
        } else if (digitMonth.equals("11")) {
            return "November";
        } else if (digitMonth.equals("12")) {
            return "December";
        } else {
            return "notValidMonth";
        }

    }

    /**
     * Returns deadline task in string format
     *
     * @param
     * @return deadline task in a string format, changing the numerical months to
     * words, and 24h time to 12h time
     * @throws
     */
    public String getDescription() {

        String newReply = super.description.replace("deadline ", "");
        String taskAtHand = newReply.split("/")[0];
        String deadline = newReply.split("/")[1].replace("by ", "by: ");
        String date = deadline.split(" ")[1].split("-")[0];
        String month = digitMonth_to_AlphabeticalMonth(deadline.split(" ")[1].split("-")[1]);
        String year = deadline.split(" ")[1].split("-")[2];
        String time = Integer.toString(Integer.parseInt(deadline.split(" ")[2]) % 1200);
        if (time.length() < 4 ) {
            time += "pm";
        } else {
            time += "am";
        }
        String finalDescription = taskAtHand + "(" + "by " + date + " " + month + " " + year + ", " + time + ")";
        return "[D]" + "[" + super.getStatusIcon() + "] " + finalDescription;
    }
}
