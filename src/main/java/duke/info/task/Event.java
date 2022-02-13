package duke.info.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Event extends Task {

    /**
     * Constructs an Event object with the specified event, date, isComplete
     * @param event - the event to be added
     * @param date - the date of the event to be added
     * @param isComplete - whether the task is complete
     */
    public Event(String event, String date, boolean isComplete) {
        super(event, "event", isComplete, date);
    }

    /**
     * Returns an ArrayList of all recurrences of the Event at the specified interval until the specified endDate
     * @param interval - interval between recurrences
     * @param endDate - endDate of recurrences
     * @return
     */
    public ArrayList<Task> getRecurrences(int interval, String endDate) {
        LocalDate start = this.getDate();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d LLL yyyy");
        LocalDate end = LocalDate.parse(endDate, dtf);
        ArrayList<Task> recurringEvents = new ArrayList<>();
        recurringEvents.add(this);
        String dateString = "";
        int count = 0;
        while (end.isAfter(start)) {
            start = start.plusDays(interval);
            dateString = start.format(dtf);
            System.out.println(dateString);
            recurringEvents.add(new Event(this.getAction(), dateString, this.getIsComplete()));
            count++;
        }
        return recurringEvents;
    }
    /**
     * Returns the save format of the Event. The string contains the save format representation from
     * the task superclass, followed by the string representation of the date as obtained from the
     * getDateString of the superclass method delimited by a "|".
     * @return - the save format of the Event as a string
     */
    @Override
    public String saveFormat() {
        return super.saveFormat() + "|" + this.getDateString();
    }

    /**
     * Returns a string presentation of the Event. The string consists of the type of the task
     * contained by "[]", the task description, followed by the date of the Event as obtained
     * by the super.getDateString method.
     * @return - a string representation of the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.getDateString());
    }
}
