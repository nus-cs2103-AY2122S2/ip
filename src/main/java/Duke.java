import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm JiaMing\nWhat can I do for you? ");
        System.out.println("____________________________________________________________");
        while (sc.hasNext()) {
            String word = sc.next();
            if (word.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(word);
            System.out.println("____________________________________________________________");
        }
    }
}
