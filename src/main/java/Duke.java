import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

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

    public void addTodo(String userTodoString){
        Todo userTodo = new Todo(userTodoString);
        this.userTasks.add(userTodo);
        this.print("Fantabulous! You have added this Todo Item:\n" + userTodo);
    }

    public void addDeadline(String userDeadlineString, String deadline){
        Deadline userDeadline = new Deadline(userDeadlineString, deadline);
        this.userTasks.add(userDeadline);
        this.print("Perfect! You have added this Deadline Item:\n" + userDeadline + "\n Remember to stick to your objective date!");
    }

    public void addEvent(String userEventString, String timing){
        Event userEvent = new Event(userEventString, timing);
        this.userTasks.add(userEvent);
        this.print("Perfect! You have added this Event Item:\n" + userEvent + "\n Remember to be there 5 minutes early!");
    }

    public void printUserTasks(){
        int numberOfTasks = this.userTasks.size();
        int counter = 0;
        if (numberOfTasks == 0){
            this.print("You currently do not have any outstanding tasks! Great job! :D");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 1; i <= numberOfTasks; i++) {
                Task userTask = this.userTasks.get(i - 1);
                System.out.println((String.valueOf(i) + ": " + userTask));
                if (!userTask.checkIsDone()) {
                    counter += 1;
                }
            }
            if (counter == 0) {
                this.print("You have completed all your tasks! Great job!");
            } else {
                this.print("These are all your tasks! Only " + String.valueOf(counter) + " more remaining! Keep going!");

            }
        }
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
            if (userTaskString.equalsIgnoreCase("bye")){
                this.printBye();
                System.exit(0);
            } else if (userTaskString.length() == 0){
                this.print("You have not keyed in any task! Maybe you missed something? Don't worry, just try again!");
            } else if (userTaskString.equalsIgnoreCase("list")){
                this.printUserTasks();
            } else if (userTaskString.matches("^[markMARK]{4}.*")){
                Pattern pattern = Pattern.compile("^[markMARK]{4}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    this.markTaskDone(Integer.parseInt(userTaskString.substring(matcher.end()+1))-1);
                }
            } else if (userTaskString.matches("^[unmarkUNMARK]{6}.*")){
                Pattern pattern = Pattern.compile("^[unmarkUNMARK]{6}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    this.markTaskNotDone(Integer.parseInt(userTaskString.substring(matcher.end()+1))-1);
                }
            } else if (userTaskString.matches("^[todTOD]{4}.*")){
                Pattern pattern = Pattern.compile("^[todTOD]{4}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    this.addTodo(userTaskString.substring(matcher.end()+1));
                }
            } else if (userTaskString.matches("^[dealinDEALIN]{8}.*")){
                Pattern pattern = Pattern.compile("^[dealinDEALIN]{8}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    String userTaskStringSliced = userTaskString.substring(matcher.end()+1);
                    String[] descriptionDeadlineSplit = userTaskStringSliced.split(" /by ", 2);
                    this.addDeadline(descriptionDeadlineSplit[0], descriptionDeadlineSplit[1]);
                }
            } else if (userTaskString.matches("^[evntEVNT]{5}.*")){
                Pattern pattern = Pattern.compile("^[evntEVNT]{5}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    String userTaskStringSliced = userTaskString.substring(matcher.end()+1);
                    String[] descriptionTimingSplit = userTaskStringSliced.split(" /at ", 2);
                    this.addEvent(descriptionTimingSplit[0], descriptionTimingSplit[1]);
                }
            } else { // If a user is adding tasks
                this.print("Invalid Input! Please either add in a Todo, Deadline or Event!");
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
