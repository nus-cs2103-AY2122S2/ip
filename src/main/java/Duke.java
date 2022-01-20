import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String GREETING_MESSAGE = "Hey, Aeromon here! Fancy a cup of tea?";
    private static final String FAREWELL_MESSAGE = "Buai Buai! Ciao for now!";
    private static final String ADD_MESSAGE = "Nicely! I've added for you: \n";
    private static final String MARK_MESSAGE = "Naisu! You've completed: \n";
    private static final String UNMARK_MESSAGE = "OI! What happened to completing: \n";
    private static final List<Task> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(FAREWELL_MESSAGE);
                break;
            } else if (input.equals("list")) {
                int count = 1;
                for (Task item : toDoList) {
                    System.out.println(String.format("%d: %s", count, item));
                    count++;
                }
            } else if (input.startsWith("mark")) {
                String intValue = input.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(intValue) - 1;
                Task temp = toDoList.get(index);
                temp.markAsDone();
                System.out.println(MARK_MESSAGE + temp);
            } else if (input.startsWith("unmark")) {
                String intValue = input.replaceAll("[^0-9]", "");
                int index = Integer.parseInt(intValue) - 1;
                Task temp = toDoList.get(index);
                temp.markAsNotDone();
                System.out.println(UNMARK_MESSAGE + temp);
            } else if (input.startsWith("todo")) {
                String taskName = input.substring(5);
                Task task = new ToDo(taskName);
                toDoList.add(task);
                System.out.println(ADD_MESSAGE + task);
                System.out.println(String.format("Aiyo, now you have %d tasks in the list, jiayous!", toDoList.size()));
            } else if (input.startsWith("deadline")) {
                String[] splitString = input.split("/by ");
                String taskName = splitString[0].substring(9);
                String by = splitString[1];
                Task task = new Deadline(taskName, by);
                toDoList.add(task);
                System.out.println(ADD_MESSAGE + task);
                System.out.println(String.format("Aiyo, now you have %d tasks in the list, jiayous!", toDoList.size()));
            } else if (input.startsWith("event")) {
                String[] splitString = input.split("/at ");
                String taskName = splitString[0].substring(6);
                String at = splitString[1];
                Task task = new Event(taskName, at);
                toDoList.add(task);
                System.out.println(ADD_MESSAGE + task);
                System.out.println(String.format("Aiyo, now you have %d tasks in the list, jiayous!", toDoList.size()));
            }
        }
    }
}
