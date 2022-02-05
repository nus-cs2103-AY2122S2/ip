package backend;

public class Ui {
    public Ui(){}

    public static String list(){
        return "Here are the current items in the list:";
    }

    public static String mark(){
        return "Nice! I've marked this task as done:";
    }

    public static String unmark(){
        return "OK, I've marked this task as not done yet:";
    }

    public static String delete(){
        return "Noted. I've removed this task: ";
    }

    public static String todo(){
        return "OK! Added this todo:";
    }

    public static String deadline(){
        return "OK! Added this deadline:";
    }

    public static String event(){
        return "OK! Added this event:";
    }

    public static String find(){
        return "Here are the matching tasks in your list:";
    }
}