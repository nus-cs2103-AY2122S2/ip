package duke.ui;

import duke.task.Task;
import java.util.List;

public class Ui {
    private String lineBreak = "-------------------------------\n";
    private String welcomeMessage =  "Hello! I'm Duke\nWhat can I do for you?\n";
    private String goodbyeMessage = "Bye. Hope to see you again soon!";

    public void greet(){
        System.out.println(lineBreak + welcomeMessage + lineBreak);
    }

    public void goodbye(){
        System.out.println(goodbyeMessage);
    }

    public void showLine() {
        System.out.println(lineBreak);
    }

    public void taskAddedMessage(Task task, int noOfTasks){
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + noOfTasks + " tasks in the list.");
    }

    public void list(List<Task> tasks){
        System.out.printf("You currently have %d task in your list:\n", tasks.size());
        for(int i = 0; i < tasks.size(); i++){
            System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
        }
    }

    public void taskMarkedMessage(Task task){
       System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    public void taskUnmarkedMessage(Task task){
        System.out.println("OK! I've marked this task as not done yet:\n" + task.toString());
    }
    public void taskDeleteMessage(Task task, int noOfTasks){
        System.out.println("OK! I've deleted this task:\n" + task.toString()
            + "\nNow you have " + noOfTasks + " tasks in the list.");
    }

    public void notEnoughFieldsMessage() {
        showLine();
        System.out.println("Not enough fields, please check your inputs and try again.");
        showLine();
    }
    public void invalidIndex() {
        showLine();
        System.out.println("Index provided is not an integer. Please try again");
        showLine();
    }
    public void invalidDate() {
        showLine();
        System.out.println("Invalid date: Please format date as yyyy-mm-dd");
        showLine();
    }

    public void listFindResults(List<Task> result) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < result.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, result.get(i).toString());
        }
    }

    public void taskNotFound() {
        System.out.println("Sorry! There are no matching tasks found.");
    }
}

