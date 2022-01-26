import yale.task.Deadline;
import yale.task.TaskList;
import yale.task.Event;
import yale.task.ToDo;
import java.time.DateTimeException;

import java.io.IOException;
import java.util.Scanner;

public class Yale {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.welcomePrompt();
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        String fileData = FileRead.loadFileContents("data/yale.txt");
        list.importIn(fileData);
        Parser parser = new Parser();
        while (true) {
            String command = ui.receiveInput(scanner);
            parser.performAction(command, list);
            writeActionTo("data/yale.txt", list);
            if (checkExit(command)) {
                break;
            }
        }
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

