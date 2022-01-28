package util;

public class Ui {

    /**
     * greets the user
     */

    public void greet() {
        System.out.println(chatBox("Yawn... You woke me up! Urgh\n    What do you need?"));
    }

    /**
     * prints the exit message to the user
     */

    public void exit() {
        System.out.println(chatBox("Bye. I don't hope to see you again soon :D"));
    }

    /**
     * creates a chatbox UI around the message
     * @param command message from Duke
     * @return message from Duke enveloped in the chatbox UI
     */

    public String chatBox(String command){
        String tab = "    ";
        String horizontalLines = tab + "___________________________________";

        return horizontalLines + "\n" + tab + command + "\n" + horizontalLines;

    }

    /**
     * prints the message with the chatbox UI around it
     * @param s message to be printed out
     */

    public void reply(String s) {
        System.out.println(chatBox(s));
    }
}