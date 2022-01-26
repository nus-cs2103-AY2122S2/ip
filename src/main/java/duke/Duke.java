package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import duke.command.Command;
import duke.command.CommandParser;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private final CommandParser cmd = new CommandParser(new Scanner(System.in));
    private final Ui ui = new Ui();

    /**
     * Perform certain behaviours according to the command passed.
     *
     * @param action command from user
     * @throws DukeException general exception for invalid user command: invalid command, arguments, etc.
     */
    private void handleAction(Command action) throws DukeException {
        switch (action.getName()) {
        case "bye":
            ui.say("Bye! See you later!");
            try {
                storage.saveTasks(tasks);
            } catch (IOException e) {
                ui.error("Can't save tasks to file");
            }
            System.exit(0);
            break;

        case "list":
            ui.say("[*] Here are the tasks in your list:\n" +
                    tasks.toString());
            break;

        case "mark":
            // User input is 1-indexed, list uses 0-index
            int markIdx = Integer.parseInt(action.getArgs()) - 1;
            tasks.markTask(markIdx);
            ui.say("[*] Marked the following duke.task as done:\n" +
                    "\t" + tasks.getTask(markIdx).nameWithStatus());
            break;

        case "unmark":
            // User input is 1-indexed, list uses 0-index
            int unmarkIdx = Integer.parseInt(action.getArgs()) - 1;
            tasks.unmarkTask(unmarkIdx);
            ui.say("[*] Marked the following duke.task as not done:\n" +
                    "\t" + tasks.getTask(unmarkIdx).nameWithStatus());
            break;

        case "delete":
            // User input is 1-indexed, list uses 0-index
            int dltIdx = Integer.parseInt(action.getArgs()) - 1;
            Task deletedTask = tasks.removeTask(dltIdx);
            ui.say(String.format(
                    "[-] Removed this duke.task:\n" +
                            "\t%s\n" +
                            "Now you have %d tasks in the list.",
                    deletedTask.nameWithStatus(), tasks.size()));
            break;

        case "todo":
            String todoName = action.getArgs();
            Todo todo = new Todo(todoName);
            tasks.addTask(todo);
            ui.logNewTask(todo, tasks.size());
            break;

        case "deadline":
            String deadlineName = action.getArgs();
            String deadlineDate = action.getKwargs().get("by");
            Deadline deadline = new Deadline(deadlineName, deadlineDate);
            tasks.addTask(deadline);
            ui.logNewTask(deadline, tasks.size());
            break;

        case "event":
            String eventName = action.getArgs();
            String eventDate = action.getKwargs().get("at");
            Event event = new Event(eventName, eventDate);
            tasks.addTask(event);
            ui.logNewTask(event, tasks.size());
            break;

        default:
            throw new DukeException("I don't know what to do");

        }
    }

    /**
     * Construct a duke.Duke instance
     *
     * @param filename File to load and save Tasks
     */
    public Duke(String filename) {
        ui.printBanner();

        String introduction = "Hello, I'm Nikki\n" +
                "What can I do for you?";
        ui.say(introduction);

        try {
            storage = new Storage(filename);
            tasks = storage.loadTasks();
        } catch (IOException| DukeException e) {
            ui.warning("[!] Error reading file - initializing duke.task list as empty list");
            tasks = new TaskList();
        }

    }

    /**
     * Main driver method to run duke.Duke
     */
    public void run() {
        while (true) {
            try {
                Command action = cmd.readAndParse();
                handleAction(action);
            } catch (DukeException e) {
                ui.error("!( ｀Д´)ﾉ  " + e.getLocalizedMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
