import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChatBot duke = new ChatBot("Duke");
        duke.greet();
        String input;
        boolean quit = false;
        while(!quit) {
            input = sc.nextLine();
            switch (input.toLowerCase()) {
                case "bye":
                    duke.quit();
                    quit = true;
                    break;

                case "list":
                    duke.printTasks();
                    break;

                default:
                    duke.addTask(input);
                    break;
            }
        }
        sc.close();
    }
}
