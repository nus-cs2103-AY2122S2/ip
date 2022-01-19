import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String curr = sc.next();

            if (curr.equals("bye")) {
                break;
            } else {
                System.out.println("____________________________________________________________" + '\n'
                        + curr + '\n'
                        + "____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________" + '\n'
                + "Bye. Hope to see you again soon!" + '\n'
                + "____________________________________________________________");
    }
}
