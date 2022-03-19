package duke.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.tasks.Deadlines;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class Deadline extends Commands {

    public Deadline(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Handles the logic when we receive a "deadline" command.
     * 
     * @param tasks       reference to the task ArrayList in TaskList
     * @param deadline    to complete task by
     * @param description of task
     * @param counter     integer value, holding the number of tasks we have
     * @return
     */
    public String handleDeadline(TaskList tasks, String deadline, String description, int counter) {
        String res = "";

        boolean isDate = super.isDate(deadline);
        if (isDate) {
            Task newTask = new Deadlines(description, LocalDate.parse(deadline));
            tasks.addToTasks(newTask);
            // adding to the array
            res += super.LINE_BREAK + "\n";
            res += "Got it. I added this deadline already bro: \n" + " " + newTask.toString() + "\n";
            res += "Now you have " + counter + " tasks in the list. \n";

            return res;
        } else {
            Task newTask = new Deadlines(description, deadline);
            tasks.addToTasks(newTask);

            res += super.LINE_BREAK + "\n";
            res += "Got it. I added this deadline already bro: \n" + " " + newTask.toString() + "\n";
            res += "Now you have " + counter + " tasks in the list. \n";

            return res;
        }
    }
}
