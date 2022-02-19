package duke.modules;

import java.util.ArrayList;

/**
 * Represents a command to mark a task as done in the chatbots task list.
 */
public class CommandMark extends Command {

    private String commandDescription;
    private TaskList taskList;
    private ArrayList<Task> tasks;
    String[] descriptionStrings;

    /**
     * Constructor for a CommandMark object.
     *
     * @param commandDescription The whole user input String.
     * @param taskList The task list associated with this instance of the chatbot.
     */
    public CommandMark(String commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        this.tasks = taskList.getToDoList();
        descriptionStrings = commandDescription.split(" ");
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
            int itemToMark = Integer.parseInt(descriptionStrings[1]);
            if (itemToMark - 1 >= tasks.size()) {
                return "the index you have entered does not exist!\n";
            }
            Task toMark = tasks.get(itemToMark - 1);
            toMark.mark();
            output = String.format("great job! I've marked this task as done: %s\n", toMark.getName());
            Storage.save(taskList);

        } catch (IndexOutOfBoundsException e) {
            output = "mark description cannot be empty!\n";
        } catch (NumberFormatException e) {
            output = "mark description must be an integer!\n";
        }
        return output;
    }
}
