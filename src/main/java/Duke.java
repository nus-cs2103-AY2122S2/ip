import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Welcome to Duke! \nWhat can i do for you?\n");

        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();

        String input = sc.nextLine();
        int i = input.indexOf(' ');
        String firstWord = "";
        String restOfWord = "";
        if (i != -1) {
            firstWord = input.substring(0, i);
            restOfWord = input.substring(i);
        }

        while(!input.equals("bye")) {
            if (input.equals("list")) {
                list.print();
            } else if(firstWord.equals("mark")) {
                int indexOfList = Integer.parseInt(restOfWord);
                list.markComplete(indexOfList);
                System.out.println("Marked as complete");
                list.print(indexOfList);
            } else if(firstWord.equals("unmark")) {
                int indexOfList = Integer.parseInt(restOfWord);
                list.markIncomplete(indexOfList);
                System.out.println("Marked as incomplete");
                list.print(indexOfList);
            } else {
                boolean addSuccess = list.add(input);
                if (addSuccess) {
                    System.out.println("added: " + input);
                } else {
                    System.out.println("Failed to add " + input);
                }
            }
            input = sc.nextLine();
            i = input.indexOf(' ');
            firstWord = "";
            restOfWord = "";
            if (i != -1) {
                firstWord = input.substring(0, i);
                restOfWord = input.substring(i+1);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
