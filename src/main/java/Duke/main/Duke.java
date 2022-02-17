package Duke.main;

import Duke.task.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;



public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor of the Duck program, data store in ip/duke_Saved.txt
     */
    public Duke() {
        storage = new Storage("duke_Saved.txt");
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(new DukeException("loading failed"));
        }
    }

    /**
     * Constructor of the Duck program
     * @param filePath path used to load the file
     * @throws IOException handles input output exceptions
     * @throws ClassNotFoundException handle class not found errors
     */
    public Duke(String filePath) throws ClassNotFoundException, IOException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(new DukeException("loading failed"));
        }
    }

    /**
     * this method is used to run the Duke program
     */
    public void run() throws IOException {
        Ui.greeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            Parser p = new Parser(storage, tasks, sc.nextLine());
            p.userCommand();
        }
    }

    /**
     * Give corresponding response for user inputs
     * @param input read user inputs
     * @return response by duke
     */
    public String getResponse(String input) {
        ByteArrayOutputStream outPuts = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outPuts);
        PrintStream old = System.out;
        System.setOut(ps);
        try {
            Parser p = new Parser(storage, tasks, input);
            p.userCommand();
        } catch (IOException e) {
            tasks = new TaskList();
        }
        System.out.flush();
        System.setOut(old);
        return outPuts.toString();
    }

    /**
     * Give greeting message
     * @return greeting by duke
     */
    public String getGreeting() {
        ByteArrayOutputStream outPuts = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outPuts);
        PrintStream old = System.out;
        System.setOut(ps);
        Ui.greeting();
        System.out.flush();
        System.setOut(old);
        return outPuts.toString();
    }


    /**
     * main method to drive the program
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Duke("duke_Saved.txt").run();
    }
}
