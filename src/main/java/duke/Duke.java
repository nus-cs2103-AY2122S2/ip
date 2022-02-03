package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EditTaskMarkCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Duke chatbot behavior and data.
 */
public class Duke {
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    private boolean isRunning;
    private Storage storage;

    /**
     * Duke constructor. Initializes values.
     */
    public Duke() {
        taskList = new TaskList();
        ui = new Ui();
        isRunning = true;

        // init storage
        Function<String, Task> taskFactory = (String info) -> {
            Task newTask = null;
            char type = info.charAt(0);
            if (type == 'T') {
                newTask = new Todo();
            } else if (type == 'E') {
                newTask = new Event();
            } else if (type == 'D') {
                newTask = new Deadline();
            }

            return newTask;
        };

        try {
            storage = new Storage("data.txt", "./data/");
            storage.loadFromSave(taskList.getTaskList(), taskFactory);
        } catch (DukeException exception) {
            // TODO:: issues loading from storage
            System.out.println(exception.getMessage());
        }

        // init parser
        HashMap<String, Command> commands = new HashMap<String, Command>();
        commands.put("bye", new ByeCommand("bye"));
        commands.put("find", new FindCommand("find"));
        commands.put("list", new ListCommand("list"));
        commands.put("mark", new EditTaskMarkCommand("mark", true));
        commands.put("unmark", new EditTaskMarkCommand("unmark", false));
        commands.put("todo", new TodoCommand("todo"));
        commands.put("deadline", new DeadlineCommand("deadline"));
        commands.put("event", new EventCommand("event"));
        commands.put("delete", new DeleteCommand("delete"));

        parser = new Parser(commands);
    }
    
    /**
     * Gets Duke response from an input.
     *
     * @param input User input.
     * @return Duke response.
     */
    public String getResponse(String input) {
        String response = "";

        try {
            Command command = parser.parse(input);
            response = command.execute(input, taskList, storage);
        } catch(DukeException error) {
            return error.getMessage();
        }

        return response;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runDuke();
    }

    /**
     * Runs duke logic and behavior.
     */
    public void runDuke() {
        ui.startGreeting();
        Scanner sc = new Scanner(System.in);

        while (this.isRunning) {
            String userResponse = sc.nextLine();

            try {
                Command command = parser.parse(userResponse);
                String response = command.execute(userResponse, taskList, storage);
                if (command.getKey().equals("bye")) {
                    this.isRunning = false;
                }

                ui.printResponse(response);
            } catch (DukeException error) {
                ui.printError(error.getMessage());
            }
        }

        sc.close();
    }
}
