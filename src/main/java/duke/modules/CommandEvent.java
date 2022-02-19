package duke.modules;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a command to add an event task to the chatbot task list.
 */
public class CommandEvent extends Command {
    private String commandDescription;
    private TaskList taskList;
    private ArrayList<Task> tasks;
    private String[] descriptionStrings;

    /**
     * Constructor for a CommandEvent object.
     *
     * @param commandDescription The whole user input String.
     * @param taskList The task list associated with this instance of the chatbot.
     */
    public CommandEvent(String commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        this.tasks = taskList.getToDoList();
        descriptionStrings = commandDescription.split(" ");
    }

    /**
     * Handles the execution of a event command.
     *
     * @return A String message regarding the execution status of the event command.
     */
    @Override
    public String execute() {
        String output = "";
        if (descriptionStrings.length < 2) {
            return "event description cannot be empty!\n";
        }
        // Take the substring of user input after event
        String item = commandDescription.substring(6);
        // Divide the substring into task name and date
        String[] nameAndDate = item.split("/");
        String name = nameAndDate[0];
        if (nameAndDate.length < 2) {
            return "event description must contain a date!\n";
        }
        String time = nameAndDate[1];
        try{
            Event e = new Event(name.trim(), time.substring(3));
            if (taskList.canAddTask(e)) {
                tasks.add(e);
                output = String.format("task added:\n%s\n", e);
                output += String.format("you now have %d tasks\n", tasks.size());
                Storage.save(taskList);
            } else {
                output = "duplicate task! check that all your tasks have unique names\n";
            }

        } catch (DateTimeParseException e) {
            output = "date must be in the format yyyy-mm-dd\n";
        }
        return output;
    }
}
