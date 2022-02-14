package duke.command;

import java.time.LocalDate;

import duke.functionality.TaskList;

/**
 * Represents the schedule command. A <code>ScheduleCommand</code> object corresponds to finding similar tasks
 * in the taskList of TaskList class.
 */
public class ScheduleCommand extends Command {
    private LocalDate date;

    /**
     * Constructor of FindCommand.
     * @param date date used to find similar tasks in taskList of TaskList class.
     */
    public ScheduleCommand(LocalDate date) {
        super(null);
        this.date = date;
    }

    /**
     * Returns a string which contains all the task after the execution of findSameSchedule in the TaskList class.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     * @return crafted message after calling findSameSchedule in the TaskList class.
     */
    @Override
    public String execute(TaskList tasks) {
        TaskList newTaskList = tasks.findSameSchedule(this.date);
        String message = "Here is your schedule for: " + this.date + "\n";
        int counter = 1;
        for (int i = 0; i < newTaskList.getListSize(); i++) {
            String output = counter + "." + newTaskList.getTask(i);
            counter++;
            message += output + "\n";
        }
        if (counter == 1) {
            message = "You have no schedule on:" + this.date + "\n";
        }
        return message;
    }

    /**
     * Returns false as the Command is not an ExitCommand.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
