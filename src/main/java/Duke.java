import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int numOfTask = 0;
    private static final int EVENT_OFFSET = 5;
    private static final int TODO_OFFSET = 4;
    private static final int DEADLINE_OFFSET = 8;
    private static final int INPUT_OFFSET = 3;

    public static void addToList(Task t) {
        String message = "Got it. I've added this task:\n";
        taskList.add(t);
        numOfTask++;
        try {
            writeToFile("C:\\data\\TaskData.txt");
        } catch (IOException e ) {
            System.out.println("Something happened to the text file !" + e.getMessage());
        }
        System.out.println(message + t.toString() + "\nNow you have " + numOfTask + " tasks in the list.");
    }

    public static void storeToList(Task t) { //same as addtoList but no printing
        taskList.add(t);
        numOfTask++;
    }

    public static void deleteTask(int taskNum) {
        String message = "Noted. I've removed this task:\n";
        int actualTaskNum = taskNum - 1;
        System.out.println(message + taskList.get(actualTaskNum).toString());
        taskList.remove(actualTaskNum);
        numOfTask--;
        updateTextFile();
        System.out.println("Now you have " + numOfTask + " tasks in the list.");
    }

    public static void markTask(int taskNum) {
        String message = "Nice! I've marked this task as done:\n" ;
        int actualTaskNum = taskNum - 1; //minus 1 as list index is from 0
        Task t = taskList.get(actualTaskNum); // get the task from the array
        t.setTaskDone();
        updateTextFile();
        System.out.println(message + t.toString());
    }

    public static void unMarkTask(int taskNum) {
        String message = "OK, I've marked this task as not done yet:\n";
        int actualTaskNum = taskNum - 1;
        Task t = taskList.get(actualTaskNum); // get the task from the array
        t.setTaskNotDone();
        updateTextFile();
        System.out.println(message + t.toString());
    }

    public static void printList(){
        String message = "Here are the tasks in your list:";
        System.out.println(message);

        for(int i = 0; i < numOfTask; i++){
            String output = i + 1 + "." + taskList.get(i).toString();
            System.out.println(output);
        }
    }

    public static void readFileDataAndStoreInList(File f) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        while ((sc.hasNextLine())) {
            String input = sc.nextLine();
            String[] inputSplit = input.split("\\|"); //split input by |
            String task = inputSplit[0];
            Integer mark = Integer.parseInt(inputSplit[1]);
            if(task.equals("T")) {
                Todo tempTask = new Todo(inputSplit[2]);
                if(mark == 1) {
                    tempTask.setTaskDone();
                }
                storeToList(tempTask);
            } else if(task.equals("D")) {
                Deadline tempTask = new Deadline(inputSplit[2],inputSplit[3]);
                if(mark == 1) {
                    tempTask.setTaskDone();
                }
                storeToList(tempTask);
            } else if (task.equals("E")) {
                Event tempTask = new Event(inputSplit[2],inputSplit[3]);
                if(mark == 1) {
                    tempTask.setTaskDone();
                }
                storeToList(tempTask);
            }
        }
    }

    public static void writeToFile(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        for(int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            fw.write(craftOutput(t));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public static String craftOutput(Task t) {
        String output = "";
        String doneIcon = t.getStatusIcon();
        if(t instanceof Todo) {
            if(doneIcon.equals("X")) {
                output = "T|1|" + t.getDescription();
            } else {
                output = "T|0|" + t.getDescription();
            }
        } else if(t instanceof Deadline) {
            if(doneIcon.equals("X")) {
                output = "D|1|" + t.getDescription() + "|" + ((Deadline) t).getBy();
            } else {
                output = "D|0|" + t.getDescription() + "|" + ((Deadline) t).getBy();
            }
        } else if(t instanceof Event) {
            if(doneIcon.equals("X")) {
                output = "E|1|" + t.getDescription() + "|" + ((Event) t).getAt();
            } else {
                output = "E|0|" + t.getDescription() + "|" + ((Event) t).getAt();
            }
        }
        return output;
    }

    public static void updateTextFile() {
        try {
            writeToFile("C:\\data\\TaskData.txt");
        } catch (IOException e ) {
            System.out.println("Something happened to the text file !" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        //checkfile
        try {
            File directory = new File("C:\\data");
            File inputFile = new File("C:\\data\\TaskData.txt");
            if(!directory.exists()) {
                throw new FileNotFoundException("Please create a data directory in C:");
            } else if(directory.exists() && !inputFile.exists()) {
                throw new FileNotFoundException("Please create a TaskData.txt file under C:\\data");
            } else { //both have
                try {
                    readFileDataAndStoreInList(inputFile);
                } catch (FileNotFoundException e) {
                    System.out.println("FileNotFound");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        String greeting = "Hello! I'm TaskJamie\nWhat can i do for you?";
        String ending =  "Bye. Hope to see you again soon!";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            try {
                String input = sc.nextLine();

                if(input.length() == 0) {
                    throw new BlankCommandException();
                }

                String[] inputSplit = input.split(" "); //split input by space
                String command = inputSplit[0];

                if (command.equals("bye")) {
                    System.out.print(ending);
                    break;

                } else if (command.equals("list")) {
                    printList();

                } else if (command.equals("todo")) {
                    String description = input.substring(TODO_OFFSET).trim();

                    if(description.length() == 0) {
                        throw new IncompleteCommandException(command);
                    }

                    addToList(new Todo(description));

                } else if (command.equals("deadline")) {
                    String[] inputSlash = input.split("/");
                    String description = inputSlash[0].substring(DEADLINE_OFFSET).trim();

                    if(description.length() == 0) {
                        throw new IncompleteCommandException(command);
                    }

                    String time = inputSlash[1].substring(INPUT_OFFSET);
                    addToList(new Deadline(description, time));

                } else if (command.equals("event")) {
                    String[] inputSlash = input.split("/");
                    String description = inputSlash[0].substring(EVENT_OFFSET).trim();

                    if(description.length() == 0) {
                        throw new IncompleteCommandException(command);
                    }

                    String time = inputSlash[1].substring(INPUT_OFFSET);
                    addToList(new Event(description, time));

                } else if (command.equals("mark")) {
                    markTask(Integer.parseInt(inputSplit[1]));

                } else if (command.equals("unmark")) {
                    unMarkTask(Integer.parseInt(inputSplit[1]));

                } else if (command.equals("delete")) {
                    deleteTask(Integer.parseInt(inputSplit[1]));

                } else {
                    throw new InvalidCommandException();
                }
            }  catch (IncompleteCommandException | InvalidCommandException | BlankCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
