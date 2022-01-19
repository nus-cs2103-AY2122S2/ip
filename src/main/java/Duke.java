import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String indent = "    ";
    static ArrayList<Task> taskList;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        taskList = new ArrayList<>(); // arraylist to hold inputs

        dukeOutput(" Hello! I'm YourBoss.\n" +
                indent +
                " What can you do for me?");

        String userInput = scanner.nextLine();

        while(true) {
            String[] splitUserInput = userInput.split(" ",2);
            String firstWord = splitUserInput[0];

            if (userInput.equals("bye")) { // exit loop
                dukeOutput(" Bye. Hope I never see you again!");
                break;
            } else if (userInput.equals("list")) { // list out everything in list
                StringBuilder tempOut = new StringBuilder(taskList.get(0).toString());
                for (int i = 1; i<taskList.size();i++) {
                    tempOut.append(" " + indent).append(taskList.get(i).toString());
                }
                hLineBreak();
                printlnWithIndent(" Here are the tasks in your list:");
                System.out.print(indent + " " + tempOut); // newline in tempOut
                hLineBreak();

            } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                int taskIndex = -1;
                try {
                    taskIndex = Integer.parseInt(splitUserInput[1]) - 1;
                } catch (NumberFormatException ex) {
                    System.out.println("Argument after mark/unmark not an integer!");
                }

                Task currTask = taskList.get(taskIndex);

                if (firstWord.equals("mark")) {
                    dukeOutput(currTask.markAsDone(true));
                } else { // unmark case
                    dukeOutput(currTask.markAsDone(false));
                }

            } else { // add task to list, todo, event or deadline

                String remainingUserInput = splitUserInput[1];

                if (firstWord.equals("todo")) {
                    ToDo newToDo = new ToDo(userInput);
                    taskList.add(newToDo);
                    dukeAddTaskOutput(newToDo);
                } else if (firstWord.equals("deadline")) {
                    String taskName = remainingUserInput.substring(0,remainingUserInput.indexOf("/by"));
                    String timeBy = remainingUserInput.substring(remainingUserInput.indexOf("/by") + 4);
                    Deadline newDeadline = new Deadline(taskName,timeBy);
                    taskList.add(newDeadline);
                    dukeAddTaskOutput(newDeadline);
                } else if (firstWord.equals("event")) {
                    String taskName = remainingUserInput.substring(0,remainingUserInput.indexOf("/at"));
                    String timeRange = remainingUserInput.substring(remainingUserInput.indexOf("/at") + 4);
                    Event newEvent = new Event(taskName,timeRange);
                    taskList.add(newEvent);
                    dukeAddTaskOutput(newEvent);
                } else { // if none of the above tasks

                }
            }
            userInput = scanner.nextLine();
        }

        scanner.close();
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
        printlnWithIndent("Now you have "+ taskList.size() +" tasks in the list.");
        hLineBreak();
    }
}
