package duke;

/**
 * The user interface.
 * Interacts with user to receive inputs and print outputs.
 */
public class Ui {

    /**
     * Prints an error message for failure in loading files
     */
    public void showLoadingError() {
        System.out.println("Failed to retrieve data from storage");
    }

    /**
     * Interprets the user input and prints outputs according to them
     *
     * @param input User input
     * @param tasks Current TaskList in the Duke program
     */
    public void nextInput(String input, TaskList tasks) {
        try {
            Command cmd = Parser.getCommand(input);
            
            switch(cmd) {
            case LIST:
                printResponse(tasks.list());
                break;
            case TODO:
                printResponse(tasks.add(new ToDo(Parser.getDescription(input))));
                break;
            case DEADLINE:
                printResponse(tasks.add(new Deadline(Parser.getDescription(input), Parser.getDate(input))));
                break;
            case EVENT:
                printResponse(tasks.add(new Event(Parser.getDescription(input), Parser.getDate(input))));
                break;
            case MARK:
                printResponse(tasks.mark(Parser.getIndex(input)));
                break;
            case UNMARK:
                printResponse(tasks.unmark(Parser.getIndex(input)));
                break;
            case DELETE:
                printResponse(tasks.delete(Parser.getIndex(input)));
                break;
            case FIND:
                printResponse(tasks.find(Parser.getDescription(input)));
                break;
            }
        } catch (DukeException e) {
            printResponse(e.toString());
        }
    }

    /**
     * Prints the starting logo and introduction for the Duke program
     */
    public void start() {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printResponse("Hello! I'm Duke\n    What can I do for you?");
    }

    public String getDukeOutput(String input, TaskList tasks) {
        try {
            Command cmd = Parser.getCommand(input);

            switch(cmd) {
                case LIST:
                    return tasks.list();
                case TODO:
                    return tasks.add(new ToDo(Parser.getDescription(input)));
                case DEADLINE:
                    return tasks.add(new Deadline(Parser.getDescription(input), Parser.getDate(input)));
                case EVENT:
                    return tasks.add(new Event(Parser.getDescription(input), Parser.getDate(input)));
                case MARK:
                    return tasks.mark(Parser.getIndex(input));
                case UNMARK:
                    return tasks.unmark(Parser.getIndex(input));
                case DELETE:
                    return tasks.delete(Parser.getIndex(input));
                case FIND:
                    return tasks.find(Parser.getDescription(input));
                case BYE:
                    return "Bye bye. BMO will see you again soon ' v '";
            }
        } catch (DukeException e) {
            return e.toString();
        }

        return "";
    }

    /**
     * Prints the exiting sentence of the Duke program
     */
    public void exit() {
        printResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the String input in a specific format as a response from Duke
     *
     * @param toPrint String to be printed as Duke output
     */
    public static void printResponse(String toPrint) {
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + toPrint);
        System.out.println("    ________________________________________________________________\n");
        
    }

}
