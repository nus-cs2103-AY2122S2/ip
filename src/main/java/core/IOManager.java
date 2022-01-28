package core;

import core.exceptions.FileIsCorruptException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Manager class to handle input/output data.
 *
 * @author s7manth
 * @version 0.1
 */
public class IOManager {
    private InputStream inputStream;
    private PrintWriter printWriter;
    private InputHandler inputHandler;

    private final String WELCOME_MESSAGE = "Hello! I'm Dooke\n What can I do for you?";
    private final String BYE_COMMAND = "bye";
    private final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    private final String FILE_PATH = "./data/duke.txt";

    /**
     * Factory method to obtain an instance of IOManager.
     *
     * @return An instance of IOManager class.
     */
    public static IOManager getInstance() {
        return new IOManager();
    }

    /**
     * Factory method to obtain an instance of IOManager with a specified input stream and print writer.
     *
     * @param inputStream The input stream used to take inputs from the user.
     * @param printWriter The print writer used to display appropriate output to the user.
     * @return An instance of the IOManager class with the specified input stream and print writer.
     */
    public static IOManager getInstance(InputStream inputStream, PrintWriter printWriter) {
        return new IOManager(inputStream, printWriter);
    }

    /**
     * Constructor for the IOManager class.
     */
    private IOManager() {
        this(System.in, new PrintWriter(System.out));
        this.inputHandler = InputHandler.getInstance();
    }

    /**
     * Overloaded constructor for the IOManager class with a specified input stream and print writer.
     *
     * @param inputStream The input stream used to take inputs from the user.
     * @param printWriter The print writer used to display appropriate output to the user.
     */
    private IOManager(InputStream inputStream, PrintWriter printWriter) {
        this.inputStream = inputStream;
        this.printWriter = printWriter;
        this.inputHandler = InputHandler.getInstance();
    }

    /**
     * Produces pre input formatting.
     */
    private void formattingBeforeInput() {
        printWriter.println("-------------");
    }

    /**
     * Produces post output formatting.
     */
    private void formattingAfterOutput() {
        printWriter.println("- - - - - - -");
    }

    /**
     * Displays text in an appropriate manner on the console.
     *
     * @param content The string to be displayed.
     */
    private void display(String content) {
        formattingBeforeInput();
        printWriter.println(content);
        formattingAfterOutput();
        printWriter.flush();
    }

    /**
     * Method to display Dooke's welcome message.
     */
    private void displayWelcomeMessage() {
        display(WELCOME_MESSAGE);
    }

    /**
     * Initializer method to start Dooke.
     */
    public void start() {
        displayWelcomeMessage();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                inputHandler.initializeWithFile(file);
            }
            beginRepl();
            inputHandler.saveToFile(FILE_PATH);
        } catch (FileIsCorruptException fileIsCorruptException) {
            display(fileIsCorruptException.getMessage());
            fileIsCorruptException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Starts the read, eval, print loop ie interactive segment of Dooke.
     */
    private void beginRepl() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(this.inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while (true) {
                String input = bufferedReader.readLine();
                if (input.equalsIgnoreCase(BYE_COMMAND)) {
                    display(BYE_MESSAGE);
                    break;
                }
                String output = inputHandler.handleInput(input);
                display(output);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
