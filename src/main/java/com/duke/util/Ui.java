package com.duke.util;

import java.util.Scanner;

/**
 * A user interface that can show message.
 */
public class Ui {

    private final String logo;
    private final String line;
    private static final Scanner scanner = new Scanner(System.in);

    public Ui() {
        this.logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        this.line = "\t____________________________________________________________________";
    }

    /**
     * Show the welcome message when Duke is run.
     */
    public void showWelcome() {
        showLine();
        System.out.println(logo + "\t Hello! I'm Duke\n" + "\t What can I do for you?");
        showLine();
    }

    /**
     * Show the line
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Show the error message when there is an exception
     * @param msg error message.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Read the input by the user.
     * @return input by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Show the message.
     * @param msg message to show.
     */
    public void showMsg(String msg) {
        System.out.println(msg);
    }
}
