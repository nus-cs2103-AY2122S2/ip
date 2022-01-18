import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        String greeting = "     ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "     ____________________________________________________________";

        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String output = "";
            String line = "     ____________________________________________________________\n";

            if (input.equals("bye")) {
                output = "Bye. Hope to see you again soon!";
                System.out.println(line
                        + "     "
                        + output
                        + "\n"
                        + line);
                break;
            }
            else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++)
                    output += "     "
                            + (i + 1)
                            + ". "
                            + list.get(i)
                            + "\n";
            }
            else {
                list.add(input);
                output = "     "
                        + "added: "
                        + input
                        + "\n";
            }

            System.out.print(line + output + line);
        }
        sc.close();
    }
}
