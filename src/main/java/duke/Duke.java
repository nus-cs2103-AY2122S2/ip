package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Map;
import java.util.Scanner;

public class Duke {
    private final Ui ui = new Ui();
    private final TaskList tasks = new TaskList();
    private final Storage storage = new Storage();
    private boolean shouldExit = false;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greet();
        storage.loadTasks(tasks);

        while (!shouldExit) {
            try {
                String input = scanner.nextLine();
                Command command = Parser.parse(input);
                executeCommand(command);
                storage.saveTasks(tasks);
            } catch (DukeException e) {
                ui.showError(e.toString());
            }
        }
    }

    private void executeCommand(Command command) {
        Map<String, String> params = command.getParams();

        switch (command.getType()) {
        case EXIT:
            ui.sayGoodbye();
            shouldExit = true;
            break;
        case LIST:
            ui.listTasks(tasks);
            break;
        case MARK_TASK:
            markTask(getParamAsInt(params, "id") - 1);
            break;
        case UNMARK_TASK:
            unmarkTask(getParamAsInt(params, "id") - 1);
            break;
        case DELETE_TASK:
            deleteTask(getParamAsInt(params, "id") - 1);
            break;
        case ADD_TODO:
            addTask(new ToDo(params.get("desc")));
            break;
        case ADD_DEADLINE:
            addTask(new Deadline(params.get("desc"), getParamAsDateTime(params, "by")));
            break;
        case ADD_EVENT:
            addTask(new Event(params.get("desc"), getParamAsDateTime(params, "at"), getParamAsDuration(params, "dur")));
            break;
        }
    }

    private int getParamAsInt(Map<String, String> map, String param) {
        String strVal = map.get(param);

        try {
            return Integer.parseInt(strVal);
        } catch (NumberFormatException e) {
            throw new DukeException(strVal + " is not an integer");
        }
    }

    private LocalDateTime getParamAsDateTime(Map<String, String> map, String param) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-M-d[ HHmm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
        String strVal = map.get(param);

        try {
            return LocalDateTime.parse(strVal, formatter);

        } catch (DateTimeParseException e) {
            throw new DukeException(strVal + " is not a valid date. Example: 2022-3-15 1630");
        }
    }

    private Duration getParamAsDuration(Map<String, String> map, String param) {
        String strVal = map.get(param);

        try {
            return Duration.parse("PT" + strVal);
        } catch (DateTimeParseException e) {
            throw new DukeException(strVal + " is not a valid duration. Example: 1h5m");
        }
    }

    private void markTask(int index) {
        tasks.mark(index);
        ui.acknowledgeMark(tasks.get(index));
    }

    private void unmarkTask(int index) {
        tasks.unmark(index);
        ui.acknowledgeUnmark(tasks.get(index));
    }

    private void addTask(Task task) {
        tasks.add(task);
        ui.acknowledgeAdd(task, tasks.size());
    }

    private void deleteTask(int index) {
        Task deleted = tasks.get(index);
        tasks.remove(index);
        ui.acknowledgeDelete(deleted, tasks.size());
    }
}
