package pikabot.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import pikabot.Parser;
import pikabot.Storage;
import pikabot.TaskList;
import pikabot.Ui;
import pikabot.exception.EventException;
import pikabot.task.Event;


/**
 * Represents a command to create an EventCommand.
 */
public class EventCommand extends Command {

    private String[] eventCommand;

    /**
     * Constructs an EventCommand.
     *
     * @param eventCommand String array containing input string from user.
     */
    public EventCommand(String[] eventCommand) {
        this.eventCommand = eventCommand;
    }

    /**
     * Executes EventCommand by creating an Event task.
     *
     * @param taskList TaskList to add the Event task to.
     * @param storage Storage to update data file in computer.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        try {
            Event currEvent = Parser.parseEvent(eventCommand);
            taskList.add(currEvent);
            storage.appendToFile(currEvent);
            return Ui.indicateAddedTask(currEvent, taskList);
        } catch (EventException | IOException e) {
            return Ui.printExceptionMessage(e);
        } catch (DateTimeParseException e) {
            return Ui.printExceptionCustomisedMessage("The description of an event must"
                    + "contain a date in the numerical format YYYY-MM-DD");
        }
    }
}
