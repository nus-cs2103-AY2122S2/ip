public class Action {

    Action() {}

    void greet() {
        String dash = "____________________________________________________________\n";
        String greet = "Hello! I'm JiaMing\nWhat can I do for you?\n";
        String output = String.format("%s%s%s", dash, greet, dash);
        System.out.println(output);
    }

    void echo(String phrase) {
        String dash = "____________________________________________________________\n";
        String output = String.format("%s%s%s", dash, phrase, dash);
        System.out.println(output);
    }

    void bye() {
        String dash = "____________________________________________________________\n";
        String bye = "Bye. Hope to see you again soon!";
        String output = String.format("%s%s%s", dash, bye, dash);
        System.out.println(output);
    }

    @Override
    public String toString() {
        return "this is an Action (Parent)";
    }

}
