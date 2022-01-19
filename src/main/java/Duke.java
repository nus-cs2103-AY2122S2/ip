import exceptions.DukeException;

import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String SEPARATOR = "____________________________________________________________";
    private static final String EXIT_MSG = "Bye. Hope to see you again soon!";
    private static final String GREET_MSG = "Hello! I'm Jarvis\nWhat can I do for you?";

    private static final String STATUS_RUNNING = "running";
    private static final String STATUS_STOPPED = "stopped";

    private static final String QUIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private String status;
    private DukeList list;

    /**
     * Constructor for the Duke bot
     */
    public Duke() {
        this.status = STATUS_RUNNING;
        this.list = new DukeList();
    }

    /**
     * Formats and prints the given message.
     *
     * @param msg
     */
    private static void printMsg(String msg) {
        System.out.println(SEPARATOR);
        System.out.println(msg);
        System.out.println(SEPARATOR);
        System.out.println();
    }

    /**
     * Prints the greeting message.
     */
    public static void greet() {
        printMsg(GREET_MSG);
    }

    /**
     * Parse the user command.
     *
     * @param cmd
     * @return result after executing the command
     */
    private String parseCommand(String cmd) {
        String[] argv = cmd.split(" ", 2);
        String action = argv[0];

        String result;
        int idx;
        switch (action) {
            case QUIT_COMMAND:
                result = EXIT_MSG;
                this.status = STATUS_STOPPED;
                break;

            case LIST_COMMAND:
                result = this.list.toString();
                break;

            case MARK_COMMAND:
                try {
                    idx = Integer.parseInt(argv[1]);
                    result = this.list.markTask(idx);
                } catch (IndexOutOfBoundsException e) {
                    result = e.getMessage();
                }
                break;

            case UNMARK_COMMAND:
                try {
                    idx = Integer.parseInt(argv[1]);
                    result = this.list.unmarkTask(idx);
                } catch (IndexOutOfBoundsException e) {
                    result = e.getMessage();
                }
                break;

            case DELETE_COMMAND:
                try {
                    idx = Integer.parseInt(argv[1]);
                    result = this.list.delete(idx);
                } catch (IndexOutOfBoundsException e) {
                    result = e.getMessage();
                }
                break;

            case TODO_COMMAND:
            case DEADLINE_COMMAND:
            case EVENT_COMMAND:
                try {
                    result = this.list.add(cmd);
                } catch (DukeException e) {
                    result = e.getMessage();
                }
                break;

            default:
                result = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                break;
        }
        return result;
    }

    /**
     * Handler for the duke bot.
     *
     * @param sc
     */
    private void handler(Scanner sc) {
        String cmd;

        while (this.status.equals(STATUS_RUNNING) && sc.hasNextLine()) {
            cmd = sc.nextLine();
            String result = parseCommand(cmd);
            printMsg(result);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Duke.greet();
        Scanner sc = new Scanner(System.in);
        duke.handler(sc);
        sc.close();
    }
}
