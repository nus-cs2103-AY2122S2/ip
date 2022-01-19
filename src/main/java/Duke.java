import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {


    private static boolean sizeCheck(List<Task> inputList) {
        return inputList.size() < 100;
    }

    private static void run() {
        Scanner sc = new Scanner(System.in);
        boolean exitFlag = false;
        List<Task> taskList = new ArrayList<>();

        while(!exitFlag) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");

            switch (inputArr[0]) {
                case "exit": botResponse("Pleasure to be of service, see you soon!");
                             exitFlag = true;
                             break;
                case "list": botResponse(taskList);
                             break;
                case "mark": Task markTask = taskList.get(Integer.parseInt(inputArr[1]) - 1);
                             markTask.isMarked = true;
                             botResponse(markTask);
                             break;
                case "unmark": Task unmarkTask = taskList.get(Integer.parseInt(inputArr[1]) - 1);
                               unmarkTask.isMarked = false;
                               botResponse(unmarkTask);
                               break;
                default:
                    if (sizeCheck(taskList)) {
                        Task newTask = new Task(input);
                        taskList.add(newTask);
                        botResponse("added: " + input);
                    } else {
                        botResponse("List is full, sorry! :(");
                    }
            }
        }
        sc.close();
    }

    private static void botResponse(Task task) {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";
        String markedString = "Nice! I've marked this task as done: ";
        String unmarkedString = "OK, I've marked this task as not done yet: ";
        String outString = task.isMarked ? markedString : unmarkedString;

        System.out.println(divString);
        System.out.println(strPadding + outString);
        System.out.println(strPadding + "   " + task.toMarkedString());
        System.out.println(divString);
    }

    private static void botResponse(List<Task> taskList) {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            sb.append(String.format(strPadding + "%02d." + curr.toMarkedString(), i + 1));
            if (i != taskList.size() - 1) { sb.append("\n"); }

        }
        System.out.println(divString);
        System.out.println(strPadding + "Here are the tasks in your list: ");
        System.out.println(sb.toString());
        System.out.println(divString);
    }

    private static void botResponse(String resString) {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";

        System.out.println(divString);
        System.out.println(strPadding + resString);
        System.out.println(divString);
    }

    private static void greet() {
        String strPadding = "      ";
        String botName = "Baymax";
        String greeting = "Greetings, I am " + botName + ".\n" +
                strPadding + "What can I do you for?";

        botResponse(greeting);
    }

    public static void main(String[] args) {
        greet();
        run();
    }

}
