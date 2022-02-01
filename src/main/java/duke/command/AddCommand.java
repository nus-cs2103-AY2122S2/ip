package duke.command;

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

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    private Action action;
    private String input;

    public AddCommand(Action action, String input) {
        this.action = action;
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
            switch (action) {
            case TODO:
                try {
                    String description = Parser.parseTodo(this.input);
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
                    String[] deadlineArr = input.split("/by", 2);
                    String[] deadlineSplit = deadlineArr[0].split("\\s", 2);
                    if (deadlineSplit.length <= 1) {    // no description
                        throw new InvalidArgumentException(Messages.UNKNOWN_DEADLINE);
                    }
                    if (deadlineArr.length <= 1) { // don't have /by keyword
                        throw new InvalidArgumentException(Messages.UNKNOWN_DATETIME);
                    }
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
                    String[] eventArr = input.split("/at", 2);
                    String[] eventSplit = eventArr[0].split("\\s", 2);
                    if (eventSplit.length <= 1) {
                        throw new InvalidArgumentException(Messages.UNKNOWN_EVENT);
                    }
                    if (eventArr.length <= 1) {
                        throw new InvalidArgumentException(Messages.UNKNOWN_LOCATION);
                    }
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
            }
    }
    
}
