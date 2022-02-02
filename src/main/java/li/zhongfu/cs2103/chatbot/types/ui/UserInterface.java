package li.zhongfu.cs2103.chatbot.types.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * The interface between Duke and the user.
 */
public class UserInterface {
    private static final String HLINE = "____________________________________________________________";

    private static final String MOTD = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private PrintStream output;
    private BufferedReader br;
    private Parser parser;

    /**
     * Creates a new UserInterface instance with the given InputStream and PrintStream as input and output.
     * 
     * @param input the InputStream to read user input from
     * @param output the PrintStream to output messages to
     */
    public UserInterface(InputStream input, PrintStream output) {
        this.parser = new Parser();
        this.output = output;
        this.br = new BufferedReader(new InputStreamReader(input));
    }

    /**
     * Prints the given lines in a dialog.
     * 
     * Each array element represents a new line, and each element will additionally be split into multiple
     * lines if a newline character is present.
     * 
     * @param lines an array containing the lines to print, split by lines
     */
    public void printDialog(String[] lines) {
        this.output.println("    " + HLINE);
        for (String line : lines) {
            if (line.contains("\n")) {
                String[] sublines = line.split("\n");
                for (String subline : sublines) {
                    this.output.println("    " + subline);
                }
            } else {
                this.output.println("    " + line);
            }
        }
        this.output.println("    " + HLINE + "\n");
    }

    /**
     * Print the given string in a dialog.
     * 
     * @param line the string to be printed
     */
    public void printDialog(String line) {
        this.printDialog(line.split("\n"));
    }

    /**
     * Prints the message of the day.
     */
    public void printMotd() {
        this.output.println(MOTD); // extra newline
    }

    /**
     * Prints a string and a newline character to the output without additional formatting.
     * 
     * @param str the string to be printed
     */
    public void println(String str) {
        this.output.println(str);
    }

    /**
     * Prints a string to the output without additional newline characters or formatting.
     * 
     * @param str the string to be printed
     */
    public void print(String str) {
        this.output.print(str);
    }

    /**
     * Parses user input as a command and returns a ParserResult with the user input.
     * 
     * @return a ParserResult containing the user input
     */
    public ParserResult getInput() throws IOException {
        String input = this.br.readLine();
        String[] parts = input.split("\\s+", 2); // split into command and args
        String cmd = parts[0];
        if (parts.length == 1) { // only command
            return new ParserResult(cmd, new HashMap<>());
        } else { // length is 2
            Map<String, String> args = this.parser.parseArgString(parts[1]);
            return new ParserResult(cmd, args);
        }
    }

    // i know this really really really doesn't belong here
    // but i kinda can't be arsed
    // todo fix this when you implement... idk... command objects, handlers, whatever
    /**
     * Parses a LocalDateTime from a given date-time string.
     * 
     * @param dateTimeString a string containing a date and time to be parsed
     * @return a parsed date/time in the form of a LocalDateTime instances
     * @throws DateTimeParseException if dateTimeString contains an unrecognized date-time format
     */
    public LocalDateTime parseDateTime(String dateTimeString) throws DateTimeParseException {
        return this.parser.parseDateTime(dateTimeString);
    }
}