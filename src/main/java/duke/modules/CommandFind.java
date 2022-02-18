package duke.modules;

import java.util.ArrayList;

/**
 * Represents a command to find a task in the task list.
 */
public class CommandFind extends Command {
    private String commandDescription;
    private ArrayList<Task> tasks;
    private String[] descriptionStrings;

    /**
     * Constructor for a CommandFind object.
     *
     * @param commandDescription The whole user input String.
     * @param tasks The task list associated with this instance of the chatbot.
     */
    public CommandFind(String commandDescription, ArrayList<Task> tasks) {
        this.commandDescription = commandDescription;
        this.tasks = tasks;
        descriptionStrings = commandDescription.split(" ");
    }

    /**
     * Handles the execution of a find command.
     *
     * @return A String message regarding the execution status of the find command.
     */
    @Override
    public String execute() {
        if (descriptionStrings.length < 2) {
            return "find description cannot be empty!\n";
        }

        String output = "";
        boolean hasMatches = false;
        String toFind = commandDescription.substring(5);
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.getName().contains(toFind)) {
                hasMatches = true;
                output += String.format("%d. " + currentTask + "\n", i + 1);
            }
        }
        if (hasMatches) {
            return output;
        } else {
            return "no items match your description.\n";
        }
    }
}
