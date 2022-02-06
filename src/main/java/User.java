import java.util.Scanner;

public class User {
    public static void start() {
        Action.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Parser parser = new Parser();
        while(true) {
            String displayMessage = parser.parse(input);
            System.out.println(displayMessage);
            if (input.equals("bye")) {
                break;
            }
            input = sc.nextLine();
        }
        sc.close();
    }
}
