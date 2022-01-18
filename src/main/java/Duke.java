import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bot bot = new Bot();
        bot.greet();
        while (true) {
            String input = sc.nextLine();
            boolean end = bot.respond(input);
            if (end) {
                break;
            }
        }
    }
}
