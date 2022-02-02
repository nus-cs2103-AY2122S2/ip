public class Parser {
    private final static String EXITKEY = "bye";
    private final static String LISTKEY = "list";
    private final static String DELETEKEY = "delete\\s\\d";
    private final static String MARKKEY = "mark\\s\\d";
    private final static String UNMARKKEY = "unmark\\s\\d";
    private final static String TODOKEY = "^todo.*$";
    private final static String EVENTKEY = "^event.*$";
    private final static  String DEADLINEKEY = "^deadline.*$";

    public Parser() {
    }

    public static Command parseCommand(String toParse) throws Exception {
        if (isListCommand(toParse)) {
            return new ListCommand();
        }

        if (isDeleteCommand(toParse)) {
            return new DeleteCommand(toParse);
        }

        if (isMarkCommand(toParse)) {
            return new EditCommand(toParse, "1");
        }

        if (isUnmarkCommad(toParse)) {
            return new EditCommand(toParse, "0");
        }

        if (isTodoCommand(toParse)) {
            return new AddCommand(toParse, "todo");
        }

        if (isDeadlineCommand(toParse)) {
            return new AddCommand(toParse, "deadline");
        }

        if (isEventCommand(toParse)) {
            return new AddCommand(toParse, "event");
        }

        if (isExitCommand(toParse)) {
            return new ExitCommand();
        }

        else {
            throw new UnrecognizedCommandException("");
        }

    }

    public static boolean isExitCommand(String nextKey) {
        return nextKey.equals(EXITKEY);
    }

    public static boolean isDeleteCommand(String nextKey) {
        return nextKey.matches(DELETEKEY);
    }

    public static boolean isListCommand(String key) {
        return key.equals(LISTKEY);
    }

    public static boolean isMarkCommand(String nextKey) {
        return nextKey.matches(MARKKEY);
    }

    public static boolean isUnmarkCommad(String nextKey) {
        return nextKey.matches(UNMARKKEY);
    }

    public static boolean isTodoCommand(String nextKey) {
        return nextKey.matches(TODOKEY);
    }

    public static boolean isEventCommand(String nextKey) {
        return nextKey.matches(EVENTKEY);
    }

    public static boolean isDeadlineCommand(String nextKey) {
        return nextKey.matches(DEADLINEKEY);
    }
}
