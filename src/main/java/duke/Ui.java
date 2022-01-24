package duke;

public class Ui {
    String welcomeMessage;
    String byeMessage;
    public Ui(){
        welcomeMessage =
                "       __  \n"
                + "(____()'`; \n"
                + "/,    /` \n"
                + "\\\\\"--\\\\\n"
                + "Woof, I am (supposed to look like) a dog bot. \n What do you want from me?\n";

        byeMessage = "Bye! Hope not to see you again :)";
    };
    public void showLoadingError(){
        System.out.println("Error loading file! A new list will be created.");
    }
    public void printWelcomeMessage(){
        System.out.println(welcomeMessage);
    }
    public void printByeMessage(){
        System.out.println(byeMessage);
    }
}
