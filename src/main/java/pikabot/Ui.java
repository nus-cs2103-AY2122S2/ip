package pikabot;

import pikabot.task.Task;

public class Ui {

    static String LINE = "_________________________________";
    static String INDENTATION = "     ";

    //opening
    public static void printWelcomeText() {
        System.out.println(INDENTATION + LINE + "\n" + INDENTATION + "Hello! I'm PikaBot" +
            "\n" + INDENTATION + "What can I do for you? シ\n" + INDENTATION + LINE);
    }

    //ending
    public static void printClosingText() {
        System.out.println(INDENTATION + LINE + "\n" + INDENTATION + "Bye. Hope to see you again!" +
            "\n" + INDENTATION + LINE);
    }

    public static void printListOfTasks(TaskList taskList) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Here are the tasks in your list:");

        int taskNumber = 1;
        int length = taskList.noOfTasks();

        while (taskNumber <= length) {
            System.out.println(INDENTATION + taskNumber + "." +
                taskList.get(taskNumber - 1));
            taskNumber++;
        }
        System.out.println(INDENTATION + LINE);
    }

    public static void indicateMarked(Task task) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + task);
        System.out.println(INDENTATION + LINE);
    }

    public static void indicateUnmarked(Task task) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Ok, I've marked this task as not done yet:");
        System.out.println(INDENTATION + task);
        System.out.println(INDENTATION + LINE);
    }

    public static void indicateAddedTask(Task task, TaskList taskList) {
        System.out.println(INDENTATION + LINE + "\n" +
            INDENTATION + "Got it. I've added this task:" + "\n" +
            INDENTATION + "  " + task + "\n" +
            INDENTATION + "Now you have " + taskList.noOfTasks() + " tasks in the list." +
            "\n" +
            INDENTATION + LINE);
    }

    public static void indicateRemovedTask(Task task, TaskList taskList) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Noted. I've removed this task:");
        System.out.println(INDENTATION + "  " + task);
        System.out.println(INDENTATION + "Now you have " + taskList.noOfTasks() + " tasks in the list");
        System.out.println(INDENTATION + LINE);
    }


    public static void printExceptionMessage(Exception e) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + e.getMessage());
        System.out.println(INDENTATION + LINE);
    }

    public static void printExceptionCustomisedMessage(String message) {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + message);
        System.out.println(INDENTATION + LINE);
    }
}
