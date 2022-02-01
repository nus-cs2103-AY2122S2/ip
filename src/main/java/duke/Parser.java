package main.java.duke;

public class Parser {

    /**
     * Makes sense of the user command
     *
     * @param userCommand Contains the original user commmand to be parsed.
     * @param ui Deals with interaction with the user.
     * @return Command that is based off the userCommand input.
     * @throws UnsupportedOperationException If userCommand is in the wrong format.
     */
    public static Command parse(String userCommand, Ui ui) throws UnsupportedOperationException {

        String[] descriptions = userCommand.split(" ", 2);
        String command = descriptions[0];
        switch (command) {
        case ("list"):
            return new ListCommand();
        case ("mark"):
            return new MarkCommand(Integer.parseInt(descriptions[1]) - 1);
        case ("unmark"):
            return new UnmarkCommand(Integer.parseInt(descriptions[1]) - 1);
        case ("todo"):
            try {
                return new AddTaskCommand(descriptions[1], command);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError("TodoFormatError");
                throw new UnsupportedOperationException();
            }
        case ("deadline"):
            try {
                String[] descriptionsSplitByTime = descriptions[1].split(" /by ", 2);
                return new AddTaskCommand(descriptionsSplitByTime[0], command, descriptionsSplitByTime[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError("DeadlineFormatError");
                throw new UnsupportedOperationException();
            }
        case ("event"):
            try {
                String[] descriptionsSplitAtTime = descriptions[1].split(" /at ", 2);
                return new AddTaskCommand(descriptionsSplitAtTime[0], command, descriptionsSplitAtTime[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError("EventFormatError");
                throw new UnsupportedOperationException();
            }
        case ("find"):
            return new FindCommand(descriptions[1]);
        case ("delete"):
            return new DeleteCommand(Integer.parseInt(descriptions[1]) - 1);
        case ("bye"):
            return new ExitCommand();
        default:
            throw new UnsupportedOperationException();
        }

    }

}


