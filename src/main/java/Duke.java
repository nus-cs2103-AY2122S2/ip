import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String input = "";
        ArrayList<Task> list = new ArrayList<Task>();

        myPrint("Hello! I'm Duke\n    What can I do for you?");

        input = sc.nextLine();

        while(!input.equals("bye")) {
            String[] stringArr = input.split(" ", 2);

            if (stringArr[0].equals("list")) {
                String tempStr = "Here are the tasks in your list:\n    ";
                for (int i = 0; i < list.size(); i++) {
                    if (i != 0) {
                        tempStr += "\n    ";
                    }
                    tempStr += (i+1) + ". " + list.get(i);
                }
                myPrint(tempStr);

            } else if (stringArr[0].equals("mark")) {
                int idx = Integer.parseInt(stringArr[1]);
                list.get(idx - 1).mark();

                myPrint("Nice! I've marked this task as done:\n  " + list.get(idx - 1));

            } else if (stringArr[0].equals("unmark")) {
                int idx = Integer.parseInt(stringArr[1]);
                list.get(idx -1).unmark();

                myPrint("OK, I've marked this task as not done yet:\n  " + list.get(idx - 1));

            } else {
                Task tempTask = new Task(input);
                list.add(tempTask);
                myPrint("added: " + tempTask);
            }

            input = sc.nextLine();

        }

        myPrint("Bye. Hope to see you again soon!");

        sc.close();

    }

    public static void myPrint(String toPrint){
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + toPrint);
        System.out.println("    ________________________________________________________________\n");

    }

}
