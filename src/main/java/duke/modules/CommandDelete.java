package duke.modules;

import java.util.ArrayList;

/**
 * Represents a command to delete a task from the chatbots task list.
 */
public class CommandDelete extends Command {
    private String commandDescription;
    private ArrayList<Task> tasks;
    private String[] split;

    /**
     * Constructor for a CommandDelete object.
     *
     * @param commandDescription The whole user input String.
     * @param tasks The task list associated with this instance of the chatbot.
     */
    public CommandDelete(String commandDescription, ArrayList<Task> tasks) {
        this.commandDescription = commandDescription;
        this.tasks = tasks;
        split = commandDescription.split(" ");
    }

    /**
     * Handles the execution of a delete command.
     *
     * @return A String message regarding the execution status of the delete command.
     */
    @Override
    public String execute() {
        String output = "";
        try {
            // Checks if description is empty
            int toDelete = Integer.parseInt(split[1]);
            try {
                Task t = tasks.get(toDelete - 1);
                tasks.remove(toDelete - 1);
                output = String.format("task removed:\n%s\n", t.toString());
                output += String.format("you now have %d tasks\n", tasks.size());
            } catch (IndexOutOfBoundsException e) {
                output = "the index you have entered does not exist!\n";
            }
        } catch (IndexOutOfBoundsException e) {
            output = "delete description cannot be empty!\n";
        } catch (NumberFormatException e) {
            output = "delete description must be an integer!\n";
        }
        return output;
    }

}
