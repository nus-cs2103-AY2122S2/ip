import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> taskList;
    private Scanner scanner;

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

    public void printAddTaskSuccess(Task task) {
        System.out.println("____________________________________________________________");
        System.out.printf("Got it. I've added this task:%n%s%n", task);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        System.out.println("____________________________________________________________");
    }

    public void addTodoTask(String input) {
        TodoTask newTask = new TodoTask(input);
        taskList.add(newTask);
        printAddTaskSuccess(newTask);
    }

    public void addDeadlineTask(String input, String deadline) {
        DeadlineTask newTask = new DeadlineTask(input, deadline);
        taskList.add(newTask);
        printAddTaskSuccess(newTask);
    }

    public void addEventTask(String input, String deadline) {
        EventTask newTask = new EventTask(input, deadline);
        taskList.add(newTask);
        printAddTaskSuccess(newTask);
    }

    public void completeTask(int taskIndex) {
        Task foundTask = taskList.get(taskIndex - 1);
        foundTask.markDone();
    }

    public void undoTask(int taskIndex) {
        Task foundTask = taskList.get(taskIndex - 1);
        foundTask.markUndone();
    }

    public void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.printf("%d. %s%n", i, taskList.get(i - 1));
        }
        System.out.println("____________________________________________________________");
    }

    public void deleteTask(int index) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        Task foundTask = taskList.get(index - 1);
        System.out.println(foundTask);
        taskList.remove(index - 1);
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
        System.out.println("____________________________________________________________");

    }

    public void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public void start() {
        this.greet();
        this.taskList = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        String input = "";
        while (true) {
            try {
                input = scanner.nextLine();
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    this.listTasks();
                } else if (input.startsWith("mark")) {
                    String[] string = input.split(" ");
                    int index = Integer.parseInt(string[1]);
                    if (index > taskList.size()) {
                        throw new InvalidParameterException("☹ OOPS!!! The index provided is invalid.");
                    }
                    completeTask(index);
                } else if (input.startsWith("unmark")) {
                    String[] string = input.split(" ");
                    int index = Integer.parseInt(string[1]);
                    undoTask(index);
                } else if (input.startsWith("todo")) {
                    String[] string = input.split(" ", 2);
                    if (string.length == 1) {
                        throw new InvalidParameterException("☹ OOPS!!! The description of a task cannot be empty.");
                    }
                    this.addTodoTask(string[1]);
                } else if (input.startsWith("deadline")) {
                    String[] string = input.split(" ", 2);
                    String[] contents = string[1].split(" /by ");
                    this.addDeadlineTask(contents[0], contents[1]);
                } else if (input.startsWith("event")) {
                    String[] string = input.split(" ", 2);
                    String[] contents = string[1].split(" /at ");
                    this.addEventTask(contents[0], contents[1]);
                } else if (input.startsWith("delete")) {
                    String[] string = input.split(" ", 2);
                    int index = Integer.parseInt(string[1]);
                    this.deleteTask(index);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (DukeException exception) {
                System.out.println("____________________________________________________________");
                System.out.println(exception.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
        this.exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
