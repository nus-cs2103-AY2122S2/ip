import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Storage storage = new Storage();
            tasks.addAll(storage.loadALlTasks());
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);


            System.out.println("What can I do for you?");
            while (true) {

                String userInput = scanner.nextLine();
                System.out.println("  ===================================");

                InstructionType instructionType = parseUserInput(userInput);

                if (instructionType.equals(InstructionType.BYE)) {
                    storage.saveAllTasks(tasks);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("  ===================================");
                    break;
                }


                if (instructionType.equals(InstructionType.LIST)) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (instructionType.equals(InstructionType.MARK)) {
                    String[] userInputArr = userInput.split(" ", 2);
                    Task task = tasks.get(Integer.parseInt(userInputArr[1]) - 1);

                    task.markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task.toString());
                } else if (instructionType.equals(InstructionType.UNMARK)) {
                    String[] userInputArr = userInput.split(" ", 2);
                    Task task = tasks.get(Integer.parseInt(userInputArr[1]) - 1);

                    task.markAsNotDone();

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + task.toString());
                } else if (instructionType.equals(InstructionType.DELETE)) {
                    String[] userInputArr = userInput.split(" ", 2);
                    Task task = tasks.remove(Integer.parseInt(userInputArr[1]) - 1);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + task.toString());
                } else {
                    String[] userInputArr = userInput.split(" ");
                    String title = String.join(" ",
                            Arrays.copyOfRange(userInputArr, 1, userInputArr.length));

                    if (instructionType.equals(InstructionType.TODO)) {
                        if (title.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task todo = new Todo(title);
                        tasks.add(todo);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo);
                    } else if (instructionType.equals(InstructionType.DEADLINE)) {
                        String[] taskArr = title.split(" /by ");
                        String taskTitle = taskArr[0];
                        String dueBy = taskArr[1];

                        Task deadline = new Deadline(taskTitle, dueBy);
                        tasks.add(deadline);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline.toString());
                    } else if (instructionType.equals(InstructionType.EVENT)) {
                        String[] taskArr = title.split(" /at ");
                        String taskTitle = taskArr[0];
                        String eventAt = taskArr[1];

                        Task event = new Event(taskTitle, eventAt);
                        tasks.add(event);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(event.toString());
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Now you have " + tasks.size() + " task" + parseTaskSize(tasks.size()) + "in the list.");
                }
                System.out.println("  ===================================");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println("  ===================================");
        }
    }


    public static InstructionType parseUserInput(String userInput) {
        String[] userInputArr = userInput.split(" ", 2);
        if (userInputArr.length > 2) {
            return InstructionType.INVALID;
        }

        switch (userInputArr[0]) {
            case "mark":
                return InstructionType.MARK;
            case "unmark":
                return InstructionType.UNMARK;
            case "todo":
                return InstructionType.TODO;
            case "event":
                return InstructionType.EVENT;
            case "deadline":
                return InstructionType.DEADLINE;
            case "delete":
                return InstructionType.DELETE;
            case "list":
                return InstructionType.LIST;
            case "bye":
                return InstructionType.BYE;
            default:
                return InstructionType.INVALID;
        }
    }

    public static String parseTaskSize(int size) {
        return size > 1 ? "s " : " ";
    }
}
