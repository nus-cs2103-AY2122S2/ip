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
        Task tempTask;

        myPrint("Hello! I'm Duke\n    What can I do for you?");

        input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                String[] stringArr = input.split(" ", 2);

                if (stringArr[0].equals("list")) {
                    String tempStr = "Here are the tasks in your list:\n    ";
                    for (int i = 0; i < list.size(); i++) {
                        if (i != 0) {
                            tempStr += "\n    ";
                        }
                        tempStr += (i + 1) + ". " + list.get(i);
                    }
                    myPrint(tempStr);

                } else if (stringArr[0].equals("mark")) {

                    if (stringArr.length < 2) {
                        throw new DukeAbsentInfoException("☹ OOPS!!! Please specify the task to be marked using a int");
                    }

                    int idx = Integer.parseInt(stringArr[1]);

                    if (idx < 1 || idx > list.size()) {
                        throw new DukeIdxOOBException();
                    }

                    list.get(idx - 1).mark();

                    myPrint("Nice! I've marked this task as done:\n      " + list.get(idx - 1));

                } else if (stringArr[0].equals("unmark")) {

                    if (stringArr.length < 2) {
                        throw new DukeAbsentInfoException(
                            "☹ OOPS!!! Please specify the task to be unmarked using a int");
                    }

                    int idx = Integer.parseInt(stringArr[1]);

                    if (idx < 1 || idx > list.size()) {
                        throw new DukeIdxOOBException();
                    }

                    list.get(idx - 1).unmark();

                    myPrint("OK, I've marked this task as not done yet:\n      " + list.get(idx - 1));

                } else if (stringArr[0].equals("delete")) {

                    if (list.size() == 0) {
                        throw new DukeException("☹ OOPS!!! There is nothing on the list to delete!");
                    }

                    if (stringArr.length < 2) {
                        throw new DukeAbsentInfoException(
                            "☹ OOPS!!! Please specify the task to be unmarked using a int");
                    }

                    int idx = Integer.parseInt(stringArr[1]);

                    if (idx < 1 || idx > list.size()) {
                        throw new DukeIdxOOBException();
                    }

                    myPrint("Noted. I've removed this task:\n      " + list.remove(idx - 1) + countList(list));
                    
                }else if (stringArr[0].equals("todo")) {

                    if (stringArr.length < 2) {
                        throw new DukeAbsentInfoException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    tempTask = new ToDo(stringArr[1]);

                    list.add(tempTask);
                    myPrint("Got it. I've added this task:\n      " + tempTask + countList(list));

                } else if (stringArr[0].equals("deadline")) {

                    if (stringArr.length < 2) {
                        throw new DukeAbsentInfoException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }

                    String[] descriptionTime = stringArr[1].split("/", 2);

                    if (descriptionTime.length < 2) {
                        throw new DukeAbsentInfoException(
                                "☹ OOPS!!! The date/time of the deadline has to be specified and seperated from the description using a \"/\".");
                    }

                    String[] prepoTime = descriptionTime[1].split(" ", 2);
                    tempTask = new Deadline(prepoTime[0] + ": " + prepoTime[1], descriptionTime[0]);

                    list.add(tempTask);
                    myPrint("Got it. I've added this task:\n      " + tempTask + countList(list));

                } else if (stringArr[0].equals("event")) {

                    if (stringArr.length < 2) {
                        throw new DukeAbsentInfoException("☹ OOPS!!! The description of an event cannot be empty.");
                    }

                    String[] descriptionTime = stringArr[1].split("/", 2);

                    if (descriptionTime.length < 2) {
                        throw new DukeAbsentInfoException(
                                "☹ OOPS!!! The date/time of the event has to be specified and seperated from the description using a \"/\".");
                    }

                    String[] prepoTime = descriptionTime[1].split(" ", 2);
                    tempTask = new Event(prepoTime[0] + ": " + prepoTime[1], descriptionTime[0]);

                    list.add(tempTask);
                    myPrint("Got it. I've added this task:\n      " + tempTask + countList(list));

                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                myPrint(e.toString());
            }

            input = sc.nextLine();

        }

        myPrint("Bye. Hope to see you again soon!");

        sc.close();

    }


    public static void myPrint(String toPrint) {
        System.out.println("    ________________________________________________________________");
        System.out.println("    " + toPrint);
        System.out.println("    ________________________________________________________________\n");

    }

    public static String countList(ArrayList<Task> list) {
        String isSingular = "s";

        if (list.size() == 1) {
            isSingular = "";
        }

        return "\n    Now you have " + list.size() + " task" + isSingular + " in your list.";
    }

}
