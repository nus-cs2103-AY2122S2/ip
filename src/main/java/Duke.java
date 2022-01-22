/**
 * This is the main Duke program that will be able to process a Task of 3 types: todo, deadline and task
 * Duke is able to list, delete and mark/unmark tasks as done/undone.
 *
 * @author Toh Zhan Qing
 */

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Duke {

    static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        System.out.println("What can i do for you?");
        Storage storage = new Storage();
        storage.load();
        while(!isBye) {
            try {
                String input = sc.nextLine();
                isBye = parse(input);
            }
            catch (EmptyDescriptorExceptions e){
                System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
            }
        }
    }

    public static boolean parse(String input) throws EmptyDescriptorExceptions{
        if (input.equals("bye")) {
            System.out.println("Bye. I hope to see you sometime soon! :)");
            return true;
        } else {
            if (input.equals("list")) {
                listAllTask();
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                markTask(input);
            } else if (input.startsWith("todo")) {
                taskList.add(new ToDo(input.substring(4,input.length()), Task.totalTask, false));
            } else if (input.startsWith("deadline") || input.startsWith("event")) {
                if (input.startsWith("deadline")) {
                    String[] inputArr = input.split("/by ");
                    if (inputArr.length == 1) {
                        throw new EmptyDescriptorExceptions();
                    }
                    taskList.add(new Deadline(inputArr[0].substring(8, inputArr[0].length()), Task.totalTask, inputArr[1], false));
                } else {
                    String[] inputArr = input.split("/at ");
                    if (inputArr.length == 1) {
                        throw new EmptyDescriptorExceptions();
                    }
                    taskList.add(new Event(inputArr[0].substring(5, inputArr[0].length()), Task.totalTask, inputArr[1], false));
                }
            } else if (input.startsWith("delete")) {
                deleter(Integer.parseInt(input.substring(7, input.length())));
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what does that mean :-(");
            }
            return false;
        }
    }


    public static void markTaskNum(int taskNum, String check){
        if (check.equals("true")) {
            taskList.get(taskNum).mark();
        }
    }

    /**
     * Deletes a specified index (starts from 1) from the input ArrayList and shifts
     * all subsequent task numbers accordingly by +1.
     *
     * @param num index (starts from 1) to delete
     */
    public static void deleter(int num){
        if (num > 0 && num < Task.totalTask){
            num--;
            System.out.println(" Noted. I've removed this task: ");
            System.out.printf("  [%s][%s] %s\n",taskList.get(num).type, taskList.get(num).getStatus(), taskList.get(num).name);
            taskList.remove(num);
            Task.totalTask--;
            for(int i = num; i <Task.totalTask; i++){
                taskList.get(i).decrementNum();
            }
            System.out.printf("Now you have %d tasks in the list.\n", Task.totalTask);
        } else {
            System.out.println("☹ OOPS!!! There is no such task found.");
        }
        Storage.writeAllToFile();
    }

    /**
     * Marks/Unmarks tasks as done/undone. Marking tasks that are already marked will have no change;
     * same with unmarking unmarked tasks.
     *
     * @param input Original input string that was entered
     */
    public static void markTask(String input){
        String[] inputArr = input.split(" ");
        int taskNum = Integer.parseInt(inputArr[1]) - 1;
        Task curr = taskList.get(taskNum);
        if (input.startsWith("mark")){
            curr.mark();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.printf("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
        } else {
           curr.unmark();
            System.out.println("OK, I've marked this task as not done yet: ");
            System.out.printf("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
        }
        Storage.writeAllToFile();
    }

    /**
     * Lists all tasks in tasklist. (in input order)
     *
     */
    public static void listAllTask(){
        System.out.printf("Here are the tasks in your list:\n");
        for(int i=0; i<Task.totalTask; i++){
            System.out.print(taskList.get(i).toString());
        }
        System.out.println("__________________________________________________________________");
    }

}
