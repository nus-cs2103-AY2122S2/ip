import java.util.Scanner;

public class Ui {
    public void showLoadingError() {
        System.out.println("____________________________________________________________");
        System.err.println(new LoadException());
        System.out.println("____________________________________________________________");
    }

    public void showWriteError() {
        System.out.println("____________________________________________________________");
        System.err.println(new WriteException());
        System.out.println("____________________________________________________________");
    }

    public void start(TaskList tasks) {
        Scanner sc = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Ron\nWhat can I do for you?");
        System.out.println("____________________________________________________________");

        String input = sc.nextLine();
        InputParser inputParser = new InputParser();

        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            try {
                inputParser.parseInput(input, tasks);
            } catch (RonException e) {
                System.err.println(e);
            }
            System.out.println("____________________________________________________________");
            if (!sc.hasNext()) {
                break;
            }
            input = sc.nextLine();
        }

        try {
            tasks.backup();
        } catch (RonException e) {
            this.showWriteError();
        }
        System.out.println("Bye. Stay safe and have a nice day!");
    }
}
