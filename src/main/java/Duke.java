import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");

        String space = "     ";
        String line = "____________________________________________________________";

        String greeting = space + line + "\n"
                + space + "Hello! I'm Duke\n"
                + space + "What can I do for you?\n"
                + space + line;

        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String output = "";

            if (input.equals("bye")) {
                output = "Bye. Hope to see you again soon!";
                System.out.print(space + line + "\n"
                            + space + output + "\n"
                            + space + line);
                break;
            }

            else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    String bullet = space + (i + 1) + ".";
                    output += bullet + list.get(i).toString() + "\n";
                }
            }

            else if (input.startsWith("mark")) {
                Character i1 = input.charAt(5);
                String i2 = i1.toString();
                int index = Integer.parseInt(i2) - 1;
                list.get(index).mark();

                output += space + " Nice! I've marked this task as done:\n"
                        + space + "   " + list.get(index).toString() + "\n";
            }

            else if (input.startsWith("unmark")) {
                Character i1 = input.charAt(7);
                String i2 = i1.toString();
                int index = Integer.parseInt(i2) - 1;
                list.get(index).unmark();

                output += space + " Nice! I've marked this task as not done:\n"
                        + space + "   " + list.get(index).toString() + "\n";
            }

            else {
                Task current = new Task(input);
                list.add(current);
                output = space + "added: " + input + "\n";
            }

            System.out.print(space + line + "\n"
                        + output
                        + space + line + "\n");
        }
        sc.close();
    }
}
