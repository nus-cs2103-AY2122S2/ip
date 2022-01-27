import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Athena {
    private final TaskList taskList;
    private boolean isActive;
    private static final DateTimeFormatter inputFormatter =
            DateTimeFormatter.ofPattern("d/M/yyyy H:mm");

    public Athena() {
        this.taskList = new TaskList();
        this.isActive = true;
        sayText("Greetings! My name is Athena. What can I help you with?");
    }

    public void execute(String input) {
        if (!this.isActive()) {
            return;
        }

        // Separate out the command keyword from the other arguments given
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String arguments = "";
        if (splitInput.length > 1) {
            arguments = splitInput[1];
        }
        // Run the given command
        try {
            switch (command) {
            case "todo":
                String taskName = readTaskNameForTodo(arguments);
                String taskString = taskList.addTodo(taskName);
                sayTaskAddingLines(taskString);
                break;
            case "deadline":
                String[] taskNameAndDate = readTaskNameAndDateTime(arguments, "/by");
                LocalDateTime dueDate = LocalDateTime.parse(taskNameAndDate[1], inputFormatter);
                taskString = taskList.addDeadline(taskNameAndDate[0], dueDate);
                sayTaskAddingLines(taskString);
                break;
            case "event":
                taskNameAndDate = readTaskNameAndDateTime(arguments, "/at");
                LocalDateTime eventDate = LocalDateTime.parse(taskNameAndDate[1], inputFormatter);
                taskString = taskList.addEvent(taskNameAndDate[0], eventDate);
                sayTaskAddingLines(taskString);
                break;
            case "mark":
                int taskNumber = readTaskNumberFromInput(arguments);
                sayText("Alright, I've marked the following task as done:");
                System.out.println(taskList.markTaskAsDone(taskNumber));
                break;
            case "unmark":
                taskNumber = readTaskNumberFromInput(arguments);
                sayText("Alright, I've marked the following task as not done:");
                System.out.println(taskList.markTaskAsNotDone(taskNumber));
                break;
            case "delete":
                taskNumber = readTaskNumberFromInput(arguments);
                sayText("Alright, I've deleted the following task from the list.");
                System.out.println(taskList.deleteTask(taskNumber));
                sayNumberOfTasksInList();
                break;
            case "list":
                sayText("Here's the current list of tasks:");
                System.out.println(taskList);
                break;
            case "bye":
                shutdown();
                break;
            default:
                sayText("That's an invalid command. Please try again.");
                break;
            }
        } catch (AthenaInputException e) {
            sayText(e.getMessage());
        }
    }

    private int readTaskNumberFromInput(String input) throws AthenaInputException {
        int taskNumber = -1;
        try {
            taskNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new AthenaInputException(ErrorCode.MISSING_TASK_NUMBER);
        }
        if (taskNumber < 1 || taskNumber > this.taskList.getNumberOfTasks()) {
            throw new AthenaInputException(ErrorCode.INVALID_TASK_NUMBER);
        } else {
            return taskNumber;
        }
    }

    private String readTaskNameForTodo(String input) throws AthenaInputException {
        if (input.equals("")) {
            throw new AthenaInputException(ErrorCode.MISSING_TASK_NAME);
        }
        return input.strip();
    }

    private String[] readTaskNameAndDateTime(String input, String separator) throws AthenaInputException {
        if (input.equals("")) {
            throw new AthenaInputException(ErrorCode.MISSING_TASK_NAME);
        } else if (!input.contains(separator)) {
            throw new AthenaInputException(ErrorCode.MISSING_TASK_DATETIME);
        } else {
            // Split input into task name and datetime
            String[] taskNameAndDate = input.split(separator, 2);
            taskNameAndDate[0] = taskNameAndDate[0].strip();
            taskNameAndDate[1] = taskNameAndDate[1].strip();

            // Check that the required fields are present
            if (taskNameAndDate[0].equals("")) {
                throw new AthenaInputException(ErrorCode.MISSING_TASK_NAME);
            } else if (taskNameAndDate[1].equals("")) {
                throw new AthenaInputException(ErrorCode.MISSING_TASK_DATETIME);
            }
            return taskNameAndDate;
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    private void shutdown() {
        this.isActive = false;
        sayText("Farewell!");
    }

    private static void sayText(String text) {
        System.out.println("Athena: " + text);
    }

    private void sayTaskAddingLines(String taskString) {
        sayText("Okay, I've added this task to your list.");
        System.out.println(taskString);
        sayNumberOfTasksInList();
    }

    private void sayNumberOfTasksInList() {
        if (this.taskList.getNumberOfTasks() == 1) {
            sayText("Now you have 1 task in your list.");
        } else {
            sayText(String.format("Now you have %d tasks in your list.", this.taskList.getNumberOfTasks()));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Athena athena = new Athena();
        while (athena.isActive()) {
            // System.out.print("You: ");
            String rawInput = scanner.nextLine();
            athena.execute(rawInput);
        }
    }
}