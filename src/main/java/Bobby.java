import java.util.Scanner;
import java.util.ArrayList;

public class Bobby {
    public static void main(String[] args) {
        ArrayList<Task> stringArray = new ArrayList<Task>();
        System.out.println("Bobby greets you. Bobby is here to help.");

        while(true) {
            Scanner scannerObj = new Scanner(System.in);
            String userInput = scannerObj.nextLine();
            String[] inputs = userInput.split(" ");
            String command = inputs[0];

            if (userInput.equals("bye")) {
                System.out.println("Bobby bids you farewell.");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here is what you told Bobby:");
                for (int i = 0; i < stringArray.size(); i++) {
                    Task t = stringArray.get(i);
                    int count = i + 1;
                    System.out.println(count + "." + t);
                }
            } else if (command.equals("mark")) {
                Task t = stringArray.get(Integer.parseInt(inputs[1]) - 1);
                t.markAsDone();
                System.out.println("Bobby applauds you. This task is done:\n" + t);
            } else if (command.equals("unmark")) {
                Task t = stringArray.get(Integer.parseInt(inputs[1]) - 1);
                t.unmarkAsDone();
                System.out.println("Bobby will remember that this task is not yet done:\n" + t);
            } else {
                Task newTask = new Task(userInput);
                System.out.println("Bobby heard: " + newTask.description);
                stringArray.add(newTask);
            }
        }
    }
}
