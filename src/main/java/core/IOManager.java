package core;

import core.exceptions.FileIsCorruptException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class IOManager {
    private InputStream inputStream;
    private PrintWriter printWriter;
    private InputHandler inputHandler;

    private final String WELCOME_MESSAGE = "Hello! I'm Dooke\n What can I do for you?";
    private final String BYE_COMMAND = "bye";
    private final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    private final String FILE_PATH = "./data/duke.txt";

    public static IOManager getInstance() {
        return new IOManager();
    }

    public static IOManager getInstance(InputStream inputStream, PrintWriter printWriter) {
        return new IOManager(inputStream, printWriter);
    }

    private IOManager() {
        this(System.in, new PrintWriter(System.out));
        this.inputHandler = InputHandler.getInstance();
    }

    private IOManager(InputStream inputStream, PrintWriter printWriter) {
        this.inputStream = inputStream;
        this.printWriter = printWriter;
        this.inputHandler = InputHandler.getInstance();
    }

    private void formattingBeforeInput() {
        printWriter.println("-------------");
    }

    private void formattingAfterOutput() {
        printWriter.println("- - - - - - -");
    }

    private void display(String content) {
        formattingBeforeInput();
        printWriter.println(content);
        formattingAfterOutput();
        printWriter.flush();
    }

    private void displayWelcomeMessage() {
        display(WELCOME_MESSAGE);
    }

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
