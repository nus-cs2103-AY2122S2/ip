import java.util.*;
import java.lang.String;

public class Duke {
    public static void main(String[] args) throws DukeException {

        System.out.println("Hello there, I'm Duke! Let's chat!");

        // Start scanner
        FastIO sc = new FastIO();
        String input = sc.nextLine();

        // List to hold tasks
        ArrayList<Task> lst = new ArrayList<Task>();

        while (!input.equals("bye")) {

            if (!input.equals("list")) {

                // mark/unmark tasks
                String[] words = input.split("\\s",0);
                String category = words[0];
                if (category.equals("mark")) {
                    int index = Integer.parseInt(words[1]) - 1;
                    lst.get(index).mark();
                    System.out.println("Nice! I've marked this task as done: \n" + lst.get(index).toString());
                } else if (category.equals("unmark")) {
                    int index = Integer.parseInt(words[1]) - 1;
                    lst.get(index).unmark();
                    System.out.println("OK, I've marked this task as not done yet: \n" + lst.get(index).toString());

                // add task
                } else {

                    // Throw Exception for missing details
                    String[] info = input.split(" ", 2);
                    if (info.length ==  1) {
                        throw new DukeException("The task description of type " + category + " cannot be empty.");
                    }

                    // sorting out the types of task
                    System.out.println("Got it. I've added this task: ");
                    if (category.equals("todo")) {
                        ToDo item = new ToDo(info[1]);
                        lst.add(item);
                        System.out.println(item.toString());
                    } else if (category.equals("deadline")) {
                        String[] details = info[1].split(" /by ",0);
                        Deadline item = new Deadline(details[0], details[1]);
                        lst.add(item);
                        System.out.println(item.toString());
                    } else if  (category.equals("event")) {
                        String[] details = info[1].split(" /at ",0);
                        Event item = new Event(details[0], details[1]);
                        lst.add(item);
                        System.out.println(item.toString());
                    } else {
                        // Invalid/ Missing type
                        throw new DukeException("Missing type of event/ Invalid command");
                    }
                System.out.println("Now you have " + lst.size() + " tasks in the list.");
                }

            // list tasks
            } else {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println(i + 1 + ". " + lst.get(i).toString());
                }
            }

            // scan next line
            input = sc.nextLine();
        }

        // bye command
        System.out.println("Bye! It was nice having you!");
        sc.close();
    }
}
