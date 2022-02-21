import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    private static final String INDENT = "     ";
    private static final String LINE = "    ____________________________________________________________";

    public static void greetUser() {
        System.out.println(LINE);
        System.out.println(INDENT+"Hello! I'm Duke\n"+
                INDENT+
                "What can I do for you?");
        System.out.println(LINE);
        System.out.println();

    }

    public static void stopDuke() {
        System.out.println(LINE);
        System.out.println(INDENT+"Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        // Greeting
        greetUser();

        // Data Structures
        TaskList<Task> tasks = new TaskList<>();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            StringTokenizer st = new StringTokenizer(input);
            System.out.println(LINE);
            String cmd = st.nextToken();

            if (cmd.equals("list")) {

                System.out.println(INDENT+"Here are the tasks in your list:");
                for (int i=0; i<tasks.size(); i++) {
                    System.out.println(INDENT+(i+1)+"."+tasks.get(i));
                }

            } else if (cmd.equals("mark") || cmd.equals("unmark")) {

                int index = Integer.parseInt(st.nextToken());
                tasks.get(index-1).setDone(cmd.equals("mark"));
                if (cmd.equals("mark")) {
                    System.out.println(INDENT + "Nice! I've marked this task as done:");
                } else {
                    System.out.println(INDENT + "OK, I've marked this task as not done yet:");
                }
                System.out.println(INDENT + "  " + tasks.get(index-1));

            } else if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {

                try {
                    switch (cmd) {
                        case "todo":
                            tasks.add(new Todo(tasks.size()+1, input.split(cmd)[1]));
                            break;
                        case "deadline":
                            tasks.add(new Deadline(tasks.size()+1, input.split(cmd)[1]));
                            break;
                        case "event":
                            tasks.add(new Event(tasks.size()+1, input.split(cmd)[1]));
                            break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println(INDENT+"Plz input date in a valid date-time format.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(INDENT+"Plz include a task deadline");
                }


            } else if (cmd.equals("delete")) {

                int index = Integer.parseInt(st.nextToken());
                System.out.println(INDENT + "Noted. I've removed this task:");
                System.out.println(INDENT + "  " + tasks.get(index-1));
                tasks.remove(index);
                System.out.println(INDENT+"Now you have "+tasks.size()+" tasks in the list.");
            }

            System.out.println(LINE);
            System.out.println();
            input = in.nextLine();
        }

        // Exit Duke
        tasks.updateTask();
        stopDuke();
    }
}
