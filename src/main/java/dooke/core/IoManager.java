package dooke.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import dooke.core.exceptions.FileIsCorruptException;
import dooke.utilities.OutputFormatter;

/**
 * Manager class to handle input/output data.
 * @author s7manth
 * @version 0.3
 */
public class IoManager {
    private InputStream inputStream;
    private PrintWriter printWriter;
    private InputHandler inputHandler;

    private final String welcomeMessage = "Hello! I'm dooke.Dooke\n What can I do for you?";
    private final String byeCommand = "bye";
    private final String byeMessage = "Bye. Hope to see you again soon!";

    private final String filePath = "./data/duke.txt";


    /**
     * Constructor for the IoManager class.
     */
    private IoManager() {
        this(System.in, new PrintWriter(System.out));
        this.inputHandler = InputHandler.getInstance();
    }

    /**
     * Constructor for the IoManager class.
     * @param inputStream The input stream used to take inputs from the user.
     * @param printWriter The print writer used to display appropriate output to the user.
     */
    private IoManager(InputStream inputStream, PrintWriter printWriter) {
        this.inputStream = inputStream;
        this.printWriter = printWriter;
        this.inputHandler = InputHandler.getInstance();
    }

    /**
     * Obtains an instance of IoManager.
     * @return An instance of IoManager class.
     */
    public static IoManager getInstance() {
        return new IoManager();
    }


    /**
     * Obtains an instance of IOManager with a specified input stream and print writer.
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

    private String outputWrapper(String content) {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        outputFormatter.appendAll("-------------", "\n", content, "\n", "- - - - - - -", "\n");
        return outputFormatter.getFormattedOutput();
    }

    /**
     * Displays text in an appropriate manner on the console.
     * @param content The string to be displayed.
     */
    private void display(String content) {
        formattingBeforeInput();
        printWriter.println(content);
        formattingAfterOutput();
        printWriter.flush();
    }

    /**
     * Gets the welcome message.
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return outputWrapper(welcomeMessage);
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
        try {
            File file = new File(filePath);

            if (file.exists()) {
                inputHandler.initializeWithFile(file);
            }
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

    /**
     * Obtains the response for input commands.
     * @param input The input command to be executed.
     * @return The response by Dooke.
     */
    public String getResponse(String input) {
        try {
            if (input.equalsIgnoreCase(byeCommand)) {
                inputHandler.saveToFile(filePath);
                return byeMessage;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return outputWrapper(this.inputHandler.handleInput(input));
    }
}
