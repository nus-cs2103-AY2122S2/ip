import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class DukeMessage{
        public final static String welcomeMsg = "Hello! I am Duke\nWhat can I do for you?";
        public final static String horizontalLine = "________________________________";
        public final static String byeMsg = "Bye! hope to see you again soon!";
        public final static String listMsg = "Here are a list of your tasks!";
        public final static String invalidCommandMsg = "I do not understand your command!";
        public final static String addedTaskMsg = "Got it. I've added this task:\n";
        public final static String completedTaskMsg = "Task has been marked as completed.";
        public final static String notCompletedTaskMsg = "Task has been marked as not completed.";
        public static String getCompleteMessage(boolean isCompleted){
            return (isCompleted)?completedTaskMsg:notCompletedTaskMsg;
        }
    }

    public static class DukeCommand{
        public final static String bye = "bye";
        public final static String list = "list";
        public final static String mark = "mark";
        public final static String unmark = "unmark";
        public final static String todo = "todo";
        public final static String event = "event";
        public final static String deadline = "deadline";
    }


    public static void printMessage(String msg) {
        System.out.println(DukeMessage.horizontalLine);
        System.out.println(msg);
        System.out.println(DukeMessage.horizontalLine);
    }

    public static void processPrintList(ArrayList<Task> taskList){
        printMessage(DukeMessage.listMsg);
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println((i + 1) +". " + currentTask);
        }
    }
    public static Task processTodo(String command) {
        String taskStr = command.substring(DukeCommand.todo.length());
        taskStr = taskStr.trim();
        //String[] taskInfo = taskStr.split("TODO");
        String description = taskStr;
        printMessage(DukeMessage.addedTaskMsg + "\n" + command); // + "\n" + DukeMessage.getTaskInListMsg());
        return new ToDoTask(description, false);
    }

    public static Task processEvent(String command) {
        String taskStr = command.substring(DukeCommand.event.length());
        taskStr = taskStr.trim();
        String[] taskInfo = taskStr.split("/at");
        String description = taskInfo[0].trim();
        String dateStr = taskInfo[1].trim();
        return new EventTask(description, false, dateStr);
    }

    public static Task processDeadline(String command)  {
        String taskStr = command.substring(DukeCommand.deadline.length());
        taskStr = taskStr.trim();
        String[] taskInfo = taskStr.split("/by");
        String description = taskInfo[0].trim();
        String dateStr = taskInfo[1].trim();
        return new DeadlineTask(description,false, dateStr);
    }

    public static boolean processMarkingTask(String taskIndexStr, ArrayList<Task> taskList, boolean isCompleted){
        taskList.get(Integer.parseInt(taskIndexStr) - 1).setCompleted(isCompleted);
        System.out.println(DukeMessage.horizontalLine);
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (i == (Integer.parseInt(taskIndexStr) - 1)){
                System.out.println((i + 1) + ". " + currentTask);
            }
        }
        printMessage(DukeMessage.getCompleteMessage(isCompleted));
        return true;
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList();
        Scanner input = new Scanner(System.in);
        String userinput = "";
        Task newTask = null;
        printMessage(DukeMessage.welcomeMsg);
        while(true) {
            userinput = input.nextLine();
            newTask = null;
            if (userinput.equals(DukeCommand.bye)) {
                printMessage(DukeMessage.byeMsg);
                break;
            }
            else if (userinput.startsWith(DukeCommand.todo)){
                newTask = processTodo(userinput);
                //taskList.add(newTask);
            }
            else if (userinput.startsWith(DukeCommand.deadline)){
                newTask = processDeadline(userinput);
            }
            else if (userinput.startsWith(DukeCommand.event)){
                newTask = processEvent(userinput);
            }
            else if (userinput.equals(DukeCommand.list)) {
                processPrintList(taskList);
                System.out.println(DukeMessage.horizontalLine);
            }
            else if (userinput.startsWith(DukeCommand.mark)){
                String taskStr = userinput.substring(DukeCommand.mark.length());
                taskStr = taskStr.trim();
                processMarkingTask(taskStr, taskList, true);
            }
            else if (userinput.startsWith(DukeCommand.unmark)) {
                String taskStr = userinput.substring(DukeCommand.unmark.length());
                taskStr = taskStr.trim();
                processMarkingTask(taskStr, taskList, false);
            }
            else {
                printMessage(DukeMessage.invalidCommandMsg);
            }
        }

        input.close();
    }
}
