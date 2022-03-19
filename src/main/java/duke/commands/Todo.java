package duke.commands;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDos;

public class Todo extends Commands {

    public Todo(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Handle todo command. 
     * @param command
     * @param tasks
     * @param input
     * @return res
     */
    public String handleTodo(String[] command, TaskList tasks, String input) {
        String res = "";
        if (command.length == 1) {
            DukeException e = new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            res += "---------------" + "\n";
            res += e.getMessage();
            return res;
        } else {
            Task t = new ToDos(input);
            tasks.addToTasks(t);
            res += super.LINE_BREAK + "\n";
            res += " ok added alr bro: " + input;
            return res;
        }
    }

}
