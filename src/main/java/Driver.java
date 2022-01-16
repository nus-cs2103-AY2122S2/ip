import java.util.ArrayList;
import java.util.Scanner;
/**
 * Class that executes the main functionality
 */
public class Driver {
    private ArrayList<String> tasks;
    private String line = "\t____________________________________________________________";
    public Driver() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Executes the instructions given by user
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String command;
        while(true) {
            command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println(line + "\n" + "\tBye. Hope to see you again soon!\n" + line);
                break;
            } else if(command.equals("list")) {
                this.displayTasks();
            } else if(!command.equals("")) {
                this.addTask(command);
            }
        }
    }

    private void addTask(String task) {
        this.tasks.add(task);
        System.out.println(line + "\n\t added: " + task + "\n" + line);
    }

    private void displayTasks() {
        System.out.println(line);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + tasks.get(i));
        }
        System.out.println(line);
    }

}
