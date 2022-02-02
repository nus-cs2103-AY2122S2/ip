public class Ui {

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
}
