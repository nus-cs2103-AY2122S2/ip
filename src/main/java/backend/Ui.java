package backend;

public class Ui {
    public Ui(){}

    public static void list(){
        System.out.println("Here are the current items in the list:");
    }

    public static void mark(){
        System.out.println("Nice! I've marked this task as done:");
    }

    public static void unmark(){
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public static void delete(){
        System.out.println("Noted. I've removed this task: ");
    }

    public static void todo(){
        System.out.println("OK! Added this todo:");
    }

    public static void deadline(){
        System.out.println("OK! Added this deadline:");
    }

    public static void event(){
        System.out.println("OK! Added this event:");
    }
}