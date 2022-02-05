package backend;

public class Ui {
    public Ui(){}

    public static String errorInput() {
        return "Sorry I didnt catch that! Please make sure it is a valid command!";
    }

    public static String list(){
        return "Here are the current items in the list:";
    }

    public static String mark(String input){
        return "Nice! I've marked this task as done:\n" + input;
    }

    public static String unmark(String input){
        return "OK, I've marked this task as not done yet:\n" + input;
    }

    public static String delete(String input){
        return "Noted. I've removed this task:\n" + input;
    }

    public static String todo(String input){
        return "OK! Added this todo:\n" + input;
    }

    public static String deadline(String input){
        return "OK! Added this deadline:\n" + input;
    }

    public static String event(String input){
        return "OK! Added this event:\n" + input;
    }

    public static String find(){
        return "Here are the matching tasks in your list:";
    }

    public static String invalidTask(){
        return "task does not exist!";
    }

    public static String emptyDescription(){
        return "The description of a task cannot be empty";
    }

}