package tasks;

/**
 * This class encapsulates a To-Do Task.
 *
 * This is the most generic task.
 *
 * @author Ong Han Yang
 */
public class ToDoTask extends Task{
    /**
     * Constructor for a To Do task.
     *
     * @param desc the description of the task.
     */
    public ToDoTask(String desc) {
        super(desc);
    }

    /**
     * Overridden toString method to display the TO-DO task as a String.
     *
     * @return String representation of a TO-DO task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Method to parse a String to obtain an To-Do Task.
     *
     * Expected format: todo <desc>
     *
     * @param input the String command used to build the Task from.
     * @return the ToDoTask obtained.
     * @throws InvalidInputException when no description is given.
     */
    public static ToDoTask parseInput(String input) throws InvalidInputException {
        String desc = input.substring(4);
        if (desc.length() <= 1) { //happens when input is like "todo " or "todo"
            throw new InvalidInputException("No description is provided for the ToDo Task!");
        } else {
            return new ToDoTask(desc.substring(1));
        }

    }
}
