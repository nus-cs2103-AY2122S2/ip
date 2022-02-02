package bobby;

import java.util.Scanner;

import bobby.task.Task;
import bobby.task.TaskList;

public class Ui {
    private Scanner sc;
    private final String logo =
             "─██████████████───██████████████─██████████████───██████████████───████████──████████─\n"
           + "─██░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██───██░░░░░░░░░░██───██░░░░██──██░░░░██─\n"
           + "─██░░██████░░██───██░░██████░░██─██░░██████░░██───██░░██████░░██───████░░██──██░░████─\n"
           + "─██░░██──██░░██───██░░██──██░░██─██░░██──██░░██───██░░██──██░░██─────██░░░░██░░░░██───\n"
           + "─██░░██████░░████─██░░██──██░░██─██░░██████░░████─██░░██████░░████───████░░░░░░████───\n"
           + "─██░░░░░░░░░░░░██─██░░██──██░░██─██░░░░░░░░░░░░██─██░░░░░░░░░░░░██─────████░░████─────\n"
           + "─██░░████████░░██─██░░██──██░░██─██░░████████░░██─██░░████████░░██───────██░░██───────\n"
           + "─██░░██────██░░██─██░░██──██░░██─██░░██────██░░██─██░░██────██░░██───────██░░██───────\n"
           + "─██░░████████░░██─██░░██████░░██─██░░████████░░██─██░░████████░░██───────██░░██───────\n"
           + "─██░░░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░░░██─██░░░░░░░░░░░░██───────██░░██───────\n"
           + "─████████████████─██████████████─████████████████─████████████████───────██████───────\n";
    private final String line1 = "\t====================================================================\n";
    private final String line2 = "\t====================================================================";
    private final String greeting = "Howdy! I'm Bobby\t\t(｡◕‿‿◕｡)\nWhat can I do for you?";
    private final String goodbye = "Bye! Hope to see you again soon!\n(｡^‿‿^｡)";
    private final String invalid = "Invalid command!\n(╯°□°)╯︵ ┻━┻ ︵ ╯(°□° ╯)";

    public Ui(Scanner sc) {
        this.sc = sc;
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void printLongLine() {
        System.out.print(line1);
    }

    public void printLongLine(int type) {
        if (type == 1) {
            System.out.print(line1);
        } else {
            System.out.println(line2);
        }
    }

    public void printLogo() {
        System.out.println(logo);
    }

    public void printGreeting() {
        printLongLine(1);
        System.out.println(greeting);
        printLongLine(2);
    }

    public String goodbyeMessage() {
        return goodbye;
    }

    public String invalidMessage() {
        return invalid;
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public String markMessage(Task task) {
        return "Finally... I've marked this task as done:" + task;
    }

    public String unmarkMessage(Task task) {
        return "Could you be any more lazy? I've marked this task as not done yet:" + task;

    }

    public String todoMessage(Task todo) {
        return "OK you better do this today, or else...\n(ㆆ _ ㆆ)\nAdded task:" + todo;
    }

    public String deadlineMessage(Task deadline) {
        return "Oh boy, another deadline?\n(ㆆ _ ㆆ)\nAdded task:" + deadline;
    }

    public String eventMessage(Task event) {
        return "Let's see... A new event!\nAdded task:" + event;
    }

    public String deleteMessage(Task task) {
        return "Alright I'm deleting this task:\n" + task;
    }

    public String deleteAllMessage() {
        return "Alright I'm deleting ALL tasks \n(*・‿・)ノ⌒*:･ﾟ✧";
    }

    public String printNumTasks(TaskList tasks) {
        return "Now you have " + tasks.getSize() + " in the list.";
    }

    public String printTaskList(TaskList tasks) {
        String replyMessage;
        Task currTask;
        if (tasks.isEmpty()) {
            replyMessage = "Wow you are very free now! Enjoy~ \n༼ つ ◕_◕ ༽つ";
        } else {
            replyMessage = "I've sorted and put the any deadlines/events to the top for you :)\n";
            for (int i = 0; i < tasks.getSize(); i++) {
                currTask = tasks.getIndex(i);
                int index = i + 1;
                replyMessage += index + "." + currTask + "\n";
            }
        }
        return replyMessage;
    }

    public String printFindTaskList(TaskList tasks) {
        String replyMessage;
        Task currTask;
        if (tasks.isEmpty()) {
            replyMessage = "No matching tasks in your list (≧︿≦)";
        } else {
            replyMessage = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < tasks.getSize(); i++) {
                currTask = tasks.getIndex(i);
                int index = i + 1;
                replyMessage += index + "." + currTask + "\n";
            }
        }
        return replyMessage;
    }
}
