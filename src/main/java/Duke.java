import java.util.*;

public class Duke {


    private static void sizeCheck(List<Task> inputList) throws DukeException {
        if (inputList.size() >= 100)
            throw new DukeException("Too many items in the list! Please consider deleting old items.");
    }

    // Included error handling for invalid commands, large list, and invalid list index.
    private static void run() throws DukeException {
        Scanner sc = new Scanner(System.in);
        boolean exitFlag = false;
        List<Task> taskList = new ArrayList<>();

        while(!exitFlag) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");

            try {
                switch (inputArr[0].toLowerCase()) {
                    case "exit":
                        botResponse("Pleasure to be of service, see you soon!");
                        exitFlag = true;
                        break;
                    case "list":
                        listResponse(taskList);
                        break;
                    case "mark":
                    case "unmark":
                        int lastIdx = Integer.parseInt(inputArr[1]) - 1;
                        if (lastIdx < 0 || lastIdx >= taskList.size()) {
                            throw new DukeException("Please enter a valid index.");
                        }
                        Task toggleTask = taskList.get(lastIdx);
                        toggleTask.isMarked = inputArr[0].equals("mark");
                        markResponse(toggleTask);
                        break;
                    case "todo":
                        sizeCheck(taskList);
                        taskList.add(new ToDo(input));
                        subtaskResponse(taskList);
                        break;
                    case "event":
                        sizeCheck(taskList);
                        taskList.add(new Event(input.split(" /at ")));
                        subtaskResponse(taskList);
                        break;
                    case "deadline":
                        sizeCheck(taskList);
                        taskList.add(new Deadline(input.split(" /by ")));
                        subtaskResponse(taskList);
                        break;
                    case "remove":
                    case "delete":
                        int deleteIdx = Integer.parseInt(inputArr[1]) - 1;
                        if (deleteIdx < 0 || deleteIdx >= taskList.size()) {
                            throw new DukeException("Please enter a valid index.");
                        }
                        deleteResponse(taskList.remove(deleteIdx), taskList.size());
                        break;
                    case "clear-list":
                        taskList.clear();
                        botResponse("The list has been cleared!");
                        break;
                    default:
                        throw new DukeException("Invalid command!");
                }
            } catch (DukeException e) {
                botResponse(e.toString());
            }


        }
        sc.close();
    }

    public static void deleteResponse(Task deletedTask, int size) {
        String divString = "    ---------------------------------------------";
        String strPadding = "      ";
        String ackString = "Noted. I've removed this task: ";
        String sizeString = String.format("Now you have %d tasks in the list.", size);

        System.out.println(divString);
        System.out.println(strPadding + ackString);
        System.out.println(strPadding + "   " + deletedTask);
        System.out.println(strPadding + sizeString);
        System.out.println(divString);
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
        System.out.println(strPadding + "   " + task);
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
        System.out.println(sb);
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

    public static void main(String[] args) throws DukeException {
        greet();
        run();
    }

}
