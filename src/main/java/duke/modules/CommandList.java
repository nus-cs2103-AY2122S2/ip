package duke.modules;

import java.util.ArrayList;

/**
 * Represents the command to list all tasks in the chatbots task list.
 */
public class CommandList extends Command {
    private ArrayList<Task> tasks;

    /**
     * Constructor for a CommandList object.
     *
     * @param tasks The task list associated with this instance of the chatbot.
     */
    public CommandList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Handles the execution of a list command.
     *
     * @return A String message listing all the tasks in the task list.
     */
    @Override
    public String execute() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output += String.format("%d. " + task + "\n", i + 1);
        }
        return output;
    }
}
