package duke.Commands;

import duke.Deadlines;
import duke.DukeHistory;
import duke.DukeUi;

import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;

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

    @Override
    public String execute() {
        String date = generateDate(userInput[this.timeStart + 1]);
        String time = generateTime(userInput[this.timeStart + 2]);
        Deadlines tempDeadline = history.addDeadline(description, date, time);
        return "_______________________________________________________\n"
                + "Understood, adding this task now:\n"
                + "    " + tempDeadline.getDeadline()
                + "Currently you have " + history.getSize() + " tasks in our records.\n"
                + "_______________________________________________________\n";
    }

    public void generateDescriptionAndGetLabel() {
        StringBuilder tempDescription = new StringBuilder();
        for (int i = 1; i < userInput.length; i++) {
            if (userInput[i].equals("/by")) {
                this.timeStart = i;
                break;
            } else {
                tempDescription.append(userInput[i]);
            }
            tempDescription.append(" ");
        }
        this.description = tempDescription.toString();
    }

    public void checkForLabel() throws MissingFormatArgumentException {
        if (this.timeStart == -1) {
            throw new MissingFormatArgumentException("/by");
        }
    }

    public void checkForDateAndTime() throws MissingResourceException {
        if (userInput.length - this.timeStart < 3 ) {
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
