import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int num = 1;
        String welcomeMsg = "Hello! I'm Duke\nWhat can I do for you?";
        String byeMsg = "Bye. Hope to see you again soon!";
        DukeList dL = new DukeList();

        boolean echo = true;
        Scanner sc = new Scanner(System.in);

        System.out.println(message(welcomeMsg));
        while (echo) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println(dL.printTasks());
            } else if (input.equals("bye")){
                System.out.println(message(input));
                echo = false;
            } else {
                String[] splitter = input.split(" ");
                if (splitter[0].equals("mark")) {
                    System.out.println(dL.mark(Integer.valueOf(splitter[1])));
                } else if (splitter[0].equals("unmark")) {
                    System.out.println(dL.unmark(Integer.valueOf(splitter[1])));
                } else {
                    Task t = new Task(num, input, false);
                    System.out.println(dL.add(t));
                    num ++;
                }
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
