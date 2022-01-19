import java.util.*;

public class TaskFunctions {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void checkInputValidity(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please input a command");
        } else {
            String[] input_split = input.split(" ", 2);
            if (input_split.length == 1) {
                if (input_split[0].equals("todo") || input_split[0].equals("deadline") ||
                        input_split[0].equals("event") || input_split[0].equals("mark") || input_split[0].equals("unmark")
                        || input_split[0].equals("delete")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + input_split[0] + " cannot be empty.");
                }
            } else if (input_split[0].equals("event")) {
                if (!input.contains("/at")) {
                    throw new DukeException("☹ OOPS!!! Please use the /at command for an event input");
                }
                if (input.split("event ")[1].split(" ", 2)[0].equals("/at")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + input_split[0] + " cannot be empty.");
                }
            } else if (input_split[0].equals("deadline")) {
                if (!input.contains("/by")) {
                    throw new DukeException("☹ OOPS!!! Please use the /by command for an event input");
                }
                if (input.split("deadline ")[1].split(" ", 2)[0].equals("/by")) {
                    throw new DukeException("☹ OOPS!!! The description of a " + input_split[0] + " cannot be empty.");
                }
            } else if (input_split[0].equals("mark") || input_split[0].equals("unmark") || input_split[0].equals("delete")) {
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
        taskList.add(task);
        System.out.println("Got it. I've added this task: \n" + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public static void markTask(int position) throws DukeException {
        if (position < 1 || position - 1 > taskList.size()) {
            throw new DukeException("Task do not exist!");
        } else if (taskList.get(position - 1).isDone == true) {
            throw new DukeException("Task is already marked as done!");
        } else {
            taskList.get(position - 1).mark();
            System.out.println("Nice! I've marked this task as done: \n" +
                    taskList.get(position - 1).toString());
        }
    }

    public static void unmarkTask(int position) throws DukeException {
        if (position < 1 || position - 1 > taskList.size()) {
            throw new DukeException("Task do not exist!");
        } else if (taskList.get(position - 1).isDone == false) {
            throw new DukeException("Task is already marked as not done!");
        } else {
            taskList.get(position - 1).unmark();
            System.out.println("OK, I've marked this task as not done yet: \n" +
                    taskList.get(position - 1).toString());
        }
    }


    public static void deleteTask(int position) throws DukeException {
        if (position < 0 || position - 1 > taskList.size()) {
            throw new DukeException("Task do not exist!");
        } else {
            Task task = taskList.get(position - 1);
            taskList.remove(position - 1);
            System.out.println("Noted. I've removed this task:\n" +
                    task.toString() +
                    "\nNow you have 4 tasks in the list.");
        }
    }

    public static void showTaskList() {
        if (taskList.size() == 0) {
            System.out.println("You have not added any tasks!");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + "." + taskList.get(i).toString());
            }
        }
    }

}
