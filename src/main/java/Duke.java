import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    private final List<Task> tasks = new ArrayList<>();
    private boolean shouldExit = false;

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        greet();

        while (!shouldExit) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ", 2);

            try {
                CommandType commandType = CommandType.fromString(tokens[0]);
                Map<String, String> paramMap = new HashMap<>();

                if (commandType.getArgs().length > 0 && tokens.length > 1) {
                    String regex = commandType.getRegex();
                    String paramsRaw = tokens[1];
                    String[] paramsSplit = (regex == null) ? new String[]{paramsRaw} : paramsRaw.split(regex);

                    for (int i = 0; i < paramsSplit.length; i++) {
                        paramMap.put(commandType.getArgs()[i], paramsSplit[i]);
                    }
                }

                processInput(commandType, paramMap);
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
            markTask(Integer.parseInt(paramMap.get("index")) - 1);
            break;
        case UNMARK_TASK:
            unmarkTask(Integer.parseInt(paramMap.get("index")) - 1);
            break;
        case DELETE_TASK:
            deleteTask(Integer.parseInt(paramMap.get("index")) - 1);
            break;
        case ADD_TODO:
            addTask(new ToDo(paramMap.get("description")));
            break;
        case ADD_DEADLINE:
            addTask(new Deadline(paramMap.get("description"), paramMap.get("by")));
            break;
        case ADD_EVENT:
            addTask(new Event(paramMap.get("description"), paramMap.get("at")));
            break;
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
