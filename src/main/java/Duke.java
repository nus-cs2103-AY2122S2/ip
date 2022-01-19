import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\t____________________________________________________________");
        System.out.println("\t Woof! I'm Wonka!\n\t How may I be of service?");
        System.out.println("\t____________________________________________________________");

        // Initialise array of Tasks;
        Task[] tasks = new Task[100];
        int count = 0;

        while (true) {
            String input = sc.nextLine();

            // if command = "bye"
            if (input.equals("bye"))
                break;

            // if command = "list"
            if (input.equals("list")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Here are the tasks in your list:");
                for (int taskCount = 0; taskCount < count; taskCount++) {
                    Task mainTask = tasks[taskCount];
                    System.out.println("\t " + (taskCount + 1) + "." + mainTask.track()
                            + mainTask.status + " " + mainTask.toString());
                }
                System.out.println("\t____________________________________________________________");
                continue;
            }

            String[] tokens = input.split(" ");
            String command = tokens[0];
            int sizeOfInputArr = tokens.length;
            String name = "";
            for (int i = 1; i < sizeOfInputArr - 1; i++) {
                name = name.concat(tokens[i]);
                name = name.concat(" ");
            }
            name = name.concat(tokens[sizeOfInputArr - 1]);

            // if command = "mark x" where x is the task number
            if (command.equals("mark")) {
                String taskNumStr = tokens[1];
                int taskNum = Integer.parseInt(taskNumStr) - 1;
                tasks[taskNum].mark();
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t\t" + tasks[taskNum].track() + tasks[taskNum].status + " " + tasks[taskNum]);
                System.out.println("\t____________________________________________________________");
                continue;
            }
            // if command = "unmark x" where x is the task number
            if (command.equals("unmark")) {
                String taskNumStr = tokens[1];
                int taskNum = Integer.parseInt(taskNumStr) - 1;
                tasks[taskNum].unmark();
                System.out.println("\t____________________________________________________________");
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t\t" + tasks[taskNum].track() + tasks[taskNum].status + " " + tasks[taskNum]);
                System.out.println("\t____________________________________________________________");
                continue;
            }

//             if command = "todo"
            if (command.equals("todo")) {
                Todo todo = new Todo(name);
                tasks[count] = todo;
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t\t " + todo.track() + todo.status + " " + todo.toString());
                System.out.println("\t Now you have " + (count + 1) + " tasks in the list.");
                System.out.println("\t____________________________________________________________");
            }

            // if command = "event"
            if (command.equals("event")) {
                String[] tokensEvent = input.split("/");
                String time = tokensEvent[1];

                String[] tokensName = name.split("/");
                String eventName = tokensName[0];
                Event event = new Event(eventName, time);
                tasks[count] = event;

                System.out.println("\t____________________________________________________________");
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t\t " + event.track() + event.status + " " + event.toString());
                System.out.println("\t Now you have " + (count + 1) + " tasks in the list.");
                System.out.println("\t____________________________________________________________");


            }

            // if command = "deadline"
            if (command.equals("deadline")) {
                String[] tokensDeadline = input.split("/");
                String date = tokensDeadline[1];

                String[] tokensName = name.split("/");
                String deadlineName = tokensName[0];
                Deadline deadline = new Deadline(deadlineName, date);
                tasks[count] = deadline;
                
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Got it. I've added this task:");
                System.out.println("\t\t " + deadline.track() + deadline.status + " " + deadline.toString());
                System.out.println("\t Now you have " + (count + 1) + " tasks in the list.");
                System.out.println("\t____________________________________________________________");
            }

            count++;
        }

        System.out.println("\t Woof woof! Hope to see you again soon!");
        sc.close();
    }
}
