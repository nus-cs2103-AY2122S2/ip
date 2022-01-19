import java.util.*;
import java.io.*;

public class Duke {

    public static final String hl = "------------------------------------------------------------------------";
    public static ArrayList<String> task_list = new ArrayList<String>();

    public static void greetings() {
        String logo = "";
        System.out.println(hl + "\nHi! I'm Duke\nWhat can I do for you?");
    }

    public static void add(String ipt) {
        task_list.add(ipt);
        System.out.println("added: " + ipt);
    }

    public static void list() {
        if (task_list.size() == 0) {
            System.out.println("You have no tasks!");
        } else {
            for (int i = 0; i < task_list.size(); i++) {
                int task_number = i + 1;
                System.out.println(task_number + ". " + task_list.get(i));
            }
        }
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
            String input = br.readLine();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                list();
            } else {
                add(input);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        greetings();

        chat();
    }
}
