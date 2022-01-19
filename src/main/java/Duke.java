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

            if (command.equals("list")) {
                displayList(arr);
            } else if (command.equals("bye")) {
                break;
            } else if (command.equals("mark")) {
                arr.get(Integer.parseInt(strArr[1]) - 1).status = true;
                System.out.println("Nice! I've marked this task as done: ");
                displayList(arr);
            } else if (command.equals("unmark")) {
                arr.get(Integer.parseInt(strArr[1]) - 1).status = false;
                System.out.println("OK, I've marked this task as not done yet: ");
                displayList(arr);
            } else {
                arr.add(new Task(countId, curr, false));
                System.out.println("added: " + curr);
                countId++;
            }
        }
        System.out.println("____________________________________________________________" + '\n'
                + "Bye. Hope to see you again soon!" + '\n'
                + "____________________________________________________________");
    }

    public static void displayList(ArrayList<Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            Task currentTask = arr.get(i);
            System.out.println(currentTask.toString());
        }
    }
}
