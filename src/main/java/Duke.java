import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";

    public static void main(String[] args) {
        printContent("Hello! I'm Duke\n     What can I do for you?");
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while (true){
            String line = sc.nextLine();
            String[] splitted = line.split("\\s+");
            String query = splitted[0];
            if (query.compareTo(COMMAND_BYE) == 0){
                printContent("Bye. Hope to see you again soon!");
                break;
            } else if (query.compareTo(COMMAND_LIST) == 0){
                processList(tasks);
            } else if (query.compareTo(COMMAND_MARK) == 0){
                Task thisTask = tasks.get(Integer.parseInt(splitted[1])-1);
                thisTask.markAsDone();
                printContent("Nice! I've marked this task as done:\n       [" + thisTask.getStatusIcon() + "] " + thisTask.getTitle());
            } else if (query.compareTo(COMMAND_UNMARK) == 0){
                Task thisTask = tasks.get(Integer.parseInt(splitted[1])-1);
                thisTask.markAsUndone();
                printContent("OK, I've marked this task as nto done yet:\n       [" + thisTask.getStatusIcon() + "] " + thisTask.getTitle());
            } else{
                printContent("added: " + line);
                tasks.add(new Task(line));
            }
        }
        sc.close();
    }

    public static void printLine(){
        System.out.println("    ____________________________________________________________");
    }

    public static void printContent(String text){
        String spacing = "     ";
        printLine();
        System.out.println(spacing + text);
        printLine();
        System.out.println();
    }

    public static void processList(ArrayList<Task> tasks){
        String list = "Here are the tasks in your list:\n     ";
        for (int i = 0; i < tasks.size(); i++){
            list += (i+1) + ". " + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getTitle();
            if (i != tasks.size()-1)
                list += "\n     ";
        }
        printContent(list);
    }
}
