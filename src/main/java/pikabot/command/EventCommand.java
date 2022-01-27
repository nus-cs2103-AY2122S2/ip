package pikabot.command;

import pikabot.TaskList;
import pikabot.Storage;
import pikabot.Parser;
import pikabot.Ui;

import pikabot.task.Event;
import pikabot.exception.EventException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    String[] eventCommand;

    public EventCommand(String[] eventCommand) {
        this.eventCommand = eventCommand;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            Event currEvent = Parser.parseEvent(eventCommand);
            taskList.add(currEvent);
            Ui.indicateAddedTask(currEvent, taskList);
            storage.appendToFile(currEvent);

        } catch (EventException | IOException e) {
            Ui.printExceptionMessage(e);

        } catch (DateTimeParseException e) {
            Ui.printExceptionCustomisedMessage("The description of an event must" +
                "contain a date in the numerical format YYYY-MM-DD");
        }
    }
}
