package command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import task.TaskList;

public class DeadlineCommand extends Command {
    private String message;
    private String time;

    /** Stores message and time for Deadline Task to be created */
    public DeadlineCommand(String message, String time) {
        this.message = message;
        this.time = time;
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
            taskList.add(new Deadline(this.message, false, LocalDate.parse(this.time)));
        } catch (DateTimeParseException e) {
            throw new DukeException("Have you entered the date in yyyy-mm-dd format?");
        }
        storage.writeToFile(taskList);
        return "Got it. I've added this task: \n"
                + taskList.get(taskList.size() - 1)
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
