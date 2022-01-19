import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String border = "    ____________________________________________________________\n";
        String spacing = "    ";
        ListStorage myListStorage = new ListStorage();
        System.out.println("Hello from\n" + logo);
        Scanner myScanner = new Scanner(System.in);
        System.out.println(border + spacing +
                "Hello! I'm Duke\n" + spacing +
                "What can I do for you?\n" +
                border);
        while(myScanner.hasNextLine()) {
            String cmd = myScanner.nextLine();
            if (cmd.equals("bye")) {
                break;
            }
            if(cmd.equals("list")) {
                System.out.println(border + spacing
                        + "Here are the tasks in your list:\n"
                        + myListStorage.printList()
                        + border);
            } else if (cmd.contains("mark")){
                int taskNumber = Character.getNumericValue(cmd.charAt(cmd.length() - 1));
                if (cmd.contains("un")) {
                    myListStorage.findTask(taskNumber).unmark();
                    System.out.println(border
                            + spacing
                            + "OK, I've marked this task as not done yet:\n"
                            + myListStorage.printTask(taskNumber) + border);
                } else {
                    myListStorage.findTask(taskNumber).markAsDone();
                    System.out.println(border
                            + spacing
                            + "Nice! I've marked this task as done:\n"
                            + myListStorage.printTask(taskNumber) + border);
                }
            } else {
                Task newTask = new Task(cmd);
                String toPrint = myListStorage.addToList(newTask);
                System.out.println(border + spacing + toPrint + "\n" + border);
            }
        }
        System.out.println(border + spacing + "Bye. Hope to see you again soon!\n" + border);
    }
}
