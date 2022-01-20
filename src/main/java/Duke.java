import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
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
                "> Type todo <message> to put a todo in your list\n"+
                "> Type deadline <message> /by <deadline> to put a deadline in your list"+
                "\n> Type event <message> /at <date> to put an event in your list"+
                "\n> Type 'mark <x>' to mark a task in your list" +
                "\n> Type 'unmark <x>' to unmark a task in your list";;
        System.out.println(helpResponse);
    }

    public void printTask() {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            String message = currentTask.getTask();
            System.out.println(i + 1 + ". " + message);
        }
    }

    public void printBye() {
        String byeResponse = "Bye~ Hope to see you again soon!";
        System.out.println(byeResponse);
    }

    public void addTodo(String description){
        Todo entry = new Todo(description);
        this.tasks.add(entry);
        String message = entry.getTask();
        System.out.println("I have added the following todo:\n" + message);
        System.out.println("You now have " + this.tasks.size() + " tasks");
    }

    public void addDeadline(String description, String time) {
        Deadline entry = new Deadline(description, time);
        this.tasks.add(entry);
        String message = entry.getTask();
        System.out.println("I have added the following deadline:\n" + message);
        System.out.println("You now have " + this.tasks.size() + " tasks");
    }

    public void addEvent(String description, String time) {
        Event entry = new Event(description, time);
        this.tasks.add(entry);
        String message = entry.getTask();
        System.out.println("I have added the following event:\n" + message);
        System.out.println("You now have " + this.tasks.size() + " tasks");
    }

    public void unmarkItem(String strID) {
        Task currentTask = this.tasks.get(Integer.parseInt(strID)-1);
        currentTask.setNotDone();
        String message = currentTask.getTask();
        System.out.println("Sure, I have unmarked the following task:\n" + message);
    }

    public void markItem(String strID) {
        Task currentTask = this.tasks.get(Integer.parseInt(strID)-1);
        currentTask.setDone();
        String message = currentTask.getTask();
        System.out.println("Ok, I have marked the following task:\n" + message);
    }

    public void check(Duke ducky, Scanner myObj) {
        String response = myObj.nextLine();
        String [] textEntered = response.split(" ", 2);
        String command = textEntered[0];

        if (command.equals("bye")){
            ducky.printBye();
            System.exit(1);
        } else if(command.equals("help")){
            ducky.help();
        } else if(command.equals("list")){
            ducky.printTask();
        } else if(command.equals("todo")) {
            String description = textEntered[1];
            ducky.addTodo(description);
        } else if(command.equals("deadline")) {
            String text = textEntered[1];
            String [] textArr = text.split("/by ");
            String description = textArr[0];
            String time = textArr[1];
            ducky.addDeadline(description, time);
        } else if(command.equals("event")) {
            String text = textEntered[1];
            String [] textArr = text.split("/at ");
            String description = textArr[0];
            String time = textArr[1];
            ducky.addEvent(description, time);
        } else if(command.equals("unmark")) {
            String strID = textEntered[1];
            ducky.unmarkItem(strID);
        } else if(command.equals("mark")) {
            String strID = textEntered[1];
            ducky.markItem(strID);
        } else {
            ducky.help();
        }
        System.out.println("~--~~--~~--~~(~v~)~~--~~--~~--~");
    }

    public static void main(String[] args) {
        Duke ducky = new Duke();
        ducky.welcome();

        Scanner myObj = new Scanner(System.in);

        while(true){
            ducky.check(ducky, myObj);
        }
    }
}

