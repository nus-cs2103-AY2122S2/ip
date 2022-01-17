import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Duke\n\t What can I do for you?");
        System.out.println("\t____________________________________________________________");

        // Initialise array of Tasks;
        Task[] tasks = new Task[100];
        int count = 0;

        while (true) {
            String input = sc.nextLine();
            char[] inputChar = input.toCharArray();
            // if command = "bye"
            if (input.equals("bye"))
                break;
            // if command = "list"
            if (input.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for (int taskCount = 0; taskCount < count; taskCount++) {
                    System.out
                            .println("\t " + (taskCount + 1) + "." + tasks[taskCount].status + " " + tasks[taskCount]);
                }
                System.out.println("\t____________________________________________________________");
                continue;
            }
            // if command = "mark x" where x is the task number
            if (inputChar[0] == 'm' && inputChar[1] == 'a' && inputChar[2] == 'r' && inputChar[3] == 'k') {
                char taskNumChar = inputChar[5];
                int taskNum = Character.getNumericValue(taskNumChar) - 1;
                tasks[taskNum].mark();
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t\t" + tasks[taskNum].status + " " + tasks[taskNum]);
                System.out.println("\t____________________________________________________________");
                continue;
            }
            // if command = "unmark x" where x is the task number
            if (inputChar[0] == 'u' && inputChar[1] == 'n' && inputChar[2] == 'm' && inputChar[3] == 'a'
                    && inputChar[4] == 'r' && inputChar[5] == 'k') {
                char taskNumChar = inputChar[7];
                int taskNum = Character.getNumericValue(taskNumChar) - 1;
                tasks[taskNum].unmark();
                System.out.println("\t____________________________________________________________");
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t\t" + tasks[taskNum].status + " " + tasks[taskNum]);
                System.out.println("\t____________________________________________________________");
                continue;
            }

            Task newTask = new Task(input);
            tasks[count] = newTask;
            count++;

            System.out.println("\t____________________________________________________________");
            System.out.println("\t added: " + newTask.toString());
            System.out.println("\t____________________________________________________________");
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
