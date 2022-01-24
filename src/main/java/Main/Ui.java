package Main;

import java.util.Scanner;

public class Ui {

    static final private String lines = "------------------------------------------------------------------------";
    static final private String logo = "´´´´¶¶¶¶¶¶´´´´´´¶¶¶¶¶¶\n" +
            "´´¶¶¶¶¶¶¶¶¶¶´´¶¶¶¶¶¶¶¶¶¶\n" +
            "´¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶´´´´¶¶¶¶\n" +
            "¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶´´´´¶¶¶¶\n" +
            "¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶´´¶¶¶¶¶\n" +
            "¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶ ´¶¶¶¶¶´\n" +
            "´´¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
            "´´´´´¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
            "´´´´´´´¶¶¶¶¶¶¶¶¶¶¶¶¶\n" +
            "´´´´´´´´´¶¶¶¶¶¶¶¶\n" +
            "´´´´´´´´´´´¶¶¶¶";

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String userInput =  sc.nextLine();
        System.out.println(lines);
        return userInput;
    }

    public void unknownCommand() {
        printWrapper("I don't know what you want! Say something valid.");
    }

    public void printWrapper(String s) {
        System.out.println(lines);
        System.out.println(s);
        System.out.println(lines);
    }

    public void showIntro() {
        printWrapper("Hmph, it's you again...\n" + logo);
    }

    public void showError(String e) {
        printWrapper(e);
    }

    public void showLoadingError() {
        printWrapper("Save File Corrupted... some task(s) can't be read!");
    }
}
