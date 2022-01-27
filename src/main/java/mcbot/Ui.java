package mcbot;

import java.util.ArrayList;
import mcbot.task.Task;
import mcbot.exception.McBotException;

public class Ui {
    private String frameLine = "==========================================";
    private String logo = "\n" +
            "___  ___    ______       _\n" +
            "|  \\/  |    | ___ \\     | |\n" +
            "| .  . | ___| |_/ / ___ | |_\n" +
            "| |\\/| |/ __| ___ \\/ _ \\| __|\n" +
            "| |  | | (__| |_/ / (_) | |_\n" +
            "\\_|  |_/\\___\\____/ \\___/ \\__|\n" +
            "\n\n";
    
    public Ui() {}
    
    public void welcomeLine() {
        System.out.println(logo);
        System.out.println(frameLine);
        System.out.println("Ahoy! Me name be McBot.\nTell me lad, what do you want?");
        System.out.println(frameLine);
    }
    
    public void printFrame() {
        System.out.println(frameLine);
    }
    
    public void showLoadingError() {
        System.out.println("File not found. I will create one for you");
    }

    public void byeLine() {
        System.out.println("Arghh! This ain't the last time ye see me lad");
    }
    
    public void markLine() {
        System.out.println("Aye I'ave marked it done:");
    }

    public void markDuplication() {
        System.out.println("You fool!! It is already mark'd");
    }
    
    public void unmarkLine() {
        System.out.println("Aye I'ave unmarked it:");
    }
    
    public void unmarkDuplication() {
        System.out.println("You fool!! It is already unmark'd");
    }
    
    public void printTask(Task t) {
        System.out.println(t);
    }

    public void listTask(ArrayList<Task> arrList) {
        int i = 1;
        System.out.println("Here are yer tasks boi:");
        for (Task task : arrList) {
            System.out.println(i + "." + task);
            i++;
        }
    }

    public void addTodoLine(Task t, int size) {
        System.out.println("Got 'em down as todo:");
        System.out.println(t);
        System.out.println("Ye now have " + size + " tasks in list lad");
    }

    public void markError(String error) {
        switch(error) {
        case "missingData": {
            System.out.println("I don't know which one yer referring to");
            break;
        }
        case "notInteger": {
            System.out.println("I only accept integers boi");
            break;
        }
        case "integerNotFound": {
            System.out.println("Don't mess with me boi, that number is not in the list");
            break;
        }
        default: {
            System.out.println("New Error detected");
            break;
        }
        }
    }
    
    public void deleteError(String error) {
        switch (error) {
        case "empty": {
            System.out.println("Fool, I need a number to know which one to delete");
            break;
        }
        case "notInteger": {
            System.out.println("Boi, I only accept integers here for deletion");
            break;
        }
        }
    }

    public void taskError(String error) {
        switch (error) {
        case "emptyTask": {
            System.out.println("Sorry boi, ye can't leave todo task empty");
            break;
        }
        case "deadlineFormat": {
            System.out.println("Fool, follow this format: deadline -TASKNAME- /by DD/MM/YYYY HHMM");
            break;
        }
        case "eventFormat": {
            System.out.println("Fool, follow this format: event -TASKNAME- /at DD/MM/YYYY HHMM-");
            break;
        }
        case "datetimeFormat": {
            System.out.println("Yer date and time should follow this format: DD/MM/YYYY HHMM");
            break;
        }
        case "emptyFindTask":
            System.out.println("Yer cant leave it empty mate");
        }
    }

    public void addDeadlineLine(Task t, int size) {
        System.out.println("Got 'em down as deadline:");
        System.out.println(t);
        System.out.println("Ye now have " + size + " tasks in list lad");
    }
    
    public void printError(McBotException e) {
        System.out.println(e.getMessage());
    }

    public void addEventLine(Task t, int size) {
        System.out.println("Got 'em down as event:");
        System.out.println(t);
        System.out.println("Ye now have " + size + " tasks in list lad");
    }

    public void deleteLine(Task t, int size) {
        System.out.println("Aye, I 'ave deleted it");
        System.out.println(t);
        System.out.println("Ye now have " + size + " tasks in list lad");
    }

    public void noMatch() {
        System.out.println("No matching task found, sorry mate");
    }

    public void printFind() {
        System.out.println("I'ave found this: ");
    }
}
