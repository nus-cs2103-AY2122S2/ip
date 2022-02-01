package Cleese;

import java.util.Scanner;
import java.io.File;

public class Cleese {
    private static TaskList taskList;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;

    public static void main(String[] args) {
        String filePath = "./src/TaskListDB.txt";
        storage = new Storage(filePath);
        taskList = new TaskList();
        ui = new Ui();
        parser = new Parser();
        // Read inputs from file
        try {
            storage.readFromFile(taskList);
        } catch (Exception E) {
            File fileName = new File("./src/TaskListDB.txt");
        }
        // Print welcome message
        ui.printWelcomeMessage();
        ui.printLine();
        // Scanner object to detect input from CLI
        Scanner scanner = new Scanner(System.in);
        //// Loop to keep getting input until bye
        String response = "default";
        while (!response.equals("bye")) {
            try {
                response = parser.handleCommand(scanner.nextLine(), taskList, ui, storage);
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
