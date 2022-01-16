import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String GREET_MESSAGE
            = "Roarrr.... I'm Burning Charizard, tasked to burnnn down your tasks.\n" +
            "Which task shall we burn today?";
    private static final String BYE_MESSAGE = "Roarrr....Let's burn more tasks next time!";
    private static final int BORDER_LENGTH = 80;
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
        tasks = new ArrayList<Task>();
        greet();
        Scanner sc = new Scanner(System.in);
        System.out.println("What should Charizard do next?");
        String input = sc.nextLine();
        while (input.compareToIgnoreCase("bye") != 0) {
            processInput(input);
            System.out.println("What should Charizard do next?");
            input = sc.nextLine();
        }
        exit();
    }

    public static void show(String message) {
        drawBorder(BORDER_LENGTH);
        String[] lines = message.split("\n");
        for (String line: lines) {
            System.out.println("    " + line);
        }
        drawBorder(BORDER_LENGTH);
        System.out.println();
    }

    public static void processInput(String input) {
        String message = "";
        try {
            if (input.equalsIgnoreCase("list")) {
                message = "Charizard's burning wish list:\n";
                for (int i = 0; i < tasks.size(); i++) {
                    message += String.format("%d. %s", i + 1, tasks.get(i).toString());
                    if (i < tasks.size() - 1) {
                        message += "\n";
                    }
                }
            } else if (input.startsWith("mark")) {
                String inputWithoutCommand = input.replaceFirst("mark", "").trim();
                message = processMark(inputWithoutCommand, true);
            } else if (input.startsWith("unmark")) {
                String inputWithoutCommand = input.replaceFirst("unmark", "").trim();
                message = processMark(inputWithoutCommand, false);
            } else if (input.startsWith("todo")) {
                String description = input.replaceFirst("todo", "").trim();
                message = processTodo(description);
            } else if (input.startsWith("deadline")) {
                String inputWithoutCommand = input.replaceFirst("deadline", "").trim();
                message = processDeadline(inputWithoutCommand);
            } else if (input.startsWith("event")) {
                String inputWithoutCommand = input.replaceFirst("event", "").trim();
                message = processEvent(inputWithoutCommand);

            } else {
                throw new DukeException("Charizard does not know this move. Try a different command.");
            }
        } catch (DukeException e) {
            message = "OOPS! " + e.getMessage();
        }
        show(message);
    }

    public static String processMark(String input, boolean isDone) throws DukeException {
        try {
            int taskId = Integer.parseInt(input) - 1;
            tasks.get(taskId).mark(isDone);
            String message = "";
            if (isDone) {
                message = String.format("Charizard breathe out fire and burned the task.");
            } else {
                message = String.format("Oh no! The task was not burnt completely!");
            }
            message += String.format("\n  %s", tasks.get(taskId).toString());
            return message;
        } catch (NumberFormatException e) {
            throw new DukeException("Charizard's can only remember numbers. Try specifying a task number."
                + "\n(Use \"list\" command to see the tasks and their corresponding task number).");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number does not exist. Charizard is confused..");
        }
    }

    public static String processTodo(String input) throws DukeException {
        if (input.matches("\\s*")) {
            throw new DukeException("Please specify the name of new task to be burnt");
        }
        ToDo newTodo = new ToDo(input);
        tasks.add(newTodo);
        String message = String.format("Charizard is ready to burn task:\n  %s\nThere are %d tasks in the burning list.",
                newTodo.toString(), tasks.size());
        return message;
    }

    public static String processDeadline(String input) throws DukeException {
        String[] splitInput = input.split("/by");
        if (splitInput.length < 2 || splitInput[0].matches("\\s*") || splitInput[1].matches("\\s*")) {
            throw new DukeException("Charizard insists of knowing both name and deadline of the new task."
                + "\nTry using \"deadline <task_name> /by <deadline>\".");
        } else if (splitInput.length > 2) {
            throw new DukeException("Charizard can only remember one deadline per task.");
        }
        String description = splitInput[0].trim();
        String deadline = splitInput[1].trim();
        Deadline newDeadline = new Deadline(description, deadline);
        tasks.add(newDeadline);
        String message = String.format("Charizard is ready to burn task:\n  %s\nThere are %d tasks in the burning list.",
                newDeadline.toString(), tasks.size());
        return message;
    }

    public static String processEvent(String input) throws DukeException {
        String[] splitInput = input.split("/at");
        if (splitInput.length < 2) {
            throw new DukeException("Charizard insists of knowing both name and time of the new task."
                    + "\nTry using \"event <task_name> /by <event_time>\".");
        } else if (splitInput.length > 2) {
            throw new DukeException("Charizard can only remember one time per task.");
        }
        String description = splitInput[0].trim();
        String time = splitInput[1].trim();
        Event newEvent = new Event(description, time);
        tasks.add(newEvent);
        String message = String.format("Charizard is ready to burn task:\n  %s\nThere are %d tasks in the burning list.",
                newEvent.toString(), tasks.size());
        return message;
    }

    public static void exit() {
        drawBorder(BORDER_LENGTH);
        System.out.println(BYE_MESSAGE);
        drawBorder(BORDER_LENGTH);
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        drawBorder(BORDER_LENGTH);
        System.out.println(GREET_MESSAGE);
        drawBorder(BORDER_LENGTH);
        System.out.println();
    }

    private static void drawBorder(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        };
        System.out.println("");
    }
}
