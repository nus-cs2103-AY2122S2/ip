package duke.helpTool;

import java.util.Scanner;

public class Ui {
    private static final Scanner myObj = new Scanner(System.in);

    public Ui() {
    }


    public String readCommand(){
        return myObj.nextLine();
    }

    public void closeReading(){
        myObj.close();
    }

    public void showExceptionError(DukeException e){
        System.out.println(e.getMessage());
    }

    public void showSuccessMark(String input){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(input);
    }

    public void showSuccessUnmark(String input){
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(input);
    }

    public void showDelete(String input, int sizeAfterDelete){
        System.out.println("OK, I've removed this task:");
        System.out.println("\t" + input);
        System.out.format("Now you have %d tasks in the list.\n",sizeAfterDelete);
    }

    public void showList(TaskList tasks){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize() ; i++) {
            if (tasks.getTask(i) != null) {
                System.out.format("%s. %s\n", i + 1, tasks.getTask(i).toString());
            }
        }
    }

    public void showAddTodo(String todo, int size) {
        String result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                todo, size);
        System.out.println(result);
    }

    public void showAddDeadline(String ddl, int size){
        String result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                ddl, size);
        System.out.println(result);
    }
    public void showAddEvent(String event, int size){
        String result = String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                event, size);
        System.out.println(result);
    }

    public void showLine(){
        System.out.println("____________________________________________________________");
    }

    public void greet(){
        showLine();
        System.out.println("\tHello I am DDX");
        System.out.println("\tWhat can I do for you?");
        showLine();
    }

    public void bye(){
        // print result
        System.out.println("\tBye. Hope to see you again soon!");
    }

}
