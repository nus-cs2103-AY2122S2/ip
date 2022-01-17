import java.util.*;

public class Duke {
    public static void main(String[] args) throws DukeException{
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
            else if (str.startsWith("mark")) {
                try {
                    String[] markedTask = str.split(" ");
                    int indexMarked = Integer.parseInt(markedTask[1]) - 1;
                    Task currentTask = tasks.get(indexMarked);
                    currentTask.isDone = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(currentTask.toString());
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println(new DukeException("task does not exist!"));
                }
            }


            // unmark
            else if (str.startsWith("unmark")) {
                try {
                    String[] markedTask = str.split(" ");
                    int indexMarked = Integer.parseInt(markedTask[1]) - 1;
                    Task currentTask = tasks.get(indexMarked);
                    currentTask.isDone = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(currentTask.toString());
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println(new DukeException("task does not exist!"));
                }

            }

            // generate tasks
            else {
                if (str.startsWith("todo")) {
                    try {
                        Todo currentTodo = new Todo(str.substring(5));
                        tasks.add(currentTodo);
                        System.out.println("added: " + currentTodo.toString());
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        System.out.println(new DukeException("task does not exist!"));
                    }
                }
                else if (str.startsWith("deadline")) {
                    try {
                        String actualTask = str.substring(9);
                        String[] segments = actualTask.split(" /by ");
                        Deadline currentDeadline = new Deadline(segments[0], segments[1]);
                        tasks.add(currentDeadline);
                        System.out.println("added: " + currentDeadline.toString());
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        System.out.println(new DukeException("task does not exist!"));
                    }

                }

                else if (str.startsWith("event")) {
                    try {
                        String actualTask = str.substring(6);
                        String[] segments = actualTask.split(" /at ");
                        Event currentEvent = new Event(segments[0], segments[1]);
                        tasks.add(currentEvent);
                        System.out.println("added: " + currentEvent.toString());
                    }
                    catch (StringIndexOutOfBoundsException e) {
                        System.out.println(new DukeException("task does not exist!"));
                    }
                }
                else {
                    System.out.println(new DukeException("I'm sorry, but I don't know what that means :-(nr"));
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n");


    }
}
