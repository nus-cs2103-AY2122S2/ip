import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("========================");

        ArrayList<String> list = new ArrayList<>(100);

        while(true) {
            System.out.print("Me   : ");
            String message = sc.nextLine();
            if (message.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (message.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    int num = i + 1;
                    System.out.print(num + ". ");
                    System.out.println(list.get(i));
                }

            } else {
                list.add(message);
                System.out.println("Duke has added :" + message);
            }
        }
        sc.close();
    }
}
