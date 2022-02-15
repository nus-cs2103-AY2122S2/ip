package duke;

import java.util.ArrayList;

/**
 * Represents a command to add an event task to the chatbot task list.
 */
public class CommandEvent extends Command {
    private String commandDescription;
    private ArrayList<Task> tasks;
    private String[] split;

    /**
     * Constructor for a CommandEvent object.
     *
     * @param commandDescription The whole user input String.
     * @param tasks The task list associated with this instance of the chatbot.
     */
    public CommandEvent(String commandDescription, ArrayList<Task> tasks) {
        this.commandDescription = commandDescription;
        this.tasks = tasks;
        split = commandDescription.split(" ");
    }

    /**
     * Handles the execution of a event command.
     *
     * @return A String message regarding the execution status of the event command.
     */
    @Override
    public String execute() {
        String output = "";
        try {
            // Check if description is empty
            String check = split[1];
            try {
                // Take the substring of user input after event
                String item = commandDescription.substring(6);
                // Divide the substring into task name and date
                String[] divide = item.split("/");
                String name = divide[0];
                String time = divide[1];
                Event e = new Event(name.trim(), time.substring(3));
                tasks.add(e);
                output = String.format("task added:\n%s\n", e);
                output += String.format("you now have %d tasks\n", tasks.size());
            } catch (IndexOutOfBoundsException e) {
                output = "event description must contain a date!\n";
            }
        } catch (IndexOutOfBoundsException e) {
            output = "event description cannot be empty!\n";
        }
        return output;
    }
}
