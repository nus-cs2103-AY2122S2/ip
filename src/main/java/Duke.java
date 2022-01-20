import java.util.*;
import java.io.*;

public class Duke {

    public static final String hl = "------------------------------------------------------------------------";
    public static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void greetings() {
        String logo = "";
        System.out.println(hl + "\nHi! I'm Duke\nWhat can I do for you?");
    }

    public static void add(String ipt) {
        Task newTask = new Task(ipt, taskList.size() + 1);
        taskList.add(newTask);
        System.out.println("added: " + ipt);
    }

    public static void list() {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks!");
        } else {
            System.out.println("The tasks on your list. Get it done!");
            for (Task task : taskList) {
                System.out.println(task);
            }
        }
    }

    public static void mark(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
        System.out.println("Good job! This task is done:");
        System.out.println(task);
    }

    public static void unmark(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.unmark();
        System.out.println("Hurry up and get it done!");
        System.out.println(task);
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(hl);
    }

    public static void chat() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println(hl);
            System.out.print("> ");
            String userInput = br.readLine();
            StringTokenizer st = new StringTokenizer(userInput);
            String instruction = st.nextToken();
            if (instruction.equals("bye")) {
                bye();
                break;
            } else if (instruction.equals("list")) {
                list();
            } else if (instruction.equals("mark")) {
                int taskNumber = Integer.parseInt(st.nextToken());
                mark(taskNumber);
            } else if (instruction.equals("unmark")) {
                int taskNumber = Integer.parseInt(st.nextToken());
                unmark(taskNumber);
            } else {
                add(userInput);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        greetings();

        chat();
    }
}
