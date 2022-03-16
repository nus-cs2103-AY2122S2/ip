package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.IncorrectFormatException;
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
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private TaskList tasks;
    private Task added;

    /**
     * Constructor for Add Command class
     * Creates correct Task based on user input and adds to task list
     *
     * @param taskType type of task to be added
     * @param details details of the task to be added
     */
    public AddCommand(String taskType, String details) {
        this.type = taskType;
        this.input = details;
    }

    /**
     * returns the modified task list after command execution
     *
     * @return TaskList
     */
    @Override
    public TaskList getList() {
        return tasks;
    }

    /**
     * returns true boolean if command execution ends program
     *
     * @return true if it ends main program
     */
    @Override
    public boolean endsProgram() {
        return false;
    }

    /**
     * executes the add command
     * Adds respective task type depending on input
     *
     * @param tasks tasks list to be modified
     * @param ui to help with printing of messages
     * @param storage To deal with saving of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        this.tasks = tasks;
            switch (type) {
            case "todo":
                added = new ToDo(input);
                break;
            case "event":
                added = addEvent(input);
                break;
            case "deadline":
                added = addDeadline(input);
                break;
            default:
                break;
            }
        this.tasks.add(added);
        storage.saveFile(tasks);

        return ui.addMessage(added, tasks.size());
    }

    /**
     * Method to create and return an Event
     * throws DukeException if format of parsed detail is wrong
     *
     * @param input String containing the details to create an Event
     * @return Event with the correct details and date
     * @throws DukeException if format of parsed details is wrong
     */
    private Event addEvent(String input) throws DukeException{
        String[] processedInput;
        String details;
        String time;
        LocalDateTime date;
        processedInput = input.split("/at", 2);
        if(lengthNotEqual(processedInput, 2)) {
            throw new IncorrectFormatException();
        }
        details = processedInput[0];
        if(details.isEmpty()){
            throw new EmptyDescriptionException(type);
        }
        time = processedInput[1].trim();
        try{
            date = LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException(time);
        }
        return  new Event(details, date);
    }

    /**
     * Method to create and return a Deadline
     * throws DukeException if format of parsed detail is wrong
     *
     * @param input String containing the details to create an Event
     * @return deadline with the correct details and date
     * @throws DukeException if format of parsed details is wrong
     */
    private Deadline addDeadline (String input) throws DukeException {
        String[] processedInput;
        String details;
        String time;
        LocalDateTime deadline;
        processedInput = input.split("/by", 2);
        if(lengthNotEqual(processedInput, 2)) {
            throw new IncorrectFormatException();
        }
        details = processedInput[0];
        if(details.isEmpty()){
            throw new EmptyDescriptionException(type);
        }
        time = processedInput[1].trim();
        try{
            deadline = LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException(time);
        }
        return new Deadline(details, deadline);
    }

    /**
     * Method to check if the parsed string array is not equal to parsed int
     *
     * @param checked String array to be checked
     * @param compared int to be checked against
     * @return boolean value if string array length is not equal to int provided
     */
    private boolean lengthNotEqual(String[] checked, int compared){
        return checked.length != compared;
    }
}
