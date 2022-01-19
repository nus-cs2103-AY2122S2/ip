import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> arr = new ArrayList<>();

        System.out.println("____________________________________________________________" + '\n'
                + "Hello! I'm Duke" + '\n'
                + "What can I do for you?" + '\n'
                + "____________________________________________________________");


        while (sc.hasNext()) {
            String curr = sc.nextLine();

            if (curr.equals("list")) {
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(i + 1 + ". " + arr.get(i));
                }
            } else if (curr.equals("bye")) {
                break;
            } else {
                arr.add(curr);
                System.out.println("added: " + curr);
            }
        }
        System.out.println("____________________________________________________________" + '\n'
                + "Bye. Hope to see you again soon!" + '\n'
                + "____________________________________________________________");
    }
}
