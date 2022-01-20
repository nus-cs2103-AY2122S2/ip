import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    private static final String br = "\t____________________________________________________________\n";
    private static List<String> lst = new ArrayList<String>();

    private static void greeting() {
        String sentences = "\t Hello! I'm Duke\n\t What can I do for you?\n";
        System.out.println(Duke.br + sentences + Duke.br);
    }

    private static void bye() {
        String sentences = "\t Bye. Hope to see you again soon!\n";
        System.out.println(Duke.br + sentences + Duke.br);
    }

    private static void list() {
        System.out.print(Duke.br);
        for (int i = 1; i <= lst.size(); i++) {
            System.out.println("\t " + i + ". " + lst.get(i-1));
        }
        System.out.println(Duke.br);
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String input;

        Duke.greeting();
        input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                Duke.list();
                input = sc.nextLine();
                continue;
            }
            Duke.lst.add(input);
            System.out.println(Duke.br + "\t added: " + input + "\n" + Duke.br);
            input = sc.nextLine();
        }
        Duke.bye();
    }
}
