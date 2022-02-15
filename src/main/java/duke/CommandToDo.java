package duke;

import java.util.ArrayList;

public class CommandToDo extends Command {
    private String commandDescription;
    private ArrayList<Task> tasks;
    private String[] split;

    public CommandToDo(String commandDescription, ArrayList<Task> tasks) {
        this.commandDescription = commandDescription;
        this.tasks = tasks;
        split = commandDescription.split(" ");
    }

    @Override
    public String execute() {
        String output = "";
        try {
            // Check if description is empty
            String check = split[1];
            // Take the substring of user input after todo
            String name = commandDescription.substring(5);
            ToDo t = new ToDo(name);
            tasks.add(t);
            output = String.format("task added:\n%s\n", t);
            output += String.format("you now have %d tasks\n", tasks.size());
        } catch (IndexOutOfBoundsException e) {
            output = ("the description of a todo cannot be empty!\n");
        }
        return output;
    }
}
