import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMsg = "Hello! I'm Duke\nWhat can I do for you?";
        String byeMsg = "Bye. Hope to see you again soon!";
        DukeList dL = new DukeList();

        boolean echo = true;
        Scanner sc = new Scanner(System.in);

        System.out.println(message(welcomeMsg));
        while (echo) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                dL.printItems();
            } else if (input.equals("bye")){
                System.out.println(message(input));
                echo = false;
            } else {
                dL.add(input);
            }
        }
    }

    public static String message(String inp) {
        String line = "____________________________________________________________";
        if (inp.equals("bye")) {
            inp = "Bye. Hope to see you again soon!";
        }
        String res = line + "\n" + inp + "\n" + line;
        return res;
    }

}
