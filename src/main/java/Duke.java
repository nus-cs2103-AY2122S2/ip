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
        System.out.println("What can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        taskList = new TaskList();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            String[] inputArr = userInput.split(" ", 2);
            String command = inputArr[0];
            String details = inputArr.length > 1 ? inputArr[1] : "";
            try {
                if (command.equals(ValidCommand.BYE.label)) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals(ValidCommand.LIST.label)) {
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(taskList);
                } else if (command.equals(ValidCommand.MARK.label)) {
                    Duke.toggleTaskDone(ValidCommand.MARK, details);
                } else if (command.equals(ValidCommand.UNMARK.label)) {
                    Duke.toggleTaskDone(ValidCommand.UNMARK, details);
                } else if (command.equals(ValidCommand.TODO.label)) {
                    Duke.addTodo(details);
                } else if (command.equals(ValidCommand.DEADLINE.label)) {
                    Duke.addDeadline(details);
                } else if (command.equals(ValidCommand.EVENT.label)) {
                    Duke.addEvent(details);
                } else if (command.equals(ValidCommand.DELETE.label)) {
                    Duke.deleteTask(details);
                } else {
                    throw new IllegalArgumentException(
                            String.format("Sorry, the command '%s' is not supported.", command));
                }
            } catch (IllegalArgumentException | DukeException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private static int convertIndex(String indexString) throws DukeException {
        if (indexString.strip().equals("")) {
            throw new DukeException("Please specify a task.");
        }
        int index;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new DukeException("Please specify a task using its index in the task list.");
        }
        if (index < 1 || index > taskList.getLength()) {
            throw new DukeException("Please specify a valid task index.");
        }
        return index;
    }

    private static void toggleTaskDone(ValidCommand cmd, String indexString) throws DukeException {
        int index = Duke.convertIndex(indexString);
        Task task = Duke.taskList.getTask(index);
        if (cmd == ValidCommand.MARK) {
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
        System.out.println();
    }

    private static void addTaskHelper(Task task) {
        Duke.taskList.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.\n");
    }

    private static void addTodo(String details) throws DukeException {
        if (details.strip().equals("")) {
            throw new DukeException("Please enter a description for the todo task.");
        }
        Task task = new Todo(details);
        addTaskHelper(task);
    }

    private static void addDeadline(String details) throws DukeException {
        String[] deadlineInputs = details.split(" /by ", 2);
        if (deadlineInputs.length == 1 || deadlineInputs[1].strip().equals("")
                || deadlineInputs[0].strip().equals("")) {
            throw new DukeException("Please specify a deadline task as 'deadline [description] /by [date]'.");
        }
        Task task = new Deadline(deadlineInputs[0], deadlineInputs[1]);
        addTaskHelper(task);
    }

    private static void addEvent(String details) throws DukeException {
        String[] eventInputs = details.split(" /at ", 2);
        if (eventInputs.length == 1 || eventInputs[1].strip().equals("")
                || eventInputs[0].strip().equals("")) {
            throw new DukeException("Please specify an event task as 'event [description] /at [date]'.");
        }
        Task task = new Event(eventInputs[0], eventInputs[1]);
        addTaskHelper(task);
    }

    private static void deleteTask(String indexString) throws DukeException {
        int index = Duke.convertIndex(indexString);
        Task task = Duke.taskList.getTask(index);
        Duke.taskList.deleteTask(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.getLength() + " tasks in the list.\n");
    }
}
