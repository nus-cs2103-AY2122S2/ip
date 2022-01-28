package jose;

import java.io.IOException;
import java.util.Scanner;
import jose.task.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        boolean isModified = false;
        boolean isRunning = true;

        ui.showGreeting();

        while (isRunning) {
            String input = scanner.nextLine();
            String[] taskInfo;
            Task tempTask;

            try {
                Parser.Command command = parser.parse(input);
                switch (command) {
                case BYE:
                    ui.showExitMessage();
                    isRunning = false;
                    break;
                case LIST:
                    ui.showList(tasks);
                    break;
                case MARK:
                    tempTask = tasks.getTask(parser.getIndex(input));
                    tempTask.mark();
                    isModified = true;
                    ui.showMarkMessage(tempTask);
                    break;
                case UNMARK:
                    tempTask = tasks.getTask(parser.getIndex(input));
                    tempTask.unmark();
                    isModified = true;
                    ui.showUnmarkMessage(tempTask);
                    break;
                case DELETE:
                    tempTask = tasks.getTask(parser.getIndex(input));
                    ui.showDeleteMessage(tempTask);
                    tasks.removeTask(tempTask);
                    isModified = true;
                    ui.showRemainingTasks(tasks);
                    break;
                case TODO:
                    taskInfo = input.split(" ", 2);
                    tempTask = new ToDo(taskInfo[1]);
                    tasks.addTask(tempTask);
                    isModified = true;
                    ui.showAddMessage(tempTask);
                    ui.showRemainingTasks(tasks);
                    break;
                case DEADLINE:
                    taskInfo = input.split(" ", 2)[1].split(" /by ");
                    tempTask = new Deadline(taskInfo[0], taskInfo[1]);
                    tasks.addTask(tempTask);
                    isModified = true;
                    ui.showAddMessage(tempTask);
                    ui.showRemainingTasks(tasks);
                    break;
                case EVENT:
                    taskInfo = input.split(" ", 2)[1].split(" /at ");
                    tempTask = new Event(taskInfo[0], taskInfo[1]);
                    tasks.addTask(tempTask);
                    isModified = true;
                    ui.showAddMessage(tempTask);
                    ui.showRemainingTasks(tasks);
                }

                if (isModified) {
                    storage.update(tasks);
                    isModified = false;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/db.txt").run();
    }
}
