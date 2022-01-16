import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChatBot duke = new ChatBot("Duke");
        duke.greet();
        String input;
        while(true) {
            System.out.print("Command: ");
            input = sc.nextLine();
            if (input.toLowerCase().equals("bye")) {
                duke.quit();
                break;
            }
            duke.echo(input);
        }
        sc.close();
    }
}
