import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class DukeMessage{
        public final static String welcomeMsg = "Hello! I am Duke\nWhat can I do for you?";
        public final static String horizontalLine = "________________________________";
        public final static String byeMsg = "Bye! hope to see you again soon!";
        public final static String listMsg = "Here are a list of your tasks!";
        public final static String addedTaskMsg = "Got it. I've added this task:\n";
        public static String getTaskInListMsg(Task newTask, int numTask){
            return addedTaskMsg + "\t" + newTask + "\nNow you have " + numTask + " tasks in the list";
        }
    }

    public static class DukeCommand{
        public final static String bye = "bye";
        public final static String list = "list";
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

    public static Task processAdd(String command){
        String taskStr = command;
        String description = taskStr;
        printMessage(DukeMessage.addedTaskMsg + "\n" + command);
        return new Task(description, false);
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList();
        Scanner input = new Scanner(System.in);
        String userinput = "";
        Task newTask = null;
        printMessage(DukeMessage.welcomeMsg);
        while(true) {
            //System.out.println(DukeMessage.horizontalLine);
            userinput = input.nextLine();
            newTask = null;
            if (userinput.equals(DukeCommand.bye)) {
                printMessage(DukeMessage.byeMsg);
                break;
            }
            else if (userinput.equals(DukeCommand.list)) {
                processPrintList(taskList);
                System.out.println(DukeMessage.horizontalLine);
            }
            else if (!userinput.equals(null)){
                newTask = processAdd(userinput);
                taskList.add(newTask);
            }
        }

        input.close();
    }
}
