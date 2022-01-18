import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String GREETINGS = "Hello there, I am Giga Chad Duke\nHow can I help you?";
    private static final String BYE = "Bye, hope to see you again soon!";
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Duke.log(GREETINGS);

        while (true) {
            String input = sc.next();

            if (input.equals("bye")) {
                Duke.log(BYE);
                break;
            } else if (input.equals("list")){
                Duke.log(taskList.getTasks());
            } else if (input.equals("mark") || input.equals("unmark")){
                int taskId = sc.nextInt();
                Duke.log(taskList.mark(taskId));
            } else if (input.equals("todo")){
                String toDo = sc.nextLine().strip();
                Duke.log(taskList.addTask(new ToDo(toDo)));
            } else if (input.equals("event")) {
                String[] eventDetails = sc.nextLine().strip().split(" /at ");
                Duke.log(taskList.addTask(new Event(eventDetails[0], eventDetails[1])));
            } else if (input.equals("deadline")) {
                String[] deadlineDetails = sc.nextLine().strip().split(" /by ");
                Duke.log(taskList.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1])));
            }
        }
    }

    public static void log(String args) {
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println(args);
        System.out.println(Duke.HORIZONTAL_LINE);
    }
}
