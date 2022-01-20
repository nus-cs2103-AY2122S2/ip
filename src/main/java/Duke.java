import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Duke {

    protected ArrayList<Task> tasks;

    public Duke(){
        this.tasks = new ArrayList<Task>();
    }

    public void welcome() {
        String logo = " ____              _"
                + "\n|  _ \\ _   _ _____| | ____ __\n"
                + "| | | | | | |  ___| |/ /\\ v /\n"
                + "| |_| | |_| | |___|   <  | |\n"
                + "|____/ \\___/\\_____|_|\\_\\ |_|\n";

        System.out.println(logo + "Hello! I'm Ducky! :)\n" + "I am a task manager.\n" +
                "Type 'help' for more information on the commands you can give me.\n" +
                "What can I do for you today?\n" +
                "~--~~--~~--~~(~v~)~~--~~--~~--~");
    }

    public void help() {
        String helpResponse = "> Type 'list' to see what you have in your task list\n"+
                "> Type 'todo <message>' to put a todo in your list\n"+
                "> Type 'deadline <message> /by <deadline>' to put a deadline in your list." +
                "\n\t - Deadline must be in 'DD/MM/YYYY' format"+
                "\n> Type 'event <message> /at <date>' to put an event in your list"+
                "\n\t - Date must be in 'DD/MM/YYYY' format" +
                "\n> Type 'mark <x>' to mark a task in your list" +
                "\n> Type 'unmark <x>' to unmark a task in your list";;
        System.out.println(helpResponse);
    }

    public void printTasks() throws DukeException {
        if (this.tasks.size() ==0) {
            throw new DukeException("Please add some tasks first *(^.^)*");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < this.tasks.size(); i++) {
                Task currentTask = this.tasks.get(i);
                String message = currentTask.getTask();
                System.out.println(i + 1 + ". " + message);
            }
        }
    }

    public void printBye() {
        String byeResponse = "Bye~ Hope to see you again soon!";
        System.out.println(byeResponse);
    }

    /*
     * @param description which is the task description from user
     */
    public void addTodo(String description) throws DukeException{
        if (description.length() == 0){
            throw new DukeException("Oops! \\(@.@)/ You have not keyed in a description for the task!\n" +
                    "Let's try again ~(^.^)~\n" +
                    "Type 'help' if you need to know how to use this command");
        }
        else {
            Todo entry = new Todo(description);
            this.tasks.add(entry);
            String message = entry.getTask();
            System.out.println("I have added the following todo:\n" + message);
            System.out.println("You now have " + this.tasks.size() + " tasks");
        }
    }

    /*
     * @param description which is the task description from user
     * @param time which is the deadline provided by user
     */
    public void addDeadline(String description, String time) throws DukeException {
        if (description.length() == 0){
            throw new DukeException("Oops! \\(@.@)/ You have not keyed in a description for the task!\n" +
                    "Let's try again ~(^.^)~\n" +
                    "Type 'help' if you need to know how to use this command");
        }
        if (time.length() == 0){
            throw new DukeException("Oops! \\(@.@)/ You have not keyed in a due date for the task!\n" +
                    "Let's try again ~(^.^)~\n" +
                    "Type 'help' if you need to know how to use this command");
        }
        else{
            Deadline entry = new Deadline(description, time);
            this.tasks.add(entry);
            String message = entry.getTask();
            System.out.println("I have added the following deadline:\n" + message);
            System.out.println("You now have " + this.tasks.size() + " tasks");
        }
    }

    /*
     * @param description which is the task description from user
     * @param time which is the event time provided by user
     */
    public void addEvent(String description, String time) throws DukeException {
        if (description.length() == 0){
            throw new DukeException("Oops! \\(@.@)/ You have not keyed in a description for the task!\n" +
                    "Let's try again ~(^.^)~\n" +
                    "Type 'help' if you need to know how to use this command");
        }
        else {
            Event entry = new Event(description, time);
            this.tasks.add(entry);
            String message = entry.getTask();
            System.out.println("I have added the following event:\n" + message);
            System.out.println("You now have " + this.tasks.size() + " tasks");
        }
    }

    /*
     * @param strID which is the ID number of the task in the list
     */
    public void unmarkItem(String strID) {
        try {
            Task currentTask = this.tasks.get(Integer.parseInt(strID) - 1);
            currentTask.setNotDone();
            String message = currentTask.getTask();
            System.out.println("Sure, I have unmarked the following task:\n" + message);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Sorry, I could not find that \\(T.T)/\n" +
                    "Please type 'list' to view your current entries.");
        }
    }

    /*
     * @param strID which is the ID number of the task in the list
     */
    public void markItem(String strID) {
        try {
            Task currentTask = this.tasks.get(Integer.parseInt(strID) - 1);
            currentTask.setDone();
            String message = currentTask.getTask();
            System.out.println("Ok, I have marked the following task:\n" + message);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Sorry, I could not find the item \\(T.T)/\n" +
                    "Please type 'list' to view your current entries.");
        }
    }

    /*
    * @param Takes in a Duke instance
    * @param Takes in a Scanner object
    * */
    public void check(Duke ducky, Scanner myObj) throws DukeException {
        String response = myObj.nextLine();
        String [] textEntered = response.split(" ", 2);
        String command = textEntered[0];

        if (command.equals("bye")){
            ducky.printBye();
            System.exit(1);
        } else if(command.equals("help")){
            ducky.help();
        } else if(command.equals("list")){
            ducky.printTasks();
        } else if(command.equals("todo")) {
            try {
                String description = textEntered[1];
                ducky.addTodo(description);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! \\(@.@)/ You have not keyed in a description for the task!\n" +
                        "Let's try again ~(^.^)~\n" +
                        "Type 'help' if you need to know how to use this command");
            }
        } else if(command.equals("deadline")) {
            try {
                String text = textEntered[1];
                String[] textArr = text.split("/by ");
                String description = textArr[0];
                if (textArr.length == 1) {
                    throw new DukeException("Oops, please specify a date!");
                }
                String time = textArr[1];
                SimpleDateFormat date = new java.text.SimpleDateFormat("dd/MM/yyyy");
                date.parse(time);
                ducky.addDeadline(description, time);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Oops! \\(@.@)/ You have not keyed in a description for the task!\n" +
                        "Let's try again ~(^.^)~\n" +
                        "Type 'help' if you need to know how to use this command");
            } catch (ParseException e) {
                System.out.println("Oops, please put a valid time format!\n"+
                        "Let's try again ~(^.^)~\n" +
                        "Type 'help' if you need to know how to use this command");
            }
        } else if(command.equals("event")) {
            try {
                String text = textEntered[1];
                String [] textArr = text.split("/at ");
                String description = textArr[0];
                if (textArr.length == 1) {
                    throw new DukeException("Oops, please specify a date!");
                }
                String time = textArr[1];
                SimpleDateFormat date = new java.text.SimpleDateFormat("dd/MM/yyyy");
                date.parse(time);
                ducky.addEvent(description, time);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Oops! \\(@.@)/ You have not keyed in a description for the task!\n" +
                        "Let's try again ~(^.^)~\n" +
                        "Type 'help' if you need to know how to use this command");
            } catch (ParseException e) {
                System.out.println("Oops, please put a valid time format!\n"+
                        "Let's try again ~(^.^)~\n" +
                        "Type 'help' if you need to know how to use this command");
            }
        } else if(command.equals("unmark")) {
            String strID = textEntered[1];
            ducky.unmarkItem(strID);
        } else if(command.equals("mark")) {
            String strID = textEntered[1];
            ducky.markItem(strID);
        } else {
            throw new DukeException("Sorry, I did not catch that! \\(T.T)/\n" +
                    "Please type 'help' to see all commands I can help with.");
        }
        System.out.println("~--~~--~~--~~(~v~)~~--~~--~~--~");
    }

    public static void main(String[] args) throws DukeException {
        Duke ducky = new Duke();
        ducky.welcome();

        Scanner myObj = new Scanner(System.in);

        while(true){
            try{
                ducky.check(ducky, myObj);
            } catch (DukeException e){
                System.out.println(e.toString()+ "\n~--~~--~~--~~(~v~)~~--~~--~~--~") ;
            }
        }
    }
}

