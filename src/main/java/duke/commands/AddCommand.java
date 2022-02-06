package duke.commands;

import duke.exception.ChiException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Command for adding a task.
 */
public class AddCommand extends Command {

    /** The type of add command */
    private String command;
    /** The message body */
    private String description;

    /**
     * Constructor of the class.
     *
     * @param s The message sent by the user in array format.
     */
    public AddCommand(String[] s) {
        this.command = s[0].toLowerCase();
        this.description = String.join(" ", Arrays.copyOfRange(s, 1, s.length)).trim();
    }

    /**
     * Adds a task to the TaskList and Storage based on the type of task it is.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A String of the task added.
     * @throws ChiException If there are problems in the task description or date formatting.
     */
    @Override
    public String execute(TaskList tl, Storage sge) throws ChiException {
        try {
            switch(command) {
            case "todo":
                if (validateMessageBody(this.description, command)) {
                    throw new ChiException("This todo has some problems nyan!");
                } else {
                    String s = this.description;
                    Task newTask = new Todo(s, false);
                    // Add task to list
                    tl.addTask(newTask);
                    sge.updateFile(newTask, tl, "todo");
                    return String.format("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask, tl.getSize());
                }
                // FallThrough
            case "deadline":
                if (validateMessageBody(this.description, command)) {
                    throw new ChiException("This deadline has some problems nyan!");
                } else {
                    Task newTask;
                    String s = getDescription(this.description, "deadline");
                    LocalDate ld = getDeadlineDate(this.description);
                    LocalTime lt = getDeadlineTiming(this.description);
                    newTask = new Deadline(s, ld, lt, false);
                    tl.addTask(newTask);
                    sge.updateFile(newTask, tl, "deadline");
                    assert tl.getSize() > 0 : "After adding, there should not be 0 tasks";
                    return String.format("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask, tl.getSize());
                }
                // FallThrough
            case "event":
                if (validateMessageBody(this.description, command)) {
                    throw new ChiException("This event has some problems nyan!");
                } else {
                    Task newTask;
                    String s = getDescription(this.description, "event");
                    LocalDate ld = getEventDate(this.description);
                    LocalTime lt = getEventTimingStart(this.description);
                    LocalTime lt1 = getEventTimingEnd(this.description);
                    newTask = new Event(s, ld, lt, lt1, false);
                    tl.addTask(newTask);
                    sge.updateFile(newTask, tl, "deadline");
                    assert tl.getSize() > 0 : "After adding, there should not be 0 tasks";
                    return String.format("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask, tl.getSize());
                }
                // FallThrough
            default:
                throw new ChiException("Oopsies something went wrong while parsing!");
            }
        } catch (IOException e) {
            throw new ChiException("Hey something went wrong with the IO nyan!");
        }
    }

    /**
     * Checks if the message body conforms to valid syntax.
     *
     * @param msg The message sent by user.
     * @param type The type of add command used.
     * @return A boolean of whether the message is valid format.
     */
    public boolean validateMessageBody(String msg, String type) {
        switch(type) {
        case "todo":
            // No specification for todos to have any format
            return false;
            // FallThrough
        case "deadline":
            String[] separateBys = msg.split("/by");
            if (separateBys.length != 2 || separateBys[0].equals("")) {
                return true;
            }
            try {
                String datetime = msg.split("/by")[1].trim();
                LocalDateTime dt = LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                return false;
            } catch (DateTimeParseException e) {
                return true;
            }
            // FallThrough
        case "event":
            String[] separateAts = msg.split("/at");
            if (separateAts.length != 2 || separateAts[0].equals("")) {
                return true;
            }
            try {
                String datetime = msg.split("/at")[1].trim();
                if (datetime.split(" ").length != 2
                        || datetime.split(" ")[1].split("-").length != 2) {
                    return true;
                }
                LocalDate d = LocalDate.parse(datetime.split(" ")[0].trim(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalTime t1 = LocalTime.parse(datetime.split(" ")[1].split("-")[0].trim(),
                        DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime t2 = LocalTime.parse(datetime.split(" ")[1].split("-")[1].trim(),
                        DateTimeFormatter.ofPattern("HH:mm"));
                return t1.isAfter(t2);
            } catch (DateTimeParseException e) {
                return true;
            }
            // FallThrough
        default:
            return true;
        }
    }

    /**
     * Obtains the task description in the message.
     *
     * @param msg The message sent.
     * @param type The type of command used.
     * @return A String of task description.
     */
    public String getDescription(String msg, String type) {
        if (type.equals("deadline")) {
            return msg.split("/by")[0].trim();
        } else {
            return msg.split("/at")[0].trim();
        }
    }

    /**
     * Get the date component of deadline tasks.
     *
     * @param msg The message from user.
     * @return A LocalDate of date specified.
     */
    public LocalDate getDeadlineDate(String msg) {
        String datetime = msg.split("/by")[1].trim();
        return LocalDate.parse(datetime.split(" ")[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Get the time component of deadline tasks.
     *
     * @param msg The message from user.
     * @return A LocalTime of time specified.
     */
    public LocalTime getDeadlineTiming(String msg) {
        String datetime = msg.split("/by")[1].trim();
        return LocalTime.parse(datetime.split(" ")[1].trim(), DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Get the date component of event tasks.
     *
     * @param msg The message from user.
     * @return A LocalDate of date specified.
     */
    public LocalDate getEventDate(String msg) {
        String datetime = msg.split("/at")[1].trim();
        return LocalDate.parse(datetime.split(" ")[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Get the starting time component of event tasks.
     *
     * @param msg The message from user.
     * @return A LocalTime of time specified.
     */
    public LocalTime getEventTimingStart(String msg) {
        String datetime = msg.split("/at")[1].trim();
        return LocalTime.parse(datetime.split(" ")[1].split("-")[0].trim(),
                DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Get the ending time component of event tasks.
     *
     * @param msg The message from user.
     * @return A LocalTime of time specified.
     */
    public LocalTime getEventTimingEnd(String msg) {
        String datetime = msg.split("/at")[1].trim();
        return LocalTime.parse(datetime.split(" ")[1].split("-")[1].trim(),
                DateTimeFormatter.ofPattern("HH:mm"));
    }
}
