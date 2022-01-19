import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> taskList;
    private static final String goodbye = "\tBye. Hope to see you again soon!";
    private static final String greeting = "\n\tHello! I'm Duke\n\tWhat can I do for you?\n";

    public static void main(String[] args) {

        taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println(greeting);
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            String[] arr = input.trim().split(" ", 2);
            String cmd = arr[0];
            String var = arr.length < 2 ? "" : arr[1];

            switch (cmd) {

                case "list":

                    if (taskList.size() == 0)
                        System.out.println("\tEmpty list");

                    else {
                        for (int i = 1; i < taskList.size()+1; i++)
                            System.out.println("\t" + i + ". " + taskList.get(i-1).toString());
                    }

                    System.out.println();
                    break;

                case "mark":
                    marking(var, true);
                    break;

                case "unmark":
                    marking(var, false);
                    break;

                case "event":
                    String[] e = var.trim().split("/at", 2);

                    try {
                        checkInput(e, "/at");
                    }

                    catch (DukeException ex) {
                        System.out.println("\t" + ex + "\n");
                        break;
                    }

                    createNewTask(new Event(e[0], e[1]));
                    break;

                case "deadline":
                    String[] d = var.trim().split("/by", 2);

                    try {
                        checkInput(d, "/by");
                    }

                    catch (DukeException ex) {
                        System.out.println("\t" + ex + "\n");
                        break;
                    }

                    createNewTask(new Deadline(d[0], d[1]));
                    break;

                case "todo":
                    if (var.equals(""))
                        System.out.println("\tNo task to do\n");

                    else
                        createNewTask(new ToDo(var));

                    break;

                case "delete":
                    try {
                        int idx = checkList(var);
                        printOutput(taskList.get(idx), false);
                        taskList.remove(idx);
                    }

                    catch (DukeException ex) {
                        System.out.println("\t" + ex + "\n");
                    }

                    break;

                default:
                    System.out.println("\tWrong command\n");
            }

            input = sc.nextLine();
        }

        System.out.println(goodbye);
    }

    private static void createNewTask(Task task) {
        taskList.add(task);
        printOutput(task, true);
    }

    private static void marking(String str_idx, boolean state) {

        try {
            int idx = checkList(str_idx);
            taskList.get(idx).setCompleted(state);
            String reply = "\tTask marked as " + (state ? "done." : "not done yet.") + "\n";
            System.out.println(reply + "\t\t" + str_idx + ". " + taskList.get(idx).toString() + "\n");
        }

        catch (DukeException ex) {
            System.out.println("\t" + ex + "\n");
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

    private static int checkList(String str_idx) throws DukeException {

        try {
            int idx = Integer.parseInt(str_idx) - 1;

            if (!(idx >= 0 && idx < taskList.size())) {
                throw new DukeException("Task does not exist");
            }

            return idx;
        }

        catch (NumberFormatException e) {
            throw new DukeException("There is no index");
        }
    }

    private static void printOutput(Task task, boolean isAdd) {
        String out = "\tGot it. I've " + (isAdd ? "added" : "deleted") + " this task:\n";
        System.out.println(out + "\t\t" + task);
        System.out.println("\tNow you have "+ taskList.size() +" tasks in the list.\n");
    }
}