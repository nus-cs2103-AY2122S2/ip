package duke.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class Event extends Commands {

    public Event(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Handles the event command.
     * 
     * @param deadline
     * @param counter
     * @param description
     * @return res
     */
    public String handleEvent(TaskList tasks, String deadline, int counter, String description) {
        String res = "";
        boolean isDate = super.isDate(deadline);
        if (isDate) {
            Task newTask = new Events(description, LocalDate.parse(deadline));
            tasks.addToTasks(newTask);
            res += super.LINE_BREAK + "\n";
            res += "Got it. I added this event already bro: \n" + " " +
                    newTask.toString() + "\n";
            res += "Now you have " + counter + " tasks in the list. \n";

            return res;
        } else {
            Task newTask = new Events(description, deadline);
            tasks.addToTasks(newTask);
            // adding to the array
            ++counter;

            res += super.LINE_BREAK + "\n";
            res += "Got it. I added this event already bro: \n" + " " +
                    newTask.toString() + "\n";
            res += "Now you have " + counter + " tasks in the list. \n";

            return res;
        }
    }
}
