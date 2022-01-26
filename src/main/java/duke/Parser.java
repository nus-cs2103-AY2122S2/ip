package main.java.duke;

public class Parser {

    public static Command parse(String userCommand, Ui ui) throws UnsupportedOperationException {
        String words[] = userCommand.split(" ", 2);
        String command = words[0];
        switch (command) {
        case ("list"):
            return new ListCommand();
        case ("mark"):
            return new MarkCommand(Integer.parseInt(words[1]) - 1);
        case ("unmark"):
            return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
        case ("todo"):
            try {
                return new AddTaskCommand(words[1], command);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError("TodoFormatError");
                throw new UnsupportedOperationException();
            }
        case ("deadline"):
            try {
                String temp[] = words[1].split(" /by ", 2);
                return new AddTaskCommand(temp[0], command, temp[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError("DeadlineFormatError");
                throw new UnsupportedOperationException();
            }
        case ("event"):
            try {
                String temp2[] = words[1].split(" /at ", 2);
                return new AddTaskCommand(temp2[0], command, temp2[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.showError("EventFormatError");
                throw new UnsupportedOperationException();
            }
        case ("delete"):
            return new DeleteCommand(Integer.parseInt(words[1]) - 1);
        case ("bye"):
            return new ExitCommand();
        default:
            throw new UnsupportedOperationException();
        }

    }

}


