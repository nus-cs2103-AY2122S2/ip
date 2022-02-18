package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

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

            sb.append(i).append(" . ").append(taskList.get(i - 1).toString()).append(System.lineSeparator());

        }
          return sb.toString();
    }




    public String printAddTask(Task added, TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task: ").append(added.toString()).append(System.lineSeparator()).append("Now you have " + taskList.getTasks().size() + " tasks in the list.");
        return  sb.toString();
    }

    public String printMark(Task marked) {
        return "This task is marked as done:\n" + marked.toString();

    }

    public String printUnmark(Task marked) {
        return "This task is marked as not done:\n" + marked.toString();

    }

    public String printDelete(Task deleted, TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("This task has been removed: ").append(deleted.toString()).append(System.lineSeparator()).append("Now you have " + taskList.getTasks().size() + " tasks in the list.");

        return sb.toString();
    }

    public String printReq(ArrayList<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            stringBuilder.append(i).append(". ").append(tasks.get(i - 1).toString());
        }
        return stringBuilder.toString();
    }


    public void closeSc() {

        this.sc.close();
    }
}