package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Task;

public class Duke {
    private final Ui ui;
    private TasksList taskslist;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.taskslist = new TasksList();
        this.storage = new Storage();
    }

    public void run() {
        this.ui.initialGreet();

        List<Task> tasks = new ArrayList<Task>();

        Scanner sc = new Scanner(System.in);
        List<String> commands = List.of("list", "bye", "mark", "unmark", "delete", "todo", "event", "deadline", "save");

        try {
            String response = taskslist.importStorageStrings(storage.importData());
            ui.print(response);
        } catch (FileNotFoundException e) {
            ui.print(e.getMessage());
        } catch (DukeException e) {
            ui.print(e.getMessage());
        }

        while (true) {
            try {
                String[] instruction = sc.nextLine().split(" ");
                if (instruction.length == 0 || !commands.contains(instruction[0])) {
                    throw new InvalidCommandException();
                }

                if (instruction[0].equals("bye")) {
                    ui.finalBye();
                    return;

                } else if (instruction[0].equals("list")) {
                    String response = taskslist.list();
                    ui.print(response.toString());

                } else if (instruction[0].equals("mark")) {
                    int taskNum = Integer.parseInt(instruction[1]);
                    String response = taskslist.mark(taskNum);
                    ui.print(response);

                } else if (instruction[0].equals("unmark")) {
                    int taskNum = Integer.parseInt(instruction[1]);
                    String response = taskslist.unmark(taskNum);
                    ui.print(response);

                } else if (instruction[0].equals("delete")) {
                    int taskNum = Integer.parseInt(instruction[1]);
                    String response = taskslist.deleteTask(taskNum);
                    ui.print(response);

                } else if (instruction[0].equals("todo") || instruction[0].equals("event") || instruction[0].equals("deadline")) {
                    String response = taskslist.addTask(Arrays.asList(instruction));
                    ui.print(response);

                } else if (instruction[0].equals("save")) {
                    //StringBuilder response = new StringBuilder("The following tasks will be saved: \n" +
                    //        taskslist.list());
                    String response = storage.exportData(taskslist.toStorageStrings(), taskslist.list());
                    ui.print(response);
                }
            }
            catch (DukeException e){
                ui.print(e.getMessage());
            } catch (IOException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
