package com.duke.util;

import java.util.Scanner;

/**
 * A user interface that can show message.
 */
public class Ui {

    private final String LOGO;
    private final String LINE;
    private static final Scanner SCANNER = new Scanner(System.in);

    public Ui() {
        this.LOGO = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        this.LINE = "\t____________________________________________________________________";
    }

    /**
     * Show the welcome message when Duke is run.
     */
    public void showWelcome() {
        showLine();
        System.out.println(LOGO + "\t Hello! I'm Duke\n" + "\t What can I do for you?");
        showLine();
    }

    /**
     * Show the line
     */
    public void showLine() {
        System.out.println(LINE);
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
        return SCANNER.nextLine();
    }

    /**
     * Show the message.
     * @param msg message to show.
     */
    public void showMsg(String msg) {
        System.out.println(msg);
    }
}
