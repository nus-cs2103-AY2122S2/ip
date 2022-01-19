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
            String[] splitUserInput = userInput.split(" ");
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
                System.out.print(indent + " " + tempOut);
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
                if (firstWord.equals("todo")) {
                    ToDo newToDo = new ToDo(userInput);
                    taskList.add(newToDo);
                    dukeAddTaskOutput(newToDo);
                } else if (firstWord.equals("deadline")) {
                    String taskName = userInput.substring(0,userInput.indexOf("/by"));
                    String timeBy = userInput.substring(userInput.indexOf("/by") + 4);
                    Deadline newDeadline = new Deadline(taskName,timeBy);
                    taskList.add(newDeadline);
                    dukeAddTaskOutput(newDeadline);
                } else
                {
                    taskList.add(new Task(userInput));
                    dukeOutput(" added: " + userInput);
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
