import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> database = new ArrayList<>();
        String line = "_______________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = line + "\nHello! I am YQ\n" + "What can I do for you?\n" + logo + line;
        System.out.println(intro);

        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!input.equals("bye")) {
            database.add(input);
            for (int i = 0; i < database.size(); i++) {
                System.out.println(i + 1 + ". " + database.get(i));
            }
            System.out.println(line);
            input = sc.nextLine();
        }
        System.out.println(line + "\n" + "Bye. Hope to see you again soon!" + "\n" + line);
    }
}
