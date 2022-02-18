package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * The Duke program implements a simple task bot with CRUD functionality.
 * The program can add three different types of tasks (todo, deadline,
 * event), mark tasks as done, and delete tasks.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 0.1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui = new Ui();

    public String doHandler(String command, String input) throws Exception {
        int i = Integer.parseInt(input.replaceAll("[^0-9]",
                "")) - 1;
        tasks.get(i).markComplete();
        storage.save(tasks);
        return ui.showCommandMessage(command, tasks);
    }

    public String undoHandler(String command, String input) throws Exception {
        int j = Integer.parseInt(input.replaceAll("[^0-9]",
                "")) - 1;
        tasks.get(j).markIncomplete();
        storage.save(tasks);
        return ui.showCommandMessage(command, tasks);
    }

    public String deleteHandler(String command, String input) throws Exception {
        int k = Integer.parseInt(input.replaceAll("[^0-9]",
                "")) - 1;
        tasks.remove(k);
        storage.save(tasks);
        return ui.showCommandMessage(command, tasks);
    }

    public String listHandler(String command) {
        return ui.showCommandMessage(command, tasks);
    }

    public String todoHandler(String command, String input) throws Exception {
        Todo t = new Todo(input);
        tasks.add(t);
        storage.save(tasks);
        return ui.showCommandMessage(command, tasks) + "\n" + t;
    }

    public String findHandler(String command, String input) {
        ui.showCommandMessage(command, tasks);
        return tasks.find(input).toString();
    }

    public String deadlineHandler(String command, String input) throws Exception {
        String datetime = input.replaceAll(".* by ", "");
        input = input.replaceAll(" by .*", "");
        Deadline d = new Deadline(input, datetime);
        tasks.add(d);
        storage.save(tasks);
        return ui.showCommandMessage(command, tasks) + "\n" + d;
    }

    public String eventHandler(String command, String input) throws Exception {
        String time = input.replaceAll(".* at ", "");
        input = input.replaceAll(" at .*", "");
        Event e = new Event(input, time);
        tasks.add(e);
        storage.save(tasks);
        return ui.showCommandMessage(command, tasks) + "\n" + e;
    }

    public String byeHandler() {
        return "EXIT";
    }

    public String defaultHandler(String command, String input) {
        return input.equals("") ? Ui.showEmptyMessage() :
                ui.showCommandMessage(command, tasks);
    }

    public String getResponse(String input) {
        try {
            String command = Parser.parse(input, tasks);
            input = Parser.handleInput(input);

            switch (command) {
            case "list":
                return listHandler(command);
            case "do":
                return doHandler(command, input);
            case "undo":
                return undoHandler(command, input);
            case "delete":
                return deleteHandler(command, input);
            case "todo":
                return todoHandler(command, input);
            case "find":
                return findHandler(command, input);
            case "deadline":
                return deadlineHandler(command, input);
            case "event":
                return eventHandler(command, input);
            case "bye":
                return byeHandler();
            default:
                return defaultHandler(command, input);
            }
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Constructor for Duke.
     *
     * Instantiates UI and storage, and loads Tasks from a file into tasks.
     * If there is an error with loading Tasks from the specified file, it
     * initializes tasks to bean empty TaskList.
     *
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(Ui.showLoadingError());
            tasks = new TaskList();
        }
    }
}
