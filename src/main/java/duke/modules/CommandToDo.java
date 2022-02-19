package duke.modules;

import java.util.ArrayList;

/**
 * Represents a command to add a todo task to the chatbot task list.
 */
public class CommandToDo extends Command {
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
    public CommandToDo(String commandDescription, TaskList taskList) {
        this.commandDescription = commandDescription;
        this.taskList = taskList;
        this.tasks = taskList.getToDoList();
        descriptionStrings = commandDescription.split(" ");
    }

    /**
     * Handles the execution of a todo command.
     *
     * @return A String message regarding the execution status of the todo command.
     */
    @Override
    public String execute() {
        String output = "";
        if (descriptionStrings.length < 2) {
            return "the description of a todo cannot be empty!\n";
        }

        // Take the substring of user input after todo
        String name = commandDescription.substring(5);
        ToDo t = new ToDo(name);
        if (taskList.canAddTask(t)) {
            tasks.add(t);
            output = String.format("task added:\n%s\n", t);
            output += String.format("you now have %d tasks\n", tasks.size());
            Storage.save(taskList);
        } else {
            output = "duplicate task! check that all your tasks have unique names\n";
        }


        return output;
    }
}
