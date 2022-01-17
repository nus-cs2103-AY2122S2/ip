import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> list = new ArrayList<>();
    private String hLine = "____________________________________________________________";

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

        if (userInput.length > 1) {
            temp = userInput[0] + " " + userInput[1];
        } else {
            temp = userInput[0];
        }

        list.add(new Task(temp));
        System.out.println("added: " + temp);
    }

    public void readList() {
        System.out.println("Here are the task in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". [" + list.get(i).getMark() + "] " + list.get(i).getUserInput());
        }
    }

    public class Task {
        private boolean marked;
        private String description;

        Task(String description) {
            this.marked = false;
            this.description = description;
        }

        public void markTask(boolean mark) {
            this.marked = mark;
            String output;

            if (mark) {
                output = "Nice! I've marked this task as done: \n";
            } else {
                output = "OK, I've marked this task as not done yet: \n";
            }
            System.out.println(output + "[" + getMark() + "] " + getUserInput());
        }

        public char getMark() {
            return this.marked ? 'X' : ' ';
        }

        public String getUserInput() {
            return this.description;
        }
    }
}
