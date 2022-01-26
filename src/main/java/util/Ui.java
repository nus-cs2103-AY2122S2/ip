package util;

public class Ui {

    public void greet() {
        System.out.println(chatBox("Yawn... You woke me up! Urgh\n    What do you need?"));
    }

    public void exit() {
        System.out.println(chatBox("Bye. I don't hope to see you again soon :D"));
    }

    public String chatBox(String command){
        String tab = "    ";
        String horizontalLines = tab + "___________________________________";

        return horizontalLines + "\n" + tab + command + "\n" + horizontalLines;

    }

    public void reply(String s) {
        System.out.println(chatBox(s));
    }
}