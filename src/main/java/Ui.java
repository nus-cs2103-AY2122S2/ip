import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Convert the plain output text to chat-box style with indentation for output
     *
     * @param text : String (output text)
     * @return chat-box style output text : String
     */
    private String convertToChatBox(String text) {
        return "    ____________________________________________________________\n"
                + text.replaceAll("(?m)^", "     ") + "\n"
                + "    ____________________________________________________________\n";
    }

    public String getCommand() {
        return scanner.nextLine();
    }
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(convertToChatBox(logo));
    }
    public void sayGreeting() {
        System.out.println(convertToChatBox("Hello! I'm Pyke\n" + "What can I do for you?"));
    }
    public void sayFarewell() {
        System.out.println(convertToChatBox("Bye. Hope to see you again soon!"));
    }
    public void outputText(String text) {
        System.out.println(convertToChatBox(text));
    }
    public void outputException(String exceptionInfo) {
        System.out.println(convertToChatBox(exceptionInfo));
    }
}
