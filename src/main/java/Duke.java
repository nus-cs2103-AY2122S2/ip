import java.util.*;
import java.io.*;
/**
 * This program is used to add, list & mark the status of your current tasks.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Duke {
    // ArrayList to store all your tasks
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * The main method of this program
     */
    public static void main(String[] args) {
        // Greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // Scanner Object
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            // Add different types of Task, List, Mark
            while (!input.equals("bye")) {
                String[] temp = input.split(" ");
                if (temp[0].equals("mark")) {
                    int index = Integer.parseInt(temp[1]) - 1;
                    Duke.markTask(index);
                } else if (temp[0].equals("unmark")) {
                    int index = Integer.parseInt(temp[1]) - 1;
                    Duke.unMarkTask(index);
                } else if (temp[0].equals("list")) {
                    Duke.list();
                } else if (temp[0].equals("todo")) {
                    Duke.addToDo(input);
                } else if (temp[0].equals("deadline")) {
                    Duke.addDeadline(input);
                } else if (temp[0].equals("event")) {
                    Duke.addEvent(input);
                } else {
                    Duke.notSpecified();
                }
                input = scanner.nextLine();
            }
            //Exit
            System.out.println("Bye. Hope to see you again soon!");
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void markTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, can't find task :-(");
        } else {
            Task task = list.get(index);
            task.setAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.getTask());
        }
    }

    public static void unMarkTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'm sorry, can't find task :-(");
        } else {
            Task task = list.get(index);
            task.setAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task.getTask());
        }
    }

    public static void list() {
        int num = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println(num + "." + task.getTask());
            num++;
        }
    }

    public static void addToDo(String input) throws DukeException {
        Task newTask = new ToDo(Duke.rmType(input));
        list.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.getTask());
        System.out.println("Now you have "+ list.size() +" tasks in the list.");
    }

    public static void addDeadline(String input) throws DukeException {
        String adjInput = Duke.rmType(input);
        Task newTask = new Deadline(Duke.extractDateORTask(adjInput, 2),
                Duke.extractDateORTask(adjInput, 1));
        list.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.getTask());
        System.out.println("Now you have "+ list.size() +" tasks in the list.");
    }

    public static void addEvent(String input) throws DukeException {
        String adjInput = Duke.rmType(input);
        Task newTask = new Event(Duke.extractDateORTask(adjInput, 2),
                Duke.extractDateORTask(adjInput, 1));
        list.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.getTask());
        System.out.println("Now you have "+ list.size() +" tasks in the list.");
    }

    public static void notSpecified() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * This method is used to remove the task type from input
     *
     * @param input string given by the user
     * @return remaining string after task type is removed.
     */
    public static String rmType(String input) throws DukeException {
        String[] temp = input.split(" ");
        if (temp.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a " +  temp[0] +  " cannot be empty.");
        }
        String[] newTemp = new String[temp.length - 1];
        for (int i = 1; i < temp.length; i++) {
            newTemp[i - 1] = temp[i];
        }
        String adjInput = String.join(" ", newTemp);
        return adjInput;
    }

    /**
     * This method is used to extract date or task depending on the integer
     * passed into the method. Do note that the input string passed in should
     * be the result from rmType method.
     * 1 denotes date, 2 denotes task name
     *
     * @param input string given by the user
     * @return remaining string after task type is removed.
     */
    public static String extractDateORTask(String input, int integer) throws DukeException {
        String[] temp = input.split(" /");
        if (temp.length == 1) {
            throw new DukeException("☹ OOPS!!! Remember to specify your date");
        }
        if (integer == 1) {
            return temp[1];
        } else {
            return temp[0];
        }
    }
}
