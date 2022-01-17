import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Boot message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(wrap(logo + "\n" + intro));

        // Setup scanner for user input
        Scanner sc = new Scanner(System.in);

        // Store items in agenda
        List<String> agenda = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < agenda.size(); i++) {
                    result.append(i + 1).append(". ").append(agenda.get(i)).append("\n");
                }
                System.out.println(wrap(result.toString()));
            } else {
                agenda.add(input);
                System.out.println(wrap("added: " + input + "\n"));
            }
        }

        System.out.println(wrap("Bye. Hope to see you again soon!\n"));
        sc.close();
    }

    public static String wrap(String text) {
        return "__________________________________________________\n"
                + text
                + "__________________________________________________\n";
    }
}
