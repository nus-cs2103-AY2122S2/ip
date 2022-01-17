import java.util.Scanner;

public class Duke {
    private static List list;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "I am at your service.\n");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        list = new List(100);

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(list);
            } else {
                Task task = new Task(userInput);
                System.out.println(list.add(task));
            }
            userInput = sc.nextLine();
        }
        System.out.println("Ciao! Hope to see you again!");
    }
}
