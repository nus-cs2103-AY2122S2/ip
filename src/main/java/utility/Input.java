package utility;

import exception.DukeException;

import java.util.Scanner;

public class Input {

    Scanner scanner;

    public Input(){
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


}
