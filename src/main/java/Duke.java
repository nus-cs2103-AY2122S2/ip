import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> taskList;
    private Scanner scanner;

    /**
     * Prints out the default greeting.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the response when successfully adding a task.
     *
     * @param task a new task that was added to the tasklist
     */
    public void printAddTaskSuccess(Task task) {
        System.out.println("____________________________________________________________");
        System.out.printf("Got it. I've added this task:%n%s%n", task);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        System.out.println("____________________________________________________________");
    }

    /**
     * Adds a TodoTask to the task list.
     *
     * @param input the content of a todo task
     */
    public void addTodoTask(String input) {
        TodoTask newTask = new TodoTask(input);
        taskList.add(newTask);
        printAddTaskSuccess(newTask);
    }

    /**
     * Adds a DeadlineTask to the task list.
     *
     * @param input the content of a deadline task
     * @param deadline the deadline of a deadline task
     */
    public void addDeadlineTask(String input, String deadline) {
        DeadlineTask newTask = new DeadlineTask(input, deadline);
        taskList.add(newTask);
        printAddTaskSuccess(newTask);
    }

    /**
     * Adds an EventTask to the task list.
     *
     * @param input the content of an event task
     * @param deadline the deadline of an event task
     */
    public void addEventTask(String input, String deadline) {
        EventTask newTask = new EventTask(input, deadline);
        taskList.add(newTask);
        printAddTaskSuccess(newTask);
    }

    /**
     * Complete a Task.
     *
     * @param taskIndex the index of a task to complete
     */
    public void completeTask(int taskIndex) {
        Task foundTask = taskList.get(taskIndex - 1);
        foundTask.markDone();
    }

    /**
     * Undo the completion of a task with the given index
     *
     * @param taskIndex the index of a task to undo completion
     */
    public void undoTask(int taskIndex) {
        Task foundTask = taskList.get(taskIndex - 1);
        foundTask.markUndone();
    }

    /**
     * List all the tasks in the task list.
     */
    public void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.printf("%d. %s%n", i, taskList.get(i - 1));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Deletes a task from the task list with the given index.
     *
     * @param index index of the task to be deleted
     */
    public void deleteTask(int index) {
        Task foundTask = taskList.get(index - 1);
        taskList.remove(index - 1);
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(foundTask);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        System.out.println("____________________________________________________________");

    }

    /**
     * Exits from the program.
     */
    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    /**
     * Starts the program.
     */
    public void start() {
        this.greet();
        this.taskList = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        String scannedInput = "";
        while (true) {
            try {
                scannedInput = scanner.nextLine();
                String[] input = scannedInput.split(" ", 2);
                String command = input[0];
                String arguments = input.length > 1 ? input[1] : "";

                if (!Command.isValidCommand(command)) {
                    throw new InvalidCommandException();
                }

                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    this.listTasks();
                } else if (command.equals("mark")) {
                    int index = Integer.parseInt(arguments);
                    if (index > taskList.size()) {
                        throw new InvalidParameterException("☹ OOPS!!! The index provided is invalid.");
                    }
                    completeTask(index);
                } else if (command.equals("unmark")) {
                    int index = Integer.parseInt(command);
                    undoTask(index);
                } else if (command.equals("todo")) {
                    if (arguments == "") {
                        throw new InvalidParameterException("☹ OOPS!!! The description of a task cannot be empty.");
                    }
                    this.addTodoTask(arguments);
                } else if (command.equals("deadline")) {
                    String[] argsArray = arguments.split(" /by ");
                    this.addDeadlineTask(argsArray[0], argsArray[1]);
                } else if (command.equals("event")) {
                    String[] argsArray = arguments.split(" /at ");
                    this.addEventTask(argsArray[0], argsArray[1]);
                } else if (command.equals("delete")) {
                    this.deleteTask(Integer.parseInt(arguments));
                }
            } catch (DukeException exception) {
                System.out.println("____________________________________________________________");
                System.out.println(exception.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
        this.exit();
    }

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
