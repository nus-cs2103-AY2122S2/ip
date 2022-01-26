import Exceptions.EmptyMessageException;
import Exceptions.WrongCommandException;
import Exceptions.WrongDateFormatException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static String border = "    ____________________________________________________________\n";
    static String spacing = "    ";
    static ListStorage myListStorage = new ListStorage();
    static Printer myPrinter = new Printer();
    static Disk myDisk;

    /*
     * Empty constructor for Duke
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
     * Loads existing ListStorage in Disk.
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

    public void dukeRunner() {
        myPrinter.printGreeting();
        while(true){
            try{
                parseCommand();
                break;
            } catch (WrongCommandException e) {
                myPrinter.printExceptions(e);
            }
        }
    }

    public static void parseCommand() throws WrongCommandException {

        Scanner myScanner = new Scanner(System.in);
        Commands commands = new Commands(myListStorage, myPrinter);

        while(myScanner.hasNextLine()) {
            String cmd = myScanner.nextLine();
            try{
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
}
