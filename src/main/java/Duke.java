import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static String line = "\n_______________________^_^__________________________________\n";

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void addList() {
        Scanner sc = new Scanner(System.in);
        String input;
        ArrayList<Task> taskList = new ArrayList<>(100);
            while (true) {

                input = sc.nextLine();
                String inputArr[] = input.split(" ", 2);

                if (inputArr[0].equals("bye")) {
                    System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                    break;
                } else if (inputArr[0].equals("list")) {

                    System.out.println(line + "Here are the tasks in your list:\n");
                    for (int i = 1; i <= taskList.size(); i++) {
                        System.out.println(i + "." + taskList.get(i - 1));
                    }
                    System.out.println(line);


                } else if (inputArr[0].equals("mark") && isInteger(inputArr[1])) {
                    try {
                        int taskNum = Integer.parseInt(inputArr[1]) - 1;
                        taskList.get(taskNum).markAsDone();
                        System.out.println(line + "Nice! I've marked this task as done:\n");
                        System.out.println(taskList.get(taskNum) + line);
                    } catch (IndexOutOfBoundsException exp) {
                        System.out.println(line + "☹ OOPS!!! this task number is invalid\n" +
                                "enter: 'list' for all available task" + line);
                    }


                } else if (inputArr[0].equals("unmark") && isInteger(inputArr[1])) {
                    try {
                        int taskNum = Integer.parseInt(inputArr[1]) - 1;
                        taskList.get(taskNum).markAsNotDone();
                        System.out.println(line + "OK, I've marked this task as not done yet:\n");
                        System.out.println(taskList.get(taskNum) + line);
                    } catch (IndexOutOfBoundsException exp) {
                        System.out.println(line + "☹ OOPS!!! this task number is invalid\n" +
                                "enter: 'list' for all available task" + line);
                    }


                } else if (inputArr[0].equals("todo")) {
                    ToDo t = new ToDo(inputArr[1]);
                    taskList.add(t);
                    System.out.println(line + "Got it. I've added this task:\n");
                    System.out.println(t);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list." + line);

                } else if (inputArr[0].equals("deadline")) {
                    try {
                        String deadlineArr[] = inputArr[1].split("/by ", 2);
                    Deadline d = new Deadline(deadlineArr[0], deadlineArr[1]);
                    taskList.add(d);
                    System.out.println(line + "Got it. I've added this task:\n");
                    System.out.println(d);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list." + line);

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(line + "☹ OOPS!!! deadline task need to be in this format:\n" +
                                "(deadline description /by yyyy-mm-dd)" + line);

                    } catch (DateTimeParseException e) {
                    System.out.println(line + "☹ OOPS!!! the Date need to be in this format:\n" +
                            "yyyy-mm-dd" + line);
                }

                } else if (inputArr[0].equals("event")) {
                    try {
                        String eventArr[] = inputArr[1].split("/at ", 2);
                        Event e = new Event(eventArr[0], eventArr[1]);
                        taskList.add(e);
                        System.out.println(line + "Got it. I've added this task:\n");
                        System.out.println(e);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list." + line);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(line + "☹ OOPS!!! event task need to be in this format:\n" +
                                "(event description /at yyyy-mm-dd)" + line);
                    } catch (DateTimeParseException e) {
                        System.out.println(line + "☹ OOPS!!! the Date need to be in this format:\n" +
                                "yyyy-mm-dd" + line);
                    }

                } else if(inputArr[0].equals("delete") && isInteger(inputArr[1])) {
                    try {
                        int taskNum = Integer.parseInt(inputArr[1]) - 1;
                        System.out.println(line + "Noted. I've removed this task: \n");
                        System.out.println(taskList.get(taskNum));
                        taskList.remove(taskNum);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list." + line);
                    } catch (IndexOutOfBoundsException exp) {
                        System.out.println(line + "☹ OOPS!!! this task number is invalid\n" +
                                "enter: 'list' for all available task" + line);
                    }


                } else {
                    //taskList.add(new Task(input));
                    //System.out.println(line + "added: " + input + line);
                    System.out.println(line + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" +
                            "To view all task available: list\n" +
                            "To add more task: todo…… deadline……/……  event……/……  or mark/unmark taskNumber\n" +
                            "To delete any task available: delete taskNumber\n" +
                            "please try again" + line);
                }
            }

    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(line + "Hello! I'm Duke\n" +
                "What can I do for you?\n" + line);

        Duke.addList();//


    }

}
