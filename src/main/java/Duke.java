import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static final String goodbye = "\tBye. Hope to see you again soon!";
    public static final String greeting = "\tHello! I'm Duke\n\tWhat can I do for you?\n";

    public static void main(String[] args) {

        System.out.println(greeting);

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            String[] arr = input.trim().split(" ", 2);
            String cmd = arr[0];
            String var = arr.length < 2 ? "" : arr[1];

            switch (cmd) {

                case "list":

                    if (taskList.size() == 0) {
                        System.out.println("\tEmpty list\n");
                    }

                    else {
                        for (int i = 0; i < taskList.size(); i++) {
                            int idx = i + 1;
                            System.out.println("\t" + idx + ". " + taskList.get(i).toString());
                        }
                        System.out.println();
                    }
                    break;

                case "mark":
                    marking(taskList, var, true);
                    break;

                case "unmark":
                    marking(taskList, var, false);
                    break;

                case "event":
                    String[] e = var.trim().split("/at", 2);

                    try {
                        checkInput(e, "/at");
                    }

                    catch (DukeException ex) {
                        System.out.println("\t" + ex + "\n");
                        input = sc.nextLine();
                        continue;
                    }

                    createNewTask(taskList, new Event(e[0], e[1]));
                    break;

                case "deadline":
                    String[] d = var.trim().split("/by", 2);

                    try {
                        checkInput(d, "/by");
                    }

                    catch (DukeException ex) {
                        System.out.println("\t" + ex + "\n");
                        input = sc.nextLine();
                        continue;
                    }

                    createNewTask(taskList, new Deadline(d[0], d[1]));
                    break;

                case "todo":
                    if (var.equals("")) {
                        System.out.println("\tNo task to do\n");
                    }

                    else {
                        createNewTask(taskList, new ToDo(var));
                    }

                    break;

                case "delete":
                    try {
                        int i = Integer.parseInt(var) - 1;
                        Task t = taskList.get(i);
                        taskList.remove(i);
                        System.out.println("\tNoted. I've removed this task:");
                        System.out.println("\t\t" + t);
                        System.out.println("\tNow you have "+ taskList.size() +" tasks in the list.\n");
                    }

                    catch (NumberFormatException ex) {
                        System.out.println("\tThere is no index\n");
                    }

                    catch (IndexOutOfBoundsException ex) {
                        System.out.println("\tTask does not exist\n");
                    }

                    break;

                default:
                    System.out.println("\tWrong command\n");
            }

            input = sc.nextLine();
        }

        System.out.println(goodbye);
    }

    private static void createNewTask(ArrayList<Task> taskList, Task task) {
        taskList.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have "+ taskList.size() +" tasks in the list.\n");
    }

    private static void marking(ArrayList<Task> taskList, String idx, boolean state) {

        try {
            int i = Integer.parseInt(idx) - 1;
            taskList.get(i).setCompleted(state);
            System.out.println(state ? "\tNice! I've marked this task as done:" : "\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t" + idx + ". " + taskList.get(i).toString() + "\n");
        }

        catch (NumberFormatException e) {
            System.out.println("\tThere is no index\n");
        }

        catch (IndexOutOfBoundsException e) {
            System.out.println("\tTask does not exist\n");
        }
    }

    private static void checkInput(String[] str, String type) throws DukeException {

        switch (str.length) {
            case 0:
                throw new DukeException("No task in instruction");

            case 1:
                throw new DukeException(type + " not found");

            case 2:
                if (str[0].equals("") && str[1].equals("")) {
                    throw new DukeException("No task in instruction");
                }

                else if (str[0].equals("")) {
                    throw new DukeException("Description of task not found");
                }

                else if (str[1].equals("")) {
                    throw new DukeException("Datetime of task not found");
                }
        }
    }
}