package duke;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * deals with invalid commands from user's input
 */
public class DukeException extends Exception {

    //all the commands
    private String[] commands = new String[] {"deadline", "todo", "event", "delete", "mark", "unmark", "find", "search"};

    public DukeException(String message) {
        super(message);
    }

    public DukeException() {}

    /**
     * check if the user's input are valid
     * @param inp
     * @throws DukeException
     */
    public void invalidCommands (String inp) throws DukeException {
        String[] temp = inp.split(" ", 2);
        String cmd = temp[0];
        int size = temp.length;

        if (inp.equals("list") || inp.equals("bye")) {

        } else {

            if (!Arrays.asList(commands).contains(cmd)) {
                throw new DukeException("Sorry!! There is no such command!!");
            } else if (size == 1) {
                throw new DukeException("Oopss!! The description cannot be empty");
            } else if (cmd.equals("deadline")) {
                invalidDeadline(temp[1]);
            } else if (cmd.equals("event")) {
                invalidEvent(temp[1]);
            }
        }
    }

    /**
     * check if the deadline commands input by user is valid
     * @param descrip
     * @throws DukeException
     */
    public void invalidDeadline (String descrip) throws DukeException {
        assert descrip.contains("/by") : "Please include /by in your command";
        if ((descrip.split("/by", 2)[0].equals(""))) {
            throw new DukeException("Please indicate the a task for this deadline");
        } else if (descrip.indexOf("/by") == -1) {
            throw new DukeException("Please indicate the deadline for this task");
        } else if ((descrip.split("/by", 2)[1].equals(""))) {
            throw new DukeException("Please indicate the a deadline for this task, e.g /by Sunday");
        }
    }

    /**
     * @param date
     * @throws DukeException
     */
    public void invalidDate (String date) throws DukeException {
        if (!date.contains("/")) {
            throw new DukeException("Please format your date as DD/MM/YYYY");
        } else if (!isValid(date)) {
            throw new DukeException("Please format your date as DD/MM/YYYY");
        }
    }
    /**
     * @param dateStr
     * @return whether the date is in a valid format
     */
    public boolean isValid(String dateStr) {
        int noOfSlash = dateStr.length() - dateStr.replaceAll("/", "").length();
        if (!dateStr.contains("/") || noOfSlash < 2) {
            return false;
        }
        String[] str = dateStr.split("/", 3);
        int dateLength = str[0].length();
        int monthLength = str[1].length();
        int yearLength = str[2].length();
        if (dateLength == 2 && monthLength == 2 && yearLength == 4) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check if the event commands input by user is valid
     * @param descrip
     * @throws DukeException
     */
    public void invalidEvent (String descrip) throws DukeException {
        assert descrip.contains("/at") : "Please include /at in your command";
        if ((descrip.split("/at", 2)[0].equals(""))) {
            throw new DukeException("Please indicate the a task for this event");
        } else if (descrip.indexOf("/at") == -1) {
            throw new DukeException("Please indicate the time for this event");
        } else if ((descrip.split("/at", 2)[1].equals(""))) {
            throw new DukeException("Please indicate the a time for this event, e.g /at 4pm");
        }
    }
    /**
     * @param deleteNo
     * @param list
     * @throws DukeException
     */
    public void invalidDelete (int deleteNo, ArrayList<Task> list) throws DukeException {
        if (deleteNo > list.size()) {
            throw new DukeException("You only have " + list.size() + " tasks. Please input a number again.");
        }
    }

}
