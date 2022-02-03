package siri;

import java.util.Scanner;

class Ui {
    private Scanner sc;
    private String logo;
    public Ui(String logo) {
        this.logo = logo;
        sc = new Scanner(System.in);
    }

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
     * Scans for next user input.
     *
     * @return String of the input that is taken in.
     */
    public String takeInput() {
        System.out.println("What can I do for you?");
        return this.sc.nextLine();
    }

    /**
     * Class method to print a separator between different commands.
     */
    public static void separator() {
        System.out.println("====================");
    }

    /**
     * Closes scanner and print exit message.
     */
    public void exit() {
        this.sc.close();
        System.out.println("Bye!! Hope to see you again soon!!");
    }
}
