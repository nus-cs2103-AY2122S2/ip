import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static final String TEXT_LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String TEXT_GREETING = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String TEXT_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String TEXT_DIVIDER = "____________________________________________________________";
    private static final String TEXT_ACKNOWLEDGE_LIST = "Here are the tasks in your list:";
    private static final String TEXT_ACKNOWLEDGE_MARK = "Nice! I've marked this task as done:";
    private static final String TEXT_ACKNOWLEDGE_UNMARK = "OK, I've marked this task as not done yet:";
    private static final String TEXT_ACKNOWLEDGE_DELETE = "Noted. I've removed this task:";
    private static final String TEXT_ACKNOWLEDGE_TASK = "Got it. I've added this task:";
    private static final String SAVE_DIR = "data";
    private static final String FILE_NAME = SAVE_DIR + "/duke.txt";

    private final List<Task> tasks = new ArrayList<>();
    private boolean shouldExit = false;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        greet();
        loadTasks();

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
                saveTasks();
            } catch (DukeException e) {
                printDivider();
                printTabbed(e.toString(), 1);
                printDivider();
                System.out.println();
            }
        }
    }

    private void processInput(CommandType commandType, Map<String, String> paramMap) {
        switch (commandType) {
        case EXIT:
            sayGoodbye();
            shouldExit = true;
            break;
        case LIST:
            listTasks();
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
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-M-d[ HHmm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
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

    private void saveTasks() {
        try {
            Path path = Paths.get(SAVE_DIR);
            Files.createDirectories(path);
            PrintWriter writer = new PrintWriter(FILE_NAME);

            for (Task task : tasks) {
                writer.println(task.toSaveData());
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        try {
            File file = new File(FILE_NAME);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineSplit = line.split(" \\| ");

                boolean isDone = lineSplit[1].equals("1");
                LocalDateTime dateTime;
                String description = lineSplit[2];

                switch (lineSplit[0]) {
                case "T":
                    tasks.add(new ToDo(description, isDone));
                    break;
                case "E":
                    dateTime = LocalDateTime.parse(lineSplit[3]);
                    Duration duration = Duration.parse(lineSplit[4]);
                    tasks.add(new Event(description, dateTime, duration, isDone));
                    break;
                case "D":
                    dateTime = LocalDateTime.parse(lineSplit[3]);
                    tasks.add(new Deadline(description, dateTime, isDone));
                    break;
                default:
                    throw new DukeException("Invalid save data");
                }
            }
        } catch (FileNotFoundException ignored) {
        }
    }

    private void markTask(int index) {
        tasks.get(index).setDone(true);
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_MARK, 1);
        printTabbed(tasks.get(index).toString(), 3);
        printDivider();
        System.out.println();
    }

    private void unmarkTask(int index) {
        tasks.get(index).setDone(false);
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_UNMARK, 1);
        printTabbed(tasks.get(index).toString(), 3);
        printDivider();
        System.out.println();
    }

    private void addTask(Task task) {
        tasks.add(task);
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_TASK, 1);
        printTabbed(task.toString(), 3);
        printTabbed("Now you have " + tasks.size() + " tasks in the list.", 1);
        printDivider();
        System.out.println();
    }

    private void deleteTask(int index) {
        Task deleted = tasks.get(index);
        tasks.remove(index);
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_DELETE, 1);
        printTabbed(deleted.toString(), 3);
        printTabbed("Now you have " + tasks.size() + " tasks in the list.", 1);
        printDivider();
        System.out.println();
    }

    private void listTasks() {
        printDivider();
        printTabbed(TEXT_ACKNOWLEDGE_LIST, 1);
        for (int i = 0; i < tasks.size(); i++) {
            String entry = (i + 1) + "." + tasks.get(i).toString();
            printTabbed(entry, 1);
        }

        printDivider();
        System.out.println();
    }

    private void greet() {
        printDivider();
        printTabbed(TEXT_LOGO, 1);
        System.out.println();
        printTabbed(TEXT_GREETING, 1);
        printDivider();
        System.out.println();
    }

    private void sayGoodbye() {
        printDivider();
        printTabbed(TEXT_GOODBYE, 1);
        printDivider();
        System.out.println();
    }

    private void printDivider() {
        printTabbed(TEXT_DIVIDER, 0);
    }

    private void printTabbed(String s, int padding) {
        String[] lines = s.split("\n");
        char[] whiteSpace = new char[padding];
        Arrays.fill(whiteSpace, ' ');

        for (String line : lines) {
            System.out.println('\t' + String.valueOf(whiteSpace) + line);
        }
    }
}
