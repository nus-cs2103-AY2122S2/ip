import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        // Introduction
        System.out.println("Hello! I'm Duck\nWhat can I do for you? *quack*");

        // Scanner for reading user input
        Scanner sc = new Scanner(System.in);
        String command = ""; //Where user input will be stored

        // Storage for all the items listed by the user
        List<Task> tasks = new ArrayList<Task>();


        // Program will keep taking in new user input until terminated
        while (true) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            command = st.nextToken();

            // User terminates the program
            if (command.equals("bye"))  {
                System.out.println("Aww. Hope to see you again soon! *quack*");
                return;
            }
            // User lists out all stored items
            else if (command.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.println("You currently do not have any tasks *quack*, please add some more");
                    continue;
                }

                System.out.println("These are your tasks *quack*:");
                for (int i = 0; i < tasks.size(); i++) {
                    int number = i + 1;
                    Task task = tasks.get(i);
                    System.out.println(String.format("%d. %s", number, task.toString()));
                }
            }
            // Add a ToDo Task
            else if (command.equals("todo")) {
                List<String> newTaskNameArray = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    newTaskNameArray.add(st.nextToken());
                }
                String newTaskName = String.join(" ", newTaskNameArray);
                Task newTask = new ToDoTask(newTaskName);
                tasks.add(newTask);
                System.out.println("Got it. I've added this task *quack*:");
                System.out.println(String.format("  %s", newTask.toString()));
                System.out.println(String.format("Now you have %d task%s in the list *quack*.", tasks.size(), tasks.size() == 1 ? "" : "s"));
            }
            // Add a Deadline Task
            else if (command.equals("deadline")) {
                List<String> newTaskNameArray = new ArrayList<>();
                String preposition = "";
                while (st.hasMoreTokens()) {
                    String nextToken = st.nextToken();
                    if (nextToken.charAt(0) != '/') {
                        newTaskNameArray.add(nextToken);
                    } else {
                        preposition = nextToken.substring(1);
                        break;
                    }
                }
                List<String> deadlineArray = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    deadlineArray.add(st.nextToken());
                }
                String newTaskName = String.join(" ", newTaskNameArray);
                String deadline = String.join(" ", deadlineArray);
                Task newTask = new DeadlineTask(newTaskName, preposition, deadline);
                tasks.add(newTask);
                System.out.println("Got it. I've added this task *quack*:");
                System.out.println(String.format("  %s", newTask.toString()));
                System.out.println(String.format("Now you have %d task%s in the list *quack*.", tasks.size(), tasks.size() == 1 ? "" : "s"));
            }
            // Add a Event Task
            else if (command.equals("event")) {
                List<String> newTaskNameArray = new ArrayList<>();
                String preposition = "";
                while (st.hasMoreTokens()) {
                    String nextToken = st.nextToken();
                    if (nextToken.charAt(0) != '/') {
                        newTaskNameArray.add(nextToken);
                    } else {
                        preposition = nextToken.substring(1);
                        break;
                    }
                }
                List<String> deadlineArray = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    deadlineArray.add(st.nextToken());
                }
                String newTaskName = String.join(" ", newTaskNameArray);
                String deadline = String.join(" ", deadlineArray);
                Task newTask = new EventTask(newTaskName, preposition, deadline);
                tasks.add(newTask);
                System.out.println("Got it. I've added this task *quack*:");
                System.out.println(String.format("  %s", newTask.toString()));
                System.out.println(String.format("Now you have %d task%s in the list *quack*.", tasks.size(), tasks.size() == 1 ? "" : "s"));
            }
            // User marks an item as done
            else if (command.equals("mark")) {
                // Error handling if user does not input a second argument
                if (!st.hasMoreTokens()) {
                    System.out.println("Please input an item number when marking (eg. 'mark 1')");
                } else {
                    // Error handling if user inputs strings
                    try {
                        int number = Integer.parseInt(st.nextToken());
                        tasks.get(number - 1).mark();
                        System.out.println(String.format("I've marked task %d as done! *quack*", number));
                    } catch (Exception e) {
                        System.out.println("Please ONLY input integers when unmarking (eg. 'unmark 1')");
                    }
                }
            }
            // User marks an item as undone
            else if (command.equals("unmark")) {
                // Error handling if user does not input a second argument
                if (!st.hasMoreTokens()) {
                    System.out.println("Please input an item number when unmarking (eg. 'unmark 1')");
                } else {
                    // Error handling if user inputs strings
                    try {
                        int number = Integer.parseInt(st.nextToken());
                        tasks.get(number - 1).unmark();
                        System.out.println(String.format("I've unmarked task %d as done! *quack*", number));
                    } catch (Exception e) {
                        System.out.println("Please ONLY input integers when unmarking (eg. 'unmark 1')");
                    }
                }
            }
            // User adds items to the list
            else {
                System.out.println(String.format("That is not a valid command"));
            }
        }
    }
}
