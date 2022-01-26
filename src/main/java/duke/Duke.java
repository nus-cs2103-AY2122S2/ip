package duke;
import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String lines = "____________________________" +
            "            ________________________________";
    private String endline = "___________________________" +
            "            _________________________________\n";
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("No path exist");
            tasks = new TaskList();
        }

    }

    public void run() throws IOException {
        ui = new Ui();
        Scanner sc = new Scanner(System.in);
        while (true) {
            storage.saveFile(tasks);
            String input = sc.nextLine();
            String[] parts = input.split(" ");
            try {
                if (input.equals("bye")) {
                    ui.bye();
                    break;
                }
                if (input.equals("list")) {
                    ui.list(tasks);
                    continue;
                }
                if (parts[0].equals("mark")) {
                    ui.mark(tasks, parts);
                    continue;
                }
                if (parts[0].equals("unmark")) {
                    ui.unmark(tasks, parts);
                    continue;
                }
                if (parts[0].equals("todo")) {
                    ui.todo(tasks, parts, input);
                    continue;
                }
                if (parts[0].equals("deadline")) {
                    ui.event(tasks,parts,input);
                    continue;
                }
                if (parts[0].equals("event")) {
                    ui.event(tasks,parts,input);
                    continue;
                }
                if (parts[0].equals("delete")) {
                    ui.delete(tasks,parts);
                    continue;
                }
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

    }

    public static void main(String[] args) throws DukeException {
        try {
            new Duke("data/tasks.txt").run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
