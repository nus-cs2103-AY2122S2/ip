import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String GREETINGS = "Hello there, I am Giga Chad Duke\nHow can I help you?";
    private static final String BYE = "Bye, hope to see you again soon!";
    private static final TaskList taskList = new TaskList();
    private static final List<String> tasks = Arrays.asList("list", "bye", "todo", "event", "deadline", "mark", "unmark");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Duke.log(GREETINGS);

        Duke.run(sc);

        Duke.log(BYE);
    }

    public static void run(Scanner sc) {
        while (true) {
            try {
                String[] input = sc.nextLine().strip().split(" ", 2);

                if (!tasks.contains(input[0])) {
                    throw new CommandNotFoundException();
                }

                if (input[0].equals("bye")) {
                    return;
                } else if (input[0].equals("list")) {
                    Duke.log(taskList.getTasks());
                } else if (input[0].equals("mark") || input[0].equals("unmark")) {
                    int taskId = Integer.parseInt(input[1]);
                    Duke.log(taskList.mark(taskId, input[0]));
                } else {
                    if (input.length != 2) {
                        throw new InvalidArgumentException();
                    }
                    if (input[0].equals("todo")) {
                        String toDo = input[1].strip();
                        Duke.log(taskList.addTask(new ToDo(toDo)));
                    } else if (input[0].equals("event")) {
                        String[] eventDetails = input[1].strip().split(" /at ", 2);
                        Duke.log(taskList.addTask(new Event(eventDetails[0], eventDetails[1])));
                    } else if (input[0].equals("deadline")) {
                        String[] deadlineDetails = input[1].strip().split(" /by ", 2);
                        Duke.log(taskList.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1])));
                    }
                }
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void log(String args) {
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println(args);
        System.out.println(Duke.HORIZONTAL_LINE);
    }
}
