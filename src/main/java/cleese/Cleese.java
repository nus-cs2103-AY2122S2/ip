package cleese;

import task.TasksList;
import exceptions.NoDescException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Cleese {
    private static Ui ui;
    private static Parser parser;
    private static Storage storage;
    private static TasksList tasksList;

    public static void main(String[] args) {
        String filePath = "./src/TaskListDB.txt";
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        tasksList = new TasksList();
        // Read inputs from file
        try {
            storage.readFromFile(tasksList);
        } catch (FileNotFoundException e) {
            File fileName = new File("./src/TaskListDB.txt");
        }
        // Print welcome message
        ui.printWelcomeMessage();
        ui.printLine();
        // Scanner object to detect input from CLI
        Scanner scanner = new Scanner(System.in);
        // Loop to keep getting input until bye
        String response = "default";
        while (!response.equals("bye")) {
            try {
                response = parser.handleCommand(scanner.nextLine(), tasksList, ui, storage);
            } catch(NoDescException error) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                ui.printLine();
            } catch(Exception error) {
                System.out.println("☹ OOPS!!! I'm sorry but I don't know what that means :-(");
                ui.printLine();
            }
        }
    }
}
