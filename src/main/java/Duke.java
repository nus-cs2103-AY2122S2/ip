import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final ArrayList<Task> list = new ArrayList<>();
    private final String hLine = "____________________________________________________________";

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
            String[] input = scanner.nextLine().split(" ", 2);

            duke.echo(input);

            if (input[0].equals("bye")) {
                break;
            }
        }
    }

    public void echo(String userInput) {
        System.out.println(hLine + "\n" + userInput + "\n" + hLine);
    }

    public void echo(String[] userInput) {
        System.out.println(hLine + "\n");
        switch (userInput[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                readList();
                break;
            case "mark":
                list.get(Integer.parseInt(userInput[1]) - 1).markTask(true);
                break;
            case "unmark":
                list.get(Integer.parseInt(userInput[1]) - 1).markTask(false);
                break;
            default:
                addList(userInput);
        }
        System.out.println(hLine + "\n");
    }

    public void addList(String[] userInput) {
        String temp;
        Task task;

        if (userInput.length > 1) {
            String[] strings = userInput[1].split("/");

            switch (userInput[0]) {
                case "deadline":
                    task = new Deadline(strings[0], strings[1]);
                    break;
                case "event":
                    task = new Event(strings[0], strings[1]);
                    break;
                case "todo":
                    task = new Todo(userInput[1]);
                    break;
                default:
                    temp = userInput[0] + " " + userInput[1];
                    task = new Task(temp);
            }
        } else {
            temp = userInput[0];
            task = new Task(temp);
        }

        list.add(task);
        System.out.println("Got it. I have added this task:\n" + task
                + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public void readList() {
        System.out.println("Here are the task in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }
}
