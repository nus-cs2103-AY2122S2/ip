import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChatBot duke = new ChatBot("Duke");
        duke.greet();
        String input;
        boolean quit = false;
        while(!quit) {
            try {
                quit = duke.runCommand(sc.nextLine());
            } catch (DukeException e) {
                duke.echo(e.getMessage());
            }
        }
        sc.close();
    }
}
