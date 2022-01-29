import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {

    private String NAME = "\nDuke: ";
    private String GREETING = "\nDuke: Hello! I'm Duke\n      What can I do for you?\n";
    private String CLOSING = "\nDuke:  Bye. Hope to see you again soon!";

    public void showWelcome(){
        System.out.println(GREETING);
    }

    public void showClosing(){
        System.out.println(CLOSING);
    }

    public void printList(DukeList a){
        try {
            BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));
            w.write("\nDuke:\nHere are the tasks in your list:\n");
            w.write(a.toString());
            w.newLine();
            w.flush();
        } catch(IOException e) {
            System.out.println("Error when printing list");
        }
    }

    public String readInput(){
        Scanner s = new Scanner(System.in);
        String ans = "";
        try{
            ans = s.nextLine();
        } catch(NoSuchElementException e) {
            System.out.println("Input a command!");
        }
        return ans;
    }

    public void markMessage(Task x){
        System.out.println(NAME + "Nice! I've marked this task as done:\n       " + x.show());
    }

    public void unMarkMessage(Task x){
        System.out.println(NAME + "OK, I've marked this task as not done yet:\n      " + x.show());
    }

    public void unknownMessage(){
        System.out.println(NAME + "OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
}
