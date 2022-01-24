import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Tasks;
import task.Deadlines;
import task.Events;
import task.Todos;
import exception.DukeException;
import command.Commands;

public class Duke {

    static final String databasePath = "./DukeDatabase.txt";
    static final String hyphenate = "    ____________________________________________________________";

    // Erase and rewrite to file method
    public static boolean writeToFile(String filePath, String textToAdd) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
            return true;
        } catch (IOException err) {
            System.out.println("An unexpected error happen while writing to the database file.");
        }
        return false;
    }

    // Append to file method
    public static boolean appendToFile(String filePath, String textToAppend) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath, true); // Append instead of rewriting over
            fw.write(textToAppend);
            fw.close();
            return true;
        } catch (IOException err) {
            System.out.println("An unexpected error happen while writing to the database file.");
        }
        return false;
    }

    // Find and return Task based on index
    public static ArrayList<Tasks> findTheTask(String filePath, int taskIndexToMark) throws FileNotFoundException {
        ArrayList<Tasks> taskList = new ArrayList<Tasks>();
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            StringBuilder taskDataString = new StringBuilder("");
            for (int i = 0; i < taskIndexToMark; i++) {
                sc.nextLine();
            }

            taskDataString.append(sc.nextLine());
            String[] taskDataStringSplit = taskDataString.toString().split(" \\| ");
            sc.close();
            switch (taskDataStringSplit[0]) {
                case "T":
                    taskList.add(new Todos(taskDataStringSplit[2],
                            taskDataStringSplit[1].equals("X") ? true : false));
                    break;

                case "E":
                    taskList.add(new Events(taskDataStringSplit[2],
                            taskDataStringSplit[1].equals("X") ? true : false, taskDataStringSplit[3]));
                    break;

                case "D":
                    taskList.add(new Deadlines(taskDataStringSplit[2],
                            taskDataStringSplit[1].equals("X") ? true : false, taskDataStringSplit[3]));
                    break;
            }
        } catch (FileNotFoundException err) {
            System.out.println("File cannot be found");
        }
        return taskList;
    }

    // Edit task
    public static void markToFile(String filePath, int taskIndexToMark, boolean taskCompletion)
            throws FileNotFoundException {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            StringBuilder toWrite = new StringBuilder("");
            ArrayList<Tasks> taskDeletedList = new ArrayList<Tasks>();

            for (int i = 0; i < taskIndexToMark; i++) {
                toWrite.append(sc.nextLine() + "\n");
            }

            String taskToMark = sc.nextLine();
            String[] taskDataStringSplit = taskToMark.split(" \\| ");
            switch (taskDataStringSplit[0]) {
                case "T":
                    taskDeletedList.add(new Todos(taskDataStringSplit[2], taskCompletion));
                    break;

                case "E":
                    taskDeletedList.add(new Events(taskDataStringSplit[2], taskCompletion, taskDataStringSplit[3]));
                    break;

                case "D":
                    taskDeletedList.add(new Deadlines(taskDataStringSplit[2], taskCompletion, taskDataStringSplit[3]));
                    break;
            }

            if (taskDeletedList.size() > 0) {
                toWrite.append(taskDeletedList.get(0).toDatabaseString());
            }

            while (sc.hasNext()) {
                toWrite.append(sc.nextLine() + "\n");
            }

            if (writeToFile(filePath, toWrite.toString())) {
                System.out.println(hyphenate);
                System.out.println("    Nice! I've " +
                        (taskCompletion == true ? "marked this task as done:" : "unmarked this task:"));
                System.out.println("       " + taskDeletedList.get(0).toString());
                System.out.println(hyphenate);
            }
            sc.close();
        } catch (FileNotFoundException err) {
            System.out.println("File cannot be found");
        } catch (IOException err) {
            System.out.println("IOException found");
        } catch (IndexOutOfBoundsException err) {
            System.out.println("Task index out of bound.");
        }
    }

    // Delete task
    public static void deleteToFile(String filePath, int taskIndexToDelete) throws FileNotFoundException {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            StringBuilder toWrite = new StringBuilder("");
            ArrayList<Tasks> taskDeletedList = new ArrayList<Tasks>();

            for (int i = 0; i < taskIndexToDelete; i++) {
                toWrite.append(sc.nextLine() + "\n");
            }

            String taskDeleted = sc.nextLine();
            String[] taskDeletedSplit = taskDeleted.split(" \\| ");
            switch (taskDeletedSplit[0]) {
                case "T":
                    taskDeletedList.add(new Todos(taskDeletedSplit[2],
                            (taskDeletedSplit[1].equals("X") ? true : false)));
                    break;

                case "E":
                    taskDeletedList.add(new Events(taskDeletedSplit[2],
                            (taskDeletedSplit[1].equals("X") ? true : false), taskDeletedSplit[3]));
                    break;

                case "D":
                    taskDeletedList.add(new Deadlines(taskDeletedSplit[2],
                            (taskDeletedSplit[1].equals("X") ? true : false), taskDeletedSplit[3]));
                    break;
            }

            while (sc.hasNext()) {
                toWrite.append(sc.nextLine() + "\n");
            }

            if (writeToFile(filePath, toWrite.toString())) {
                // Printing client-side UI
                System.out.println(hyphenate);
                System.out.println("    I have successfully removed the task from the system:");
                System.out.println(
                        String.format("\n    You have %s tasks in your list. :)", fileContentCounter(databasePath)));
                fileContentCounter(databasePath);
                System.out.println(hyphenate);
            }

            sc.close();
        } catch (FileNotFoundException err) {
            System.out.println("File cannot be found");
        } catch (IOException err) {
            System.out.println("IOException found");
        }
    }

    // Print file content method
    public static int fileContentCounter(String filePath) throws FileNotFoundException {
        ArrayList<Tasks> taskCounterList = new ArrayList<Tasks>();
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String taskData = sc.nextLine();
                String[] taskDataSplit = taskData.split(" \\| ");
                switch (taskDataSplit[0]) {
                    case "T":
                        taskCounterList.add(new Todos(taskDataSplit[2],
                                taskDataSplit[1].equals("X") ? true : false));
                        break;
                    case "E":
                        taskCounterList.add(new Events(taskDataSplit[2],
                                taskDataSplit[1].equals("X") ? true : false, taskDataSplit[3]));
                        break;
                    case "D":
                        taskCounterList.add(new Deadlines(taskDataSplit[2],
                                taskDataSplit[1].equals("X") ? true : false, taskDataSplit[3]));
                        break;
                }
            }
            sc.close();
        } catch (FileNotFoundException err) {
            System.out.println("Unable to find database");
        }
        return taskCounterList.size();
    }

    // Print file content method
    public static void printFileContent(String filePath) throws FileNotFoundException {
        StringBuilder sb1 = new StringBuilder("");
        sb1.append(hyphenate + "\n" + "    Here are the tasks in your list:" + "\n");
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            int counter = 0;
            while (sc.hasNext()) {
                counter++;
                String taskData = sc.nextLine();
                String[] taskDataSplit = taskData.split(" \\| ");
                switch (taskDataSplit[0]) {
                    case "T":
                        Tasks currentTask = new Todos(taskDataSplit[2], taskDataSplit[1].equals("X") ? true : false);
                        sb1.append("       " + counter + ". " + currentTask.toString() + "\n");
                        break;
                    case "E":
                        currentTask = new Events(taskDataSplit[2], taskDataSplit[1].equals("X") ? true : false,
                                taskDataSplit[3]);
                        sb1.append("       " + counter + ". " + currentTask.toString() + "\n");
                        break;
                    case "D":
                        currentTask = new Deadlines(taskDataSplit[2], taskDataSplit[1].equals("X") ? true : false,
                                taskDataSplit[3]);
                        sb1.append("       " + counter + ". " + currentTask.toString() + "\n");
                        break;
                }
            }
            sb1.append(String.format("\n    You have %s tasks in your list. :)" + "\n" + hyphenate, counter));
            sc.close();
            System.out.println(sb1.toString());
        } catch (FileNotFoundException err) {
            System.out.println("Ensure that you created a database at the correct directory");
        }
    }

    public static void main(String[] args) {
        // Database path
        String hello = "hel;";
        System.out.println("\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String command = sc.nextLine();
        String[] commandSplit = command.split(" ", 2);
        ArrayList<Tasks> taskList = new ArrayList<Tasks>();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                try {
                    printFileContent(databasePath);
                } catch (FileNotFoundException err) {
                    System.out.println("File is not found");
                }
            } else if (commandSplit[0].equals("mark")) {
                int markIndex = Integer.parseInt(command.split(" ")[1]) - 1; // Zero-indexed
                try {
                    markToFile(databasePath, markIndex, true);
                } catch (FileNotFoundException err) {
                    System.out.println("Database can't be found.");
                }
            } else if (commandSplit[0].equals("unmark")) {
                int markIndex = Integer.parseInt(command.split(" ")[1]) - 1; // Zero-indexed
                try {
                    markToFile(databasePath, markIndex, false);
                } catch (FileNotFoundException err) {
                    System.out.println("Database can't be found.");
                }

            } else if (commandSplit[0].equals("delete")) {
                try {
                    deleteToFile(databasePath, Integer.parseInt(commandSplit[1]) - 1);
                } catch (IndexOutOfBoundsException err) {
                    System.out.println(hyphenate);
                    System.out.println("");
                    System.out.println(
                            "       YIKES!!! We failed to delete the task, make sure that the task ID entered exists.");
                    System.out.println(hyphenate);
                } catch (FileNotFoundException err) {
                    System.out.println(hyphenate);
                    System.out.println("");
                    System.out.println("       YIKES!!! We failed to find the database file.");
                    System.out.println(hyphenate);
                }
            } else {
                StringBuilder sb1 = new StringBuilder("");
                switch (commandSplit[0]) {
                    case "todo":
                        try {
                            Todos newTask = new Todos(commandSplit[1]);
                            if (appendToFile(databasePath, newTask.toDatabaseString())) {
                                sb1.append(hyphenate + "\n" +
                                        "    Wow, sounds fun! I have successfully added this task:" + "\n" +
                                        "       " + newTask.toString() + "\n" +
                                        "    Now you have " + (fileContentCounter(databasePath)) + " tasks in the list!"
                                        + "\n" +
                                        hyphenate);
                                System.out.println(sb1.toString());
                            }
                        } catch (IndexOutOfBoundsException err) {
                            System.out.println(hyphenate);
                            System.out.println("");
                            System.out.println("       YIKES!!! The description of a todo cannot be empty.");
                            System.out.println(hyphenate);
                        } catch (IOException e) {
                            System.out.println("File does not exist.");
                        }
                        break;

                    case "deadline":
                        try {
                            Deadlines newTask = new Deadlines(commandSplit[1].split(" /by ", 2)[0],
                                    commandSplit[1].split(" /by ", 2)[1]);
                            if (appendToFile(databasePath, newTask.toDatabaseString())) {
                                sb1.append(hyphenate + "\n" +
                                        "    Wow, sounds fun! I have successfully added this task:" + "\n" +
                                        "       " + newTask.toString() + "\n" +
                                        "    Now you have " + (fileContentCounter(databasePath)) + " tasks in the list!"
                                        + "\n" +
                                        hyphenate);
                                System.out.println(sb1.toString());
                            }
                        } catch (IndexOutOfBoundsException err) {
                            System.out.println(hyphenate);
                            System.out.println("");
                            System.out.println(
                                    "       YIKES!!! We faced an issue creating an deadline, make sure to have both a \"/by\" and a description.");
                            System.out.println(hyphenate);
                        } catch (IOException e) {
                            System.out.println("File does not exist.");
                        }
                        break;

                    case "event":
                        try {
                            Events newTask = new Events(commandSplit[1].split(" /at ", 2)[0],
                                    commandSplit[1].split(" /at ", 2)[1]);
                            if (appendToFile(databasePath, newTask.toDatabaseString())) {
                                sb1.append(hyphenate + "\n" +
                                        "    Wow, sounds fun! I have successfully added this task:" + "\n" +
                                        "       " + newTask.toString() + "\n" +
                                        "    Now you have " + (fileContentCounter(databasePath)) + " tasks in the list!"
                                        + "\n" +
                                        hyphenate);
                                System.out.println(sb1.toString());
                            }
                        } catch (IndexOutOfBoundsException err) {
                            System.out.println(hyphenate);
                            System.out.println("");
                            System.out.println(
                                    "       YIKES!!! We faced an issue creating an Event, make sure to have both an \"/at\" and a description.");
                            System.out.println(hyphenate);
                        } catch (IOException e) {
                            System.out.println("File does not exist.");
                        }
                        break;

                    default:
                        System.out.println(hyphenate);
                        System.out.println("\n       " + "YIKES!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(hyphenate);
                        break;
                }
            }
            System.out.println("");
            command = sc.nextLine();
            commandSplit = command.split(" ", 2);
        }
        System.out.println(hyphenate + "\n    Bye. Let's play some video games next time!\n" + hyphenate);
        sc.close();

    }
}

