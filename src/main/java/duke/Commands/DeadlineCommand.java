package duke.commands;

import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;

import duke.Deadlines;
import duke.DukeHistory;
import duke.DukeUi;

public class DeadlineCommand extends Commands {

    private String description;
    private int timeStart = -1;

    public DeadlineCommand(DukeHistory history, String[] userInput, DukeUi ui) {
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

    @Override
    public String execute() {
        String date = generateDate(this.getUserInput()[this.timeStart + 1]);
        String time = generateTime(this.getUserInput()[this.timeStart + 2]);
        Deadlines tempDeadline = this.getHistory().addDeadline(description, date, time);
        return "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempDeadline.getDeadline()
                + "Currently you have " + this.getHistory().getSize() + " tasks in our records.\n"
                + "_______________________________________________________\n";
    }

    /**
     * Method that generates a description for the Deadline task and looks for the label in the user input.
     */
    public void generateDescriptionAndGetLabel() {
        StringBuilder tempDescription = new StringBuilder();
        for (int i = 1; i < this.getUserInput().length; i++) {
            if (this.getUserInput()[i].equals("/by")) {
                this.timeStart = i;
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
     * @throws MissingFormatArgumentException
     */
    public void checkForLabel() throws MissingFormatArgumentException {
        if (this.timeStart == -1) {
            throw new MissingFormatArgumentException("/by");
        }
    }

    /**
     * Method that checks if a date and/or time field exists.
     * @throws MissingResourceException
     */
    public void checkForDateAndTime() throws MissingResourceException {
        if (this.getUserInput().length - this.timeStart < 3) {
            throw new MissingResourceException("No date and/or time", "timedate", "key");
        }
    }

    public String generateDate(String date) {
        return convertToDukeDate(date);
    }

    public String generateTime(String time) {
        return convertToDukeTime(time);
    }
}
