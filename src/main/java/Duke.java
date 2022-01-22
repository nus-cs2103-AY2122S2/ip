import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Duke class represents a Personal Assistant Chatbot that
 * helps a person to keep track of various things.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.printGreeting();
        String input = ui.getInput();

        while (!input.equals("bye")) {
            try {
                CommandType command = Parser.parseCommand(input);
                String[] args = Parser.parseInput(input, command);
                processCommand(command, args);
            } catch (Exception e) {
                ui.printMessage(e.toString());
            } finally {
                input = ui.getInput();
            }
        }

        try {
            storage.save(tasks);
        } catch (Exception e) {
            ui.printMessage(e.toString());
        } finally {
            ui.printGoodbye();
        }
    }

//    public void run() {
//        ui.printGreeting();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.getInput();
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.printMessage(e.getMessage());
//            }
//        }
//
//        try {
//            storage.save(tasks);
//        } catch (Exception e) {
//            ui.printMessage(e.toString());
//        } finally {
//            ui.printGoodbye();
//        }
//    }

    /**
     * Processes the command from the user.
     * @param command User command.
     * @param args Task arguments.
     */
    private void processCommand(CommandType command, String[] args) {
        switch (command) {
        case LIST:
            ui.printList(tasks.getAllTasks());
            break;
        case DELETE:
            deleteTask(args[0]);
            break;
        case MARK:
            toggleTaskStatus(args[0], CommandType.MARK);
            break;
        case UNMARK:
            toggleTaskStatus(args[0], CommandType.UNMARK);
            break;
        case DEADLINE:
            addTask(args, CommandType.DEADLINE);
            break;
        case EVENT:
            addTask(args, CommandType.EVENT);
            break;
        case TODO:
            addTask(args, CommandType.TODO);
        }
    }

    private void deleteTask(String input) {
        try {
            int index = Integer.parseInt(input);
            Task t = tasks.getTask(index);
            tasks.deleteTask(index);
            ui.printMessage("Meow! Task is removed!\n" + t + "\n" + "Number of tasks in list: " + tasks.getSize());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printMessage(ErrorMessage.MESSAGE_INVALID_TASK);
        }
    }

    private void toggleTaskStatus(String input, CommandType command) {
        try {
            int index = Integer.parseInt(input);
            if (command.equals(CommandType.MARK)) {
                tasks.getTask(index).markAsDone();
                ui.printMessage("Meow! Task is done!" + tasks.getTask(index) + "\n");
            } else {
                tasks.getTask(index).unmarkAsDone();
                ui.printMessage("Meow! Task is not done!" + tasks.getTask(index) + "\n");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printMessage(ErrorMessage.MESSAGE_INVALID_TASK);
        }
    }

    private void addTask(String[] args, CommandType command) {
        Task t;
        switch (command) {
        case DEADLINE:
            t = new Deadline(args[0], LocalDate.of(Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]), Integer.parseInt(args[3])));
            break;
        case EVENT:
            t = new Event(args[0],  LocalDate.of(Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]), Integer.parseInt(args[3])));
            break;
        default:
            t = new Todo(args[0]);
        }
        tasks.addTask(t);
        ui.printMessage("Meow! Task is added!\n" + t + "\n"
                + "Number of tasks in list: " + tasks.getSize());
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}