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
        TaskList taskList = new TaskList();

        printAce(logo);
        printAce("Hey, I'm Ace. What can I help you with?");

        String userInput = reader.nextLine();

        while(!userInput.equals("bye")) {

            if (userInput.equals("list")) {
                printAce("Here are the tasks in your list:\n" + taskList.getTasksInfo());

            } else if (userInput.startsWith("mark ")) {
                if (userInput.length() > 5 & taskList.checkValidTask(userInput.substring(5))) {
                    Task selectedTask = taskList.getTaskByNum(Integer.parseInt(userInput.substring(5)));
                    selectedTask.markAsComplete();
                    printAce("I've marked the following task as complete:\n" + selectedTask);
                } else {
                    printAce("I couldn't understand that. Please provide the number of the task you would like to mark.\n" +
                            "Here are the tasks in your list:\n" + taskList.getTasksInfo());
                }

            } else if (userInput.startsWith("unmark ")) {
                if (userInput.length() > 7 & taskList.checkValidTask(userInput.substring(7))) {
                    Task selectedTask = taskList.getTaskByNum(Integer.parseInt(userInput.substring(7)));
                    selectedTask.markAsIncomplete();
                    printAce("I've marked the following task as incomplete:\n" + selectedTask);
                } else {
                    printAce("I couldn't understand that. Please provide the number of the task you would like to mark.\n" +
                            "Here are the tasks in your list:\n" + taskList.getTasksInfo());
                }
            } else {
                Task newTask = new Task(userInput);
                taskList.addTask(newTask);
                printAce("I've added the following task to your list:\n" + newTask);
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
