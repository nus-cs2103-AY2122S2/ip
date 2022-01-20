import java.util.*;
public class Duke {
    static Scanner sc = new Scanner(System.in);
    static String line = "\n---------------------\n";
    public static void main(String[] args) {
        String logo = " ____         _   _     \n"
                    + "|  _ \\       | | | |\n"
                    + "| |_| |      | |-| |\n"
                    + "| |_| |  _   | |-| |\n"
                    + "|____/  |_|  |_| |_|\n";
        System.out.println("Hello, I am B.H. How can I help you?\n" + logo + line);

        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye") || input.equals("Bye")) {
                System.out.println(line + "GoodBye! Thanks for using B.H!" + line);
                break;
            } else {
                System.out.println(line + input + line);
            }
        }
    }
}
