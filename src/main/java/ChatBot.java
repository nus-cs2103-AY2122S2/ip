public class ChatBot {
    private String name;
    private String line = "-------------------------------------------------";

    public ChatBot(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.printf("%s%n %s%s%n %s%n%s%n", line, "Hello! I'm ", name , "What can I do for you", line);
    }

    public void echo(String input) {
        System.out.printf("%s%n %s%n%s%n", line, input, line);
    }

    public void quit() {
        System.out.printf("%s%n %s%n%s%n", line, "Bye. Hope to see you again soon!", line);
    }
}
