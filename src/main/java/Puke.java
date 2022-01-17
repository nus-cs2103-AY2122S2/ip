import java.util.Scanner;
import java.util.ArrayList;

public class Puke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = "   ____        _        \n"
                    + "  |  _ \\ _   _| | _____ \n"
                    + "  | |_| | | | | |/ / _ \\\n"
                    + "  | |__/| |_| |   <  __/\n"
                    + "  |_|    \\__,_|_|\\_\\___|\n";

        System.out.printf("____________________________________________________________\n"
                    + logo + "Hello! I'm Puke, your friendly neighbourhood chatbot!\n"
                    + "What do you want to do?\n"
                    + "____________________________________________________________\n");

        ArrayList<String> items = new ArrayList<>();

        while (true) {
            System.out.printf("> ");
            String input = sc.nextLine();

            if (input.equals("bye")) break;
            else if (input.equals("list")) list_items(items);
            else {
                System.out.println("added: " + input);
                items.add(input);
            }

            System.out.println("____________________________________________________________");
        }
        System.out.println("Alright bye. Come back to Puke anytime!\n____________________________________________________________");
    }

    public static void list_items(ArrayList<String> lst) {
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + ". " + lst.get(i));
        }
    }
}
