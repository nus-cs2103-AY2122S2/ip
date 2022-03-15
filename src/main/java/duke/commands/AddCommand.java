package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.WrongDateFormatException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class which handles the adding of commands to task list
 */
public class AddCommand extends Command {
    private final String input;
    private final String type;
    private TaskList tasks;
    private Task added;

    /**
     * Constructor for Add Command class
     * Creates correct Task based on user input and adds to task list
     * @param commandType
     * @param details
     */
    public AddCommand(String commandType, String details) {
        this.type = commandType;
        this.input = details;
    }

    /**
     * returns the modified task list after command execution
     * @return TaskList
     */
    @Override
    public TaskList getList() {
        return tasks;
    }

    /**
     * returns true boolean if command execution ends program
     * @return true if it ends main program
     */
    @Override
    public boolean endsProgram() {
        return false;
    }

    /**
     * executes the add command
     * Adds respective task type depending on input
     * @param tasks tasks list to be modified
     * @param ui to help with printing of messages
     * @param storage To deal with saving of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        this.tasks = tasks;
        String[] processedInput;
        String time = null;
        String details;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            switch (type) {
            case "todo":
                added = new ToDo(input);
                break;
            case "event":
                processedInput = input.split("/at ", 2);
                details = processedInput[0];
                time = processedInput[1];
                assert time != null;
                LocalDateTime date = LocalDateTime.parse(time, formatter);
                added = new Event(details, date);
                break;
            case "deadline":
                processedInput = input.split("/by ", 2);
                details = processedInput[0];
                time = processedInput[1];
                assert time != null;
                LocalDateTime deadline = LocalDateTime.parse(time, formatter);
                added = new Deadline(details, deadline);
                break;
            default:
                break;
            }
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException(time);
        }
        this.tasks.add(added);
        storage.saveFile(tasks);

        return ui.addMessage(added, tasks.size());
    }
}
