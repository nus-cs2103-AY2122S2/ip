import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);

        System.out.println("What can I do for you?");
        while (true) {
            try {

                String userInput = scanner.nextLine();
                System.out.println("  ===================================");

                if (userInput.equals("bye")) {
                    break;
                }

                if (userInput.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }

                } else if (parseUserInput(userInput).equals("mark")) {
                    String[] userInputArr = userInput.split(" ", 2);
                    Task task = tasks.get(Integer.parseInt(userInputArr[1]) - 1);

                    task.markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task.toString());

                } else if (parseUserInput(userInput).equals("unmark")) {
                    String[] userInputArr = userInput.split(" ", 2);
                    Task task = tasks.get(Integer.parseInt(userInputArr[1]) - 1);

                    task.markAsNotDone();

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + task.toString());
                } else if (parseUserInput(userInput).equals("delete")) {
                    String[] userInputArr = userInput.split(" ", 2);
                    Task task = tasks.remove(Integer.parseInt(userInputArr[1]) - 1);

                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + task.toString());
                } else {
                    String[] userInputArr = userInput.split(" ");
                    String instruction = userInputArr[0];
                    String title = String.join(" ",
                            Arrays.copyOfRange(userInputArr, 1, userInputArr.length));

                    if (instruction.equals("todo")) {
                        if(title.isEmpty()) {
                            throw new EmptyTodoException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task todo = new Todo(title);
                        tasks.add(todo);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo);
                    } else if (instruction.equals("deadline")) {
                        String[] taskArr = title.split(" /by");
                        String taskTitle = taskArr[0];
                        String dueBy = taskArr[1];

                        Task deadline = new Deadline(taskTitle, dueBy);
                        tasks.add(deadline);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline.toString());
                    } else if (instruction.equals("event")) {
                        String[] taskArr = title.split(" /at");
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
            } catch (DukeException e) {
                System.out.println(e.toString());
                System.out.println("  ===================================");
            } catch (EmptyTodoException e) {
                System.out.println(e.toString());
                System.out.println("  ===================================");

            }
        }

        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("  ===================================");

    }

    public static String parseUserInput(String userInput) {
        String[] userInputArr = userInput.split(" ", 2);
        if (userInputArr.length > 2) {
            return "";
        }

        return userInputArr[0];
    }

    public static String parseTaskSize (int size) {
        return size > 1 ? "s " : " ";
    }
}
