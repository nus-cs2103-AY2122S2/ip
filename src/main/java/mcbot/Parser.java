package mcbot;

import java.util.Scanner;

import mcbot.exception.InvalidCommandException;

/**
 * Parser class to handle parsing of command inputs. 
 */
public class Parser {
    private final Scanner sc;
    private String fullCommand;

    /**
     * Constructor for the parser. 
     */
    public Parser() { 
        Scanner sc = new Scanner(System.in);
        this.sc = sc;
    }

    /**
     * To parse the entire line of input. 
     */
    public void readFullCommand() {
        fullCommand = sc.nextLine();
    }

    /**
     * Method to parse the first word of the input. 
     * This allows it to identify the key command given. 
     * 
     * @return The key command. 
     */
    public String getKeyCommand() {
        return fullCommand.split(" ", 2)[0];
    }

    /**
     * Method to close the parser.
     */
    public void close() {
        sc.close();
    }

    /**
     * Method to parse the details of the command. 
     * 
     * @return The details of the command given. 
     * @throws InvalidCommandException will be thrown if the details is blank. 
     */
    public String getDetails() throws InvalidCommandException {
        String details = fullCommand.split(" ", 2)[1];
        if (details.isBlank()) {
            throw new InvalidCommandException();
        }
        return details;
    }

    /**
     * Method to parse deadline task name. 
     * 
     * @return the string of deadline task name.
     */
    public String getDeadlineTask() {
        return fullCommand.split(" ", 2)[1].split(" /by ")[0];
    }

    /**
     * Method to parse deadline date. 
     *
     * @return the string of deadline task date.
     */
    public String getDeadlineDate() {
        return fullCommand.split(" ", 2)[1].split(" /by ")[1].split(" ")[0];
    }

    /**
     * Method to parse event task name. 
     *
     * @return the string of event task name.
     */
    public String getEventTask() {
        return fullCommand.split(" ", 2)[1].split(" /at ")[0];
    }

    /**
     * Method to parse event date. 
     *
     * @return the string of event task date.
     */
    public String getEventDate() {
        return fullCommand.split(" ", 2)[1].split(" /at ")[1].split(" ")[0];
    }

    /**
     * Method to check if input has any time details. 
     * 
     * @return boolean if there is details of time. 
     */
    public boolean isThereTime() {
        return fullCommand.split(" ", 2)[1].split(" /")[1].split(" ").length == 3;
    }

    /**
     * Method to parse deadline time. 
     *
     * @return the string of deadline task time.
     */
    public String getDeadlineTime() {
        return fullCommand.split(" ", 2)[1].split(" /by ")[1].split(" ")[1];
    }
    
    /**
     * Method to parse event time. 
     *
     * @return the string of event task time.
     */
    public String getEventTime() {
        return fullCommand.split(" ", 2)[1].split(" /at ")[1].split(" ")[1];
    }
}
