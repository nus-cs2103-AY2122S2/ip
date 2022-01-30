package connor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import connor.command.ByeCommand;
import connor.exception.InvalidTaskFileException;
import connor.task.TaskList;

/**
 * Main class for Connor program to start running.
 *
 * @author jaysmyname
 * @version 1.0.0
 */
public class Connor {
    public static final String CURRENT_VERSION = "Version 1.0.0";
    public static final String LINE = "_".repeat(66);
    public static final String INDENT = " ".repeat(4);
    public static final String INPUT_HERE = ">>> ";

    private static final String TASK_FILEPATH = "data/connor.txt";

    private static final String ERROR_FILE_NOT_FOUND = "Error! Task file not found!";

    private static boolean isActive = true;
    private static final Scanner sc = new Scanner(System.in);

    private Storage storage;
    private Ui ui;

    /**
     * Constructor for {@code Connor} class.
     *
     * @param filePath File path to store text file of task list.
     * @throws IOException If an I/O error occurs.
     */
    public Connor(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    /**
     * Runs the Connor program. Greets the user first, shows their current tasks,
     * and finally asks for the user's input.
     * <p>
     * Once the user has inputted their command,
     * the program attempts to parse and activate the input if the input is valid,
     * before asking for another input. This process loops indefinitely until the user exits.
     */
    public void run() {
        ui.greetings();
        try {
            storage.loadTasks();
        } catch (FileNotFoundException e) {
            print(ERROR_FILE_NOT_FOUND);
            e.printStackTrace();
            return;
        } catch (IndexOutOfBoundsException | InvalidTaskFileException e) {
            print("ERROR: " + e.getMessage());
            print("Please fix the appropriate typo in the task file or clear it completely.");
            return;
        }
        TaskList.viewTasks();
        print(LINE);
        while (isActive) {
            System.out.print(INPUT_HERE);
            String input = sc.nextLine();
            print(input);
            print(LINE);
            Parser p = new Parser(input);
            p.parse();
            print(LINE);
            storage.updateFile();
        }
        sc.close();
    }

    /**
     * Prints the given string to the console.
     *
     * @param s String to be printed to console.
     */
    private static void print(String s) {
        System.out.println(s);
    }

    /**
     * Sets isActive. Currently, only used by {@code ByeCommand} to exit the program.
     *
     * @param isActive
     * @see ByeCommand#activate()
     */
    public static void setActive(boolean isActive) {
        Connor.isActive = isActive;
    }

    /**
     * Creates a new Connor object with the constant file path name and runs the program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        try {
            new Connor(TASK_FILEPATH).run();
        } catch (IOException e) {
            print(e.getMessage());
        }
    }

}
