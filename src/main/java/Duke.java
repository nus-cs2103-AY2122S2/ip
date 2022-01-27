import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner input = new Scanner(System.in);
        String inputText = "";

        while (!inputText.equals("bye")) {
            inputText = input.nextLine();

            ui.allTasks(inputText, tasks);
            storage.saveTasks(tasks.list);
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}