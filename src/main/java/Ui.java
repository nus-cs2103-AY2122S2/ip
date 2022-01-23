public class Ui {

    protected FastIO io;

    public Ui() {
        io = new FastIO();
    }

    public String getInput() {
        return io.nextLine();
    }

    public void showMessage(String msg) {
        echo(msg);
    }

    public void greet() {
        echo("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    protected void echo(String input) {
        System.out.println("     " + input);
    }

    public void bye() {
        echo("Bye. Hope to see you again soon!");
    }

    public void close() {
        io.close();
    }

}
