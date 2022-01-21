import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, this is Belvedere, your virtual assistant!");
        printLine();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (true) {
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
            printLine();
            input = scanner.nextLine();
        }
    }
    // Static method to print a line in the terminal to seperate calls
    public static void printLine() {
        System.out.println("------------------------------");
    }
}
