package duke.modules;

import java.util.ArrayList;

/**
 * Represents a command to find a task in the task list.
 */
public class CommandFind extends Command {
    private String commandDescription;
    private ArrayList<Task> tasks;
    private String[] split;

    /**
     * Constructor for a CommandFind object.
     *
     * @param commandDescription The whole user input String.
     * @param tasks The task list associated with this instance of the chatbot.
     */
    public CommandFind(String commandDescription, ArrayList<Task> tasks) {
        this.commandDescription = commandDescription;
        this.tasks = tasks;
        split = commandDescription.split(" ");
    }

    /**
     * Handles the execution of a find command.
     *
     * @return A String message regarding the execution status of the find command.
     */
    @Override
    public String execute() {
        String output = "";
        boolean hasMatches = false;
        try {
            // Check if description is empty
            String check = split[1];
            String toFind = commandDescription.substring(5);
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                if (currentTask.getName().contains(toFind)) {
                    hasMatches = true;
                    output += String.format("%d. " + currentTask + "\n", i + 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            output = "find description cannot be empty!\n";
        }
        if (hasMatches) {
            return output;
        } else {
            return "no items match your description.\n";
        }


    }
}
