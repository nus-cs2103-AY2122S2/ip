import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String greeting = "\tHello! I'm Duke\n\tWhat can I do for you?\n";
    public static final String goodbye = "\tBye. Hope to see you again soon!";

    public static void main(String[] args) {

        System.out.println(greeting);

        ArrayList<String> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {

            switch (input) {

                case "list":
                    for (int i = 0; i < taskList.size(); i++) {
                        int idx = i + 1;
                        System.out.println("\t" + idx + ". " + taskList.get(i));
                    }
                    System.out.println();
                    break;

                default:
                    taskList.add(input);
                    System.out.println("\t added: "+input+"\n");
            }

            input = sc.nextLine();
        }

        System.out.println(goodbye);
    }
}
