import java.util.Scanner;
import java.util.ArrayList;

public class Ultoi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String lineBreaker = "======U======L======T======O======I======\n";

        String indent = "    ";

        String greetingMessage =
                lineBreaker +
                indent + "Hello! I am Ultoi [ uhl-twah ].\n" +
                indent + "What can I do for you? <O_O>\n" +
                lineBreaker;

        String byeMessage = indent + "Bye. See you. <O_O>\n";

        System.out.print(greetingMessage);

        ArrayList<Task> logs = new ArrayList<Task>();

        for ( ; ; ) {
            String cmd = sc.nextLine();
            System.out.print(lineBreaker);

            if (cmd.equals("bye")) { // end the session
                System.out.print(byeMessage);
                System.out.print(lineBreaker);
                break;
            } else if (cmd.equals("list")) { // list all tasks
                for (int i = 0; i < logs.size(); i++) {
                    Task curr = logs.get(i);
                    System.out.println(indent + (i + 1) + ". " + curr.toString());
                }
            } else if ((cmd.split(" "))[0].equals("mark")) {
                String[] tokens = cmd.split(" ");
                int taskIndex = Integer.parseInt(tokens[1]) - 1;
                logs.get(taskIndex).markAsDone();
                System.out.println(indent + "Nice! I have marked this task as done:");
                System.out.println(indent.repeat(2) + logs.get(taskIndex).toString());
            } else if ((cmd.split(" "))[0].equals("unmark")) {
                String[] tokens = cmd.split(" ");
                int taskIndex = Integer.parseInt(tokens[1]) - 1;
                logs.get(taskIndex).markAsUndone();
                System.out.println(indent + "Nice! I have marked this task as not done yet:");
                System.out.println(indent.repeat(2) + logs.get(taskIndex).toString());
            } else if ((cmd.split(" "))[0].equals("todo")) { // add a ToDo event
                Task curr = new ToDo(cmd.substring(5));

                logs.add(curr);

                System.out.println(indent + "Got it! I have added this task:");
                System.out.println(indent.repeat(2) + curr.toString());
                System.out.println(indent + "Now you have " + logs.size() + " tasks in the list.");
            } else if ((cmd.split(" "))[0].equals("deadline")) {
                String description = "";
                String time = "";

                String[] tokens = cmd.split(" ");
                for (int i = 0; i < tokens.length; i++) {
                    if (tokens[i].equals("/by")) {
                        for (int j = i + 1; j < tokens.length; j++) {
                            time += tokens[j] + " ";
                        }
                        break;
                    } else {
                        description += tokens[i] + " ";
                    }
                }

                // omit extra whitespaces
                description = description.substring(0, description.length() - 1);
                time = time.substring(0, time.length() - 1);

                Task curr = new Deadline(description, time);

                logs.add(curr);

                System.out.println(indent + "Got it! I have added this task:");
                System.out.println(indent.repeat(2) + curr.toString());
                System.out.println(indent + "Now you have " + logs.size() + " tasks in the list.");
            } else if ((cmd.split(" "))[0].equals("event")) {
                String description = "";
                String time = "";

                String[] tokens = cmd.split(" ");
                for (int i = 0; i < tokens.length; i++) {
                    if (tokens[i].equals("/at")) {
                        for (int j = i + 1; j < tokens.length; j++) {
                            time += tokens[j] + " ";
                        }
                        break;
                    } else {
                        description += tokens[i] + " ";
                    }
                }

                // omit extra whitespaces
                description = description.substring(0, description.length() - 1);
                time = time.substring(0, time.length() - 1);

                Task curr = new Event(description, time);

                logs.add(curr);

                System.out.println(indent + "Got it! I have added this task:");
                System.out.println(indent.repeat(2) + curr.toString());
                System.out.println(indent + "Now you have " + logs.size() + " tasks in the list.");
            } else {
                logs.add(new Task(cmd));

                String generatedMessage = indent + "added: " + cmd;

                System.out.println(generatedMessage);
            }

            System.out.print(lineBreaker);
        }
    }
}
