package tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
// import java.time.temporal.ChronoUnit;

/**
* A Event class extending the Task class to create a task for the user to do.
* Includes date and time of said event.
*/
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time = null;

    /**
    * Class constructor.
    * <p>
    * Defaults the isDone boolean to false
    *
    * @param  taskName  String containing the desired name of the event
    * @param  date      LocalDate containing the date of the event
    * @param  time      LocalTime containing the time of the event
    * @see    Task
    */
    public Event(String taskName, LocalDate date, LocalTime time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    /**
    * Class constructor.
    * <p>
    * Defaults the isDone boolean to false
    * Defaults LocalTime time to null
    *
    * @param  taskName  String containing the desired name of the event
    * @param  date      LocalDate containing the date of the event
    * @see    Task
    */
    public Event(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    /**
    * Class constructor.
    *
    * @param  taskName  String containing the desired name of the event
    * @param  date      LocalDate containing the date of the event
    * @param  time      LocalTime containing the time of the event
    * @param  isDone    boolean denoting whether the event is done
    * @see    Task
    */
    public Event(String taskName, LocalDate date, LocalTime time, boolean isDone) {
        super(taskName, isDone);
        this.date = date;
        this.time = time;
    }

    /**
    * Class constructor.
    * <p>
    * Defaults LocalTime time to null
    *
    * @param  taskName  String containing the desired name of the event
    * @param  date      LocalDate containing the date of the event
    * @param  isDone    boolean denoting whether the event is done
    * @see    Task
    */
    public Event(String taskName, LocalDate date, boolean isDone) {
        super(taskName, isDone);
        this.date = date;
    }

    /**
    * Returns LocalDate date.
    *
    * @return   The LocalDate date of the event
    */
    public LocalDate getDate() {
        return this.date;
    }

    /**
    * Returns LocalTime time.
    *
    * @return   The LocalTime time of the event
    */
    public LocalTime getTime() {
        return this.time;
    }

    /**
    * Returns the formatted String of the task.
    *
    * @return      the formatted String
    */
    @Override
    public String toString() {
        String returnString = "";
        String formatDate = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        returnString += "[E]" + super.toString() + " (at: " + formatDate;
        if (this.time != null) {
            String formatTime = this.time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            returnString += " " + formatTime;
        }
        returnString += ")";
        return returnString;
    }
}