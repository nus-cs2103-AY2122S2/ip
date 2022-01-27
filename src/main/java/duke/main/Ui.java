package duke.main;

public class Ui {
    private static Parser parser;
    private static String response;
    public static String errorMsg;

    /**
     * The possible types of Reply that Burp can have, in response to
     * a Command
     */
    public enum Reply {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DEFAULT, FIND, BYE
    }

    /**
     * Constructor for Ui.
     * Simply creates a new Parser to parse Commands.
     */
    public Ui() {
        parser = new Parser();
    }

    // to determine what kind of reply Burp should give

    /**
     * Determines the type of Reply Burp should have
     * based on the user's input
     *
     * @param command the user's input to Burp
     * @return a Reply, based on the user's input
     */
    public static Reply determineType(String command) {
        switch (command) {
        case "list":
            return Reply.LIST;
        case "todo":
            return Reply.TODO;
        case "deadline":
            return Reply.DEADLINE;
        case "event":
            return Reply.EVENT;
        case "mark":
            return Reply.MARK;
        case "unmark":
            return Reply.UNMARK;
        case "delete":
            return Reply.DELETE;
        case "find":
            return Reply.FIND;
        case "bye":
            return Reply.BYE;
        default:
            return Reply.DEFAULT;
        }
    }

    /**
     * Method to output a formatted "Bye" message.
     */
    public static void showBye() {
        Ui.setDukeResponse(Parser.formatMsg("Bye. Hope to see you again soon!"));
    }

    /**
     * Passes down the parameters to Parser in order to determine
     * what Burp should reply with.
     *
     * @param type     the type of Reply Burp should give
     * @param toDoList the user's List of Tasks
     * @param cmd      the user's input to Burp
     * @throws DukeException when a WrongCommand is given
     */
    public static void burpReply(Ui.Reply type, TaskList toDoList, String cmd, Storage storage) throws DukeException {
        Parser.parseCommands(type, toDoList, cmd, storage);
    }

    /**
     * Method to output a formatted "Welcome" message.
     */
    public static void showWelcome() {
        Ui.setDukeResponse(Parser.formatMsg("Hello from Burp\nWhat can I do for you?"));
    }

    /**
     * Method to output a formatted file error message
     */
    public static void handleLoadError() {
        System.out.println("File could not be loaded -- most likely it has not been created");
    }


    public static String getDukeResponse() {
        return Ui.response;
    }

    public static String setDukeResponse(String response) {
        Ui.response = response;
        return Ui.response;
    }

    public static String getDukeResponseError() {
        return Ui.errorMsg;
    }

    public static String setDukeResponseError(String errorMsg) {
        Ui.errorMsg = errorMsg;
        return Ui.errorMsg;
    }

}
