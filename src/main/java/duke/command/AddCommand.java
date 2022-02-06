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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (action) {
        case TODO:
            try {
                String description = Parser.parseDescription(this.input);
                tasks.add(new Todo(description));
                ui.printTaskAdded(tasks);
                storage.save(tasks);
            } catch (InvalidArgumentException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(Messages.SAVE_ERROR);
            } finally {
                break;
            }
        case DEADLINE:
            //deadline do hw /by no idea :-p
            try {
                String[] deadlineFields = Parser.parseDeadline(input);
                tasks.add(new Deadline(deadlineFields[0], deadlineFields[1]));
                ui.printTaskAdded(tasks);
                storage.save(tasks);
            } catch (InvalidArgumentException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError(Messages.UNKNOWN_DATETIME);
            } catch (IOException e) {
                ui.showError(Messages.SAVE_ERROR);
            } finally {
                break;
            }
        case EVENT:
            //event project meeting /at Mon 2-4pm
            try {
                String[] eventFields = Parser.parseEvent(input);
                tasks.add(new Event(eventFields[0], eventFields[1]));
                ui.printTaskAdded(tasks);
                storage.save(tasks);
            } catch (InvalidArgumentException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(Messages.SAVE_ERROR);
            } finally {
                break;
            }
        default:
            //do nothing
        }
    }
}
