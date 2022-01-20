import java.util.Scanner;

public class Athena {
    private final TaskList taskList;
    private boolean isActive;

    public Athena() {
        this.taskList = new TaskList();
        this.isActive = true;
        sayText("Greetings! My name is Athena. What can I help you with?");
    }

    public void execute(String input) {
        if (!this.isActive()) {
            return;
        }

        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0];
        String arguments = "";
        if (splitInput.length > 1) {
            arguments = splitInput[1];
        }
        switch (command) {
            case "todo":
                String taskName = readTaskNameForTodo(arguments);
                if (!taskName.equals("")) {
                    String taskString = taskList.addTodo(taskName);
                    sayTaskAddingLines(taskString);
                }
                break;
            case "deadline":
                String[] taskNameAndDate = readTaskNameAndDate(arguments, "/by");
                if (taskNameAndDate.length != 0) {
                    String taskString = taskList.addDeadline(taskNameAndDate[0], taskNameAndDate[1]);
                    sayTaskAddingLines(taskString);
                }
                break;
            case "event":
                taskNameAndDate = readTaskNameAndDate(arguments, "/at");
                if (taskNameAndDate.length != 0) {
                    String taskString = taskList.addEvent(taskNameAndDate[0], taskNameAndDate[1]);
                    sayTaskAddingLines(taskString);
                }
                break;
            case "mark":
                int taskNumber = readTaskNumberFromInput(arguments);
                if (taskNumber != -1) {
                    sayText("Alright, I've marked the following task as done:");
                    System.out.println(taskList.markTaskAsDone(taskNumber));
                }
                break;
            case "unmark":
                taskNumber = readTaskNumberFromInput(arguments);
                if (taskNumber != -1) {
                    sayText("Alright, I've marked the following task as not done:");
                    System.out.println(taskList.markTaskAsNotDone(taskNumber));
                }
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
    }

    // Returns - 1 if invalid.
    private int readTaskNumberFromInput(String input) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            sayText("Error. I need a valid task number. Please try again.");
            return -1;
        }
        if (taskNumber < 1 || taskNumber > this.taskList.getNumberOfTasks()) {
            sayText("Error. The given task number does not exist. Please try again.");
            return -1;
        }
        return taskNumber;
    }

    // Returns "" if invalid.
    private String readTaskNameForTodo(String input) {
        if (input.equals("")) {
            sayText("Error. Please provide a task name.");
            return "";
        }
        return input.strip();
    }

    // Returns empty String array if invalid.
    private String[] readTaskNameAndDate(String input, String separator) {
        String missingTaskNameMsg = "Error. Please provide a task name.";
        String missingDateTimeMsg = "Error. Please provide a date and/or time.";

        if (input.equals("")) {
            sayText(missingTaskNameMsg);
            return new String[0];
        } else if (!input.contains(separator)) {
            sayText(missingDateTimeMsg);
            return new String[0];
        }
        String[] taskNameAndDate = input.split(separator, 2);
        taskNameAndDate[0] = taskNameAndDate[0].strip();
        taskNameAndDate[1] = taskNameAndDate[1].strip();
        if (taskNameAndDate[0].equals("")) {
            sayText(missingTaskNameMsg);
            return new String[0];
        } else if (taskNameAndDate[1].equals("")) {
            sayText(missingDateTimeMsg);
            return new String[0];
        }
        return taskNameAndDate;
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

    private void sayTaskAddingLines(String taskName) {
        sayText("Okay, I've added this task to your list.");
        System.out.println(taskName);
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