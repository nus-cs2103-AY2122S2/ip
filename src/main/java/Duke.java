import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy and welcome to\n" + logo + "\n" + "Feel free to tell duke any tasks you'd like!");
        System.out.println("-----------------------------------");

        //Initialise objects needed
        String inputData;
        ArrayList<Task> list = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String PATH = "./src/main/data/data.txt";
        String FILE_DIR = "./src/main/data";

        //Level 7 - Check if data folder exists
        try {
            File file = new File(PATH); // create a File for the given file path
            if (file.exists()) {
                Scanner sc = new Scanner(file); // create a Scanner using the File as the source
                while (sc.hasNextLine()) {
                    String[] taskLine = sc.nextLine().split("~");
                    Task task;
                    String taskType = taskLine[0];
                    try {
                        if (taskType.equals("T")) {
                            task = new Todo(taskLine[2]);
                        } else if (taskType.equals("D")) {
                            String date = taskLine[3];
                            date = "by " + date;
                            task = new Deadline(taskLine[2], date);
                        } else if (taskType.equals("E")) {
                            String date = taskLine[3];
                            date = "at " + date;
                            task = new Event(taskLine[2], date);
                        } else {
                            throw new DukeException("Invalid task was read");
                        }
                        if (taskLine[1].equals("X")) {
                            task.setDone(true);
                        }
                        list.add(task);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while(true) {

            try{
                //Take in input
                inputData = scan.nextLine();

                //Check if input == bye
                if(inputData.equals("bye")) {
                    System.out.println("~BYE!~ Come back to Duke anytime");
                    break;
                }

                //Check if input == list
                else if(inputData.equals("list")){
                    for(int i = 1; i <= list.size(); i++){
                        Task task = list.get(i-1);
                        System.out.print(i + ": ");
                        task.printTask();
                    }

                //Check if input == unmark
                } else if(inputData.contains("unmark")){
                    //split string by space
                    String[] splitted = inputData.split("\\s+");
                    int index = Integer.parseInt(splitted[1]);
                    if(index > list.size() || index <= 0){
                        System.out.println("Index out of bounds, please try again");
                    } else {
                        list.get(index-1).setDone(false);
                        for (int i = 1; i <= list.size(); i++) {
                            Task task = list.get(i - 1);
                            System.out.print(i + ": ");
                            task.printTask();
                        }
                    }
                //Check if input == mark
                } else if(inputData.contains("mark")){
                    //split string by space
                    String[] splitted = inputData.split("\\s+");
                    int index = Integer.parseInt(splitted[1]);
                    if(index > list.size() || index <= 0){
                        System.out.println("Index out of bounds, please try again");
                    } else {
                        list.get(index-1).setDone(true);
                        for (int i = 1; i <= list.size(); i++) {
                            Task task = list.get(i - 1);
                            System.out.print(i + ": ");
                            task.printTask();
                        }
                    }
                }
                // check if input == delete
                else if(inputData.contains("delete")){
                    String[] splitted = inputData.split("\\s+");
                    int index = Integer.parseInt(splitted[1]);
                    if(index > list.size() || index <= 0){
                        System.out.println("Index out of bounds, please try again");
                    } else {
                        list.remove(index-1);
                        for (int i = 1; i <= list.size(); i++) {
                            Task task = list.get(i - 1);
                            System.out.print(i + ": ");
                            task.printTask();
                        }
                    }
                }
                //input is a new type of task
                else if(inputData.contains("todo") || inputData.contains("event") || inputData.contains("deadline")) {
                    //identify type of task
                    String[] arr = inputData.split(" ", 2);
                    
                    if(arr.length < 2){
                        throw new DukeException("Description of task cannot be empty!");
                    }

                    String taskType = arr[0];
                    String taskDetails = arr[1];

                    Task newTask = new Task("");

                    if(taskType.equals("todo")){
                        newTask = new Todo(taskDetails);
                    } else if(taskType.equals("deadline")){
                        String[] spl=taskDetails.split("/");
                        if(spl.length < 2){
                            throw new DukeException("Description of deadline must include a date/time! Did you miss out a /by?");
                        }
                        String details=spl[0].trim();
                        String date=spl[1].trim();
                        newTask = new Deadline(details,date);
                    } else if(taskType.equals("event")){
                        String[] spl=taskDetails.split("/");
                        if(spl.length < 2){
                            throw new DukeException("Description of event must include a date/time! Did you miss out a /at?");
                        }
                        String details=spl[0].trim();
                        String date=spl[1].trim();
                        newTask = new Event(details,date);
                    }

                    list.add(newTask);
                    System.out.println("Got it. The task has been added:");
                    newTask.printTask();
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                } else {
                    throw new DukeException("no such task type");
                }
                System.out.println("-----------------------------------");

            } catch (DukeException e) {
                System.out.println(e);
                System.out.println("-----------------------------------");
            }

            //Saving the changes back to file
            File file = new File(FILE_DIR);
            //if prev file exists, delete it and replace with new empty file
            try {
                if (!file.exists()) {
                    file.mkdir();
                }
            } catch(Exception e) {
                System.out.println(e);
            }

            //Writing to empty txt file
            try {
                FileWriter fileWriter = new FileWriter(PATH,false);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (Task t: list) {
                    String taskToAppend = "";

                    //Identify task type
                    if(t instanceof Todo) {
                        taskToAppend += "T~";
                    } else if(t instanceof Deadline) {
                        taskToAppend += "D~";
                    } else if(t instanceof Event) {
                        taskToAppend += "E~";
                    }

                    //Identify if task is done
                    if(t.done) {
                        taskToAppend += "X~";
                    } else {
                        taskToAppend += " ~";
                    }
                    taskToAppend += t.taskName + "~";
                    if(t instanceof Deadline) {
                        Deadline tempTask = (Deadline) t;
                        String date = tempTask.date;
                        date = date.replace("(by:","");
                        date = date.replace(")","");
                        taskToAppend += date.trim();
                    } else if (t instanceof Event) {
                        Event tempTask = (Event) t;
                        String date = tempTask.date;
                        date = date.replace("(at:","");
                        date = date.replace(")","");
                        taskToAppend += date.trim();
                    }
                    printWriter.println(taskToAppend);
                }
                printWriter.close();
            } catch(IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

        scan.close();
    }
}
