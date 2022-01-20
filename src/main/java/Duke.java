import java.util.Scanner;

public class Duke {

    private static String getFirstWord(String text) {
        int index = text.indexOf(' ');
        String word;
        if (index > -1) {
            word = text.substring(0, index);
        } else {
            word = text;
        }
        return word;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList<Task> taskList = new TaskList<>();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hello! I' Duke \nWhat can I do for you?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (sc.hasNextLine()) {

            String command = sc.nextLine().toLowerCase();
            String firstWord;
            int itemIndex = 0;

            int index = command.indexOf(' ');
            if (index > -1) {
                firstWord = command.substring(0, index);
            } else {
                firstWord = command;
            }

            if (firstWord.equals("mark") || firstWord.equals("unmark") || firstWord.equals("delete")) {
                itemIndex = Integer.parseInt(command.substring(index));
            }

            if (command.equals("bye")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.exit(0);
                sc.close();
            } else if (command.equals("list")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                taskList.list();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (firstWord.equals("mark")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                taskList.mark(itemIndex);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(taskList.getTask(itemIndex).toString());

                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            } else if (firstWord.equals("unmark")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                taskList.unmark(itemIndex);
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(taskList.getTask(itemIndex).toString());
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (firstWord.equals("delete")) {
                System.out.println("mark");
            } else {
                Task task = new Task(command);
                taskList.add(task);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(String.format("added: %s", command));
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
    }
}
