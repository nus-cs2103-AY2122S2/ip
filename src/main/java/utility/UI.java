package utility;

import exception.DukeException;

import java.util.Scanner;

/**
 * Handle user input
 */
public class UI {

    Scanner scanner;

    public UI(){
        this.scanner = new Scanner(System.in);
    }

    public void startMessage(){
        System.out.println("wassup im duke what can i help you?");
    }

    public void print(String msg) {
        System.out.println(msg);
    }

    public String readLine(){
        return this.scanner.nextLine();
    }

    public void printException(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void showLine() {
        System.out.println("_____________________");
    }


}
