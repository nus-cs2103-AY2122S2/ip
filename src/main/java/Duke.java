import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____              _        \n"
                    + "|  _ \\ _   _ _____| | _  _  _ \n"
                    + "| | | | | | |  ___| |/ /\\ v /\n"
                    + "| |_| | |_| | |___|   <  | |\n"
                    + "|____/ \\___,\\_____|_|\\_\\ |_|\n";

        ArrayList<Task> tasks = new ArrayList<Task>();
        System.out.println("Hello! I'm \n" + logo + ":)" + "\nI am a task manager. " +
                "\nType 'help' for more information on the commands you can give me." +
                "\nWhat can I do for you today?" +
                "\n-----------------");

        String helpResponse = "> Type 'list' to see what you have in your task list"+
                "\n> Type anything to put a task in your list"+
                "\n> Type 'mark <x>' to mark a task in your list" +
                "\n> Type 'unmark <x>' to unmark a task in your list";;

        while(true){
            Scanner myObj = new Scanner(System.in);
            String response = myObj.nextLine();
            Task entry = new Task(response);
            if (response.equals("bye")){
                String byeResponse = "Bye. Hope to see you again soon! " +
                        "\n-----------------";
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
                tasks.add(entry);
                System.out.println("I have added the following task:\n" + response + "\n-----------------");
            }
        }
    }
}

