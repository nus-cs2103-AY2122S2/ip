package duke;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Parser dukeEngine = new Parser();

        while(dukeEngine.isPolling) {
            String input = sc.nextLine();
            dukeEngine.inputHandler(input);
        }

        sc.close();
    }
}
