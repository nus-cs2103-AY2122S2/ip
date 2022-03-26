package duke.commands;

import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;

import duke.DukeHistory;
import duke.DukeUi;
import duke.Event;

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
            return this.getUi().printMissingLabel(this.getUserInput()[0]);
        } catch (MissingResourceException ex2) {
            return this.getUi().printMissingDateTimeArgumentError(this.getUserInput()[0]);
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
        String date = generateDate(this.getUserInput()[this.timeStart + 1]);
        String time = generateTime(this.getUserInput()[this.timeStart + 2]);
        Event tempEvent = this.getHistory().addEvent(description, date, time);
        return "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempEvent.getEvent()
                + "Currently you have " + this.getHistory().getSize() + " tasks in our records.\n"
                + "_______________________________________________________\n";
    }

    /**
     * Method that generates a description for the Event task and looks for the label in the user input.
     */
    public void generateDescriptionAndGetLabel() {
        StringBuilder tempDescription = new StringBuilder();
        for (int i = 1; i < this.getUserInput().length; i++) {
            if (this.getUserInput()[i].startsWith("/at")) {
                timeStart = i;
                break;
            } else {
                tempDescription.append(this.getUserInput()[i]);
            }
            tempDescription.append(" ");
        }
        this.description = tempDescription.toString();
    }

    /**
     * Method that checks if a label exists.
     */
    public void checkForLabel() {
        if (this.timeStart == -1) {
            throw new MissingFormatArgumentException("/at");
        }
    }

    /**
     * Method that checks if a date and/or time field exists.
     */
    public void checkForDateAndTime() {
        if (this.getUserInput().length - this.timeStart < 3) {
            throw new MissingResourceException("No date and/or time", "timedate", "key");
        }
    }

    public String generateDate(String date) {
        return convertToDukeDate(date);
    }

    /**
     * Method that splits the start and end time for an Event task before converting them to Duke Time format.
     * @param time String representation of the time period in which the Event tasks takes place.
     * @return Converted time format in String representation.
     */
    public String generateTime(String time) {
        StringBuilder tempTime = new StringBuilder();
        String[] tokens = time.split("-");
        tempTime.append(convertToDukeTime(tokens[0])).append("-").append(convertToDukeTime(tokens[1]));
        return tempTime.toString();
    }
}
