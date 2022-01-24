import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            String input = scanner.nextLine();
            String[] tokens = input.split(" ", 2);

            try {
                CommandType commandType = CommandType.fromString(tokens[0]);
                Map<String, String> paramMap = new HashMap<>();

                for (String param : commandType.getParams()) {
                    String regex = "(?<=/" + param + "\\s)([^/].*?)(?=\\s*/|$)";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(input);

                    if (matcher.find()) {
                        String arg = matcher.group();
                        paramMap.put(param, arg);
                    } else {
                        throw new DukeException("Missing parameter: " + param);
                    }
                }

                processInput(commandType, paramMap);
                storage.saveTasks(tasks);
            } catch (DukeException e) {
                ui.showError(e.toString());
            }
        }
    }

    private void processInput(CommandType commandType, Map<String, String> paramMap) {
        switch (commandType) {
        case EXIT:
            ui.sayGoodbye();
            shouldExit = true;
            break;
        case LIST:
            ui.listTasks(tasks);
            break;
        case MARK_TASK:
            markTask(getParamAsInt(paramMap, "id") - 1);
            break;
        case UNMARK_TASK:
            unmarkTask(getParamAsInt(paramMap, "id") - 1);
            break;
        case DELETE_TASK:
            deleteTask(getParamAsInt(paramMap, "id") - 1);
            break;
        case ADD_TODO:
            addTask(new ToDo(paramMap.get("desc")));
            break;
        case ADD_DEADLINE:
            addTask(new Deadline(paramMap.get("desc"), getParamAsDateTime(paramMap, "by")));
            break;
        case ADD_EVENT:
            addTask(new Event(paramMap.get("desc"), getParamAsDateTime(paramMap, "at"),
                    getParamAsDuration(paramMap, "dur")));
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
