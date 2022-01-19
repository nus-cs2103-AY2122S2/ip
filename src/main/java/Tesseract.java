import java.util.*;

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

            //try {
            //    checkCommand(cmdArr);
            //} catch () {

            //}
            // will if still be executed if error is thrown?

            if (cmd.equals("list")) {
                msg = "Look what I have noted down for you~ \n";
                for (int i = 0; i < taskList.size(); i++) {
                    msg += INDENT2 + (i+1) + "." + taskList.get(i).toString() + "\n";
                }
            } else if (cmd.equals("mark")) {
                int index = Integer.parseInt(cmdArr[1]); // the 2nd element in the array is the index
                taskList.get(index).markAsDone();
                msg = "Wow you have finished a task! Excellent! \n" + INDENT2 + taskList.get(index).toString() + "\n";
            } else if (cmd.equals("unmark")) {
                int index = Integer.parseInt(cmdArr[1]);
                taskList.get(index).markAsUndone();
                msg = "Seems like you have successfully undone your done task \n" + INDENT2 + taskList.get(index).toString() + "\n";
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
    }
}
