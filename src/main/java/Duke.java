import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Duke {
    public static ArrayList<Task> manager = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        addLineBreak();

        while (true) {
            String instruct = br.readLine();
            if (instruct.equals("bye")) {
                endSession();
                break;
            } else if (instruct.equals("list")) {
                reportList();
            } else if (instruct.startsWith("mark")) {
                String[] details = instruct.split(" ");
                markAsDone(Integer.parseInt(details[1]));
            } else if (instruct.startsWith("unmark")) {
                String[] details = instruct.split(" ");
                markNotDone(Integer.parseInt(details[1]));
            } else {
                addTask(instruct);
            }
            addLineBreak();
        }

        br.close();
    }

    public static void greet() {
        String welcome = "Hi my name is Duke!";
        String assist = "How may I help you today?";
        System.out.println(welcome);
        System.out.println(assist);
    }

    public static void endSession() {
        String goodbye = "Adios! See you soon:)";
        System.out.println(goodbye);
    }

    public static void addLineBreak() {
        System.out.println("---------------------xx-------------------------");
    }

    public static void reportList() {
        int len = manager.size();
        for (int i = 0; i < len; i++) {
            System.out.println((i + 1) + ". " + manager.get(i).toString());
        }
    }

    public static void addTask(String instruct) {
        manager.add(new Task(instruct));
        System.out.println("added: " + instruct);
    }

    public static Task findTask(int num) {
        Task desiredTask = new Task("empty task");
        for (int i = 1; i <= manager.size(); i++) {
            if (i == num) {
                desiredTask = manager.get(i - 1);
            }
        }
        return desiredTask;
    }

    public static void markAsDone(int num) {
        Task t = findTask(num);
        t.markDone();
        System.out.println("Congrats! Keep going:)");
        System.out.println(t.toString());
    }

    public static void markNotDone(int num) {
        Task t = findTask(num);
        t.undo();
        System.out.println("No worries:) Stay motivated!");
        System.out.println(t.toString());
    }
}
