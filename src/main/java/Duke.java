import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.HashMap;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    private boolean isRunning;

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.runDuke();
    }

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
            storage = new Storage("data.txt", "data/");
            storage.loadFromSave(taskList.getTaskList(), taskFactory);
        } catch (DukeException exception) {
            // TODO:: issues loading from storage
        }

        // init parser
        HashMap<String, Command> commands = new HashMap<String, Command>();
        commands.put("bye", new ByeCommand("bye"));
        commands.put("list", new ListCommand("list"));
        commands.put("mark", new EditTaskMarkCommand("mark", true));
        commands.put("unmark", new EditTaskMarkCommand("unmark", false));
        commands.put("todo", new TodoCommand("todo"));
        commands.put("deadline", new DeadlineCommand("deadline"));
        commands.put("event", new EventCommand("event"));
        commands.put("delete", new DeleteCommand("delete"));

        parser = new Parser(commands);
    }

    /* Run Duke default behavior */
    public void runDuke() {
        ui.startGreeting();
        Scanner sc = new Scanner(System.in);

        while (this.isRunning) {
            String userResponse = sc.nextLine();

            try {
                Command command = parser.parse(userResponse);
                command.execute(userResponse, taskList, storage, ui);
                if (command.getKey().equals("bye")) {
                    this.isRunning = false;
                }
            } catch (DukeException error) {
                ui.printError(error.getMessage());
            }
        }

        sc.close();
    }
}
