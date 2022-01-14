import java.util.Scanner;

public class Meep {
    public static void main(String[] args) {
        Utils.printLogo();
        String userInput = "In", outputMsg = "Out";
        Scanner sc = new Scanner(System.in);

        String[] tasks = new String[100];
        int taskIndex = 0;

        while (true) {
            System.out.print("You: ");
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Meep: Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Meep: ");
                for (int i = 0; i < taskIndex; i++) {
                    System.out.println("     " + i + ".  " + tasks[i]);
                }
            } else {
                outputMsg = userInput;
                tasks[taskIndex] = userInput;
                taskIndex++;
                System.out.println("Meep: added: " + outputMsg);
            }
        }
        sc.close();
    }
}