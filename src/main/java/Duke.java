import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<> ();

        while (true){
            String str= sc.nextLine();

            // quit
            if (str.equals("bye")) {
                break;
            }

            // list
            else if (str.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task currentTask = tasks.get(i);
                    int index = i + 1;
                    System.out.println(index + "." + currentTask.toString());
                }
            }

            // mark
            else if (str.length() >= 4 && str.startsWith("mark")) {
                String[] markedTask = str.split(" ");
                int indexMarked = Integer.parseInt(markedTask[1]) - 1;
                Task currentTask = tasks.get(indexMarked);
                currentTask.isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(currentTask.toString())
                ;
            }


            // unmark
            else if (str.length() >= 6 && str.startsWith("unmark")) {
                String[] markedTask = str.split(" ");
                int indexMarked = Integer.parseInt(markedTask[1]) - 1;
                Task currentTask = tasks.get(indexMarked);
                currentTask.isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(currentTask.toString());
            }

            // generate tasks
            else {
                if (str.length() >= 4 && str.startsWith("todo")) {
                    Todo currentTodo = new Todo(str.substring(5));
                    tasks.add(currentTodo);
                    System.out.println("added: " + currentTodo.toString());
                }
                else if (str.length() >= 8 && str.startsWith("deadline")) {
                    String actualTask = str.substring(9);
                    String[] segments = actualTask.split(" /by ");
                    Deadline currentDeadline = new Deadline(segments[0], segments[1]);
                    tasks.add(currentDeadline);
                    System.out.println("added: " +  currentDeadline.toString());

                }

                else if (str.length() >= 5 && str.startsWith("event")) {
                    String actualTask = str.substring(6);
                    String[] segments = actualTask.split(" /at ");
                    Event currentEvent = new Event(segments[0], segments[1]);
                    tasks.add(currentEvent);
                    System.out.println("added: " + currentEvent.toString());
                }
                else {
                    tasks.add(new Task(str));
                    System.out.println("added: " + str);
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");


    }
}
