package duke.Commands;

import duke.DukeHistory;
import duke.DukeUi;
import duke.Event;

import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;

public class EventCommand extends Commands {

    private String description;
    private int timeStart = -1;

    public EventCommand(DukeHistory history, String[] userInput, DukeUi ui) {
        super(history, userInput, ui);
    }

    @Override
    public String validateAndExecute() {
        try {
            validate();
            return execute();
        } catch (MissingFormatArgumentException ex1) {
            return ui.printMissingLabel(userInput[0]);
        } catch (MissingResourceException ex2) {
            return ui.printMissingDateTimeArgumentError(userInput[0]);
        }
    }

    @Override
    public void validate() {
        generateDescriptionAndGetLabel();
        checkForLabel();
        checkForDateAndTime();
    }

    /**
     * A method that, when called, attempts to initialize an Event task using the provided String[] tokens
     * to build a description, date and time.
     *
     * It then gets the inputted instance of DukeHistory to add an Event task entry into it's records
     * using the generated description, date and time.
     *
     * @return Event_task response.
     */
    @Override
    public String execute() {
        String date = generateDate(userInput[this.timeStart + 1]);
        String time = generateTime(userInput[this.timeStart + 2]);
        Event tempEvent = history.addEvent(description, date, time);
        return "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempEvent.getEvent()
                + "Currently you have " + history.getSize() + " tasks in our records.\n"
                + "_______________________________________________________\n";
    }

    public void generateDescriptionAndGetLabel() {
        StringBuilder tempDescription = new StringBuilder();
        for (int i = 1; i < userInput.length; i++) {
            if (userInput[i].startsWith("/at")) {
                timeStart = i;
                break;
            } else {
                tempDescription.append(userInput[i]);
            }
            tempDescription.append(" ");
        }
        this.description = tempDescription.toString();
    }

    public void checkForLabel() {
        if (this.timeStart == -1) {
            throw new MissingFormatArgumentException("/at");
        }
    }

    public void checkForDateAndTime() {
        if (userInput.length - this.timeStart < 3 ) {
            throw new MissingResourceException("No date and/or time", "timedate", "key");
        }
    }

    public String generateDate(String date) {
        return convertToDukeDate(date);
    }

    public String generateTime(String time) {
        StringBuilder tempTime = new StringBuilder();
        String[] tokens = time.split("-");
        tempTime.append(convertToDukeTime(tokens[0])).append("-").append(convertToDukeTime(tokens[1]));
        return tempTime.toString();
    }
}
