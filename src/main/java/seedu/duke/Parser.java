package seedu.duke;
import java.time.LocalDate;

public class Parser {
    
    private static final int LIST = 0;
    private static final int TODO = 1;
    private static final int DEADLINE = 2;
    private static final int EVENT = 3;
    private static final int MARK = 4;
    private static final int UNMARK = 5;
    private static final int DELETE = 6;
    
    public static int getCommand(String input) throws DukeException {
        String str = input.split(" ", 2)[0];
        
        if (str.equals("list")) {
            return LIST;
        } else if (str.equals("todo")) {
            return TODO;
        } else if (str.equals("deadline")) {
            return DEADLINE;
        } else if (str.equals("event")) {
            return EVENT;
        } else if (str.equals("mark")) {
            return MARK;
        } else if (str.equals("unmark")) {
            return UNMARK;
        } else if (str.equals("delete")) {
            return DELETE;
        } else {
            throw new DukeException("OOPS!!! I don't understand what that means");
        }
        
    }
    
    public static String getDescription(String input) throws DukeException {
        try {
            return input.split(" ", 2)[1].split("/", 2)[0];  
        } catch (Exception e) {
            throw new DukeAbsentInfoException("Description of task not specified");
        }
        
    }
    
    public static int getIndex(String input) throws DukeException {
        try {
            return Integer.parseInt(input.split(" ", 2)[1].split("/", 2)[0]);  
        } catch (Exception e) {
            throw new DukeException("description parsing problem");
        }
        
    }
    
    public static LocalDate getDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input.split(" ", 2)[1].split("/", 2)[1]);
        } catch (Exception e) {
            throw new DukeException("Date not provided or not specified in correct format ([task] [description]/yyyy-mm-dd)");
        }
        
    }
    
    public static Task getTask(String input) throws DukeException {
        
        String[] stringArr = input.split("#");
        
        if (stringArr[0].equals("T")) {
            return new ToDo(stringArr[2], stringArr[1].equals("true"));

        } else if (stringArr[0].equals("D")) {
            return new Deadline(stringArr[2], stringArr[1].equals("true"), LocalDate.parse(stringArr[3]));

        } else if (stringArr[0].equals("E")) {
            return new Event(stringArr[2], stringArr[1].equals("true"), LocalDate.parse(stringArr[3]));

        } else {
            throw new DukeException("Problem retrieving files from data");

        }
        
    }
    
}
