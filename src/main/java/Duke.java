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
        String note = "NOTE: For events and deadlines do remember to include a date in the format of YYYY-MM-DD.\n" +
                "Adding time for events and deadlines are optional, but the format is in the form HH:mm";
        System.out.println("Howdy and welcome to\n" + logo + "\n" + "Feel free to tell duke any tasks you'd like!");
        System.out.println(note);
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
                    printList(list);

                //Check if input == unmark
                } else if(inputData.contains("unmark")){

                    unmark(inputData,list);

                //Check if input == mark
                } else if(inputData.contains("mark")){
                    mark(inputData,list);
                }
                // check if input == delete
                else if(inputData.contains("delete")){
                    delete(inputData, list);
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
                        String[] spl = taskDetails.split("/by");
                        if(spl.length < 2){
                            throw new DukeException("Description of deadline must include a date/time! Did you miss out a /by?");
                        }
                        String details = spl[0].trim();
                        String dateTime = spl[1].trim();
                        newTask = new Event(details,dateTime);
                    } else if(taskType.equals("event")){
                        String[] spl = taskDetails.split("/at");
                        if(spl.length < 2){
                            throw new DukeException("Description of event must include a date/time! Did you miss out a /at?");
                        }
                        String details = spl[0].trim();
                        String dateTime = spl[1].trim();
                        newTask = new Event(details,dateTime);
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

    //Abstracted methods
    public static void printList(ArrayList<Task> taskArrayList) {
        for(int i = 1; i <= taskArrayList.size(); i++){
            Task task = taskArrayList.get(i-1);
            System.out.print(i + ": ");
            task.printTask();
        }
    }
    public static void unmark(String input, ArrayList<Task> taskArrayList) {
        String[] splitted = input.split("\\s+");
        if(splitted.length < 2) {
            System.out.println("Did you miss out the index in your input?");
        } else {
            try {
                int index = Integer.parseInt(splitted[1]);
                if (index > taskArrayList.size() || index <= 0) {
                    System.out.println("Index out of bounds, please try again");
                } else {
                    taskArrayList.get(index - 1).setDone(false);
                    printList(taskArrayList);
                }
            } catch (Exception e) {
                System.out.println("You have entered an invalid input");
            }
        }
    }

    public static void mark(String input, ArrayList<Task> taskArrayList) {
        String[] splitted = input.split("\\s+");
        if(splitted.length < 2) {
            System.out.println("Did you miss out the index in your input?");
        } else {
            try {
                int index = Integer.parseInt(splitted[1]);
                if (index > taskArrayList.size() || index <= 0) {
                    System.out.println("Index out of bounds, please try again");
                } else {
                    taskArrayList.get(index - 1).setDone(true);
                    printList(taskArrayList);
                }
            } catch (Exception e) {
                System.out.println("You have entered an invalid input");
            }
        }
    }

    public static void delete(String input, ArrayList<Task> taskArrayList) {
        String[] splitted = input.split("\\s+");
        if(splitted.length < 2) {
            System.out.println("Did you miss out the index in your input?");
        } else {
            try {
                int index = Integer.parseInt(splitted[1]);
                if (index > taskArrayList.size() || index <= 0) {
                    System.out.println("Index out of bounds, please try again");
                } else {
                    taskArrayList.remove(index - 1);
                    printList(taskArrayList);
                }
            } catch (Exception e) {
                System.out.println("You have entered an invalid input");
            }
        }
    }
}
