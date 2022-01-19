import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public ArrayList<Task> userTasks;

    public Duke(){
        userTasks = new ArrayList<Task>();
    }

    public void print(String str){
        System.out.println(str);
        System.out.println("_".repeat(100));
    }

    public void printBye(){
        this.print("Bye. Hope I've motivated you as much as I could have, and SMILE :D");
    }

    public void addTask(String userTaskString){
        Task userTask = new Task(userTaskString);
        this.userTasks.add(userTask);
    }

    public void printUserTasks(){
        for (int i = 1;i<=this.userTasks.size();i++) {
            System.out.println((String.valueOf(i) + ": " + this.userTasks.get(i-1)));
        }
        System.out.println("These are all your tasks! Complete them and be soooo satisfied :D");
        System.out.println("_".repeat(100));
    }

    public boolean checkValidTask(int taskIndex){
        if (taskIndex > this.userTasks.size()-1) {
            return false;
        } else {
            return true;
        }
    }

    public void markTaskDone(int taskIndex){ //This is the actual index for arraylist checking (minus one already)
        if (checkValidTask(taskIndex)) {
            Task task = this.userTasks.get(taskIndex);
            task.markAsDone();
            this.print("Congratulations on completing the task!\n" + task);
        } else {
            this.print("There is no such task! Maybe you entered the wrong task?");
        }

    }

    public void markTaskNotDone(int taskIndex){ //This is the actual index for arraylist checking (minus one already)
        if (checkValidTask(taskIndex)) {
            Task task = this.userTasks.get(taskIndex);
            task.markAsNotDone();
            this.print("I have un-marked this task!\n" + task);
        } else {
            this.print("There is no such task! Maybe you entered the wrong task?");
        }
    }

    public void performCommand(String userTaskString){
        String[] splitString = userTaskString.split(" ");
            if (userTaskString.equalsIgnoreCase("bye")){
                this.printBye();
                System.exit(0);
            } else if (userTaskString.length() == 0){
                this.print("You have not keyed in any task! Maybe you missed something? Don't worry, just try again!");
            } else if (userTaskString.equalsIgnoreCase("list")){
                this.printUserTasks();
            } else if (splitString[0].equalsIgnoreCase("mark")){
                this.markTaskDone(Integer.parseInt(splitString[1])-1);
            } else if (splitString[0].equalsIgnoreCase("unmark")){
                this.markTaskNotDone(Integer.parseInt(splitString[1])-1);
            } else{ // If a user is adding tasks
                this.addTask(userTaskString);
                this.print("Wonderful! You've added a task and are one step closer to your goals!\n" +
                        "Added your task: " + userTaskString);
            }
        }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Duke duke = new Duke();
        duke.print("Hello, My Dear Friend... I'm Duke, your personal motivator!");
        Quote quoteOfTheDay = new Quote();
        duke.print(quoteOfTheDay.generateQuote());
        duke.print("What can i do for you today?");
        while (true) {
            String userTaskString = sc.nextLine();
            duke.performCommand(userTaskString);
        }
    }
}
