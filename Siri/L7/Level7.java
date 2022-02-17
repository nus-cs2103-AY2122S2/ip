import java.nio.file.Path;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;

public class Level7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arrList = new ArrayList<Task>();
        java.nio.file.Path path = java.nio.file.Paths.get("data", "Siri.txt");
        java.nio.file.Path pathToSiriTxt = path.toAbsolutePath();
        String strPathToSiriTxt = pathToSiriTxt.toString();
        System.out.println(strPathToSiriTxt);

        String line = "    ____________________________________________________________________________________\n";
        //prints a line before and after greeting message upon runnind of
        System.out.println(line + "     Hi, I'm Siri, your personal to-do list program. \n"
                + "     You can add 3 types of tasks to the list: todo, deadline and event tasks.\n"
                + "     To add to the list, specify the type of task before it's description.\n"
                + "     Only deadlines and events can accept dates and timings\n\n"
                + "     Examples:\n"
                + "     todo run a mile\n"
                + "     deadline return library book /by Sunday 2359\n"
                + "     event Jack's wedding /at Holiday Inn 1800\n\n"
                + "     What do you wish to take note of today? \n" + line);
        readFromFile(arrList, strPathToSiriTxt);

        while (true) { //will keep querying user for input until user inputs "bye"
            String word = sc.next();
            sc.skip("\\s"); //skips 1 whitespace char to avoid scanning it as part of user input
            if (word.equals("bye")) { //prints goodbye text and exit while loop,stop scanner from scanning
                System.out.println(line + "     Goodbye.\n" + line);
                break; //end while loop
            } else if (word.equals("list")) { //prints all elements in a list with index in front
                System.out.println(line + "     Tasks to do:");
                int s = arrList.size();
                for (int i = 0; i < s; i++) {
                    String currTask = arrList.get(i).toString();
                    System.out.println("     " + (i + 1) + "." + currTask);
                }
                System.out.println(line);
            } else if (word.equals("mark")) { //marks the specified task with an "X"
                int num = sc.nextInt();
                int index = num - 1;
                Task t = arrList.get(index);
                t.markAsDone();
                arrList.set(index, t);
                System.out.println(line + "     This task has been marked:\n       "
                        + t.toString() + "\n"
                        + line);
            } else if (word.equals("unmark")) { //unmarks the specified task
                int num = sc.nextInt();
                int index = num - 1;
                Task t = arrList.get(index);
                t.markAsNotDone();
                arrList.set(index, t);
                System.out.println(line + "     This task has been unmarked:\n       "
                        + t.toString() + "\n"
                        + line);
            } else if (word.equals("todo")) { //creates todo task with "[T]" prefix and adds to the list
                String desc = sc.nextLine();
                try {
                    checkRestOfInput(desc);
                    Todo toDoTask = new Todo(desc, "T");
                    arrList.add(toDoTask);
                    writeToFile(arrList, strPathToSiriTxt);
                    System.out.println(line + "     This task has been added to your list:\n       "
                            + toDoTask.toString() + "\n" + "\n"
                            + "     Number of task(s) in your list: " + arrList.size() + "\n"
                            + line);
                } catch (Exception e) {
                    System.out.println(line + e.toString() + "\n" + line);
                }
            } else if (word.equals("deadline")) {
                //creates deadline task with "[D]" prefix and deadline postfix and adds to list
                while (sc.hasNextLine()) {
                    String[] userInput = sc.nextLine().split("\\s/by\\s");
                    // The userInput array now contains [taskDescription, deadline]
                    Deadline deadLineTask = new Deadline(userInput[0], "D", userInput[1]);
                    arrList.add(deadLineTask);
                    writeToFile(arrList, strPathToSiriTxt);
                    System.out.println(line + "     This task has been added to your list:\n       "
                            + deadLineTask.toString() + "\n" + "\n"
                            + "     Number of task(s) in your list: " + arrList.size() + "\n"
                            + line);
                    break;
                }
            } else if (word.equals("event")) {
                //creates event task with "[E]" prefix and event date postfix and adds to list
                while (sc.hasNextLine()) {
                    String[] userInput = sc.nextLine().split("\\s/at\\s");
                    // The userInput array now contains [taskDescription, event date]
                    Event eventTask = new Event(userInput[0], "E", userInput[1]);
                    arrList.add(eventTask);
                    writeToFile(arrList, strPathToSiriTxt);
                    System.out.println(line + "     This task has been added to your list:\n       "
                            + eventTask.toString() + "\n" + "\n"
                            + "     Number of task(s) in your list: " + arrList.size() + "\n"
                            + line);
                    break;
                }
            } else if (word.equals("delete")) {
                    int listIndex = sc.nextInt() - 1;
                    Task tempT = arrList.get(listIndex);
                    arrList.remove(listIndex);
                    writeToFile(arrList, strPathToSiriTxt);
                    System.out.println(line + "     Sure. I've removed this task from the list:\n       "
                            + tempT.toString() + "\n" + "\n"
                            + "     Number of task(s) in your list: " + arrList.size() + "\n"
                            + line);
            } else {
                System.out.println(line + "     Please enter a valid task type with description.       \n" + line);
            }
        }
    }

    static void readFromFile(ArrayList<Task> arrayList, String pathToFile) {
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(pathToFile));
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println("1");
                String taskDetails = s;
                System.out.println("taskDetails: " + taskDetails);
                String[] arrOfTaskDetails = taskDetails.split("\\s\\|\\s");
                for (String str : arrOfTaskDetails) {
                    System.out.println("task: " + str);
                }
                String taskInitials = arrOfTaskDetails[0];
                System.out.println("taskInitials: " + taskInitials);
                if (taskInitials.equals("T")) {
                    System.out.println("2");
                    String taskDesc = arrOfTaskDetails[1];
                    System.out.println("taskDesc: " + taskDesc);
                    Todo toDoTask = new Todo(taskDesc, taskInitials);
                    arrayList.add(toDoTask);
                } else if (taskInitials.equals("D")) {
                    System.out.println("3");
                    String taskDesc = arrOfTaskDetails[1];
                    System.out.println("taskDesc: " + taskDesc);
                    String taskDeadline = arrOfTaskDetails[2];
                    System.out.println("taskDeadline: " + taskDeadline);
                    Deadline deadlineTask = new Deadline(taskDesc, taskInitials, taskDeadline);
                    arrayList.add(deadlineTask);
                } else if (taskInitials.equals("E")) {
                    System.out.println("4");
                    String taskDesc = arrOfTaskDetails[1];
                    System.out.println("taskDesc: " + taskDesc);
                    String taskDate = arrOfTaskDetails[2];
                    System.out.println("taskDate: " + taskDate);
                    Event eventTask = new Event(taskDesc, taskInitials, taskDate);
                    arrayList.add(eventTask);
                }
            }
            br.close();
        } catch (Exception e) {
            return;
        }
    }

    static void writeToFile(ArrayList<Task> arrayList, String pathToFile) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(pathToFile, false));
            for (Task t : arrayList) {
                String taskInitials = t.initialLetter;
                if (taskInitials.equals("T")) {
                    String taskDescription = t.description;
                    String taskToWrite = taskInitials + " | " + taskDescription + "\n";
                    bw.write(taskToWrite);
                } else if (taskInitials.equals("D")) {
                    String taskDescription = t.description;
                    String taskDeadline = ((Deadline)t).deadline;
                    String taskToWrite = taskInitials + " | " + taskDescription + " | " + taskDeadline + "\n";
                    bw.write(taskToWrite);
                } else if (taskInitials.equals("E")) {
                    String taskDescription = t.description;
                    String taskDate = ((Event)t).eventDate;
                    String taskToWrite = taskInitials + " | " + taskDescription + " | " + taskDate + "\n";
                    bw.write(taskToWrite);
                }
            }
            bw.close();
        } catch (Exception e) {
            return;
        }
    }

    static void checkRestOfInput(String description) throws EmptyDescException {

        if (description.isBlank()) {
            throw new EmptyDescException("     The description of a todo task cannot be empty.");
        } else {
            return;
        }
    }

}
