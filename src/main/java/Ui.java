public class Ui {
    static String line = "\n---------------------\n";

    String getLine() {
        return line;
    }

    void greet() {
        String logo = " ____         _   _     \n"
                + "|  _ \\       | | | |\n"
                + "| |_| |      | |-| |\n"
                + "| |_| |  _   | |-| |\n"
                + "|____/  |_|  |_| |_|\n";
        System.out.println("Hello, I am B.H. How can I help you?\n" + logo + this.getLine());
    }
}