// Level 1
/*
 * public static void main(String[] args) {
 * // String logo = " ____        _        \n"
 * // + "|  _ \\ _   _| | _____ \n"
 * // + "| | | | | | | |/ / _ \\\n"
 * // + "| |_| | |_| |   <  __/\n"
 * // + "|____/ \\__,_|_|\\_\\___|\n";
 * // System.out.println("Hello from\n" + logo);
 * 
 * String hyphenate =
 * "    ____________________________________________________________";
 * System.out.println(hyphenate +
 * "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n"
 * + hyphenate);
 * 
 * System.out.println("");
 * Scanner sc = new Scanner(System.in);
 * String command = sc.nextLine();
 * while (!command.equals("bye")){
 * System.out.println(hyphenate + "\n    " + command + "\n" + hyphenate);
 * System.out.println("");
 * command = sc.nextLine();
 * }
 * System.out.println(hyphenate +
 * "\n    Bye. Let's play some video games next time!\n" + hyphenate);
 * sc.close();
 * }
 */

// Level 2
/*
 * public static void main(String[] args) {
 * String hyphenate =
 * "    ____________________________________________________________";
 * System.out.println(hyphenate +
 * "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n"
 * + hyphenate);
 * 
 * Scanner sc = new Scanner(System.in);
 * System.out.println("");
 * String command = sc.nextLine();
 * String[] commandStorage = new String[100];
 * int ID = 0;
 * 
 * while (!command.equals("bye")){
 * if (command.equals("list")){
 * System.out.println(hyphenate);
 * for (int i=0; i<ID; i++){
 * System.out.println("    " + (i+1) + ". " + commandStorage[i]);
 * }
 * System.out.println(hyphenate);
 * }else{
 * commandStorage[ID] = command;
 * System.out.println(hyphenate + "\n    added: " + command + "\n" + hyphenate);
 * ID++;
 * }
 * System.out.println("");
 * command = sc.nextLine();
 * }
 * System.out.println(hyphenate +
 * "\n    Bye. Let's play some video games next time!\n" + hyphenate);
 * sc.close();
 * }
 */

