package duke;

import duke.command.*;

public class Parser {
    private String command;
    private Checker checker;
    private Storage storage;
    private Command parsedCommand;

    private static final String DESC_RESPONSE = "Oops! \\(@.@)/ You have not keyed in a description for the duke" +
            ".task!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this duke.command";

    public Parser(String input) throws DukeException {
        String[] textEntered = input.split(" ", 2);
        command = textEntered[0];
        checker = new Checker(command);

        switch (checker.getStatus()) {
        case BYE:
            parsedCommand = new ExitCommand();
            break;
        case HELP:
            parsedCommand = new HelpCommand();
            break;
        case LIST:
            parsedCommand = new ListTaskCommand();
            break;
        case DELETE:
            try {
                String deleteId = textEntered[1];
                parsedCommand = new DeleteCommand(deleteId);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! \\(@.@)/ You have not keyed in an ID!\n" +
                        "Let's try again ~(^.^)~\n" +
                        "Type 'help' if you need to know how to use this duke.command");
            }
            break;
        case TODO:
            try {
                String description = textEntered[1];
                parsedCommand = new AddTodoCommand(description);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(DESC_RESPONSE);
            }
            break;
        case DEADLINE:
            try {
                String text = textEntered[1];
                if (!text.contains("/by")) {
                    throw new DukeException("Please use \"/by\"");
                }
                String[] textArr = text.split("/by");
                String description = textArr[0].trim();
                if (textArr.length == 1) {
                    throw new DukeException("Oops, please specify a date!");
                }
                String time = textArr[1].trim();
                parsedCommand = new AddDeadlineCommand(description, time);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(DESC_RESPONSE);
            }
            break;
        case EVENT:
            try {
                String text = textEntered[1];
                if (!text.contains("/at")) {
                    throw new DukeException("Please use \"/at\"");
                }
                String[] textArr = text.split("/at ");
                String description = textArr[0].trim();
                if (textArr.length == 1) {
                    throw new DukeException("Oops, please specify a date!");
                }
                String time = textArr[1];
                parsedCommand = new AddEventCommand(description, time);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(DESC_RESPONSE);
            }
            break;
        case UNMARK:
            String unmarkId = textEntered[1];
            parsedCommand = new UnmarkCommand(unmarkId);
            break;
        case MARK:
            String markId = textEntered[1];
            parsedCommand = new MarkCommand(markId);
            break;
        }
    }

    public Command parse() {
        return parsedCommand;
    }
}
