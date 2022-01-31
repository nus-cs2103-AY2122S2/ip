
public class Parser {

    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    public static Command parse(String input) {

        try {
            String[] inputWords = input.split("\\s");
            Action action = Action.valueOf(inputWords[0].toUpperCase());    //action is first word of the input
            switch (action) {
                case BYE:
                    return new ExitCommand();
                case LIST:
                    return new ListCommand();
                case TODO:
                    return new AddCommand(Action.TODO, input);
                case DEADLINE:
                    return new AddCommand(Action.DEADLINE, input);
                case EVENT:
                    return new AddCommand(Action.EVENT, input);
                case MARK:
                    return new MarkCommand(inputWords);
                case UNMARK:
                    return new UnmarkCommand(inputWords);
                case DELETE:
                    return new DeleteCommand(inputWords);
                default:
                    return new InvalidCommand();
            }
        } catch (IllegalArgumentException e) {
            return new InvalidCommand();
        }
    }

    // returns the todo task description
    public static String parseTodo(String input) throws InvalidArgumentException {

        String[] todoArr = input.split("\\s", 2);
        if (todoArr.length <= 1) {
            throw new InvalidArgumentException(Messages.UNKNOWN_TODO);
        }
        return todoArr[1].trim();
    }

    // returns Deadline description and dateTime in a String[] like a pair.
    public static String[] parseDeadline(String input) throws InvalidArgumentException {
        String[] deadlineArr = input.split("/by", 2);
        String[] deadlineSplit = deadlineArr[0].split("\\s", 2);
        if (deadlineSplit.length <= 1) {    // no description
            throw new InvalidArgumentException(Messages.UNKNOWN_DEADLINE);
        }
        if (deadlineArr.length <= 1) { // don't have /by keyword
            throw new InvalidArgumentException(Messages.UNKNOWN_DATETIME);
        }
        return new String[]{deadlineSplit[1].trim(), deadlineArr[1].trim()};
    }

    //returns pair of description and /at for event, so that an event Task can be created.
    public static String[] parseEvent(String input) throws  InvalidArgumentException {
        String[] eventArr = input.split("/at", 2);
        String[] eventSplit = eventArr[0].split("\\s", 2);
        if (eventSplit.length <= 1) {
            throw new InvalidArgumentException(Messages.UNKNOWN_EVENT);
        }
        if (eventArr.length <= 1) {
            throw new InvalidArgumentException(Messages.UNKNOWN_LOCATION);
        }
        return new String[]{eventSplit[1].trim(), eventArr[1].trim()};  // description, and at respectively
    }
}
