package siri;

import java.util.Scanner;

class Ui {
    private String logo;
    public Ui(String logo) {
        this.logo = logo;
    }

    public static Scanner sc = new Scanner(System.in);

    /**
     * Class method to print the start up message.
     */
    public void startUp() {
        System.out.println("Hello from\n" + this.logo);
    }

    /**
     * Class method to print additional message if there is a backlog data.
     */
    public static void startUpSavedData() {
        System.out.println("Welcome back!!");
    }

    /**
     * Class method to allow for input to be taken in.
     * 
     * @return String of the input that is taken in.
     */
    public static String takeInput() {
        System.out.println("What can I do for you?");
        return sc.nextLine();
    }

    /**
     * Class method to print a separator between different commands.
     */
    public static void separator() {
        System.out.println("====================");
    }
    
    /**
     * Class method to be printed when exiting the application.
     */
    public static void exit() {
        sc.close();
        System.out.println("Bye!! Hope to see you again soon!!");
    }
}