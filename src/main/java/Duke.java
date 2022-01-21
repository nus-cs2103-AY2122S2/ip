import Exceptions.EmptyMessageException;
import Exceptions.WrongCommandException;

import java.util.Scanner;

public class Duke {
    static String border = "    ____________________________________________________________\n";
    static String spacing = "    ";
    static ListStorage myListStorage = new ListStorage();
    static Printer myPrinter = new Printer();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println(border + spacing +
                "Hello! I'm Duke\n" + spacing +
                "What can I do for you?\n" +
                border);
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
                    break;
                } else if (cmd.contains("todo")) {
                    commands.cmdTodo(cmd);
                } else if (cmd.contains("event")) {
                    commands.cmdEvent(cmd);
                } else if (cmd.contains("deadline")) {
                    commands.cmdDeadline(cmd);
                } else if (cmd.contains("list")) {
                    commands.cmdList();
                } else {
                    int taskNumber = Character.getNumericValue(cmd.charAt(cmd.length() - 1));
                    if (cmd.contains("mark")) {
                        if (cmd.contains("unmark")) {
                            commands.cmdUnmark(taskNumber);
                        } else {
                            commands.cmdMark(taskNumber);
                        }
                    } else if (cmd.contains("delete")) {
                        commands.cmdDelete(taskNumber);
                    } else {
                        throw new WrongCommandException("Invalid Command");
                    }
                }
            } catch (EmptyMessageException e) {
                myPrinter.printExceptions(e);
                //cmd = myScanner.nextLine();
            }
        }
    }
}
