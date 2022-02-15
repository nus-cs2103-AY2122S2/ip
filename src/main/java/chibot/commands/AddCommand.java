package chibot.commands;

import chibot.exception.ChiException;
import chibot.storage.Storage;
import chibot.task.Deadline;
import chibot.task.Event;
import chibot.task.Task;
import chibot.task.Todo;
import chibot.tasklist.TaskList;

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

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm";

    /** The type of add command */
    private final String command;
    /** The message body */
    private final String description;

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
     * @return A confirmation String of the task added.
     * @throws ChiException If the command somehow does not equate to any of the given ones.
     */
    @Override
    public String execute(TaskList tl, Storage sge) throws ChiException {
        try {
            switch(command) {
            case "todo":
                return handleTodoCommand(tl, sge);
                // FallThrough
            case "deadline":
                return handleDeadlineCommand(tl, sge);
                // FallThrough
            case "event":
                return handleEventCommand(tl, sge);
                // FallThrough
            default:
                throw new ChiException("Oopsies something went wrong while parsing!");
            }
        } catch (IOException e) {
            throw new ChiException("Hey something went wrong with the IO nyan!");
        }
    }

    /**
     * Deals with the adding of a new Todo task.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A confirmation String of the task added.
     * @throws ChiException If there are problems in the task description or date formatting.
     * @throws IOException If there are problems adding the file to hard-disk.
     */
    public String handleTodoCommand(TaskList tl, Storage sge) throws ChiException, IOException {
        if (validateMessageBody(this.description, command)) {
            throw new ChiException("This todo has some problems nyan! Make sure:\n1.Description is not empty");
        } else {
            Task newTask = new Todo(this.description, false);
            // Add task to list
            tl.addTask(newTask);
            sge.updateFile(newTask, tl, "todo");
            return String.format("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                    newTask, tl.getSize());
        }
    }

    /**
     * Deals with the adding of a new Deadline task.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A confirmation String of the task added.
     * @throws ChiException If there are problems in the task description or date formatting.
     * @throws IOException If there are problems adding the file to hard-disk.
     */
    public String handleDeadlineCommand(TaskList tl, Storage sge) throws ChiException, IOException {
        if (validateMessageBody(this.description, command)) {
            throw new ChiException("This deadline has some problems nyan! Make sure:\n"
                    + "1.Description is not empty\n2.Datetime is not missing\n3.Datetime is formatted properly");
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
    }

    /**
     * Deals with the adding of a new Event task.
     *
     * @param tl The TaskList from each task will be added/deleted.
     * @param sge The Storage which stores/removes tasks from the hard-disk.
     * @return A confirmation String of the task added.
     * @throws ChiException If there are problems in the task description or date formatting.
     * @throws IOException If there are problems adding the file to hard-disk.
     */
    public String handleEventCommand(TaskList tl, Storage sge) throws ChiException, IOException {
        if (validateMessageBody(this.description, command)) {
            throw new ChiException("This event has some problems nyan! Make sure:\n"
                    + "1.Description is not empty\n2.Datetime is not missing\n3.Datetime is formatted properly");
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
            return validateTodoMessage(msg);
            // FallThrough
        case "deadline":
            return validateDeadlineMessage(msg);
            // FallThrough
        case "event":
            return validateEventMessage(msg);
            // FallThrough
        default:
            return true;
        }
    }

    /**
     * Checks if the todo message is of a proper format.
     *
     * @param msg The todo message sent.
     * @return A boolean of whether it was properly formatted.
     */
    public boolean validateTodoMessage(String msg) {
        return msg.contains("\\|");
    }

    /**
     * Checks if the deadline message is of a proper format.
     *
     * @param msg The deadline message sent.
     * @return A boolean of whether it was properly formatted.
     */
    public boolean validateDeadlineMessage(String msg) {
        String[] separateBys = msg.split("/by");
        if (separateBys.length != 2 || separateBys[0].equals("") || separateBys[0].contains("\\|")) {
            return true;
        }
        try {
            String datetime = msg.split("/by")[1].trim();
            LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }

    /**
     * Checks if the event message is of a proper format.
     *
     * @param msg The event message sent.
     * @return A boolean of whether it was properly formatted.
     */
    public boolean validateEventMessage(String msg) {
        String[] separateAts = msg.split("/at");
        if (separateAts.length != 2 || separateAts[0].equals("") || separateAts[0].contains("\\|")) {
            return true;
        }
        try {
            String dateTime = msg.split("/at")[1].trim();
            int numberOfDateTimeTokens = dateTime.split(" ").length;
            int numberOfTimingsSpecified = dateTime.split(" ")[1].split("-").length;

            if (numberOfDateTimeTokens != 2 || numberOfTimingsSpecified != 2) {
                return true;
            }

            String datePortion = dateTime.split(" ")[0].trim();
            String startTiming = dateTime.split(" ")[1].split("-")[0].trim();
            String endTiming = dateTime.split(" ")[1].split("-")[1].trim();

            LocalDate.parse(datePortion, DateTimeFormatter.ofPattern(DATE_FORMAT));

            LocalTime t1 = LocalTime.parse(startTiming, DateTimeFormatter.ofPattern(TIME_FORMAT));
            LocalTime t2 = LocalTime.parse(endTiming, DateTimeFormatter.ofPattern(TIME_FORMAT));

            return t1.isAfter(t2);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
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
        } else if (type.equals("event")) {
            return msg.split("/at")[0].trim();
        } else {
            return "";
        }
    }

    /**
     * Gets the date component of deadline tasks.
     *
     * @param msg The message from user.
     * @return A LocalDate of date specified.
     */
    public LocalDate getDeadlineDate(String msg) {
        String datetime = msg.split("/by")[1].trim();
        String date = datetime.split(" ")[0].trim();
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Gets the time component of deadline tasks.
     *
     * @param msg The message from user.
     * @return A LocalTime of time specified.
     */
    public LocalTime getDeadlineTiming(String msg) {
        String datetime = msg.split("/by")[1].trim();
        String time = datetime.split(" ")[1].trim();
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * Gets the date component of event tasks.
     *
     * @param msg The message from user.
     * @return A LocalDate of date specified.
     */
    public LocalDate getEventDate(String msg) {
        String datetime = msg.split("/at")[1].trim();
        String date = datetime.split(" ")[0].trim();
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Gets the starting time component of event tasks.
     *
     * @param msg The message from user.
     * @return A LocalTime of time specified.
     */
    public LocalTime getEventTimingStart(String msg) {
        String dateTime = msg.split("/at")[1].trim();
        String timings = dateTime.split(" ")[1];
        String startTime = timings.split("-")[0].trim();
        return LocalTime.parse(startTime, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * Gets the ending time component of event tasks.
     *
     * @param msg The message from user.
     * @return A LocalTime of time specified.
     */
    public LocalTime getEventTimingEnd(String msg) {
        String dateTime = msg.split("/at")[1].trim();
        String timings = dateTime.split(" ")[1];
        String startTime = timings.split("-")[1].trim();
        return LocalTime.parse(startTime, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }
}
