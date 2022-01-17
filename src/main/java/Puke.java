import java.util.Scanner;

public class Puke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| |_| | | | | |/ / _ \\\n"
                    + "| |__/| |_| |   <  __/\n"
                    + "|_|    \\__,_|_|\\_\\___|\n";

        System.out.printf("____________________________________________________________\n"
                    + logo + "Hello! I'm Puke, your friendly neighbourhood chatbot!\n"
                    + "What do you want to do?\n"
                    + "____________________________________________________________\n");

        while (true) {
            System.out.printf("> ");
            String input = sc.nextLine();
            if (input.equals("bye")) break;

            System.out.println(input + "\n____________________________________________________________");
        }
        System.out.println("Goodbye. Come back anytime!\n____________________________________________________________");
    }
}
