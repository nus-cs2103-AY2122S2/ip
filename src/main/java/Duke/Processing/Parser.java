package Duke.Processing;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Duke.Exception.DukeException;

public class Parser {
    /**
     * Converts the String for a new task to a date
     *
     * @param  date the String the user inputs
     * @return      the String in a date format the system can recognise
     */
    static Date convert1(String date) throws DukeException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //System.out.println(date);
        try {
            return  formatter.parse(date);
        } catch (ParseException e) {
            throw  new DukeException("what is wrong" + e);
        }
    }
    /**
     * Converts the String for a preexisting task to a date
     *
     * @param  date the String the user inputs
     * @return      the String in a date format the system can recognise
     */
    static Date convert2(String date) throws DukeException {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            throw  new DukeException("What is wrong " + e);
        }
    }
    /**
     * Takes in the input command and the Tasklist to modify it accordingly.
     *
     * @param  input the type of command the user has input
     * @param  tasklist the current Tasklist that contains tasks.
     * @return A string output
     */

    public static String parse(String input, TaskList tasklist) throws DukeException{
        String output;
        try {
            if(input.equalsIgnoreCase("list")) {
                output = tasklist.list();
            } else if(input.startsWith("todo")) {
                String item = input.substring(5);
                output = tasklist.addTodo(item);
            } else if(input.startsWith("deadline")) {
                String item = input.substring(9);
                String[] split = item.split(" /by ");
                output = tasklist.addDeadline(split);
            } else if (input.startsWith("Event")) {
                String item = input.substring(6);
                String[] split = item.split(" /at ");
                output = tasklist.addEvent(split);
            } else if (input.startsWith("mark")) {
                String num = input.substring(5);
                output = tasklist.mark(num);
            } else if (input.startsWith("unmark")) {
                String num = input.substring(7);
                output = tasklist.unmark(num);
            } else if (input.startsWith("delete")) {
                String num = input.substring(7);
                output = tasklist.delete(num);
            } else if (input.startsWith("find")) {
                String name = input.substring(5);
                output = tasklist.findS(name);
            } else if (input.equals("clear")) {
                output = tasklist.clear();
            } else {
                throw new DukeException("I'm not sure we can do that");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("We don't even have that many things to do");
        }
        return  output;
    }
}
