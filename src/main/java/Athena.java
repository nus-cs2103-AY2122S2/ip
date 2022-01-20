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
                String taskString = taskList.addTodo(arguments.strip());
                sayTaskAddingLines(taskString);
                break;
            case "deadline":
                String[] deadlineArgs = arguments.split("/by", 2);
                taskString = taskList.addDeadline(deadlineArgs[0].strip(), deadlineArgs[1].strip());
                sayTaskAddingLines(taskString);
                break;
            case "event":
                String[] eventArgs = arguments.split("/at", 2);
                taskString = taskList.addEvent(eventArgs[0].strip(), eventArgs[1].strip());
                sayTaskAddingLines(taskString);
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
    private int readTaskNumberFromInput(String arguments) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(arguments);
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