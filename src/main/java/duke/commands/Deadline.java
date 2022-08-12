package duke.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.tasks.Deadlines;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * A Deadline class that handles the deadline command.
 */
public class Deadline extends Commands {

    private final String LINE_BREAK_DEADLINE = super.LINE_BREAK + "\n";
    private final String ADD_DEADLINE_RESPONSE = "Got it. I added this deadline already bro: \n" + " " + "%s" + "\n";
    private final String TASK_COUNT_MESSAGE = "Now you have " + "%d" + " tasks in the list. \n"; 

    public Deadline(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Handles the logic when we receive a "deadline" command.
     * @param tasks       reference to the task ArrayList in TaskList
     * @param deadline    to complete task by
     * @param description of task
     * @param counter     integer value, holding the number of tasks we have
     * @return
     */
    public String handleDeadline(TaskList tasks, String deadline, String description, int counter) {
        String res = "";
        Task newTask;

        res += LINE_BREAK_DEADLINE;
        boolean isDate = super.isDate(deadline);

        if (isDate) {
            newTask = new Deadlines(description, LocalDate.parse(deadline));
        } else {
            newTask = new Deadlines(description, deadline);
        }  

        tasks.addToTasks(newTask);
        res += String.format(ADD_DEADLINE_RESPONSE, newTask.toString());
        res += String.format(TASK_COUNT_MESSAGE, counter);
        
        return res;
    }
}
