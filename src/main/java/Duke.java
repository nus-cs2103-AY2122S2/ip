import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____              _"
                    + "\n|  _ \\ _   _ _____| | ____ __\n"
                    + "| | | | | | |  ___| |/ /\\ v /\n"
                    + "| |_| | |_| | |___|   <  | |\n"
                    + "|____/ \\___/\\_____|_|\\_\\ |_|\n";

        ArrayList<Task> tasks = new ArrayList<Task>();

        String helpResponse = "> Type 'list' to see what you have in your task list\n"+
                "> Type todo <message> to put a todo in your list\n"+
                "> Type deadline <message> /by <deadline> to put a deadline in your list"+
                "\n> Type event <message> /at <date> to put an event in your list"+
                "\n> Type 'mark <x>' to mark a task in your list" +
                "\n> Type 'unmark <x>' to unmark a task in your list";;

        System.out.println(logo + "Hello! I'm Ducky! :)\n" + "I am a task manager.\n" +
                "Type 'help' for more information on the commands you can give me.\n" +
                "What can I do for you today?\n" +
                "---------------------------------------------");

        Scanner myObj = new Scanner(System.in);

        while(true){
            String response = myObj.nextLine();
            if (response.equals("bye")){
                String byeResponse = "Bye~ Hope to see you again soon!";
                System.out.println(byeResponse);
                break;
            } else if(response.equals("help")){
                System.out.println(helpResponse);
            } else if(response.equals("list")){
                System.out.println("Here are your tasks:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task currentTask = tasks.get(i);
                    String message = currentTask.getTask();
                    System.out.println(i+1 +". " + message);
                }
            } else if(response.contains("todo")) {
                String [] textEntered = response.split(" ", 2);
                String description = textEntered[1];
                Todo entry = new Todo(description);
                tasks.add(entry);
                String message = entry.getTask();
                System.out.println("I have added the following todo:\n" + message);
                System.out.println("You now have " + tasks.size() + " tasks");
            } else if(response.contains("deadline")) {
                String [] textEntered = response.split(" ", 2);
                String text = textEntered[1];
                String [] textArr = text.split("/by ");
                String description = textArr[0];
                String time = textArr[1];
                Deadline entry = new Deadline(description, time);
                tasks.add(entry);
                String message = entry.getTask();
                System.out.println("I have added the following deadline:\n" + message);
                System.out.println("You now have " + tasks.size() + " tasks");
            } else if(response.contains("event")) {
                String [] textEntered = response.split(" ", 2);
                String text = textEntered[1];
                String [] textArr = text.split("/at ");
                String description = textArr[0];
                String time = textArr[1];
                Event entry = new Event(description, time);
                tasks.add(entry);
                String message = entry.getTask();
                System.out.println("I have added the following event:\n" + message);
                System.out.println("You now have " + tasks.size() + " tasks");
            } else if(response.contains("unmark")) {
                String strID = response.substring(response.length() - 1);
                Task currentTask = tasks.get(Integer.parseInt(strID)-1);
                currentTask.setNotDone();
                String message = currentTask.getTask();
                System.out.println("Sure, I have unmarked the following task:\n" + message);
            } else if(response.contains("mark")) {
                String strID = response.substring(response.length() - 1);
                Task currentTask = tasks.get(Integer.parseInt(strID)-1);
                currentTask.setDone();
                String message = currentTask.getTask();
                System.out.println("Ok, I have marked the following task:\n" + message);
            } else {
                System.out.println(helpResponse);
            }
            System.out.println("---------------------------------------------");
        }
    }
}

