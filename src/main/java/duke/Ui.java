package duke;

import java.util.Scanner;
//Deals with interactions with users
public class Ui {
    public void showLoadingError(){
        System.out.println("LOADING ERROR");
    }

    public void showWelcome(){
        System.out.println("Hello!! I am duke.Duke, your humble personal chatbot.\n" +
                "What can I do for you?");
    }

    public String readCommand(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showError(String s) {
        System.out.println("ERROR: " + s);
    }
}
