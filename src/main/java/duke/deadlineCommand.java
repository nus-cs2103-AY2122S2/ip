package duke;

import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;

public class deadlineCommand extends Commands {

    private String description;
    private int timeStart;

    public deadlineCommand(DukeHistory history, String[] userInput, DukeUi ui) {
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

    public void generateDescriptionAndGetLabel() {
        int timeStart = -1;
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < userInput.length; i++) {
            if (userInput[i].equals("/by")) {
                timeStart = i;
                break;
            } else {
                description.append(userInput[i]);
            }
            description.append(" ");
        }
        this.description = description.toString();
        this.timeStart = timeStart;
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

    public String generateDate(String date) {
        return convertToDukeDate(date);
    }

    public String generateTime(String time) {
        return convertToDukeTime(time);
    }
}
