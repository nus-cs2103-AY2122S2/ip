import java.time.format.DateTimeParseException;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    static String indent = "    ";
    static ArrayList<Task> taskList;

    static String DB = "./data/task_list.txt";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        taskList = new ArrayList<>(); // arraylist to hold inputs

        loadTaskListFromDb();

        dukeOutput(" Hello! I'm YourBoss.\n" +
                indent +
                " What can you do for me?");

        while (true) {
            String userInput = scanner.nextLine();
            String[] splitUserInput = userInput.split(" ",2);
            String firstWord = splitUserInput[0];
            int taskIndex = -1;

            try {
                switch (firstWord) {
                    case "bye":
                        dukeOutput(" Bye. Hope I never see you again!");
                        scanner.close();
                        updateDB();
                        System.exit(0);
                    case "list":
                        StringBuilder tempOut = new StringBuilder("");
                        for (int i = 0; i < taskList.size(); i++) {
                            tempOut.append(" " + indent).append((i + 1) + ".").append(taskList.get(i).toString());
                        }
                        hLineBreak();
                        printlnWithIndent(" Here are the tasks in your list:");
                        System.out.print(tempOut); // newline in tempOut
                        hLineBreak();
                        break;
                    case "mark":
                        // fallthrough
                    case "unmark":
                        taskIndex = Integer.parseInt(splitUserInput[1]) - 1;

                        Task currTask = taskList.get(taskIndex);

                        if (firstWord.equals("mark")) {
                            dukeOutput(currTask.markAsDone(true));
                        } else { // unmark case
                            dukeOutput(currTask.markAsDone(false));
                        }
                        break;
                    case "delete":
                        taskIndex = Integer.parseInt(splitUserInput[1]) - 1;

                        Task removedTask = taskList.remove(taskIndex);

                        dukeDeleteTaskOutput(removedTask);
                        break;
                    case "todo":
                        // fallthrough
                    case "deadline":
                        // fallthrough
                    case "event":
                        String remainingUserInput = "";
                        remainingUserInput = splitUserInput[1];

                        if (firstWord.equals("todo")) {
                            ToDo newToDo = new ToDo(remainingUserInput);
                            taskList.add(newToDo);
                            dukeAddTaskOutput(newToDo);
                        } else if (firstWord.equals("deadline")) {
                            String taskName = remainingUserInput.substring(0, remainingUserInput.indexOf("/by") - 1);
                            String timeBy = remainingUserInput.substring(remainingUserInput.indexOf("/by") + 4);
                            Deadline newDeadline = new Deadline(taskName, timeBy, false);
                            taskList.add(newDeadline);
                            dukeAddTaskOutput(newDeadline);
                        } else if (firstWord.equals("event")) {
                            String taskName = remainingUserInput.substring(0, remainingUserInput.indexOf("/at") - 1);
                            String timeRange = remainingUserInput.substring(remainingUserInput.indexOf("/at") + 4);
                            Event newEvent = new Event(taskName, timeRange, false);
                            taskList.add(newEvent);
                            dukeAddTaskOutput(newEvent);
                        }
                        break;
                    default:
                        throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (StringIndexOutOfBoundsException ex) {
                dukeOutput(" OOPS!!! Argument after missing /at or /by!!!");
                continue;
            } catch (ArrayIndexOutOfBoundsException e) {
                dukeOutput(" OOPS!!! I think you're missing some arguments!");
                continue;
            } catch (DukeException e) {
                dukeOutput(e.getMessage());
                continue;
            } catch (DateTimeParseException e) {
                dukeOutput("Date must be of format yyyy-mm-dd!");
            }
        }
    }

    static void hLineBreak() {
        System.out.println(indent+"____________________________________________________________");
    }

    static void printlnWithIndent(String input) {
        System.out.print(indent);
        System.out.println(input);
    }

    static void dukeOutput(String output) {
        hLineBreak();
        printlnWithIndent(output);
        hLineBreak();
    }

    static void dukeAddTaskOutput(Task task) {
        hLineBreak();
        printlnWithIndent(" Got it. I've added this task:");
        System.out.print(indent + "   " + task);
        printlnWithIndent(" Now you have "+ taskList.size() +" tasks in the list.");
        hLineBreak();
    }

    static void dukeDeleteTaskOutput(Task task) {
        hLineBreak();
        printlnWithIndent(" Noted. I've removed this task:");
        System.out.print(indent + "   " + task);
        printlnWithIndent(" Now you have "+ taskList.size() +" tasks in the list.");
        hLineBreak();
    }

    static void updateDB() {
        createDirAndFileIfNonExistent(DB);
        clearDB();
        taskList.forEach((task)
                -> appendTaskToDB(task));
    }

    static void appendTaskToDB(Task task) {
        try {
            FileWriter fw = new FileWriter(DB, true);
            String stringToAppend = task.taskType + "|" + task.isDone + "|" + task.taskName;
            switch (task.taskType) {
                case "E":
                    // typecasting because only an event would have taskType "E"
                    Event event = (Event) task;
                    stringToAppend = stringToAppend + "|" + event.eventDate + "|" + event.timeRange;
                    break;
                case "D":
                    // typecasting because only a deadline would havetaskType "D"
                    Deadline deadlineTask = (Deadline) task;
                    stringToAppend = stringToAppend + "|" + deadlineTask.deadline;
                    break;
            }
            fw.write(stringToAppend + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            dukeOutput("Error appending task to text file!");
        }
    }

    static void clearDB() {
        try {
            FileWriter fw = new FileWriter(DB);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            dukeOutput("Error clearing DB");
        }
    }

    static void createDirAndFileIfNonExistent(String fileName) {
        try {
            File fileObj = new File(fileName);
            File parentFile = fileObj.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdir();
            }
            if (!fileObj.exists()) {
                fileObj.createNewFile();
            }
        } catch (IOException e) {
            dukeOutput("An Error has occured with file creation!");
        }
    }

    static void loadTaskListFromDb() {
        // get taskList from hard disk aka DB
        try {
            File dbObj = new File(DB);
            if (!dbObj.exists()) {
                createDirAndFileIfNonExistent(DB);
                return;
            }
            Scanner s = new Scanner(dbObj);
            while (s.hasNext()) {
                String currLine = s.nextLine();
                String[] currLineSplit = currLine.split("\\|");
                String taskType = currLineSplit[0];
                boolean isDone = currLineSplit[1].equals("true");
                String taskName = currLineSplit[2];

                if (taskType.equals("E")) {
                    taskList.add(new Event(taskName,
                            currLineSplit[3], currLineSplit[4], isDone));
                } else if (taskType.equals("D")) {
                    taskList.add(new Deadline(taskName, currLineSplit[3], isDone));
                } else {
                    taskList.add(new ToDo(taskName, isDone));
                }
            }
        } catch (FileNotFoundException e) {
            dukeOutput("Error database not found!");
        }
    }
}
