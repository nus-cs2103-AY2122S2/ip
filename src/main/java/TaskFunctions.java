import java.util.*;

public class TaskFunctions {

    private static final int SIZE = 100;
    private static Task[] taskList = new Task[SIZE];
    private static int pointer = 0;

    public static void checkInputValidity(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please input a command");
        } else {
            String[] input_split = input.split(" ", 2);
            if (input_split.length == 1 && input_split.equals("todo") || input_split.equals("deadline") ||
                    input_split.equals("event") || input_split.equals("mark") || input_split.equals("unmark")) {
                throw new DukeException("☹ OOPS!!! The description of a " + input_split[0] + " cannot be empty.");
            }
            if (input_split[0].equals("event")) {
                if (!input.contains("/at")) {
                    throw new DukeException("☹ OOPS!!! Please use the /at command for an event input");
                }
                if (input.split("event ")[1].split(" ", 2)[0].equals("/at")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + input_split[0] + " cannot be empty.");
                }
            }
            if (input_split[0].equals("deadline")) {
                if (!input.contains("/by")) {
                    throw new DukeException("☹ OOPS!!! Please use the /by command for an event input");
                }
                if (input.split("deadline ")[1].split(" ", 2)[0].equals("/by")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + input_split[0] + " cannot be empty.");
                }
            }
            if (input_split[0].equals("mark") || input_split[0].equals("unmark")) {
                if (input_split.length > 2) {
                    throw new DukeException("☹ OOPS!!! Please make sure there is only one number following the " +
                            input_split[0] + " command");
                }
                try {
                    Integer.parseInt(input_split[1]);
                } catch (NumberFormatException err) {
                    throw new DukeException("☹ OOPS!!! Please make sure to input only one integer following the " +
                            input_split[0] + " command");
                }
            }
        }
    }

    public static void addToList(Task task) {

        String task_type = task.type;

        if (pointer == 99) {
            System.out.println("Task list is full!");
        } else {
            taskList[pointer++] = task;
            System.out.println("Got it. I've added this task: \n" + task.toString()
                    + "\nNow you have " + pointer + " tasks in the list.");
        }
    }

    public static void markTask(int position) {
        if (position < 1 || position - 1 > pointer) {
            System.out.println("Task do not exist!");
        } else if (taskList[position - 1].isDone == true) {
            System.out.println("Task is already marked as done!");
        } else {
            taskList[position - 1].mark();
            System.out.println("Nice! I've marked this task as done: \n" +
                    taskList[position - 1].toString());
        }
    }

    public static void unmarkTask(int position) {
        if (position < 1 || position - 1 > pointer) {
            System.out.println("Task do not exist!");
        } else if (taskList[position - 1].isDone == false) {
            System.out.println("Task is already marked as not done!");
        } else {
            taskList[position - 1].unmark();
            System.out.println("OK, I've marked this task as not done yet: \n" +
                    taskList[position - 1].toString());
        }
    }

    public static void showTaskList() {
        if (pointer == 0) {
            System.out.println("You have not added any tasks!");
        } else {
            for (int i = 0; i < pointer; i++) {
                System.out.println(i + 1 + "." + taskList[i].toString());
            }
        }
    }

}
