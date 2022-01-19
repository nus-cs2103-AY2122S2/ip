import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println(logo);

        System.out.println(output("Hello! I'm Duke by A0221330A.\n    What can I do for you?"));

        ArrayList<Task> list = new ArrayList<>();

        boolean exit = false;
        while (!exit) {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                List<String> input = Arrays.asList(sc.nextLine().split(" "));
                switch (input.get(0)) {
                    case "bye":
                        System.out.println(output("Bye. Hope to see you again soon!"));
                        exit = true;
                        break;
                    case "list":
                        StringBuilder text = new StringBuilder();
                        text.append("Here are the tasks in your list:\n");
                        for (int i = 0; i < list.size(); i++) {
                            text.append("    ").append(i + 1).append(". ")
                                    .append(list.get(i))
                                    .append("\n");
                        }
                        text.delete(text.length() - 1, text.length());
                        System.out.println(output(text.toString()));
                        break;
                    case "mark":
                        int markIndex = Integer.parseInt(input.get(1)) - 1;
                        list.set(markIndex, list.get(markIndex).mark());
                        System.out.println(output("Nice! I've marked this task as done:\n        "
                                + list.get(markIndex)));
                        break;
                    case "unmark":
                        int unmarkIndex = Integer.parseInt(input.get(1)) - 1;
                        list.set(unmarkIndex, list.get(unmarkIndex).unmark());
                        System.out.println(output("OK, I've marked this task as not done yet:\n        "
                                + list.get(unmarkIndex)));
                        break;
                    case "todo":
                        StringBuilder todo = new StringBuilder();
                        for (int i = 1; i < input.size(); i++) {
                            todo.append(input.get(i)).append(" ");
                        }
                        list.add(new ToDo(todo.toString()));
                        System.out.println(output("Got it. I've added this task: \n        "
                                + list.get(list.size() - 1) + "\n    Now you have " + list.size() + " tasks in the list."));
                        break;
                    case "deadline":
                        StringBuilder deadline = new StringBuilder();
                        StringBuilder deadlineBy = new StringBuilder();
                        int byIndex = input.indexOf("/by");
                        for (int i = 1; i < byIndex; i++) {
                            deadline.append(input.get(i)).append(" ");
                        }
                        for (int i = byIndex + 1; i < input.size(); i++) {
                            deadlineBy.append(input.get(i)).append(" ");
                        }
                        list.add(new Deadline(deadline.toString(), deadlineBy.toString()));
                        System.out.println(output("Got it. I've added this task: \n        "
                                + list.get(list.size() - 1) + "\n    Now you have " + list.size() + " tasks in the list."));
                        break;
                    case "event":
                        StringBuilder event = new StringBuilder();
                        StringBuilder eventAt = new StringBuilder();
                        int atIndex = input.indexOf("/at");
                        for (int i = 1; i < atIndex; i++) {
                            event.append(input.get(i)).append(" ");
                        }
                        for (int i = atIndex + 1; i < input.size(); i++) {
                            eventAt.append(input.get(i)).append(" ");
                        }
                        list.add(new Event(event.toString(), eventAt.toString()));
                        System.out.println(output("Got it. I've added this task: \n        "
                                + list.get(list.size() - 1) + "\n    Now you have " + list.size() + " tasks in the list."));
                        break;
                    default:
                        list.add(new Task(input.get(0)));
                        System.out.println(output("added: " + input.get(0)));
                        break;
                }
            }
        }
    }

    public static String output(String text) {
        String line = "    ____________________________________________________________";
        return line + "\n    " + text + "\n" + line;
    }
}
