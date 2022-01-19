import java.util.Scanner;
import java.util.*;
import myPackage.*;

public class Duke {
    public static void main(String[] args) {
        //Task c = new Task("HELLO");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        int listCount = 0;
        Task[] list = new Task[100];
        //String[] list = new String[100];
        while (true) {
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();
            String[] userInputSplit = userInput.split(" ");
            String keyword = userInputSplit[0];
            int lenSplit = userInputSplit.length;
            switch (keyword) {
                case "bye": {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                    break;
                }
                case "list": {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listCount; i++) {
                        System.out.printf("%d.[%s] %s%n", i + 1,list[i].getStatusIcon(), list[i].getDescription());
                    }
                    break;
                }
                case "mark": {
                    if (lenSplit > 1) {
                        list[Integer.parseInt(userInputSplit[1]) - 1].markAsDone();
                    }
                    break;
                }
                case "unmark": {
                    if (lenSplit > 1) {
                        list[Integer.parseInt(userInputSplit[1]) - 1].unmarkAsDone();
                    }
                    break;
                }
                default: {
                    System.out.println("added: " + userInput);
                    list[listCount] = new Task(userInput);
                    listCount++;
                }
            }



        }

    }
}
