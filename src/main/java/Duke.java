import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> allTasks = new ArrayList<Task>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String intro = "I am a Personal Assistant Chatbot that helps you to keep track of various things.\n"
                + "Let's get started.\n";

        String line = "-------------------------------------------------------------------------------------";

        String bye = "Bye. Hope to see you again soon!";

        System.out.println("Hello from\n" + logo);
        System.out.println(intro);
        System.out.println(line);

        Scanner s = new Scanner(System.in);

        String input;

        while(true) {

             input = s.nextLine();

            if (input.equals("bye")){
                break;
            }
            else if (input.equals("list")){
                list();
            }
            else {
                System.out.println("\n");
                System.out.println(line);
                String response = addTask(input);
                System.out.println(String.format("added: %s",response));
                System.out.println(line);
            }
        }

        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);
    }

    public static String addTask(String s){
        /*
        * Takes a string as input, create a new task with the input as the name,
        * and adds it to the task list.
        *
        * @param s
        * input string to be used as the task name
        *
        * @return the reply of the task added
         */
        Task t = new Task(s);
        allTasks.add(t);
        return t.reply();
    }

    public static void list(){
        /*
        * Display all current tasks in the task list as an ordered list.
         */
        int counter = 1;
        for (Task t : allTasks) {
            System.out.println(String.format("%d. %s",counter,t.reply()));
            counter++;
        }
    }
}
