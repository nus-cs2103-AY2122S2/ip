import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String newline = "----------------------------------------------------\n";
        System.out.println("\n" + newline + newline + logo);
        System.out.println("Hello there! I'm Duke, your very own Personal Assistant ChatBot.\nHow may I help you?");
        System.out.println(newline);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(newline + "Goodbye! See you soon. :)\n" + newline);
                break;
            } else {
                System.out.println(newline + input + "\n" + newline);
            }
        }
    }
}
