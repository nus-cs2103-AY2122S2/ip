package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

/**
 * Class for event related tasks.
 */
public class Event extends Task {
    protected LocalDate eventDate;
    protected DayOfWeek day;
    protected Month month;
    protected int year;
    protected LocalTime time;
    protected Tag tag;

    /**
     * Constructs an Event object.
     *
     * @param description description of event.
     * @param eventDate event date.
     * @param time event time.
     */
    public Event(String description, LocalDate eventDate, LocalTime time) {
        super(description);
        this.eventDate = eventDate;
        this.day = eventDate.getDayOfWeek();
        this.month = eventDate.getMonth();
        this.year = eventDate.getYear();
        this.time = time;
    }

    /**
     * Constructs event object.
     *
     * @param description event description.
     * @param eventDate event date.
     * @param time event time.
     * @param tag event tag.
     */
    public Event(String description, LocalDate eventDate, LocalTime time, Tag tag) {
        super(description);
        this.eventDate = eventDate;
        this.day = eventDate.getDayOfWeek();
        this.month = eventDate.getMonth();
        this.year = eventDate.getYear();
        this.time = time;
        this.tag = tag;
    }

    /**
     * Tags event object.
     *
     * @param taskTag event tag.
     * @return event object.
     */
    @Override
    public Event tag(Tag taskTag) {
        return new Event(description, eventDate, time, taskTag);
    }

    /**
     * Returns a string representation to save to disk.
     *
     * @return String representation to save to disk.
     */
    @Override
    public String toStringForSave() {
        return tag == null
                ? "E " + super.toStringForSave() + " # " + this.eventDate + " " + this.time
                : "E " + super.toStringForSave() + " # " + this.eventDate + " " + this.time + " " + this.tag.toString();
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String represenation.
     */
    @Override
    public String toString() {
        return tag == null
                ? ("[E]" + super.toString() + " (at: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + ")"
                + "\n")
                : ("[E]" + super.toString() + " (at: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + " "
                + this.tag.toString() + ")"
                + "\n");
    }

    /**
     * Loads an event task from the hard disk.
     *
     * @param todoList List of tasks in the hard disk.
     * @param taskDescription Event task description.
     * @param taskStatus Current completion status of the task.
     * @param taskContents Important content details of a task including date and time.
     */
    public static void loadEventTask(ArrayList<Task> todoList, String taskDescription,
                                     String taskStatus, String[] taskContents) {
        String dateAndTimeEvent = taskContents[3];
        String eventDateString = dateAndTimeEvent.split(" ")[0];
        String eventTimeString = dateAndTimeEvent.split(" ")[1];

        // event date and time
        LocalTime eventTime = LocalTime.parse(eventTimeString);
        LocalDate eventDate = LocalDate.parse(eventDateString);

        // creating event task
        Event eventTask = new Event(taskDescription, eventDate, eventTime);

        // check if task is completed
        if (taskStatus.equals("0")) {
            eventTask.markAsNotDone();
        } else {
            eventTask.markAsDone();
        }

        // add eventTask to todoList
        todoList.add(eventTask);
    }
}
