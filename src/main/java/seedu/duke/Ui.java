package seedu.duke;

/**
 * The user interface.
 * Interacts with user to receive inputs and print outputs.
 */
public class Ui {

    private static final int LIST = 0;
    private static final int TODO = 1;
    private static final int DEADLINE = 2;
    private static final int EVENT = 3;
    private static final int MARK = 4;
    private static final int UNMARK = 5;
    private static final int DELETE = 6;

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
            int cmd = Parser.getCommand(input);
            
            switch(cmd) {
            case LIST:
                myPrint(tasks.list());
                break;
            case TODO:
                myPrint(tasks.add(new ToDo(Parser.getDescription(input))));
                break;
            case DEADLINE:
                myPrint(tasks.add(new Deadline(Parser.getDescription(input), Parser.getDate(input))));
                break;
            case EVENT:
                myPrint(tasks.add(new Event(Parser.getDescription(input), Parser.getDate(input))));
                break;
            case MARK:
                myPrint(tasks.mark(Parser.getIndex(input)));
                break;
            case UNMARK:
                myPrint(tasks.unmark(Parser.getIndex(input)));
                break;
            case DELETE:
                myPrint(tasks.delete(Parser.getIndex(input)));
                break;
            }
        } catch (DukeException e) {
            myPrint(e.toString());
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

        myPrint("Hello! I'm Duke\n    What can I do for you?");
    }

    /**
     * Prints the exiting sentence of the Duke program
     */
    public void exit() {
        myPrint("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the String input in a specific format as a response from Duke
     *
     * @param toPrint String to be printed as Duke output
     */
    public static void myPrint(String toPrint) {
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + toPrint);
        System.out.println("    ________________________________________________________________\n");
        
    }

}
