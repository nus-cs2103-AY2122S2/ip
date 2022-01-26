package connor;

import connor.exception.InvalidTaskFileException;
import connor.task.Task;
import connor.task.TaskList;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Connor {
    public static final String CURRENT_VERSION = "Version 1.0.0";
    public static final String LINE = "_".repeat(66);
    public static final String INDENT = " ".repeat(4);
    public static final String INPUT_HERE = ">>> ";

    private static final String TASK_FILEPATH = "data/connor.txt";

    private static final String ERROR_FILE_NOT_FOUND = "Error! Task file not found!";

    private static boolean isActive = true;
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    private Storage storage;
    private Ui ui;

    public Connor(String filePath) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    public void run() {
        ui.greetings();
        try {
            storage.loadTasks();
        } catch (FileNotFoundException e) {
            print(ERROR_FILE_NOT_FOUND);
            e.printStackTrace();
            return;
        } catch (IndexOutOfBoundsException | InvalidTaskFileException e) {
            print("ERROR: " + e.getMessage());
            print("Please fix the appropriate typo in the task file or clear it completely.");
            return;
        }
        TaskList.viewTasks();
        print(LINE);
        while (isActive) {
            System.out.print(INPUT_HERE);
            String input = sc.nextLine();
            print(input);
            print(LINE);
            Parser p = new Parser(input);
            p.parse();
            print(LINE);
            storage.updateFile();
        }
        sc.close();
    }

        private static void print(String s) {
        System.out.println(s);
    }

    public static void setActive(boolean b) {
        isActive = b;
    }

    public static void main(String[] args) {
        try {
            new Connor(TASK_FILEPATH).run();
        } catch (IOException e) {
            print(e.getMessage());
        }
    }

}
