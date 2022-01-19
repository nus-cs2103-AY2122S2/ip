import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke\nWhat can i do for you?\n");

        Scanner input = new Scanner(System.in);
        String intputText = "";
        Task[] list = new Task[100];
        int tasks = 0;

        while (!intputText.equals("bye")) {
            intputText = input.nextLine();
            String[] tempList = intputText.split(" ", 2);

            try {
                new DukeException().invalidChecker(tempList);
            } catch (DukeException e) {
                System.err.println(e);
                continue;
            }

            System.out.println("------------------------------------------------------------");
            if (intputText.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (intputText.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks; i++) {
                    System.out.println((i + 1) + "." + list[i].toString());
                }
            } else if (tempList[0].equals("mark")) {
                list[Integer.parseInt(tempList[1]) - 1].markAsDone();
                System.out.println(list[Integer.parseInt(tempList[1]) - 1].toString());
            } else if (tempList[0].equals("unmark")) {
                list[Integer.parseInt(tempList[1]) - 1].markAsNotDone();
                System.out.println(list[Integer.parseInt(tempList[1]) - 1].toString());
            } else {
                System.out.println("Got it. I've added this task:");
                if (tempList[0].equals("todo")) {
                    list[tasks] = new Todo(tempList[1]);
                    System.out.println(list[tasks].toString());
                } else if (tempList[0].equals("deadline")){
                    String[] restOfPara  = tempList[1].split("/by ", 2);
                    list[tasks] = new Deadline(restOfPara[0], restOfPara[1]);
                    System.out.println(list[tasks].toString());
                } else if (tempList[0].equals("event")){
                    String[] restOfPara  = tempList[1].split("/at ", 2);
                    list[tasks] = new Event(restOfPara[0], restOfPara[1]);
                    System.out.println(list[tasks].toString());
                }
                tasks++;
                if (tasks == 1) {
                    System.out.println("Now you have " + tasks + " task in the list.");
                } else {
                    System.out.println("Now you have " + tasks + " tasks in the list.");
                }
            }
            System.out.println("------------------------------------------------------------");
        }

    }
}

