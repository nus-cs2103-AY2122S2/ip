package Duke.main;

import Duke.task.TaskList;

import java.io.*;
import java.util.Scanner;

public class Duke {
    public Storage storage;
    public TaskList tasks;
    public Ui ui;

    /**
     * constructor of the Duck program
     * @param filePath path used to load the file
     * @throws IOException handles input output exceptions
     * @throws ClassNotFoundException handle class not found errors
     */
    public Duke(String filePath) throws ClassNotFoundException, IOException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * this method is used to run the Duke program
     */
    public void run() throws IOException, ClassNotFoundException {
        Ui.greeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            Parser p = new Parser(storage, tasks, ui, sc.nextLine());
            p.userCommand();
        }
    }

    /**
     * main method to drive the program
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Duke("duke_Data_Saved.txt").run();
    }
}
