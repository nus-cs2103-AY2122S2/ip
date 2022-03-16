package lucifer.commands;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import lucifer.exceptions.InvalidException;
import lucifer.exceptions.WrongFormatException;
import lucifer.task.Deadline;
import lucifer.task.Event;
import lucifer.task.Task;
import lucifer.task.Todo;
import lucifer.tasklist.TaskList;


/**
 * Class AddCommands which inherits from ParseCommands.
 * @author Yu Meng
 */
public class AddCommands extends ParseCommands {
    /** list that stores all the task **/
    private final ArrayList<Task> list;

    /**
     * Constructor for Class AddCommands.
     *
     * @param task the class that stores the list of tasks
     */
    public AddCommands(TaskList task) {
        super(task);
        this.list = task.getTaskList();
    }

    /**
     * Adds the task to the list if it is of type Event.
     *
     * @param input the user input
     */
    public String addEvent(String input) {
        try {
            String[] eventTime = input.split("/at ");
            String[] split = input.split(" ");
            if (split[1].equals("") || eventTime[1].isBlank()) {
                throw new InvalidException();
            } else {
                int index = input.indexOf("/");
                String description = input.substring(6, index);
                String time = eventTime[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime old = LocalDateTime.parse(time, formatter);
                String output = "Got it. I have added this to your desires:\n";
                Event curr = new Event(description, old);
                list.add(curr);
                output += curr;
                output += "\nCurrently you have " + list.size() + (list.size() == 1 ? " thing" : " things")
                        + "  yet to be desired.";
                return output;
            }
        } catch (InvalidException e) {
            return e.getMessage();
        } catch (DateTimeException e) {
            return "My love, you have to give me a correct date format! e.g. 31-12-2022 1800.\n"
                    + "for help, type !help to see the list of commands available.";
        }
    }

    /**
     * Adds the task to the list if it is of type Deadline.
     *
     * @param input the user input
     */
    public String addDeadline(String input) {
        try {
            String[] split = input.split(" ");
            String[] dead = input.split("/by ");
            if (split[1].equals("") || dead[1].isBlank()) {
                throw new InvalidException();
            } else {
                String deadline = dead[1];
                int index = input.indexOf("/");
                String description = input.substring(9, index);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime old = LocalDateTime.parse(deadline, formatter);
                String output = "Got it. I have added this to your desires:\n";
                Deadline curr = new Deadline(description, old);
                list.add(curr);
                output += curr;
                output += "\nCurrently you have " + list.size() + (list.size() == 1 ? " thing" : " things")
                        + " yet to be desired.";
                return output;
            }
        } catch (InvalidException e) {
            return e.getMessage();
        } catch (DateTimeException e) {
            return "My love, you have to give me a correct date format! e.g. 31-12-2022 1800.\n"
                    + "for help, type !help to see the list of commands available.";
        }
    }

    /**
     * Adds the task to the list if it is of type Todo.
     *
     * @param input the user input
     */
    public String addToDo(String input) {
        try {
            String description = input.substring(input.indexOf(" ") + 1);
            if (!description.isBlank()) {
                String output = "Got it. I have added this to your desires:\n";
                Todo curr = new Todo(description);
                list.add(curr);
                output += curr;
                output += "\nCurrently you have " + list.size() + (list.size() == 1 ? " thing" : " things")
                        + " yet to be desired.";
                return output;
            } else {
                throw new WrongFormatException();
            }
        } catch (WrongFormatException e) {
            return e.getMessage();
        }

    }
}
