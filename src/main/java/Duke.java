import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private TaskList taskList;

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
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();

        this.greet();
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
                    taskList.listTasks();
                } else if (command.equals("mark")) {
                    int index = Integer.parseInt(arguments);
                    if (index > taskList.size) {
                        throw new InvalidParameterException("☹ OOPS!!! The index provided is invalid.");
                    }
                    taskList.completeTask(index);
                } else if (command.equals("unmark")) {
                    int index = Integer.parseInt(arguments);
                    if (index > taskList.size) {
                        throw new InvalidParameterException("☹ OOPS!!! The index provided is invalid.");
                    }
                    taskList.undoTask(index);
                } else if (command.equals("todo")) {
                    if (arguments == "") {
                        throw new InvalidParameterException("☹ OOPS!!! The description of a task cannot be empty.");
                    }
                    taskList.addTodoTask(arguments);
                } else if (command.equals("deadline")) {
                    String[] argsArray = arguments.split(" /by ");
                    taskList.addDeadlineTask(argsArray[0], argsArray[1]);
                } else if (command.equals("event")) {
                    String[] argsArray = arguments.split(" /at ");
                    taskList.addEventTask(argsArray[0], argsArray[1]);
                } else if (command.equals("delete")) {
                    taskList.deleteTask(Integer.parseInt(arguments));
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
