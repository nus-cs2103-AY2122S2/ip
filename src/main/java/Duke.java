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
            String arg = sc.nextLine();
            String[] input = arg.split(" ");

            if (input[0].equals("bye")) {
                Duke.log(BYE);
                break;
            } else if (input[0].equals("list")){
                Duke.log(taskList.getTasks());
            } else if (input[0].equals("mark") || input[0].equals("unmark")){
                int taskId = Integer.parseInt(input[1]);
                Duke.log(taskList.mark(taskId));
            } else {
                Duke.log(taskList.addTask(new Task(arg)));
            }
        }
    }

    public static void log(String args) {
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println(args);
        System.out.println(Duke.HORIZONTAL_LINE);
    }
}
