import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Pun Hui Min
 */

public class Duke {

    //    protected ArrayList<Task> tasks;
    protected Storage storage;
    private String filename;
    private Ui ui;
    private TaskList tasks;

    static final String NO_DESC = "Oops! \\(@.@)/ You have not keyed in a description for the task!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this command";
    static final String BYE_RESPONSE = "Bye~ Hope to see you again soon!\n៚ ⋯⋯⋯ |\\__( o)> ⋯⋯⋯⋯ ༄";
    static final String INVALID_DATE = "Oops, please put a valid time format!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this command";


    public void welcome() {
        String logo = " ____              _"
                + "\n|  _ \\ _   _ _____| | ____ __\n"
                + "| | | | | | |  ___| |/ /\\ v /\n"
                + "| |_| | |_| | |___|   <  | |\n"
                + "|____/ \\___/\\_____|_|\\_\\ |_|\n";

        System.out.println(logo + "Hello! I'm Ducky! :)\n" + "I am a task manager.\n" +
                "Type 'help' for more information on the commands you can give me.\n" +
                "What can I do for you today?\n" +
                "✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧");
    }

    /**
     * Processes the string inputted by the user. Filters the command and calls on other functions to print a string.
     *
     * @throws DukeException when the specified ID number is not in the list, if the time is not provided accurately,
     *                       or if there was no description or command provided.
     */
    public void run(Scanner myObj) throws DukeException {
        String response = myObj.nextLine();
        String[] textEntered = response.split(" ", 2);
        String command = textEntered[0];
        Parser state = new Parser(command);

        switch (state.getStatus()) {
        case BYE:
            System.out.println("Your tasks have been saved in " + this.filename +
                    "✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧");
            this.ui.printBye(this.tasks);
            System.exit(1);
            break;
        case HELP:
            this.ui.help();
            break;
        case LIST:
            this.ui.printTasks(this.tasks);
            break;
        case DELETE:
            try {
                String id = textEntered[1];
                this.ui.deleteTask(id, this.tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! \\(@.@)/ You have not keyed in an ID!\n" +
                        "Let's try again ~(^.^)~\n" +
                        "Type 'help' if you need to know how to use this command");
            }
            break;
        case TODO:
            try {
                String description = textEntered[1].trim();
                this.tasks = this.ui.addTodo(description, this.tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(NO_DESC);
            }
            break;
        case DEADLINE:
            try {
                String text = textEntered[1];
                if (!text.contains("/by")) {
                    throw new DukeException("Please use \"/by\"");
                }
                String[] textArr = text.split("/by ");
                String description = textArr[0].trim();
                if (textArr.length == 1) {
                    throw new DukeException("Oops, please specify a date!");
                }
                String time = textArr[1];
                this.ui.addDeadline(description, time, this.tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(NO_DESC);
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
                this.tasks = this.ui.addEvent(description, time, this.tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(NO_DESC);
            }
            break;
        case UNMARK:
            String strID = textEntered[1];
            this.ui.unmarkItem(strID, this.tasks);
            break;
        case MARK:
            String id = textEntered[1];
            this.ui.markItem(id, this.tasks);
            break;
        }
        // call system to save the list to text file
        this.storage.saveFile(this.tasks.formatTasks());
        System.out.println("✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧");
    }

    public Duke(String filepath) {
        this.tasks = new TaskList();
        this.filename = filepath;
        this.storage = new Storage(filepath);
        this.ui = new Ui();
    }

    /**
     * Takes in the user input and creates a scanner.
     *
     * @param args Takes in the user input from the CLI
     */
    public static void main(String[] args) {
        Duke ducky = new Duke("duke.txt");
        ducky.welcome();
        Scanner myObj = new Scanner(System.in);
        try {
            ducky.tasks = ducky.storage.readFile(ducky.filename);
        } catch (DukeException e) {
            System.out.println(e.toString() + "\n✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧");
        }
        while (true) {
            try {
                ducky.run(myObj);
            } catch (DukeException e) {
                System.out.println(e.toString() + "\n✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧");
            }
        }
    }
}

