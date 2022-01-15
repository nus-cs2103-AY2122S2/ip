import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Meep {
    public static void main(String[] args) {
        Utils.printLogo();
        String userInput = "In", outputMsg = "Out";
        Scanner sc = new Scanner(System.in);

        List<Task> taskList = new ArrayList<Task>();

        while (true) {
            System.out.print("You: ");
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Meep: Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Meep: ");
                int i = 0;
                for (Task task : taskList) {
                    System.out.println("     " + i + ".  " + task.getTitle());
                    i++;
                }
            } else {
                outputMsg = userInput;
                taskList.add(new Task(userInput));
                System.out.println("Meep: added: " + outputMsg);
            }
        }
        sc.close();
    }
}