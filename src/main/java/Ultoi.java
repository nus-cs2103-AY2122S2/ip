import java.util.Scanner;
import java.util.ArrayList;

public class Ultoi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String lineBreaker = "======U======L======T======O======I======\n";

        String greetingMessage =
                lineBreaker +
                "    Hello! I am Ultoi [ uhl-twah ].\n" +
                "    What can I do for you? <O_O>\n" +
                lineBreaker;

        String byeMessage =
                lineBreaker +
                "     Bye. See you. <O_O>\n" +
                lineBreaker;

        System.out.print(greetingMessage);

        ArrayList<Task> logs = new ArrayList<Task>();

        for ( ; ; ) {
            String cmd = sc.nextLine();

            if (cmd.equals("bye")) { // end the session
                System.out.print(byeMessage);
                break;
            } else if (cmd.equals("list")) { // list all tasks
                System.out.print(lineBreaker);

                for (int i = 0; i < logs.size(); i++) {
                    Task curr = logs.get(i);
                    System.out.println("     " + (i + 1) + ". " + curr.toString());
                }

                System.out.print(lineBreaker);
            } else if ((cmd.split(" "))[0].equals("mark")) {
                System.out.print(lineBreaker);

                String[] tokens = cmd.split(" ");
                int taskIndex = Integer.parseInt(tokens[1]) - 1;
                logs.get(taskIndex).markAsDone();
                System.out.println("     Nice! I have marked this task as done:");
                System.out.println("          " + logs.get(taskIndex).toString());

                System.out.print(lineBreaker);
            } else if ((cmd.split(" "))[0].equals("unmark")) {
                System.out.print(lineBreaker);

                String[] tokens = cmd.split(" ");
                int taskIndex = Integer.parseInt(tokens[1]) - 1;
                logs.get(taskIndex).markAsUndone();
                System.out.println("     Nice! I have marked this task as not done yet:");
                System.out.println("          " + logs.get(taskIndex).toString());

                System.out.print(lineBreaker);
            } else {
                logs.add(new Task(cmd));

                String generatedMessage =
                        lineBreaker +
                        "     added: " + cmd + "\n" +
                        lineBreaker;

                System.out.print(generatedMessage);
            }
        }
    }
}
