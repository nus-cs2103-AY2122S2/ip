import java.io.InputStream;
import java.io.PrintStream;

/**
 * Represents the UI component of the task manager.
 * This class is responsible for generating outputs to the user.
 */
class Ui {

    private static final String WELCOME_MESSAGE = "Hello, this is Duke!\nWhat can I do for you today?";
    private static final String OUTPUT_PREFIX = ">> ";
    private static final String SEE_YOU_MESSAGE = "Bye!";
    private static final String PROMPT_INSTRUCTION_MESSAGE = "What else can I do for you?";

    private PrintStream outputStream;
    private Parser parser;

    /**
     * Constructs a UI, with the specified output stream.
     *
     * @param outputStream The output stream that the UI writes its output to.
     * @param inputStream The input stream that the UI accepts commands from.
     */
    Ui(PrintStream outputStream, InputStream inputStream) {

        this.outputStream = outputStream;
        this.parser = new Parser(inputStream);
    }

    /**
     * Prints the welcome message to the output stream.
     */
    void printWelcomeMessage() {

        outputStream.println(WELCOME_MESSAGE);
    }

    /**
     * Prints the see you message to the output stream.
     */
    void printSeeYouMessage() {

        outputStream.println(SEE_YOU_MESSAGE);
    }

    /**
     * Reads the next instruction and interprets it.
     *
     * @param tasks The task manager to be used.
     * @return The instruction.
     * @throws DukeException If the instruction is not valid.
     */
    Instruction getNextInstruction(TaskManager tasks) throws DukeException {
        return parser.parseInstruction(tasks);
    }

    /**
     * Prints the message to the output stream.
     *
     * @param message The message to be printed.
     */
    void printMessage(String message) {
        outputStream.println(OUTPUT_PREFIX + message);
    }

    /**
     * Prints a message to prompt the user for next instruction.
     */
    void askForInstruction() {
        outputStream.println(PROMPT_INSTRUCTION_MESSAGE);
    }


}
