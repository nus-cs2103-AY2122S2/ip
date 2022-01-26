import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui () {
        this.sc =  new Scanner(System.in);
    }

    public void greet() {
        String greeting = encloseWithin("Hello! I'm Duke\nWhat can I do for you?\n");
        System.out.println(greeting);
    }

    public void sayGoodbye() {
        String goodbye = encloseWithin("Bye. Hope to see you again soon!\n");
        System.out.println(goodbye);
        this.sc.close();
    }

    public void say(String str) {
        if (!str.equals("null")) {
            String say = encloseWithin(str);
            System.out.println(say);
        }
    }

    public String askForInput() {
        return this.sc.nextLine();
    }

    private String encloseWithin(String str) {
        String Header = "____________________________________________________________";
        StringBuilder string = new StringBuilder();
        string.append(Header).append("\n").append(str).append(Header).append("\n");
        return string.toString();
    }
}
