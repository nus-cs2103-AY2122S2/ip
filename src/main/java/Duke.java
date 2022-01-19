import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\n" +
                "    ___   ____________\n" +
                "   /   | / ____/ ____/\n" +
                "  / /| |/ /   / __/   \n" +
                " / ___ / /___/ /___   \n" +
                "/_/  |_\\____/_____/   \n" +
                "                      \n";
        Scanner reader = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        printAce(logo);
        printAce("Hey, I'm Ace. What can I help you with?");

        String userInput = reader.nextLine();

        while(!userInput.equals("bye")) {

            if (userInput.equals("list")) {
                StringBuilder strBuilder = new StringBuilder("Here are the tasks in your list:\n");
                for (int i = 0; i < taskList.size(); i++) {
                    String task = (i + 1 + ". " + taskList.get(i) + "\n");
                    strBuilder.append(task);
                }
                printAce(strBuilder.toString());

            } else if (userInput.startsWith("mark ")) {

                if (userInput.length() < 6) {
                    printAce("Please provide the number of the task you'd like to mark. Type 'list' to view your tasks.");
                } else {
                    String taskNumber = userInput.substring(5);
                    try {
                        int taskNum = Integer.parseInt(taskNumber);

                        if (taskNum <= taskList.size() & 0 < taskNum) {
                            Task selected = taskList.get(taskNum - 1);
                            selected.markAsComplete();
                            printAce("I've marked the following task as complete:\n" + selected);
                        } else {
                            printAce("You don't have a task number " + taskNum + " on your list. Type 'list' to view your tasks.");
                        }

                    } catch (NumberFormatException e) {
                        printAce("Please provide the number of the task you'd like to mark. Type 'list' to view your tasks.");
                    }
                }

            } else if(userInput.startsWith("unmark ")) {
                if (userInput.length() < 8) {
                    printAce("Please provide the number of the task you'd like to unmark. Type 'list' to view your tasks.");
                } else {
                    String taskNumber = userInput.substring(7);
                    try {
                        int taskNum = Integer.parseInt(taskNumber);

                        if (taskNum <= taskList.size() & 0 < taskNum) {
                            Task selected = taskList.get(taskNum - 1);
                            selected.markAsIncomplete();
                            printAce("I've unmarked the following task as complete:\n" + selected);
                        } else {
                            printAce("You don't have a task number " + taskNum + " on your list. Type 'list' to view your tasks.");
                        }

                    } catch (NumberFormatException e) {
                        printAce("Please provide the number of the task you'd like to unmark. Type 'list' to view your tasks.");
                    }
                }
            } else {
                Task newTask = new Task(userInput);
                taskList.add(newTask);
                printAce("I've added the following task:\n" + newTask);
            }
            userInput = reader.nextLine();
        }
        printAce("See you later!");
        reader.close();
    }

    private static void printAce(String str) {
        System.out.println("________\n" + str.indent(4));
    }
}
