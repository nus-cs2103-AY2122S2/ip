import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

public class Duke {
    public ArrayList<Task> userTasks;

    public Duke(){
        userTasks = new ArrayList<Task>();
    }

    public void print(Object str){
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
        this.print("Perfect! You have added this Deadline Item:\n" + userDeadline + "\nRemember to stick to your objective date!");
    }

    public void addEvent(String userEventString, String timing){
        Event userEvent = new Event(userEventString, timing);
        this.userTasks.add(userEvent);
        this.print("Perfect! You have added this Event Item:\n" + userEvent + "\nRemember to be there 5 minutes early!");
    }

    public void deleteTask(int taskIndex) throws DukeException{
        if (this.checkValidTask(taskIndex)) {
            Task userTask = this.userTasks.get(taskIndex);
            this.userTasks.remove(taskIndex);
            this.print("I have removed " + userTask);
        }
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

    public boolean checkValidTask(int taskIndex) throws DukeException {
        if ((taskIndex > this.userTasks.size()-1) || (taskIndex <0)) {
            throw new DukeException("There is no such task! Maybe you entered the wrong task?");
        } else {
            return true;
        }
    }

    public void markTaskDone(int taskIndex) throws DukeException { //This is the actual index for arraylist checking (minus one already)
        if (this.checkValidTask(taskIndex)) {
            Task task = this.userTasks.get(taskIndex);
            task.markAsDone();
            this.print("Congratulations on completing the task!\n" + task);
        }
    }

    public void markTaskNotDone(int taskIndex) throws DukeException { //This is the actual index for arraylist checking (minus one already)
        if (this.checkValidTask(taskIndex)) {
            Task task = this.userTasks.get(taskIndex);
            task.markAsNotDone();
            this.print("I have un-marked this task!\n" + task);
        }
    }

    public void performCommand(String userTaskString) throws DukeException{
            if (userTaskString.equalsIgnoreCase("bye")){
                this.printBye();
                System.exit(0);
            } else if (userTaskString.length() == 0){
                throw new DukeException("You have not keyed in any task! Maybe you missed something? Don't worry, just try again!");
            } else if (userTaskString.equalsIgnoreCase("list")){
                this.printUserTasks();
            } else if (userTaskString.matches("^[deltDELT]{6}.*")){
                Pattern pattern = Pattern.compile("^[deltDELT]{6}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    try {
                        this.deleteTask(Integer.parseInt(userTaskString.substring(matcher.end()+1))-1);
                        //+1 is to take into account the " "after delete, -1 is to convert it into 0-based indexing
                    } catch (StringIndexOutOfBoundsException err) { // For cases like "delete" without a number
                        throw new DukeException("Please enter a task number to delete!");
                    }catch (NumberFormatException err) { // For cases like "delete1" without a space in between or "delete 3b" where the term is not a number
                        throw new DukeException("Please delete a task in the following format:\n" +
                                "delete [number]");
                    }
                }
            } else if (userTaskString.matches("^[markMARK]{4}.*")){
                Pattern pattern = Pattern.compile("^[markMARK]{4}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    try {
                        this.markTaskDone(Integer.parseInt(userTaskString.substring(matcher.end()+1))-1);
                        //+1 is to take into account the " "after mark, -1 is to convert it into 0-based indexing
                    } catch (StringIndexOutOfBoundsException err) { // For cases like "mark" without a number
                        throw new DukeException("Please enter a task number to mark!");
                     }catch (NumberFormatException err) { // For cases like "mark1" without a space in between or "mark 3b" where the term is not a number
                        throw new DukeException("Please mark a task in the following format:\n" +
                                "mark [number]");
                    }
                }
            } else if (userTaskString.matches("^[unmarkUNMARK]{6}.*")){
                Pattern pattern = Pattern.compile("^[unmarkUNMARK]{6}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    try {
                        this.markTaskNotDone(Integer.parseInt(userTaskString.substring(matcher.end()+1))-1);
                        //+1 is to take into account the " "after unmark, -1 is to convert it into 0-based indexing
                    } catch (StringIndexOutOfBoundsException err) { // For cases like "unmark" without a number
                        throw new DukeException("Please enter a task number to unmark!");
                    } catch (NumberFormatException err) { // For cases like "unmark1" without a space in between or "unmark 3b" where the term is not a number
                        throw new DukeException("Please unmark a task in the following format:\n" +
                                "unmark [number]");
                    }
                }
            } else if (userTaskString.matches("^[todTOD]{4}.*")){
                Pattern pattern = Pattern.compile("^[todTOD]{4}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    try {
                        this.addTodo(userTaskString.substring(matcher.end()+1));
                    } catch (StringIndexOutOfBoundsException err) { //For cases like "todo" without any further description
                        throw new DukeException("Please enter a description!");
                    }
                }
            } else if (userTaskString.matches("^[dealinDEALIN]{8}.*")){
                Pattern pattern = Pattern.compile("^[dealinDEALIN]{8}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    try {
                        String userTaskStringSliced = userTaskString.substring(matcher.end()+1);
                        String[] descriptionDeadlineSplit = userTaskStringSliced.split(" /by ", 2);
                        this.addDeadline(descriptionDeadlineSplit[0], descriptionDeadlineSplit[1]);
                    } catch (StringIndexOutOfBoundsException err) { // For cases like "deadline" without any other information
                        throw new DukeException("Please enter a Deadline in the following format:\n" +
                                "deadline [description] /by [datetime]");
                    } catch (ArrayIndexOutOfBoundsException err) { //For cases like "deadline homework" without a "/by"
                        throw new DukeException("Please enter a Deadline in the following format:\n" +
                                "deadline [description] /by [datetime]");
                    }

                }
            } else if (userTaskString.matches("^[evntEVNT]{5}.*")){
                Pattern pattern = Pattern.compile("^[evntEVNT]{5}");
                Matcher matcher = pattern.matcher(userTaskString);
                if (matcher.find()) { // Finds first match (finds a match for each time .find() is run)
                    try {
                        String userTaskStringSliced = userTaskString.substring(matcher.end()+1);
                        String[] descriptionTimingSplit = userTaskStringSliced.split(" /at ", 2);
                        this.addEvent(descriptionTimingSplit[0], descriptionTimingSplit[1]);
                    } catch (StringIndexOutOfBoundsException err) { // For cases like "event" without any other information
                        throw new DukeException("Please enter an Event in the following format:\n" +
                                "event [description] /at [datetime]");
                    } catch (ArrayIndexOutOfBoundsException err) { //For cases like "event meeting" without a "/at"
                        throw new DukeException("Please enter an Event in the following format:\n" +
                                "event [description] /at [datetime]");
                    }
                }
            } else { // If a user does not specify a todo, deadline or event
                throw new DukeException("Invalid Input! Please either add in a Todo, Deadline or Event!");
            }
        }

    public static void main(String[] args) throws DukeException{
        Scanner sc= new Scanner(System.in);
        Duke duke = new Duke();
        duke.print("Hello, My Dear Friend... I'm Duke, your personal motivator!");
        Quote quoteOfTheDay = new Quote();
        duke.print(quoteOfTheDay.generateQuote());
        duke.print("What can i do for you today?");
        while (true) {
            String userTaskString = sc.nextLine();
            try {
                duke.performCommand(userTaskString);
            } catch (DukeException exception){
                duke.print(exception);
            }
        }
    }
}
