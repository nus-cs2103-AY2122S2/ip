import java.util.*;
import java.lang.String;

public class Duke {
    public static void main(String[] args) {

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
                    System.out.println("Got it. I've added this task: ");
                    // sorting out the types of task
                    if (category.equals("todo")) {
                        String info = input.split(" ", 2)[1];
                        ToDo item = new ToDo(info);
                        lst.add(item);
                        System.out.println(item.toString());
                    } else if (category.equals("deadline")) {
                        String[] info = input.split(" ", 2)[1].split(" /by ",0);
                        Deadline item = new Deadline(info[0], info[1]);
                        lst.add(item);
                        System.out.println(item.toString());
                    } else if  (category.equals("event")) {
                        String[] info = input.split(" ", 2)[1].split(" /at ",0);
                        Event item = new Event(info[0], info[1]);
                        lst.add(item);
                        System.out.println(item.toString());
                    } else {
                        System.out.println("Error: Missing type");
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
