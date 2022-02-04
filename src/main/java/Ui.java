public class Ui {

    public static void displayError(String message) {
        System.out.println(message);
    }

    public static void showSuccessfulMessage(String mssg) {
        System.out.println(mssg);
    }

    public static void displayTask(Task task) {
        System.out.println(task);
    }

    public void greet() {
        String welcome = "Hi my name is Duke!";
        String assist = "How may I help you today?";
        System.out.println(welcome);
        System.out.println(assist);
    }

    public void endSession() {
        String goodbye = "Adios! See you soon:)";
        System.out.println(goodbye);
    }

    public void addLineBreak() {
        System.out.println("---------------------xx-------------------------");
    }

    public void showLoadingError() {
        System.out.println("Oops! I had problem creating/loading logs.txt");
    }
//    public static void showBlankDescriptionToDoError() {
//        System.out.println();
//    }
//
//    public static void showIncorrectTimeFormatDeadline() {
//        System.out.println();
//    }
//
//    public static void showIncorrectTimeFormatEvent() {
//        System.out.println("Incorrect time format: ensure to prefix time with '/at'");
//    }

    public void showInvalidCommandError() {
        System.out.println("sorry, this isn't a valid command yet!");
    }
}
