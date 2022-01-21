import java.util.Scanner;

public class Duke {
    private static TaskList taskList = new TaskList();
    private static Database db = new Database(taskList);

    public static void main(String[] args) {
        Duke.log(Constants.GREETINGS);
        Duke.setup();
        Duke.run();
        Duke.log(Constants.BYE);
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String[] input = sc.nextLine().strip().split(" ", 2);

                if (!Constants.tasks.contains(input[0])) {
                    throw new CommandNotFoundException();
                }

                if (input[0].equals("bye")) {
                    return;
                } else if (input[0].equals("list")) {
                    Duke.log(taskList.getTasks());
                } else {
                    if (input.length != 2) {
                        throw new InvalidArgumentException();
                    }

                    if (input[0].equals("mark") || input[0].equals("unmark")) {
                        int taskId = Integer.parseInt(input[1]);
                        Duke.log(taskList.mark(taskId, input[0]));
                    } else if (input[0].equals("todo")) {
                        String toDo = input[1].strip();
                        Duke.log(taskList.addTask(new ToDo(toDo)));
                    } else if (input[0].equals("event")) {
                        String[] eventDetails = input[1].strip().split(" /at ", 2);
                        Duke.log(taskList.addTask(new Event(eventDetails[0], eventDetails[1])));
                    } else if (input[0].equals("deadline")) {
                        String[] deadlineDetails = input[1].strip().split(" /by ", 2);
                        Duke.log(taskList.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1])));
                    } else if (input[0].equals("delete")) {
                        int taskId = Integer.parseInt(input[1]);
                        Duke.log(taskList.remove(taskId));
                    }
                }
            } catch (Exception e) {
                Duke.log(e.getMessage());
            }
            Duke.update();
        }
    }

    public static void log(String args) {
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println(args);
        System.out.println(Constants.HORIZONTAL_LINE);
    }

    public static void setup() {
        taskList = new TaskList();
        db = new Database(taskList);
    }

    public static void update() {
        db.update();
    }
}
