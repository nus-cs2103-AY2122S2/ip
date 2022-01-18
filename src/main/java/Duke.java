import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String COMMAND_BYE = "bye";
    private static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    private static final String COMMAND_LIST = "list";
    private static final String MESSAGE_LIST = "Here are the tasks in your list:";

    private static final String COMMAND_MARK = "mark";
    private static final String MESSAGE_MARK = "Nice! I've marked this task as done:";

    private static final String COMMAND_UNMARK = "unmark";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";

    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String MESSAGE_TASKADD = "Got it. I've added this task:";

    public static void main(String[] args) {
        printContent("Hello! I'm Duke\n     What can I do for you?");
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while (true){
            String line = sc.nextLine();
            String[] splitted = line.split("\\s+");
            String query = splitted[0];
            if (query.compareTo(COMMAND_BYE) == 0){
                printContent(MESSAGE_BYE);
                break;
            } else if (query.compareTo(COMMAND_LIST) == 0){
                processList(tasks);
            } else if (query.compareTo(COMMAND_MARK) == 0){
                Task thisTask = tasks.get(Integer.parseInt(splitted[1])-1);
                thisTask.markAsDone();
                printContent(taskLine(thisTask, MESSAGE_MARK));
            } else if (query.compareTo(COMMAND_UNMARK) == 0){
                Task thisTask = tasks.get(Integer.parseInt(splitted[1])-1);
                thisTask.markAsUndone();
                printContent(taskLine(thisTask, MESSAGE_UNMARK));
            } else if (query.compareTo(COMMAND_TODO) == 0){
                Task thisTask = new TodoTask(line.substring(5));
                tasks.add(thisTask);
                printAddTaskSuccess(tasks, thisTask);
            } else if (query.compareTo(COMMAND_DEADLINE) == 0){
                String[] subSplit = line.split("/by");
                Task thisTask = new DeadlineTask(subSplit[0].substring(9), subSplit[1]);
                tasks.add(thisTask);
                printAddTaskSuccess(tasks, thisTask);
            } else if (query.compareTo(COMMAND_EVENT) == 0){
                String[] subSplit = line.split("/at");
                Task thisTask = new EventTask(subSplit[0].substring(6), subSplit[1]);
                tasks.add(thisTask);
                printAddTaskSuccess(tasks, thisTask);
            } else{
                printContent("Invalid input!");
            }
        }
        sc.close();
    }

    public static void printLine(){
        System.out.println("    ____________________________________________________________");
    }

    public static void printAddTaskSuccess(ArrayList<Task> tasks, Task task){
        String content = taskLine(task, MESSAGE_TASKADD) + "\n";
        content += listSizeLine(tasks);
        printContent(content);
    }

    public static String taskLine(Task task, String message){
        return message + "\n       [" + task.getType() + "][" + task.getStatusIcon() + "] " + task.toString();
    }

    public static String listSizeLine(ArrayList<Task> tasks){
        return "     Now you have " + tasks.size() + " tasks in the list.";
    }

    public static void printContent(String text){
        String spacing = "     ";
        printLine();
        System.out.println(spacing + text);
        printLine();
        System.out.println();
    }

    public static void processList(ArrayList<Task> tasks){
        String list = MESSAGE_LIST + "\n     ";
        for (int i = 0; i < tasks.size(); i++){
            Task thisTask = tasks.get(i);
            list += (i+1) + ". " + "[" + thisTask.getType() + "]" +"[" + thisTask.getStatusIcon() + "] " + thisTask.toString();
            if (i != tasks.size()-1)
                list += "\n     ";
        }
        printContent(list);
    }
}
