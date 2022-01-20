import java.util.Scanner;

public class Duke {
    private static final String br = "\t____________________________________________________________\n";

    private static void greeting() {
        String sentences = "\tHello! I'm Duke\n\tWhat can I do for you?\n";
        System.out.println(Duke.br + sentences + Duke.br + "\n");
    }

    private static void bye() {
        String sentences = "\tBye. Hope to see you again soon!\n";
        System.out.println(Duke.br + sentences + Duke.br + "\n");
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String input;

        Duke.greeting();
        input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(Duke.br + "\t" + input + "\n" + Duke.br + "\n");
            input = sc.nextLine();
        }
        Duke.bye();
    }
}
