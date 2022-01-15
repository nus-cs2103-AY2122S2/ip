import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Task[] myList;
        myList = new Task[100];
        int count = 0;

        String welcome = "Hello! I'm ChatCat\n" + "What can I do for you?\n";
        System.out.println(welcome);

        String input = " ";
        while (!input.equals("bye")) {

            input = sc.nextLine();
            switch(input) {
                case "list":
                    if (count == 0) {
                        System.out.println("empty list!");
                    }
                    for (int i = 0; i < count; i++) {


                        System.out.println((i + 1) + ". " + myList[i].getTask());
                    }
                    System.out.println("\n");
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                default:
                    Task task = new Task(input);
                    System.out.println("added: " + input + "\n");
                    myList[count] = task;
                    count++;
            }
        }
    }
}
