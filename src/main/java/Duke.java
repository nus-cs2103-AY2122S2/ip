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

        BH bh = new BH();

        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye") || input.equals("Bye")) {
                System.out.println(line + "GoodBye! Thanks for using B.H!" + line);
                break;
            } else if (input.equals("list")) {
                System.out.println(bh.getList());
            } else {
                bh.addToList(input);
                System.out.println(line + "added: " + input + line);
            }
        }
    }
}
