package siri;

import java.util.Scanner;

class Ui {
    public static Scanner sc = new Scanner(System.in);
    //deals with interactions with the user
    private static final String logo = "   -----      O    -----      O\n" +
                                        " /   _   \\   __   |       \\   __\n" + 
                                        " |  | |__|  |  |  |   O   |  |  |\n" +
                                        " |   ----\\  |  |  |       /  |  |\n" +
                                        "  \\ __   |  |  |  |   ---    |  |\n" +
                                        " |---|   |  |  |  |       \\  |  |\n" +
                                        "  \\______/  |__|  |___|\\___\\ |__|\n";

    public static void startUp() {
        System.out.println("Hello from\n" + logo);
    }

    public static void startUpSavedData() {
        System.out.println("Welcome back!!");
    }

    public static String takeInput() {
        System.out.println("What can I do for you?");
        return sc.nextLine();
    }

    public static void separator() {
        System.out.println("====================");
    }
    
    public static void exit() {
        System.out.println("Bye!! Hope to see you again soon!!");
    }
}