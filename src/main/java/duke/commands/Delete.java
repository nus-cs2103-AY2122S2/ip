package duke.commands;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Delete class that handles the delete command. 
 */
public class Delete extends Commands {

    public Delete(ArrayList<Task> tasks) {
        super(tasks);
    }

    public String handleDelete(TaskList tasks, String[] command) {
        String res = "";

        // index to be deleted
        int number = Integer.parseInt(command[1]) - 1;
        // task being deleted
        Task beingDeleted = tasks.getTasks().get(number);
        // deleting operation
        tasks.deleteFromTasks(number);

        res += "Noted. I've removed this task: " + "\n";
        res += " " + beingDeleted + "\n";
        res += "Now you have " + tasks.getTasks().size() + " in the list.";

        return res;
    }
}
