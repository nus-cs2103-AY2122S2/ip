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
                case "list": listResponse(taskList);
                             break;
                case "mark":
                case "unmark": Task toggleTask = taskList.get(Integer.parseInt(inputArr[1]) - 1);
                               toggleTask.isMarked = inputArr[0].equals("mark");
                               markResponse(toggleTask);
                               break;
                case "todo": ToDo newToDo = new ToDo(input);
                             taskList.add(newToDo);
                             subtaskResponse(taskList);
                             break;
                case "event": Event newEvent = new Event(input.split(" /at "));
                              taskList.add(newEvent);
                              subtaskResponse(taskList);
                              break;
                case "deadline": Deadline newDL = new Deadline(input.split(" /by "));
                                 taskList.add(newDL);
                                 subtaskResponse(taskList);
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

    public static void subtaskResponse(List<Task> taskList) {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";
        String ackString = "Got it. I've added this task: ";
        String sizeString = String.format("Now you have %d tasks in the list.", taskList.size());

        System.out.println(divString);
        System.out.println(strPadding + ackString);
        System.out.println(strPadding + "   " + taskList.get(taskList.size() - 1));
        System.out.println(strPadding + sizeString);
        System.out.println(divString);
    }

    private static void markResponse(Task task) {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";
        String markedString = "Nice! I've marked this task as done: ";
        String unmarkedString = "OK, I've marked this task as not done yet: ";
        String outString = task.isMarked ? markedString : unmarkedString;

        System.out.println(divString);
        System.out.println(strPadding + outString);
        System.out.println(strPadding + "   " + task.toString());
        System.out.println(divString);
    }

    private static void listResponse(List<Task> taskList) {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            sb.append(String.format(strPadding + "%02d." + curr.toString(), i + 1));
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
