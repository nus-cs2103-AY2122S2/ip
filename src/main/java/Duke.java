import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println(output("Hello! I'm Duke by A0221330A.\n    What can I do for you?"));

        List<Task> list = new ArrayList<>();

        boolean exit = false;
        while (!exit) {
            Scanner sc = new Scanner(System.in);
            String[] input = sc.nextLine().split(" ");
            switch (input[0]) {
                case "bye":
                    System.out.println(output("Bye. Hope to see you again soon!"));
                    exit = true;
                    break;
                case "list":
                    StringBuilder text = new StringBuilder();
                    text.append("Here are the tasks in your list:\n");
                    for (int i = 0; i < list.size(); i++) {
                        text.append("    ").append(i + 1).append(". ")
                                .append(list.get(i).getStatus())
                                .append(list.get(i).getTask())
                                .append("\n");
                    }
                    text.delete(text.length() - 1, text.length());
                    System.out.println(output(text.toString()));
                    break;
                case "mark":
                    int i = Integer.parseInt(input[1]) - 1;
                    list.set(i, list.get(i).mark());
                    System.out.println(output("Nice! I've marked this task as done:\n        "
                            + list.get(i).getStatus() + list.get(i).getTask()));
                    break;
                case "unmark":
                    int j = Integer.parseInt(input[1]) - 1;
                    list.set(j, list.get(j).unmark());
                    System.out.println(output("OK, I've marked this task as not done yet:\n        "
                            + list.get(j).getStatus() + list.get(j).getTask()));
                    break;
                default:
                    list.add(new Task(input[0]));
                    System.out.println(output("added: " + input[0]));
                    break;
            }
        }
    }

    public static String output(String text) {
        String line = "    ____________________________________________________________";
        return line + "\n    " + text + "\n" + line;
    }
}
