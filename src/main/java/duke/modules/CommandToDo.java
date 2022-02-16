package duke.modules;

import java.util.ArrayList;

/**
 * Represents a command to add a todo task to the chatbot task list.
 */
public class CommandToDo extends Command {
    private String commandDescription;
    private ArrayList<Task> tasks;
    private String[] split;

    /**
     * Constructor for a CommandDeadline object.
     *
     * @param commandDescription The whole user input String.
     * @param tasks The task list associated with this instance of the chatbot.
     */
    public CommandToDo(String commandDescription, ArrayList<Task> tasks) {
        this.commandDescription = commandDescription;
        this.tasks = tasks;
        split = commandDescription.split(" ");
    }

    /**
     * Handles the execution of a todo command.
     *
     * @return A String message regarding the execution status of the todo command.
     */
    @Override
    public String execute() {
        String output = "";
        try {
            // Check if description is empty
            String check = split[1];
            // Take the substring of user input after todo
            String name = commandDescription.substring(5);
            ToDo t = new ToDo(name);
            tasks.add(t);
            output = String.format("task added:\n%s\n", t);
            output += String.format("you now have %d tasks\n", tasks.size());
        } catch (IndexOutOfBoundsException e) {
            output = ("the description of a todo cannot be empty!\n");
        }
        return output;
    }
}
