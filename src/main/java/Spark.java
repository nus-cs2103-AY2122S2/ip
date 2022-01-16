import java.util.Scanner;

public class Spark {
    public static void main(String[] args) {
        // assuming that there will be no more than 100 tasks,
        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(System.in);

        // print welcome message
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Hello! I'm Spark");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------------------------------------------");

        String input;
        String[] split;
        String command;
        do {
            // get input from user
            input = sc.nextLine();
            split = input.split(" "); // split command into individual keywords
            command = split[0]; // assume that the first keyword is always the command word

            System.out.println("----------------------------------------------------------------------");

            if (command.equals("bye")) {// print goodbye message
                System.out.println("Cool, see you around!");

            } else if (command.equals("list")) {
                taskList.print();

            } else if (command.equals("mark")) {
                int taskId = Integer.parseInt(split[1]);
                taskList.markTask(taskId);

            } else if (command.equals("unmark")) {
                int taskId = Integer.parseInt(split[1]);
                taskList.unMarkTask(taskId);

            } else {
                taskList.add(input);

            }

            System.out.println("----------------------------------------------------------------------");
        } while (!command.equals("bye"));
    }
}
