package duke.main;

public class Ui {
    private static Parser parser;

    /**
     * The possible types of Reply that Burp can have, in response to
     * a Command
     */
    public enum Reply {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DEFAULT
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
        default:
            return Reply.DEFAULT;
        }
    }

    /**
     * Method to output a formatted "Bye" message.
     */
    public static void showBye() {
        System.out.println(parser.formatMsg("Bye. Hope to see you again soon!"));
    }

    /**
     * Passes down the parameters to Parser in order to determine
     * what Burp should reply with.
     *
     * @param type the type of Reply Burp should give
     * @param toDoList the user's List of Tasks
     * @param cmd the user's input to Burp
     * @throws DukeException when a WrongCommand is given
     */
    public static void burpReply(Ui.Reply type, TaskList toDoList, String cmd, Storage storage) throws DukeException {
        Parser.parseCommands(type, toDoList, cmd, storage);
    }

    /**
     * Method to output a formatted "Welcome" message.
     */
    public static void showWelcome() {
        System.out.println(parser.formatMsg("Hello from Burp\n\tWhat can I do for you?"));
    }

    /**
     * Method to output a formatted file error message
     */
    public static void handleLoadError() {
        System.out.println("File could not be loaded -- most likely it has not been created");
    }

}
