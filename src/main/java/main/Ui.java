package main;

public class Ui {
    private static Parser parser;

    public enum Reply {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DEFAULT
    }

    public Ui() {
        parser = new Parser();
    }

    // to determine what kind of reply Burp should give
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

    public static void showBye() {
        System.out.println(parser.formatMsg("Bye. Hope to see you again soon!"));
    }

    public static void burpReply(Ui.Reply type, TaskList toDoList, String cmd) {
        Parser.parseCommands(type, toDoList, cmd);
    }

    public static void showWelcome() {
        System.out.println(parser.formatMsg("Hello from Burp\n\tWhat can I do for you?"));
    }

    public static void handleLoadError() {
        System.out.println("File could not be loaded -- most likely it has not been created");
    }

}
