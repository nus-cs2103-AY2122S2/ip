import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> Tasks = new ArrayList<Task>();
        int count = 0;

        String welcome = "Hello! I'm ChatCat\n" + "What can I do for you?\n";
        System.out.println(welcome);

        String input = " ";

        try {
            while (!input.equals("bye")) {

                input = br.readLine();
                String toCheck = firstN(input, 8);

                switch(input) {
                    case "list":
                        if (Tasks.size() == 0) {
                            System.out.println("empty list!");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < Tasks.size(); i++) {
                                System.out.println((i + 1) + ". " + Tasks.get(i));
                            }
                        }
                        System.out.println("");
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    default:
                        String[] splitInput = input.split(" ");
                        int len = splitInput.length;
                        switch(splitInput[0]) {
                            case "mark":
                                int taskID = Integer.parseInt(splitInput[1]) - 1;
                                Tasks.get(taskID).setDone();
                                System.out.println("Nice! I've marked this task as done:\n" +
                                        Tasks.get(taskID) + "\n");
                                break;
                            case "unmark":
                                int taskIDu = Integer.parseInt(splitInput[1]) - 1;
                                Tasks.get(taskIDu).setUnDone();
                                System.out.println("OK, I've marked this task as not done yet:\n" +
                                        Tasks.get(taskIDu) + "\n");
                                break;
                            case "todo":
                                if (len == 1) {
                                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                                }
                                Todo todo = new Todo(input.substring(5));
                                Tasks.add(todo);
                                System.out.println("Got it. I've added this task:\n" + todo);
                                System.out.println("Now you have " + Tasks.size() + " tasks in the list." + "\n");
                                break;
                            case "deadline":
                                if (len == 1) {
                                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                                }
                                String[] split = input.split("/by ");
                                Deadline deadline = new Deadline(split[0].substring(9), split[1]);
                                Tasks.add(deadline);
                                System.out.println("Got it. I've added this task:\n" + deadline);
                                System.out.println("Now you have " + Tasks.size() + " tasks in the list." + "\n");
                                break;
                            case "event":
                                if (len == 1) {
                                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                                }
                                split = input.split("/at ");
                                Event event = new Event(split[0].substring(6), split[1]);
                                Tasks.add(event);
                                System.out.println("Got it. I've added this task:\n" + event);
                                System.out.println("Now you have " + Tasks.size() + " tasks in the list." + "\n");
                                break;
                            case "delete":
                                int toDelete = Integer.parseInt(splitInput[1]) - 1;
                                Task removed = Tasks.remove(toDelete);
                                System.out.println("Noted. I've removed this task:\n" + removed);
                                System.out.println("Now you have " + Tasks.size() + " tasks in the list." + "\n");
                                break;
                            default:
                                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
                        }
                }
            }
        } catch (DukeException wrf) {
            wrf.printStackTrace();
        }
    }

    public static String firstN(String string, int n) {
        return string.length() < n ? string : string.substring(0, n);
    }
}
