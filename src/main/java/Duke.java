import java.util.Scanner;

public class Duke {

    private static TaskList taskList;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        taskList = new TaskList();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] inputArr = userInput.split(" ", 2);
            String command = inputArr[0];
            String details = inputArr.length > 1 ? inputArr[1] : "";

            if (command.equals(ValidCommand.BYE.label)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals(ValidCommand.LIST.label)) {
                System.out.println("Here are the tasks in your list:");
                System.out.println(taskList);
            } else if (command.equals(ValidCommand.MARK.label)) {
                Task task = taskList.getTask(Integer.parseInt(details));
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
                System.out.println();
            } else if (command.equals(ValidCommand.UNMARK.label)) {
                Task task = taskList.getTask(Integer.parseInt(details));
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
                System.out.println();
            } else if (command.equals(ValidCommand.TODO.label)) {
                Task task = new Todo(details);
                addTaskHelper(task);
            } else if (command.equals(ValidCommand.DEADLINE.label)) {
                String[] deadlineInputs = details.split(" /by ");
                Task task = new Deadline(deadlineInputs[0], deadlineInputs[1]);
                addTaskHelper(task);
            } else if (command.equals(ValidCommand.EVENT.label)) {
                String[] eventInputs = details.split(" /at ");
                Task task = new Event(eventInputs[0], eventInputs[1]);
                addTaskHelper(task);
            } else {
                System.out.println("Unknown input");
            }
        }
    }

    private static void addTaskHelper(Task task) {
        taskList.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.");
        System.out.println();
    }
}
