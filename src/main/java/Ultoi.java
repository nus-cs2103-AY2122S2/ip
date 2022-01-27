import java.util.Scanner;
import java.util.ArrayList;

public class Ultoi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String indent = "    ";

        String lineBreaker = indent + "======U======L======T======O======I======\n";

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

            try {
                checkInput(cmd, logs);


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
            } else if ((cmd.split(" "))[0].equals("delete")) {
                String[] tokens = cmd.split(" ");
                int taskIndex = Integer.parseInt(tokens[1]) - 1;
                Task removed = logs.remove(taskIndex);
                System.out.println(indent + "Noted. I have removed this task:");
                System.out.println(indent.repeat(2) + removed.toString());
                System.out.println(indent + "Now you have " + logs.size() + " tasks in the list.");
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
                System.out.println(indent + "Okay! I have marked this task as not done yet:");
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

            } catch (UltoiException e) {
                System.out.print(lineBreaker + indent + e.getMessage() + "\n" + lineBreaker);
                continue;
            }
        }
    }

    /* Checks whether a given command is valid.
     *
     * @return A Command enum representing the type of input.
     */
    private static void checkInput(String cmd, ArrayList<Task> logs) throws UltoiException {
        String[] tokens = cmd.split(" ");

        switch (tokens[0]) {
            case "list":
            case "bye":
                if (tokens.length > 1) {
                    throw new UltoiException("<OoO> Error! There should be nothing following this command.");
                }
                break;
            case "mark":
            case "unmark":
            case "delete":
                try {
                    int taskIndex = Integer.parseInt(cmd.substring(tokens[0].length() + 1)) - 1;
                    if (taskIndex < 0 || taskIndex >= logs.size()) {
                        throw new UltoiException("<OoO> Error! You do not have a task of this number.");
                    }
                } catch (Exception e) {
                    throw new UltoiException("<OoO> Error! Please key in a valid index after this command.");
                }
                break;
            case "todo":
                if (cmd.length() <= tokens[0].length() + 1) {
                    throw new UltoiException("<OoO> Error! A todo requires a description.");
                }
                break;
            case "deadline":
                if (cmd.length() <= tokens[0].length() + 1) {
                    throw new UltoiException("<OoO> Error! A deadline requires a description.");
                } else {
                    boolean hasBy = false;
                    for (int i = 0; i < tokens.length; i++) {
                        if (tokens[i].equals("/by")) {
                            hasBy = true;
                            break;
                        }
                    }
                    if (!hasBy) {
                        throw new UltoiException("<OoO> Error! A deadline requires a [/by] keyword followed by the time.");
                    }
                }
                break;
            case "event":
                if (cmd.length() <= tokens[0].length() + 1) {
                    throw new UltoiException("<OoO> Error! An event requires a description.");
                } else {
                    boolean hasAt = false;
                    for (int i = 0; i < tokens.length; i++) {
                        if (tokens[i].equals("/at")) {
                            hasAt = true;
                            break;
                        }
                    }
                    if (!hasAt) {
                        throw new UltoiException("<OoO> Error! An event requires a [/at] keyword followed by the time.");
                    }
                }
                break;
            default:
                throw new UltoiException("<OoO> Error! I do not understand what that means.");
        }
    }
}
