package duke;

import java.util.ArrayList;

public class CommandUnmark extends Command {
    private String commandDescription;
    private ArrayList<Task> tasks;
    String[] split;

    public CommandUnmark(String commandDescription, ArrayList<Task> tasks) {
        this.commandDescription = commandDescription;
        this.tasks = tasks;
        split = commandDescription.split(" ");
    }

    @Override
    public String execute() {
        String output = "";
        try {
            int item = Integer.parseInt(split[1]);
            try {
                Task t = tasks.get(item - 1);
                t.unmark();
                output = String.format("Boo! more work to do: %s\n", t.getName());
            } catch (IndexOutOfBoundsException e) {
                output = "the index you have entered does not exist!\n";
            }
        } catch (IndexOutOfBoundsException e) {
            output = ("unmark description cannot be empty!\n");
        } catch (NumberFormatException e) {
            output = "unmark description must be an integer!\n";
        }
        return output;
    }
}
