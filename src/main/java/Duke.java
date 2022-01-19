import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public ArrayList<String> userTasks;

    public Duke(){
        userTasks = new ArrayList<String>();
    }

    public void print(String str){
        System.out.println("\t" + str);
        System.out.println("_".repeat(100));
    }

    public void printBye(){
        this.print("Bye. Hope I've motivated you as much as I could have, and SMILE :D");
    }

    public void addTask(String userTask){
        this.userTasks.add(userTask);
    }

    public void printUserTasks(){
        for (int i = 1;i<=this.userTasks.size();i++) {
            System.out.println((String.valueOf(i) + ": " + this.userTasks.get(i-1)));
        }
        System.out.println("These are all your tasks! Complete them and be soooo satisfied :D");
        System.out.println("_".repeat(100));
    }

    public void performCommand(String userTask){
            if (userTask.equalsIgnoreCase("bye")){
                this.printBye();
                System.exit(0);
            } else if (userTask.length() == 0){
                this.print("You have not keyed in any task! Maybe you missed something? Don't worry, just try again!");
            } else if (userTask.equalsIgnoreCase("list")){
                this.printUserTasks();
            } else{
                this.addTask(userTask);
                this.print("Wonderful! You've added a task and are one step closer to your goals!\n" +
                        "Added your task: " + userTask);
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
            String userTask = sc.nextLine();
            duke.performCommand(userTask);
        }
    }
}
