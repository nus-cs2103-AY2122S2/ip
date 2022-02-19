package duke.modules;

import java.util.ArrayList;

/**
 * Represents a command to unmark a task in the chatbots task list.
 */
public class CommandUnmark extends Command {
    private String commandDescription;
    private TaskList taskList;
    private ArrayList<Task> tasks;
    String[] descriptionStrings;

    /**
     * Constructor for a CommandUnmark object.
     *
     * @param commandDescription The whole user input String.
     * @param taskList The task list associated with this instance of the chatbot.
     */
    public CommandUnmark(String commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        this.tasks = taskList.getToDoList();
        descriptionStrings = commandDescription.split(" ");
    }

    /**
     * Handles the execution of an unmark command.
     *
     * @return A String message regarding the execution status of the unmark command.
     */
    @Override
    public String execute() {
        String output = "";
        try {
            int itemToUnmark = Integer.parseInt(descriptionStrings[1]);
            if (itemToUnmark - 1 >= tasks.size()) {
                return "the index you have entered does not exist!\n";
            }
            Task toUnmark = tasks.get(itemToUnmark - 1);
            toUnmark.unmark();
            output = String.format("Boo! more work to do: %s\n", toUnmark.getName());
            Storage.save(taskList);

        } catch (IndexOutOfBoundsException e) {
            output = ("unmark description cannot be empty!\n");
        } catch (NumberFormatException e) {
            output = "unmark description must be an integer!\n";
        }
        return output;
    }
}
