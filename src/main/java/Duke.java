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
        TaskList taskList = new TaskList();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hello! I' Duke \nWhat can I do for you?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (sc.hasNextLine()) {

            String command = sc.nextLine().toLowerCase();
            String firstWord;
            int itemIndex = -1;

            int index = command.indexOf(' ');
            if (index > -1) {
                firstWord = command.substring(0, index);
            } else {
                firstWord = command;
            }

            if (firstWord.equals("mark") || firstWord.equals("unmark") || firstWord.equals("delete")) {
                itemIndex = Integer.parseInt(command.substring(index + 1));
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
                if (itemIndex > taskList.length()) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Out of bounds!");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    taskList.mark(itemIndex - 1);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(taskList.getTask(itemIndex - 1).toString());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

            } else if (firstWord.equals("unmark")) {
                if (itemIndex > taskList.length()) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Out of bounds!");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    taskList.unmark(itemIndex - 1);
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(taskList.getTask(itemIndex - 1).toString());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }


            } else if (firstWord.equals("delete")) {
                if (itemIndex > taskList.length()) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Out of bounds!");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(taskList.getTask(itemIndex - 1).toString());
                    taskList.delete(itemIndex - 1);
                    System.out.println(String.format("Now you have %d task(s) in the list.", taskList.getSize()));
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

            } else if (firstWord.equals("todo")){
                if (index == -1) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    String toDoItem = command.substring(index + 1);
                    ToDo toDo = new ToDo(toDoItem);
                    taskList.add(toDo);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Got it, I have added this task:" );
                    System.out.println(toDo.toString());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

            } else if (firstWord.equals("deadline")){
                if (index == -1) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    String deadlineItem = command.substring(index + 1, command.indexOf("/by"));
                    String time = command.substring(command.indexOf("/by") + 1);
                    Deadline deadline = new Deadline(deadlineItem, time);
                    taskList.add(deadline);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Got it, I have added this task:" );
                    System.out.println(deadline.toString());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

            } else if (firstWord.equals("event")) {
                if (index == -1) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                } else {
                    String eventItem = command.substring(index + 1, command.indexOf("/at"));
                    String time = command.substring(command.indexOf("/at") + 1);
                    Event event = new Event(eventItem, time);
                    taskList.add(event);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Got it, I have added this task:" );
                    System.out.println(event.toString());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }

            }
        }
    }
}
