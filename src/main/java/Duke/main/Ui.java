package Duke.main;

public class Ui {
    public static final String line = "\n_______________________^_^__________________________________\n";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void greeting() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(line + "Hello! I'm Duke\n" +
                "What can I do for you?\n" + line);
    }

    public static void byeMessage() {
        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }

    public static void listMessage() {
        System.out.println(line + "Here are the tasks in your list:\n");
    }

    public static void markMessage() {
        System.out.println(line + "Nice! I've marked this task as done:\n");
    }

    public static void unmarkMessage() {
        System.out.println(line + "OK, I've marked this task as not done yet:\n");
    }

    public static void markErrorMessage() {
        System.out.println(line + "☹ OOPS!!! this task number is invalid\n" +
                "enter: 'list' for all available task" + line);
    }

    public static void addTaskMessage() {
        System.out.println(line + "Got it. I've added this task:\n");
    }

    public static void deleteTaskMessage() {
        System.out.println(line + "Noted. I've removed this task: \n");
    }

    public static void deadlineErrorMessage() {
        System.out.println(line + "☹ OOPS!!! deadline task need to be in this format:\n" +
                "(deadline description /by yyyy-mm-dd)" + line);
    }

    public static void eventErrorMessage() {
        System.out.println(line + "☹ OOPS!!! event task need to be in this format:\n" +
                "(event description /at yyyy-mm-dd)" + line);
    }

    public static void localDateErrorMessage() {
        System.out.println(line + "☹ OOPS!!! the Date need to be in this format:\n" +
                "yyyy-mm-dd" + line);
    }

    public static void generalErrorMessage() {
        System.out.println(line + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" +
                "To view all task available: list\n" +
                "To add more task: todo…… deadline……/……  event……/……  or mark/unmark taskNumber\n" +
                "To delete any task available: delete taskNumber\n" +
                "please try again" + line);
    }





}
