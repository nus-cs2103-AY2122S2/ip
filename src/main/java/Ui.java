import java.time.format.DateTimeFormatter;

public class Ui {

    public static class DukeMessage{
        public final static String welcomeMsg = "Hello! I am Duke\nWhat can I do for you?";
        public final static String horizontalLine = "________________________________";
        public final static String byeMsg = "Bye! hope to see you again soon!";
        public final static String listMsg = "Here are a list of your tasks!";
        public final static String deleteMsg = "Alright! Let me delete this task:\n";
        public final static String invalidCommandMsg = "I do not understand your command!";
        public final static String addedTaskMsg = "Got it. I've added this task:\n";
        public final static String completedTaskMsg = "Task has been marked as completed.\n";
        public final static String notCompletedTaskMsg = "Task has been marked as not completed.\n";
        public final static String expectedDeadline = "Try typing it in the following format:\n deadline <desciption> /by 31-01-2022 1800";
        public final static String expectedEvent = "Try typing it in the following format:\n event <desciption> /at 31-01-2022 1800_to_31-01-2022 2100";
        public static String getTaskInListMsg(Task task, int numTask){
            return addedTaskMsg + "\t" + task + "\nNow you have " + numTask + " tasks in the list";
        }

        public static String getDeleteTaskInListMsg(Task task, int numTask){
            return deleteMsg + "\t" + task + "\nNow you have " + numTask + " tasks in the list";
        }

        public static String getCompleteMessage(boolean isCompleted){
            return (isCompleted)?completedTaskMsg:notCompletedTaskMsg;
        }
    }

    public static class DukeCommand{
        public final static String bye = "bye";
        public final static String list = "list";
        public final static String todo = "todo";
        public final static String deadline = "deadline";
        public final static String event = "event";
        public final static String delete = "delete";
        public final static String mark = "mark";
        public final static String unmark = "unmark";
        public final static DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    }

    public static void printMessage(String msg) {
        System.out.println(Duke.DukeMessage.horizontalLine);
        System.out.println(msg);
        System.out.println(Duke.DukeMessage.horizontalLine);
    }
}
