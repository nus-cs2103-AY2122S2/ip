import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Duke duke = new Duke();
        duke.echo(logo + "\nHello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            duke.echo(input);

            if (input.equals("bye")) {
                break;
            }
        }
    }

    public void echo(String userInput) {
        String hLine = "____________________________________________________________";
        System.out.println(hLine);

        switch (userInput) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                readList();
                break;
            default:
                addList(userInput);
        }
        System.out.println(hLine + "\n");
    }

    public void addList(String userInput) {
        list.add(userInput);
        System.out.println("added: " + userInput);
    }

    public void readList() {
        for (int i = 1; i < list.size(); i++) {
            System.out.println(i + ". " + list.get(i));
        }
    }
}
