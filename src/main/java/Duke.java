import java.io.*;
import java.util.*;


/**
 * This program is used to add, list & mark the status of your current tasks.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class Duke {
    // ArrayList to store all your tasks
    private static ArrayList<Task> list = new ArrayList<>();

    // Main method of this program
    public static void main(String[] args) {
        // Start Message
        System.out.println("IF YOU ARE NEW TO THIS PROGRAM, ENTER ? TO SEE A LIST OF AVAILABLE COMMANDS.");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("HELLO! I'M DUKE");
        System.out.println("WHAT CAN I DO FOR YOU?");

        // Scanner Object
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            while (!input.equals("bye")) {
                String[] temp = input.split(" ");
                if (temp[0].equals("?")) {
                    helpCmd();
                } else if (temp[0].equals("mark")) {
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
                } else if (temp[0].equals("delete")) {
                    int index = Integer.parseInt(temp[1]) - 1;
                    Duke.deleteTask(index);
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

    /**
     *  Prints out a list of available commands in this program.
     */
    public static void helpCmd() {
        System.out.println("LIST OF AVAILABLE COMMANDS IN THIS PROGRAM      DESCRIPTION");
        System.out.println("list                                            List out all added tasks");
        System.out.println("todo {task description}                         Add ToDo into list");
        System.out.println("deadline {task description} /by {DATE}          Add Deadline into list");
        System.out.println("event {task description} /at {DATE}             Add Event into list");
        System.out.println("mark {Task ID}                                  Mark specific task as done");
        System.out.println("unmark {Task ID}                                Mark specific task as not done");
        System.out.println("delete {Task ID}                                Delete specific task from list");
    }

    /**
     * Prints out all the tasks in the list.
     */
    public static void list() {
        int num = 1;
        System.out.println("HERE ARE THE TASKS IN YOUR LIST:");
        for (Task task : list) {
            System.out.println(num + "." + task);
            num++;
        }
    }

    /**
     * Adds to do task into our current list.
     *
     * @param input string entered by the user.
     * @throws DukeException if task description is empty.
     */
    public static void addToDo(String input) throws DukeException {
        String[] str = input.split(" ");
        if (str.length == 1) {
            throw new DukeException("PLEASE INCLUDE TASK DESCRIPTION IN YOUR COMMAND.");
        }
        String desc = str[1];
        for (int i = 2; i < str.length; i++) {
            desc = desc + " " + str[i];
        }
        Task newTask = new ToDo(desc);
        list.add(newTask);
        System.out.println("GOT IT. I'VE ADDED THIS TASK:");
        System.out.println(newTask);
        System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
    }

    /**
     * Adds deadline task into our current list.
     *
     * @param input string entered by the user.
     * @throws DukeException if task description or date is empty.
     */
    public static void addDeadline(String input) throws DukeException {
        String[] str = input.split(" /by ");
        String[] str2 = str[0].split(" ");
        if (str2.length == 1) {
            throw new DukeException("PLEASE INCLUDE TASK DESCRIPTION IN YOUR COMMAND.");
        }
        if (str.length == 1) {
            throw new DukeException("PLEASE INCLUDE THE DATE IN YOUR COMMAND.");
        }
        String desc = str2[1];
        for (int i = 2; i < str2.length; i++) {
            desc = desc + " " + str2[i];
        }
        Task newTask = new Deadline(desc, str[1]);
        list.add(newTask);
        System.out.println("GOT IT. I'VE ADDED THIS TASK:");
        System.out.println(newTask);
        System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
    }

    /**
     * Adds event task into our current list.
     *
     * @param input string entered by the user.
     * @throws DukeException if task description or date is empty.
     */
    public static void addEvent(String input) throws DukeException {
        String[] str = input.split(" /at ");
        String[] str2 = str[0].split(" ");
        if (str2.length == 1) {
            throw new DukeException("PLEASE INCLUDE TASK DESCRIPTION IN YOUR COMMAND.");
        }
        if (str.length == 1) {
            throw new DukeException("PLEASE INCLUDE THE DATE IN YOUR COMMAND.");
        }
        String desc = str2[1];
        for (int i = 2; i < str2.length; i++) {
            desc = desc + " " + str2[i];
        }
        Task newTask = new Event(desc, str[1]);
        list.add(newTask);
        System.out.println("GOT IT. I'VE ADDED THIS TASK:");
        System.out.println(newTask);
        System.out.println("NOW YOU HAVE "+ list.size() + " TASKS IN THE LIST.");
    }

    /**
     * This method changes the status of a particular task to Done
     * in the list.
     *
     * @param index position of the task in the list
     * @throws DukeException if position of the task exceeds what we have on the list
     */
    public static void markTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'M SORRY, CAN'T FIND TASK");
        } else {
            Task task = list.get(index);
            task.setAsDone();
            System.out.println("NICE! I'VE MARKED THIS TASK AS DONE:");
            System.out.println(task);
        }
    }

    /**
     * This method changes the status of a particular task to Not Done
     * in the list.
     *
     * @param index position of the task in the list
     * @throws DukeException if position of the task exceeds what we have on the list
     */
    public static void unMarkTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'M SORRY, CAN'T FIND TASK");
        } else {
            Task task = list.get(index);
            task.setAsNotDone();
            System.out.println("NICE! I'VE MARKED THIS TASK AS DONE:");
            System.out.println(task);
        }
    }


    /**
     * This method deletes Task from our list
     *
     * @param index the position of the task
     * @throws DukeException if position of the task exceeds what we have on the list
     */
    public static void deleteTask(int index) throws DukeException {
        if (index >= list.size()) {
            throw new DukeException("☹ OOPS!!! I'M SORRY, CAN'T FIND TASK");
        } else {
            Task task = list.remove(index);
            System.out.println("NOTED. I'VE REMOVED THIS TASK:");
            System.out.println(task);
            System.out.println("NOW YOU HAVE \"+ list.size() + \" TASKS IN THE LIST.");
        }
    }

    /**
     * This method handles the case where a random input is supplied by the user
     *
     * @throws DukeException if add type description is empty
     */
    public static void notSpecified() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

}
