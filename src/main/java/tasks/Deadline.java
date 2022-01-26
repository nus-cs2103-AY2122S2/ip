package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// import java.time.temporal.ChronoUnit;
import java.time.LocalTime;
/**
* A Deadline class extending the Task class to create a task for the user to do.
* Includes date and time of said event.
*/
public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time = null;

    /**
    * Class constructor.
    * <p>
    * defaults the isDone boolean to false
    *
    * @param  taskName  String containing the desired name of the deadline
    * @param  date      LocalDate containing the date of the deadline
    * @param  time      LocalTime containing the time of the deadline
    * @see    Task
    */
    public Deadline(String taskName, LocalDate date, LocalTime time) {
        super(taskName);
        this.date = date;
        this.time = time;
    }

    /**
    * Class constructor.
    * <p>
    * defaults the isDone boolean to false
    * defaults LocalTime time to null
    *
    * @param  taskName  String containing the desired name of the deadline
    * @param  date      LocalDate containing the date of the deadline
    * @see    Task
    */
    public Deadline(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    /**
    * Class constructor.
    *
    * @param  taskName  String containing the desired name of the deadline
    * @param  date      LocalDate containing the date of the deadline
    * @param  time      LocalTime containing the time of the deadline
    * @param  isDone    boolean denoting whether the deadline is done
    * @see    Task
    */
    public Deadline(String taskName, LocalDate date, LocalTime time, boolean isDone) {
        super(taskName, isDone);
        this.date = date;
        this.time = time;
    }

    /**
    * Class constructor.
    * <p>
    * defaults LocalTime time to null
    *
    * @param  taskName  String containing the desired name of the deadline
    * @param  date      LocalDate containing the date of the deadline
    * @param  isDone    boolean denoting whether the deadline is done
    * @see    Task
    */
    public Deadline(String taskName, LocalDate date, boolean isDone) {
        super(taskName, isDone);
        this.date = date;
    }

    /**
    * Returns LocalDate date.
    *
    * @return   The LocalDate date of the deadline
    */
    public LocalDate getDate() {
        return this.date;
    }

    /**
    * Returns LocalTime time.
    *
    * @return       The LocalTime time of the deadline
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
        returnString += "[D]" + super.toString() + " (at: " + formatDate;
        if (this.time != null) {
            String formatTime = this.time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            returnString += " " + formatTime;
        }
        returnString += ")";
        return returnString;
    }
}