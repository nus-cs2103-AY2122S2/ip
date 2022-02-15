package duke;

import java.util.ArrayList;

public class CommandList extends Command {
    private ArrayList<Task> tasks;

    public CommandList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output += String.format("%d. " + task + "\n", i + 1);
        }
        return output;
    }
}
