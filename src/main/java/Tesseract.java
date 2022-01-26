import java.util.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Tesseract {
    public static void main(String[] args) {
        String INDENT1 = "    ";
        String INDENT2 = "        ";
        String BREAKER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        List<Task> taskList = new ArrayList<Task>();

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

        String cmdLine = sc.nextLine();

        while (!cmdLine.equals("bye")) {
            String msg = "";
            String[] cmdArr = cmdLine.split(" ");
            String cmd = cmdArr[0];

            try {
                checkCommand(cmdLine, taskList);
                if (cmd.equals("event") || cmd.equals("deadline") || cmd.equals("filter")) { // currently can only date
                    checkValidTime(cmdArr[cmdArr.length - 1]);
                }
            } catch (TesseractException errMsg) {
                System.out.println(errMsg);
                System.out.println();
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
            } else if (cmd.equals("filter")) { // "filter /by date YYYY-MM-DDDD"
                String date = cmdArr[cmdArr.length - 1];
                msg = "Here are the tasks occuring on " + formatTime(date) + ":\n";
                for (Task task : taskList) {
                    if (task.isOn(date)) {
                        msg += INDENT2 + task.toString() + "\n";
                    }
                }
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
                            time += cmdArr[j];
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
                            time += cmdArr[j];
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
            case "filter":
                if (cmdLen < 3 || cmdLine.indexOf("/by") < 0) {
                    throw new TesseractException("What do you want to filter by again?");
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

    public static void checkValidTime(String time) throws TesseractException {
        if (time.length() != 10) { // check if the string is of length 10
            throw new TesseractException("Please enter date in the YYYY-MM-DD format~");
        } else {
            try {

                String[] ints = time.split("-", 3);
                for (String integer : ints) {
                    Integer.parseInt(integer);
                }
            } catch (NumberFormatException e) {
                throw new TesseractException("Please enter date in the YYYY-MM-DD format~");
            }
        }
    }

    public static String formatTime(String time) { // format "yyyy-mm-dd" to "MMM d yyyy"
        LocalDate date = LocalDate.parse(time);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
}