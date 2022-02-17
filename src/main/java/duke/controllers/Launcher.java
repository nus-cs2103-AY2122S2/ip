package duke.controllers;

import duke.Duke;
import javafx.application.Application;

public class Launcher extends Exception {

    /**
     * Entry point for Duke GUI app
     * @param args
     */
    public static void main(String[] args) {
        try {
            Application.launch(Duke.class, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
