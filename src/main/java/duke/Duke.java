package duke;

import duke.task.*;

import java.util.Scanner;

public class Duke {

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Duke(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        try {
            tasks = new TaskList(storage.ParseFile());
        } catch (DukeException e) {
            ui.sayMessage(e.getErrorMsg());
        }
    }

    public static void main(String[] args) {
        new Duke("save.txt").run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        ui.showWelcome();
        boolean isExit = true;
        while (isExit) {
            try {
                String input = scanner.nextLine();
                Parser.RESULT parseResult = parser.parseInput(input);
                switch (parseResult) {
                case BYE:
                    ui.sayGoodbye();
                    storage.OverwriteFile(tasks.getTasks());
                    isExit = false;
                    break;
                case LIST:
                    ui.listTasks(tasks);
                    break;
                case MARK: {
                    int index = parser.parseIndex(input);
                    if (index < tasks.getTasks().size()) {
                        tasks.markTaskDone(index);
                        ui.sayMessage("Nice! I've marked this task as done:\n" + tasks.getTask(index).toString());
                    } else {
                        throw new DukeException("No such task exists.");
                    }
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                }
                case UNMARK: {
                    int index = parser.parseIndex(input);
                    if (index < tasks.getTasks().size()) {
                        tasks.markTaskNotDone(index);
                        ui.sayMessage("Nice! I've marked this task as not done:\n" + tasks.getTask(index).toString());
                    } else {
                        throw new DukeException("No such task exists.");
                    }
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                }
                case DELETE:
                    int index = parser.parseIndex(input);
                    if (index < tasks.getTasks().size()) {
                        Task removedTask = tasks.removeTask(index);
                        ui.sayMessage("Noted. I've removed this task:\n" + removedTask.toString());
                    } else {
                        throw new DukeException("No such task exists.");
                    }
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                case TODO: {
                    Todo newTodo = new Todo(parser.parseTodo(input));
                    tasks.addTask(newTodo);
                    ui.sayMessage(
                            "Got it. I've added this task:\n" + newTodo + "\n" + "Now you have " + tasks.getTasks()
                                    .size() + " tasks in the list.");
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                }
                case DEADLINE:
                    String[] deadlineInput = parser.parseDeadline(input);
                    Deadline newDeadline = new Deadline(deadlineInput[0], deadlineInput[1]);
                    tasks.addTask(newDeadline);
                    ui.sayMessage(
                            "Got it. I've added this task:\n" + newDeadline + "\n" + "Now you have " + tasks.getTasks()
                                    .size() + " tasks in the list.");
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                case EVENT:
                    String[] eventInput = parser.parseEvent(input);
                    Event newEvent = new Event(eventInput[0], eventInput[1]);
                    tasks.addTask(newEvent);
                    ui.sayMessage(
                            "Got it. I've added this task:\n" + newEvent + "\n" + "Now you have " + tasks.getTasks()
                                    .size() + " tasks in the list.");
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                case ERROR:
                    throw new DukeException("Sorry :( I don't know what this means.");
                }
            } catch (DukeException e) {
                ui.sayMessage(e.getErrorMsg());
            }
        }
    }
}