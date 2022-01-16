import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String greeting = "\tHello! I'm Duke\n\tWhat can I do for you?\n";
    public static final String goodbye = "\tBye. Hope to see you again soon!";

    public static void main(String[] args) {

        System.out.println(greeting);

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            String[] arr = input.split(" ");

            switch (arr[0]) {

                case "list":
                    for (int i = 0; i < taskList.size(); i++) {
                        int idx = i + 1;
                        System.out.println("\t" + idx + ". " + taskList.get(i).toString());
                    }
                    System.out.println();
                    break;

                case "mark":
                    int mark = Integer.parseInt(arr[1]) - 1;
                    taskList.get(mark).setCompleted(true);
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t\t" + arr[1] + ". " + taskList.get(mark).toString() + "\n");
                    break;

                case "unmark":
                    int unmark = Integer.parseInt(arr[1]) - 1;
                    taskList.get(unmark).setCompleted(false);
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    System.out.println("\t\t" + arr[1] + ". " + taskList.get(unmark).toString() + "\n");
                    break;

                default:
                    taskList.add(new Task(input));
                    System.out.println("\tadded: " + input + "\n");
            }

            input = sc.nextLine();
        }

        System.out.println(goodbye);
    }
}
