import java.util.Scanner;

public class Duke {

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
        TaskList taskList = new TaskList();

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
            } else if (command.equals(ValidCommand.UNMARK.label)) {
                Task task = taskList.getTask(Integer.parseInt(details));
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task);
            } else {
                taskList.addTask(new Task(userInput));
                System.out.println("added: " + userInput);
            }
        }
    }
}
