package batman.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import batman.parser.DateUtil;

public class Todo extends Task {

    private LocalDateTime time;

    /**
     * @param isDone Whether the task is done.
     * @param description Description of the task.
     * @throws DateTimeParseException  If by is wrongly formatted.
     */
    public Todo(boolean isDone, String description, String time) {
        super(description);
        this.isDone = isDone;
        this.time = DateUtil.stringToDate(time);
    }

    /**
     * @param description Description of the task.
     * @throws DateTimeParseException  If by is wrongly formatted.
     */
    public Todo(String description) throws DateTimeParseException {
        this(false, description, DateUtil.dateToString(LocalDateTime.now()));
    }

    /**
     * Returns Task's details to store in file.
     *
     * @return String object of task.
     */
    @Override
    public String appendToFile() {
        return "T|" + (super.isDone ? "1" : "0") + "|" + super.description
                + "|" + "Date Added: " + DateUtil.dateToString(time) + "\n";
    }

    /**
     * Returns details of task's date and time.
     *
     * @return LocalDateTime object
     */
    @Override
    public LocalDateTime getDateTime() {
        return time;
    }

    /**
     * Returns task's type.
     *
     * @return String object of task's type.
     */
    @Override
    public String taskType() {
        return "T";
    }

    /**
     * Checks if keyword is found in tasks.
     *
     * @param keyword Total number of tasks.
     * @return Boolean object of whether matched tasks exists.
     */
    @Override
    public boolean contains(String keyword) {
        String task = taskType() + description;
        return task.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns status icon and description of tasks.
     *
     * @return String object of task's details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
