import java.util.Scanner;

public class Duke {
    private static final String botName = "Feline";

    public Duke() {

    }
    private static void greet() {
        System.out.println("Yoooo! My name is " + botName + "!\n" + "How can I help you bro?\n");
    }
    private static void farewell() {
        System.out.println("See you next time!\n");
    }
    public static void main(String[] args) {

        Duke.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
//            if (input.equals(list)) {
//
//            }
            System.out.println("added: " + input + "\n");
            input = sc.nextLine();
        }
        sc.close();
        Duke.farewell();

    }
}
