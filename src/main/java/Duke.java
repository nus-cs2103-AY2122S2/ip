import java.util.*;

public class Duke {
    private static List list;
    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        list = new List(100);

        while (!userInput.equals("bye")) {
            try {
                processUserInput(userInput, list);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            userInput = sc.nextLine();
        }
        bye();
    }

    /**
     * This method greets the user at the start of the program.
     */
    private static void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "I am at your service.\n");
    }

    /**
     * This method bids farewell to the user at the end of the program.
     */
    private static void bye() {
        System.out.println("Ciao! Hope to see you again!");
    }

    /**
     * This method process the user input.
     * @param userInput user input
     * @param list list of tasks
     * @throws DukeException throws an exception when the user input is invalid
     */
    private static void processUserInput(String userInput, List list) throws DukeException {
        if (userInput.equals("list")) {
            System.out.println(list);
        }
        else if (userInput.startsWith("mark")) {
            String str = userInput.substring(5);
            int number = Integer.parseInt(str) - 1;
            Task doneTask = list.markDone(number);
            System.out.println("Nice! I've marked this task as done:\n  "
                    + doneTask.toString());
        }
        else if (userInput.startsWith("unmark")) {
            String str = userInput.substring(7);
            int number = Integer.parseInt(str) - 1;
            Task unDoneTask = list.unmarkDone(number);
            System.out.println("OK, I've marked this task as not done yet:\n  "
                    + unDoneTask.toString());
        }
        else if (userInput.startsWith("delete")) {
            String indexStr = userInput.substring(7);
            int index = Integer.parseInt(indexStr);
            Task task = list.delete(index);
            System.out.println("Roger. I've deleted this task:\n  "
                                +  task.toString() + "\n"
                                + "Now you have "
                                + list.getArrayList().size()
                                + " tasks in the list");
        }
        else if (userInput.startsWith("todo") || userInput.startsWith("deadline") ||
                    userInput.startsWith("event")) {
            try {
                System.out.println("Got it. I've added this task:\n  "
                        + processTask(userInput, list)
                        + "\n" + "Now you have " + list.getArrayList().size()
                        + " tasks in the list.");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * This method process the user input if it is a Task(ToDos, Deadlines, Events).
     *  and adds it to the list.
     * @param userInput
     * @param list: list of tasks.
     * @return returns the toString of the task.
     */
    private static String processTask(String userInput, List list) throws DukeException {
        String str = userInput.trim();
        if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {
            throw new DukeException("OOPS!!! The description of a " + str + " cannot be empty.");
        } else {
            if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);
                Tasks taskType = Tasks.TODO;
                Task toDo = list.add(taskType, description, "");
                return toDo.toString();
            } else {
                int start = userInput.indexOf("/");
                String timing = userInput.substring(start + 3);
                String output = "";
                if (userInput.startsWith("deadline")) {
                    String description = userInput.substring(9, start - 1);
                    //Deadline deadline = new Deadline(description, timing);
                    Tasks taskType = Tasks.DEADLINE;
                    Task deadline =  list.add(taskType, description, timing);
                    output = output + deadline.toString();
                }

                if (userInput.startsWith("event")) {
                    String description = userInput.substring(6, start - 1);
                    //Event event = new Event(description, timing);
                    Tasks taskType = Tasks.EVENT;
                    Task event = list.add(taskType, description, timing);
                    output = output + event.toString();
                }
                return output;
            }
        }
    }
}
