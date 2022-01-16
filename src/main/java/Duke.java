import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Oh hello dear, I'm Dukie, Zi Xin's favourite chattie box\n" +
                            "Nice to meet you dear:>\n" +
                            "What can I do for you?");

        Scanner myObj = new Scanner(System.in); //Create a Scanner object
        String input; //declare a string variable to store input
        List<Task> all = new ArrayList<Task>(); //ArrayList of Task

        while (!(input = myObj.nextLine()).equals("bye")) { //check input not "bye"
            String[] words = input.split(" ", 2); //split input string to get first word (action)

            if (input.equals("list")) { //if list
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= all.size(); i++) {
                    System.out.println(i + ". " + all.get(i-1).toString());
                }
            }

            else if (words[0].equals("mark")) {
                int n = Integer.parseInt(words[1]);
                all.get(n-1).markDone(); //call Task method, mark task as done
            }

            else if (words[0].equals("unmark")) {
                int n = Integer.parseInt(words[1]);
                all.get(n-1).unMarkDone(); //call Task method, mark task as not done
            }

            else { //adding of Tasks

                if (words[0].equals("todo")) {
                    ToDo item = new ToDo(words[1]);
                    all.add(item);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(item.toString());
                }

                if (words[0].equals("deadline")) {
                    String[] split_deadline = words[1].split("/by", 2);
                    Deadline item = new Deadline(split_deadline[0], split_deadline[1]);
                    all.add(item);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(item.toString());
                }

                if (words[0].equals("event")) {
                    String[] split_event = words[1].split("/at", 2);
                    Event item = new Event(split_event[0], split_event[1]);
                    all.add(item);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(item.toString());
                }
                System.out.println("Now you have " + all.size() + " tasks in the list."); //print when new task added
            }

        }
        System.out.println("Bye. Hope to see you again soon!"); //ending sentence
    }
}
