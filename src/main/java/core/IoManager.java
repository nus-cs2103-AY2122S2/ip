package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import core.exceptions.FileIsCorruptException;

/**
 * Manager class to handle input/output data.
 *
 * @author s7manth
 * @version 0.1
 */
public class IoManager {
    private InputStream inputStream;
    private PrintWriter printWriter;
    private InputHandler inputHandler;

    private final String welcomeMessage = "Hello! I'm Dooke\n What can I do for you?";
    private final String byeCommand = "bye";
    private final String byeMessage = "Bye. Hope to see you again soon!";

    private final String filePath = "./data/duke.txt";


    /**
     * Constructor for the IOManager class.
     */
    private IoManager() {
        this(System.in, new PrintWriter(System.out));
        this.inputHandler = InputHandler.getInstance();
    }

    /**
     * Overloaded constructor for the IOManager class with a specified input stream and print writer.
     *
     * @param inputStream The input stream used to take inputs from the user.
     * @param printWriter The print writer used to display appropriate output to the user.
     */
    private IoManager(InputStream inputStream, PrintWriter printWriter) {
        this.inputStream = inputStream;
        this.printWriter = printWriter;
        this.inputHandler = InputHandler.getInstance();
    }

    /**
     * Factory method to obtain an instance of IOManager.
     *
     * @return An instance of IOManager class.
     */
    public static IoManager getInstance() {
        return new IoManager();
    }

    /**
     * Factory method to obtain an instance of IOManager with a specified input stream and print writer.
     *
     * @param inputStream The input stream used to take inputs from the user.
     * @param printWriter The print writer used to display appropriate output to the user.
     * @return An instance of the IOManager class with the specified input stream and print writer.
     */
    public static IoManager getInstance(InputStream inputStream, PrintWriter printWriter) {
        return new IoManager(inputStream, printWriter);
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
        display(welcomeMessage);
    }

    /**
     * Initializer method to start Dooke.
     */
    public void start() {
        displayWelcomeMessage();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                inputHandler.initializeWithFile(file);
            }
            beginRepl();
            inputHandler.saveToFile(filePath);
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
                if (input.equalsIgnoreCase(byeCommand)) {
                    display(byeMessage);
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
