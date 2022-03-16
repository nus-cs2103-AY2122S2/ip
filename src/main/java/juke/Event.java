package juke;

import java.util.ArrayList;

/**
 * Represents the event tasks input by the user
 */
public class Event extends Task {
    /**
     * Super constructor to the task class
     *
     * @param description the description of the event task
     * @return
     * @throws
     */
    public Event(String description, String tag) {
        super(description, tag);
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
        String newReply = super.description.replace("event ", "");
        String taskAtHand = newReply.split("/")[0];
        String deadLine = newReply.split("/")[1].replace("at ", "at: ");
        String date = deadLine.split(" ")[1].split("-")[0];
        String month = digitMonth_to_AlphabeticalMonth(deadLine.split(" ")[1].split("-")[1]);
        String year = deadLine.split(" ")[1].split("-")[2];
        int timeHours = Math.round(Integer.parseInt(deadLine.split(" ")[2]) / 100);
        int timeMinutes = Integer.parseInt(deadLine.split(" ")[2]) - timeHours * 100;
        String time = "";
        if (month == "notValidMonth") {
            return month;
        } else if (Integer.parseInt(date) < 1 || Integer.parseInt(date) > 31) {
            return "notValidDate";
        } else {
            if (deadLine.split(" ")[2].length() < 4 ||
                    !(timeHours <= 23 && timeHours >= 00) ||
                    !(timeMinutes <= 59 && timeMinutes >= 00)) {
                return "notValidTime";
            } else {
                if (timeHours == 0) {
                    time += 12 + ":" + String.format("%02d", timeMinutes) + "am";
                } else if (timeHours == 12) {
                    time += 12 + ":" + String.format("%02d", timeMinutes) + "pm";
                } else if (timeHours <= 12) {
                    time += timeHours + ":" + String.format("%02d", timeMinutes) + "am";
                } else {
                    time += timeHours - 12 + ":" + String.format("%02d", timeMinutes) + "pm";
                }
            }

        }
        String finalDescription = taskAtHand + "(" + "at " + date + " " + month + " " + year + ", " + time + ")";
        return "[E]" + "[" + super.getStatusIcon() + "] " + finalDescription;
    }

    public static boolean isEvent(String[] splittedString) {
        return splittedString[0].equals("event");
    }
    public static String executeEvent(ArrayList<Task> itemList, String[] splittedString, String reply, Outputs op) {
            if (splittedString.length < 5) { //invalid event command
                return op.border +
                        "     ☹ OOPS!!! The details of event cannot be empty.\n" +
                        op.instructions +
                        op.border;
            } else { //valid event command
                Task eventTask = new Event(reply, "");
                if (eventTask.getDescription() == "notValidMonth") {
                    return op.border +
                            "     Oops, you've entered an invalid month! \n" +
                            op.instructions +
                            op.border;
                } else if (eventTask.getDescription() == "notValidDate") {
                    return op.border +
                            "     Oops, you've entered an invalid date! \n" +
                            op.instructions +
                            op.border;
                } else if (eventTask.getDescription() == "notValidTime") {
                    return op.border +
                            "     Oops, you've entered an invalid time! \n" +
                            op.instructions +
                            op.border;
                } else {
                    itemList.add(eventTask);
                    return op.border +
                            "     Got it. I've added this task: \n" +
                            "       " + eventTask.getDescription() + "\n" +
                            "     Now you have " + itemList.size() + " tasks in the list.\n" +
                            op.instructions +
                            op.border;
                }
            }
        }
    }
