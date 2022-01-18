import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChatBot duke = new ChatBot("Duke");
        duke.greet();
        String input;
        boolean quit = false;
        while(!quit) {
            quit = duke.runCommand(sc.nextLine());
        }
        sc.close();
    }
}
