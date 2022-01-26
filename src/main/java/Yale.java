import yale.task.Deadline;
import yale.task.TaskList;
import yale.task.Event;
import yale.task.ToDo;
import java.time.DateTimeException;

import java.io.IOException;
import java.util.Scanner;

public class Yale {
    public static void main(String[] args) {
        String logo = "-----YALE-----";
        System.out.println("Allow me to introduce myself\n" + logo);
        System.out.println("The name's Yale.\n" );
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        String fileData = FileRead.loadFileContents("data/yale.txt");
        list.importIn(fileData);
        Parser parser = new Parser();
        while (true) {
            String command = receiveInput(scanner);
            parser.performAction(command, list);
            writeActionTo("data/yale.txt", list);
            if (checkExit(command)) {
                break;
            }
        }
    }

    /**
     * Method to receive input from the scanner and
     * returns that input in a String format
     * @param scanner
     * @return Input
     */
    public static String receiveInput(Scanner scanner) {
        System.out.println("\nEnter command below:");
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Method to check if user input
     * is equal to "bye"
     * @param input
     * @return
     */
    public static boolean checkExit(String input) {
        return input.equals("bye");
    }

    /**
     * Writes String from list into specified file
     * @param filePath
     * @param list
     */
    public static void writeActionTo(String filePath, TaskList list) {
        String file2 = filePath;
        try {
            FileWrite.writeToFile(file2, list.exportOut());
        }
        catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}

