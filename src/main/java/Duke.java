import Tasks.Event;
import Tasks.Task;
import Tasks.Deadline;
import Tasks.ToDo;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();
        int countId = 1;

        System.out.println("____________________________________________________________" + '\n'
                + "Hello! I'm Duke" + '\n'
                + "What can I do for you?" + '\n'
                + "____________________________________________________________");


        while (sc.hasNext()) {
            String curr = sc.nextLine();
            String strArr[] = curr.split(" ", 2);
            String command = strArr[0];

            switch(command) {
                case "list":
                    displayList(arr);
                    continue;
                case "mark":
                    arr.get(Integer.parseInt(strArr[1]) - 1).setComplete();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(arr.get(Integer.parseInt(strArr[1]) - 1).toString());
                    continue;
                case "unmark":
                    arr.get(Integer.parseInt(strArr[1]) - 1).setIncomplete();
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println(arr.get(Integer.parseInt(strArr[1]) - 1).toString());
                    continue;
                case "todo":
                    System.out.println("Got it. I've added this task:  ");
                    arr.add(new ToDo(strArr[1]));
                    System.out.println(arr.get(arr.size() - 1).toString());
                    System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    continue;
                case "deadline": {
                    String strArrDate[] = strArr[1].split("/by ", 2);
                    String eventName = strArrDate[0];
                    String eventDate = strArrDate[1];
                    System.out.println("Got it. I've added this task:  ");
                    arr.add(new Deadline(eventName, eventDate));
                    System.out.println(arr.get(arr.size() - 1).toString());
                    System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    continue;
                }
                case "event": {
                    String strArrDate[] = strArr[1].split("/at ", 2);
                    String eventName = strArrDate[0];
                    String eventDate = strArrDate[1];
                    System.out.println("Got it. I've added this task:  ");
                    arr.add(new Event(eventName, eventDate));
                    System.out.println(arr.get(arr.size() - 1).toString());
                    System.out.println("Now you have " + arr.size() + " tasks in the list.");
                    continue;
                }
                case "bye":
                    break;
                default:
                    System.out.println("Invalid command, try again");
            }
        }
        System.out.println("____________________________________________________________" + '\n'
                + "Bye. Hope to see you again soon!" + '\n'
                + "____________________________________________________________");
    }

    public static void displayList(ArrayList<Task> arr) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arr.size(); i++) {
            Task currentTask = arr.get(i);
            System.out.println(i + 1 + "." + currentTask.toString());
        }
    }
}
