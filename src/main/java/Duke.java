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
            // Add a ToDo Task OR Deadline Task OR Event Task
            else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                //Reading in the name and preposition
                List<String> nameArray = new ArrayList<>();
                String preposition = "";
                while (st.hasMoreTokens()) {
                    String nextToken = st.nextToken();
                    if (command.equals("todo") || nextToken.charAt(0) != '/') {
                        nameArray.add(nextToken);
                    }
                    //Only take in preposition if it is not a todo
                    else {
                        preposition = nextToken.substring(1);
                        break;
                    }
                }
                String name = String.join(" ", nameArray);
                if (name.equals("")) {
                    System.out.println("Please add a description of your todo as it cannot be empty! *quack*");
                    continue;
                }
                //Setting up deadline/ date
                List<String> deadlineOrDateArray = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    deadlineOrDateArray.add(st.nextToken());
                }
                String deadlineOrDate = String.join(" ", deadlineOrDateArray);
                //Creating the new task
                Task newTask = new Task("placeholder task");
                if (command.equals("todo")) newTask = new ToDoTask(name);
                else if (command.equals("deadline")) newTask = new DeadlineTask(name, preposition, deadlineOrDate);
                else if (command.equals("event")) newTask = new EventTask(name, preposition, deadlineOrDate);
                tasks.add(newTask);
                //Output to update user
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
                    } catch (NumberFormatException e) {
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
            // Help command
            else if (command.equals("help")) {
                System.out.println("These are the commands you can use *quack*:");
                System.out.println("  'list' to list out all your tasks");
                System.out.println("  'todo <name>' to add a todo task");
                System.out.println("  'deadline /<preposition> <name>' to add a task with a deadline");
                System.out.println("  'event /<preposition> <name>' to add an event with a date");
                System.out.println("  'mark <task number>' to mark a task as done");
                System.out.println("  'unmark <task number>' to unmark a task as done");
                System.out.println("  'bye' to close your Duck app");
                System.out.println("*quack*");
            }
            // User adds items to the list
            else {
                System.out.println(String.format("That is not a valid command *quack*\nType 'help' to see a list of valid commands"));
            }
        }
    }
}
