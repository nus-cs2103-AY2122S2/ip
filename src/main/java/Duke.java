import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Ron\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        String input = sc.nextLine();
        String bye = "bye";
        String list = "list";
        List<String> store = new ArrayList<>();

        while (!input.equals(bye)) {
            System.out.println("____________________________________________________________");
            if (input.equals(list)) {
                for (int i = 0; i < store.size(); i++) {
                    System.out.println((i + 1) + ". " + store.get(i));
                }
            } else {
                System.out.println("added: " + input);
                store.add(input);
            }
            System.out.println("____________________________________________________________");
            input = sc.nextLine();
        }

        System.out.println("Bye. Stay safe and have a nice day!");
    }
}
