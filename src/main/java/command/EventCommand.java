package command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.DukeException;
import storage.Storage;
import task.Event;
import task.TaskList;

public class EventCommand extends Command {
    private final String message;
    private final String time;
    private final Integer priorityLevel;

    /** Stores message and time for Event Task to be created. */
    public EventCommand(String message, String time, Integer priorityLevel) {
        this.message = message;
        this.time = time;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Add Event Task to TaskList.
     * Also overwrite Storage.
     *
     * @param storage Storage for rewriting TaskList.
     * @param taskList TaskList that stores Tasks.
     * @throws DukeException If time is in wrong format or problems with writing to Storage.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        try {
            taskList.add(new Event(this.message, false, this.priorityLevel, LocalDate.parse(this.time)));
        } catch (DateTimeParseException e) {
            throw new DukeException("Have you entered the date in yyyy-mm-dd format?");
        }
        storage.writeToFile(taskList);
        return "TAKE THAT!\nI've added this task: \n"
                + taskList.get(taskList.size() - 1)
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
