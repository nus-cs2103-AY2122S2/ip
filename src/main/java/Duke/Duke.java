package Duke;

import Duke.Exceptions.EmptyMessageException;
import Duke.Exceptions.WrongCommandException;
import Duke.Exceptions.WrongDateFormatException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Driver class of Duke
 */
public class Duke {

    private static final ListStorage myListStorage = new ListStorage();
    private static final Printer myPrinter = new Printer();
    private static final UiPrinter myUiPrinter = new UiPrinter();
    private static Disk myDisk;
    /**
     * Empty contructor for Duke
     */
    public Duke() {
        myDisk = new Disk("src/main/java/data/savedTasks.txt", myListStorage);
        try {
            myDisk.loadFromDisk();
        } catch (IOException e) {
            myPrinter.printExceptions(e);
        }
    }
    /**
     * Constructor for Duke
     * Loads existing ListStorage in Disk
     * @param filePath filePath
     */
    public Duke(String filePath) {
        myDisk = new Disk(filePath, myListStorage);
        try {
            myDisk.loadFromDisk();
        } catch (IOException e) {
            myPrinter.printExceptions(e);
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/java/data/savedTasks.txt").dukeRunner();
    }

    /**
     * Runner method for Duke
     */
    public void dukeRunner() {
        myPrinter.printGreeting();
        while (true) {
            try {
                parseCommand();
                break;
            } catch (WrongCommandException e) {
                myPrinter.printExceptions(e);
            }
        }
    }

    /**
     * Method to parse commands entered by user
     *
     * @throws WrongCommandException if user enters invalid commands
     */
    public static void parseCommand() throws WrongCommandException {

        Scanner myScanner = new Scanner(System.in);
        Commands commands = new Commands(myListStorage, myPrinter);

        while (myScanner.hasNextLine()) {
            String cmd = myScanner.nextLine();
            try {
                if (cmd.equals("bye")) {
                    commands.cmdBye();
                    myDisk.saveToDisk();
                    break;
                } else if (cmd.contains("todo")) {
                    commands.cmdTodo(cmd);
                    myDisk.saveToDisk();
                } else if (cmd.contains("event")) {
                    commands.cmdEvent(cmd);
                    myDisk.saveToDisk();
                } else if (cmd.contains("deadline")) {
                    commands.cmdDeadline(cmd);
                    myDisk.saveToDisk();
                } else if (cmd.contains("list")) {
                    commands.cmdList();
                } else if (cmd.contains("find")) {
                    commands.cmdFind(cmd);
                } else {
                    int taskNumber = Character.getNumericValue(cmd.charAt(cmd.length() - 1));
                    if (cmd.contains("mark")) {
                        if (cmd.contains("unmark")) {
                            commands.cmdUnmark(taskNumber);
                            myDisk.saveToDisk();
                        } else {
                            commands.cmdMark(taskNumber);
                            myDisk.saveToDisk();
                        }
                    } else if (cmd.contains("delete")) {
                        commands.cmdDelete(taskNumber);
                        myDisk.saveToDisk();
                    } else {
                        throw new WrongCommandException("Invalid Command");
                    }
                }
            } catch (EmptyMessageException | WrongDateFormatException e) {
                myPrinter.printExceptions(e);
                //cmd = myScanner.nextLine();
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws EmptyMessageException, WrongDateFormatException {
        String result;
        int taskNumber;
        String[] cmds = Parser.parseCmdAndDes(input);
        Commands commands = new Commands(myListStorage, myPrinter);
        try {
            switch (cmds[0]) {
            case "hi":
                return myUiPrinter.printGreeting();
            case "todo":
                result = commands.cmdTodo(input);
                myDisk.saveToDisk();
                return result;
            case "deadline":
                result = commands.cmdDeadline(input);
                myDisk.saveToDisk();
                return result;
            case "event":
                result = commands.cmdEvent(input);
                myDisk.saveToDisk();
                return result;
            case "find":
                result = commands.cmdFind(input);
                return result;
            case "mark":
                taskNumber = Character.getNumericValue(input.charAt(input.length() - 1));
                result = commands.cmdMark(taskNumber);
                myDisk.saveToDisk();
                return result;
            case "unmark":
                taskNumber = Character.getNumericValue(input.charAt(input.length() - 1));
                result = commands.cmdUnmark(taskNumber);
                myDisk.saveToDisk();
                return result;
            case "list":
                result = commands.cmdList();
                return result;
            case "delete":
                taskNumber = Character.getNumericValue(input.charAt(input.length() - 1));
                result = commands.cmdDelete(taskNumber);
                myDisk.saveToDisk();
                return result;
            case "bye":
                result = commands.cmdBye();
                return result;
            default:
                return "That is not in my defined features! Try again please!";
            }
        } catch (EmptyMessageException | WrongDateFormatException e) {
            myPrinter.printExceptions(e);
            return myUiPrinter.printExceptions(e);
        }
    }
}
