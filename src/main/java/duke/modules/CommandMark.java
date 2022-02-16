package duke.modules;

import java.util.ArrayList;

/**
 * Represents a command to mark a task as done in the chatbots task list.
 */
public class CommandMark extends Command {

    private String commandDescription;
    private ArrayList<Task> tasks;
    String[] split;

    /**
     * Constructor for a CommandMark object.
     *
     * @param commandDescription The whole user input String.
     * @param tasks The task list associated with this instance of the chatbot.
     */
    public CommandMark(String commandDescription, ArrayList<Task> tasks) {
        this.commandDescription = commandDescription;
        this.tasks = tasks;
        split = commandDescription.split(" ");
    }

    /**
     * Handles the execution of a mark command.
     *
     * @return A String message regarding the execution status of the mark command.
     */
    @Override

    public String execute() {
        String output = "";
        try {
            int item = Integer.parseInt(split[1]);
            try {
                Task t = tasks.get(item - 1);
                t.mark();
                output = String.format("great job! I've marked this task as done: %s\n", t.getName());
            } catch (IndexOutOfBoundsException e) {
                output = "the index you have entered does not exist!\n";
            }
        } catch (IndexOutOfBoundsException e) {
            output = "mark description cannot be empty!\n";
        } catch (NumberFormatException e) {
            output = "mark description must be an integer!\n";
        }
        return output;
    }
}
