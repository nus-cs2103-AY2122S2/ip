package duke.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidArgumentException;
import duke.parser.Action;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Messages;
import duke.ui.Ui;

public class AddCommand extends Command {

    private Action action;
    private String input;

    /**
     * Adds a Task into TaskList.
     *
     * @param action
     * @param input
     */
    public AddCommand(Action action, String input) {
        this.action = action;
        this.input = input;
    }

    /**
     * Adds a task to the Tasklist.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        switch (action) {
        case TODO:
            return addTodo(tasks, ui, storage);
        case DEADLINE:
            return addDeadline(tasks, ui, storage);
        case EVENT:
            return addEvent(tasks, ui, storage);
        default:
            assert false : "add command should not reach here";
        }
        assert false : "add command should not reach here";
        return null;
    }

    /**
     * Adds a Todo task.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     * @return resultant string to output to user.
     */
    public String addTodo(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        try {
            String description = Parser.parseDescription(this.input);

            tasks.add(new Todo(description));
            storage.save(tasks);

            output = ui.printTaskAdded(tasks);
        } catch (InvalidArgumentException e) {
            output = ui.showError(e.getMessage());
        } catch (IOException e) {
            output = ui.showError(Messages.SAVE_ERROR);
        }
        return output;
    }

    /**
     * Adds a Deadline Task.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     * @return resultant string to output to user.
     */
    public String addDeadline(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        try {
            String[] deadlineFields = Parser.parseDeadline(input);
          
            tasks.add(new Deadline(deadlineFields[0], deadlineFields[1]));
            storage.save(tasks);
          
            output = ui.printTaskAdded(tasks);
        } catch (InvalidArgumentException e) {
            output = ui.showError(e.getMessage());
        } catch (DateTimeParseException e) {
            output = ui.showError(Messages.UNKNOWN_DATETIME);
        } catch (IOException e) {
            output = ui.showError(Messages.SAVE_ERROR);
        }
        return output;
    }

    /**
     * Adds an Event task.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     * @return resultant string to output to user.
     */
    public String addEvent(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        //event project meeting /at Mon 2-4pm
        try {
            String[] eventFields = Parser.parseEvent(input);
          
            tasks.add(new Event(eventFields[0], eventFields[1]));
            storage.save(tasks);
          
            output = ui.printTaskAdded(tasks);
        } catch (InvalidArgumentException e) {
            output = ui.showError(e.getMessage());
        } catch (IOException e) {
            output = ui.showError(Messages.SAVE_ERROR);
        }
        return output;
    }

}
