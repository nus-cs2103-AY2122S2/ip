import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws EmptyDescriptionException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.print("Hello from\n" + logo + "\n");

        String space = "     ";
        String longSpace = "        ";
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
                output += space + " Here are the tasks in your list:\n";
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

                output = space + " Nice! I've marked this task as done:\n"
                        + longSpace + list.get(index).toString() + "\n";
            }

            else if (input.startsWith("unmark")) {
                Character i1 = input.charAt(7);
                String i2 = i1.toString();
                int index = Integer.parseInt(i2) - 1;
                list.get(index).unmark();

                output += space + " Nice! I've marked this task as not done:\n"
                        + longSpace + list.get(index).toString() + "\n";
            }

            else if (input.startsWith("delete")) {
                Character i1 = input.charAt(7);
                String i2 = i1.toString();
                int index = Integer.parseInt(i2) - 1;

                output += space + " Noted. I've removed this task:\n"
                        + longSpace + list.get(index).toString() + "\n"
                        + space + "Now you have " + list.size() + " tasks in the list.\n";

                list.remove(index);
            }

            else if (input.startsWith("todo")) {
                try {
                    String name = input.substring(5);
                    Task current = new ToDo(name);
                    list.add(current);

                    output = space +  "Got it. I've added this task:\n"
                            + longSpace + current.toString() + "\n"
                            + space + "Now you have " + list.size() + " tasks in the list.\n";
                } catch (IndexOutOfBoundsException e) {
                    output += "     ☹ OOPS!!! The description of a todo cannot be empty.\n";
                } catch (EmptyDescriptionException e) {
                    output += "     ☹ OOPS!!! The description of a todo cannot be empty.\n";
                }
            }

            else if (input.startsWith("event")) {
                try {
                    String name = input.substring(6);
                    String[] inputArray = name.split("/");
                    Task current = new Event(inputArray[0], inputArray[1]);
                    list.add(current);

                    output = space +  "Got it. I've added this task:\n"
                            + longSpace + current.toString() + "\n"
                            + space + "Now you have " + list.size() + " tasks in the list.\n";
                } catch (IndexOutOfBoundsException e) {
                    output += "     ☹ OOPS!!! The description of a todo cannot be empty.\n";
                } catch (EmptyDescriptionException e) {
                    output += "     ☹ OOPS!!! The description of a todo cannot be empty.\n";
                }
            }

            else if (input.startsWith("deadline")) {
                try {
                    String name = input.substring(6);
                    String[] inputArray = input.split("/");
                    Task current = new Deadline(inputArray[0], inputArray[1]);
                    list.add(current);

                    output = space +  "Got it. I've added this task:\n"
                            + longSpace + current.toString() + "\n"
                            + space + "Now you have " + list.size() + " tasks in the list.\n";
                } catch (IndexOutOfBoundsException e) {
                    output += "     ☹ OOPS!!! The description of a todo cannot be empty.\n";
                } catch (EmptyDescriptionException e) {
                    output += "     ☹ OOPS!!! The description of a todo cannot be empty.\n";
                }
            }

            else {
                output = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
            }

            System.out.print(space + line + "\n"
                        + output
                        + space + line + "\n");
        }
        sc.close();
    }
}
