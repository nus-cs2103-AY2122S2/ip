import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {

        this.sc = new Scanner(System.in);
    }

    public void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public String readCommand() {

        return this.sc.nextLine();
    }


    public String printTasks(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= taskList.size(); i++) {
            sb.append(i).append(". ").append(taskList.get(i - 1).toString());
        }
          return sb.toString();
    }

    public String printNumTasks(TaskList taskList) {
       return "Now you have " + taskList.getTasks().size() + " tasks in the list.";

    }

    public String printAddTask(Task added, TaskList taskList) {
        String temp = "Got it. I've added this task:\n" + added.toString();
        temp += printNumTasks(taskList);
        return temp;
    }

    public String printMark(Task marked) {
        return "This task is marked as done:\n" + marked.toString();

    }

    public String printDelete(Task deleted, TaskList taskList) {
        String toReturn = "This task has been removed:\n" + deleted.toString();
        toReturn += printNumTasks(taskList);
        return toReturn;
    }

    public String printReq(ArrayList<Task> tasks) {
        StringBuilder toReturn = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            toReturn.append(i).append(". ").append(tasks.get(i - 1).toString());
        }
        return toReturn.toString();
    }


    public void closeSc() {

        this.sc.close();
    }
}