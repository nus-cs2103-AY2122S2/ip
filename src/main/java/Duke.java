import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DukeEngine dukeEngine = new DukeEngine();

        while(dukeEngine.isPolling) {
            String input = sc.nextLine();
            dukeEngine.inputHandler(input);
        }

        sc.close();
    }
}
