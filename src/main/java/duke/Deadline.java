package duke;

public class Deadline extends Task {
    public Deadline(String description) {
        super(description);
    }

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

    public String getDescription() {

        String newReply = super.description.replace("deadline ", "");
        String taskAtHand = newReply.split("/")[0];
        String deadLine = newReply.split("/")[1].replace("by ", "by: ");
        String date = deadLine.split(" ")[1].split("-")[0];
        String month = digitMonth_to_AlphabeticalMonth(deadLine.split(" ")[1].split("-")[1]);
        String year = deadLine.split(" ")[1].split("-")[2];
        String time = Integer.toString(Integer.parseInt(deadLine.split(" ")[2]) % 1200);
        if (time.length() < 4 ) {
            time += "pm";
        } else {
            time += "am";
        }
        String finalDescription = taskAtHand + "(" + "by " + date + " " + month + " " + year + ", " + time + ")";
        return "[D]" + "[" + super.getStatusIcon() + "] " + finalDescription;
    }
}
