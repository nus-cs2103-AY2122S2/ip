package duke;

import java.util.ArrayList;

public class CommandDelete extends Command {
    private String commandDescription;
    private ArrayList<Task> tasks;
    private String[] split;

    public CommandDelete(String commandDescription, ArrayList<Task> tasks) {
        this.commandDescription = commandDescription;
        this.tasks = tasks;
        split = commandDescription.split(" ");
    }

    @Override
    public String execute() {
        String output = "";
        try {
            // Checks if description is empty
            int toDelete = Integer.parseInt(split[1]);
            try {
                Task t = tasks.get(toDelete - 1);
                tasks.remove(toDelete - 1);
                output = String.format("task removed:\n%s\n", t.toString());
                output += String.format("you now have %d tasks\n", tasks.size());
            } catch (IndexOutOfBoundsException e) {
                output = "the index you have entered does not exist!\n";
            }
        } catch (IndexOutOfBoundsException e) {
            output = "delete description cannot be empty!\n";
        } catch (NumberFormatException e) {
            output = "delete description must be an integer!\n";
        }
        return output;
    }

}
