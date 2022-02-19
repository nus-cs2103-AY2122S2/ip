package duke.modules;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a command to add a deadline task to the chatbot task list.
 */
public class CommandDeadline extends Command {
    private String commandDescription;
    private TaskList taskList;
    private ArrayList<Task> tasks;
    private String[] descriptionStrings;

    /**
     * Constructor for a CommandDeadline object.
     *
     * @param commandDescription The whole user input String.
     * @param taskList The task list associated with this instance of the chatbot.
     */
    public CommandDeadline(String commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        this.tasks = taskList.getToDoList();
        descriptionStrings = commandDescription.split(" ");
    }

    /**
     * Handles the execution of a deadline command.
     *
     * @return A String message regarding the execution status of the deadline command.
     */
    @Override
    public String execute() {
        if (descriptionStrings.length < 2) {
            return "deadline description cannot be empty!\n";
        }

        String output = "";
        String item = commandDescription.substring(9);
        // Divide the substring into task name and deadline
        String[] nameAndDate = item.split("/");
        String name = nameAndDate[0];

        if (nameAndDate.length < 2) {
            return "deadline description must contain a date!\n";
        }

        String dueDate = nameAndDate[1];
        try {
            Deadline d = new Deadline(name.trim(), dueDate.substring(3));
            if (taskList.canAddTask(d)) {
                tasks.add(d);
                output = String.format("task added:\n%s\n", d);
                output += String.format("you now have %d tasks\n", tasks.size());
                Storage.save(taskList);
            } else {
                output = "duplicate task! check that all your tasks have unique names\n";
            }

        } catch (DateTimeParseException e ) {
            output = "date must be in the format yyyy-mm-dd\n";
        }
        return output;
    }

}
