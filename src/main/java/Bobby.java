import java.util.Scanner;
import java.util.ArrayList;

public class Bobby {
    public static void main(String[] args) {
        ArrayList<Task> taskArray = new ArrayList<Task>();
        System.out.println("Bobby greets you. Bobby is here to help.");
        Scanner scannerObj = new Scanner(System.in);

        while(scannerObj.hasNextLine()) {
            String userInput = scannerObj.nextLine();
            String[] inputs = userInput.split(" ", 2);
            String command = inputs[0];

            if (userInput.equals("bye")) {
                System.out.println("Bobby bids you farewell.");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here is what you told Bobby:");
                for (int i = 0; i < taskArray.size(); i++) {
                    Task t = taskArray.get(i);
                    int count = i + 1;
                    System.out.println(count + "." + t);
                }
            } else if (command.equals("mark")) {
                Task t = taskArray.get(Integer.parseInt(inputs[1]) - 1);
                t.markAsDone();
                System.out.println("Bobby applauds you. This task is done:\n" + t);
            } else if (command.equals("unmark")) {
                Task t = taskArray.get(Integer.parseInt(inputs[1]) - 1);
                t.unmarkAsDone();
                System.out.println("Bobby will remember that this task is not yet done:\n" + t);
            } else if (command.equals("todo")) {
                Todo newTodo = new Todo(inputs[1]);
                System.out.println("Bobby heard: " + newTodo);
                taskArray.add(newTodo);
                System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
            } else if (command.equals("deadline")) {
                String[] splitInputs = inputs[1].split(" /by ", 2);
                String description = splitInputs[0];
                String by = splitInputs[1];
                Deadline newDeadline = new Deadline(description, by);
                System.out.println("Bobby heard: " + newDeadline);
                taskArray.add(newDeadline);
                System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
            } else if (command.equals("event")) {
                String[] splitInputs = inputs[1].split(" /at ", 2);
                String description = splitInputs[0];
                String at = splitInputs[1];
                Event newEvent = new Event(description, at);
                System.out.println("Bobby heard: " + newEvent);
                taskArray.add(newEvent);
                System.out.println("Bobby remembers " + taskArray.size() + " task(s).");
            } else {
                Task newTask = new Task(userInput);
                System.out.println("Bobby heard: " + newTask.description);
                taskArray.add(newTask);
            }
        }
    }
}
