package ui.command;

import java.util.ArrayList;

/**
 * @author Jiaaa-yang
 *
 * An abstract class to represent the commands
 * that can be issued to a ChatBot.
 */
public abstract class Command {
    /**
     * Constants for styling of printing command feedback
     */
    private final static int DELIMITER_LENGTH = 25;
    private final static int INDENT_SIZE = 4;
    private final static String DELIMITER = "_".repeat(DELIMITER_LENGTH);
    private final static String INDENT = " ".repeat(INDENT_SIZE);

    /**
     * Name of current command, as inputted by user
     */
    private final String name;

    public Command(String name) {
        this.name = name;
    }

    /**
     * Print response of executed command styled between 2 lines
     * with indentation. Each item in response array is printed
     * in a new line
     *
     * @param response list of response to print
     */
    protected static void styledPrint(ArrayList<String> response) {
        response.add(0, DELIMITER);
        response.add(DELIMITER);
        // Prepend each response item with an indent and a line break
        String styledResponse = response.stream()
                .map(item -> INDENT + item + '\n')
                .reduce("", (prevLine, nextLine) -> prevLine + nextLine);
        System.out.println(styledResponse);
    }

    /**
     * Execute current command in ChatBot context
     *
     * @return whether the command is a terminating command
     */
    public abstract boolean execute();

    protected String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
