import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChatBot duke = new ChatBot("Duke");
        duke.greet();
        String input;
        boolean quit = false;
        while(!quit) {
            input = sc.next();
            switch (input.toLowerCase()) {
                case "bye":
                    duke.quit();
                    quit = true;
                    break;

                case "list":
                    duke.printTasks();
                    break;

                case "mark":
                    duke.markTask(sc.nextInt() - 1);
                    sc.nextLine();
                    break;

                case "unmark":
                    duke.unmarkTask(sc.nextInt() - 1);
                    sc.nextLine();
                    break;

                default:
                    duke.addTask(input + sc.nextLine());
                    break;
            }
        }
        sc.close();
    }
}