// Level 3
/*
 * public static void main(String[] args) {
 * String hyphenate =
 * "    ____________________________________________________________";
 * System.out.println(hyphenate +
 * "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n"
 * + hyphenate);
 * 
 * Scanner sc = new Scanner(System.in);
 * System.out.println("");
 * String command = sc.nextLine();
 * String[] commandStorage = new String[100];
 * boolean[] commandMark = new boolean[100];
 * int ID = 0;
 * 
 * while (!command.equals("bye")){
 * if (command.equals("list")){
 * System.out.println(hyphenate);
 * for (int i=0; i<ID; i++){
 * System.out.println("    " + (i+1) + "." + "[" +
 * (commandMark[i]==true?"X":" ") + "]" + " " + commandStorage[i]);
 * }
 * System.out.println(hyphenate);
 * } else if (command.split(" ")[0].equals("mark")){
 * int markIndex = Integer.parseInt(command.split(" ")[1]) - 1; //Zero-indexed
 * commandMark[markIndex] = true;
 * System.out.println(hyphenate);
 * System.out.println("    Nice! I've marked this task as done:");
 * System.out.println("       [" + (commandMark[markIndex]==true?"X":" ") + "] "
 * + commandStorage[markIndex]);
 * System.out.println(hyphenate);
 * } else if (command.split(" ")[0].equals("unmark")){
 * int markIndex = Integer.parseInt(command.split(" ")[1]) - 1; //Zero-indexed
 * commandMark[markIndex] = false;
 * System.out.println(hyphenate);
 * System.out.println("    Okay, I've marked this task as not done yet:");
 * System.out.println("       [" + (commandMark[markIndex]==true?"X":" ") + "] "
 * + commandStorage[markIndex]);
 * System.out.println(hyphenate);
 * } else {
 * commandStorage[ID] = command;
 * System.out.println(hyphenate + "\n    added: " + command + "\n" + hyphenate);
 * ID++;
 * }
 * System.out.println("");
 * command = sc.nextLine();
 * }
 * System.out.println(hyphenate +
 * "\n    Bye. Let's play some video games next time!\n" + hyphenate);
 * sc.close();
 * }
 */
