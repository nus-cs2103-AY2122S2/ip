import java.util.Scanner;

public class Duke {
    static String border = "    ____________________________________________________________\n";
    static String spacing = "    ";
    static ListStorage myListStorage = new ListStorage();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println(border + spacing +
                "Hello! I'm Duke\n" + spacing +
                "What can I do for you?\n" +
                border);
        parseCommand();
    }

    public static void parseCommand(){

        Scanner myScanner = new Scanner(System.in);

        while(myScanner.hasNextLine()) {
            String cmd = myScanner.nextLine();
            if (cmd.equals("bye")) {
                System.out.println(border + spacing + "Bye. Hope to see you again soon!\n" + border);
                break;
            }
            if (cmd.equals("list")) {
                System.out.println(border + spacing
                        + "Here are the tasks in your list:\n"
                        + myListStorage.printList()
                        + border);
            } else if (cmd.contains("mark")) {
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
            } else if (cmd.contains("todo")) {
                String[] parsedCmd = Parser.parseCmdAndDes(cmd);
                Task newTask = new ToDo(parsedCmd[1]);
                myListStorage.addToList(newTask);
                System.out.println(border
                        + spacing
                        + "Got it. I've added this task:\n"
                        + spacing
                        + newTask.toString()
                        + "\n"
                        + spacing
                        + "Now you have " + myListStorage.length() + " tasks in the list.\n"
                        + border);
            } else if (cmd.contains("deadline")) {
                String[] parsedCmd = Parser.parseCmdAndDes(cmd);
                String[] deadline = Parser.splitDeadlineAndTime(parsedCmd[1]);
                Task newTask = new Deadline(deadline[0], deadline[1]);
                myListStorage.addToList(newTask);
                System.out.println(border
                        + spacing
                        + "Got it. I've added this task:\n"
                        + spacing
                        + newTask.toString()
                        + "\n"
                        + spacing
                        + "Now you have " + myListStorage.length() + " tasks in the list.\n"
                        + border);
            } else if (cmd.contains("event")) {
                String[] parsedCmd = Parser.parseCmdAndDes(cmd);
                String[] event = Parser.splitEventAndTime(parsedCmd[1]);
                Task newTask = new Event(event[0], event[1]);
                myListStorage.addToList(newTask);
                System.out.println(border
                        + spacing
                        + "Got it. I've added this task:\n"
                        + spacing
                        + newTask.toString()
                        + "\n"
                        + spacing
                        + "Now you have " + myListStorage.length() + " tasks in the list.\n"
                        + border);
            } else {
                Task newTask = new ToDo(cmd);
                String toPrint = myListStorage.addToList(newTask);
                System.out.println(border + spacing + toPrint + "\n" + border);
            }
        }
    }
}
