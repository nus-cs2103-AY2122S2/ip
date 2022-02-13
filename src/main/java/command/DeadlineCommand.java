package command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.TaskList;

public class DeadlineCommand extends Command {
    private final String message;
    private final String time;
    private final Integer priorityLevel;

    /** Stores message and time for Deadline Task to be created */
    public DeadlineCommand(String message, String time, Integer priorityLevel) {
        this.message = message;
        this.time = time;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Add Deadline Task to TaskList.
     * Also overwrite Storage.
     *
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     * @throws DukeException If time is in wrong format or problems with writing to Storage.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException { //throw exception if necessary
        try {
            taskList.add(new Deadline(this.message, false, this.priorityLevel, LocalDate.parse(this.time)));
        } catch (DateTimeParseException e) {
            throw new DukeException("Have you entered the date in yyyy-mm-dd format?");
        }
        storage.writeToFile(taskList);
        return "TAKE THAT!\nI've added this task: \n"
                + taskList.get(taskList.size() - 1)
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
