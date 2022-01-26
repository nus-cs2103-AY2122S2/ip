import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;

public class Tesseract {
    public static void main(String[] args) {
        String INDENT1 = "    ";
        String INDENT2 = "        ";
        String BREAKER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        String SCHEDULE_PATH = "src/main/Data/Schedule.txt";
        boolean hasUpdatedMemory = false;

        Scanner sc = new Scanner(System.in);

        String greetingMsg = BREAKER + "\n" + "Hi fellow! I am Tesseract\n" +
                "I can bring you to wherever you want in the universe\n" +
                "How can I help you?\n"
                + BREAKER;

        String farewellMsg = BREAKER + "\n" +
                "Ok I'm gonna travel to another planet now\n" +
                "Hope to see you again when I'm back :P\n"
                + BREAKER;

        // greet the user
        System.out.println(greetingMsg);

        List<Task> taskList = memToSchedule(SCHEDULE_PATH);

        String cmdLine = sc.nextLine();

        while (!cmdLine.equals("bye")) {
            String msg = "";
            String[] cmdArr = cmdLine.split(" ");
            String cmd = cmdArr[0];

            try {
                checkCommand(cmdLine, taskList);
                hasUpdatedMemory = true;
            } catch (TesseractException errMsg) {
                System.out.println(errMsg);
                cmdLine = sc.nextLine();
                continue;
            }
            // will if still be executed if error is thrown?

            if (cmd.equals("list")) {
                msg = "Look what I have noted down for you~ \n";
                for (int i = 0; i < taskList.size(); i++) {
                    msg += INDENT2 + (i+1) + "." + taskList.get(i).toString() + "\n";
                }
            } else if (cmd.equals("delete")) {
                int index = Integer.parseInt(cmdArr[1]) - 1;
                Task removed = taskList.remove(index);
                msg = "Okies the following task has been removed:\n" + INDENT2 + removed.toString() + "\n"
                        + INDENT1 + "Now you have " + taskList.size() + " tasks in the list~\n";
            } else if (cmd.equals("mark")) {
                int index = Integer.parseInt(cmdArr[1]) - 1; // the 2nd element in the array is the index
                taskList.get(index).markAsDone();
                msg = "Wow you have finished a task! Excellent! \n" + INDENT2 + taskList.get(index).toString() + "\n";
            } else if (cmd.equals("unmark")) {
                int index = Integer.parseInt(cmdArr[1]) - 1;
                taskList.get(index).markAsUndone();
                msg = "Seems like you have successfully undone your done task \n"
                        + INDENT2 + taskList.get(index).toString() + "\n";
            } else {
                Task newTask;
                if (cmd.equals("todo")) { // _Todo
                    newTask = new Todo(cmdLine.substring(5));
                } else if (cmd.equals("event")) { // Event
                    String description = "";
                    String time = "";
                    int cmdLen = 0;
                    for (int i = 1; i < cmdArr.length; i++) {
                        if (cmdArr[i].equals("/at")) {
                            cmdLen = i;
                            break;
                        }
                    }
                    for (int j = 1; j < cmdArr.length; j++) {
                        if (j < cmdLen) {
                            description += cmdArr[j] + " ";
                        } else if (j > cmdLen) {
                            time += cmdArr[j] + " ";
                        }
                    }
                    newTask = new Event(description, time);
                } else { // if (cmd.equals("deadline")) { // Deadline
                    String description = "";
                    String time = "";
                    int cmdLen = 0;
                    for (int i = 1; i < cmdArr.length; i++) {
                        if (cmdArr[i].equals("/by")) {
                            cmdLen = i;
                            break;
                        }
                    }
                    for (int j = 1; j < cmdArr.length; j++) {
                        if (j < cmdLen) {
                            description += cmdArr[j] + " ";
                        } else if (j > cmdLen) {
                            time += cmdArr[j] + " ";
                        }
                    }
                    newTask = new Deadline(description, time);
                }

                taskList.add(newTask);
                msg = "This has been added to your schedule. Wish you can finish it on time\n"
                        + INDENT2 + newTask.toString() + "\n" + INDENT1
                        + "Now you have " + taskList.size() + " tasks waiting to be finished.\n";
            }

            String out = INDENT1 + BREAKER + "\n" + INDENT1 + msg + INDENT1 + BREAKER + "\n";
            System.out.println(out);
            cmdLine = sc.nextLine();
        }

        System.out.println(farewellMsg);
        if (hasUpdatedMemory) {
            try {
                updatedMemory(SCHEDULE_PATH, taskList);
            } catch (TesseractException e) {
                System.out.println(e);
            }
        }
    }

    public static void checkCommand(String cmdLine, List<Task> taskList) throws TesseractException {
        String[] cmdArr = cmdLine.split(" ");
        int cmdLen = cmdArr.length;
        switch (cmdArr[0]) {
            case "list":
            case "bye":
                if (cmdLen > 1) {
                    throw new TesseractException("You have entered an invalid command leh~");

                }
                break;
            case "delete":
            case "mark":
            case "unmark":
                if (cmdLen == 1) {
                    throw new TesseractException("Which task you want to remove again?");
                } else if (cmdLen > 2 || !isInteger(cmdArr[1]) || Integer.parseInt(cmdArr[1]) > taskList.size()) {
                    throw new TesseractException("You need to enter a valid list number mah~");
                }
                break;

            case "event":
                if (cmdLen == 1) {
                    throw new TesseractException("Nah you need to provide me with the details of this event *_*");
                } else if (cmdLine.indexOf("/at") < 0) {
                    throw new TesseractException("When is the timing for your event again?");
                }
                break;
            case "deadline":
                if (cmdLen == 1) {
                    throw new TesseractException("Sorry I can't create deadline without its details )-:");
                } else if (cmdLine.indexOf("/by") < 0) {
                    throw new TesseractException("When do you need to do this by again?");
                }
                break;
            case "todo":
                if (cmdLen == 1) {
                    throw new TesseractException("I cannot create todo if you don't tell me what it's about eh :-(");
                }
                break;
            default:
                throw new TesseractException("Hey bro, not sure if this command is valid eh #_#");
        }
    }

    // check if a command is valid
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<Task> memToSchedule(String filePath) {
        File f = new File(filePath);
        List<Task> taskList = new ArrayList<Task>();

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] task = (sc.nextLine()).split("@", 4); // format: D^1^do something
                Task taskNew;
                if (task.length < 3)
                    break;

                switch (task[0]) {
                    case "E":
                        taskNew = new Event(task[2], task[3]);
                        break;
                    case "D":
                        taskNew = new Deadline(task[2], task[3]);
                        break;
                    default: // todo
                        taskNew = new Todo(task[2]);
                        break;
                }
                if (Integer.parseInt(task[1]) == 1) {
                    taskNew.markAsDone();
                }
                taskList.add(taskNew);
            }
        } catch (IOException e) {
            try {
                f.createNewFile();
            } catch (IOException err) {
                System.out.println("Not joking but I cannot create a memory for you.. \nYou mind changing a laptop?");
            }
        }

        return taskList;
    }

    public static void updatedMemory(String filePath, List<Task> taskList) throws TesseractException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toMemory());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new TesseractException("Sorry but I cannot upload your list of tasks into memory due to" +
                        "some unforeseen errors :(");
        }
    }
}