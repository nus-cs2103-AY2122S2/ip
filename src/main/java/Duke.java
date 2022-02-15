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
                if (words[0].equals("mark")) {
                    int index = Integer.parseInt(words[1]) - 1;
                    lst.get(index).mark();
                    System.out.println("Nice! I've marked this task as done: \n"
                            + lst.get(index).toString());
                } else if (words[0].equals("unmark")) {
                    int index = Integer.parseInt(words[1]) - 1;
                    lst.get(index).unmark();
                    System.out.println("OK, I've marked this task as not done yet: \n"
                            + lst.get(index).toString());

                // add task
                } else {
                    Task tsk = new Task(input);
                    lst.add(tsk);
                    System.out.println("added: " + tsk.toString());
                }

            // list
            } else {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println(i + 1 + ". " + lst.get(i).toString());
                }
            }

            // scan next line
            input = sc.nextLine();
        }

        // end
        System.out.println("Bye! It was nice having you!");
        sc.close();
    }
}
