package duke;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        while(parser.isPolling) {
            String input = sc.nextLine();
            parser.inputHandler(input);
        }

        sc.close();
    }
}
